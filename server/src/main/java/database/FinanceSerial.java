package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import po.financepo.FinancePO;

public class FinanceSerial {

	
	public void serialize(FinancePO po,int year) throws FileNotFoundException, IOException{
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(year+".out"));
		
		oos.writeObject(po);
		oos.flush();
		oos.close();
	}
	
	public FinancePO read(int year) throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(year+".out"));
		
		FinancePO po=(FinancePO)ois.readObject();
		ois.close();
		return po;
	}
	
}
