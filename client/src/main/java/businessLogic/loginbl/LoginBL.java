package businessLogic.loginbl;

import businessLogic.infobl.UserAccountInfoBL;
import businessLogicService.loginblservice.LoginBLService;
import typeDefinition.Job;

public class LoginBL implements LoginBLService{

	public Job getPermission(String id, String password) {
		// TODO Auto-generated method stub
		UserAccountInfoBL useraccountinfobl=new UserAccountInfoBL();
		return 	useraccountinfobl.verifyPassword(id, password);
	}
	
}
