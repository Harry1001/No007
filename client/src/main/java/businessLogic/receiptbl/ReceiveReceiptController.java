package businessLogic.receiptbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import businessLogicService.receiptblservice.ReceiveReceiptBLService;
import vo.receiptvo.ReceiveReceiptVO;

public class ReceiveReceiptController implements ReceiveReceiptBLService{

	private ReceiveReceiptBL receiptBL;
	
	public ReceiveReceiptController() throws MalformedURLException, RemoteException, NotBoundException{
		receiptBL=new ReceiveReceiptBL();
	}
	
	public ArrayList<ReceiveReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException {
		return receiptBL.getListByTime(fromTime, toTime);
	}

	public void createReceipt(ReceiveReceiptVO item) throws RemoteException, SQLException {
		receiptBL.createReceipt(item);		
	}

}
