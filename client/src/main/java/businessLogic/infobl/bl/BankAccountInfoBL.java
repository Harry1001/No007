package businessLogic.infobl.bl;

import dataService.infodataservice.InfoDataService;
import myexceptions.InfoBLException;
import po.infopo.BankAccountPO;
import typeDefinition.InfoType;
import vo.infovo.BankAccountVO;
import java.rmi.RemoteException;
import java.util.ArrayList;

import data.infodataimpl.InfoDataImpl;

/**
 * Created by Harry on 2015/11/16.
 */
public class BankAccountInfoBL extends InfoBL {
    private ArrayList<BankAccountPO> bankAccountPOs;

    public BankAccountInfoBL(){
        this(new InfoDataImpl());
    }

    public BankAccountInfoBL(InfoDataService infoData) {
        super(infoData);
       // bankAccountPOs=(ArrayList<BankAccountPO>)super.getList(InfoType.BANKACCOUNT);
    }

    public ArrayList<BankAccountVO> getBankAccountList() throws RemoteException{

        bankAccountPOs=(ArrayList<BankAccountPO>)super.getList(InfoType.BANKACCOUNT);
        ArrayList<BankAccountVO> bankAccountVOs=new ArrayList<BankAccountVO>();

        for(BankAccountPO po:bankAccountPOs){
            bankAccountVOs.add(new BankAccountVO(po));
        }

        return bankAccountVOs;
    }


    public void addBankAccount(BankAccountVO vo) throws RemoteException, InfoBLException {

            super.add(new BankAccountPO(vo));

    }


    public void deleteBankAccount(String id) throws RemoteException {
        super.delete(InfoType.BANKACCOUNT, id);
    }

    public void modifyBankAccout(String id, BankAccountVO vo) throws RemoteException, InfoBLException {

       super.modify(id, new BankAccountPO(vo));
    }

}
