package businessLogicService.infoblservice;

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
    public ArrayList<BankAccountVO> getInfoList();
}
