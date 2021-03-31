package edu.ucalgary.ensf409;

/**
 *
 */
public class Filing
{
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
    public Filing(String id, String type, String rails, String drawers, String cabinet, int price, String manuId)
    {
        this.id = id;
        this.type = type;
        this.rails = rails;
        this.drawers = drawers;
        this.cabinet = cabinet;
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
    public String getRails()
    {
        return this.rails;
    }

    /**
     *
     * @return
     */
    public String getDrawers()
    {
        return this.drawers;
    }

    /**
     *
     * @return
     */
    public String getCabinet() { return this.cabinet; }

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
    public String getManuId() { return this.manuId; }

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
     * @param rails
     */
    public void setRails(String rails){this.rails = rails;}

    /**
     *
     * @param drawers
     */
    public void setDrawers(String drawers){this.drawers = drawers;}

    /**
     *
     * @param cabinet
     */
    public void setCabinet(String cabinet){this.cabinet = cabinet;}

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
