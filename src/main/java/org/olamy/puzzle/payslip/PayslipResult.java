package org.olamy.puzzle.payslip;

/**
 * Model for a payslip calculation result
 *
 * @author Olivier Lamy
 */
public class PayslipResult
{

    private EmployeeData employeeData;

    // note all values are rounded

    private long grossIncome;

    private long incomeTax;

    private long netIncome;

    private long superValue;

    public PayslipResult()
    {
        // no op
    }

    public PayslipResult( EmployeeData employeeData, long grossIncome, long incomeTax, long netIncome, long superValue )
    {
        this.employeeData = employeeData;
        this.grossIncome = grossIncome;
        this.incomeTax = incomeTax;
        this.netIncome = netIncome;
        this.superValue = superValue;
    }

    public EmployeeData getEmployeeData()
    {
        return employeeData;
    }

    public void setEmployeeData( EmployeeData employeeData )
    {
        this.employeeData = employeeData;
    }

    public PayslipResult employeeData( EmployeeData employeeData )
    {
        this.employeeData = employeeData;
        return this;
    }

    public long getGrossIncome()
    {
        return grossIncome;
    }

    public void setGrossIncome( long grossIncome )
    {
        this.grossIncome = grossIncome;
    }

    public PayslipResult grossIncome( long grossIncome )
    {
        this.grossIncome = grossIncome;
        return this;
    }

    public long getIncomeTax()
    {
        return incomeTax;
    }

    public void setIncomeTax( long incomeTax )
    {
        this.incomeTax = incomeTax;
    }

    public PayslipResult incomeTax( long incomeTax )
    {
        this.incomeTax = incomeTax;
        return this;
    }

    public long getNetIncome()
    {
        return netIncome;
    }

    public void setNetIncome( long netIncome )
    {
        this.netIncome = netIncome;
    }

    public PayslipResult netIncome( long netIncome )
    {
        this.netIncome = netIncome;
        return this;
    }

    public long getSuperValue()
    {
        return superValue;
    }

    public void setSuperValue( long superValue )
    {
        this.superValue = superValue;
    }

    public PayslipResult superValue( long superValue )
    {
        this.superValue = superValue;
        return this;
    }

    @Override
    public String toString()
    {
        return "PayslipResult{" +
            "employeeData=" + employeeData +
            ", grossIncome=" + grossIncome +
            ", incomeTax=" + incomeTax +
            ", netIncome=" + netIncome +
            ", superValue=" + superValue +
            '}';
    }
}
