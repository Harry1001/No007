package businessLogicService.loginblservice;

import vo.loginvo.LoginInputVO;
import vo.loginvo.LoginResultVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface LoginBLService {
	/*
	 * 获得权限跳转界面
	 */
	public LoginResultVO getPermission(LoginInputVO vo) throws RemoteException, MalformedURLException, NotBoundException;
	
}
