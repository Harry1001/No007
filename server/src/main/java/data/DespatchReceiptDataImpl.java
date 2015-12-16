package data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.DespatchReceiptDataService;
import database.DespatchReceiptDBManager;
import po.receiptpo.DespatchReceiptPO;

public class DespatchReceiptDataImpl extends UnicastRemoteObject implements DespatchReceiptDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DespatchReceiptDBManager despatchreceiptDBManager;
	
	public DespatchReceiptDataImpl() throws RemoteException {
		super();
		despatchreceiptDBManager=new DespatchReceiptDBManager();
	}
	
	public ArrayList<DespatchReceiptPO> getList(Date fromtime, Date toTime) throws RemoteException, SQLException {
		return despatchreceiptDBManager.getList(fromtime, toTime);
	}

	public void addItem(DespatchReceiptPO item) throws RemoteException, SQLException {
		despatchreceiptDBManager.addItem(item);
	}

	public void deleteAll() throws RemoteException, SQLException {
		despatchreceiptDBManager.deleteAll();
	}

}
