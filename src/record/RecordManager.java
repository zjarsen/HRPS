package record;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.lang.Long;

import guestinfo.Guest;
import guestinfo.GuestManager;
import orderinfo.Order;
import roominfo.Room;
import roominfo.RoomManager;
import orderinfo.OrderManager;

/**
 * Created by zjarsen on 11/4/16.
 */
public class RecordManager {

    /**
     * the singleton of this manager class. Same theory with controller class
     */
    private static RecordManager singleInstance;

    /**
     * need to use the singleton of the RecordController class
     */
    private RecordController recordController;
    private GuestManager guestManager;
    private RoomManager roomManager;
    private OrderManager orderManager;





    /**
     * Constructor of this manager class. The instantiation automatically gets the singleton of its controller class and
     * save the singleton into the "RecordController" attribute
     */
    private RecordManager(){
        recordController = RecordController.getInstance();
        guestManager = GuestManager.getInstance();
        roomManager = RoomManager.getInstance();
        orderManager = OrderManager.getInstance();
    }

    /**
     * To return the singleton of this RecordManager class to its caller
     * @return the singleton of RecordManager class
     */
    public static RecordManager getInstance(){
        if (singleInstance==null){
            singleInstance=new RecordManager();
        }
        return singleInstance;
    }

    /**
     * return all record entries
     * @return all record entries
     */
    public ArrayList<Record> getRecordList() {
        return this.recordController.getRecords();
    }

    /**
     * Search a reservation and perform the update
     */
    public void searchReservation() {
        Scanner sc = new Scanner(System.in);
        Record theRecord = new Record();
        boolean foundARecord = false;
        while (foundARecord == false) {
            System.out.println("Please enter the guest's name");
            String newguest = sc.nextLine();
            for (Record i : recordController.getRecords()) {
                if (i.getGuest().getName().equals(newguest) && i.isValid() && i.getCheckOutDate() == null) {
                    theRecord = i;
                }
            }
            if (theRecord.isValid() != true) {
                System.out.println("You may entered a wrong name or the reservation is outdated. Want to try again? 1: yes / 2: no ");
                int choice1 = sc.nextInt();
                sc.nextLine();
                if (choice1 == 2) {
                    System.out.println("Returning to the previous menu");
                    return;
                }
            } else {
                foundARecord = true;
            }
        }
        System.out.println("");
        System.out.println("The detailed information of the reservation: ");
        System.out.println("");
        System.out.println(theRecord.toString());


        int choice;
        do {
            System.out.println("");
            System.out.println("Perform the following methods:");
            System.out.println("(1) Check the guest in");
            System.out.println("(2) Make an order");
            System.out.println("(3) Update an order");
            System.out.println("(4) Display the orders");
            System.out.println("(5) Check the guest out");
            System.out.println("(6) Return to previous menu");
            System.out.println("");
            choice = sc.nextInt();
            switch (choice) {
                case -1:
                    System.out.println("you entered -1");
                    break;
                case 1:
                    checkGuestIn(theRecord);
                    System.out.println("The guest is checked in");
                    System.out.println("");
                    break;
                case 2:
                    if (!theRecord.isCheckedIn()) {
                        System.out.println("Please check the guest in to continue the process");
                        break;
                    }
                    addOrder(theRecord);
                    System.out.println("");
                    break;
                case 3:
                    if (!theRecord.isCheckedIn()) {
                        System.out.println("Please check the guest in to continue the process");
                        break;
                    }
                    if (theRecord.getOrders() == null) {
                        System.out.println("The guest hasn't made any orders");
                        break;
                    }
                    System.out.println("Pleaase choose an order to update status.");
                    displayOrders(theRecord);
                    int orderChoice = sc.nextInt();
                    theRecord.getOrders().get(orderChoice-1).setStatus(orderManager.updateOrder(theRecord.getOrders().get(orderChoice-1).getOrderTime()));
                    break;
                case 4:
                    if (!theRecord.isCheckedIn()) {
                        System.out.println("Please check the guest in to continue the process");
                        break;
                    }
                    displayOrders(theRecord);
                    System.out.println("");
                    break;
                case 5:
                    if (!theRecord.isCheckedIn()) {
                        System.out.println("Please check the guest in to continue the process");
                        break;
                    }
                    checkGuestOut(theRecord);
                    System.out.println("Check out is successful! Returning to the previous menu");
                    return;
            }
        } while (choice < 6);
    }


