package org.olamy.puzzle.payslip;

import org.olamy.puzzle.payslip.input.CsvFileEmployeeDataReader;
import org.olamy.puzzle.payslip.input.EmployeeDataReader;

/**
 * @author Olivier Lamy
 */
public class CsvFilePayslipProcessorModule
    extends AbstractPayslipProcessorModule
{

    @Override
    protected void configure()
    {
        super.configure();
        bind( EmployeeDataReader.class ).to( CsvFileEmployeeDataReader.class );
    }
}


