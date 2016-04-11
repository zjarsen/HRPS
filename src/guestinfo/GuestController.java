package guestinfo;

import storage.StorageManager;

import java.util.ArrayList;

/**
 * Created by zjarsen on 30/3/16.
 */
public class GuestController {
    /**
     * set the key name so that the storage package knows which class to serialise or deserialise
     */
    private static final String STORAGE_KEY = "Guest";

    /**
     * set a singleton for this class. So the manager classes of Guest could use the following approach to get the
     * singleton of this class:
     * GuestController guestController = GuestController.getInstance();
     */
    private static GuestController singleInstance;

    /**
     * Because the 'guestController' object is from 'GuestController' class, then it stores the "ArrayList<Guest> guests",
     * so in the manager class of Guest, to get all the Guests' information, use the following approach to get an
     * ArrayList of all the information of the guests stored in the Manager:
     * ArrayList<Guest> guestList = guestController.getGuests();
     *
     * And then do operations to the guestList:
     * guestList.add(newGuest);
     *
     * Finally save the list back to the storage:
     * guestList.add(newGuest);
     */
    private ArrayList<Guest> guests;

//    Use the storage manager. the storageManager will be used during the instantiation of a GuestController class:
//    storageManager=StorageManager.getInstance();
    private StorageManager storageManager;





    /**
     * when a manager class needs to use the controller class, it will use this static method to get the singleton out
     * INSTEAD OF doing "GuestController guestController = new GuestController()". The method below shows the process:
     * @return the singleton of GuestController class
     */
    public static GuestController getInstance(){
//        If it's the starting time running the program, then needs to instantiate the singleton, but only once in the
// whole runtime of the program
        if (singleInstance==null){
            singleInstance=new GuestController();
        }
//        If it's not the starting time running the program, then the singleton is already instantiated so that it can
// be returned directly to the manager class
        return singleInstance;
    }


    /**
     * This private method is the instantiation(constructor) of this controller class. it cannot be used by other classes
     * but can only be used in the "getInstance" method (Shown above) when the program starts. During the instantiation,
     * the storageManager is used to load all entries of the corresponding class from the file database
     */
    private GuestController(){
        guests=new ArrayList<Guest>();
        storageManager=StorageManager.getInstance();
        //load all entries of the corresponding class from the file database
        loadEntries();
    }

    /**
     * A private method, to load all entries of the corresponding class from the file database
     */
    private void loadEntries(){
        ArrayList<Guest> inputEntries = (ArrayList<Guest>)storageManager.read(STORAGE_KEY);  // check for file name
//        check if the database is empty. if it's not empty then store all entries into the "guest" arrayList attribute
// of the singleton object for manager's further use
        if(inputEntries != null)
            guests = inputEntries;
        else
            System.out.println("No data loaded for guest info!");
    }


    /**
     * After storing all guest entries in the singleton's "guest" arrayList attribute, use this public method to pass the
     * guests' entries to the manager class
     * @return all guest entries
     */
    public ArrayList<Guest> getGuests() {
        return guests;
    }




    /**
     * This public method is used for the manager class to save the modified "guests" arrayList back to the file database
     * if it succeeds, then return true, else return false
     * @return result
     */
    public boolean saveChanges(){
        if (storageManager.store(STORAGE_KEY, guests)){    //check for binary file name
            System.out.println("Student Info all saved!");
            return true;
        }else
        {
            System.out.println("Saving failed");
            return false;
        }
    }
}
