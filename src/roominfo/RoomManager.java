package roominfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RoomManager {
	private static RoomManager singleInstance;
	private static RoomTypeController roomTypeController;
	private static RoomController roomController;

	/**
	 * update the singletons
	 */
	private RoomManager() {
		roomTypeController = RoomTypeController.getInstance();
		roomController = RoomController.getInstance();
	}

	/**
	 * get the singleton instance
	 * @return
	 */
	public static RoomManager getInstance() {
		if (singleInstance == null)
			singleInstance = new RoomManager();
		return singleInstance;
	}

	/**
	 * update the room type
	 */
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
				System.out.println("Current: " + rtToUpdate.getType());
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
	}

	/**
	 * Method to add new room types for selection when adding new rooms.
	 */
	public void addRoomTypes() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Room Type Name: ");
		String roomTypeName = scan.nextLine();
		boolean wifiEnabled = false;
		System.out.println("WiFi Enabled? 1: yes 2: no");
		int wifiOpt = scan.nextInt();
		if (wifiOpt == 1) {
			wifiEnabled = true;
		}
		scan.nextLine();
		System.out.print("With View Of (leave blank if not applicable): ");
		String facing = scan.nextLine();
		System.out.println("Smoking Allowed? 1: yes 2: no");
		boolean smokingAllowed = false;
		int smokingOpt = scan.nextInt();
		if (smokingOpt == 1) {
			smokingAllowed = true;
		}
		scan.nextLine();
		System.out.print("Price: ");
		float price = scan.nextFloat();
		System.out.print("Discount Amount: ");
		float discount = scan.nextFloat();
		RoomType rt = new RoomType(roomTypeName, wifiEnabled, facing, smokingAllowed, price, discount);
		ArrayList<RoomType> roomTypeList = roomTypeController.getRoomTypes();
		roomTypeList.add(rt);
		if (roomTypeController.saveChanges())
			System.out.println("Room Type saved successfully");
		else
			System.out.println("Room Type failed to save");
	}

	/**
	 * add a room, for testing and fake data setup
	 */
	public void addRoom() {
		ArrayList<RoomType> rt = roomTypeController.getRoomTypes();
		if (rt.isEmpty()) {
			System.out.println("Error: No room types unavailable, unable to add rooms");
		} else {
			Scanner scan = new Scanner(System.in);
			System.out.print("Room Number (floor-unit e.g. 02-04): ");
			String roomID = scan.nextLine();
			while (!roomID.matches("\\d{2}-\\d{2}")) {
				System.out.println("Error: Invalid room number. Please try again.");
				System.out.print("\nRoom Number (floor-unit e.g. 02-04): ");
				roomID = scan.nextLine();
			}
			System.out.println("Room Type (select):");
			int index = 0;
			for (RoomType rt2 : rt) {
				System.out.println((index + 1) + ") " + rt2.getType());
				System.out.println("\tWiFi Enabled: " + (rt2.isWifiEnabled() ? "Yes" : "No"));
				System.out.print("\tWith View Of: " + rt2.getFacing());
				if (rt2.getFacing().isEmpty())
					System.out.println("NA");
				System.out.println("\tSmoking Room: " + (rt2.isSmokingAllowed() ? "Yes" : "No"));
				System.out.println("\tPrice: " + rt2.getPrice());
				System.out.println("\tDiscount: " + rt2.getDiscount());
				index++;
			}

			RoomType roomieType = rt.get(scan.nextInt() - 1);

			Room newRoom = new Room(roomID, roomieType, "Vacant");
			ArrayList<Room> roomList = roomController.getRooms();
			roomList.add(newRoom);
			if (roomController.saveChanges())
				System.out.println("Room added successfully");
			else
				System.out.println("Failed to add room");
		}
	}

	/**
	 * search room by ID
	 * @param roomID
	 * @return
	 */
	public Room searchRoomByID(String roomID) {
		ArrayList<Room> rooms = roomController.getRooms();
		for (Room r : rooms) {
			if (r.getRoomID().equals(roomID)) {
				return r;
			}
		}
		return null;
	}

	/**
	 * print a room
	 * @param r
	 */
	public void printRoomDetails(Room r) {
		System.out.println(r.toString());
		System.out.println();
	}

	/**
	 * print a room and change a room status
	 * @param r
	 */
	public void changeRoomStatus(Room r) {
		System.out.println("Room Number: " + r.getRoomID());
		System.out.print("Status: " + r.getStatus());
		
		updateRoomStatus(r);
	}

	/**
	 * update the room
	 * @param r
	 */
	public void updateRoomStatus(Room r) {
		Scanner scan = new Scanner(System.in);
		String[] status = {"Vacant", "Occupied", "Reserved", "Under Maintenance"};
		System.out.println("\nUpdate status to:");
		System.out.println("[1] Vacant");
		System.out.println("[2] Occupied");
		System.out.println("[3] Reserved");
		System.out.println("[4] Under Maintenance");
		
		int sel = scan.nextInt();
		scan.nextLine();

		r.setStatus(status[sel - 1]);
		if (roomController.saveChanges())
			System.out.println("Status changed successfully");
		else
			System.out.println("Error: Unable to change status");
	}

	/**
	 * Checks the availability of a room through a given room ID. If the room is available ("Vacant"), it will return true. Otherwise it will return false if the room is in any of the other states ("Reserved", "Occupied", and "Under Maintenance")
	 * @param roomID	The room number of the Room to check
	 * @return			The availability of the Room
	 */
	public boolean checkRoomAvailability(String roomID) {
		ArrayList<Room> rooms = roomController.getRooms();
		for (Room r : rooms) {
			if (r.getRoomID().equals(roomID)) {
				if (r.getStatus().equals("Vacant")) {
					return true;
				}
				return false;
			}
		}
		return false;
	}

	/**
	 * Prints a statistical report of the rooms in the hotel.
	 * Option 1 prints the number of vacant rooms out of the total number of rooms categorized by their room type.
	 * Option 2 prints all the rooms categorized by their status.
	 */
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
					roomTypeStrings.add(r.getRoomType().getType());
			}

			Set<String> sRoom = new HashSet<String>(roomTypeStrings);

			for (String r2 : sRoom) {
				int numOfVacancy = 0;
				int numOfRooms = 0;

				ArrayList<String> roomNums = new ArrayList<String>();
				for (Room r3 : rooms) {

					if (r3.getRoomType().getType().equals(r2)) {
						if (r3.getStatus().equals("Vacant")) {
							roomNums.add(r3.getRoomID());
							numOfVacancy++;
						}
						numOfRooms++;
					}

				}

				System.out.println(r2 + " : \tNumber : " + numOfVacancy + " out of " + numOfRooms);
				System.out.print("\t\t\tRooms : ");
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

				ArrayList<String> roomNums = new ArrayList<String>();

				for (Room r : rooms) {
					if (r.getStatus().equals(s)) {
						roomNums.add(r.getRoomID());
					}
				}
				for (int i = 0; i < roomNums.size(); i++) {
					System.out.print(roomNums.get(i));
					if (i != roomNums.size() - 1) {
						System.out.print(", ");
					}
				}
				System.out.println("");
			}
		}
	}

	/**
	 * print all rooms
	 */
	public void viewAllRooms() {
		ArrayList<Room> rooms = roomController.getRooms();
		for (Room r : rooms) {
			printRoomDetails(r);
		}
	}

	/**
	 * print available rooms
	 */
	public void viewAllAvailableRooms() {
		for (Room r : roomController.getRooms()) {
			if (r.getStatus().equals("Vacant")) {
				printRoomDetails(r);
			}
		}
	}

	/**
	 * set room reserved
	 * @param roomId
	 */
	public void setRoomReserved(String roomId) {
		for (Room r : roomController.getRooms()) {
			if (r.getRoomID().equals(roomId)) {
				r.setStatus("Reserved");
				roomController.saveChanges();
			}
		}
	}

	/**
	 * set room vacant
	 * @param roomId
	 */
	public void setRoomVacant(String roomId) {
		for (Room r : roomController.getRooms()) {
			if (r.getRoomID().equals(roomId)) {
				r.setStatus("Vacant");
				roomController.saveChanges();
			}
		}
	}

	/**
	 * set room occupied
	 * @param roomId
	 */
	public void setRoomOccupied(String roomId) {
		for (Room r : roomController.getRooms()) {
			if (r.getRoomID().equals(roomId)) {
				r.setStatus("Occupied");
			}
		}
		roomController.saveChanges();
	}

	/**
	 * print the room by ID
	 */
	public void printRoomByID() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter Room Number: ");
		String roomID = scan.nextLine();
		Room r = searchRoomByID(roomID);
		if (r != null)
			System.out.println(r.toString());
		else
			System.out.println("Room with number " + roomID + " not found");
	}

	/**
	 * Update a room's detail
	 */
	public void updateRoomDetails() {
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter Room Number: ");

		String roomID = scan.nextLine();

		Room r = searchRoomByID(roomID);

		if (r == null) {
			System.out.println("Room does not exist");
		} else {
			System.out.print("Change availability: ");
			changeRoomStatus(r);
		}

		// update room details and availability

	}

}
