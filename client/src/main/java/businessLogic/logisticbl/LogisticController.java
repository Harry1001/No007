package businessLogic.logisticbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businessLogicService.logisticblservice.LogisticBLService;
import vo.logisticvo.LogisticVO;
import vo.receiptvo.ReceiptVO;

public class LogisticController implements LogisticBLService{
	
	LogisticBL logisticbl=new LogisticBL();
	
	public ArrayList<LogisticVO> getLogistic(String orderID) throws RemoteException {	
		return logisticbl.getLogistic(orderID);
	}

	public void update(ReceiptVO vo) throws RemoteException {
		logisticbl.update(vo);		
	}

}
