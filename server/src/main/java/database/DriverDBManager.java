package database;

import java.sql.Driver;

import po.infopo.DriverPO;

public class DriverDBManager extends DBManager{
	
	public void add(DriverPO po){
		String iDString = po.getDriverID();
		String name = po.getName();
		
	}

}
