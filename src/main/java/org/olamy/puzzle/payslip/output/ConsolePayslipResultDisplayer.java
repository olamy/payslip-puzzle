package org.olamy.puzzle.payslip.output;

import org.apache.commons.lang3.time.FastDateFormat;

/**
 * @author Olivier Lamy
 */
public class ConsolePayslipResultDisplayer
    implements PayslipResultDisplayer
{
    private final FastDateFormat fastDateFormat = FastDateFormat.getInstance( "dd MMMM" );

    @Override
    public void display( PayslipResultDisplayerRequest displayerRequest )
    {
        // Output (name, pay period, gross income, income tax, net income, super):
        // David Rudd,01 March – 31 March,5004,922,4082,450
        System.out.println( "---------------------------------" );
        System.out.println( "Payslip result calculation:" );
        System.out.println( "---------------------------------" );

        for ( PayslipResult payslipResult : displayerRequest.getPayslipResults() )
        {
            StringBuilder display = new StringBuilder( payslipResult.getEmployeeData().getFirstName() ) //
                .append( " " ) //
                .append( payslipResult.getEmployeeData().getLastName() ) //
                .append( ',' ) //
                .append( fastDateFormat.format( payslipResult.getEmployeeData().getStartDate() ) ) //
                .append( " - " ) //
                .append( fastDateFormat.format( payslipResult.getEmployeeData().getEndDate() ) ) //
                .append( ',' ) //
                .append( payslipResult.getGrossIncome() ) //
                .append( ',' ) //
                .append( payslipResult.getIncomeTax() ) //
                .append( ',' ) //
                .append( payslipResult.getNetIncome() ) //
                .append( ',' ) //
                .append( payslipResult.getSuperValue() );

            System.out.println( display.toString() );

        }
    }
}
