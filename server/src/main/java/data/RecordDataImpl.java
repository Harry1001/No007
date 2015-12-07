package data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import dataService.RecordDataService;
import database.RecordDBManager;
import po.recordpo.RecordPO;

public class RecordDataImpl extends UnicastRemoteObject implements RecordDataService{

	RecordDBManager rds;
	public RecordDataImpl() throws RemoteException {
		super();
		rds=new RecordDBManager();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArrayList<RecordPO> getRecord() throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		
		ArrayList<RecordPO> apo=rds.getAll();
		return apo;
	}

	public void record(RecordPO po) throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		
		rds.addRecord(po);
	}

}
