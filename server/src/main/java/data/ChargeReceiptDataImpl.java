package data;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.ChargeReceiptDataService;
import database.ChargeReceiptDBManager;
import po.receiptpo.ChargeReceiptPO;

public class ChargeReceiptDataImpl implements ChargeReceiptDataService{

	private ChargeReceiptDBManager chargeReceiptDBManager=new ChargeReceiptDBManager();
	
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
