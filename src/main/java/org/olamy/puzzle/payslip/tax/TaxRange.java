package org.olamy.puzzle.payslip.tax;

/**
 * <p>
 * Model class for taxe ranges.
 * sample data content:
 * <ul>
 * <li>0 - $18,200     Nil</li>
 * <li>$18,201 - $37,000, 19c for each $1 over $18,200</li>
 * </ul>
 * </p>
 *
 * @author Olivier Lamy
 */
public class TaxRange
{
    /**
     * the min value of this tax range
     */
    private final long downRange;

    /**
     * the max value for this tax range
     */
    private final long upRange;

    /**
     * the minimum tax amount to pay for this tax range
     */
    private final long baseTaxe;

    /**
     * the per dollar amount to pay for each $1 income more than the downRange
     */
    private final double perDollarIncrease;


    public TaxRange( long downRange, long upRange, long baseTaxe, double perDollarIncrease )
    {
        this.downRange = downRange;
        this.upRange = upRange;
        this.baseTaxe = baseTaxe;
        this.perDollarIncrease = perDollarIncrease;
    }

    public long getDownRange()
    {
        return downRange;
    }

    public long getUpRange()
    {
        return upRange;
    }

    public long getBaseTaxe()
    {
        return baseTaxe;
    }

    public double getPerDollarIncrease()
    {
        return perDollarIncrease;
    }

    @Override
    public String toString()
    {
        return "TaxeRange{" +
            "downRange=" + downRange +
            ", upRange=" + upRange +
            ", baseTaxe=" + baseTaxe +
            ", perDollarIncrease=" + perDollarIncrease +
            '}';
    }
}
