package businessLogicService.loginblservice;

import typeDefinition.Job;

public interface LoginBLService {
	/*
	 * 获得权限跳转界面
	 */
	public Job getPermission(String id,String password);
	
	/*
	 * 登录界面上点击查询物流信息后跳转到查询物流信息界面
	 */
	public void skip();
	
}
