package data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import dataService.LogisticDataService;
import database.LogisticDBManager;
import po.logisticpo.LogisticPO;

public class LogisticDataImpl extends UnicastRemoteObject implements LogisticDataService{

	public LogisticDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private LogisticDBManager logisticDBManager=new LogisticDBManager();

	public void update(LogisticPO po) throws RemoteException, SQLException {
		logisticDBManager.update(po);
	}

	public ArrayList<LogisticPO> read(String num) throws RemoteException, SQLException {
		logisticDBManager.read(num);
		return null;
	}

	public void remove(String num) throws RemoteException, SQLException {
		logisticDBManager.removeLogistic(num);
	}

}
