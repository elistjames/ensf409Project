/**
 * Active Team Member:
 @author     chad Holst <a href="mailto:chad.holst1@ucalgary.ca">chad.holst1@ucalgary.ca</a>
 @version    1.9
 @since      1.0
 */

package edu.ucalgary.ensf409;

/**
 *  This class contains data for the Manufacturers of furniture, Manufacturer.
 *  It stores the values for each column used in the Chair table in SQL as data members.
 */
public class Manufacturer {
    private String manuId;
    private String name;
    private String phone;
    private String province;

    /**
     * Constructor for the Manufacturer class called from the Database class
     * @param id String representing the unique manufacturer ID
     * @param name String representing the Manufacturer's name
     * @param phone String representing the Manufacturer's Phone number
     * @param province String representing the two letter abbreviation of the manufacturer's
     * warehouse province location
     */
    public Manufacturer(String id, String name, String phone, String province) {
        this.manuId = id;
        this.name = name;
        this.phone = phone;
        this.province = province;
    }

    /**
     * getter method for the Manufacturer ID data field
     * @return A String
     */
    public String getManuId() { return this.manuId; }

    /**
     * getter method for the Manufacturer's name
     * @return A String
     */
    public String getName() { return this.name; }

    /**
     * getter method for the Manufacturer's Phone Number
     * @return A String
     */
    public String getPhone() { return this.phone; }

    /**
     * getter method for the location of the Manufacturer's warehouse
     * @return A String
     */
    public String getProvince() { return this.province; }
    //Methods below should not see use
    /**
     * Setter method for the Manufacturer's ID
     * @param manuId A String representing the Manufacturer's custom ID number
     */
    public void setManuId(String manuId){this.manuId = manuId;}

    /**
     * Setter method for the Manufacturer's name
     * @param name A String representing the Manufacturer's name
     */
    public void setName(String name){this.name = name;}

    /**
     * Setter method for the Manufacturer's phone number
     * @param phone A String representing the Manufacturer's phone number
     */
    public void setPhone(String phone){this.phone = phone;}

    /**
     * Setter Method for the Manufacturer's warehouse provincial location
     * @param province A 2 character String representing the provincial location of the
     * Manufacturer's warehouse
     */
    public void setProvince(String province){this.province = province;}
}
