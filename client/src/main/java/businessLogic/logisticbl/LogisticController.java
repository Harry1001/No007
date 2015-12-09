package businessLogic.logisticbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import businessLogicService.logisticblservice.LogisticBLService;
import vo.logisticvo.LogisticVO;
import vo.receiptvo.ReceiptVO;

public class LogisticController implements LogisticBLService{
	
	LogisticBL logisticbl;
	
	public LogisticController() throws MalformedURLException, RemoteException, NotBoundException {
		this.logisticbl = new LogisticBL();
	}
	
	public ArrayList<LogisticVO> getLogistic(String orderID) throws RemoteException, SQLException {	
		return logisticbl.getLogistic(orderID);
	}

	public void update(ReceiptVO vo) throws RemoteException, SQLException {
		logisticbl.update(vo);		
	}

}
