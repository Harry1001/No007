package data.infodataimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import dataService.infodataservice.DriverDataService;
import database.DriverDBManager;
import myexceptions.InfoBLException;
import po.infopo.DriverPO;

public class DriverDataImpl extends UnicastRemoteObject implements DriverDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DriverDBManager driverDBManager;
	
	public DriverDataImpl() throws RemoteException {
		super();
		this.driverDBManager = new DriverDBManager();
	}

	public ArrayList<DriverPO> getList() throws RemoteException, SQLException {
		ArrayList<DriverPO> driverPOs = driverDBManager.getAll();
		return driverPOs;
	}

	public void addItem(DriverPO item) throws RemoteException, InfoBLException, SQLException {
		driverDBManager.add(item);
	}

	public void deleteItem(String id) throws RemoteException, SQLException {
		driverDBManager.delete(id);
		
	}

	public void update(String id, DriverPO item) throws RemoteException, InfoBLException, SQLException {
		driverDBManager.delete(id);
		driverDBManager.add(item);	
	}
}
