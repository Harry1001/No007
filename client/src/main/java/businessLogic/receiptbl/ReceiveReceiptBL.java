package businessLogic.receiptbl;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import data.ReceiveReceiptDataImpl;
import dataService.ReceiveReceiptDataService;
import po.receiptpo.ReceiveReceiptPO;
import vo.receiptvo.ReceiveReceiptVO;

public class ReceiveReceiptBL {

	private ReceiveReceiptDataService receiveReceiptData=new ReceiveReceiptDataImpl();
	
	public ArrayList<ReceiveReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException{
		ArrayList<ReceiveReceiptVO> receiveReceiptVOs=new ArrayList<ReceiveReceiptVO>();
		ArrayList<ReceiveReceiptPO> receiveReceiptPOs=receiveReceiptData.getList(fromTime, toTime);
		for(ReceiveReceiptPO po:receiveReceiptPOs){
			receiveReceiptVOs.add(new ReceiveReceiptVO(po));
		}
		return receiveReceiptVOs;
	}
	
	public void createReceipt(ReceiveReceiptVO item) throws RemoteException, SQLException{
		receiveReceiptData.addItem(new ReceiveReceiptPO(item));
	}
}
