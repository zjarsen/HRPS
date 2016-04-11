package roominfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RoomManager {
	private static RoomManager singleInstance;
	private static RoomTypeController roomTypeController;
	private static RoomController roomController;
	
	public RoomManager() {
		roomTypeController = RoomTypeController.getInstance();
		roomController = RoomController.getInstance();
	}
	
	public static RoomManager getInstance() {
		if (singleInstance == null)
			singleInstance = new RoomManager();
		return singleInstance;
	}
	
	public void updateRoomTypes() {
		ArrayList<RoomType> roomType = roomTypeController.getRoomTypes();
		if (roomType != null) {
			int index = 0;
			System.out.println("Select the room type you wish to update:");
			for (RoomType rt : roomType) {
				System.out.println("[" + (index + 1)+ "]\t\"" + rt.getRoomType() + "\"");
				System.out.println("\tWiFi Enabled: " + (rt.isWifiEnabled() ? 'Y' : 'N'));
				index++;
			}
		}
	}
	
	public void addRoomTypes() {
		// Currently no error checking
		Scanner scan = new Scanner(System.in);
		System.out.print("Room Type Name: ");
		String roomTypeName = scan.nextLine();
		//rt.setRoomType(roomTypeName);
		boolean wifiEnabled = false;
		System.out.print("WiFi Enabled (Y/N): ");
		if (scan.nextLine().charAt(0) == 'Y' || scan.nextLine().charAt(0) == 'y')
			wifiEnabled = true;
		//rt.setWifiEnabled(wifiEnabled);
		System.out.print("With View Of (leave blank if not applicable): ");
		String facing = "";
		facing = scan.nextLine();
		//rt.setFacing(facing);
		System.out.print("Smoking Allowed (Y/N): ");
		boolean smokingAllowed = false;
		if (scan.nextLine().charAt(0) == 'Y' || scan.nextLine().charAt(0) == 'y')
			smokingAllowed = true;
		//rt.setSmokingAllowed(smokingAllowed);
		float price = (float) 0.0;
		System.out.print("Room Price: ");
		price = scan.nextFloat();
		//rt.setPrice(price);
		float discount = (float) 100.0;
		RoomType rt = new RoomType(roomTypeName, wifiEnabled, facing, smokingAllowed, price, discount);
		ArrayList<RoomType> roomTypeList = roomTypeController.getRoomTypes();
		roomTypeList.add(rt);
		if (roomTypeController.saveChanges())
			System.out.println("Room Type saved successfully");
		else
			System.out.println("Room Type failed to save");
	}
	
	public void updateRoom() {
		ArrayList<Room> roomList = roomController.getRooms();
		
		for (Room r : roomList) {
			System.out.println("Room ID: " + r.getRoomID());
			System.out.println("Type: " + r.getRoomType().getRoomType());
			System.out.println("WiFi Enabled: " + (r.getRoomType().isWifiEnabled() ? "Yes" : "No"));
			//System.out.println("Smoking Room: " + r.getRoomType().getPrice());
			System.out.println("Status: " + r.getStatus() + "\n");
		}
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
		if (roomController.saveChanges()) {
			System.out.println("Room added successfully");
		}
		else
			System.out.println("Failed to add room");
		scan.close();
	}
	
	public Room searchRoomByID(String roomID) {
		ArrayList<Room> rooms = roomController.getRooms();
		for (Room r : rooms) {
			if (r.getRoomID().equals(roomID))
				System.out.println("Room found!");
				return r;
		}
		System.out.println("Room not found");
		return null;
	}
	
	public boolean checkRoomAvailability(String roomID) {
		return false;
		//return Room.getStatus(roomID) == "Vacant" ? true : false;
	}
	
	public void printRoomStatsReport() {
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		
		System.out.println("[1] By Type");
		System.out.println("[2] By Status");
		
		choice = scan.nextInt();

		ArrayList<Room> rooms = roomController.getRooms();
		Set<Room> sRoom = new HashSet<Room>(rooms);
		
		for (Room r2 : sRoom) {
			System.out.println(r2.getRoomType() + "\n");
		}
		
		/*
		if (sel) {
			// loop
			System.out.print("Type : ");
			System.out.println("\tNumber : ");
			System.out.println("\t\tRooms : ");
		}
		else {
			// loop
			System.out.println("Status : ");
			System.out.print("\t\tRooms : ");
			
		}*/
		scan.close();
	}
}
