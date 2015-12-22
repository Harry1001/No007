package dataService.infodataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import myexceptions.InfoBLException;
import po.infopo.StaffPO;

public interface StaffDataService extends Remote {

	/**
	 * 返回员工列表
	 * @throws RemoteException, SQLException 
	 */
	public ArrayList<StaffPO> getList() throws RemoteException, SQLException;

	/**
	 * 在持久化数据中增加一个po条目
	 * @throws RemoteException, InfoBLException, SQLException
	 */
	public void addItem(StaffPO item) throws RemoteException, InfoBLException, SQLException;

	/**
	 * 在持久化数据中删除一个po
	 * @throws RemoteException, SQLException 
	 */
	public void deleteItem(String id) throws RemoteException, SQLException;

	/**
	 * 在持久化数据中更新一个po
	 * @throws RemoteException, InfoBLException, SQLException
	 */
	public void update(String id, StaffPO item) throws RemoteException, InfoBLException, SQLException;

	/**
	 * 当快递员完成一单后增加他的工作次数
	 * @param staffID
	 * @throws SQLException
	 * @throws RemoteException
	 * @throws InfoBLException
	 */
	public void addWorkFrequency(String staffID) throws SQLException, RemoteException, InfoBLException;

	/**
	 * 每次付完工资后需要清空工作次数
	 * @param id
	 * @throws SQLException
	 * @throws RemoteException
	 * @throws InfoBLException
	 */
	public void refreshWorkFreqeuncy(String id) throws SQLException, RemoteException, InfoBLException;
}
