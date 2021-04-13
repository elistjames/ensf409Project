/**
 * Active Team Member:
 @author     chad Holst <a href="mailto:chad.holst1@ucalgary.ca">chad.holst1@ucalgary.ca</a>
 @version    1.9
 @since      1.0
 */
package edu.ucalgary.ensf409;
/**
 * This class contains data for the furniture category, Lamp.
 * It stores the values for each column used in the Lamp table in SQL as data members.
 */

public class Lamp {
    private String id;
    private String type;
    private String base;
    private String bulb;
    private int price;
    private String manuId;

    /**
     *
     * @param id - receives String from id column in "Lamp" table
     * @param type - receives String from type column in "Lamp" table
     * @param base - receives String from base column in "Lamp" table
     * @param bulb - receives String from bulb column in "Lamp" table
     * @param price - receives int from price column in "Lamp" table
     * @param manuId - receives String from manuId column in "Lamp"
     */
    public Lamp(String id, String type, String base, String bulb, int price, String manuId){
        this.id = id;
        this.type = type;
        this.base = base;
        this.bulb = bulb;
        this.price = price;
        this.manuId = manuId;
    }

    /**
     * @return - String data member this.id
     */
    public String getId() { return this.id; }

    /**
     * @return - String data member this.price
     */
    public String getType() { return this.type; }

    /**
     * @return - String data member this.base
     */
    public String getBase() { return this.base; }

    /**
     * @return - String data member this.bulb
     */
    public String getBulb() { return this.bulb; }

    /**
     * @return - String data member this.price
     */
    public int getPrice() { return this.price; }

    /**
     * @return - int data member this.manuId
     */
    public String getManuId() { return this.manuId; }

    /**
     * @param id - set data member this.id
     */
    public void setId(String id){this.id = id;}

    /**
     * @param type - set data member this.type
     */
    public void setType(String type){this.type = type;}

    /**
     * @param base - set data member this.base
     */
    public void setBase(String base){this.base = base;}

    /**
     * @param bulb - set data member this.base
     */
    public void setBulb(String bulb){this.bulb = bulb;}

    /**
     * @param price - set data member this.price
     */
    public void setPrice(int price){this.price = price;}

    /**
     * @param manuId set data member this.manuId
     */
    public void setManuId(String manuId){this.manuId = manuId;}

    /**
     * Sets all pieces with desired string
     * @param s - data member to set pieces with
     */
    public void setAllpieces(String s){
        this.base = s;
        this.bulb = s;
    }
}
