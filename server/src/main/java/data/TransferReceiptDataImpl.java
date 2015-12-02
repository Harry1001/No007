package data;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.TransferReceiptDataService;
import database.TransferReceiptDBManager;
import po.receiptpo.TransferReceiptPO;

public class TransferReceiptDataImpl implements TransferReceiptDataService{

	private TransferReceiptDBManager transferreceiptDBManager=new TransferReceiptDBManager();
	
	public ArrayList<TransferReceiptPO> getList(Date fromtime, Date toTime) throws RemoteException, SQLException {
		return transferreceiptDBManager.getList(fromtime, toTime);
	}

	public void addItem(TransferReceiptPO item) throws RemoteException, SQLException {
		transferreceiptDBManager.addItem(item);
	}

	public void deleteAll() throws RemoteException, SQLException {
		transferreceiptDBManager.deleteAll();
	}

}
