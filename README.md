Payslip Puzzle
--------------

get sources:

`git clone https://github.com/olamy/payslip-puzzle.git`

You need to use jdk 1.7 and Apache Maven to build it and execute the test.

`mvn clean install `

will compile, execute the tests and build a jar.

You can now execute the application on any csv input file using a shaded jar:

`java -jar target/payslip-puzzle-1.0-SNAPSHOT.jar -f src/test/resources/sample_input.csv`

For help use:

`java -jar target/payslip-puzzle-1.0-SNAPSHOT.jar -h`

