package dataService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.recordpo.RecordPO;

public interface RecordDataService {

	/*
	 * 读系统日志
	 */
	public ArrayList<RecordPO> getRecord() throws  RemoteException;
	
	/*
	 * 写系统日志
	 */
	public void record(RecordPO po) throws RemoteException;
	
}
