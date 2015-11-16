package dataService;

import java.rmi.RemoteException;

import po.logisticpo.LogisticPO;

public interface LogisticDataService {

	/*
	 * 更新物流信息po
	 */
	public void update(LogisticPO po) throws RemoteException;
	
	/*
	 * 根据num查找相应物流信息并返回，如果不存在则返回一个物流信息为“此单号不存在”的po
	 */
	public LogisticPO read(String num) throws RemoteException;
	
	/*
	 * 删除物流信息持久化数据
	 */
	public void remove(String num) throws RemoteException;
}
