package businessLogic.infobl.bl;

import dataService._RMI;
import dataService.infodataservice.UserAccountDataService;
import myexceptions.InfoBLException;
import po.infopo.UserAccountPO;
import vo.infovo.UserAccountVO;
import vo.loginvo.LoginInputVO;
import vo.loginvo.LoginResultVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import businessLogicService.infoblservice.UserAccoutBLService;

/**
 * Created by Harry on 2015/11/16.
 */
public class UserAccountInfoBL implements UserAccoutBLService {

    private ArrayList<UserAccountPO> userAccountPOs;
    private UserAccountDataService userAccountData;
    
    public UserAccountInfoBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url = "rmi://"+_RMI.getIP()+"/central_useraccount";
		this.userAccountData = (UserAccountDataService) Naming.lookup(url);
    }

    public ArrayList<UserAccountVO> getUserAccountList() throws RemoteException, SQLException {

        userAccountPOs = userAccountData.getList();
        ArrayList<UserAccountVO> userAccountVOs=new ArrayList<UserAccountVO>();

        for(UserAccountPO po:userAccountPOs){
            userAccountVOs.add(new UserAccountVO(po));
        }
        return userAccountVOs;
    }

    public void addUserAccount(UserAccountVO vo) throws RemoteException, InfoBLException, SQLException {
    	UserAccountPO item = new UserAccountPO(vo);
    	userAccountData.addItem(item);
    }
    
    public void deleteUserAccount(String id) throws RemoteException, SQLException {
    	userAccountData.deleteItem(id);
    }


    public void modifyUserAccount(String id, UserAccountVO vo) throws RemoteException, InfoBLException, SQLException {
        UserAccountPO item = new UserAccountPO(vo);
    	userAccountData.update(id, item);
    }

    public LoginResultVO verifyPassword(LoginInputVO inputVO) throws RemoteException {
        //todo 密码验证应该在数据层执行，为了安全密码不可以传到逻辑层
    	LoginResultVO vo = userAccountData.verify(inputVO);
    	return vo;
    }
   
}
