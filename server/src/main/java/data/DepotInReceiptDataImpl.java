package data;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.DepotInReceiptDataService;
import database.DepotInReceiptDBManager;
import po.receiptpo.DepotInReceiptPO;

public class DepotInReceiptDataImpl implements DepotInReceiptDataService{

	private DepotInReceiptDBManager depotInReceipt=new DepotInReceiptDBManager();
	
	public ArrayList<DepotInReceiptPO> getList(Date fromtime, Date toTime) throws RemoteException, SQLException {
		return depotInReceipt.getList(fromtime, toTime);
	}

	public void addItem(DepotInReceiptPO item) throws RemoteException, SQLException {
		depotInReceipt.addItem(item);
	}

	public void deleteAll() throws RemoteException, SQLException {
		depotInReceipt.deleteAll();
	}

}