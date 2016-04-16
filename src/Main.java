import guestinfo.GuestManager;
import record.RecordManager;
import roominfo.RoomManager;
import orderinfo.OrderManager;

import java.util.Scanner;

public class Main {

    private static GuestManager guestManager;
    private static RecordManager recordManager;
    private static RoomManager roomManager;
    private static OrderManager orderManager;


    public static void main(String[] args) {
        guestManager = GuestManager.getInstance();
        recordManager = RecordManager.getInstance();
        roomManager = RoomManager.getInstance();
        orderManager = OrderManager.getInstance();

        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("");
            System.out.println("Perform the following methods:");
            System.out.println("(1)Guest Info Operations");
            System.out.println("(2)Room Info Operations");
            System.out.println("(3)Order Info Operations");
            System.out.println("(4)Record Operations");
            System.out.println("(5) Exit");
            System.out.println("");
            choice = sc.nextInt();
            switch (choice) {
                case -1:
                    System.out.println("you entered -1");
                    break;
                case 1:
                    guestOperation();
                    System.out.println("");
                    break;
                case 2:
                    roomOperation();
                    System.out.println("");
                    break;
                case 3:
                    orderOperation();
                    System.out.println("");
                    break;
                case 4:
                    recordOperation();
                    System.out.println("");
                    break;
                case 5: System.out.println("Program terminating ....");
            }
        } while (choice < 5);
    }


    /**
     * all operations where guest info involves
     */
    private static void guestOperation() {
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("");
            System.out.println("Perform the following methods:");
            System.out.println("(1)List all guests' information");
            System.out.println("(2)Add a new guest");
            System.out.println("(3)Update a guest by keywords");
            System.out.println("(4)Return to main menu");
            System.out.println("");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case -1:
                    System.out.println("you entered -1");
                    break;
                case 1:
                    guestManager.printAllGuests();
                    System.out.println("");
                    break;
                case 2:
                    guestManager.addGuest();
                    System.out.println("");
                    break;
                case 3:
                    guestManager.searchGuest();
                    System.out.println("");
                    break;
                case 4: System.out.println("Returning to Main Menu");
            }
        } while (choice < 4);
    }

    /**
     * all operations where room info involves
     */
    private static void roomOperation() {
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("");
            System.out.println("Perform the following methods:");
            System.out.println("(1) Search Room");
            System.out.println("(2) Change Availability/Room Details");
            System.out.println("(3) Print Room Statistics Report");
            System.out.println("(4) Return to main menu");
            System.out.println("");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case -1:
                    System.out.println("you entered -1");
                    break;
                case 1:
                    roomManager.printRoomByID();	// search for room with its id and print its room details
                    break;
                case 2:
                    /////////////////////////////////NOT COMPLETE////////////////////////////
                    roomManager.updateRoomDetails();	// update room details and status
                    break;
                case 3:
                    roomManager.printRoomStatsReport();
                    break;
                default:
                    System.out.println("Returning to Main Menu");
                    break;
            }
        } while (choice < 4);
    }

    /**
     * all operations where order info involves
     */
    private static void orderOperation() {
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("");
            System.out.println("Perform the following methods:");
            System.out.println("(1) Add a Menu Item");
            System.out.println("(2) Update a Menu Item");
            System.out.println("(3) Remove a Menu Item");
            System.out.println("(4) View Menu");
            System.out.println("(5) Return to main menu");
            System.out.println("");
            choice = sc.nextInt();
            switch (choice) {
                case -1:
                    System.out.println("you entered -1");
                    orderManager.addOrder();
                    break;
                case 1:
                    orderManager.addOrderItem();
                    break;
                case 2:
                    orderManager.updateOrderItem();
                    break;
                case 3:
                    orderManager.removeOrderItem();
                    break;
                case 4:
                    orderManager.printOrderItemList();
                    break;
                case 5:
                    System.out.println("Returning to Main Menu");
            }
        } while (choice < 5);
    }

    /**
     * all operations where record info involves
     */
    private static void recordOperation() {
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("");
            System.out.println("Perform the following methods:");
            System.out.println("(1)Create a reservation / Walk in check In");
            System.out.println("(2)Print all reservations");
            System.out.println("(3)Go to a reservation");
            System.out.println("(4)Return to main menu");
            System.out.println("");
            choice = sc.nextInt();
            switch (choice) {
                case -1:
                    System.out.println("you entered -1");
                    break;
                case 1:
                    recordManager.makeReservation();
                    System.out.println("");
                    break;
                case 2:
                    recordManager.printAllReservations();
                    break;
                case 3:
                    recordManager.searchReservation();
                    break;
                case 4:
                    System.out.println("");
            }
        } while (choice < 4);
    }
}
