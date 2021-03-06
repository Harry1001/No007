package dataService;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import po.receiptpo.ReceiveReceiptPO;

public interface ReceiveReceiptDataService {

	/**
	 * 按对应time返回相应ReceiveReceiptPO列表
	 * @throws SQLException 
	 */
	public ArrayList<ReceiveReceiptPO> getList(Date fromtime, Date toTime) throws RemoteException, SQLException;
	
	/**
	 * 在持久化数据中增加一个po
	 * @throws SQLException 
	 */
	public void addItem(ReceiveReceiptPO item) throws RemoteException, SQLException;
	
	/**
	 * 清空持久化数据中type类型的所有单据
	 * @throws SQLException 
	 */
	public void deleteAll() throws RemoteException, SQLException;
}
