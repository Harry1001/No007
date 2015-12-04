package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import po.strategypo.DistancePO;

public class DistanceDBManager {
	public void serialize(DistancePO po) throws FileNotFoundException, IOException{
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("my.out"));
		 
		oos.writeObject(po);
		oos.flush();
		oos.close();
	}
	public DistancePO read() throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream("my.out"));
		DistancePO po=(DistancePO)ois.readObject();
		ois.close();
		return po;
	}
}
