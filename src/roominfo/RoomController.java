package roominfo;

import java.util.ArrayList;
import storage.StorageManager;

/**
 * Controller class for managing Room information inside the storage
 * @author	SHAFIQAH BINTE ROSTAM
 * @since	2016-04-01
 */
public class RoomController {
	/**
	 * The storage key for this RoomController
	 */
	private static final String STORAGE_KEY = "Room";
	/**
	 * The storage manager for this RoomController to use
	 */
	private StorageManager storageManager;
	/**
	 * The list of Room objects to use
	 */
	private ArrayList<Room> rooms;
	/**
	 * The single instance of this RoomController
	 */
	private static RoomController singleInstance;
	
	/**
	 * Default constructor for RoomController. Accessible only through the getInstance() method.
	 */
	private RoomController() {
		rooms = new ArrayList<Room>();
        storageManager = StorageManager.getInstance();
        loadEntries();
	}
	
	/**
	 * Instantiates the RoomController class once
	 * @return	A single instance of this RoomController
	 */
	public static RoomController getInstance(){
		if (singleInstance == null)
			singleInstance = new RoomController();
		return singleInstance;
	}
	
	/**
	 * Stores the Room objects found within the storage (if any) into the list of Room objects, "rooms"
	 */
	public void loadEntries() {
		@SuppressWarnings("unchecked")
		ArrayList<Room> inputEntries = (ArrayList<Room>)storageManager.read(STORAGE_KEY);
		if(inputEntries != null)
			rooms = inputEntries;
		else
			System.out.println("No data loaded for room info!");
	}
	
	/**
	 * Saves any changes made to the list of Room objects to storage
	 * @return	The outcome of the save, determining whether it saved successfully or failed
	 */
	public boolean saveChanges() {
		if (storageManager.store(STORAGE_KEY, rooms))
			return true;
		else
			return false;
	}
	
	/**
	 * Retrieves the list of Room objects
	 * @return	The list of Room objects
	 */
	public ArrayList<Room> getRooms() {
		return rooms;
	}
}
