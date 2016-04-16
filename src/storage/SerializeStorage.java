package storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * A concrete storage method, using java's built-in read/write object methods
 * @author Zhu Jiahui
 *
 */
class SerializeStorage implements StorageMethod {
	
	public List readList(String fileName) {

		List pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(fileName);
			in = new ObjectInputStream(fis);
			pDetails = (ArrayList) in.readObject();
			in.close();
		} catch (IOException ex) {
			//System.out.println("Data file doesn't exist!");
			return null;
		} catch (ClassNotFoundException ex) {
			//System.out.println("Data file is not valid!");
			return null;
		}
		catch(Exception e){
			//System.out.println("Data file doesn't exist!");
			return null;
		}
		return pDetails;
	}

	public boolean writeList(String fileName, List list) {
		
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(fileName);
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
			return true;
		} catch (IOException ex) {
			//ex.printStackTrace();
			return false;
		}
	}
}
