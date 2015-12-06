package businessLogic.receiptbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import businessLogicService.receiptblservice.DespatchReceiptBLService;
import vo.receiptvo.DespatchReceiptVO;

public class DespatchReceiptController implements DespatchReceiptBLService{

	private DespatchReceiptBL receiptBL;
	
	public DespatchReceiptController() throws MalformedURLException, RemoteException, NotBoundException{
		receiptBL=new DespatchReceiptBL();
	}
	
	public ArrayList<DespatchReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException {
		return receiptBL.getListByTime(fromTime, toTime);
	}

	public void createReceipt(DespatchReceiptVO item) throws RemoteException, SQLException {
		receiptBL.createReceipt(item);
	}

}
