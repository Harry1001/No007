package data;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataService.ReceiptDataService;
import po.receiptpo.ReceiptPO;
import typeDefinition.ReceiptState;
import typeDefinition.ReceiptType;
import java.util.Date;

public class ReceiptDataImpl implements ReceiptDataService {


	public ArrayList<ReceiptPO> getList(ReceiptType type, Date fromtime, Date toTime) throws RemoteException {
		return null;
	}

	public void addItem(ReceiptPO item) throws RemoteException {
		// TODO Auto-generated method stub

	}

	public void update(ReceiptPO item) throws RemoteException {
		// TODO Auto-generated method stub

	}

	public void deleteAll(ReceiptType type) throws RemoteException {
		// TODO Auto-generated method stub

	}

}
