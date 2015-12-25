package businessLogicService.infoblservice;

import myexceptions.InfoBLException;
import vo.infovo.BankAccountVO;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/18.
 */
public interface BankAccountBLService {
    /**
     * 获取银行账户信息列表，用于期初建账
     * @return
     * @throws SQLException 
     */
    public ArrayList<BankAccountVO> getBankAccountList() throws RemoteException, SQLException;

    /**
     *
     * @throws InfoBLException, 表示RMI连接失败
     * @throws SQLException 
     */
    public void addBankAccount(BankAccountVO vo) throws InfoBLException, RemoteException, SQLException;

    /**
     * 界面层调用此方法请求在数据层中删除对应Info
     * @throws SQLException 
     */
    public void deleteBankAccount(String id) throws RemoteException, SQLException;

    /**
     *
     * @param id 需要改变余额的账户
     * @param change 余额改变值，正数表示收入，负数表示支出
     * @throws InfoBLException 如果余额不足可能会抛出该异常
     * @throws RemoteException
     * @throws SQLException
     */
    public void modifyBankAccount( String id, double change) throws InfoBLException, RemoteException, SQLException;
}
