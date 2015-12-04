package dataService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.receiptpo.ReceiptPO;
import typeDefinition.ReceiptState;
import typeDefinition.ReceiptType;
import java.util.Date;

public interface ReceiptDataService {
	
	/**
	 * 按对应type,state,time返回相应ReceiptPO列表
	 */
	public ArrayList<ReceiptPO> getList(ReceiptType type, Date fromtime, Date toTime) throws RemoteException;
	
	/**
	 * 在持久化数据中增加一个po
	 */
	public void addItem(ReceiptPO item) throws RemoteException;
	
	/*
	 *  向持久化数据中更新一个具有相同单号的po
	 */
	public void update(ReceiptPO item) throws RemoteException;
	
	/**
	 * 清空持久化数据中所有type类型的单据
	 */
	public void deleteAll(ReceiptType type) throws RemoteException;
}
