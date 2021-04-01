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
    private Chair[] chairs;
    private Desk[] desks;
    private Filing[] filings;
    private Lamp[] lamps;
    private Manufacturer[] manufacturers;
    private Connection dbConnect;
    private ResultSet results;

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
            dbConnect = DriverManager.getConnection(this.getDburl(), this.getUserName(),this.getPassword());
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     This method creates the appropriate sized arrays for each table. It then calls the respective update method to populate the arrays.
     @param table The table name from the database
     */
    public void pullData(String table){
        int rows = countRows(table);

        switch (table){
            case "CHAIR":
                chairs = new Chair[rows];
                updateChairs();
                break;
            case "DESK":
                desks = new Desk[rows];
                updateDesks();
                break;
            case "FILING":
                filings = new Filing[rows];
                updateFiling();
                break;
            case "LAMP":
                lamps = new Lamp[rows];
                updateLamp();
                break;
            case "MANUFACTURER":
                manufacturers = new Manufacturer[rows];
                updateMan();
                break;
        }
    }

    /**
     This method populates the chairs array using the updated data contained within the database.
     */
    public void updateChairs(){
        ResultSet result;
        int counter = 0;
        try {
            Statement myStmt = dbConnect.createStatement();
            result = myStmt.executeQuery("SELECT * FROM CHAIR");
            while(result.next()) {
                Chair temp = new Chair(result.getString("ID"), result.getString("Type"), result.getString ("Legs"), result.getString ("Arms"), result.getString ("Seat"),result.getString ("Cushion"), result.getInt ("Price"), result.getString ("ManuID"));
                chairs[counter] = temp;
                counter++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     This method populates the desks array using the updated data contained within the database.
     */
    public void updateDesks(){
        ResultSet result;
        int counter = 0;
        try {
            Statement myStmt = dbConnect.createStatement();
            result = myStmt.executeQuery("SELECT * FROM DESK");
            while(result.next()) {
                Desk temp = new Desk(result.getString("ID"), result.getString("Type"), result.getString ("Legs"), result.getString ("Top"), result.getString ("Drawer"), result.getInt ("Price"), result.getString ("ManuID"));
                desks[counter] = temp;
                counter++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     This method populates the filings array using the updated data contained within the database.
     */
    public void updateFiling(){
        ResultSet result;
        int counter = 0;
        try {
            Statement myStmt = dbConnect.createStatement();
            result = myStmt.executeQuery("SELECT * FROM FILING");
            while(result.next()) {
                Filing temp = new Filing(result.getString("ID"), result.getString("Type"), result.getString ("Rails"), result.getString ("Drawers"), result.getString ("Cabinet"), result.getInt ("Price"), result.getString ("ManuID"));
                filings[counter] = temp;
                counter++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     This method populates the lamps array using the updated data contained within the database.
     */
    public void updateLamp(){
        ResultSet result;
        int counter = 0;
        try {
            Statement myStmt = dbConnect.createStatement();
            result = myStmt.executeQuery("SELECT * FROM LAMP");
            while(result.next()) {
                Lamp temp = new Lamp(result.getString("ID"), result.getString("Type"), result.getString ("Base"), result.getString ("Bulb"), result.getInt ("Price"), result.getString ("ManuID"));
                lamps[counter] = temp;
                counter++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     This method populates the manufacturers array using the updated data contained within the database.
     */
    public void updateMan(){
        ResultSet result;
        int counter = 0;
        try {
            Statement myStmt = dbConnect.createStatement();
            result = myStmt.executeQuery("SELECT * FROM MANUFACTURER");
            while(result.next()) {
                Manufacturer temp = new Manufacturer(result.getString("ManuID"), result.getString("Name"), result.getString ("Phone"), result.getString ("Province"));
                manufacturers[counter] = temp;
                counter++;
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
            Statement statement = dbConnect.createStatement();
            statement.executeUpdate("TRUNCATE CHAIR");
            String query;
            for(int i = 0; i < chairs.length; i++){
                query = "INSERT INTO CHAIR (ID,Type,Legs,Arms,Seat,Cushion,Price,ManuID) VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement myStmt = dbConnect.prepareStatement(query);

                myStmt.setString(1, chairs[i].getId());
                myStmt.setString(2, chairs[i].getType());
                myStmt.setString(3, chairs[i].getLegs());
                myStmt.setString(4, chairs[i].getArms());
                myStmt.setString(5, chairs[i].getSeat());
                myStmt.setString(6, chairs[i].getCushion());
                myStmt.setInt(7, chairs[i].getPrice());
                myStmt.setString(8, chairs[i].getManuId());

                myStmt.execute();
                myStmt.close();
            }
        } catch (SQLException e) {

        }
    }

    /**
     This method updates the database with the data contained within the desks array.
     */
    public void sendDesk(){
        try {
            Statement statement = dbConnect.createStatement();
            statement.executeUpdate("TRUNCATE DESK");
            String query;
            for(int i = 0; i < desks.length; i++){
                query = "INSERT INTO DESK (ID,Type,Legs,Top,Drawer,Price,ManuID) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement myStmt = dbConnect.prepareStatement(query);

                myStmt.setString(1, desks[i].getId());
                myStmt.setString(2, desks[i].getType());
                myStmt.setString(3, desks[i].getLegs());
                myStmt.setString(4, desks[i].getTop());
                myStmt.setString(5, desks[i].getDrawer());
                myStmt.setInt(6, desks[i].getPrice());
                myStmt.setString(7, desks[i].getManuId());

                myStmt.execute();
                myStmt.close();
            }
        } catch (SQLException e) {

        }
    }

    /**
     This method updates the database with the data contained within the filings array.
     */
    public void sendFiling(){
        try {
            Statement statement = dbConnect.createStatement();
            statement.executeUpdate("TRUNCATE FILING");
            String query;
            for(int i = 0; i < filings.length; i++){
                query = "INSERT INTO FILING (ID,Type,Rails,Drawers,Cabinet,Price,ManuID) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement myStmt = dbConnect.prepareStatement(query);

                myStmt.setString(1, filings[i].getId());
                myStmt.setString(2, filings[i].getType());
                myStmt.setString(3, filings[i].getRails());
                myStmt.setString(4, filings[i].getDrawers());
                myStmt.setString(5, filings[i].getCabinet());
                myStmt.setInt(6, filings[i].getPrice());
                myStmt.setString(7, filings[i].getManuId());

                myStmt.execute();
                myStmt.close();
            }
        } catch (SQLException e) {

        }
    }

    /**
     This method updates the database with the data contained within the lamps array.
     */
    public void sendLamp(){
        try {
            Statement statement = dbConnect.createStatement();
            statement.executeUpdate("TRUNCATE LAMP");
            String query;
            for(int i = 0; i < lamps.length; i++){
                query = "INSERT INTO LAMP (ID,Type,Base,Bulb,Price,ManuID) VALUES (?,?,?,?,?,?)";
                PreparedStatement myStmt = dbConnect.prepareStatement(query);

                myStmt.setString(1, lamps[i].getId());
                myStmt.setString(2, lamps[i].getType());
                myStmt.setString(3, lamps[i].getBase());
                myStmt.setString(4, lamps[i].getBulb());
                myStmt.setInt(5, lamps[i].getPrice());
                myStmt.setString(6, lamps[i].getManuId());

                myStmt.execute();
                myStmt.close();
            }
        } catch (SQLException e) {

        }
    }

    /**
     This pulls all the data from the database and updates the local arrays with the table objects.
     */
    public void updateLocal(){

        pullData("CHAIR");
        pullData("DESK");
        pullData("FILING");
        pullData("LAMP");
        pullData("MANUFACTURER");

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
        int rowCount = 0;
        ResultSet result;
        try {
            Statement myStmt = dbConnect.createStatement();
            result = myStmt.executeQuery("SELECT count(*) FROM " + table);
            result.next();
            rowCount = result.getInt(1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rowCount;
    }

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
}

