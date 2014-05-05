package org.olamy.puzzle.payslip;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Olivier Lamy
 */
public class DefaultPayslipCalculator
    implements PayslipCalculator
{

    private final Logger logger = LoggerFactory.getLogger( getClass() );

    @Override
    public PayslipResult calculate( EmployeeData employeeData )
        throws PayslipCalculatorException
    {

        validateEmployeeData( employeeData );

        TaxRange taxRange = TaxRangeSearcher.INSTANCE.findTaxeRange( employeeData );

        if ( taxRange == null )
        {
            throw new PayslipCalculatorException( "cannot find TaxRange for employeeData:" + employeeData );
        }

        long grossIncome = calculateGrossIncome( employeeData.getAnnualSalary() );

        long incomeTax = calculateIncomeTax( employeeData, taxRange );

        long netIncome = grossIncome - incomeTax;

        double superValue = Math.floor( grossIncome * employeeData.getSuperRate() / 100 );

        return new PayslipResult() //
            .employeeData( employeeData ) //
            .grossIncome( grossIncome ) //
            .netIncome( netIncome ) //
            .incomeTax( incomeTax ) //
            .superValue( (long) superValue );

    }

    protected long calculateGrossIncome( long annualSalary )
    {
        double grossIncome = Math.floor( annualSalary / 12 );
        return (long) grossIncome;
    }

    protected long calculateIncomeTax( EmployeeData employeeData, TaxRange taxRange )
    {

        // no need to calculate here
        if ( taxRange.getBaseTaxe() == 0 && taxRange.getPerDollarIncrease() == 0 )
        {
            return 0;
        }

        //  (3,572 + (60,050 - 37,000) x 0.325) / 12  = 921.9375 (round up) = 922

        double incomeTaxe = ( taxRange.getBaseTaxe() + //
            ( ( employeeData.getAnnualSalary() - ( taxRange.getDownRange() - 1 ) ) * taxRange.getPerDollarIncrease() ) )
            / 12;

        incomeTaxe = Math.ceil( incomeTaxe );

        logger.debug( "found incomeTax: {} for employeeDate: {}", incomeTaxe, employeeData );

        return (long) incomeTaxe;

    }

    private void validateEmployeeData( EmployeeData employeeData )
        throws PayslipCalculatorException
    {
        if ( employeeData == null )
        {
            throw new PayslipCalculatorException( "employeeData cannot be null" );
        }

        // we validate some entries
        if ( employeeData.getStartDate() == null )
        {
            throw new PayslipCalculatorException( "employeeData.startDate cannot be null" );
        }
        if ( employeeData.getEndDate() == null )
        {
            throw new PayslipCalculatorException( "employeeData.endDate cannot be null" );
        }
        if ( employeeData.getAnnualSalary() < 1 )
        {
            throw new PayslipCalculatorException( "employeeData.annualSalary must be at least 1" );
        }
        if ( employeeData.getSuperRate() < 0 )
        {
            throw new PayslipCalculatorException( "employeeData.superRate must be at least 0" );
        }
        if ( StringUtils.isEmpty( employeeData.getFirstName() ) )
        {
            throw new PayslipCalculatorException( "employeeData.firstName cannot be empty" );
        }
        if ( StringUtils.isEmpty( employeeData.getLastName() ) )
        {
            throw new PayslipCalculatorException( "employeeData.lastName cannot be empty" );
        }
    }

}
