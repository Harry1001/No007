package dataService;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import po.receiptpo.PayReceiptPO;
import typeDefinition.ReceiptState;

public interface PayReceiptDataService {

	/**
	 * 按对应time返回相应PayReceiptPO列表
	 * @throws SQLException 
	 */
	public ArrayList<PayReceiptPO> getList(Date fromtime, Date toTime) throws RemoteException, SQLException;
	
	/**
	 * 按对应state返回相应PayReceiptPO列表
	 * @param state
	 * @return
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public ArrayList<PayReceiptPO> getListByState(ReceiptState state) throws RemoteException, SQLException;
	
	/**
	 * 在持久化数据中增加一个po
	 * @throws SQLException 
	 */
	public void addItem(PayReceiptPO item) throws RemoteException, SQLException;

	/**
	 * 更新订单号对应单据的状态
	 * @param orderID
	 * @param state
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public void updateItem(String orderID,ReceiptState state) throws RemoteException, SQLException;
	
	/**
	 * 清空持久化数据中type类型的所有单据
	 * @throws SQLException 
	 */
	public void deleteAll() throws RemoteException, SQLException;
	
}
