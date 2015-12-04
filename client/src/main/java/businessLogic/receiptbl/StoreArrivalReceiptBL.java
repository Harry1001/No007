package businessLogic.receiptbl;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import data.StoreArrivalReceiptDataImpl;
import dataService.StoreArrivalReceiptDataService;
import po.receiptpo.StoreArrivalReceiptPO;
import vo.receiptvo.StoreArrivalReceiptVO;

public class StoreArrivalReceiptBL {
	
	private StoreArrivalReceiptDataService storeArrivalReceiptData=new StoreArrivalReceiptDataImpl();
	
	public ArrayList<StoreArrivalReceiptVO> getListByTime(Date fromTime, Date toTime)throws RemoteException, SQLException {
		ArrayList<StoreArrivalReceiptVO> storeArrivalReceiptVOs=new ArrayList<StoreArrivalReceiptVO>();
		ArrayList<StoreArrivalReceiptPO> storeArrivalReceiptPOs=storeArrivalReceiptData.getList(fromTime, toTime);
		for(StoreArrivalReceiptPO po:storeArrivalReceiptPOs){
			storeArrivalReceiptVOs.add(new StoreArrivalReceiptVO(po));
		}
		return storeArrivalReceiptVOs;
	}

	public void createReceipt(StoreArrivalReceiptVO item) throws RemoteException, SQLException {
		storeArrivalReceiptData.addItem(new StoreArrivalReceiptPO(item));
	}

}
