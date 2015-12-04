package businessLogic.receiptbl;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import data.DespatchReceiptDataImpl;
import dataService.DespatchReceiptDataService;
import po.receiptpo.DespatchReceiptPO;
import vo.receiptvo.DespatchReceiptVO;

public class DespatchReceiptBL {

	private DespatchReceiptDataService despatchReceiptData=new DespatchReceiptDataImpl();
	
	public ArrayList<DespatchReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException {
		//TODO 如果起始时间小于终止时间，报错
		ArrayList<DespatchReceiptVO> despatchReceiptVOs=new ArrayList<DespatchReceiptVO>();
		ArrayList<DespatchReceiptPO> despatchReceiptPOs=despatchReceiptData.getList(fromTime, toTime);
		for(DespatchReceiptPO po:despatchReceiptPOs){
			despatchReceiptVOs.add(new DespatchReceiptVO(po));
		}
		return despatchReceiptVOs;
	}

	public void createReceipt(DespatchReceiptVO item) throws RemoteException, SQLException {
		despatchReceiptData.addItem(new DespatchReceiptPO(item));		
	}

}
