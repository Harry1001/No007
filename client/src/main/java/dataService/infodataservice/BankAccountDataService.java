package dataService.infodataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import myexceptions.InfoBLException;
import po.infopo.BankAccountPO;

public interface BankAccountDataService extends Remote {

	/**
	 * 返回银行账号列表
	 * @throws SQLException 
	 * @throws RemoteException
	 */
	public ArrayList<BankAccountPO> getList() throws RemoteException, SQLException;
	
	/**
	 * 增
	 * @param item
	 * @throws RemoteException
	 * @throws InfoBLException
	 * @throws SQLException
	 */
	public void addItem(BankAccountPO item) throws RemoteException, InfoBLException, SQLException;
	
	/**
	 * 删
	 * @param id
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public void deleteItem(String id) throws RemoteException, SQLException;
	
	/**
	 * 改
	 * @param id
	 * @param change 变化的金额
	 * @throws RemoteException
	 * @throws InfoBLException
	 * @throws SQLException 
	 */
	public void update(String id, double change) throws RemoteException, InfoBLException, SQLException;
}
