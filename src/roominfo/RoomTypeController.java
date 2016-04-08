package roominfo;

import storage.StorageManager;
import studentinfo.Guest;
import studentinfo.GuestController;

import java.util.ArrayList;

public class RoomTypeController {
	private static final String STORAGE_KEY = "RoomType";
	private StorageManager storageManager;
	private ArrayList<RoomType> roomTypes;
	private static RoomTypeController singleInstance;
	
	private RoomTypeController() {
		roomTypes = new ArrayList<RoomType>();
        storageManager = StorageManager.getInstance();
        loadEntries();
	}

	public static RoomTypeController getInstance(){
		if (singleInstance == null)
			singleInstance = new RoomTypeController();
		return singleInstance;
	}
	
	public void loadEntries() {
		 ArrayList<RoomType> inputEntries = (ArrayList<RoomType>)storageManager.read(STORAGE_KEY);
       if(inputEntries != null)
    	   roomTypes = inputEntries;
       else
           System.out.println("No data loaded for room info!");
	}
	
	public boolean saveChanges() {
		if (storageManager.store(STORAGE_KEY, roomTypes))
            return true;
        else
            return false;
	}
	
	public ArrayList<RoomType> getRoomTypes() {
		return roomTypes;
	}
}
