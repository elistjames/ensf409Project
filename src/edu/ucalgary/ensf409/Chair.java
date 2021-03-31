package edu.ucalgary.ensf409;

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

    public String getId()
    {
        return this.id;
    }
    public String getType()
    {
        return this.type;
    }
    public String getLegs()
    {
        return this.legs;
    }
    public String getArms()
    {
        return this.arms;
    }
    public String getSeat()
    {
        return this.seat;
    }
    public String getCushion()
    {
        return this.cushion;
    }
    public int getPrice()
    {
        return this.price;
    }
    public String getManuId()
    {
        return this.manuId;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public void setType(String type)
    {
        this.type = type;
    }
    public void setLegs(String legs)
    {
        this.legs = legs;
    }
    public void setArms(String arms)
    {
        this.arms = arms;
    }
    public void setSeat(String seat)
    {
        this.seat = seat;
    }
    public void setCushion(String cushion)
    {
        this.cushion = cushion;
    }
    public void setPrice(int price)
    {
        this.price = price;
    }
    public void setManuId(String manuId)
    {
        this.manuId = manuId;
    }
}
