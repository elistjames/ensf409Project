/**
 * Active Team Member:
 @author     chad Holst <a href="mailto:chad.holst1@ucalgary.ca">chad.holst1@ucalgary.ca</a>
 @version    1.9
 @since      1.0
 */
package edu.ucalgary.ensf409;
import org.junit.*;
import java.sql.*;

/**
 * This class tests the database read and write methods in regards to the Class arrays that represent
 * their respective furniture category which hold their categorical information based on the table columns
 * in SQL
 */
public class DatabaseTest
{

    private Database databaseTest;
    private Connection dbConnectTest;

    /**
     * setUpTest() creates a DataBase object before each test with a different USERNAME and PASSWORD for testing purposes but uses
     * the same data
     */
    @Before
    public void setUpTests()
    {
        databaseTest = new Database("jdbc:mysql://localhost/inventory", "scm", "ensf409");
        // create Database object for testing before each test
    }

    /**
     * testInitializeConnection() tests the connection with the SQL server which allows access to the database.
     *  We have set the actual boolean value to be true. We attempt to establish a connection and if it successful,
     *  then the isValid() method will return true.
     */
    @Test
    public void testInitializeConnection()
    {
        // setup
        boolean actual = true;
        boolean expected = false;

        // run test
        databaseTest.initializeConnection();
        dbConnectTest = databaseTest.getdbConnect();
        try
        {
            expected = dbConnectTest.isValid(10); // if connection is valid then expected = true
            dbConnectTest.close();
        }
        catch (SQLException | NullPointerException e)
        {
            e.printStackTrace();
        }
        // verify
        Assert.assertEquals("The truth value for expected must be true to verify that the connection is valid", expected, actual);
    }
    /**
     * testUpdateChairs() will test populating a Chair array from the database and into the Database class Chair[] array, then
     * compares the data with a hardcoded representation of original data. This is done through the process of calling the writeChairs()
     * method to get the hardcoded representation of the data which is in the Chair table in SQL. After, it compares
     * the hardcoded representation with the data in that is in Chair[] array in Database class and Asserts that
     * both arrays are exactly the same with assertArrayEquals().
     */
    @Test
    public void testUpdateChairs()
    {
        // setup
        databaseTest.initializeConnection(); // connect to SQL server
        String[][] actual = writeChairs(); // calls a helper method to get hardcoded String[][] representation of Chair table in SQL
        int actualRowSize = actual.length; // assign row length in hardcoded String[][]
        int actualColSize = actual[0].length; // assign col length in hardcoded String[][]

        // run test
        databaseTest.updateChairs(); // load the data from SQL into the Chair furniture category array in Database class
        String[][] expected = chairsToStringArray(); // calls a helper method to load the data from the Chair[] array in Database class and convert it to String[][] array.
        int expectRowSize = expected.length; // assign row length retrieved Class[] array converted into String[][]
        int expectedColSize = expected[0].length; // assign column length retrieved Class[] array converted into String[][]

        // verify
        Assert.assertArrayEquals(expected, actual); // checks if arrays are exactly the same
        Assert.assertEquals("Row length are not the same for Chair local and database", expectRowSize, actualRowSize); // checks row size
        Assert.assertEquals("Col length are not the same for Chair local and database", expectedColSize,actualColSize); // checks col size
    }
    /**
     * testUpdateDesks() will test populating a Desk array from the database and into the Database class Desk[] array, then
     * compares the data with a hardcoded representation of original data. This is done through the process of calling the writeDesks()
     * method to get the hardcoded representation of the data which is in the the Desk table in SQL. After, it compares
     * the hardcoded representation with the data in that is in Desk[] array in Database class and asserts that
     * both arrays are exactly the same with assertArrayEquals().
     */
    @Test
    public void testUpdateDesks()
    {
        // setup
        databaseTest.initializeConnection();
        String[][] actual = writeDesks(); // calls a helper method to get hardcoded String[][] representation of Desk table in SQL
        int actualRowSize = actual.length; // assign row length in hardcoded String[][]
        int actualColSize = actual[0].length; // assign col length in hardcoded String[][]

        // run test
        databaseTest.updateDesks(); // load the data from SQL into the Desk furniture category array in Database class
        String[][] expected = desksToStringArray(); // calls a helper method to load the data from the Desk[] array in Database class and convert it to String[][] array.
        int expectRowSize = expected.length; // assign row length retrieved Class[] array converted into String[][]
        int expectedColSize = expected[0].length; // assign column length retrieved Class[] array converted into String[][]

        // verify
        Assert.assertArrayEquals(expected, actual); // checks if arrays are exactly the same.
        Assert.assertEquals("Row length are not the same for Desk local and database", expectRowSize, actualRowSize); // checks row size
        Assert.assertEquals("Col length are not the same for Desk local and database", expectedColSize,actualColSize); // checks col size
    }
    /**
     * testUpdateFilings() will test populating a Filing array from the database and into the Database class Filing[] array, then
     * compares the data with a hardcoded representation of original data. This is done through the process of calling the writeFilings()
     * method to get the hardcoded representation of the data which is in the the Filing table in SQL. After, it compares
     * the hardcoded representation with the data in that is in Filing[] array in Database class and asserts that
     * both arrays are exactly the same with assertArrayEquals().
     */
    @Test
    public void testUpdateFilings()
    {
        // setup
        databaseTest.initializeConnection();
        String[][] actual = writeFiling();// calls a helper method to get hardcoded String[][] representation of Filing table in SQL
        int actualRowSize = actual.length; // assign row length in hardcoded String[][]
        int actualColSize = actual[0].length; // assign col length in hardcoded String[][]

        // run test
        databaseTest.updateFilings(); // load the data from SQL into the Filing array in Database class
        String[][] expected = filingsToStringArray(); // calls a helper method to load the data from the Filing[] array in Database class and convert it to String[][] array.
        int expectRowSize = expected.length; // assign row length retrieved Class[] array converted into String[][]
        int expectedColSize = expected[0].length; // assign column length retrieved Class[] array converted into String[][]
      
        // verify
        Assert.assertArrayEquals(expected, actual); // checks if arrays are exactly the same
        Assert.assertEquals("Row length are not the same for Filing local and database", expectRowSize, actualRowSize); // checks row size
        Assert.assertEquals("Col length are not the same for Filing local and database", expectedColSize,actualColSize); // checks col size

    }
    /**
     * testUpdateLamps() will test populating a Lamp array from the database and into the Database class Lamp[] array, then
     * compares the data with a hardcoded representation of original data. This is done through the process of calling the writeFilings()
     * method to get the hardcoded representation of the data which is in the the Lamp table in SQL. After, it compares
     * the hardcoded representation with the data in that is in Lamp[] array in Database class and asserts that
     * both arrays are exactly the same with assertArrayEquals()..
     */
    @Test
    public void testUpdateLamps()
    {
        // setup
        databaseTest.initializeConnection();
        String[][] actual = writeLamp(); // calls a helper method to get hardcoded String[][] representation of Lamp table in SQL
        int actualRowSize = actual.length; // assign row length in hardcoded String[][]
        int actualColSize = actual[0].length; // assign col length in hardcoded String[][]

        // run test
        databaseTest.updateLamps(); // load the data from SQL into the Lamp array in Database class
        String[][] expected = lampsToStringArray();  // calls a helper method to load the data from the Lamp[] array in Database class and convert it to String[][] array.
        int expectRowSize = expected.length; // assign row length retrieved Class[] array converted into String[][]
        int expectedColSize = expected[0].length; // assign column length retrieved Class[] array converted into String[][]


        // verify
        Assert.assertArrayEquals(expected, actual); // checks if arrays are exactly the same
        Assert.assertEquals("Row length are not the same for Filing local and database", expectRowSize, actualRowSize); // checks row size
        Assert.assertEquals("Col length are not the same for Filing local and database", expectedColSize,actualColSize); // checks col size
    }
    /**
     * testUpdateManufacturer() will test populating a Manufacturer array from the database and into the Database class Manufacturer[] array, then
     * compares the data with a hardcoded representation of original data. This is done through the process of calling the writeManufacturer()
     * method to get the hardcoded representation of the data which is in the the Manufacturer table in SQL. After, it compares
     * the hardcoded representation with the data in that is in Manufacturer[] array in Database class and asserts that
     * both arrays are exactly the same with assertArrayEquals().
     */
    @Test
    public void testUpdateManufacturer()
    {
        // setup
        databaseTest.initializeConnection();
        String[][] actual = writeManufacturer(); // calls a helper method to get hardcoded String[][] representation of Manufacturer table in SQL
        int actualRowSize = actual.length; // assign row length in hardcoded String[][]
        int actualColSize = actual[0].length; // assign col length in hardcoded String[][]

        // run test
        databaseTest.updateMans(); // load the data from SQL into the Manufacturer array in Database class
        String[][] expected = manusToStringArray(); // calls a helper method to load the data from the Desk[] array in Database class and convert it to String[][] array.
        int expectRowSize = expected.length; // assign row length retrieved Class[] array converted into String[][]
        int expectedColSize = expected[0].length; // assign column length retrieved Class[] array converted into String[][]

        // verify
        Assert.assertArrayEquals(expected, actual); // checks if Arrays are the same
        Assert.assertEquals("Row length are not the same for Manufacturer local and database", expectRowSize, actualRowSize); // checks row size
        Assert.assertEquals("Col length are not the same for Manufacturer local and database", expectedColSize,actualColSize); // checks col size
    }
    /**
     * sendChairTest() will test storing data from the Chair[] array into the Chair table in SQL. This is done through
     * the process of calling writeChairs() to receive a String[][] representation of the original hardcoded data. Then,
     * changes a couple of values within a row to ensure that the updated array data is stored in the Chair SQL table.
     * We instantiate a Chair object, use the setChairRow() method to set updated data from Chair object. Then call the
     * sendChair() method to store the updated data into Chair table in SQL. Then, assert that the arrays are exactly the same.
     */
    @Test
    public void sendChairTest()
    {
        // setup
        databaseTest.initializeConnection();
        String[][] actual = writeChairs(); // calls a helper method to get hardcoded String[][] representation of Chair table in SQL
        actual[0][0] = "C0914"; // changes values of a row in the hardcoded String[][] representation
        actual[0][1] = "Mesh";
        actual[0][2] = "Y";
        actual[0][3] = "Y";
        actual[0][4] = "Y";
        actual[0][5] = "Y";
        actual[0][6] = "10";
        actual[0][7] = "001";
        int actualRowSize = actual.length; // assign row length in hardcoded String[][]
        int actualColSize = actual[0].length; // assign col length in hardcoded String[][]

        // run test
        databaseTest.updateChairs(); // initializes Chair[] array with data that is in SQL
        Chair chair = new Chair("C0914","Mesh","Y","Y","Y","Y",10,"001");
        databaseTest.setChairRow(chair, 0); // changes a row with different values to ensure updated data from array is stored in SQL
        databaseTest.sendChair(); // stores Chair[] array data into the Chair table in SQL
        String[][] expected = chairsToStringArray(); // calls a helper method to load the data from the Chair[] array in Database class and convert it to String[][] array.
        int expectRowSize = expected.length; // assign row length retrieved Class[] array converted into String[][]
        int expectedColSize = expected[0].length; // assign column length retrieved Class[] array converted into String[][]

        // verify
        Assert.assertArrayEquals(expected, actual); // checks if arrays are exactly the same
        Assert.assertEquals("Row length are not the same for Chair local and database", expectRowSize, actualRowSize); // checks row size
        Assert.assertEquals("Col length are not the same for Chair local and database", expectedColSize,actualColSize); // checks col size

        // after test, put original data back in the Chair table in SQL
        Chair oldChair = new Chair("C0914", "Task", "N", "N", "Y", "Y", 50, "002");
        databaseTest.setChairRow(oldChair, 0);
        databaseTest.sendChair();

    }
    /**
     * sendDeskTest() will test storing data from the Desk[] array into the Desk table in SQL. This is done through
     * the process of calling writeDesks() to receive a String[][] representation of the original hardcoded data. Then,
     * changes a couple of values within a row to ensure that the updated array data is stored in the Desk SQL table.
     * We instantiate a Desk object, use the setDeskRow() method to set updated data from Chair object. Then call the
     * sendDesk() method to store the updated data into Desk table in SQL. Then, assert that the arrays are exactly the same.
     */
    @Test
    public void sendDeskTest()
    {
        // setup
        databaseTest.initializeConnection();
        String[][] actual = writeDesks(); // calls a helper method to get String[][] representation of Desk table in SQL
        actual[0][0] = "D0890";          // changes values of a row in the hardcoded String[][] representation
        actual[0][1] = "Adjustable";
        actual[0][2] = "Y";
        actual[0][3] = "Y";
        actual[0][4] = "Y";
        actual[0][5] = "15";
        actual[0][6] = "002";
        int actualRowSize = actual.length; // assign row length in hardcoded String[][]
        int actualColSize = actual[0].length; // assign col length in hardcoded String[][]

        // run test
        databaseTest.updateDesks(); // initializes Desk[] array with data that is in SQL
        Desk desk = new Desk("D0890", "Adjustable", "Y", "Y", "Y", 15, "002");
        databaseTest.setDeskRow(desk, 0); // changes a row with different values to ensure updated data from array is stored in SQL
        databaseTest.sendDesk(); // stores Desk[] array data into the Desk table in SQL
        String[][] expected = desksToStringArray(); // calls a helper method to load the data from the Desk[] array in Database class and convert it to String[][] array.
        int expectRowSize = expected.length; // assign row length retrieved Class[] array converted into String[][]
        int expectedColSize = expected[0].length; // assign column length retrieved Class[] array converted into String[][]

        // verify
        Assert.assertArrayEquals(expected, actual); // checks if arrays are exactly the same
        Assert.assertEquals("Row length are not the same for Desk local and database", expectRowSize, actualRowSize); // checks row size
        Assert.assertEquals("Col length are not the same for Desk local and database", expectedColSize,actualColSize); // checks col size

        // after test, store old data back into row
        Desk oldDesk = new Desk("D0890", "Traditional", "N", "N", "Y",25,"002");
        databaseTest.setDeskRow(oldDesk, 0);
        databaseTest.sendDesk();
    }
    /**
     * sendFilingTest() will test storing data from the Filing[] array into the Filing table in SQL. This is done through
     * the process of calling writeFilings() to receive a String[][] representation of the original hardcoded data. Then,
     * changes a couple of values within a row to ensure that the updated array data is stored in the Filing SQL table.
     * We instantiate a Filing object, use the setFilingRow() method to set updated data from Filing object. Then, call the
     * sendFiling() method to store the updated data into Filing table in SQL. Then, assert that the arrays are exactly the same.
     */
    @Test
    public void sendFilingTest()
    {
        // setup
        databaseTest.initializeConnection();
        String[][] actual = writeFiling(); // calls a helper method to get String[][] representation of Filing table in SQL
        actual[0][0] = "F001";            // changes values of a row in the hardcoded String[][] representation
        actual[0][1] = "Medium";
        actual[0][2] = "Y";
        actual[0][3] = "Y";
        actual[0][4] = "Y";
        actual[0][5] = "110";
        actual[0][6] = "002";
        int actualRowSize = actual.length; // assign row length in hardcoded String[][]
        int actualColSize = actual[0].length; // assign col length in hardcoded String[][]

        // run test
        databaseTest.updateFilings(); // initializes Filing[] array with data that is in SQL
        Filing filing = new Filing("F001", "Medium", "Y", "Y", "Y", 110, "002");
        databaseTest.setFilingRow(filing, 0); // changes a row with different values to ensure updated data from array is stored in SQL
        databaseTest.sendFiling();  // stores Filing[] array data into the Filing table in SQL
        String[][] expected = filingsToStringArray(); // calls a helper method to load the data from the Filing[] array in Database class and convert it to String[][] array.
        int expectRowSize = expected.length; // assign row length retrieved Class[] array converted into String[][]
        int expectedColSize = expected[0].length; // assign column length retrieved Class[] array converted into String[][]

        // verify
        Assert.assertArrayEquals(expected, actual); // checks if Arrays are the same
        Assert.assertEquals("Row length are not the same for Filing local and database", expectRowSize, actualRowSize); // checks row size
        Assert.assertEquals("Col length are not the same for Filing local and database", expectedColSize,actualColSize); // checks col size

        // after test, store data back into row
        Filing oldFiling = new Filing("F001", "Small", "Y", "Y", "N", 50, "005");
        databaseTest.setFilingRow(oldFiling, 0);
        databaseTest.sendFiling();
    }
    /**
     * sendLampTest() will test storing data from the Lamp[] array into the Lamp table in SQL. This is done through
     * the process of calling writeLamps() to receive a String[][] representation of the original hardcoded data. Then,
     * changes a couple of values within a row to ensure that the updated array data is stored in the Lamp SQL table.
     * We instantiate a Lamp object, use the setLampRow() method to set updated data from Lamp object. Then call the
     * sendLamp() method to store the updated data into Lamp table in SQL. Then, assert that the arrays are exactly the same.
     */
    @Test
    public void sendLampTest()
    {
        // setup
        databaseTest.initializeConnection();
        String[][] actual = writeLamp(); // calls a helper method to get String[][] representation of Lamp table in SQL
        actual[0][0] = "L013";          // changes values of a row in the hardcoded String[][] representation
        actual[0][1] = "Swing Arm";
        actual[0][2] = "Y";
        actual[0][3] = "Y";
        actual[0][4] = "90";
        actual[0][5] = "001";
        int actualRowSize = actual.length; // assign row length in hardcoded String[][]
        int actualColSize = actual[0].length; // assign col length in hardcoded String[][]

        // run test
        databaseTest.updateLamps(); // initializes Filing[] array with data that is in SQL
        Lamp lamp = new Lamp("L013", "Swing Arm", "Y", "Y",90, "001");
        databaseTest.setLampRow(lamp, 0); // changes a row with different values to ensure updated data from array is stored in SQL
        databaseTest.sendLamp(); // stores Lamp[] array data into the Lamp table in SQL
        String[][] expected = lampsToStringArray(); // calls a helper method to load the data from the Lamp[] array in Database class and convert it to String[][] array.
        int expectRowSize = expected.length; // assign row length retrieved Class[] array converted into String[][]
        int expectedColSize = expected[0].length; // assign column length retrieved Class[] array converted into String[][]

        // verify
        Assert.assertArrayEquals(expected, actual); // checks if arrays are the same
        Assert.assertEquals("Row length are not the same for Filing local and database", expectRowSize, actualRowSize); // checks row size
        Assert.assertEquals("Col length are not the same for Filing local and database", expectedColSize,actualColSize); // checks col size

        // after test, put old date back into row
        Lamp oldLamp = new Lamp("L013", "Desk", "Y", "N", 18, "004");
        databaseTest.setLampRow(oldLamp, 0);
        databaseTest.sendLamp();
    }

