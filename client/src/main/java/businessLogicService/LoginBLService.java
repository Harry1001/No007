package businessLogicService;

import typeDefinition.Job;

public interface LoginBLService {

	public Job getPermission(String id,String password);
	
}
