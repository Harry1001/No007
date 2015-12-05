package businessLogicService.infoblservice;

import myexceptions.InfoBLException;
import typeDefinition.Job;
import vo.infovo.UserAccountVO;
import vo.loginvo.LoginResultVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/18.
 */
public interface UserAccoutBLService {

    /**
     * 获取用户账户信息列表，用于期初建账
     * @return
     */
    public ArrayList<UserAccountVO> getUserAccountList() throws RemoteException;

    /**
     *
     * @throws InfoBLException, 表示RMI连接失败
     */
    public void addUserAccount(UserAccountVO vo) throws InfoBLException, RemoteException;

    /**
     * 界面层调用此方法请求在数据层中删除对应Info
     */
    public void deleteUserAccount(String id) throws RemoteException;

    /**
     * 将type类型的编号为id的信息修改为传入的vo中的信息，如果修改失败则返回false，成功返回true
     */
    public void modifyUserAccount( String id, UserAccountVO vo) throws InfoBLException, RemoteException;

    /**
     *
     * @param id
     * @param password
     * @return
     * @throws RemoteException
     */
    public LoginResultVO verifyPassword(String id, String password) throws RemoteException;
}
