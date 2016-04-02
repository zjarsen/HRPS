package studentinfo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by zjarsen on 30/3/16.
 */
public class GuestManager {
    /**
     * the singleton of this manager class. Same theory with controller class
     */
    private static GuestManager singleInstance;

    /**
     * need to use the singleton of the GuestController class
     */
    private GuestController guestController;






    /**
     * Constructor of this manager class. The instantiation automatically gets the singleton of its controller class and
     * save the singleton into the "guestController" attribute
     */
    private GuestManager(){
        guestController=GuestController.getInstance();
    }

    /**
     * To return the singleton of this GuestManager class to its caller
     * @return the singleton of GuestManager class
     */
    public static GuestManager getInstance(){
        if (singleInstance==null){
            singleInstance=new GuestManager();
        }
        return singleInstance;
    }


    /**
     * add a new guest to the database
     */
    public void addGuest() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the guest's name:");
        String name = sc.nextLine();
        System.out.println("Enter the credit card number");
        String creditCardNum = sc.nextLine();
        System.out.println("Enter the address");
        String address=sc.nextLine();
        System.out.println("Enter the country");
        String country = sc.nextLine();
        System.out.println("Enter the passport/driving license number");
        String identity = sc.nextLine();
        System.out.println("Enter the nationality");
        String nationality = sc.nextLine();
        System.out.println("Enter the phone number");
        String phoneNum = sc.nextLine();
        Guest newGuest = new Guest(name, creditCardNum, address, country, identity, nationality, phoneNum);
        //extract the guest entries from the file database
        ArrayList<Guest> guestList = guestController.getGuests();
        //do the modification on the guest entries
        guestList.add(newGuest);
        //save the modified entries back to the file database
        guestController.saveChanges();
    }


    /**
     * return all guest entries
     * @return all guest entries
     */
    public ArrayList<Guest> getGuestList() {
        return this.guestController.getGuests();
    }


    /**
     * print all guests' information
     */
    public void printAllGuests(){
        for (Guest i : getGuestList()) {
            System.out.println(i.toString());
        }
    }


    /**
     * update the guest's information
     */
    public void updateGuest() {

    }


    /**
     * search a guest by its ID
     * @param guestID
     * @return the guest object
     */
    public Guest searchGuestById(int guestID) {

        return null;
    }


    /**
     * search a guest by its keywords
     */
    public void searchGuestByKeyWords() {

    }
}
