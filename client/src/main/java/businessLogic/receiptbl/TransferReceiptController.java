package businessLogic.receiptbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import businessLogicService.receiptblservice.TransferReceiptBLService;
import typeDefinition.ReceiptState;
import vo.receiptvo.TransferReceiptVO;

public class TransferReceiptController implements TransferReceiptBLService{

	private TransferReceiptBL receiptBL;
	
	public TransferReceiptController() throws MalformedURLException, RemoteException, NotBoundException{
		receiptBL=new TransferReceiptBL();
	}
	
	public ArrayList<TransferReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException {
		return receiptBL.getListByTime(fromTime, toTime);
	}

	public void createReceipt(TransferReceiptVO item) throws RemoteException, SQLException {
		receiptBL.createReceipt(item);		
	}

	public ArrayList<TransferReceiptVO> getListByState(ReceiptState state) throws RemoteException, SQLException {
		return receiptBL.getListByState(state);
	}
	
	public void updateState(String orderID, ReceiptState state) throws RemoteException, SQLException {
		receiptBL.updateState(orderID, state);
	}
}
