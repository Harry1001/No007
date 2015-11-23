package businessLogic.loginbl;

import blfactory.BLFactory;
import businessLogicService.infoblservice.UserAccoutBLService;
import businessLogicService.loginblservice.LoginBLService;
import typeDefinition.Job;

public class LoginBL implements LoginBLService{

	public Job getPermission(String id, String password) {
		// TODO Auto-generated method stub
		UserAccoutBLService useraccountblservice=BLFactory.getUserAccountBLService();
		return 	useraccountblservice.verifyPassword(id, password);
	}
	
}
