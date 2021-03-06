package businessLogic.receiptbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import businessLogicService.receiptblservice.PayReceiptBLService;
import typeDefinition.ReceiptState;
import vo.receiptvo.PayReceiptVO;

public class PayReceiptController implements PayReceiptBLService{

	private PayReceiptBL receiptBL;
	
	public PayReceiptController() throws MalformedURLException, RemoteException, NotBoundException{
		receiptBL=new PayReceiptBL();
	}
	
	public ArrayList<PayReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException{	
		return receiptBL.getListByTime(fromTime, toTime);
	}
	
	public void createReceipt(PayReceiptVO item) throws RemoteException, SQLException{
		receiptBL.createReceipt(item);
	}

	public ArrayList<PayReceiptVO> getListByState(ReceiptState state) throws RemoteException, SQLException {
		return receiptBL.getListByState(state);
	}

	public void updateState(String orderID, ReceiptState state) throws RemoteException, SQLException {
		receiptBL.updateState(orderID, state);
	}
	
}
