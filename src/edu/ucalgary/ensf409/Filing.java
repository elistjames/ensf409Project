package edu.ucalgary.ensf409;

public class Filing
{
    private String id;
    private String type;
    private String rails;
    private String drawers;
    private String cabinet;
    private int price;
    private String manuId;

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
    public String getId()
    {
        return this.id;
    }
    public String getType()
    {
        return this.type;
    }
    public String getRails()
    {
        return this.rails;
    }
    public String getDrawers()
    {
        return this.drawers;
    }
    public String getCabinet() { return this.cabinet; }
    public int getPrice()
    {
        return this.price;
    }
    public String getManuId() { return this.manuId; }
    public void setId(String id){this.id = id;}
    public void setType(String type){this.type = type;}
    public void setRails(String rails){this.rails = rails;}
    public void setDrawers(String drawers){this.drawers = drawers;}
    public void setCabinet(String cabinet){this.cabinet = cabinet;}
    public void setPrice(int price){this.price = price;}
    public void setManuId(String manuId){this.manuId = manuId;}
}
