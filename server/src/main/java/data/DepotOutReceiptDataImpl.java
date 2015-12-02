package data;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.DepotOutReceiptDataService;
import database.DepotOutReceiptDBManager;
import po.receiptpo.DepotOutReceiptPO;

public class DepotOutReceiptDataImpl implements DepotOutReceiptDataService{

	private DepotOutReceiptDBManager depotOutReceipt=new DepotOutReceiptDBManager();
	
	public ArrayList<DepotOutReceiptPO> getList(Date fromtime, Date toTime) throws RemoteException, SQLException {
		return depotOutReceipt.getList(fromtime, toTime);
	}

	public void addItem(DepotOutReceiptPO item) throws RemoteException, SQLException {
		depotOutReceipt.addItem(item);
	}

	public void deleteAll() throws RemoteException, SQLException {
		depotOutReceipt.deleteAll();
	}
	
}
