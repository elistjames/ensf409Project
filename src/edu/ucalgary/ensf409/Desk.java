package edu.ucalgary.ensf409;

public class Desk
{
    private String id;
    private String type;
    private String legs;
    private String top;
    private String drawer;
    private int price;
    private String manuId;


    /**
     *
     * @param id - receives a String from "id" column in Desk table
     * @param type - receives a String from "type" column in Desk table
     * @param legs - receives a String from "legs" column in Desk table
     * @param top - receives a String from "top" column in Desk table
     * @param drawer - receives a String from "drawer" column in Desk table
     * @param price - receives a int from "price" column in Desk table
     * @param manuId - receives a String from "manuId" column in Desk table
     */
    public Desk(String id, String type, String legs, String top, String drawer, int price, String manuId)
    {

        this.id = id;
        this.type = type;
        this.legs = legs;
        this.top = top;
        this.drawer = drawer;
        this.price = price;
        this.manuId = manuId;
    }

    /**
     *
     * @return - String data member this.id
     */
    public String getId()
    {
        return this.id;
    }

    /**
     *
     * @return - String data member this.type
     */
    public String getType()
    {
        return this.type;
    }

    /**
     *
     * @return - String data member this.legs
     */
    public String getLegs()
    {
        return this.legs;
    }

    /**
     *
     * @return - String data member this.top
     */
    public String getTop()
    {
        return this.top;
    }

    /**
     *
     * @return - String data member this.drawer
     */
    public String getDrawer()
    {
        return this.drawer;
    }

    /**
     *
     * @return int data member this.price
     */
    public int getPrice()
    {
        return this.price;
    }

    /**
     *
     * @return String data member this.manuId
     */
    public String getManuId()
    {
        return this.manuId;
    }

    /**
     *
     * @param id - set String data member this.id
     */
    public void setId(String id){this.id = id;}

    /**
     *
     * @param type - set String data member this.type
     */
    public void setType(String type){this.type = type;}

    /**
     *
     * @param legs - set String data member this.legs
     */
    public void setLegs(String legs){this.legs = legs;}

    /**
     *
     * @param top set String data member this.top
     */
    public void setTop(String top){this.top = top;}

    /**
     *
     * @param drawer - set String data member this.drawer
     */
    public void setDrawer(String drawer){this.drawer = drawer;}

    /**
     *
     * @param price - set int data member this.price
     */
    public void setPrice(int price){this.price = price;}

    /**
     *
     * @param manuId - set String data member this.manuId
     */
    public void setManuId(String manuId){this.manuId = manuId;}


}
