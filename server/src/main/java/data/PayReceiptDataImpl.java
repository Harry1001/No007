package data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.PayReceiptDataService;
import database.PayReceiptDBManager;
import po.receiptpo.PayReceiptPO;
import typeDefinition.ReceiptState;

public class PayReceiptDataImpl extends UnicastRemoteObject implements PayReceiptDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PayReceiptDBManager payReceiptDBManager;
	
	public PayReceiptDataImpl() throws RemoteException {
		super();
		payReceiptDBManager=new PayReceiptDBManager();
	}
	
	public ArrayList<PayReceiptPO> getList(Date fromtime, Date toTime) throws RemoteException, SQLException {
		return payReceiptDBManager.getList(fromtime, toTime);
	}

	public ArrayList<PayReceiptPO> getListByState(ReceiptState state) throws RemoteException, SQLException{
		return payReceiptDBManager.getListByState(state);
	}
	
	public void addItem(PayReceiptPO item) throws RemoteException, SQLException {
		payReceiptDBManager.addItem(item);
	}

	public void updateItem(String orderID,ReceiptState state) throws RemoteException, SQLException {
		payReceiptDBManager.update(orderID, state);
	}
	
	public void deleteAll() throws RemoteException, SQLException {
		payReceiptDBManager.deleteAll();
	}

}
