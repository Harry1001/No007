package businessLogic.loginbl;

import businessLogicService.loginblservice.LoginBLService;
import typeDefinition.Job;
import vo.loginvo.LoginResultVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class LoginController implements LoginBLService{
	
	LoginBL loginBL=new LoginBL();

	public LoginResultVO getPermission(String id, String password) throws RemoteException, MalformedURLException, NotBoundException {
		// TODO Auto-generated method stub
		return 	new LoginBL().getPermission(id, password);
	}

}
