package org.olamy.puzzle.payslip.input;

import java.io.File;

/**
 * @author Olivier Lamy
 */
public class EmployeeDataReaderRequest
{

    private File inputFile;

    /**
     * the parser or data input will fail or not in case of invalid data
     * if <code>false</code> the parser will ignore the error and continue on next entries
     */
    private boolean failOnInvalidData = true;

    public EmployeeDataReaderRequest()
    {
        // no op
    }

    public File getInputFile()
    {
        return inputFile;
    }

    public void setInputFile( File inputFile )
    {
        this.inputFile = inputFile;
    }

    public EmployeeDataReaderRequest inputFile( File inputFile )
    {
        this.inputFile = inputFile;
        return this;
    }

    public boolean isFailOnInvalidData()
    {
        return failOnInvalidData;
    }

    public void setFailOnInvalidData( boolean failOnInvalidData )
    {
        this.failOnInvalidData = failOnInvalidData;
    }

    public EmployeeDataReaderRequest failOnInvalidData( boolean failOnInvalidData )
    {
        this.failOnInvalidData = failOnInvalidData;
        return this;
    }
}
