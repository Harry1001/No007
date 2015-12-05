package businessLogic.loginbl;

import businessLogicService.loginblservice.LoginBLService;
import vo.loginvo.LoginResultVO;

import java.rmi.RemoteException;

public class LoginController implements LoginBLService{
	
	LoginBL loginBL=new LoginBL();

	public LoginResultVO getPermission(String id, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return 	new LoginBL().getPermission(id, password);
	}

}