    /**
     * make a reservation (also for walk-in by setting the checkin date to today
     */
    public void makeReservation() {
        Scanner sc = new Scanner(System.in);
        Room newRoom = new Room();
        Guest newGuest = new Guest();
        Record newRecord = new Record();
        System.out.println("Is it a new guest? 1: yes / 2: no ");
        int choice1 = sc.nextInt();
        boolean foundAGuest = false;
        sc.nextLine();
        if (choice1 == 1) {
            newGuest = guestManager.addGuest();
        } else {
            while (foundAGuest == false) {
                System.out.println("Please input the guest's name");
                String newName = sc.nextLine();
                newGuest = guestManager.searchGuestByKeyWords(newName);
                if (newGuest == null) {
                    System.out.println("Would you like to search again? 1: yes / 2: no");
                    int searchChoice = sc.nextInt();
                    sc.nextLine();
                    if (searchChoice == 2) {
                        System.out.println("Returning to the previous menu....");
                        return;
                    }
                } else {
                    foundAGuest = true;
                }
            }
        }
        newRecord.setGuest(newGuest);
        System.out.println("How many days later do you want to check in? \nIf it's a walk-in check-in, then input 0");
        int daysTillCheckIn = sc.nextInt();
        sc.nextLine();
        Long checkInTime = System.currentTimeMillis() - 86400000 * daysTillCheckIn;
        newRecord.setCheckInDate(checkInTime);
        System.out.println("The available rooms are listed below: ");
        roomManager.viewAllAvailableRooms();
        System.out.println("Please enter the room number");
        String roomId = "";
        boolean foundARoom = false;
        while (foundARoom == false) {
            roomId = sc.nextLine();
            newRoom = roomManager.searchRoomByID(roomId);
            if (newRoom == null) {
                System.out.println("Cannot find the room, please enter the room ID again. ");
            } else {
                if (newRoom.getStatus().equals("Vacant") == true) {
                    foundARoom = true;
                }
            }
        }
        newRecord.setRoom(newRoom);
        roomManager.setRoomReserved(roomId);
        System.out.println("Discount available? 1: yes / 2: no ");
        int choice2 = sc.nextInt();
        if (choice2 == 1) {
            newRecord.setHasDiscount(true);
        }
        newRecord.setValid(true);
        if (daysTillCheckIn == 0) {
            newRecord.setIsCheckedIn(true);
            newRecord.getRoom().setStatus("Occupied");
            roomManager.setRoomOccupied(newRecord.getRoom().getRoomID());
        }
        //extract the record entries from the file database
        ArrayList<Record> recordList = recordController.getRecords();
        //do the modification on the guest entries
        recordList.add(newRecord);
        //save the modified entries back to the file database
        recordController.saveChanges();
        System.out.println("");
        if (daysTillCheckIn == 0) {
            System.out.println("The guest is checked in");
        } else {
            System.out.println("Congratulations! A new reservation is successfully made.");
        }

        System.out.println("");
        System.out.println("The reservation confirmation: ");
        System.out.println("");
        System.out.println(newRecord.getGuest().toString());
        System.out.println(newRecord.getRoom().toString());
    }

    /**
     * check in choice
     * @param theRecord
     */
    private void checkGuestIn(Record theRecord) {
        roomManager.setRoomOccupied(theRecord.getRoom().getRoomID());
        for (Record i : recordController.getRecords()) {
            if (theRecord.getID() == i.getID()) {
                i.setIsCheckedIn(true);
            }
        }
        recordController.saveChanges();
    }

    /**
     * add order
     * @param theRecord
     */
    private void addOrder(Record theRecord) {
        ArrayList<Order> theOrders = theRecord.getOrders();
        if (theOrders == null) {
            theOrders = new ArrayList<Order>();
        }
        theOrders.add(orderManager.addOrder());
        for (Record i : recordController.getRecords()) {
            if (theRecord.getID() == i.getID()) {
                i.setOrders(theOrders);
            }
        }
        recordController.saveChanges();
    }

