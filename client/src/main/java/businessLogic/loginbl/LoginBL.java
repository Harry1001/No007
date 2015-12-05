package businessLogic.loginbl;

import blfactory.BLFactory;
import businessLogicService.infoblservice.UserAccoutBLService;
import businessLogicService.loginblservice.LoginBLService;
import typeDefinition.Job;
import vo.loginvo.LoginResultVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class LoginBL implements LoginBLService{

	public LoginResultVO getPermission(String id, String password) throws RemoteException, MalformedURLException, NotBoundException{
		// TODO Auto-generated method stub
		UserAccoutBLService useraccountblservice=BLFactory.getUserAccountBLService();
		return 	useraccountblservice.verifyPassword(id, password);
	}
	
}
