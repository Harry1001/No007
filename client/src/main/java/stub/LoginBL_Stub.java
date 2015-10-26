package stub;

import businessLogicService.LoginBLService;
import typeDefinition.Job;

public class LoginBL_Stub implements LoginBLService{

	public Job getPermission(String id,String password){
		
		return Job.STROESALSMAN;
		
	}

}