    /**
     * display all orders
     * @param theRecord
     */
    private void displayOrders(Record theRecord) {
        if (theRecord.getOrders() == null) {
            System.out.println("The guest hasn't made any orders by now.");
        } else {
            for (int i=0;i< theRecord.getOrders().size();i++) {
            	System.out.println("Order "+(i+1));
                System.out.println(theRecord.getOrders().get(i).toString());
            }
        }
    }

    /**
     * check out the guest
     * @param theRecord
     */
    private void checkGuestOut(Record theRecord) {
        roomManager.setRoomVacant(theRecord.getRoom().getRoomID());
        theRecord.setCheckOutDate(System.currentTimeMillis());
        theRecord.setTotalPrice(updateTotalPrice(theRecord));
        for (Record i : recordController.getRecords()) {
            if (theRecord.getID() == i.getID()) {
                i.setCheckOutDate(System.currentTimeMillis());
                i.setValid(false);
                i.getRoom().setStatus("Vacant");
                i.setCheckOutDate(System.currentTimeMillis());
            }
        }
        recordController.saveChanges();

        System.out.println("");
        System.out.println("=================RECIEPT================");
        System.out.println("");

        System.out.println("Guest Info: ");
        System.out.println("");
        System.out.println(theRecord.getGuest().toString());
        System.out.println("");
        System.out.println("........................................");
        System.out.println("");
        System.out.println("Order Info: ");
        System.out.println("");

        if (theRecord.getOrders() != null) {
            displayOrders(theRecord);
        } else {
            System.out.println("The guest didn't make any order");
        }

        System.out.println("........................................");
        System.out.println("");
        System.out.println("Room Info: ");
        System.out.println("");
        System.out.println(theRecord.getRoom().toString());
        System.out.println("Discount implied? ");
        if (theRecord.hasDiscount()) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        System.out.println("");
        System.out.println("........................................");
        System.out.println("");

        System.out.println("Reservation Info: \n");
        Calendar calendarIn = Calendar.getInstance();
        Calendar calendarOut = Calendar.getInstance();
        calendarIn.setTimeInMillis(theRecord.getCheckInDate());
        calendarOut.setTimeInMillis(theRecord.getCheckOutDate());
        System.out.println("Check in Date: ");
        System.out.println(calendarIn.get(Calendar.YEAR) + "-" + (1 + calendarIn.get(Calendar.MONTH)) + "-" + calendarIn.get(Calendar.DATE));
        System.out.println("Check out Date: ");
        System.out.println(calendarOut.get(Calendar.YEAR) + "-" + (1 + calendarOut.get(Calendar.MONTH)) + "-" + calendarOut.get(Calendar.DATE));
        System.out.println("");
        System.out.println("........................................");
        System.out.println("");
        System.out.print("Total price: $");


        System.out.println((int)theRecord.getTotalPrice());

        System.out.println("Proceeding the payment.......");
    }

    /**
     *update the total price
     */
    private double updateTotalPrice(Record theRecord) {
        double sum;
        sum = theRecord.getRoom().getRoomType().getPrice();
        Calendar calendarIn = Calendar.getInstance();
        Calendar calendarOut = Calendar.getInstance();
        calendarIn.setTimeInMillis(theRecord.getCheckInDate());
        calendarOut.setTimeInMillis(theRecord.getCheckOutDate());
        int days = calendarOut.get(Calendar.DAY_OF_YEAR) - calendarIn.get(Calendar.DAY_OF_YEAR);
        if (days <1) {
            days = 1;
        }

        if (theRecord.hasDiscount()) {
            sum = sum * (1 - theRecord.getRoom().getRoomType().getDiscount()) * days;
        }
        if (theRecord.getOrders() != null) {
            for (Order i : theRecord.getOrders()) {
                sum += i.getTotalPrice();
            }
        }
        return sum;
    }

    /**
     * print all reservations
     */
    public void printAllReservations() {
        for (Record i : getRecordList()) {
            if (i.getRoom().getStatus().equals("Reserved") || (i.isCheckedIn() && i.getCheckOutDate()==null)) {
                System.out.println("........................................");
                System.out.println(i.toString());
            }
        }
    }


}
