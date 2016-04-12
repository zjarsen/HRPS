package roominfo;

import storage.StorageManager;
import java.util.ArrayList;

/**
 * Controller class for managing RoomType information inside the storage
 * @author	SHAFIQAH BINTE ROSTAM
 * @since	2016-04-01
 */
public class RoomTypeController {
	/**
	 * The storage key for this RoomTypeController
	 */
	private static final String STORAGE_KEY = "RoomType";
	/**
	 * The storage manager for this RoomController to use
	 */
	private StorageManager storageManager;
	/**
	 * The list of RoomType objects for this RoomController to use
	 */
	private ArrayList<RoomType> roomTypes;
	/**
	 * The single instance of this RoomTypeController
	 */
	private static RoomTypeController singleInstance;
	
	/**
	 * Default constructor for RoomTypeController. Accessible only through the getInstance() method.
	 */
	private RoomTypeController() {
		roomTypes = new ArrayList<RoomType>();
        storageManager = StorageManager.getInstance();
        loadEntries();
	}

	/**
	 * Instantiates the RoomController class once
	 * @return	A single instance of this RoomController
	 */
	public static RoomTypeController getInstance(){
		if (singleInstance == null)
			singleInstance = new RoomTypeController();
		return singleInstance;
	}
	
	/**
	 * Stores the RoomType objects found within the storage (if any) into the list of RoomType objects, "roomTypes"
	 */
	public void loadEntries() {
		@SuppressWarnings("unchecked")
		ArrayList<RoomType> inputEntries = (ArrayList<RoomType>)storageManager.read(STORAGE_KEY);
		if(inputEntries != null)
			roomTypes = inputEntries;
		else
			System.out.println("No data loaded for room info!");
	}
	
	/**
	 * Saves any changes made to the list of RoomType objects to storage
	 * @return	The outcome of the save, determining whether it saved successfully or failed
	 */
	public boolean saveChanges() {
		if (storageManager.store(STORAGE_KEY, roomTypes))
            return true;
        else
            return false;
	}
	
	/**
	 * Retrieves the list of RoomType objects
	 * @return	The list of RoomType objects
	 */
	public ArrayList<RoomType> getRoomTypes() {
		return roomTypes;
	}
}
