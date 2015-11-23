package businessLogic.infobl.controller;

import businessLogic.infobl.bl.BankAccountInfoBL;
import businessLogicService.infoblservice.BankAccountBLService;
import myexceptions.InfoBLException;
import vo.infovo.BankAccountVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/21.
 */
public class BankAccountBLController implements BankAccountBLService {

    private BankAccountInfoBL bankAccountInfoBL=new BankAccountInfoBL();

    public ArrayList<BankAccountVO> getBankAccountList() throws RemoteException {
        return bankAccountInfoBL.getBankAccountList();
    }

    public void addBankAccount(BankAccountVO vo) throws InfoBLException, RemoteException {
        bankAccountInfoBL.addBankAccount(vo);
    }

    public void deleteBankAccount(String id) throws RemoteException {
        bankAccountInfoBL.deleteBankAccount(id);
    }

    public void modifyBankAccount(String id, BankAccountVO vo) throws InfoBLException, RemoteException {
        bankAccountInfoBL.modifyBankAccout(id, vo);
    }
}
