package edu.ucalgary.ensf409;
import java.sql.*;

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

    public Database(String DBURL, String USERNAME, String PASSWORD) {
        /**
         * @param DBURL,USERNAME,PASSWORD.
         *             Requires:
         *             DBURL - stores the database url information
         *             USERNAME - stores the user's account username
         *             PASSWORD - stores the user's account password.
         *             Does not use database but assists in connecting to SQL database
         *
         * @return returns nothing, since 3 arg constructor
         */

        this.DBURL = DBURL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }
    public String getDburl()
    {
        /**
         * Does not use database
         * @return no params, returns String representation stored Data Base URL
         */

        return this.DBURL;
    }
    public String getUserName()
    {
        /**
         * Does not use database
         * @return no params, returns String representation of stored User Name
         */

        return this.USERNAME;
    }
    public String getPassword()
    {
        /**
         * Does not use database
         * @return no params, returns String representation of stored Password
         */

        return this.PASSWORD;
    }
    public void initializeConnection()
    {
        /**
         *  method tries to make a connection with the database URL and if the connection is not made,
         *  then the SQL exception is caught.
         */

        try
        {
            dbConnect = DriverManager.getConnection(this.getDburl(), this.getUserName(),this.getPassword());
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

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
    public void updateLocal(){

        pullData("CHAIR");
        pullData("DESK");
        pullData("FILING");
        pullData("LAMP");
        pullData("MANUFACTURER");

    }

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

    public Chair[] getChairs(){
        return this.chairs;
}

    public Desk[] getDesk(){
        return this.desks;
    }

    public Filing[] getFilings(){
        return this.filings;
    }

    public Lamp[] getLamps(){
        return this.lamps;
    }

    public Manufacturer[] getManufacturers(){
        return this.manufacturers;
    }
    public void pushLocal(){

    }
}

