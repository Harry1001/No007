package data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.SendReceiptDataService;
import database.SendReceiptDBManager;
import po.receiptpo.SendReceiptPO;

public class SendReceiptDataImpl extends UnicastRemoteObject implements SendReceiptDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SendReceiptDBManager sendreceiptDBManager;
	
	public SendReceiptDataImpl() throws RemoteException {
		super();
		sendreceiptDBManager=new SendReceiptDBManager();
	}

	public ArrayList<SendReceiptPO> getList(Date fromtime, Date toTime) throws RemoteException, SQLException {
		return sendreceiptDBManager.getList(fromtime, toTime);
	}

	public void addItem(SendReceiptPO item) throws RemoteException, SQLException {
		sendreceiptDBManager.addItem(item);
	}

	public void deleteAll() throws RemoteException, SQLException {
		sendreceiptDBManager.deleteAll();
	}

	public SendReceiptPO getItem(String orderID) throws SQLException {
		return sendreceiptDBManager.getItem(orderID);
	}

}
