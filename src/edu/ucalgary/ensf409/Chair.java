package edu.ucalgary.ensf409;
// here is a changee
/*
This class contains data for Chair object. It stores the attributes for each column used in the Chair table used in the database.
 */
public class Chair {
    private String id;
    private String type;
    private String legs;
    private String arms;
    private String seat;
    private String cushion;
    private int price;
    private String manuId;

    /**
     *
     * @param id - receives a String from "id" column in Chair table
     * @param type - receives a String from "type" column in Chair table
     * @param legs - receives a String from "legs" column in Chair table
     * @param arms - receives a String from "arms" column in Chair table
     * @param seat - receives a String from "seat" column in Chair table
     * @param cushion - receives a String from "cushion" column in Chair table
     * @param price - receives an int from "price" column in Chair table
     * @param manuId - receives an String from "manu" column in Chair table
     */

    public Chair(String id, String type, String legs, String arms, String seat, String cushion, int price, String manuId) {
        this.id = id;
        this.type = type;
        this.legs = legs;
        this.arms = arms;
        this.seat = seat;
        this.cushion = cushion;
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
     * @return - String data member this.legs
     */
    public String getLegs() { return this.legs; }

    /**
     * @return - String data member this.arms
     */
    public String getArms() { return this.arms; }

    /**
     * @return - String data member this.seat
     */
    public String getSeat() { return this.seat; }

    /**
     * @return - String data member this.cushion
     */
    public String getCushion() { return this.cushion; }

    /**
     * @return - String data member this.price
     */
    public int getPrice() { return this.price; }

    /**
     * @return - String data member this.manuId
     */
    public String getManuId() { return this.manuId; }

    /**
     * @param id - set String data member this.id
     */
    public void setId(String id) { this.id = id; }

    /**
     * @param type - set String data member this.type
     */
    public void setType(String type) { this.type = type; }

    /**
     * @param legs - set String data member this.legs
     */
    public void setLegs(String legs) { this.legs = legs; }

    /**
     * @param arms - set String data member this.arms
     */
    public void setArms(String arms) { this.arms = arms; }

    /**
     * @param seat - set String data member this.seat
     */
    public void setSeat(String seat) { this.seat = seat; }

    /**
     * @param cushion - set String data member this.cushion
     */
    public void setCushion(String cushion) { this.cushion = cushion; }

    /**
     * @param price - set String data member this.price
     */
    public void setPrice(int price) { this.price = price; }

    /**
     * @param manuId - set String data member this.manuId
     */
    public void setManuId(String manuId) { this.manuId = manuId; }

    /**
     * Sets all pieces with desired string
     * @param s - data member to set pieces with
     */
    public void setAllpieces(String s){
        this.legs = s;
        this.arms = s;
        this.seat = s;
        this.cushion = s;
    }
}