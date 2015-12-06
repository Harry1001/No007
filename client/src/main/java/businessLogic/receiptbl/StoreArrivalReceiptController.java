package businessLogic.receiptbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import businessLogicService.receiptblservice.StoreArrivalReceiptBLService;
import vo.receiptvo.StoreArrivalReceiptVO;

public class StoreArrivalReceiptController implements StoreArrivalReceiptBLService{

	private StoreArrivalReceiptBL receiptBL;
	
	public StoreArrivalReceiptController() throws MalformedURLException, RemoteException, NotBoundException{
		receiptBL=new StoreArrivalReceiptBL();
	}
	
	public ArrayList<StoreArrivalReceiptVO> getListByTime(Date fromTime, Date toTime)throws RemoteException, SQLException {		
		return receiptBL.getListByTime(fromTime, toTime);
	}

	public void createReceipt(StoreArrivalReceiptVO item) throws RemoteException, SQLException {
		receiptBL.createReceipt(item);		
	}

}
