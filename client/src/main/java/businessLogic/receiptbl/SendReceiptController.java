package businessLogic.receiptbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import businessLogicService.receiptblservice.SendReceiptBLService;
import po.receiptpo.SendReceiptPO;
import vo.receiptvo.SendReceiptVO;

public class SendReceiptController implements SendReceiptBLService{

    private SendReceiptBL receiptBL;

    public SendReceiptController() throws MalformedURLException, RemoteException, NotBoundException{
    	receiptBL=new SendReceiptBL();
    }
    
	public ArrayList<SendReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException {
		return receiptBL.getListByTime(fromTime, toTime);
	}

	public void createReceipt(SendReceiptVO item) throws RemoteException, SQLException {
		receiptBL.createReceipt(item);	
	}

	public SendReceiptPO getSendReceipt(String orderID) throws RemoteException, SQLException {
		return receiptBL.getSendReceipt(orderID);
	}

}
