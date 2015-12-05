package dataService.infodataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import myexceptions.InfoBLException;
import po.infopo.AgencyPO;

public interface AgencyDataService extends Remote {

	/**
	 * 返回agency列表
	 * @throws RemoteException, SQLException
	 */
	public ArrayList<AgencyPO> getList() throws RemoteException, SQLException;
	
	/**
	 * 在持久化数据中增加一个agencypo条目
	 * @throws RemoteException, InfoBLException, SQLException 
	 */
	public void addItem(AgencyPO item) throws RemoteException, InfoBLException, SQLException;
	
	/**
	 * 在持久化数据中删除一个agencypo
	 * @throws RemoteException, SQLException 
	 */
	public void deleteItem(String id) throws RemoteException, SQLException;
	
	/**
	 * 在持久化数据中更新一个agencypo
	 * @throws RemoteException, InfoBLException
	 * @throws SQLException 
	 */
	public void update(String id, AgencyPO item) throws RemoteException, InfoBLException, SQLException;
}
