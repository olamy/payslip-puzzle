package org.olamy.puzzle.payslip;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

/**
 * @author Olivier Lamy
 */
@Parameters
public class PayslipCalculatorCliParameters
{

    @Parameter( names = { "-f", "--file" }, description = "employees datas csv file", required = true )
    protected String csvFile;

    @Parameter( names = { "-h", "--help" }, description = "show help" )
    protected boolean help;

    @Parameter( names = { "-foid", "--fail-on-invalid-data" },
                description = "software will fail in case of any invalid datas" )
    protected boolean failOnInvalidData = true;

}
