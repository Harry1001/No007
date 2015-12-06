package businessLogic.receiptbl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.ChargeReceiptDataService;
import dataService._RMI;
import po.receiptpo.ChargeReceiptPO;
import vo.receiptvo.ChargeReceiptVO;

public class ChargeReceiptBL {
	
	private ChargeReceiptDataService chargeReceiptData;
	
	public ChargeReceiptBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url="rmi://"+_RMI.getIP()+"/central_chargeReceipt";
		chargeReceiptData=(ChargeReceiptDataService)Naming.lookup(url);
	}
	
	public ArrayList<ChargeReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException {
		ArrayList<ChargeReceiptVO> chargeReceiptVOs=new ArrayList<ChargeReceiptVO>();
		ArrayList<ChargeReceiptPO> chargeReceiptPOs=chargeReceiptData.getList(fromTime, toTime);
		for(ChargeReceiptPO po:chargeReceiptPOs){
			chargeReceiptVOs.add(new ChargeReceiptVO(po));
		}
		return chargeReceiptVOs;
	}

	public void createReceipt(ChargeReceiptVO item) throws RemoteException, SQLException {
		chargeReceiptData.addItem(new ChargeReceiptPO(item));
	}

}
