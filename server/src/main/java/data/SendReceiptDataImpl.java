package data;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.SendReceiptDataService;
import database.SendReceiptDBManager;
import po.receiptpo.SendReceiptPO;

public class SendReceiptDataImpl implements SendReceiptDataService{

	private SendReceiptDBManager sendreceiptDBManager=new SendReceiptDBManager();

	public ArrayList<SendReceiptPO> getList(Date fromtime, Date toTime) throws RemoteException, SQLException {
		return sendreceiptDBManager.getList(fromtime, toTime);
	}

	public void addItem(SendReceiptPO item) throws RemoteException, SQLException {
		sendreceiptDBManager.addItem(item);
	}

	public void deleteAll() throws RemoteException, SQLException {
		sendreceiptDBManager.deleteAll();
	}

}
