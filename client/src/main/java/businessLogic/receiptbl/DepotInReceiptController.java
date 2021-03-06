package businessLogic.receiptbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import businessLogicService.receiptblservice.DepotInReceiptBLService;
import vo.receiptvo.DepotInReceiptVO;

public class DepotInReceiptController implements DepotInReceiptBLService{

	private DepotInReceiptBL receiptBL;
	
	public DepotInReceiptController() throws MalformedURLException, RemoteException, NotBoundException{
		receiptBL=new DepotInReceiptBL();
	}
	
	public ArrayList<DepotInReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException {
		return receiptBL.getListByTime(fromTime, toTime);
	}

	public void createReceipt(DepotInReceiptVO item) throws RemoteException, SQLException {
		receiptBL.createReceipt(item);		
	}

}
