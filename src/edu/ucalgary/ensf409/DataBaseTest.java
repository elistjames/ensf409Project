package edu.ucalgary.ensf409;
import org.junit.*;
import java.sql.*;

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

    /**
     * Creates a DataBase object before each test with different USERNAME and PASSWORD for testing purposes but same data
     */
    @Before
    public void setUpTests()
    {
        dataBaseTest = new Database("jdbc:mysql://localhost/inventory", "chad", "@@Kawa1000");
        // create Database object for testing before each test
    }
    @Test
    public void testInitializeConnection()
    {
        // setup
        boolean actual = true;
        boolean expected = false;

        // run test
        dataBaseTest.initializeConnection();
        dbConnectTest = dataBaseTest.getdbConnect();
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
     * tests populating a Chair array from the database
     */
    @Test
    public void testUpdateChairs()
    {
        // setup
        dataBaseTest.initializeConnection();
        String[][] actual = writeChairs();
        int rows = actual.length;
        int columns = actual[0].length;
        String[][] expected = new String[rows][columns];

        // run test
        dataBaseTest.updateChairs();
        Chair[] chairs = dataBaseTest.getChairs();
        int row = 0;
        int column = 0;
        while(row != chairs.length)
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
        // verify
        Assert.assertArrayEquals(expected, actual); // checks if Arrays are the same
    }
    /**
     * tests populating a Desk array from the database
     */
    @Test
    public void testUpdateDesks()
    {
        // setup
        dataBaseTest.initializeConnection();
        String[][] actual = writeDesks();
        int rows = actual.length;
        int columns = actual[0].length;
        String[][] expected = new String[rows][columns];

        // run test
        dataBaseTest.updateDesks();
        Desk[] desks = dataBaseTest.getDesk();
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
        // verify
        Assert.assertArrayEquals(expected, actual); // checks if Arrays are the same
    }
    /**
     * tests populating a Filing array from the database
     */
    @Test
    public void testUpdateFilings()
    {
        // setup
        dataBaseTest.initializeConnection();
        String[][] actual = writeFiling();
        int rows = actual.length;
        int columns = actual[0].length;
        String[][] expected = new String[rows][columns];

        // run test
        dataBaseTest.updateFilings();
        Filing[] cabinets = dataBaseTest.getFilings();
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
        // verify
        Assert.assertArrayEquals(expected, actual); // checks if Arrays are the same
    }
    /**
     * tests populating a Lamp array from the database
     */
    @Test
    public void testUpdateLamps()
    {
        // setup
        dataBaseTest.initializeConnection();
        String[][] actual = writeLamp();
        int rows = actual.length;
        int columns = actual[0].length;
        String[][] expected = new String[rows][columns];

        // run test
        dataBaseTest.updateLamps();
        Lamp[] theLamps = dataBaseTest.getLamps();
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
        // verify
        Assert.assertArrayEquals(expected, actual); // checks if Arrays are the same
    }
    /**
     * tests populating a Manufacturer array from the database
     */
    @Test
    public void testUpdateManufacturer()
    {
        // setup
        dataBaseTest.initializeConnection();
        String[][] actual = writeManufacturer();
        int rows = actual.length;
        int columns = actual[0].length;
        String[][] expected = new String[rows][columns];

        // run test
        dataBaseTest.updateMans();
        Manufacturer[] manufs = dataBaseTest.getManufacturers();
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
        // verify
        Assert.assertArrayEquals(expected, actual); // checks if Arrays are the same

    }
    /**
     * tests updating a Desk array through the pullData method which uses a table name as input for switch
     */
    @Test
    public void testPullData()
    {
        // setup
        dataBaseTest.initializeConnection();
        String[][] actual = writeDesks();
        int rows = actual.length;
        int columns = actual[0].length;
        String[][] expected = new String[rows][columns];

        // run test
        dataBaseTest.updateDesks();
        Desk[] desks = dataBaseTest.getDesk();
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
        Assert.assertArrayEquals(expected, actual);
    }

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
                    {"D5437", "Adjustable", "Y", "N", "N", "50", "001"},
                    {"D7373", "Adjustable", "Y", "Y", "N", "350", "005"},
                    {"D8675", "Traditional", "Y", "Y", "N", "75", "001"},
                    {"D9352", "Traditional", "Y", "N", "Y", "75", "002"},
                    {"D9387", "Standing", "Y", "Y", "N", "250", "004"}
                };
        return deskData;
    }
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
                          {"F009", "Medium", "Y", "Y", "N", "150", "004"},
                          {"F010", "Large", "Y", "N", "Y", "225", "002"},
                          {"F011", "Large", "N", "Y", "Y", "225", "005"},
                          {"F012", "Large", "N", "Y", "N", "75", "005" },
                          {"F013", "Small", "N", "N", "Y", "50", "002"},
                          {"F014", "Medium", "Y", "Y", "Y", "200", "002"},
                          {"F015", "Large", "Y", "N", "N", "75", "004"}
                 };
          return filingData;
    }

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
}



                  
                          
                          
       
       
       
       
                          
                          
                         


