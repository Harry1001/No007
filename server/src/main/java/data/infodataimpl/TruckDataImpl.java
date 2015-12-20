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
		if(!isExist(item.getTruckID()))
			truckDBManager.add(item);		
		else throw new InfoBLException("该车辆编号已存在");
	}

	public void deleteItem(String id) throws RemoteException, SQLException {
		truckDBManager.delete(id);
	}

	public void update(String id, TruckPO item) throws RemoteException, InfoBLException, SQLException {
		if(!isExist(item.getTruckID()) || id.equals(item.getTruckID())){
			deleteItem(id);
			addItem(item);			
		}
		else throw new InfoBLException("该车辆编号已存在");
	}
	
	private boolean isExist(String id) throws SQLException {
		TruckPO po = truckDBManager.get(id);
		if(po==null) return false;
		else return true;
	}
}
