package businessLogic.receiptbl;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import data.TransferReceiptDataImpl;
import dataService.TransferReceiptDataService;
import po.receiptpo.TransferReceiptPO;
import vo.receiptvo.TransferReceiptVO;

public class TransferReceiptBL {

	private TransferReceiptDataService transferReceiptData=new TransferReceiptDataImpl();
	
	public ArrayList<TransferReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException {
		ArrayList<TransferReceiptVO> transferReceiptVOs=new ArrayList<TransferReceiptVO>();
		ArrayList<TransferReceiptPO> transferReceiptPOs=transferReceiptData.getList(fromTime, toTime);
		for(TransferReceiptPO po:transferReceiptPOs){
			transferReceiptVOs.add(new TransferReceiptVO(po));
		}
		return transferReceiptVOs;
	}

	public void createReceipt(TransferReceiptVO item) throws RemoteException, SQLException {
		transferReceiptData.addItem(new TransferReceiptPO(item));
	}
	
}
