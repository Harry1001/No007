package data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.ChargeReceiptDataService;
import database.ChargeReceiptDBManager;
import po.receiptpo.ChargeReceiptPO;

public class ChargeReceiptDataImpl extends UnicastRemoteObject implements ChargeReceiptDataService{

	private ChargeReceiptDBManager chargeReceiptDBManager;
	
	public ChargeReceiptDataImpl() throws RemoteException {
		super();
		chargeReceiptDBManager=new ChargeReceiptDBManager();
	}
		
	public ArrayList<ChargeReceiptPO> getList(Date fromtime, Date toTime) throws RemoteException, SQLException {
		return chargeReceiptDBManager.getList(fromtime, toTime);
	}

	public void addItem(ChargeReceiptPO item) throws RemoteException, SQLException {
		chargeReceiptDBManager.addItem(item);
	}

	public void deleteAll() throws RemoteException, SQLException {
		chargeReceiptDBManager.deleteAll();
	}

}
