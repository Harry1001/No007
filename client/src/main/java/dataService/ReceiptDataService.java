package dataService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.receiptpo.ReceiptPO;
import typeDefinition.ReceiptState;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;

public interface ReceiptDataService {
	
	/**
	 * 按对应type,state,time返回相应ReceiptPO列表
	 */
	public ArrayList<ReceiptPO> getList(ReceiptType type, ReceiptState state, myTime time) throws RemoteException;
	
	/**
	 * 在持久化数据中增加一个po
	 */
	public void addItem(ReceiptPO item) throws RemoteException;
	

	/**
	 * 清空持久化数据中所有type类型的单据
	 */
	public void deleteAllByType(ReceiptType type) throws RemoteException;

	/**
	 * 清空持久化数据中的所有单据
	 */
	public void deleteAll() throws RemoteException;
}
