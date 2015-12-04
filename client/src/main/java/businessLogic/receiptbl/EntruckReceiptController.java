package businessLogic.receiptbl;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import businessLogicService.receiptblservice.EntruckReceiptBLService;
import vo.receiptvo.EntruckReceiptVO;

public class EntruckReceiptController implements EntruckReceiptBLService{

	private EntruckReceiptBL receiptBL=new EntruckReceiptBL();
	
	public ArrayList<EntruckReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException {
		return receiptBL.getListByTime(fromTime, toTime);
	}

	public void createReceipt(EntruckReceiptVO item) throws RemoteException, SQLException {
		receiptBL.createReceipt(item);
	}

}
