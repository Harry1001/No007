package businessLogic.infobl;

import data.InfoDataImpl;
import dataService.InfoDataService;
import po.infopo.DriverPO;
import typeDefinition.InfoType;
import vo.infovo.InfoVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/16.
 */
public class DriverInfoBL extends InfoBL {
    private ArrayList<DriverPO> driverPOs;

    public DriverInfoBL(){
        this(new InfoDataImpl());
    }

    public DriverInfoBL(InfoDataService infoData) {
        super(infoData);
        driverPOs=(ArrayList<DriverPO>)super.getList(InfoType.DRIVER);
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
