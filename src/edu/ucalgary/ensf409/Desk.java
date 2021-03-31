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

    public Desk(String id, String type, String legs, String top, String drawer, int price, String manuId){
        this.id = id;
        this.type = type;
        this.legs = legs;
        this.top = top;
        this.drawer = drawer;
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
    public String getTop()
    {
        return this.top;
    }
    public String getDrawer()
    {
        return this.drawer;
    }
    public int getPrice()
    {
        return this.price;
    }
    public String getManuId()
    {
        return this.manuId;
    }
    public void setId(String id){this.id = id;}
    public void setType(String type){this.type = type;}
    public void setLegs(String legs){this.legs = legs;}
    public void setTop(String top){this.top = top;}
    public void setDrawer(String drawer){this.drawer = drawer;}
    public void setPrice(int price){this.price = price;}
    public void setManuId(String manuId){this.manuId = manuId;}


}
