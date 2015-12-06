package businessLogic.receiptbl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.SendReceiptDataService;
import dataService._RMI;
import po.receiptpo.SendReceiptPO;
import vo.receiptvo.SendReceiptVO;

public class SendReceiptBL {	

	private SendReceiptDataService sendReceiptData;
	
	public SendReceiptBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url="rmi://"+_RMI.getIP()+"/central_send";
		sendReceiptData=(SendReceiptDataService)Naming.lookup(url);
	}
	
	public ArrayList<SendReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException {
		//TODO 如果起始时间小于终止时间，报错
		ArrayList<SendReceiptVO> sendReceiptVOs=new ArrayList<SendReceiptVO>();
		ArrayList<SendReceiptPO> sendReceiptPOs=sendReceiptData.getList(fromTime, toTime);
		for(SendReceiptPO po:sendReceiptPOs){
			sendReceiptVOs.add(new SendReceiptVO(po));
		}
		return sendReceiptVOs;
	}

	public void createReceipt(SendReceiptVO item) throws RemoteException, SQLException {
		sendReceiptData.addItem(new SendReceiptPO(item));		
	}

}
