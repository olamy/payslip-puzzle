package org.olamy.puzzle.payslip;


import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author Olivier Lamy
 */
public class DateParserTest
{

    @Test
    public void match_date_with_year()
    {
        SimpleDateFormat sdf = DateParser.INSTANCE.matchDateFormats( "01 December 2013" );
        assertNotNull( sdf );
        assertEquals( DateParser.FORMAT_WITH_YEAR, sdf );
    }

    @Test
    public void match_date_without_year()
    {
        SimpleDateFormat sdf = DateParser.INSTANCE.matchDateFormats( "01 December" );
        assertNotNull( sdf );
        assertEquals( DateParser.FORMAT_NO_YEAR, sdf );
    }

    @Test
    public void match_date_null()
    {
        SimpleDateFormat sdf = DateParser.INSTANCE.matchDateFormats( null );
        assertNull( sdf );
    }

    @Test
    public void match_date_empty()
    {
        SimpleDateFormat sdf = DateParser.INSTANCE.matchDateFormats( "" );
        assertNull( sdf );
    }

    @Test
    public void date_without_year()
        throws Exception
    {
        Date date = DateParser.INSTANCE.parseDateString( "01 May" );
        assertNotNull( date );
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( date );

        assertEquals( 1, calendar.get( Calendar.DAY_OF_MONTH ) );
        assertEquals( 4, calendar.get( Calendar.MONTH ) );
        assertEquals( Calendar.getInstance().get( Calendar.YEAR ), calendar.get( Calendar.YEAR ) );
    }

    @Test
    public void date_with_year()
        throws Exception
    {
        Date date = DateParser.INSTANCE.parseDateString( "17 December 2014" );
        assertNotNull( date );
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( date );

        assertEquals( 17, calendar.get( Calendar.DAY_OF_MONTH ) );
        assertEquals( 11, calendar.get( Calendar.MONTH ) );
        assertEquals( 2014, calendar.get( Calendar.YEAR ) );
    }

    @Test
    public void date_invalid_format()
        throws Exception
    {
        Date date = DateParser.INSTANCE.parseDateString( "01/01/2014" );
        assertNull( date );
    }

    @Test
    public void date_null()
        throws Exception
    {
        Date date = DateParser.INSTANCE.parseDateString( null );
        assertNull( date );
    }

}
