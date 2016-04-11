package storage;


import java.util.List;
/**
 * This class offers methods to read/retrieve data from persistent storage
 * Note: this is a singleton class
 * @author Zhang Jie
 *
 */
public class StorageManager {
	private static StorageManager singleInstance;
	
	private StorageMethod storageMethod;
	private StorageConfig config;
	
	private StorageManager(){
		config=StorageConfig.getInstance();
		storageMethod=StorageMethodFactory.createStorageMethod(config);
	}
	
	public static StorageManager getInstance(){
		if (singleInstance==null){
			singleInstance=new StorageManager();
		}
		return singleInstance;
	}
	
	public StorageManager(StorageConfig sysc){
		config=sysc;
		storageMethod=StorageMethodFactory.createStorageMethod(config);
	}
	
	public boolean store(String fileNameKey,List ls){
		String fileName=config.get(fileNameKey);
		return storageMethod.writeList(fileName, ls);
	}
	
	public List read(String fileNameKey){
		String fileName=config.get(fileNameKey);
		return storageMethod.readList(fileName);
	}
}
