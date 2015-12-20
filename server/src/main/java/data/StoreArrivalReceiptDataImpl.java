package data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.StoreArrivalReceiptDataService;
import database.StoreArrivalReceiptDBManager;
import myexceptions.TransportBLException;
import po.receiptpo.StoreArrivalReceiptPO;

public class StoreArrivalReceiptDataImpl extends UnicastRemoteObject implements StoreArrivalReceiptDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StoreArrivalReceiptDBManager storeArrivalReceipt;
	
	public StoreArrivalReceiptDataImpl() throws RemoteException {
		super();
		storeArrivalReceipt=new StoreArrivalReceiptDBManager();
	}
	
	public ArrayList<StoreArrivalReceiptPO> getList(Date fromtime, Date toTime) throws RemoteException, SQLException {
		return storeArrivalReceipt.getList(fromtime, toTime);
	}

	public void addItem(StoreArrivalReceiptPO item) throws RemoteException, SQLException, TransportBLException {
		if(!isExist(item.getOrderID()))
			storeArrivalReceipt.addItem(item);
		else
			throw new TransportBLException("该单据已存在！");
	}

	public void deleteAll() throws RemoteException, SQLException {
		storeArrivalReceipt.deleteAll();
	}

	private boolean isExist(String orderID) throws SQLException{
		StoreArrivalReceiptPO po=storeArrivalReceipt.getItem(orderID);
		if(po==null)
			return false;
		else 
			return true;
	}
}
