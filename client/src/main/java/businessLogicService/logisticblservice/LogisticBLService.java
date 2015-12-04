package businessLogicService.logisticblservice;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.logisticvo.LogisticVO;
import vo.receiptvo.ReceiptVO;

public interface LogisticBLService {

	public ArrayList<LogisticVO> getLogistic(String orderID) throws RemoteException, SQLException;
	public void update(ReceiptVO vo) throws RemoteException, SQLException;
	
}
