package org.olamy.puzzle.payslip;

import com.google.inject.AbstractModule;
import org.olamy.puzzle.payslip.output.ConsolePayslipResultDisplayer;
import org.olamy.puzzle.payslip.output.PayslipResultDisplayer;
import org.olamy.puzzle.payslip.tax.DefaultTaxRangeSearcher;
import org.olamy.puzzle.payslip.tax.TaxRangeSearcher;

/**
 * @author Olivier Lamy
 */
public abstract class AbstractPayslipProcessorModule
    extends AbstractModule
{

    @Override
    protected void configure()
    {
        bind( PayslipCalculator.class ).to( DefaultPayslipCalculator.class );
        bind( TaxRangeSearcher.class ).to( DefaultTaxRangeSearcher.class );
        bind( PayslipResultDisplayer.class ).to( ConsolePayslipResultDisplayer.class );
    }
}
