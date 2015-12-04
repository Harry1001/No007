package businessLogic.receiptbl;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import data.EntruckReceiptDataImpl;
import dataService.EntruckReceiptDataService;
import po.receiptpo.EntruckReceiptPO;
import vo.receiptvo.EntruckReceiptVO;

public class EntruckReceiptBL {

	private EntruckReceiptDataService entruckReceiptData=new EntruckReceiptDataImpl();
	
	public ArrayList<EntruckReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException {
		ArrayList<EntruckReceiptVO> entruckReceiptVOs=new ArrayList<EntruckReceiptVO>();
		ArrayList<EntruckReceiptPO> entruckReceiptPOs=entruckReceiptData.getList(fromTime, toTime);
		for(EntruckReceiptPO po:entruckReceiptPOs){
			entruckReceiptVOs.add(new EntruckReceiptVO(po));
		}
		return entruckReceiptVOs;
	}

	public void createReceipt(EntruckReceiptVO item) throws RemoteException, SQLException {
		entruckReceiptData.addItem(new EntruckReceiptPO(item));
	}
}
