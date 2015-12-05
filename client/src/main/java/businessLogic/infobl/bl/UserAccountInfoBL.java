package businessLogic.infobl.bl;

import dataService.infodataservice.InfoDataService;
import myexceptions.InfoBLException;
import po.infopo.UserAccountPO;
import typeDefinition.InfoType;
import typeDefinition.Job;
import vo.infovo.UserAccountVO;
import vo.loginvo.LoginResultVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

import data.infodataimpl.InfoDataImpl;

/**
 * Created by Harry on 2015/11/16.
 */
public class UserAccountInfoBL extends InfoBL {

    private ArrayList<UserAccountPO> userAccountPOs;

    public UserAccountInfoBL(){
        this(new InfoDataImpl());
    }

    public UserAccountInfoBL(InfoDataService infoData) {

        super(infoData);
       // userAccountPOs=(ArrayList<UserAccountPO>)super.getList(InfoType.ACCOUNT);
    }


    public ArrayList<UserAccountVO> getUserAccountList() throws RemoteException {

        userAccountPOs=(ArrayList<UserAccountPO>)super.getList(InfoType.ACCOUNT);
        ArrayList<UserAccountVO> userAccountVOs=new ArrayList<UserAccountVO>();

        for(UserAccountPO po:userAccountPOs){
            userAccountVOs.add(new UserAccountVO(po));
        }
        return userAccountVOs;
    }


    public void addUserAccount(UserAccountVO vo) throws RemoteException, InfoBLException {
        super.add(new UserAccountPO(vo));
    }


    public void deleteUserAccount(String id) throws RemoteException {
        super.delete(InfoType.ACCOUNT, id);
    }


    public void modifyUserAccount(String id, UserAccountVO vo) throws RemoteException, InfoBLException {
        super.modify(id, new UserAccountPO(vo));
    }

    public LoginResultVO verifyPassword(String id, String password) throws RemoteException{
        //todo 密码验证应该在数据层执行，为了安全密码不可以传到逻辑层

        return new LoginResultVO(id, Job.COURIER, "张三");
    }

}
