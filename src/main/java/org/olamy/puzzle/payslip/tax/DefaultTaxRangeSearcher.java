package org.olamy.puzzle.payslip.tax;

import org.olamy.puzzle.payslip.EmployeeData;
import org.olamy.puzzle.payslip.tax.TaxRange;
import org.olamy.puzzle.payslip.tax.TaxRangeSearcher;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Olivier Lamy
 */
public class DefaultTaxRangeSearcher
    implements TaxRangeSearcher
{

    private List<TaxRange> taxRanges;

    public DefaultTaxRangeSearcher()
    {
        this.taxRanges = new ArrayList<>( 5 );

        //Taxable income   Tax on this income
        //0 - $18,200     Nil
        //$18,201 - $37,000       19c for each $1 over $18,200
        //$37,001 - $80,000       $3,572 plus 32.5c for each $1 over $37,000
        //$80,001 - $180,000      $17,547 plus 37c for each $1 over $80,000
        //$180,001 and over       $54,547 plus 45c for each $1 over $180,000

        this.taxRanges.add( new TaxRange( 0, 18200, 0, 0 ) );
        this.taxRanges.add( new TaxRange( 18201, 37000, 0, 0.19 ) );
        this.taxRanges.add( new TaxRange( 37001, 80000, 3572, 0.325 ) );
        this.taxRanges.add( new TaxRange( 80001, 180000, 17547, 0.37 ) );
        this.taxRanges.add( new TaxRange( 180001, Long.MAX_VALUE, 54547, 0.45 ) );
    }

    @Override
    public TaxRange findTaxeRange( EmployeeData employeeData )
    {
        for ( TaxRange taxRange : taxRanges )
        {
            if ( employeeData.getAnnualSalary() > +taxRange.getDownRange() //
                && employeeData.getAnnualSalary() <= taxRange.getUpRange() )
            {
                return taxRange;
            }
        }

        return null;
    }
}
