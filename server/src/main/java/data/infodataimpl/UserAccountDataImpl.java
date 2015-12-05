package data.infodataimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import dataService.infodataservice.UserAccountDataService;
import database.UserAccountDBManager;
import myexceptions.InfoBLException;
import po.infopo.UserAccountPO;
import typeDefinition.Job;

public class UserAccountDataImpl extends UnicastRemoteObject implements UserAccountDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserAccountDBManager userAccountDBManager;
	
	public UserAccountDataImpl() throws RemoteException {
		super();
		this.userAccountDBManager = new UserAccountDBManager();
	}

	public ArrayList<UserAccountPO> getList() throws RemoteException, SQLException {
		ArrayList<UserAccountPO> userAccountPOs = userAccountDBManager.getall();
		return userAccountPOs;
	}

	public void addItem(UserAccountPO item) throws RemoteException, InfoBLException, SQLException {
		userAccountDBManager.add(item);		
	}

	public void deleteItem(String id) throws RemoteException, SQLException {
		userAccountDBManager.delete(id);		
	}

	public void update(String id, UserAccountPO item) throws RemoteException, InfoBLException, SQLException {
		deleteItem(id);
		addItem(item);
		
	}


	public Job verify(String id, String password) throws RemoteException {
		Job job = Job.NOTFOUND;
		try {
			UserAccountPO po = userAccountDBManager.get(id);
			job = po.getPosition();
		} catch (SQLException e) {//没有找到po
		}
		return job;		
	}
}
