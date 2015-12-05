package businessLogic.infobl.bl;

import dataService._RMI;
import dataService.infodataservice.BankAccountDataService;
import myexceptions.InfoBLException;
import po.infopo.BankAccountPO;
import vo.infovo.BankAccountVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import businessLogicService.infoblservice.BankAccountBLService;

/**
 * Created by Harry on 2015/11/16.
 */
public class BankAccountInfoBL implements BankAccountBLService{
    private ArrayList<BankAccountPO> bankAccountPOs;
    private BankAccountDataService bankAccountData;
    
    public BankAccountInfoBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url = "rmi://"+_RMI.getIP()+"/central_bankaccount";
		this.bankAccountData = (BankAccountDataService) Naming.lookup(url);
    }

    public ArrayList<BankAccountVO> getBankAccountList() throws RemoteException, SQLException{
        bankAccountPOs = bankAccountData.getList();
        ArrayList<BankAccountVO> bankAccountVOs=new ArrayList<BankAccountVO>();

        for(BankAccountPO po:bankAccountPOs){
            bankAccountVOs.add(new BankAccountVO(po));
        }

        return bankAccountVOs;
    }


    public void addBankAccount(BankAccountVO vo) throws RemoteException, InfoBLException, SQLException {
    	BankAccountPO bankAccountPO = new BankAccountPO(vo);
    	bankAccountData.addItem(bankAccountPO);
    }


    public void deleteBankAccount(String id) throws RemoteException, SQLException {
    	bankAccountData.deleteItem(id);
    }

	public void modifyBankAccount(String id, double change) throws InfoBLException, RemoteException, SQLException {
		bankAccountData.update(id, change);
	}

}
