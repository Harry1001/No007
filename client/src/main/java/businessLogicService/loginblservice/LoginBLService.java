package businessLogicService.loginblservice;

import typeDefinition.Job;

import java.rmi.RemoteException;

public interface LoginBLService {
	/*
	 * 获得权限跳转界面
	 */
	public Job getPermission(String id,String password) throws RemoteException;
	
}
