package edu.ucalgary.ensf409;

/**
 *
 */
public class Chair
{
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
     * @param id
     * @param type
     * @param legs
     * @param arms
     * @param seat
     * @param cushion
     * @param price
     * @param manuId
     */
    public Chair(String id, String type, String legs, String arms, String seat, String cushion, int price, String manuId)
    {
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
    public String getArms()
    {
        return this.arms;
    }

    /**
     *
     * @return
     */
    public String getSeat()
    {
        return this.seat;
    }

    /**
     *
     * @return
     */
    public String getCushion()
    {
        return this.cushion;
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
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     *
     * @param type
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     *
     * @param legs
     */
    public void setLegs(String legs)
    {
        this.legs = legs;
    }

    /**
     *
     * @param arms
     */
    public void setArms(String arms)
    {
        this.arms = arms;
    }

    /**
     *
     * @param seat
     */
    public void setSeat(String seat)
    {
        this.seat = seat;
    }

    /**
     *
     * @param cushion
     */
    public void setCushion(String cushion)
    {
        this.cushion = cushion;
    }

    /**
     *
     * @param price
     */
    public void setPrice(int price)
    {
        this.price = price;
    }

    /**
     *
     * @param manuId
     */
    public void setManuId(String manuId)
    {
        this.manuId = manuId;
    }
}
