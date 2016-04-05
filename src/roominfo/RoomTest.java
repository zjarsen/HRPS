package roominfo;

import java.util.Scanner;

public class RoomTest {

	public static void main(String[] args) {
		RoomManager manager = RoomManager.getInstance();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("What would you like to do?");
		System.out.println("[1] Add room");
		System.out.println("[2] Update room");
		System.out.println("[3] Add room type");
		System.out.println("[4] Update room type");

		int choice = 0;
		choice = scan.nextInt();
		
		switch (choice) {
		case 1:
			manager.addRoom();
			break;
		case 2:
			manager.updateRoom();
			break;
		case 3:
			manager.addRoomTypes();
			break;
		case 4:
			manager.updateRoomTypes();
			break;
		}
		
		//manager.updateRoomTypes();
		//manager.addRoomTypes();
	}

}
