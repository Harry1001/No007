package dataService.infodataservice;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import myexceptions.InfoBLException;
import po.infopo.TruckPO;

public interface TruckDataService {

	/**
	 * 返回车辆信息列表
	 * @throws SQLException 
	 */
	public ArrayList<TruckPO> getList() throws RemoteException, SQLException;
	
	/**
	 * 在持久化数据中增加一个po条目
	 * @throws SQLException 
	 */
	public void addItem(TruckPO item) throws RemoteException, InfoBLException, SQLException;
	
	/**
	 * 在持久化数据中删除一个po
	 * @throws SQLException 
	 */
	public void deleteItem(String id) throws RemoteException, SQLException;
	
	/**
	 * 在持久化数据中更新一个po
	 * @throws SQLException 
	 */
	public void update(String id, TruckPO item) throws RemoteException, InfoBLException, SQLException;
}
