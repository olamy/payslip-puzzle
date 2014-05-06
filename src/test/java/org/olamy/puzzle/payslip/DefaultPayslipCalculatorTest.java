package org.olamy.puzzle.payslip;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;
import org.olamy.puzzle.payslip.output.PayslipResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Olivier Lamy
 */
public class DefaultPayslipCalculatorTest
{

    SimpleDateFormat sdf = new SimpleDateFormat( "dd MMM" );

    final Logger logger = LoggerFactory.getLogger( getClass() );

    PayslipCalculator payslipCalculator;

    @Before
    public void setup()
    {
        Injector injector = Guice.createInjector( new CsvFilePayslipProcessorModule() );
        payslipCalculator = injector.getInstance( PayslipCalculator.class );
    }

    @Test
    public void test_sample_data1()
        throws Exception
    {

        // David,Rudd,60050,9%,01 March – 31 March
        EmployeeData data = new EmployeeData( "David", "Rudd", 60050, 9, //
                                              DateParser.INSTANCE.parseDateString( "01 March" ), //
                                              DateParser.INSTANCE.parseDateString( "31 March" ) );

        PayslipResult result = payslipCalculator.calculate( data );

        assertNotNull( result );

        logger.info( "result: {}", result );

        // result David Rudd,01 March – 31 March,5004,922,4082,450

        assertEquals( "David", result.getEmployeeData().getFirstName() );
        assertEquals( "Rudd", result.getEmployeeData().getLastName() );
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime( result.getEmployeeData().getStartDate() );
        assertEquals( 1, startCalendar.get( Calendar.DAY_OF_MONTH ) );
        assertEquals( 2, startCalendar.get( Calendar.MONTH ) );
        assertEquals( 2014, startCalendar.get( Calendar.YEAR ) );

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime( result.getEmployeeData().getEndDate() );
        assertEquals( 31, endCalendar.get( Calendar.DAY_OF_MONTH ) );
        assertEquals( 2, endCalendar.get( Calendar.MONTH ) );
        assertEquals( 2014, endCalendar.get( Calendar.YEAR ) );

        assertEquals( 5004, result.getGrossIncome() );
        assertEquals( 922, result.getIncomeTax() );
        assertEquals( 4082, result.getNetIncome() );
        assertEquals( 450, result.getSuperValue() );
    }

    @Test
    public void test_sample_data2()
        throws Exception
    {

        //Ryan,Chen,120000,10%,01 March – 31 March
        EmployeeData data = new EmployeeData( "Chen", "Ryan", 120000, 10, //
                                              DateParser.INSTANCE.parseDateString( "01 March" ), //
                                              DateParser.INSTANCE.parseDateString( "31 March" ) );

        PayslipResult result = payslipCalculator.calculate( data );

        assertNotNull( result );

        logger.info( "result: {}", result );

        // result Ryan Chen,01 March – 31 March,10000,2696,7304,1000

        assertEquals( "Chen", result.getEmployeeData().getFirstName() );
        assertEquals( "Ryan", result.getEmployeeData().getLastName() );
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime( result.getEmployeeData().getStartDate() );
        assertEquals( 1, startCalendar.get( Calendar.DAY_OF_MONTH ) );
        assertEquals( 2, startCalendar.get( Calendar.MONTH ) );
        assertEquals( 2014, startCalendar.get( Calendar.YEAR ) );

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime( result.getEmployeeData().getEndDate() );
        assertEquals( 31, endCalendar.get( Calendar.DAY_OF_MONTH ) );
        assertEquals( 2, endCalendar.get( Calendar.MONTH ) );
        assertEquals( 2014, endCalendar.get( Calendar.YEAR ) );

        assertEquals( 10000, result.getGrossIncome() );
        assertEquals( 2696, result.getIncomeTax() );
        assertEquals( 7304, result.getNetIncome() );
        assertEquals( 1000, result.getSuperValue() );
    }

    @Test
    public void test_sample_data3()
        throws Exception
    {

        //Ryan,Chen,120000,10%,01 March – 31 March
        EmployeeData data = new EmployeeData( "Foo", "Bar", 150000, 9.5, //
                                              DateParser.INSTANCE.parseDateString( "01 March" ), //
                                              DateParser.INSTANCE.parseDateString( "31 March" ) );

        PayslipResult result = payslipCalculator.calculate( data );

        assertNotNull( result );

        logger.info( "result: {}", result );

        assertEquals( "Foo", result.getEmployeeData().getFirstName() );
        assertEquals( "Bar", result.getEmployeeData().getLastName() );
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime( result.getEmployeeData().getStartDate() );
        assertEquals( 1, startCalendar.get( Calendar.DAY_OF_MONTH ) );
        assertEquals( 2, startCalendar.get( Calendar.MONTH ) );
        assertEquals( 2014, startCalendar.get( Calendar.YEAR ) );

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime( result.getEmployeeData().getEndDate() );
        assertEquals( 31, endCalendar.get( Calendar.DAY_OF_MONTH ) );
        assertEquals( 2, endCalendar.get( Calendar.MONTH ) );
        assertEquals( 2014, endCalendar.get( Calendar.YEAR ) );

        assertEquals( 12500, result.getGrossIncome() );
        assertEquals( 3621, result.getIncomeTax() );
        assertEquals( 8879, result.getNetIncome() );
        assertEquals( 1187, result.getSuperValue() );
    }

    @Test( expected = PayslipCalculatorException.class )
    public void test_exception_with_null_employeeData()
        throws Exception
    {
        payslipCalculator.calculate( (EmployeeData) null );
    }

    @Test( expected = PayslipCalculatorException.class )
    public void test_exception_with_empty_data()
        throws Exception
    {
        payslipCalculator.calculate( new EmployeeData( "Chen", "Ryan", 120000, 10, null, null ) );
    }

    @Test( expected = PayslipCalculatorException.class )
    public void test_exception_with_annual_income()
        throws Exception
    {
        payslipCalculator.calculate( new EmployeeData( "Chen", "Ryan", 0, 10, null, null ) );
    }

}
