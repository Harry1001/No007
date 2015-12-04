package businessLogic.receiptbl;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import data.DepotOutReceiptDataImpl;
import dataService.DepotOutReceiptDataService;
import po.receiptpo.DepotOutReceiptPO;
import vo.receiptvo.DepotOutReceiptVO;

public class DepotOutReceiptBL {
	
	private DepotOutReceiptDataService depotOutReceiptData=new DepotOutReceiptDataImpl();
	
	public ArrayList<DepotOutReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException {
		ArrayList<DepotOutReceiptVO> depotOutReceiptVOs=new ArrayList<DepotOutReceiptVO>();
		ArrayList<DepotOutReceiptPO> depotOutReceiptPOs=depotOutReceiptData.getList(fromTime, toTime);
		for(DepotOutReceiptPO po:depotOutReceiptPOs){
			depotOutReceiptVOs.add(new DepotOutReceiptVO(po));
		}
		return depotOutReceiptVOs;
	}

	public void createReceipt(DepotOutReceiptVO item) throws RemoteException, SQLException {
		depotOutReceiptData.addItem(new DepotOutReceiptPO(item));
	}

}
