package orderinfo;

import java.util.ArrayList;

import studentinfo.Guest;
import studentinfo.GuestController;
import storage.StorageManager;

public class OrderItemController {
	private static final String STORAGE_KEY = "OrderItem";
	private StorageManager storageManager;
	private static OrderItemController singleInstance;
	private ArrayList<OrderItem> orderItem;
	
	public static OrderItemController getInstance(){
//      If it's the starting time running the program, then needs to instantiate the singleton, but only once in the
//whole runtime of the program
      if (singleInstance==null){
          singleInstance=new OrderItemController();
      }
//      If it's not the starting time running the program, then the singleton is already instantiated so that it can
//be returned directly to the manager class
      return singleInstance;
	}
	
	private void loadEntries(){
        ArrayList<OrderItem> inputEntries = (ArrayList<OrderItem>)storageManager.read(STORAGE_KEY);  // check for file name
//        check if the database is empty. if it's not empty then store all entries into the "guest" arrayList attribute
// of the singleton object for manager's further use
        if(inputEntries != null)
            orderItem = inputEntries;
        else
            System.out.println("No data loaded for guest info!");
    }
	
	public boolean saveChanges(){
        if (storageManager.store(STORAGE_KEY, orderItem)){    //check for binary file name
            System.out.println("OrderItem Info all saved!");
            return true;
        }else
        {
            System.out.println("Saving failed");
            return false;
        }
    }
	
	

}
