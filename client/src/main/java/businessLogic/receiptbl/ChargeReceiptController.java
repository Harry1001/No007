package businessLogic.receiptbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import businessLogicService.receiptblservice.ChargeReceiptBLService;
import vo.receiptvo.ChargeReceiptVO;

public class ChargeReceiptController implements ChargeReceiptBLService{

	private ChargeReceiptBL receiptBL;
	
	public ChargeReceiptController() throws MalformedURLException, RemoteException, NotBoundException{
		receiptBL=new ChargeReceiptBL();
	}
	
	public ArrayList<ChargeReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException {
		return receiptBL.getListByTime(fromTime, toTime);
	}

	public void createReceipt(ChargeReceiptVO item) throws RemoteException, SQLException {
		receiptBL.createReceipt(item);		
	}

}
