package org.olamy.puzzle.payslip.input;

import org.apache.commons.lang3.StringUtils;
import org.olamy.puzzle.payslip.DateParser;
import org.olamy.puzzle.payslip.EmployeeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Olivier Lamy
 */
@Singleton
@Named( "employeeDataReader#csvfile" )
public class CsvFileEmployeeDataReader
    implements EmployeeDataReader
{

    private final Logger logger = LoggerFactory.getLogger( getClass() );

    private static final Pattern SUPER_PERCENTAGE_PATTERN = Pattern.compile( "(\\d\\.*\\d*)%" );

    @Override
    public List<EmployeeData> readData( EmployeeDataReaderRequest employeeDataReaderRequest )
        throws EmployeeDataReaderException
    {

        File file = employeeDataReaderRequest.getInputFile();

        if ( file == null || !file.exists() )
        {
            throw new EmployeeDataReaderException( "input file cannot be null and must exist" );
        }

        // TODO could be improved with some streaming as all lines are in memory now
        try
        {
            List<String> lines = Files.readAllLines( file.toPath(), Charset.defaultCharset() );
            if ( lines == null || lines.isEmpty() )
            {
                return Collections.emptyList();
            }

            List<EmployeeData> employeeDatas = new ArrayList<>( lines.size() );

            for ( String line : lines )
            {
                logger.debug( "parsing line: '{}'", line );
                EmployeeData employeeData = parseFileLine( line, employeeDataReaderRequest.isFailOnInvalidData() );
                if ( employeeData != null )
                {
                    employeeDatas.add( employeeData );
                }
            }

            return employeeDatas;
        }
        catch ( IOException e )
        {
            throw new EmployeeDataReaderException( e.getMessage(), e );
        }

    }

    protected EmployeeData parseFileLine( String line, boolean failOnInvalidData )
        throws EmployeeDataReaderException
    {
        String[] datas = StringUtils.split( line, ',' );
        // the format is: David,Rudd,60050,9%,01 March – 31 March
        // we control we have at least 5 splits
        if ( datas.length != 5 )
        {
            if ( failOnInvalidData )
            {
                throw new EmployeeDataReaderException( "found line with invalid data: " + line );
            }
            logger.warn( "skip line with invalid data: '{}'", line );
            return null;
        }

        // date entry is date1 - date2
        // so we split it
        int dateSeparatorIndex = datas[4].indexOf( '-' );
        if ( dateSeparatorIndex < 0 )
        {
            if ( failOnInvalidData )
            {
                throw new EmployeeDataReaderException( "no valid data separator for dates: " + datas[4] );
            }
            logger.warn( "no valid data separator for dates: '{}'", datas[4] );
            return null;
        }
        String startDate = StringUtils.trim( datas[4].substring( 0, dateSeparatorIndex ) );
        String endDate = StringUtils.trim( datas[4].substring( dateSeparatorIndex + 1, datas[4].length() ) );

        String superPercentage = extractPercentage( datas[3] );
        if ( StringUtils.isEmpty( superPercentage ) )
        {
            if ( failOnInvalidData )
            {
                throw new EmployeeDataReaderException(
                    "cannot extract super percentage value from string: " + datas[3] );
            }
            logger.warn( "cannot extract super percentage value from string: '{}'", datas[3] );
            return null;
        }

        return new EmployeeData( StringUtils.trim( datas[0] ), //
                                 StringUtils.trim( datas[1] ), //
                                 Long.parseLong( StringUtils.trim( datas[2] ) ), //
                                 Double.parseDouble( superPercentage ), //
                                 DateParser.INSTANCE.parseDateString( startDate ), //
                                 DateParser.INSTANCE.parseDateString( endDate ) );
    }

    /**
     * @param percentageString can contains 8.5% or 10%
     * @return the number part of the String
     */
    protected String extractPercentage( String percentageString )
    {
        Matcher matcher = SUPER_PERCENTAGE_PATTERN.matcher( StringUtils.trim( percentageString ) );
        if ( !matcher.matches() )
        {
            return null;
        }
        return matcher.group( 1 );
    }

}
