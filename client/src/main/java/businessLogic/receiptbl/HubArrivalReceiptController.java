package businessLogic.receiptbl;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import businessLogicService.receiptblservice.HubArrivalReceiptBLService;
import vo.receiptvo.HubArrivalReceiptVO;

public class HubArrivalReceiptController implements HubArrivalReceiptBLService{

	private HubArrivalReceiptBL receiptBL=new HubArrivalReceiptBL();
	
	public ArrayList<HubArrivalReceiptVO> getListByTime(Date fromTime, Date toTime)throws RemoteException, SQLException {
		return receiptBL.getListByTime(fromTime, toTime);
	}

	public void createReceipt(HubArrivalReceiptVO item) throws RemoteException, SQLException {
		receiptBL.createReceipt(item);		
	}

}
