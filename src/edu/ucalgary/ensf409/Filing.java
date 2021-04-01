package edu.ucalgary.ensf409;

/*
This class contains data for Filing object. It stores the attributes for each column used in the filing table used in the database.
 */

public class Filing {
    private String id;
    private String type;
    private String rails;
    private String drawers;
    private String cabinet;
    private int price;
    private String manuId;

    /**
     *
     * @param id - receives String from id column in "Filing" table
     * @param type - receives String from type column in "Filing" table
     * @param rails - receives String from rails column in "Filing" table
     * @param drawers - receives String from drawers column in "Filing" table
     * @param cabinet - receives String from cabinet column in "Filing" table
     * @param price - receives int from price column in "Filing" table
     * @param manuId - receives String from manuId column in "Filing table
     */
    public Filing(String id, String type, String rails, String drawers, String cabinet, int price, String manuId) {
        this.id = id;
        this.type = type;
        this.rails = rails;
        this.drawers = drawers;
        this.cabinet = cabinet;
        this.price = price;
        this.manuId = manuId;
    }

    /**
     * @return - String data member this.id
     */
    public String getId() { return this.id; }

    /**
     * @return - String data member this.type
     */
    public String getType() { return this.type; }

    /**
     * @return - String data member this.rails
     */
    public String getRails() { return this.rails; }

    /**
     * @return - String data member this.drawers
     */
    public String getDrawers() { return this.drawers; }

    /**
     * @return - String data member this.cabinet
     */
    public String getCabinet() { return this.cabinet; }

    /**
     * @return - int data member this.price
     */
    public int getPrice() { return this.price; }

    /**
     * @return - String data member this.manuId
     */
    public String getManuId() { return this.manuId; }

    /**
     * @param id - set String data member this.id
     */
    public void setId(String id){this.id = id;}

    /**
     * @param type - set String data member this.type
     */
    public void setType(String type){this.type = type;}

    /**
     * @param rails - set String data member this.rails
     */
    public void setRails(String rails){this.rails = rails;}

    /**
     * @param drawers - set String data member this.drawers
     */
    public void setDrawers(String drawers){this.drawers = drawers;}

    /**
     * @param cabinet - set String data member this.cabinet
     */
    public void setCabinet(String cabinet){this.cabinet = cabinet;}

    /**
     * @param price - set int data member this.price
     */
    public void setPrice(int price){this.price = price;}

    /**
     * @param manuId - set String data member this.manuId
     */
    public void setManuId(String manuId){this.manuId = manuId;}

    /**
     * Sets all pieces with desired string
     * @param s - data member to set pieces with
     */
    public void setAllpieces(String s){
        this.rails = s;
        this.drawers = s;
        this.cabinet = s;
    }
}
