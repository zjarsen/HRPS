package storage;


/**
 * Factory class for creating the correct StorageMethod using SystemConfig object
 * @author Zhu Jiahui
 *
 */
class StorageMethodFactory {
	/**
	 * 
	 * @param sysc - a SystemConfiguration object
	 * @return StorageMethod
	 */
	public static StorageMethod createStorageMethod(StorageConfig sysc){
		if (sysc.get("StorageMethod").equals("Serialize")){
			return new SerializeStorage();
		}
		else{
			// here return default storage method 
			return new SerializeStorage();
		}
	}
}
