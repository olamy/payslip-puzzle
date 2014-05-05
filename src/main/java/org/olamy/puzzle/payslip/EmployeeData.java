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

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        {
            return true;
        }
        if ( o == null || getClass() != o.getClass() )
        {
            return false;
        }

        EmployeeData that = (EmployeeData) o;

        if ( annualSalary != that.annualSalary )
        {
            return false;
        }
        if ( Double.compare( that.superRate, superRate ) != 0 )
        {
            return false;
        }
        if ( !endDate.equals( that.endDate ) )
        {
            return false;
        }
        if ( !firstName.equals( that.firstName ) )
        {
            return false;
        }
        if ( !lastName.equals( that.lastName ) )
        {
            return false;
        }
        if ( !startDate.equals( that.startDate ) )
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result;
        long temp;
        result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + (int) ( annualSalary ^ ( annualSalary >>> 32 ) );
        temp = Double.doubleToLongBits( superRate );
        result = 31 * result + (int) ( temp ^ ( temp >>> 32 ) );
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        return result;
    }
}
