package data.infodataimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import dataService.infodataservice.TruckDataService;
import database.TruckDBManager;
import myexceptions.InfoBLException;
import po.infopo.TruckPO;

public class TruckDataImpl extends UnicastRemoteObject implements TruckDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TruckDBManager truckDBManager;

	public TruckDataImpl() throws RemoteException {
		super();
		this.truckDBManager = new TruckDBManager();
	}

	public ArrayList<TruckPO> getList() throws RemoteException, SQLException {
		ArrayList<TruckPO> truckPOs = truckDBManager.getAll();
		return truckPOs;
	}

	public void addItem(TruckPO item) throws RemoteException, InfoBLException, SQLException {
		truckDBManager.add(item);		
	}

	public void deleteItem(String id) throws RemoteException, SQLException {
		truckDBManager.delete(id);
	}

	public void update(String id, TruckPO item) throws RemoteException, InfoBLException, SQLException {
		deleteItem(id);
		addItem(item);
	}
}