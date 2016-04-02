package storage;



import java.util.HashMap;
import java.util.Map;

/**
 * Class for storing some constant parameters
 * it's actually a wrapper around the map data structure
 * This is put as a singleton method since it stores constant settings
 * @author Zhang Jie
 *
 */
public class StorageConfig {
	private static StorageConfig singleInstance;
	// Store <name, value> pairs of system configuration
	private Map<String, String> configs = new HashMap<String, String>();

	public String get(String name) {
		return configs.get(name);
	}
	
	private StorageConfig(){
		configs.put("StorageMethod", "Serialize");
		configs.put("Guest", "guest.dat");
		configs.put("RoomType", "roomType.dat");
	}
	
	public static StorageConfig getInstance(){
		if (singleInstance==null){
			singleInstance=new StorageConfig();
		}
		return singleInstance;
	}
}