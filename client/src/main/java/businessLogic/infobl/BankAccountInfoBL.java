package businessLogic.infobl;

import data.InfoDataImpl;
import dataService.InfoDataService;
import po.infopo.BankAccountPO;
import typeDefinition.InfoType;
import vo.infovo.InfoVO;

import java.util.ArrayList;

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
        bankAccountPOs=(ArrayList<BankAccountPO>)super.getList(InfoType.BANKACCOUNT);
    }

    @Override
    public ArrayList<? extends InfoVO> getInfoList() {
        return null;
    }

    @Override
    public boolean addInfo(InfoVO infoItem) {
        return false;
    }

    @Override
    public void deleteInfo(String id) {

    }

    @Override
    public boolean modifyInfo(String id, InfoVO infoItem) {
        return false;
    }
}
