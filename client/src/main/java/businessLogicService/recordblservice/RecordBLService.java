package businessLogicService.recordblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.recordvo.RecordVO;

public interface RecordBLService {

	public ArrayList<RecordVO> lookup() throws RemoteException;
	
	public void add(RecordVO vo) throws RemoteException;
	
	
}
