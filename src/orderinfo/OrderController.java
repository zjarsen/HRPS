package orderinfo;

import java.util.ArrayList;

import storage.StorageManager;

public class OrderController {
	
	private static final String STORAGE_KEY = "Order";
	private StorageManager storageManager;
	private static OrderController singleInstance;
	/*
	 * 
	 * problem class diagram
	 * 
	 */
	private ArrayList<Order> order;
	/*
	 * 
	 * problem class diagram            s
	 * 
	 */
	
	
	
	public static OrderController getInstance(){
//      If it's the starting time running the program, then needs to instantiate the singleton, but only once in the
//whole runtime of the program
      if (singleInstance==null){
          singleInstance=new OrderController();
      }
//      If it's not the starting time running the program, then the singleton is already instantiated so that it can
//be returned directly to the manager class
      return singleInstance;
	}
	
	private void loadEntries(){
        ArrayList<Order> inputEntries = (ArrayList<Order>)storageManager.read(STORAGE_KEY);  // check for file name
//        check if the database is empty. if it's not empty then store all entries into the "guest" arrayList attribute
// of the singleton object for manager's further use
        if(inputEntries != null)
            order = inputEntries;
        else
            System.out.println("No data loaded for order info!");
    }
	
	
	public boolean saveChanges(){
        if (storageManager.store(STORAGE_KEY, order)){    //check for binary file name
            System.out.println("Order Info all saved!");
            return true;
        }else
        {
            System.out.println("Saving failed");
            return false;
        }
    }
	
	  public ArrayList<Order> getOrders() {
	        return order;
	    }
}
