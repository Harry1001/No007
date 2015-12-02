package data;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.HubArrivalReceiptDataService;
import database.HubArrivalReceiptDBManager;
import po.receiptpo.HubArrivalReceiptPO;

public class HubArrivalReceiptDataImpl implements HubArrivalReceiptDataService{

	private HubArrivalReceiptDBManager hubArrivalReceipt=new HubArrivalReceiptDBManager();
	
	public ArrayList<HubArrivalReceiptPO> getList(Date fromtime, Date toTime) throws RemoteException, SQLException {
		return hubArrivalReceipt.getList(fromtime, toTime);
	}

	public void addItem(HubArrivalReceiptPO item) throws RemoteException, SQLException {
		hubArrivalReceipt.addItem(item);
	}

	public void deleteAll() throws RemoteException, SQLException {
		hubArrivalReceipt.deleteAll();
	}

}
