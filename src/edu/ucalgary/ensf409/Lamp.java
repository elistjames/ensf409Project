package edu.ucalgary.ensf409;

public class Lamp
{
    private String id;
    private String type;
    private String base;
    private String bulb;
    private int price;
    private String manuId;

    public Lamp(String id, String type, String base, String bulb, int price, String manuId){
        this.id = id;
        this.type = type;
        this.base = base;
        this.bulb = bulb;
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
    public String getBase()
    {
        return this.base;
    }
    public String getBulb()
    {
        return this.bulb;
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
    public void setBase(String base){this.base = base;}
    public void setBulb(String bulb){this.bulb = bulb;}
    public void setPrice(int price){this.price = price;}
    public void setManuId(String manuId){this.manuId = manuId;}
}
