package dataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import po.receiptpo.TransferReceiptPO;

public interface TransferReceiptDataService extends Remote {

	/**
	 * 按对应time返回相应TransferReceiptPO列表
	 * @throws SQLException 
	 */
	public ArrayList<TransferReceiptPO> getList(Date fromtime, Date toTime) throws RemoteException, SQLException;
	
	/**
	 * 在持久化数据中增加一个po
	 * @throws SQLException 
	 */
	public void addItem(TransferReceiptPO item) throws RemoteException, SQLException;
	
	/**
	 * 清空持久化数据中type类型的所有单据
	 * @throws SQLException 
	 */
	public void deleteAll() throws RemoteException, SQLException;
	

	public ArrayList<String> getOrderID(String transportID) throws RemoteException, SQLException;


}