    /**
     *  This is where all of the helper methods for testing class Database will be placed for convenience
     *  ---------------------------------------------------------------------------------------------------------------
     */

    /**
     @return returns String array of the hardcoded representation of original Chair object data member values
     * that corresponds to the original data in the Chair table in SQL
     */
    public String[][] writeChairs()
    {
        String[][] chairData =
                {
                    {"C0914", "Task", "N", "N", "Y", "Y", "50", "002"},
                    {"C0942", "Mesh", "Y", "N", "Y", "Y", "175", "005"},
                    {"C1148", "Task", "Y", "N", "Y", "Y", "125", "003"},
                    {"C1320", "Kneeling", "Y", "N", "N", "N", "50", "002"},
                    {"C2483", "Executive", "Y", "Y", "N", "N", "175", "002"},
                    {"C3405", "Task", "Y", "Y", "N", "N", "100", "003"},
                    {"C3819", "Kneeling", "N", "N", "Y", "N", "75", "005"},
                    {"C4839", "Ergonomic", "N", "N", "N", "Y", "50", "002"},
                    {"C5409", "Ergonomic", "Y", "Y", "Y", "N", "200", "003"},
                    {"C5784", "Executive", "Y", "N", "N", "Y", "150", "004"},
                    {"C5789", "Ergonomic", "Y", "N", "N", "Y", "125", "003"},
                    {"C6748", "Mesh", "Y", "N", "N", "N", "75", "003"},
                    {"C7268", "Executive", "N", "N", "Y", "N", "75", "004"},
                    {"C8138", "Mesh", "N", "N", "Y", "N", "75", "005"},
                    {"C9890", "Mesh", "N", "Y", "N", "Y", "50", "003"}
                };
        return chairData;
    }
    /**
     * @return returns a String[][] array of the loaded data from Chair SQL table.
     * This method initializes the Chair[] array in Database class that loads the data from SQL
     * Then, converts the object array into a String[][] array for comparing with hardcoded data.
     */
    public String[][] chairsToStringArray()
    {
            String[][] actual = writeChairs(); // get hardcoded String[][] representation
            int rows = actual.length; // get number of rows
            int columns = actual[0].length; // get number of columns
            String[][] expected = new String[rows][columns]; // initialize array

            databaseTest.updateChairs();
            Chair[] chairs = databaseTest.getChairs(); // get Chair[] array from Database class
            int row = 0;
            int column = 0;
            while(row != chairs.length) // loop through and assign Chair data member values that represent the values corresponding to each column in SQL table.
            {
            expected[row][column] = chairs[row].getId();
            column++;
            expected[row][column] = chairs[row].getType();
            column++;
            expected[row][column] = chairs[row].getLegs();
            column++;
            expected[row][column] = chairs[row].getArms();
            column++;
            expected[row][column] = chairs[row].getSeat();
            column++;
            expected[row][column] = chairs[row].getCushion();
            column++;
            expected[row][column] = String.valueOf(chairs[row].getPrice());
            column++;
            expected[row][column] = chairs[row].getManuId();
            column = 0;
            row++;
            }
        return expected;
    }
    /**
     @return returns String array of the hardcoded representation of original Desk object data member values
      * that corresponds to the original data in the Desk table in SQL.
     */
    public String[][] writeDesks()
    {
        String[][] deskData =
                {
                    {"D0890", "Traditional", "N", "N", "Y", "25", "002"},
                    {"D1030", "Adjustable", "N", "Y", "N", "150", "002"},
                    {"D1927", "Standing", "Y", "N", "Y", "200", "005"},
                    {"D2341", "Standing", "N", "Y", "N", "100", "001"},
                    {"D2746", "Adjustable", "Y", "N", "Y", "250", "004"},
                    {"D3682", "Adjustable", "N", "N", "Y", "50", "005"},
                    {"D3820", "Standing", "Y", "N", "N", "150", "001"},
                    {"D4231", "Traditional", "N", "Y", "Y", "50", "005"},
                    {"D4438", "Standing", "N", "Y", "Y", "150", "004"},
                    {"D4475", "Adjustable", "N", "Y", "Y", "200", "002"},
                    {"D5437", "Adjustable", "Y", "N", "N", "200", "001"},
                    {"D7373", "Adjustable", "Y", "Y", "N", "350", "005"},
                    {"D8675", "Traditional", "Y", "Y", "N", "75", "001"},
                    {"D9352", "Traditional", "Y", "N", "Y", "75", "002"},
                    {"D9387", "Standing", "Y", "Y", "N", "250", "004"}
                };
        return deskData;
    }
     /** @return returns a String[][] array of the loaded data from Desk SQL table.
        * This method initializes the Desk[] array in Database class that loads the data from SQL
        * Then, converts the object array into a String[][] array for comparing with hardcoded data.
        */
    public String[][] desksToStringArray()
    {
        String[][] actual = writeDesks(); // get hardcoded String[][] representation
        int rows = actual.length; // get number of rows
        int columns = actual[0].length; // get number of columns
        String[][] expected = new String[rows][columns];

        databaseTest.updateDesks();
        Desk[] desks = databaseTest.getDesk();
        int row = 0;
        int column = 0;
        while(row != desks.length)
        {
            expected[row][column] = desks[row].getId();
            column++;
            expected[row][column] = desks[row].getType();
            column++;
            expected[row][column] = desks[row].getLegs();
            column++;
            expected[row][column] = desks[row].getTop();
            column++;
            expected[row][column] = desks[row].getDrawer();
            column++;
            expected[row][column] = String.valueOf(desks[row].getPrice());
            column++;
            expected[row][column] = desks[row].getManuId();
            column = 0;
            row++;
        }
        return expected;
    }
    /**
     @return returns String array of the hardcoded representation of original Filing object data member values
      * that corresponds to the original data in the Filing table in SQL
     */
    public String[][] writeFiling()
    {
          String [][] filingData =
                  {
                          {"F001", "Small", "Y", "Y", "N", "50", "005"},
                          {"F002", "Medium", "N", "N", "Y", "100", "004"},
                          {"F003", "Large", "N", "N", "Y", "150", "002"},
                          {"F004", "Small", "N", "Y", "Y", "75", "004"},
                          {"F005", "Small", "Y", "N", "Y", "75", "005"},
                          {"F006", "Small", "Y", "Y", "N", "50", "005"},
                          {"F007", "Medium", "N", "Y", "Y", "150", "002"},
                          {"F008", "Medium", "Y", "N", "N", "50", "005"},
                          {"F009", "Medium", "Y", "Y", "N", "100", "004"},
                          {"F010", "Large", "Y", "N", "Y", "225", "002"},
                          {"F011", "Large", "N", "Y", "Y", "225", "005"},
                          {"F012", "Large", "N", "Y", "N", "75", "005" },
                          {"F013", "Small", "N", "N", "Y", "50", "002"},
                          {"F014", "Medium", "Y", "Y", "Y", "200", "002"},
                          {"F015", "Large", "Y", "N", "N", "75", "004"}
                 };
          return filingData;
    }
     /** @return returns a String[][] array of the loaded data from Filing SQL table.
        * This method initializes the Filing[] array in Database class that loads the data from SQL
        * Then, converts the object array into a String[][] array for comparing with hardcoded data.
       */
   public String[][] filingsToStringArray()
   {
       String[][] actual = writeFiling();
       int rows = actual.length;
       int columns = actual[0].length;
       String[][] expected = new String[rows][columns];

       databaseTest.updateFilings();
       Filing[] cabinets = databaseTest.getFilings();
       int counter = 0;
       int column = 0;
       while(counter != cabinets.length)
       {
           expected[counter][column] = cabinets[counter].getId();
           column++;
           expected[counter][column] = cabinets[counter].getType();
           column++;
           expected[counter][column] = cabinets[counter].getRails();
           column++;
           expected[counter][column] = cabinets[counter].getDrawers();
           column++;
           expected[counter][column] = cabinets[counter].getCabinet();
           column++;
           expected[counter][column] = String.valueOf(cabinets[counter].getPrice());
           column++;
           expected[counter][column] = cabinets[counter].getManuId();
           column = 0;
           counter++;
       }
       return expected;
   }
    /**
     @return returns String array of the hardcoded representation of original Lamp object data member values
      * that corresponds to the original data in the Lamp table in SQL
     */
   public String[][] writeLamp()
   {
       String[][] lampData =
               {
                       {"L013", "Desk", "Y", "N", "18", "004"},
                       {"L053", "Swing Arm", "Y", "N", "27", "002"},  
                       {"L096", "Swing Arm", "N", "Y", "3", "002"},  
                       {"L112", "Desk", "Y", "N", "18", "005"}, 
                       {"L132", "Desk", "Y", "N", "18", "005"}, 
                       {"L208", "Desk", "N", "Y", "2", "005"},
                       {"L223", "Study", "N", "Y", "2", "005"},
                       {"L342", "Desk", "N", "Y", "2", "002"},
                       {"L487", "Swing Arm", "Y", "N", "27", "002"},
                       {"L564", "Desk", "Y", "Y", "20", "004"},
                       {"L649", "Desk", "Y", "N", "18", "004"},
                       {"L879", "Swing Arm", "N", "Y", "3", "005"},
                       {"L928", "Study", "Y", "Y", "10", "002"},  
                       {"L980", "Study", "N", "Y", "2", "004"},
                       {"L982", "Study", "Y", "N", "8", "002"}
               };
       return lampData;
   }
    /** @return returns a String[][] array of the loaded data from Lamp SQL table.
      * This method initializes the Lamp[] array in Database class that loads the data from SQL
      * Then, converts the object array into a String[][] array for comparing with hardcoded data.
      */
   public String[][] lampsToStringArray()
   {
       String[][] actual = writeLamp();
       int rows = actual.length;
       int columns = actual[0].length;
       String[][] expected = new String[rows][columns];

       databaseTest.updateLamps();
       Lamp[] theLamps = databaseTest.getLamps();
       int row = 0;
       int column = 0;
       while(row != theLamps.length)
       {
           expected[row][column] = theLamps[row].getId();
           column++;
           expected[row][column] = theLamps[row].getType();
           column++;
           expected[row][column] = theLamps[row].getBase();
           column++;
           expected[row][column] = theLamps[row].getBulb();
           column++;
           expected[row][column] = String.valueOf(theLamps[row].getPrice());
           column++;
           expected[row][column] = theLamps[row].getManuId();
           column = 0;
           row++;
       }
       return expected;
   }
    /**
     @return returns String array of the hardcoded representation of original Manufacturer object data member values
      * that corresponds to the original data in the Manufacturer table in SQL
     */
   public String[][] writeManufacturer()
   {
      String[][] manuData =
              {
                      {"001", "Academic Desks", "236-145-2542", "BC"},
                      {"002", "Office Furnishings", "587-890-4387", "AB"},
                      {"003", "Chairs R Us", "705-667-9481", "ON"},
                      {"004", "Furniture Goods", "306-512-5508", "SK"},
                      {"005", "Fine Office Supplies", "403-980-9876", "AB"},
              };
      return manuData;
   }
   public String[][] manusToStringArray()
   {
       String[][] actual = writeManufacturer();
       int rows = actual.length;
       int columns = actual[0].length;
       String[][] expected = new String[rows][columns];

       databaseTest.updateMans();
       Manufacturer[] manufs = databaseTest.getManufacturers();
       int row = 0;
       int column = 0;
       while(row != manufs.length)
       {
           expected[row][column] = manufs[row].getManuId();
           column++;
           expected[row][column] = manufs[row].getName();
           column++;
           expected[row][column] = manufs[row].getPhone();
           column++;
           expected[row][column] = manufs[row].getProvince();
           column = 0;
           row++;
       }
       return expected;
   }

}