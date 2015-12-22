package data.infodataimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import dataService.infodataservice.StaffDataService;
import database.StaffDBManager;
import myexceptions.InfoBLException;
import po.infopo.StaffPO;
import vo.infovo.StaffVO;

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
		if(!isExist(item.getStaffID()) || id.equals(item.getStaffID())) { //检查新的item中的工号是否存在，不存在则删除旧id的信息，再加入新的信息
			deleteItem(id);
			addItem(item);
		}
		else throw new InfoBLException("该工号已存在!");
	}

	public void addWorkFrequency(String staffID) throws SQLException, RemoteException, InfoBLException {
		StaffPO po=staffDBManager.get(staffID);
		if (po!=null){
			po.addWorkFrequency();
			deleteItem(po.getStaffID());
			addItem(po);
		}
	}

	public void refreshWorkFreqeuncy(String staffID) throws SQLException, RemoteException, InfoBLException {
		StaffPO po=staffDBManager.get(staffID);
		if (po!=null){
			po.setWorkFrequency(0);
			deleteItem(po.getStaffID());
			addItem(po);
		}
	}


	private boolean isExist(String id) throws SQLException {
		StaffPO staffPO = staffDBManager.get(id);
		if(staffPO == null) return false;
		else return true;
	}
}
