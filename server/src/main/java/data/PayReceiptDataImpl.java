package data;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.PayReceiptDataService;
import database.PayReceiptDBManager;
import po.receiptpo.PayReceiptPO;

public class PayReceiptDataImpl implements PayReceiptDataService{

	private PayReceiptDBManager payReceiptDBManager=new PayReceiptDBManager();
	
	public ArrayList<PayReceiptPO> getList(Date fromtime, Date toTime) throws RemoteException, SQLException {
		return payReceiptDBManager.getList(fromtime, toTime);
	}

	public void addItem(PayReceiptPO item) throws RemoteException, SQLException {
		payReceiptDBManager.addItem(item);
	}

	public void deleteAll() throws RemoteException, SQLException {
		payReceiptDBManager.deleteAll();
	}

}
