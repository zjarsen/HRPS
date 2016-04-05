package roominfo;

import java.io.Serializable;

public class Room implements Serializable {
	private String roomID;
	private RoomType roomType;
	private String status;		// vacant, occupied, reserved, under maintenance
	
	public Room() {
		// testing for GIT
	}

	public Room(String roomID, RoomType roomType, String status) {
		super();
		this.roomID = roomID;
		this.roomType = roomType;
		this.status = status;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String toString() {
		return "RoomID: " + roomID + "\nRoom Type: " + roomType.getRoomType();
	}
}
