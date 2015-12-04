package businessLogic.receiptbl;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import businessLogicService.receiptblservice.DepotOutReceiptBLService;
import vo.receiptvo.DepotOutReceiptVO;

public class DepotOutReceiptController implements DepotOutReceiptBLService{

	private DepotOutReceiptBL receiptBL=new DepotOutReceiptBL();
	
	public ArrayList<DepotOutReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException {
		return receiptBL.getListByTime(fromTime, toTime);
	}

	public void createReceipt(DepotOutReceiptVO item) throws RemoteException, SQLException {
		receiptBL.createReceipt(item);		
	}

}
