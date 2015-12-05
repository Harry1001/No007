package businessLogic.infobl.controller;

import businessLogic.infobl.bl.BankAccountInfoBL;
import businessLogicService.infoblservice.BankAccountBLService;
import myexceptions.InfoBLException;
import vo.infovo.BankAccountVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/21.
 */
public class BankAccountBLController implements BankAccountBLService {

    private BankAccountInfoBL bankAccountInfoBL;
    
    public BankAccountBLController() throws MalformedURLException, RemoteException, NotBoundException {
    	this.bankAccountInfoBL = new BankAccountInfoBL();
    }

    public ArrayList<BankAccountVO> getBankAccountList() throws RemoteException, SQLException {
        return bankAccountInfoBL.getBankAccountList();
    }

    public void addBankAccount(BankAccountVO vo) throws InfoBLException, RemoteException, SQLException {
        bankAccountInfoBL.addBankAccount(vo);
    }

    public void deleteBankAccount(String id) throws RemoteException, SQLException {
        bankAccountInfoBL.deleteBankAccount(id);
    }

    public void modifyBankAccount(String id, double change) throws InfoBLException, RemoteException, SQLException {
        bankAccountInfoBL.modifyBankAccount(id, change);
    }
}
