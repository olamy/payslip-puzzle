package org.olamy.puzzle.payslip;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.commons.lang3.StringUtils;
import org.olamy.puzzle.payslip.input.EmployeeDataReader;
import org.olamy.puzzle.payslip.input.EmployeeDataReaderException;
import org.olamy.puzzle.payslip.input.EmployeeDataReaderRequest;
import org.olamy.puzzle.payslip.output.PayslipResult;
import org.olamy.puzzle.payslip.output.PayslipResultDisplayer;
import org.olamy.puzzle.payslip.output.PayslipResultDisplayerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Olivier Lamy
 */
public class PayslipCalculatorCli
{

    public static void main( String[] args )
    {

        Logger logger = LoggerFactory.getLogger( PayslipCalculatorCli.class );
        final PayslipCalculatorCliParameters parameters = new PayslipCalculatorCliParameters();
        JCommander jCommander = new JCommander( parameters );
        jCommander.setProgramName( "Payslip Calculator" );
        try
        {
            jCommander.parse( args );
        }
        catch ( ParameterException e )
        {
            System.out.println( "Incorrect options usage." );
            jCommander.usage();
            return;
        }

        if ( parameters.help )
        {
            jCommander.usage();
            return;
        }

        // so currently the only input implementation is csv file so use it directly
        // ideally we could have a validator per input implementation and validate datas

        Injector injector = Guice.createInjector( new CsvFilePayslipProcessorModule() );

        File csvFile = Paths.get( parameters.csvFile ).toFile();

        if ( !csvFile.exists() )
        {
            logger.error( "csvFile '{}' not exists", csvFile );
            System.exit( 1 );
        }

        EmployeeDataReaderRequest employeeDataReaderRequest = new EmployeeDataReaderRequest() //
            .inputFile( csvFile ) //
            .failOnInvalidData( parameters.failOnInvalidData );

        EmployeeDataReader employeeDataReader = injector.getInstance( EmployeeDataReader.class );

        PayslipCalculator payslipCalculator = injector.getInstance( PayslipCalculator.class );

        PayslipResultDisplayer payslipResultDisplayer = injector.getInstance( PayslipResultDisplayer.class );

        try
        {
            List<EmployeeData> employeeDatas = employeeDataReader.readData( employeeDataReaderRequest );

            List<PayslipResult> payslipResults = payslipCalculator.calculate( employeeDatas );

            payslipResultDisplayer.display( new PayslipResultDisplayerRequest( payslipResults ) );

        }
        catch ( EmployeeDataReaderException e )
        {
            logger.error( "issue reading input: {}", e.getMessage(), e );
            System.exit( 1 );
        }
        catch ( PayslipCalculatorException e )
        {
            logger.error( "issue calculating payslips: {}", e.getMessage(), e );
            System.exit( 1 );
        }


    }

}
