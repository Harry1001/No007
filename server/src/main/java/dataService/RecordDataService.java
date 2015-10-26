package dataService;

import java.rmi.RemoteException;

import po.RecordPO;

public interface RecordDataService {

	/*
	 * 读系统日志
	 */
	public RecordPO getRecord() throws  RemoteException;
	
	/*
	 * 写系统日志
	 */
	public void record(RecordPO po) throws RemoteException;
	
}
