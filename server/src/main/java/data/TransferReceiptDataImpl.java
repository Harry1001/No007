package data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.TransferReceiptDataService;
import database.TransferReceiptDBManager;
import po.receiptpo.TransferReceiptPO;
import typeDefinition.ReceiptState;

public class TransferReceiptDataImpl extends UnicastRemoteObject implements TransferReceiptDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TransferReceiptDBManager transferreceiptDBManager;
	
	public TransferReceiptDataImpl() throws RemoteException {
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

	public ArrayList<TransferReceiptPO> getListByState(ReceiptState state) throws RemoteException, SQLException {
		return transferreceiptDBManager.getListByState(state);
	}

	public void updateItem(String orderID, ReceiptState state) throws RemoteException, SQLException {
		transferreceiptDBManager.update(orderID,state);
	}

}
