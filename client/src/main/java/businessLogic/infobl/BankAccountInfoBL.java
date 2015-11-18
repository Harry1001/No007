package businessLogic.infobl;

import businessLogicService.infoblservice.GetBankAccountInfoBLService;
import data.InfoDataImpl;
import dataService.InfoDataService;
import po.infopo.BankAccountPO;
import typeDefinition.InfoType;
import vo.infovo.BankAccountVO;
import vo.infovo.InfoVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/16.
 */
public class BankAccountInfoBL extends InfoBL implements GetBankAccountInfoBLService {
    private ArrayList<BankAccountPO> bankAccountPOs;

    public BankAccountInfoBL(){
        this(new InfoDataImpl());
    }

    public BankAccountInfoBL(InfoDataService infoData) {
        super(infoData);
       // bankAccountPOs=(ArrayList<BankAccountPO>)super.getList(InfoType.BANKACCOUNT);
    }

    @Override
    public ArrayList<BankAccountVO> getInfoList() {

        bankAccountPOs=(ArrayList<BankAccountPO>)super.getList(InfoType.BANKACCOUNT);
        ArrayList<BankAccountVO> bankAccountVOs=new ArrayList<BankAccountVO>();

        for(BankAccountPO po:bankAccountPOs){
            bankAccountVOs.add(new BankAccountVO(po));
        }

        return bankAccountVOs;
    }

    @Override
    public boolean addInfo(InfoVO infoItem) {

        BankAccountVO vo=(BankAccountVO)infoItem;
        if(!containsInfo(vo.getAccountUser())){//如果不含新加的员工
            return super.add(new BankAccountPO(vo));
        }

        //todo 需要在界面层提示
        System.out.println("已包含该银行账户");
        return false;
    }

    @Override
    public void deleteInfo(String id) {
        super.delete(InfoType.BANKACCOUNT, id);
    }

    @Override
    public boolean modifyInfo(String id, InfoVO infoItem) {

        BankAccountVO vo=(BankAccountVO)infoItem;
        if(!vo.getAccountUser().equals(id)){//修改了原来的编号
            if(containsInfo(vo.getAccountUser())){
                System.out.println("已包含该银行账户");
                return false;
            }
        }
        //机构编号没变
        deleteInfo(id);

        return addInfo(infoItem);
    }

    private boolean containsInfo(String id){
        for(BankAccountPO po:bankAccountPOs){
            if(po.getAccountUser().equals(id))
                return true;
        }
        return false;
    }
}
