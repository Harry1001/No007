package dataService.infodataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import myexceptions.InfoBLException;
import po.infopo.DriverPO;

public interface DriverDataService extends Remote {

	/**
	 * 按照type类型返回对应Info列表
	 * @throws SQLException 
	 */
	public ArrayList<DriverPO> getList() throws RemoteException, SQLException;
	
	/**
	 * 在持久化数据中增加一个po条目
	 * @throws SQLException 
	 */
	public void addItem(DriverPO item) throws RemoteException, InfoBLException, SQLException;
	
	/**
	 * 在持久化数据中删除一个po
	 * @throws SQLException 
	 */
	public void deleteItem(String id) throws RemoteException, SQLException;
	
	/**
	 * 在持久化数据中更新一个po
	 * @throws SQLException 
	 */
	public void update(String id, DriverPO item) throws RemoteException, InfoBLException, SQLException;
}
