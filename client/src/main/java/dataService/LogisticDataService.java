package dataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import po.logisticpo.LogisticPO;

public interface LogisticDataService extends Remote{

	/**
	 * 更新物流信息po
	 * @throws SQLException 
	 */
	public void update(LogisticPO po) throws RemoteException, SQLException;
	
	/**
	 * 根据num查找相应物流信息并返回，如果不存在则返回一个物流信息为“此单号不存在”的po
	 * @throws SQLException 
	 */
	public ArrayList<LogisticPO> read(String num) throws RemoteException, SQLException;
	
	/**
	 * 删除物流信息持久化数据，被update调用
	 * @throws SQLException 
	 */
	public void remove(String num) throws RemoteException, SQLException;
}
