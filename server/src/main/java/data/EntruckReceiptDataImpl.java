package data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.EntruckReceiptDataService;
import database.EntruckReceiptDBManager;
import po.receiptpo.EntruckReceiptPO;

public class EntruckReceiptDataImpl extends UnicastRemoteObject implements EntruckReceiptDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntruckReceiptDBManager entruckreceiptDBManager;
	
	public EntruckReceiptDataImpl() throws RemoteException {
		entruckreceiptDBManager=new EntruckReceiptDBManager();
	}
	
	public ArrayList<EntruckReceiptPO> getList(Date fromtime, Date toTime) throws RemoteException, SQLException {
		return entruckreceiptDBManager.getList(fromtime, toTime);
	}

	public void addItem(EntruckReceiptPO item) throws RemoteException, SQLException {
		entruckreceiptDBManager.addItem(item);
	}

	public void deleteAll() throws RemoteException, SQLException {
		entruckreceiptDBManager.deleteAll();
	}

	
}
