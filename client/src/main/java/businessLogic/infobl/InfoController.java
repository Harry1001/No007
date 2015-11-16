package businessLogic.infobl;

import businessLogicService.infoblservice.InfoBLService;
import dataService.InfoDataService;
import typeDefinition.InfoType;
import vo.infovo.InfoVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/15.
 */
public class InfoController implements InfoBLService {

    InfoBL infoBL;
    //InfoDataService infoData ;

    public InfoController(InfoType type){
        createInfoBL(type);
    }

    public ArrayList<? extends InfoVO> getInfoList() {

        return infoBL.getInfoList();
    }

    public boolean addInfo(InfoVO infoItem) {

        return infoBL.addInfo(infoItem);
    }

    public void deleteInfo(String id) {
        infoBL.deleteInfo(id);
    }

    public boolean modifyInfo(String id, InfoVO infoItem) {

        return infoBL.modifyInfo(id, infoItem);
    }

    private void createInfoBL(InfoType type){
        switch (type){
            case STAFF:infoBL=new StaffInfoBL();break;
            //todo
            case AGENCY:infoBL=new AgencyInfoBL();break;
            case TRUCK:infoBL=new TruckInfoBL();break;
            case DRIVER:infoBL=new DriverInfoBL();break;
            case BANKACCOUNT:infoBL=new BankAccountInfoBL();break;
            case ACCOUNT:infoBL=new UserAccountInfoBL();break;
        }
    }
}
