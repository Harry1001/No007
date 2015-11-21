package businessLogic.infobl.controller;

import businessLogicService.infoblservice.BankAccountBLService;
import myexceptions.InfoBLException;
import vo.infovo.BankAccountVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/21.
 */
public class BankAccountBLController implements BankAccountBLService {
    public ArrayList<BankAccountVO> getBankAccountList() throws InfoBLException {
        return null;
    }

    public void addBankAccount(BankAccountVO vo) throws InfoBLException {

    }

    public void deleteBankAccount(String id) throws InfoBLException {

    }

    public void modifyBankAccount(String id, BankAccountVO vo) throws InfoBLException {

    }
}
