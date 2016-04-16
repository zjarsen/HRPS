package guestinfo;

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
    public Guest addGuest() {
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
        return newGuest;
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
     * search a guest by its keywords and do the update
     */
    public Guest searchGuestByKeyWords(String name) {
        for (Guest i : getGuestList()) {
            if (i.getName().equals(name)) {
                return i;
            }
        }
        System.out.println("The guest is not found.");
        return null;
    }

    public void searchGuest() {
        Scanner sc = new Scanner(System.in);
        Guest theGuest = new Guest();
        boolean foundAGuest = false;
        while (foundAGuest == false) {
            System.out.println("Please enter the guest's name");
            String newguest = sc.nextLine();
            for (Guest i : guestController.getGuests()) {
                if (i.getName().equals(newguest)) {
                    theGuest = i;
                }
            }
            if (theGuest.getName() == null) {
                System.out.println("The name is not stored. Want to try again? 1: yes / 2: no ");
                int choice1 = sc.nextInt();
                sc.nextLine();
                if (choice1 == 2) {
                    System.out.println("Returning to the previous menu");
                    return;
                }
            } else {
                foundAGuest = true;
            }
        }
        System.out.println("");
        System.out.println("The detailed information of the guest: ");
        System.out.println("");
        System.out.println(theGuest.toString());


        String newInfo = "";
        int choice;
        do {
            System.out.println("");
            System.out.println("Perform the following methods:");
            System.out.println("(1) Update the guest's name");
            System.out.println("(2) Update the guest's credit card number");
            System.out.println("(3) Update the guest's address");
            System.out.println("(4) Update the guest's country");
            System.out.println("(5) Update the guest's identity");
            System.out.println("(6) Update the guest's nationality");
            System.out.println("(7) Update the guest's phoneNum");
            System.out.println("(8) print the guest's information");
            System.out.println("(9) Return to previous menu...");
            System.out.println("");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case -1:
                    System.out.println("you entered -1");
                    break;
                case 1:
                    System.out.println("Enter the new guest info: ");
                    newInfo = sc.nextLine();
                    theGuest.setName(newInfo);
                    for (Guest i : guestController.getGuests()) {
                        i.setName(newInfo);
                    }
                    guestController.saveChanges();
                    System.out.println("");
                    break;
                case 2:
                    System.out.println("Enter the new guest info: ");
                    newInfo = sc.nextLine();
                    theGuest.setCreditCardNum(newInfo);
                    for (Guest i : guestController.getGuests()) {
                        i.setCreditCardNum(newInfo);
                    }
                    guestController.saveChanges();
                    System.out.println("");
                    break;
                case 3:
                    System.out.println("Enter the new guest info: ");
                    newInfo = sc.nextLine();
                    theGuest.setAddress(newInfo);
                    for (Guest i : guestController.getGuests()) {
                        i.setAddress(newInfo);
                    }
                    guestController.saveChanges();
                    System.out.println("");
                    break;
                case 4:
                    System.out.println("Enter the new guest info: ");
                    newInfo = sc.nextLine();
                    theGuest.setCountry(newInfo);
                    for (Guest i : guestController.getGuests()) {
                        i.setCountry(newInfo);
                    }
                    guestController.saveChanges();
                    System.out.println("");
                    break;
                case 5:
                    System.out.println("Enter the new guest info: ");
                    newInfo = sc.nextLine();
                    theGuest.setIdentity(newInfo);
                    for (Guest i : guestController.getGuests()) {
                        i.setIdentity(newInfo);
                    }
                    guestController.saveChanges();
                    System.out.println("");
                case 6:
                    System.out.println("Enter the new guest info: ");
                    newInfo = sc.nextLine();
                    theGuest.setNationality(newInfo);
                    for (Guest i : guestController.getGuests()) {
                        i.setNationality(newInfo);
                    }
                    guestController.saveChanges();
                    System.out.println("");
                case 7:
                    System.out.println("Enter the new guest info: ");
                    newInfo = sc.nextLine();
                    theGuest.setPhoneNum(newInfo);
                    for (Guest i : guestController.getGuests()) {
                        i.setPhoneNum(newInfo);
                    }
                    guestController.saveChanges();
                    System.out.println("");
                    break;
                case 8:
                    System.out.println(theGuest.toString());
                    System.out.println("");
            }
        } while (choice < 9);
    }
}
