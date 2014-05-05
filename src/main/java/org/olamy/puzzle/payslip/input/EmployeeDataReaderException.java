package org.olamy.puzzle.payslip.input;

/**
 * @author Olivier Lamy
 */
public class EmployeeDataReaderException
    extends Exception
{
    public EmployeeDataReaderException( String message )
    {
        super( message );
    }

    public EmployeeDataReaderException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
