package businessLogicService.recordblservice;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.recordvo.RecordVO;

public interface RecordBLService {

	public ArrayList<RecordVO> lookup() throws RemoteException, SQLException;
	
	public void add(RecordVO vo) throws RemoteException, SQLException;
	
	
}
