package roominfo;

import java.util.ArrayList;

import storage.StorageManager;

public class RoomController {
	private static final String STORAGE_KEY = "Room";
	private StorageManager storageManager;
	private ArrayList<Room> rooms;
	private static RoomController singleInstance;
	
	private RoomController() {
		rooms = new ArrayList<Room>();
        storageManager = StorageManager.getInstance();
        loadEntries();
	}
	
	public static RoomController getInstance(){
		if (singleInstance == null)
			singleInstance = new RoomController();
		return singleInstance;
	}
	
	public void loadEntries() {
		 ArrayList<Room> inputEntries = (ArrayList<Room>)storageManager.read(STORAGE_KEY);
       if(inputEntries != null)
    	   rooms = inputEntries;
       else
           System.out.println("No data loaded for room info!");
	}
	
	public boolean saveChanges() {
		if (storageManager.store(STORAGE_KEY, rooms))
            return true;
        else
            return false;
	}
	
	public ArrayList<Room> getRooms() {
		return rooms;
	}
}
