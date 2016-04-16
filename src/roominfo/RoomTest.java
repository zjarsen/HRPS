package roominfo;

import java.util.Scanner;

public class RoomTest {

	public static void main(String[] args) {
		RoomManager manager = RoomManager.getInstance();
		Scanner scan = new Scanner(System.in);

		int choice = 0;
		//while (choice != 7) {
			System.out.println("What would you like to do?");
			System.out.println("[1] Add room");
			System.out.println("[2] Update room");
			System.out.println("[3] Add room type");
			System.out.println("[4] Update room type");
			System.out.println("[5] Print stats");
			System.out.println("[6] View all rooms");
			System.out.println("[7] Check availability");
			System.out.println("[8] Search room by ID");

			choice = scan.nextInt();
			
			switch (choice) {
			case 1:
				manager.addRoom();
				break;
			case 2:
				//manager.updateRoom();
				break;
			case 3:
				manager.addRoomTypes();
				break;
			case 4:
				manager.updateRoomTypes();
				break;
			case 5:
				manager.printRoomStatsReport();
				break;
			case 6:
				manager.viewAllRooms();
				break;
			case 7:
				System.out.print("Enter the room ID: ");
				String roomNum = scan.nextLine();
				if (manager.checkRoomAvailability(roomNum))
					System.out.println("Room is available");
				else
					System.out.println("Room is not available");
				break;
			case 8:
				System.out.print("Enter room ID: ");
				String roomID = scan.next();
				Room r = manager.searchRoomByID(roomID);
				if (r != null) {
					System.out.println("=== Room Information ===");
					manager.printRoomDetails(r);
				} else {
					System.out.println("No room found with given room number");
				}
				break;
			}
		//}
	}

}
