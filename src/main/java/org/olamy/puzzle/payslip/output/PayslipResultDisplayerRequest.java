package org.olamy.puzzle.payslip.output;

import java.util.Collections;
import java.util.List;

/**
 * @author Olivier Lamy
 */
public class PayslipResultDisplayerRequest
{

    private List<PayslipResult> payslipResults;

    public PayslipResultDisplayerRequest( List<PayslipResult> payslipResults )
    {
        this.payslipResults = payslipResults;
    }

    public List<PayslipResult> getPayslipResults()
    {
        return payslipResults == null ? Collections.<PayslipResult>emptyList() : this.payslipResults;
    }

    public void setPayslipResults( List<PayslipResult> payslipResults )
    {
        this.payslipResults = payslipResults;
    }

    @Override
    public String toString()
    {
        return "PayslipResultDisplayerRequest{" +
            "payslipResults=" + payslipResults +
            '}';
    }
}
