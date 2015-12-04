package data;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.ReceiveReceiptDataService;
import database.ReceiveReceiptDBManager;
import po.receiptpo.ReceiveReceiptPO;

public class ReceiveReceiptDataImpl implements ReceiveReceiptDataService{

	private ReceiveReceiptDBManager receivereceiptDBManager=new ReceiveReceiptDBManager();
	
	public ArrayList<ReceiveReceiptPO> getList(Date fromtime, Date toTime) throws RemoteException, SQLException {
		return receivereceiptDBManager.getList(fromtime, toTime);
	}

	public void addItem(ReceiveReceiptPO item) throws RemoteException, SQLException {
		receivereceiptDBManager.addItem(item);
	}

	public void deleteAll() throws RemoteException, SQLException {
		receivereceiptDBManager.deleteAll();
	}

}
