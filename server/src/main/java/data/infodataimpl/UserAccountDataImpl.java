package data.infodataimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import dataService.infodataservice.UserAccountDataService;
import database.UserAccountDBManager;
import myexceptions.InfoBLException;
import po.infopo.UserAccountPO;
import typeDefinition.Job;
import vo.loginvo.LoginInputVO;
import vo.loginvo.LoginResultVO;

public class UserAccountDataImpl extends UnicastRemoteObject implements UserAccountDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserAccountDBManager userAccountDBManager;
	
	public UserAccountDataImpl() throws RemoteException {
		super();
		this.userAccountDBManager = new UserAccountDBManager();
	}

	public ArrayList<UserAccountPO> getList() throws RemoteException, SQLException {
		ArrayList<UserAccountPO> userAccountPOs = userAccountDBManager.getall();
		return userAccountPOs;
	}

	public void addItem(UserAccountPO item) throws RemoteException, InfoBLException, SQLException {
		if(!isExist(item.getUserID()))
			userAccountDBManager.add(item);		
		else throw new InfoBLException("该账号已经存在");
	}

	public void deleteItem(String id) throws RemoteException, SQLException {
		userAccountDBManager.delete(id);		
	}

	public void update(String id, UserAccountPO item) throws RemoteException, InfoBLException, SQLException {
		if(!isExist(item.getUserID())) {
			deleteItem(id);
			addItem(item);
		}
		else throw new InfoBLException("该账号已经存在");		
	}

	public LoginResultVO verify(LoginInputVO vo) throws RemoteException {
		String id = vo.getName();
		String password = vo.getPassword();
		Job job = Job.NOTFOUND;
		String name = "";
		try {
			UserAccountPO po = userAccountDBManager.get(id);
			if(password .equals(po.getPassword()) ) {
				job = po.getPosition();
				name = po.getName();
			}
		} catch (SQLException e) {//没有找到po
		}
		LoginResultVO resultVO = new LoginResultVO(id, job, name);
		return resultVO;		
	}
	
	private boolean isExist(String id) throws SQLException {
		UserAccountPO po = userAccountDBManager.get(id);
		if(po==null) return false;
		else return true;
	}
}
