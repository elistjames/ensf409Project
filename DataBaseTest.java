package edu.ucalgary.ensf409;
import org.junit.*;
import java.sql.*;

import static org.junit.Assert.*;

public class DataBaseTest
{
    // Database update methods populate the FurnitureCategory class arrays with updated data from database
    // Database send methods send the updated data in the FurnitureCategory class arrays to the database

    private String[][] chairs;
    private String[][] desks;
    private String[][] filings;
    private String[][] lamps;
    private String[][] manufacturers;
    private Database dataBaseTest;
    private DataBaseTest dataTest;
    private Connection dbConnectTest;


    @Before
    public void setUpTests()
    {
        dataBaseTest = new Database("jdbc:mysql://localhost/inventory", "chad", "@@Kawa1000");
        // create Database object for testing before each test
    }
    @Before
    public void setUpData()
    {
         dataTest = new DataBaseTest();
    }
    @Test
    public void testInitializeConnection()
    {
        // setup
        boolean actual = true;
        boolean expected = false;

        // run test
        dataBaseTest.initializeConnection();
        dbConnectTest = dataBaseTest.getDbConnect();
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
    @Test
    public void testUpdateChairs()
    {
        // setup
        dataBaseTest.initializeConnection();
        dataTest.chairs = writeChairs();
        String[][] actual = dataTest.chairs;
        int rows = actual.length;
        int columns = actual[0].length;
        String[][] expected = new String[rows][columns];

        // run test
        dataBaseTest.updateChairs();
        Chair[] chairs = dataBaseTest.getChairs();
        int counter = 0;
        int column = 0;
        while(counter != chairs.length)
        {
            expected[counter][column] = chairs[counter].getId();
            column++;
            expected[counter][column] = chairs[counter].getType();
            column++;
            expected[counter][column] = chairs[counter].getLegs();
            column++;
            expected[counter][column] = chairs[counter].getArms();
            column++;
            expected[counter][column] = chairs[counter].getSeat();
            column++;
            expected[counter][column] = chairs[counter].getCushion();
            column++;
            expected[counter][column] = String.valueOf(chairs[counter].getPrice());
            column++;
            expected[counter][column] = chairs[counter].getManuId();
            column = 0;
            counter++;
        }
        // verify
        Assert.assertSame(expected, actual); // checks if objects are the same
    }
    /*
    @Test
    public void testPullData(String table)
    {
        // setup
        chairs = dataBaseTest.getChairs();
        desks = dataBaseTest.getDesk();
        filings = dataBaseTest.getFilings();
        lamps = dataBaseTest.getLamps();
        manufacturers = dataBaseTest.getManufacturers();

        // run test
    }
    */

    /**
     *  This is where all of the helper methods for testing class Database will be placed
     *  ---------------------------------------------------------------------------------
     */


    /**
     @return returns String array representation of chair object data member values
     */
    public String[][] writeChairs()
    {
        String[][] chairData =
                {
                    {"C0914", "Task", "N", "N", "Y", "Y", "50", "002"},
                    {"C0942", "Mesh", "Y", "N", "Y", "Y", "100", "005"},
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

}