package edu.ucalgary.ensf409;

public class Manufacturer
{
    private String manuId;
    private String name;
    private String phone;
    private String province;

    public Manufacturer(String id, String name, String phone, String province)
    {
        this.manuId = id;
        this.name = name;
        this.phone = phone;
        this.province = province;
    }

    public String getManuId()
    {
        return this.manuId;
    }
    public String getName()
    {
        return this.name;
    }
    public String getPhone()
    {
        return this.phone;
    }
    public String getProvince()
    {
        return this.province;
    }
    public void setManuId(String manuId){this.manuId = manuId;}
    public void setName(String name){this.name = name;}
    public void setPhone(String phone){this.phone = phone;}
    public void setProvince(String province){this.province = province;}
}
