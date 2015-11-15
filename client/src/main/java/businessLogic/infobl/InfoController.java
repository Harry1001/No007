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
    InfoDataService infoData ;

    public ArrayList<InfoVO> getInfoList(InfoType type) {
        return null;
    }

    public boolean addInfo(InfoVO infoItem) {
        return false;
    }

    public void deleteInfo(InfoType type, String id) {

    }

    public boolean modifyInfo(InfoType type, String id, InfoVO infoItem) {
        return false;
    }

    private void createInfoBL(InfoType type){
        switch (type){
           
        }
    }
}
