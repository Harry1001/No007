package data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.ReceiveReceiptDataService;
import database.ReceiveReceiptDBManager;
import po.receiptpo.ReceiveReceiptPO;

public class ReceiveReceiptDataImpl extends UnicastRemoteObject implements ReceiveReceiptDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReceiveReceiptDBManager receivereceiptDBManager;
	
	public ReceiveReceiptDataImpl() throws RemoteException {
		super();
		receivereceiptDBManager=new ReceiveReceiptDBManager();
	}
	
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
