package org.olamy.puzzle.payslip;

import org.olamy.puzzle.payslip.output.PayslipResult;

import java.util.List;

/**
 * @author Olivier Lamy
 */
public interface PayslipCalculator
{

    PayslipResult calculate( EmployeeData employeeData )
        throws PayslipCalculatorException;

    List<PayslipResult> calculate( List<EmployeeData> employeeDatas )
        throws PayslipCalculatorException;

}
