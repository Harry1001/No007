package dataService.infodataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import myexceptions.InfoBLException;
import po.infopo.StaffPO;

public interface StaffDataService extends Remote {

	/**
	 * 返回员工列表
	 * @throws SQLException 
	 */
	public ArrayList<StaffPO> getList() throws RemoteException, SQLException;
	
	/**
	 * 在持久化数据中增加一个po条目
	 * @throws SQLException 
	 */
	public void addItem(StaffPO item) throws RemoteException, InfoBLException, SQLException;
	
	/**
	 * 在持久化数据中删除一个po
	 * @throws SQLException 
	 */
	public void deleteItem(String id) throws RemoteException, SQLException;
	
	/**
	 * 在持久化数据中更新一个po
	 * @throws SQLException 
	 */
	public void update(String id, StaffPO item) throws RemoteException, InfoBLException, SQLException;
}
