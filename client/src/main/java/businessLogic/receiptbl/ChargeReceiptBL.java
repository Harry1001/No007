package businessLogic.receiptbl;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import data.ChargeReceiptDataImpl;
import dataService.ChargeReceiptDataService;
import po.receiptpo.ChargeReceiptPO;
import vo.receiptvo.ChargeReceiptVO;

public class ChargeReceiptBL {
	
	private ChargeReceiptDataService chargeReceiptData=new ChargeReceiptDataImpl();
	
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
