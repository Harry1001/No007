package businessLogicService.recordblservice;

import java.rmi.RemoteException;

import vo.recordvo.RecordVO;

public interface RecordBLService {

	public RecordVO lookup() throws RemoteException;
	
	public void add(RecordVO vo) throws RemoteException;
	
	
}
