package businessLogic.loginbl;

import businessLogicService.loginblservice.LoginBLService;
import typeDefinition.Job;

public class LoginController implements LoginBLService{
	
	LoginBL loginBL=new LoginBL();

	public Job getPermission(String id, String password) {
		// TODO Auto-generated method stub
		return 	new LoginBL().getPermission(id, password);
	}

}
