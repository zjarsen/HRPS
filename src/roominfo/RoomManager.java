package roominfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RoomManager {
	private static RoomManager singleInstance;
	private static RoomTypeController roomTypeController;
	private static RoomController roomController;
	
	private RoomManager() {
		roomTypeController = RoomTypeController.getInstance();
		roomController = RoomController.getInstance();
	}
	
	public static RoomManager getInstance() {
		if (singleInstance == null)
			singleInstance = new RoomManager();
		return singleInstance;
	}
	
	public void updateRoomTypes() {
		Scanner scan = new Scanner(System.in);
		ArrayList<RoomType> roomType = roomTypeController.getRoomTypes();
		if (roomType != null) {
			int index = 0;
			System.out.println("Select the room type you wish to update:");
			for (RoomType rt : roomType) {
				System.out.print("[" + (index + 1) +"] ");
				System.out.println(rt.toString());
				index++;
			}
			
			int sel = scan.nextInt();
			RoomType rtToUpdate = roomType.get(sel - 1);

			System.out.println("Select to modify: ");
			System.out.println("[1] Room Type Name");
			System.out.println("[2] WiFi Enabled");
			System.out.println("[3] View Description");
			System.out.println("[4] Smoking Allowed");
			System.out.println("[5] Price");
			System.out.println("[6] Quit");
			
			int choice = scan.nextInt();
			
			switch(choice) {
			case 1:
				System.out.println("Current: " + rtToUpdate.getRoomType());
				System.out.print("New: ");
				String newRTName = "";
				newRTName = scan.next();
				rtToUpdate.setRoomType(newRTName);
				if (roomTypeController.saveChanges())
					System.out.println("Room Type updated");
				else
					System.out.println("Error: Failed to update room name");
				break;
			}
				
		}
		scan.close();
	}
	
	public void addRoomTypes() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Room Type Name: ");
		String roomTypeName = scan.nextLine();
		boolean wifiEnabled = false;
		System.out.print("WiFi Enabled (Y/N): ");
		if (scan.nextLine().charAt(0) == 'Y' || scan.nextLine().charAt(0) == 'y')
			wifiEnabled = true;
		System.out.print("With View Of (leave blank if not applicable): ");
		String facing = "";
		facing = scan.nextLine();
		System.out.print("Smoking Allowed (Y/N): ");
		boolean smokingAllowed = false;
		if (scan.nextLine().charAt(0) == 'Y' || scan.nextLine().charAt(0) == 'y')
			smokingAllowed = true;
		float price = (float) 0.0;
		System.out.print("Room Price: ");
		price = scan.nextFloat();
		float discount = (float) 100.0;
		RoomType rt = new RoomType(roomTypeName, wifiEnabled, facing, smokingAllowed, price, discount);
		ArrayList<RoomType> roomTypeList = roomTypeController.getRoomTypes();
		roomTypeList.add(rt);
		if (roomTypeController.saveChanges())
			System.out.println("Room Type saved successfully");
		else
			System.out.println("Room Type failed to save");
		scan.close();
	}
	
	public void updateRoom() {
		//ArrayList<Room> roomList = roomController.getRooms();
		System.out.println("Not yet implemented");
	}
	
	public boolean updateRoomByRecord() {
		return false;
	}
	
	public void updateRoomByMenu() {
		
	}
	
	public void addRoom() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Room Number (floor-unit e.g. 02-04):");
		String roomID = scan.nextLine();
		System.out.println("Room Type (select):");
		ArrayList<RoomType> rt = roomTypeController.getRoomTypes();
		int index = 0;
		for (RoomType rt2 : rt) {
			System.out.println((index + 1) + ") " + rt2.getRoomType());
			System.out.println("\tWiFi Enabled: " + (rt2.isWifiEnabled() ? "Yes" : "No"));
			System.out.println("\tFacing: " + rt2.getFacing());
			System.out.println("\tSmoking Room: " + (rt2.isSmokingAllowed() ? "Yes" : "No"));
			System.out.println("\tPrice: " + rt2.getPrice());
			System.out.println("\tDiscount: " + rt2.getDiscount());
			index++;
		}
		
		RoomType roomieType = rt.get(scan.nextInt() - 1);
		
		System.out.println("Room Status (select):");
		System.out.println("[1] Vacant");
		System.out.println("[2] Occupied");
		System.out.println("[3] Reserved");
		System.out.println("[4] Under Maintenance");
		
		int selection = scan.nextInt();
		String status = "";
		
		switch (selection) {
		case 1:
			status = "Vacant";
			break;
		case 2:
			status = "Occupied";
			break;
		case 3:
			status = "Reserved";
			break;
		case 4:
			status = "Under Maintenance";
			break;
		default:
			break;
		}

		Room newRoom = new Room(roomID, roomieType, status);
		System.out.println("ID: " + newRoom.getRoomID());
		System.out.println("Status: " + newRoom.getStatus());
		ArrayList<Room> roomList = roomController.getRooms();
		roomList.add(newRoom);
		if (roomController.saveChanges())
			System.out.println("Room added successfully");
		else
			System.out.println("Failed to add room");
		scan.close();
	}
	
	public Room searchRoomByID(String roomID) {
		ArrayList<Room> rooms = roomController.getRooms();
		for (Room r : rooms) {
			if (r.getRoomID().equals(roomID)) {
				return r;
			}
		}
		return null;
	}
	
	/*public Room reserveRoom(String roomNum) {
		ArrayList<Room> rooms = roomController.getRooms();
		for (Room r : rooms) {
			if (r.getRoomID().equals(roomNum)) {
				r.setStatus("Reserved");
				if (roomController.saveChanges())
					return r;
			}
		}
		return null;
	}*/
	
	/*public void checkInRoom(Room r) {
		r.setStatus("Occupied");
	}
	
	public void checkOutRoom(Room r) {
		r.setStatus("Vacant");
	}*/
	
	public void printRoomDetails(Room r) {
		System.out.println(r.toString());
		System.out.println();
	}
	
	/*public void changeRoomStatus() {
		Scanner scan = new Scanner(System.in);
		String roomID = "";
		System.out.print("Enter the room number: ");
		roomID = scan.nextLine();
		ArrayList<Room> rooms = roomController.getRooms();
		boolean roomFound = false;
		Room rChange = new Room();
		for (Room r : rooms) {
			if (r.getRoomID().equals(roomID)) {
				roomFound = true;
				rChange = r;
				break;
			}
		}
		if (roomFound) {
			System.out.println("Room Number: " + rChange.getRoomID());
			System.out.print("Status: " + rChange.getStatus());
			
			updateRoomStatus(rChange);
		} else {
			System.out.println("No such room");
		}
		scan.close();
	}*/
	
	public void changeRoomStatus(Room r) {
		System.out.println("Room Number: " + r.getRoomID());
		System.out.print("Status: " + r.getStatus());
		
		updateRoomStatus(r);
	}
	
	public void updateRoomStatus(Room r) {
		Scanner scan = new Scanner(System.in);
		String[] status = {"Vacant", "Occupied", "Reserved", "Under Maintenance"};
		System.out.println("\nUpdate status to:");
		System.out.println("[1] Vacant");
		System.out.println("[2] Occupied");
		System.out.println("[3] Reserved");
		System.out.println("[4] Under Maintenance");
		
		int sel = scan.nextInt();
		
		r.setStatus(status[sel - 1]);
		if (roomController.saveChanges())
			System.out.println("Status changed successfully");
		else
			System.out.println("Error: Unable to change status");
		scan.close();
	}
	
	/**
	 * this method..
	 * @param roomID
	 * @return 
	 */
	public boolean checkRoomAvailability(String roomID) {
		ArrayList<Room> rooms = roomController.getRooms();
		for (Room r : rooms) {
			if (r.getRoomID().equals(roomID)) {
				if (r.getStatus().equals("Vacant"))
					return true;
			}
		}
		return false;
	}
	
	public void printRoomStatsReport() {
		Scanner scan = new Scanner(System.in);
		int sel = 0;
		
		System.out.println("[1] By Type");
		System.out.println("[2] By Status");
		
		sel = scan.nextInt();

		ArrayList<Room> rooms = roomController.getRooms();
		
		if (sel == 1) {
			ArrayList<String> roomTypeStrings = new ArrayList<String>();
			for (Room r : rooms) {
				if (r.getStatus().equals("Vacant"))
					roomTypeStrings.add(r.getRoomType().getRoomType());
			}
			
			Set<String> sRoom = new HashSet<String>(roomTypeStrings);

			for (String r2 : sRoom) {			
				int numOfVacancy = 0;
				int numOfRooms = 0;

				ArrayList<String> roomNums = new ArrayList<String>();
				for (Room r3 : rooms) {
					
					if (r3.getRoomType().getRoomType().equals(r2)) {
						if (r3.getStatus().equals("Vacant")) {
							roomNums.add(r3.getRoomID());
							numOfVacancy++;
						}
						numOfRooms++;
					}
					
				}

				System.out.println(r2 + " : \tNumber : " + numOfVacancy + " out of " + numOfRooms);
				System.out.print("\t\tRooms : ");
				for (int i = 0; i < roomNums.size(); i++) {
					System.out.print(roomNums.get(i));
					if (i != roomNums.size() - 1) {
						System.out.print(", ");
					}
				}
				System.out.println("");
			}
		} else {
			ArrayList<String> statuses = new ArrayList<String>();
			for (Room r : rooms) {
				statuses.add(r.getStatus());
			}
			
			Set<String> distinctStatuses = new HashSet<String>(statuses);
			
			for (String s : distinctStatuses) {
				System.out.println(s + " : ");
				System.out.print("\tRooms : ");
				for (Room r : rooms) {
					if (r.getStatus().equals(s)) {
						System.out.print(r.getRoomID() + ", ");
					}
				}
				System.out.println("");
			}
		}
		scan.close();
	}
	
	// FOR TESTING
	public void viewAllRooms() {
		ArrayList<Room> rooms = roomController.getRooms();
		for (Room r : rooms) {
			printRoomDetails(r);
		}
	}
}
