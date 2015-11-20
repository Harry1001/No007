package businessLogicService.infoblservice;

import myexceptions.InfoBLException;
import vo.infovo.AgencyVO;
import vo.infovo.BankAccountVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/18.
 */
public interface GetBankAccountInfoBLService {
    /**
     * 获取银行账户信息列表，用于期初建账
     * @return
     */
    public ArrayList<BankAccountVO> getBankAccountList();

    /**
     * 界面层要求添加一个机构Info
     * 添加成功则返回true并让数据层更新相关持久化数据并通知更新系统日志，添加失败则返回false
     * @param
     * @return 机构信息列表
     * @throws InfoBLException, 表示RMI连接失败
     */
    public void addBankAccount(BankAccountVO vo) throws InfoBLException;

    /**
     * 界面层调用此方法请求在数据层中删除对应Info
     */
    public void deleteBankAccount(String id) throws InfoBLException;

    /**
     * 将type类型的编号为id的信息修改为传入的vo中的信息，如果修改失败则返回false，成功返回true
     */
    public void modifyBankAccount( String id, AgencyVO agencyItem) throws InfoBLException;
}
