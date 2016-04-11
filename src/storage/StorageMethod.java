package storage;

import java.util.List;

/**
 * An interface for strategy pattern
 * used to read/write list object to and from files
 * @author Zhang Jie
 *
 */
interface StorageMethod {
	/**
	 * 
	 * @param fileName - the filename to read from
	 * @return a list object
	 */
	public List readList(String fileName);
	/**
	 * 
	 * @param fileName - the filename to write to
	 * @param list - the list object to write into file
	 * @return true if write successful, false otherwise
	 */
	public boolean writeList(String fileName, List list);
}
