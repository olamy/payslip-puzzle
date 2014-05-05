package org.olamy.puzzle.payslip;

/**
 * @author Olivier Lamy
 */
public class PayslipCalculatorException
    extends Exception
{
    public PayslipCalculatorException( String message )
    {
        super( message );
    }

    public PayslipCalculatorException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
