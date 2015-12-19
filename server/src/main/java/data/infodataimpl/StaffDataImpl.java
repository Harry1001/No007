package data.infodataimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import dataService.infodataservice.StaffDataService;
import database.StaffDBManager;
import myexceptions.InfoBLException;
import po.infopo.StaffPO;

public class StaffDataImpl extends UnicastRemoteObject implements StaffDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StaffDBManager staffDBManager;
	
	public StaffDataImpl() throws RemoteException {
		super();
		this.staffDBManager = new StaffDBManager();
	}

	public ArrayList<StaffPO> getList() throws RemoteException, SQLException {
		ArrayList<StaffPO> staffPOs = staffDBManager.getAll();
		return staffPOs;
	}

	public void addItem(StaffPO item) throws RemoteException, InfoBLException, SQLException {
		if (!isExist(item.getStaffID())){
			staffDBManager.add(item);
		}
		else {
			throw new InfoBLException("该工号已存在!");
		}
	}

	public void deleteItem(String id) throws RemoteException, SQLException {
		staffDBManager.delete(id);
	}

	public void update(String id, StaffPO item) throws RemoteException, InfoBLException, SQLException {
		if(!isExist(item.getStaffID())) {
			deleteItem(id);
			addItem(item);
		}
		else throw new InfoBLException("该工号已存在!");
	}
	
	private boolean isExist(String id) throws SQLException {
		System.out.println("staff exited");
		StaffPO staffPO = staffDBManager.get(id);
		if(staffPO == null) return false;
		else return true;
	}
}
