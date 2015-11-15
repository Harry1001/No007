package stub;

import businessLogicService.loginblservice.LoginBLService;
import typeDefinition.Job;

public class LoginBL_Stub implements LoginBLService{

	public Job getPermission(String id,String password){
		
		return Job.STROESALSMAN;
		
	}

	public void skip() {
		// TODO Auto-generated method stub
		
	}

}
