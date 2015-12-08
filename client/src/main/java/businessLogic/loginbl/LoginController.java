package businessLogic.loginbl;

import businessLogicService.loginblservice.LoginBLService;
import vo.loginvo.LoginInputVO;
import vo.loginvo.LoginResultVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class LoginController implements LoginBLService{
	
	LoginBL loginBL=new LoginBL();

	public LoginResultVO getPermission(LoginInputVO vo) throws RemoteException, MalformedURLException, NotBoundException {
		return 	new LoginBL().getPermission(vo);
	}

}
