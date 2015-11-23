package data;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataService.LogisticDataService;
import database.LogisticDBManager;
import po.logisticpo.LogisticPO;

public class LogisticDataImpl implements LogisticDataService{

	public void update(LogisticPO po) throws RemoteException {
		LogisticDBManager logisticDBManager=new LogisticDBManager();
		logisticDBManager.update(po);
	}

	public ArrayList<LogisticPO> read(String num) throws RemoteException {
		LogisticDBManager logisticDBManager=new LogisticDBManager();
		logisticDBManager.read(num);
		return null;
	}

	public void remove(String num) throws RemoteException {
		LogisticDBManager logisticDBManager=new LogisticDBManager();
		logisticDBManager.removeLogistic(num);
	}

}
