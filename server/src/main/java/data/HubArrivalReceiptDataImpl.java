package data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.HubArrivalReceiptDataService;
import database.HubArrivalReceiptDBManager;
import myexceptions.TransportBLException;
import po.receiptpo.HubArrivalReceiptPO;

public class HubArrivalReceiptDataImpl extends UnicastRemoteObject implements HubArrivalReceiptDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HubArrivalReceiptDBManager hubArrivalReceipt;
	
	public HubArrivalReceiptDataImpl() throws RemoteException {
		super();
		hubArrivalReceipt=new HubArrivalReceiptDBManager();
	}
	
	public ArrayList<HubArrivalReceiptPO> getList(Date fromtime, Date toTime) throws RemoteException, SQLException {
		return hubArrivalReceipt.getList(fromtime, toTime);
	}

	public void addItem(HubArrivalReceiptPO item) throws RemoteException, SQLException, TransportBLException {
		if(!isExist(item.getOrderID(), item.getTransReceiptID()))
			hubArrivalReceipt.addItem(item);
		else
			throw new TransportBLException("该单据已存在！");
	}

	public void deleteAll() throws RemoteException, SQLException {
		hubArrivalReceipt.deleteAll();
	}
	
	private boolean isExist(String orderID, String transID) throws SQLException{
		HubArrivalReceiptPO po=hubArrivalReceipt.getItem(orderID);
		if(po==null)
			return false;
		else if (transID.equals(po.getTransReceiptID())){
			return true;
		}
		else {
			return false;
		}

	}
}
