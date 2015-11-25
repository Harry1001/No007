package businessLogicService.infoblservice;

import myexceptions.InfoBLException;
import vo.infovo.BankAccountVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/18.
 */
public interface BankAccountBLService {
    /**
     * 获取银行账户信息列表，用于期初建账
     * @return
     */
    public ArrayList<BankAccountVO> getBankAccountList() throws RemoteException;

    /**
     *
     * @throws InfoBLException, 表示RMI连接失败
     */
    public void addBankAccount(BankAccountVO vo) throws InfoBLException, RemoteException;

    /**
     * 界面层调用此方法请求在数据层中删除对应Info
     */
    public void deleteBankAccount(String id) throws RemoteException;

    /**
     * 将type类型的编号为id的信息修改为传入的vo中的信息，如果修改失败则返回false，成功返回true
     */
    public void modifyBankAccount( String id, BankAccountVO vo) throws InfoBLException, RemoteException;
}
