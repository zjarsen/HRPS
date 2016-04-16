package guestinfo;

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

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreditCardNum() {
        return creditCardNum;
    }

    public void setCreditCardNum(String creditCardNum) {
        this.creditCardNum = creditCardNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

//    @Override
//    public String toString() {
//        return "Guest{" +
//                "ID=" + ID +
//                ", name='" + name + '\'' +
//                ", creditCardNum='" + creditCardNum + '\'' +
//                ", address='" + address + '\'' +
//                ", country='" + country + '\'' +
//                ", identity='" + identity + '\'' +
//                ", nationality='" + nationality + '\'' +
//                ", phoneNum='" + phoneNum + '\'' +
//                '}';
//    }


    @Override
    public String toString() {
        return "Guest Name: " + name + "\n" + "\t" +
                "Credit Card: " + creditCardNum + "\n" + "\t" +
                "Address: " + address + "\n" + "\t" +
                "Country: " + country + "\n" + "\t" +
                "ID: " + identity + "\n" + "\t"+
                "Nationality: " + nationality + "\n" + "\t" +
                "Phone Number: " + phoneNum;
    }
}