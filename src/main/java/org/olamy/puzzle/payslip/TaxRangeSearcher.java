package org.olamy.puzzle.payslip;

/**
 * We define an interface for possible enhancement (coming from database or external services)
 *
 * @author Olivier Lamy
 */
public interface TaxRangeSearcher
{
    /**
     * @param employeeData
     * @return the {@link TaxRange} matching the employee data or <code>null</code> in none found
     */
    TaxRange findTaxeRange( EmployeeData employeeData );

    /**
     * for the purpose of this puzzle we use the one with hardcoded values
     */
    static final TaxRangeSearcher INSTANCE = new DefaultTaxRangeSearcher();

}
