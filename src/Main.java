import guestinfo.GuestManager;

import java.util.Scanner;

public class Main {

    private static GuestManager guestManager;

    public static void main(String[] args) {
        guestManager = GuestManager.getInstance();

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
            System.out.println("(3)Search guests by keywords");
            System.out.println("(4)Return to main menu");
            System.out.println("");
            choice = sc.nextInt();
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
                    guestManager.searchGuestByKeyWords();
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
            System.out.println("(1)Search Room and Show its Information by Room ID");
            System.out.println("(2)Change the availability of a room");
            System.out.println("(3)Update Room Type details");
            System.out.println("(4)Print Room Status Statistics Report");
            System.out.println("(5)Return to main menu");
            System.out.println("");
            choice = sc.nextInt();
            switch (choice) {
                case -1:
                    System.out.println("you entered -1");
                    break;
                case 1:
                    System.out.println("");
                    break;
                case 2:
                    System.out.println("");
                    break;
                case 3:
                    System.out.println("");
                    break;
                case 4: System.out.println("Returning to Main Menu");
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
            System.out.println("(1)Update Order Item List");
            System.out.println("(2)Create an Order");
            System.out.println("(3) ");
            System.out.println("(4)Return to main menu");
            System.out.println("");
            choice = sc.nextInt();
            switch (choice) {
                case -1:
                    System.out.println("you entered -1");
                    break;
                case 1:
                    System.out.println("");
                    break;
                case 2:
                    System.out.println("");
                    break;
                case 3:
                    System.out.println("");
                    break;
                case 4: System.out.println("Returning to Main Menu");
            }
        } while (choice < 4);
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
            System.out.println("(1)Create a reservation");
            System.out.println("(2)Cancel a reservation");
            System.out.println("(3)Check a guest in");
            System.out.println("(4)Check a guest out");
            System.out.println("(5)Return to main menu");
            System.out.println("");
            choice = sc.nextInt();
            switch (choice) {
                case -1:
                    System.out.println("you entered -1");
                    break;
                case 1:
                    System.out.println("");
                    break;
                case 2:
                    System.out.println("");
                    break;
                case 3:
                    System.out.println("");
                    break;
                case 4: System.out.println("Returning to Main Menu");
            }
        } while (choice < 4);
    }
}
