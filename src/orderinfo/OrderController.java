package orderinfo;

import java.util.ArrayList;

import roominfo.Room;
import storage.StorageManager;

public class OrderController {
	
	private static final String STORAGE_KEY = "Order";
	private StorageManager storageManager;
	private static OrderController singleInstance;
	private ArrayList<Order> order;
	
	public OrderController(){
		order = new ArrayList<Order>();
		storageManager = StorageManager.getInstance();
		loadEntries();
	}

	public static OrderController getInstance(){
      if (singleInstance==null){
          singleInstance=new OrderController();
      }
      return singleInstance;
	}
	
	private void loadEntries(){
        ArrayList<Order> inputEntries = (ArrayList<Order>)storageManager.read(STORAGE_KEY);  
        if(inputEntries != null)
            order = inputEntries;
        else
            System.out.println("No data loaded for order info!");
    }
	
	/**
	 * this function is to...
	 * @return whether the change is saved successfully 
	 */
	public boolean saveChanges(){
        if (storageManager.store(STORAGE_KEY, order)){    //check for binary file name
            System.out.println("Order Info all saved!");
            return true;
        } else {
            System.out.println("Saving failed");
            return false;
        }
    }
	
	  public ArrayList<Order> getOrders() {
	        return order;
	    }
	 
}
