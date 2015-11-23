package businessLogic.infobl.controller;

import businessLogic.infobl.bl.UserAccountInfoBL;
import businessLogicService.infoblservice.UserAccoutBLService;
import myexceptions.InfoBLException;
import typeDefinition.Job;
import vo.infovo.UserAccountVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/21.
 */
public class UserAccountBLController implements UserAccoutBLService {

    UserAccountInfoBL userAccountInfoBL=new UserAccountInfoBL();

    public ArrayList<UserAccountVO> getUserAccountList() throws RemoteException {
        return userAccountInfoBL.getUserAccountList();
    }

    public void addUserAccount(UserAccountVO vo) throws InfoBLException, RemoteException {
        userAccountInfoBL.addUserAccount(vo);
    }

    public void deleteUserAccount(String id) throws RemoteException {
        userAccountInfoBL.deleteUserAccount(id);
    }

    public void modifyUserAccount(String id, UserAccountVO vo) throws InfoBLException, RemoteException {
        userAccountInfoBL.modifyUserAccount(id, vo);
    }

    public Job verifyPassword(String id, String password) throws RemoteException {
        return userAccountInfoBL.verifyPassword(id, password);
    }
}
