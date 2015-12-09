package data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.TransferReceiptDataService;
import database.TransferReceiptDBManager;
import po.receiptpo.TransferReceiptPO;

public class TransferReceiptDataImpl extends UnicastRemoteObject implements TransferReceiptDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TransferReceiptDBManager transferreceiptDBManager;
	
	public TransferReceiptDataImpl() throws RemoteException {
		super();
		transferreceiptDBManager=new TransferReceiptDBManager();
	}
	
	public ArrayList<TransferReceiptPO> getList(Date fromtime, Date toTime) throws RemoteException, SQLException {
		return transferreceiptDBManager.getList(fromtime, toTime);
	}

	public void addItem(TransferReceiptPO item) throws RemoteException, SQLException {
		transferreceiptDBManager.addItem(item);
	}

	public void deleteAll() throws RemoteException, SQLException {
		transferreceiptDBManager.deleteAll();
	}

	public ArrayList<String> getOrderID(String transportID) throws SQLException {
		return transferreceiptDBManager.getOrderID(transportID);
	}

}
