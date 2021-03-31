package edu.ucalgary.ensf409;

/**
 *
 */
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
     * @param id
     * @param type
     * @param legs
     * @param top
     * @param drawer
     * @param price
     * @param manuId
     */
    public Desk(String id, String type, String legs, String top, String drawer, int price, String manuId){
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
     * @return
     */
    public String getId()
    {
        return this.id;
    }

    /**
     *
     * @return
     */
    public String getType()
    {
        return this.type;
    }

    /**
     *
     * @return
     */
    public String getLegs()
    {
        return this.legs;
    }

    /**
     *
     * @return
     */
    public String getTop()
    {
        return this.top;
    }

    /**
     *
     * @return
     */
    public String getDrawer()
    {
        return this.drawer;
    }

    /**
     *
     * @return
     */
    public int getPrice()
    {
        return this.price;
    }

    /**
     *
     * @return
     */
    public String getManuId()
    {
        return this.manuId;
    }

    /**
     *
     * @param id
     */
    public void setId(String id){this.id = id;}

    /**
     *
     * @param type
     */
    public void setType(String type){this.type = type;}

    /**
     *
     * @param legs
     */
    public void setLegs(String legs){this.legs = legs;}

    /**
     *
     * @param top
     */
    public void setTop(String top){this.top = top;}

    /**
     *
     * @param drawer
     */
    public void setDrawer(String drawer){this.drawer = drawer;}

    /**
     *
     * @param price
     */
    public void setPrice(int price){this.price = price;}

    /**
     *
     * @param manuId
     */
    public void setManuId(String manuId){this.manuId = manuId;}


}
