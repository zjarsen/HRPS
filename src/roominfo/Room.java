package roominfo;

import java.io.Serializable;

/**
 * Represents a room within the hotel
 * @author	SHAFIQAH BINTE ROSTAM
 * @since	2016-04-01
 */
public class Room implements Serializable {
	/**
	 * RoomType's serial number for storage purposes
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The room number (identifier) of this room
	 */
	private String roomID;
	/**
	 * The associated room type of this room
	 */
	private RoomType roomType;
	/**
	 * The current room status of this room.
	 * It can be in one of four states; Vacant, Occupied, Reserved and Under Maintenance
	 */
	private String status;		// vacant, occupied, reserved, under maintenance
	
	/**
	 * Creates a new Room without any meaningful data
	 */
	public Room() {
		roomID = "";
		roomType = null;
		status = "";
	}

	/**
	 * Creates a new Room with a given room number, its room type and status
	 * @param roomID	This Room's room number
	 * @param roomType	This Room's room type
	 * @param status	This Room's status
	 */
	public Room(String roomID, RoomType roomType, String status) {
		this.roomID = roomID;
		this.roomType = roomType;
		this.status = status;
	}

	/**
	 * Retrieves the room number of this Room
	 * @return	This Room's number
	 */
	public String getRoomID() {
		return roomID;
	}

	/**
	 * Changes the room number of this Room
	 * @param roomID	New room number
	 */
	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	/**
	 * Retrieves this Room's associated room type object
	 * @return	This Room's new RoomType object 
	 */
	public RoomType getRoomType() {
		return roomType;
	}

	/**
	 * Changes the room type object of this Room
	 * @param roomType	This Room's new RoomType object
	 */
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	/**
	 * Retrieves the current status of this Room
	 * @return	This Room's status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Changes the current status of this Room
	 * @param status	This Room's new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Retrieves this Room's formatted data regarding its room number, the details of its room type and its current status
	 * @return	This Room's information
	 */
	public String toString() {
		return "Number: " + roomID + "\nStatus: " + status + "\n" + roomType.toString();
	}
}
