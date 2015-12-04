package businessLogic.receiptbl;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import data.SendReceiptDataImpl;
import dataService.SendReceiptDataService;
import po.receiptpo.SendReceiptPO;
import vo.receiptvo.SendReceiptVO;

public class SendReceiptBL {	

	private SendReceiptDataService sendReceiptData=new SendReceiptDataImpl();
	
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
