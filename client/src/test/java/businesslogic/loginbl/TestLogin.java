package businesslogic.loginbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import businessLogic.loginbl.LoginBL;
import vo.loginvo.LoginInputVO;
import vo.loginvo.LoginResultVO;

public class TestLogin {

	public static void main(String[] args) {
		LoginBL loginBL = new LoginBL();
		try {
			LoginInputVO inputVO = new LoginInputVO("000000003", "000000003");
			LoginResultVO vo = loginBL.getPermission(inputVO);
			System.out.println(vo.getName());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
