package businessLogic.receiptbl;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import businessLogicService.receiptblservice.PayReceiptBLService;
import vo.receiptvo.PayReceiptVO;

public class PayReceiptController implements PayReceiptBLService{

	private PayReceiptBL receiptBL=new PayReceiptBL();
	
	public ArrayList<PayReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException{	
		return receiptBL.getListByTime(fromTime, toTime);
	}
	
	public void createReceipt(PayReceiptVO item) throws RemoteException, SQLException{
		receiptBL.createReceipt(item);
	}
	
}
