package roominfo;

import java.io.Serializable;

public class RoomType implements Serializable {
	private String roomType;
	private boolean wifiEnabled;	// true for enabled, false for not enabled
	private String facing;
	private boolean smokingAllowed;	// true for allowed, false for not allowed
	private float price;
	private float discount;
	
	public RoomType() {
		//
	}
	
	public RoomType(String roomType, boolean wifiEnabled, String facing, boolean smokingAllowed, float price,
			float discount) {
		this.roomType = roomType;
		this.wifiEnabled = wifiEnabled;
		this.facing = facing;
		this.smokingAllowed = smokingAllowed;
		this.price = price;
		this.discount = discount;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public boolean isWifiEnabled() {
		return wifiEnabled;
	}

	public void setWifiEnabled(boolean wifiEnabled) {
		this.wifiEnabled = wifiEnabled;
	}

	public String getFacing() {
		return facing;
	}

	public void setFacing(String facing) {
		this.facing = facing;
	}

	public boolean isSmokingAllowed() {
		return smokingAllowed;
	}

	public void setSmokingAllowed(boolean smokingAllowed) {
		this.smokingAllowed = smokingAllowed;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}
}
