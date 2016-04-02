package studentinfo;

import java.io.Serializable;

/**
 * Created by zjarsen on 25/3/16.
 */
public class Guest implements Serializable {
    private long ID;
    private String name;
    private String creditCardNum;
    private String address;
    private String country;
    private String identity;
    private String nationality;
    private String phoneNum;

    public Guest() {
    }

    public Guest(String name, String creditCardNum, String address, String country, String identity, String nationality, String phoneNum) {
        this.ID = System.currentTimeMillis(); //Let's use currentTimeToMillisecond to represent the ID
        this.name = name;
        this.creditCardNum = creditCardNum;
        this.address = address;
        this.country = country;
        this.identity = identity;
        this.nationality = nationality;
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", creditCardNum='" + creditCardNum + '\'' +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", identity='" + identity + '\'' +
                ", nationality='" + nationality + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}