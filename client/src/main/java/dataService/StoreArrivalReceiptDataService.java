package dataService;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import myexceptions.TransportBLException;
import po.receiptpo.StoreArrivalReceiptPO;

public interface StoreArrivalReceiptDataService {

	/**
	 * 按对应time返回相应StoreArrivalReceiptPO列表
	 * @throws SQLException 
	 */
	public ArrayList<StoreArrivalReceiptPO> getList(Date fromtime, Date toTime) throws RemoteException, SQLException;
	
	/**
	 * 在持久化数据中增加一个po
	 * @throws SQLException 
	 * @throws TransportBLException 
	 */
	public void addItem(StoreArrivalReceiptPO item) throws RemoteException, SQLException, TransportBLException;
	
	/**
	 * 清空持久化数据中type类型的所有单据
	 * @throws SQLException 
	 */
	public void deleteAll() throws RemoteException, SQLException;
	
}
