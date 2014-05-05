package org.olamy.puzzle.payslip.input;

import org.olamy.puzzle.payslip.EmployeeData;

import java.util.List;

/**
 * @author Olivier Lamy
 */
public interface EmployeeDataReader
{
    List<EmployeeData> readData( EmployeeDataReaderRequest employeeDataReaderRequest )
        throws EmployeeDataReaderException;

}
