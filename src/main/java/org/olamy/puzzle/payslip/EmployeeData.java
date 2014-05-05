package org.olamy.puzzle.payslip;

import java.util.Date;

/**
 * Model for minimal data needed to calculate employee salary for a month
 *
 * @author Olivier Lamy
 */
public class EmployeeData
{
    private final String firstName;

    private final String lastName;

    /**
     * we assume no decimal for an annual salary
     */
    private final long annualSalary;


    private final double superRate;

    private final Date startDate;

    private final Date endDate;

    public EmployeeData( String firstName, String lastName, long annualSalary, double superRate, Date startDate,
                         Date endDate )
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.annualSalary = annualSalary;
        this.superRate = superRate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public long getAnnualSalary()
    {
        return annualSalary;
    }

    public double getSuperRate()
    {
        return superRate;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    @Override
    public String toString()
    {
        return "EmployeeData{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", annualSalary=" + annualSalary +
            ", superRate=" + superRate +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            '}';
    }
}
