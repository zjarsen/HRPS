package roominfo;

import java.io.Serializable;

/**
 * Represents a room type associated with a room
 * A room type can be for many rooms
 * @author	SHAFIQAH BINTE ROSTAM
 * @since	2016-04-01
 */
public class RoomType implements Serializable {
	/**
	 * RoomType's serial number for storage purposes
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The title of this RoomType
	 */
	private String roomType;
	/**
	 * The availability of WiFi in this RoomType. "true" for WiFi availability, "false" for otherwise.
	 */
	private boolean wifiEnabled;	// true for enabled, false for not enabled
	/**
	 * The short description of this RoomType's view (e.g. ocean), if any.
	 */
	private String view;
	/**
	 * Deduces if the RoomType is a smoking room or not.
	 */
	private boolean smokingAllowed;	// true for allowed, false for not allowed
	/**
	 * The price for this RoomType.
	 */
	private float price;
	/**
	 * The discount amount to offset the price for this room type, if any.
	 */
	private float discount;
	
	/**
	 * Creates a new RoomType without any meaningful data.
	 */
	public RoomType() {
		roomType = "";
		wifiEnabled = false;
		view = "";
		smokingAllowed = false;
		price = 0;
		discount = 0;
	}

	/**
	 * Creates a new RoomType with a given room type name, WiFi availability, view description, smoking allowance and its price
	 * @param roomType			This RoomType's name (e.g. Single, Double, etc)
	 * @param wifiEnabled		This RoomType's WiFi availability
	 * @param view				This RoomType's view
	 * @param smokingAllowed	This RoomType's smoking allowance
	 * @param price				This RoomType's price
	 */
	public RoomType(String roomType, boolean wifiEnabled, String view, boolean smokingAllowed, float price) {
		this.roomType = roomType;
		this.wifiEnabled = wifiEnabled;
		this.view = view;
		this.smokingAllowed = smokingAllowed;
		this.price = price;
		this.discount = 0;
	}

	/**
	 * Retrieves the name of this RoomType
	 * @return	This RoomType's name
	 */
	public String getRoomType() {
		return roomType;
	}

	/**
	 * Changes the name of this RoomType
	 * @param roomType	New name for this RoomType
	 */
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	/**
	 * Checks if this RoomType offers WiFi connectivity
	 * @return	This RoomType's connectivity status
	 */
	public boolean isWifiEnabled() {
		return wifiEnabled;
	}

	/**
	 * Changes the availability of a WiFi connection for this RoomType
	 * @param wifiEnabled	New WiFi connection status for this RoomType
	 */
	public void setWifiEnabled(boolean wifiEnabled) {
		this.wifiEnabled = wifiEnabled;
	}

	/**
	 * Retrieves the view description of this RoomType
	 * @return	This RoomType's view description
	 */
	public String getView() {
		return view;
	}

	/**
	 * Changes the view description of this RoomType
	 * @param view	New view description for this RoomType
	 */
	public void setView(String view) {
		this.view = view;
	}

	/**
	 * Checks if smoking is allowed in this RoomType
	 * @return	This RoomType's smoking allowance status
	 */
	public boolean isSmokingAllowed() {
		return smokingAllowed;
	}

	/**
	 * Changes the allowance of smoking inside this RoomType
	 * @param smokingAllowed	New smoking allowance status for this RoomType
	 */
	public void setSmokingAllowed(boolean smokingAllowed) {
		this.smokingAllowed = smokingAllowed;
	}

	/**
	 * Retrieves the price of this RoomType
	 * @return	This RoomType's price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * Changes the price of this RoomType
	 * @param price	New price for this RoomType
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * Retrieves the discount amount of this RoomType
	 * @return	This RoomType's discount amount
	 */
	public float getDiscount() {
		return discount;
	}

	/**
	 * Sets the discount amount of this RoomType
	 * @param discount	This RoomType's discount amount
	 */
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	
	/**
	 * Retrieves this RoomType's formatted data regarding its title, WiFi connectivity, view description, smoking allowance and its price
	 */
	public String toString() {
		return "Room Type: " + roomType + "\n\tWiFi Enabled: " + (isWifiEnabled() ? "Yes" : "No") + "\n\t" + "View: " + view + "\n\tSmoking Room: " + (isSmokingAllowed() ? "Yes" : "No") + "\n\tRoom Price: " + price;
	}
}
