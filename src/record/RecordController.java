package record;

import storage.StorageManager;

import java.util.ArrayList;

/**
 * Created by zjarsen on 8/4/16.
 */
public class RecordController {
    private static final String STORAGE_KEY = "Record";
    private StorageManager storageManager;
    private static RecordController singleInstance;
    private ArrayList<Record> records;

    private RecordController(){
        records=new ArrayList<Record>();
        storageManager=StorageManager.getInstance();
        loadEntries();
    }

    public static RecordController getInstance(){
        if (singleInstance==null){
            singleInstance=new RecordController();
        }
        return singleInstance;
    }

    private void loadEntries(){
        ArrayList<Record> inputEntries = (ArrayList<Record>)storageManager.read(STORAGE_KEY);
        if(inputEntries != null)
            records = inputEntries;
        else
            System.out.println("No data loaded for record info!");
    }


    public boolean saveChanges(){
        if (storageManager.store(STORAGE_KEY, records)){    //check for binary file name
            System.out.println("Order Info all saved!");
            return true;
        }else
        {
            System.out.println("Saving failed");
            return false;
        }
    }

    public ArrayList<Record> getRecords() {
        return records;
    }
}
