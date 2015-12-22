package data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import dataService.LogisticDataService;
import database.LogisticDBManager;
import po.logisticpo.LogisticPO;

public class LogisticDataImpl extends UnicastRemoteObject implements LogisticDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LogisticDBManager logisticDBManager;
	
	public LogisticDataImpl() throws RemoteException {
		super();
		logisticDBManager=new LogisticDBManager();
	}

	public void update(LogisticPO po) throws RemoteException, SQLException {
		logisticDBManager.update(po);
	}

	public ArrayList<LogisticPO> read(String num) throws RemoteException, SQLException {
		return logisticDBManager.read(num);
	}

	public void remove(String num) throws RemoteException, SQLException {
		logisticDBManager.removeLogistic(num);
	}

}
