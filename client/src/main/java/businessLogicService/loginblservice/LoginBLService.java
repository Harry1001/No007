package businessLogicService.loginblservice;

import typeDefinition.Job;
import vo.loginvo.LoginResultVO;

import java.rmi.RemoteException;

public interface LoginBLService {
	/*
	 * 获得权限跳转界面
	 */
	public LoginResultVO getPermission(String id, String password) throws RemoteException;
	
}
