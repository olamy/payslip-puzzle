package org.olamy.puzzle.payslip.input;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.olamy.puzzle.payslip.DateParser;
import org.olamy.puzzle.payslip.EmployeeData;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Olivier Lamy
 */
public class CsvFileEmployeeDataReaderTest
{

    CsvFileEmployeeDataReader csvFileEmployeeDataReader = new CsvFileEmployeeDataReader();

    @Test
    public void parse_single_line()
        throws Exception
    {
        EmployeeData employeeData =
            csvFileEmployeeDataReader.parseFileLine( "David,Rudd,60050,9%,01 March - 31 March", true );

        Assertions.assertThat( employeeData ).isNotNull().isEqualTo( new EmployeeData( "David", "Rudd", 60050, 9, //
                                                                                       DateParser.INSTANCE.parseDateString(
                                                                                           "01 March" ), //
                                                                                       DateParser.INSTANCE.parseDateString(
                                                                                           "31 March" ) ) );

    }

    @Test
    public void read_sample_data_file()
        throws Exception
    {
        //
        //David,Rudd,60050,9%,01 March - 31 March
        //Ryan,Chen,120000,10%,01 March - 31 March
        //Foo,Bar,150000,9.5%,01 Jun - 30 Jun

        Path sampleDataPath =
            Paths.get( System.getProperty( "basedir" ), "src", "test", "resources", "sample_input.csv" );

        List<EmployeeData> employeeDatas =
            csvFileEmployeeDataReader.readData( new EmployeeDataReaderRequest().inputFile( sampleDataPath.toFile() ) );

        Assertions.assertThat( employeeDatas ).isNotNull() //
            .isNotEmpty() //
            .hasSize( 3 ) //
            .contains( new EmployeeData( "David", "Rudd", 60050, 9, //
                                         DateParser.INSTANCE.parseDateString( "01 March" ), //
                                         DateParser.INSTANCE.parseDateString( "31 March" ) ),
                       new EmployeeData( "Ryan", "Chen", 120000, 10, //
                                         DateParser.INSTANCE.parseDateString( "01 March" ), //
                                         DateParser.INSTANCE.parseDateString( "31 March" ) ),
                       new EmployeeData( "Foo", "Bar", 150000, 9.5, //
                                         DateParser.INSTANCE.parseDateString( "01 Jun" ), //
                                         DateParser.INSTANCE.parseDateString( "30 Jun" ) )
            );

    }
}
