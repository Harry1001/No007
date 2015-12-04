package businessLogic.receiptbl;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import businessLogicService.receiptblservice.SendReceiptBLService;
import vo.receiptvo.SendReceiptVO;

public class SendReceiptController implements SendReceiptBLService{

    private SendReceiptBL receiptBL=new SendReceiptBL();

	public ArrayList<SendReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException {
		return receiptBL.getListByTime(fromTime, toTime);
	}

	public void createReceipt(SendReceiptVO item) throws RemoteException, SQLException {
		receiptBL.createReceipt(item);	
	}

}
