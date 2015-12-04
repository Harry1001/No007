package data;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.StoreArrivalReceiptDataService;
import database.StoreArrivalReceiptDBManager;
import po.receiptpo.StoreArrivalReceiptPO;

public class StoreArrivalReceiptDataImpl implements StoreArrivalReceiptDataService{

	private StoreArrivalReceiptDBManager storeArrivalReceipt=new StoreArrivalReceiptDBManager();
	
	public ArrayList<StoreArrivalReceiptPO> getList(Date fromtime, Date toTime) throws RemoteException, SQLException {
		return storeArrivalReceipt.getList(fromtime, toTime);
	}

	public void addItem(StoreArrivalReceiptPO item) throws RemoteException, SQLException {
		storeArrivalReceipt.addItem(item);
	}

	public void deleteAll() throws RemoteException, SQLException {
		storeArrivalReceipt.deleteAll();
	}

}
