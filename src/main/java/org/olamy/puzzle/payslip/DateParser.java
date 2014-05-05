package org.olamy.puzzle.payslip;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * <p>
 * Simple helper class for parsing date
 * We assume dates can have 2 formats (the parsing will be tried in this order):
 * <ul>
 * <li>01 March 2014</li>
 * <li>01 March (without the year so the year will be assumed to be the current one</li>
 * </ul>
 * </p>
 * <b>currently this class is not ThreadSafe as {@link java.text.SimpleDateFormat} is not!</b>
 * TODO use a ThreadLocal to construct SimpleDateFormat instances
 *
 * @author Olivier Lamy
 */
public class DateParser
{
    // protected for testing reasing
    protected static final SimpleDateFormat FORMAT_WITH_YEAR = new SimpleDateFormat( "dd MMMM yyyy" );

    protected static final Pattern FORMAT_WITH_YEAR_PATTERN = Pattern.compile( "^\\d{1,2} [a-zA-Z]+ \\d{4}$" );

    protected static final SimpleDateFormat FORMAT_NO_YEAR = new SimpleDateFormat( "dd MMMM" );

    protected static final Pattern FORMAT_NO_YEAR_PATTERN = Pattern.compile( "^\\d{1,2} [a-zA-Z]+$" );

    private static final Logger LOGGER = LoggerFactory.getLogger( DateParser.class );

    public static final DateParser INSTANCE = new DateParser();

    private DateParser()
    {
        // helper class so private and nothing to do
    }

    /**
     * @param dateString a String representing a date according to the accepted format
     * @return the date from the string content or <code>null</code> if the String doesn't match any format
     */
    public Date parseDateString( String dateString )
    {
        LOGGER.debug( "parsing dateString: '{}'", dateString );
        if ( StringUtils.isEmpty( dateString ) )
        {
            return null;
        }
        SimpleDateFormat formatToUse = matchDateFormats( dateString );
        if ( formatToUse == null )
        {
            return null;
        }

        try
        {
            Date date = formatToUse.parse( dateString );

            // we need to configure the year
            if ( formatToUse.equals( FORMAT_NO_YEAR ) )
            {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime( date );
                calendar.set( Calendar.YEAR, Calendar.getInstance().get( Calendar.YEAR ) );
                date = calendar.getTime();
            }

            return date;
        }
        catch ( ParseException e )
        {
            LOGGER.debug( "cannot parse dateString: '{}'", dateString, e );
        }
        return null;
    }

    /**
     * @param dateString
     * @return the appropriate {@link java.text.SimpleDateFormat} or <code>NULL</code> if not matching any
     */
    public SimpleDateFormat matchDateFormats( String dateString )
    {
        if ( StringUtils.isEmpty( dateString ) )
        {
            return null;
        }

        if ( FORMAT_NO_YEAR_PATTERN.matcher( dateString ).matches() )
        {
            return FORMAT_NO_YEAR;
        }

        if ( FORMAT_WITH_YEAR_PATTERN.matcher( dateString ).matches() )
        {
            return FORMAT_WITH_YEAR;
        }

        return null;
    }

}
