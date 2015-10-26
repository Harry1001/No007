package driver;

import businessLogicService.LoginBLService;
import typeDefinition.Job;

public class LoginBL_Driver {

	public void driver(LoginBLService loginBLService){
		
		String id="141250001";
		String password="141250001";
		Job job=loginBLService.getPermission(id, password);
		if(job!=Job.NOTFOUND){
			System.out.println(job+" jump to relevant ui!");
		}
		else{
			System.out.println("not found!");
		}
	}
}
