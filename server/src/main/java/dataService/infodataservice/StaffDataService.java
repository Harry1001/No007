package dataService.infodataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import myexceptions.InfoBLException;
import po.infopo.InfoPO;

public interface StaffDataService extends Remote {

	/**
	 * 按照type类型返回对应Info列表
	 */
	public ArrayList<InfoPO> getList() throws RemoteException;
	
	/**
	 * 在持久化数据中增加一个po条目
	 */
	public void addItem(InfoPO item) throws RemoteException, InfoBLException;
	
	/**
	 * 在持久化数据中删除一个po
	 */
	public void deleteItem(String id) throws RemoteException;
	
	/**
	 * 在持久化数据中更新一个po
	 */
	public void update(String id, InfoPO item) throws RemoteException, InfoBLException;
}
