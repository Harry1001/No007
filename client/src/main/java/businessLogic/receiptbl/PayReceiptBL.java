package businessLogic.receiptbl;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import data.PayReceiptDataImpl;
import dataService.PayReceiptDataService;
import po.receiptpo.PayReceiptPO;
import vo.receiptvo.PayReceiptVO;

public class PayReceiptBL {

	private PayReceiptDataService payReceiptData=new PayReceiptDataImpl();
	
	public ArrayList<PayReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException{	
		ArrayList<PayReceiptVO> payReceiptVOs=new ArrayList<PayReceiptVO>();
		ArrayList<PayReceiptPO> payReceiptPOs=payReceiptData.getList(fromTime, toTime);
		for(PayReceiptPO po:payReceiptPOs){
			payReceiptVOs.add(new PayReceiptVO(po));
		}
		return payReceiptVOs;
	}
	
	public void createReceipt(PayReceiptVO item) throws RemoteException, SQLException{
		payReceiptData.addItem(new PayReceiptPO(item));
	}
	
}
