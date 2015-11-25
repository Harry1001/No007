package dataService;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import po.commoditypo.CommodityPO;

public interface CommodityDataService {

	/**
	 * 
	 * @param TransferNum
	 * @return 返回指定中转中心仓库的库存信息
	 * @throws RemoteException
	 * @throws SQLException 
	 */
	public ArrayList<CommodityPO> check(String TransferNum) throws RemoteException, SQLException;

	/**
	 * 
	 * @return	返回所有中转中心的库存信息
	 * @throws RemoteException
	 * @throws SQLException 
	 */
	public ArrayList<CommodityPO> getAll() throws RemoteException, SQLException;
	
	
	/**
	 * 初始化库存数据库
	 * @throws RemoteException
	 * @throws SQLException 
	 */
	public void renew(String transferNum) throws RemoteException, SQLException;
	
	/**
	 * 库存数据块新增库存信息
	 * @param commodityPO
	 * @throws RemoteException
	 * @throws SQLException 
	 */
	public void add(CommodityPO commodityPO) throws RemoteException, SQLException;
	
	/**
	 * 库存数据库删除库存信息
	 * @param transferNum
	 * @throws RemoteException
	 * @throws SQLException 
	 */
	public void delete(String expressNum) throws RemoteException, SQLException;
	
}
