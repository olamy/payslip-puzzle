package org.olamy.puzzle.payslip;

/**
 * @author Olivier Lamy
 */
public interface PayslipCalculator
{

    PayslipResult calculate( EmployeeData employeeData )
        throws PayslipCalculatorException;

}
