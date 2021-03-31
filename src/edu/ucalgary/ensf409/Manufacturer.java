package edu.ucalgary.ensf409;

/**
 *
 */
public class Manufacturer
{
    private String manuId;
    private String name;
    private String phone;
    private String province;

    /**
     *
     * @param id
     * @param name
     * @param phone
     * @param province
     */
    public Manufacturer(String id, String name, String phone, String province)
    {
        this.manuId = id;
        this.name = name;
        this.phone = phone;
        this.province = province;
    }

    /**
     *
     * @return
     */
    public String getManuId() { return this.manuId; }

    /**
     *
     * @return
     */
    public String getName() { return this.name; }

    /**
     *
     * @return
     */
    public String getPhone() { return this.phone; }

    /**
     *
     * @return
     */
    public String getProvince() { return this.province; }

    /**
     *
     * @param manuId
     */
    public void setManuId(String manuId){this.manuId = manuId;}

    /**
     *
     * @param name
     */
    public void setName(String name){this.name = name;}

    /**
     *
     * @param phone
     */
    public void setPhone(String phone){this.phone = phone;}

    /**
     *
     * @param province
     */
    public void setProvince(String province){this.province = province;}
}
