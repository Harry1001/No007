package businessLogic.infobl.controller;

import businessLogic.infobl.bl.UserAccountInfoBL;
import businessLogicService.infoblservice.UserAccoutBLService;
import myexceptions.InfoBLException;
import typeDefinition.Job;
import vo.infovo.UserAccountVO;
import vo.loginvo.LoginResultVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/21.
 */
public class UserAccountBLController implements UserAccoutBLService {

    UserAccountInfoBL userAccountInfoBL;
    
    public UserAccountBLController() throws MalformedURLException, RemoteException, NotBoundException {
    	this.userAccountInfoBL = new UserAccountInfoBL();
    }

    public ArrayList<UserAccountVO> getUserAccountList() throws RemoteException, SQLException {
        return userAccountInfoBL.getUserAccountList();
    }

    public void addUserAccount(UserAccountVO vo) throws InfoBLException, RemoteException, SQLException {
        userAccountInfoBL.addUserAccount(vo);
    }

    public void deleteUserAccount(String id) throws RemoteException, SQLException {
        userAccountInfoBL.deleteUserAccount(id);
    }

    public void modifyUserAccount(String id, UserAccountVO vo) throws InfoBLException, RemoteException, SQLException {
        userAccountInfoBL.modifyUserAccount(id, vo);
    }

    public LoginResultVO verifyPassword(String id, String password) throws RemoteException {
        return userAccountInfoBL.verifyPassword(id, password);
    }
}
