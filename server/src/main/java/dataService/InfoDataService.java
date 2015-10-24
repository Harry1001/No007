package dataService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.InfoPO;
import typeDefinition.InfoType;

public interface InfoDataService {
	
	/**
	 * 按照type类型返回对应Info列表
	 */
	public ArrayList<InfoPO> getList(InfoType type) throws RemoteException;
	
	/**
	 * 在持久化数据中增加一个po条目
	 */
	public void addItem(InfoPO item) throws RemoteException;
	
	/**
	 * 在持久化数据中删除一个po
	 */
	public void deleteItem(InfoType type, String id) throws RemoteException;
	
	/**
	 * 在持久化数据中更新一个po
	 */
	public void update(String id, InfoPO item) throws RemoteException;
}
