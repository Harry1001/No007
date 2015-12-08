package dataService.infodataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import myexceptions.InfoBLException;
import po.infopo.UserAccountPO;
import typeDefinition.Job;
import vo.loginvo.LoginInputVO;
import vo.loginvo.LoginResultVO;

public interface UserAccountDataService extends Remote {

	/**
	 * 返回所有用户列表
	 * @throws SQLException 
	 */
	public ArrayList<UserAccountPO> getList() throws RemoteException, SQLException;
	
	/**
	 * 在持久化数据中增加一个po条目
	 * @throws SQLException 
	 */
	public void addItem(UserAccountPO item) throws RemoteException, InfoBLException, SQLException;
	
	/**
	 * 在持久化数据中删除一个po
	 * @throws SQLException 
	 */
	public void deleteItem(String id) throws RemoteException, SQLException;
	
	/**
	 * 在持久化数据中更新一个po
	 * @throws SQLException 
	 */
	public void update(String id, UserAccountPO item) throws RemoteException, InfoBLException, SQLException;
	
	/**
	 * 验证登录信息
	 * @param id
	 * @param password
	 * @return
	 * @throws RemoteException
	 * @throws SQLException 
	 */
	public LoginResultVO verify(LoginInputVO vo) throws RemoteException;
}
