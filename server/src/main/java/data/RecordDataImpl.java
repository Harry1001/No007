package data;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import dataService.RecordDataService;
import database.RecordDBManager;
import po.recordpo.RecordPO;

public class RecordDataImpl implements RecordDataService{

	public ArrayList<RecordPO> getRecord() throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		RecordDBManager rds=new RecordDBManager();
		ArrayList<RecordPO> apo=rds.getAll();
		return apo;
	}

	public void record(RecordPO po) throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		RecordDBManager rds=new RecordDBManager();
		rds.addRecord(po);
	}

}
