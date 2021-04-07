package edu.ucalgary.ensf409;
import java.sql.*;
import java.util.ArrayList;

/*
This class contains data for Database object. It stores the attributes used for accessing the SQL database, as well as local copies of the data in arrays.
It contains methods which facilitate the accessing and updating of the database. It also includes the various getter methods for the private data members.
 */
public class Database {

    public final String DBURL; //store the database url information
    public final String USERNAME; //store the user's account username
    public final String PASSWORD; //store the user's account password
    private Chair[] chairs; //create array that stores chairs
    private Desk[] desks; //create array that stores desks
    private Filing[] filings; //create array that stores filings
    private Lamp[] lamps; //create array that stores lamps
    private Manufacturer[] manufacturers; //create array that stores manufacturers
    private Connection dbConnect; //create new connection object
    private ResultSet results; //create new ResultSet object

    /**
     Constructor for Database object
     @param DBURL Url of database
     @param USERNAME Access username
     @param PASSWORD Access password
     */
    public Database(String DBURL, String USERNAME, String PASSWORD) {

        this.DBURL = DBURL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    /**
     *  method tries to make a connection with the database URL and if the connection is not made,
     *  then the SQL exception is caught.
     */
    public void initializeConnection() {
        try {
            dbConnect = DriverManager.getConnection(this.getDburl(), this.getUserName(),this.getPassword()); //initialize connections
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /**
     This method populates the chairs array using the updated data contained within the database.
     */
    public void updateChairs(){
        ResultSet result; //create new ResultSet object
        this.chairs = new Chair[this.countRows("CHAIR")]; //initialize chairs array
        int counter = 0; //set row counter to 0;
        try {
            Statement myStmt = dbConnect.createStatement(); //create new statement
            result = myStmt.executeQuery("SELECT * FROM CHAIR"); //execute statement select all from Chair table
            while(result.next()) { //run while next row exists
                Chair temp = new Chair(result.getString("ID"), result.getString("Type"),
                        result.getString ("Legs"), result.getString ("Arms"),
                        result.getString ("Seat"),result.getString ("Cushion"),
                        result.getInt ("Price"), result.getString ("ManuID")); //populate the temporary chair
                chairs[counter] = temp; //load temp into array
                counter++; //increment the counter
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     This method populates the desks array using the updated data contained within the database.
     */
    public void updateDesks(){
        ResultSet result; //create new ResultSet object
        this.desks = new Desk[this.countRows("DESK")]; //initialize desks array
        int counter = 0; //set row counter to 0
        try {
            Statement myStmt = dbConnect.createStatement(); //create and initialize statement
            result = myStmt.executeQuery("SELECT * FROM DESK"); //execute statement select all from DDesk
            while(result.next()) {
                Desk temp = new Desk(result.getString("ID"), result.getString("Type"),
                        result.getString ("Legs"), result.getString ("Top"),
                        result.getString ("Drawer"), result.getInt ("Price"),
                        result.getString ("ManuID")); //populate temp desk object
                desks[counter] = temp; //update desks array with new object
                counter++; //increment the counter
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     This method populates the filings array using the updated data contained within the database.
     */
    public void updateFilings(){
        ResultSet result; //create new ResultSet object
        this.filings = new Filing[this.countRows("FILING")]; //initialize filings array
        int counter = 0; //set row counter to 0
        try {
            Statement myStmt = dbConnect.createStatement(); //create and initialize statement
            result = myStmt.executeQuery("SELECT * FROM FILING"); //execute statement select all from Filing table
            while(result.next()) {
                Filing temp = new Filing(result.getString("ID"), result.getString("Type"),
                        result.getString ("Rails"), result.getString ("Drawers"),
                        result.getString ("Cabinet"), result.getInt ("Price"),
                        result.getString ("ManuID")); //create temp Filing object
                filings[counter] = temp; //update the filings array with temp Filing
                counter++; //increment counter
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     This method populates the lamps array using the updated data contained within the database.
     */
    public void updateLamps(){
        ResultSet result; //create new ResultSet obejct
        this.lamps = new Lamp[this.countRows("LAMP")]; //initialize lamps array
        int counter = 0; //set row counter to 0
        try {
            Statement myStmt = dbConnect.createStatement(); //create and initialize statement
            result = myStmt.executeQuery("SELECT * FROM LAMP"); //execute statement SELECT * FROM Lamp
            while(result.next()) {
                Lamp temp = new Lamp(result.getString("ID"), result.getString("Type"),
                        result.getString ("Base"), result.getString ("Bulb"),
                        result.getInt ("Price"), result.getString ("ManuID")); //create Temp lamp object
                lamps[counter] = temp; //update lamps array with Lamp temp
                counter++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     This method populates the manufacturers array using the updated data contained within the database.
     */
    public void updateMans(){
        ResultSet result; //create new ResultSet
        this.manufacturers = new Manufacturer[(this.countRows("MANUFACTURER"))]; //initialize manufacturers array
        int counter = 0; //set row counter to 0
        try {
            Statement myStmt = dbConnect.createStatement(); //create and intiilize statement
            result = myStmt.executeQuery("SELECT * FROM MANUFACTURER"); //execute statement select * from Manufacturer
            while(result.next()) {
                Manufacturer temp = new Manufacturer(result.getString("ManuID"),
                        result.getString("Name"), result.getString ("Phone"),
                        result.getString ("Province")); //create temp manufacturer object
                manufacturers[counter] = temp; //update manufacturers array
                counter++; //increment the counter
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     This method updates the database with the data contained within the chairs array.
     */
    public void sendChair(){
        try {
            Statement statement = dbConnect.createStatement(); //create and initialize statement
            statement.executeUpdate("TRUNCATE CHAIR"); //remove old entries from Chair table
            String query; //create new query

            //create a loop to update each entry in the Table from the Chairs array
            for(int i = 0; i < chairs.length; i++){
                query = "INSERT INTO CHAIR (ID,Type,Legs,Arms,Seat,Cushion,Price,ManuID) VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement myStmt = dbConnect.prepareStatement(query); //generate query for each loop iteration

                //populate all required table fields
                myStmt.setString(1, chairs[i].getId());
                myStmt.setString(2, chairs[i].getType());
                myStmt.setString(3, chairs[i].getLegs());
                myStmt.setString(4, chairs[i].getArms());
                myStmt.setString(5, chairs[i].getSeat());
                myStmt.setString(6, chairs[i].getCushion());
                myStmt.setInt(7, chairs[i].getPrice());
                myStmt.setString(8, chairs[i].getManuId());

                myStmt.execute(); //execute the statement
                myStmt.close(); //close the statement
            }
        } catch (SQLException e) {

        }
    }

    /**
     This method updates the database with the data contained within the desks array.
     */
    public void sendDesk(){
        try {
            Statement statement = dbConnect.createStatement(); //create and initialize statement
            statement.executeUpdate("TRUNCATE DESK"); //remove old entries from Desk table
            String query; //create new query

            //create a loop to update each entry in the Table from the desks array
            for(int i = 0; i < desks.length; i++){
                query = "INSERT INTO DESK (ID,Type,Legs,Top,Drawer,Price,ManuID) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement myStmt = dbConnect.prepareStatement(query); //generate query for each loop iteration

                //populate all required table fields
                myStmt.setString(1, desks[i].getId());
                myStmt.setString(2, desks[i].getType());
                myStmt.setString(3, desks[i].getLegs());
                myStmt.setString(4, desks[i].getTop());
                myStmt.setString(5, desks[i].getDrawer());
                myStmt.setInt(6, desks[i].getPrice());
                myStmt.setString(7, desks[i].getManuId());

                myStmt.execute(); //execute query
                myStmt.close(); //close statement
            }
        } catch (SQLException e) {

        }
    }

    /**
     This method updates the database with the data contained within the filings array.
     */
    public void sendFiling(){
        try {
            Statement statement = dbConnect.createStatement(); //create a loop to update each entry in the Table from the Chairs array
            statement.executeUpdate("TRUNCATE FILING"); //remove all old entries from Filing table
            String query; //create new query

            //create a loop to update each entry in the Table from the Filings array
            for(int i = 0; i < filings.length; i++){
                query = "INSERT INTO FILING (ID,Type,Rails,Drawers,Cabinet,Price,ManuID) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement myStmt = dbConnect.prepareStatement(query); //generate query for each loop iteration

                //populate all required table fields
                myStmt.setString(1, filings[i].getId());
                myStmt.setString(2, filings[i].getType());
                myStmt.setString(3, filings[i].getRails());
                myStmt.setString(4, filings[i].getDrawers());
                myStmt.setString(5, filings[i].getCabinet());
                myStmt.setInt(6, filings[i].getPrice());
                myStmt.setString(7, filings[i].getManuId());

                myStmt.execute(); //execute query
                myStmt.close(); //close statement
            }
        } catch (SQLException e) {

        }
    }

    /**
     This method updates the database with the data contained within the lamps array.
     */
    public void sendLamp(){
        try {
            Statement statement = dbConnect.createStatement(); //create and initialize statement
            statement.executeUpdate("TRUNCATE LAMP"); //remove old entries from Lamp table
            String query; //create new query

            //create a loop to update each entry in the Table from the Lamps array
            for(int i = 0; i < lamps.length; i++){
                query = "INSERT INTO LAMP (ID,Type,Base,Bulb,Price,ManuID) VALUES (?,?,?,?,?,?)";
                PreparedStatement myStmt = dbConnect.prepareStatement(query); //generate query for each loop iteration

                //populate all required table fields
                myStmt.setString(1, lamps[i].getId());
                myStmt.setString(2, lamps[i].getType());
                myStmt.setString(3, lamps[i].getBase());
                myStmt.setString(4, lamps[i].getBulb());
                myStmt.setInt(5, lamps[i].getPrice());
                myStmt.setString(6, lamps[i].getManuId());

                myStmt.execute(); //execute the query
                myStmt.close(); //close the statement
            }
        } catch (SQLException e) {

        }
    }
    /**
     This pulls all the data from the database and updates the local arrays with the table objects.
     */
    public void updateLocal(){
        updateChairs();
        updateDesks();
        updateFilings();
        updateLamps();
        updateMans();
    }

    /**
     This pushes all the local data from the Java arrays to the database at once.
     */
    public void pushLocal(){
        sendChair();
        sendDesk();
        sendFiling();
        sendLamp();
    }

    /**
     This method counts the rows of the specified table located in the database.
     @param table The table name from the database
     @return The number of rows in the table.
     */
    public int countRows(String table){
        int rowCount = 0; //set row counter to 0
        ResultSet result;
        try {
            Statement myStmt = dbConnect.createStatement();  //create a loop to update each entry in the Table from the Chairs array
            result = myStmt.executeQuery("SELECT count(*) FROM " + table); //execute count query
            result.next(); //go to next ResultSet
            rowCount = result.getInt(1); //get the total number of rows

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rowCount; //return number of rows
    }

    /**
     This method counts determines whether an
     @param itemIndexes The table name from the database
     @param index -
     @return Whether or not the index was used
     */
    public boolean indexWasUsed(ArrayList<Integer> itemIndexes, int index){
        boolean used = false;
        for(int i = 0; i < itemIndexes.size(); i++){
            if(index == itemIndexes.get(i)){
                used = true;
                break;
            }
        }
        return used;
    }

    public void updateTable(ArrayList<Integer> itemIndexes, int indicator){
        switch(indicator){
            case 0:
                for(int i = 0; i < desks.length; i++){
                    if(indexWasUsed(itemIndexes, i)){
                        desks[i].setAllpieces("N");
                    }
                }
                break;
            case 1:
                for(int i = 0; i < chairs.length; i++){
                    if(indexWasUsed(itemIndexes, i)){
                        chairs[i].setAllpieces("N");
                    }
                }
                break;
            case 2:
                for(int i = 0; i < filings.length; i++){
                    if(indexWasUsed(itemIndexes, i)){
                        filings[i].setAllpieces("N");
                    }
                }
                break;
            default:
                for(int i = 0; i < lamps.length; i++){
                    if(indexWasUsed(itemIndexes, i)){
                        lamps[i].setAllpieces("N");
                    }
                }
                break;
        }
    }
    /**
     @return returns String array representation of stored chairs
     */
    public Chair[] getChairs(){
        return this.chairs;
    }

    /**
     @return returns String array representation of stored desks
     */
    public Desk[] getDesk(){
        return this.desks;
    }

    /**
     @return returns String array representation of stored filings
     */
    public Filing[] getFilings(){
        return this.filings;
    }

    /**
     @return returns String array representation of stored lamps
     */
    public Lamp[] getLamps(){
        return this.lamps;
    }

    /**
     @return returns String array representation of stored manufacturers
     */
    public Manufacturer[] getManufacturers(){
        return this.manufacturers;
    }

    /**
     Does not use database
     @return returns String representation stored Data Base URL
     */
    public String getDburl() {
        return this.DBURL;
    }

    /**
     Does not use database
     @return returns String representation of stored User Name
     */
    public String getUserName() {
        return this.USERNAME;
    }

    /**
     Does not use database
     @return returns String representation of stored Password
     */
    public String getPassword() {
        return this.PASSWORD;
    }

    /**
     * Does not use database
     * @return returns Connection object, dbConnect.
     */
    public Connection getdbConnect()
    {
        return this.dbConnect;
    }

    /**
     *  The following methods are used specifically for testing that the sendClass() methods update
     *  their corresponding SQL table with data from the Class[] array stored as a data member representing
     *  each furniture category
     *  ------------------------------------------------------------------------------------------------
     */

    /**
     *
     * @param chair Chair object for updating into Chair[] array in specific row
     * @param row row corresponding to Chair[] array to be set with object
     */
    public void setChairRow(Chair chair, int row)
    {
        this.chairs[row] = chair;
    }

    /**
     * @param desk Desk object for setting Desk[] array in specific row
     * @param row row corresponding to Desk[] array to be set with object
     */
    public void setDeskRow(Desk desk, int row)
    {
        this.desks[row] = desk;
    }

    /**
     * @param filing Chair object for setting Filing[] array in specific row
     * @param row row corresponding to Filing[] array to be set
     */
    public void setFilingRow(Filing filing, int row)
    {
        this.filings[row] = filing;
    }

    /**
     *
     * @param lamp Lamp object for setting Lamp[] array in specific row
     * @param row row corresponding to Lamp[] array to be set
     */
    public void setLampRow(Lamp lamp, int row)
    {
        this.lamps[row] = lamp;
    }
}