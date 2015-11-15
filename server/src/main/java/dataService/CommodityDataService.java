package dataService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.commoditypo.CommodityPO;

public interface CommodityDataService {

	/**
	 * 
	 * @param TransferNum
	 * @return 返回指定中转中心仓库的库存信息
	 * @throws RemoteException
	 */
	public ArrayList<CommodityPO> check(String TransferNum) throws RemoteException;

	/**
	 * 
	 * @return	返回所有中转中心的库存信息
	 * @throws RemoteException
	 */
	public ArrayList<CommodityPO> getAll() throws RemoteException;
	
	
	/**
	 * 初始化库存数据库
	 * @throws RemoteException
	 */
	public void renew() throws RemoteException;
	
	/**
	 * 库存数据块更新库存信息
	 * @param commodityPO
	 * @throws RemoteException
	 */
	public void update(CommodityPO commodityPO) throws RemoteException;
	
}
