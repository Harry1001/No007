package businessLogic.loginbl;

import blfactory.BLFactory;
import businessLogicService.infoblservice.UserAccoutBLService;
import businessLogicService.loginblservice.LoginBLService;
import typeDefinition.Job;

import java.rmi.RemoteException;

public class LoginBL implements LoginBLService{

	public Job getPermission(String id, String password) throws RemoteException{
		// TODO Auto-generated method stub
		UserAccoutBLService useraccountblservice=BLFactory.getUserAccountBLService();
		return 	useraccountblservice.verifyPassword(id, password);
	}
	
}
