package data;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataService.ReceiptDataService;
import po.ReceiptPO;
import typeDefinition.ReceiptState;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;

public class ReceiptDataImpl implements ReceiptDataService {

	public ArrayList<ReceiptPO> getList(ReceiptType type, ReceiptState state, myTime time) throws RemoteException {
		// TODO Auto-generated method stub
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
