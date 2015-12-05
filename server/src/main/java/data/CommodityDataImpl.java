package data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import dataService.CommodityDataService;
import database.CommodityDBManager;
import po.commoditypo.CommodityPO;

public class CommodityDataImpl extends UnicastRemoteObject implements CommodityDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CommodityDBManager commodityDBManager;
	
	public CommodityDataImpl() throws RemoteException {
		super();
		this.commodityDBManager = new CommodityDBManager();
	}

	public ArrayList<CommodityPO> check(String transferNum) throws RemoteException, SQLException {
		ArrayList<CommodityPO> commodityPOs = commodityDBManager.getByTransferNum(transferNum);
		return commodityPOs;
	}

	public ArrayList<CommodityPO> getAll() throws RemoteException, SQLException {
		ArrayList<CommodityPO> commodityPOs = commodityDBManager.getAll();
		return commodityPOs;
	}

	public void renew(String transferNum) throws RemoteException, SQLException {
		commodityDBManager.clear(transferNum);
	}

	public void add(CommodityPO commodityPO) throws RemoteException, SQLException {
		commodityDBManager.addCommodity(commodityPO);
	}

	public void delete(String expressNum) throws RemoteException, SQLException {
		commodityDBManager.removeCommodity(expressNum);
	}

}
