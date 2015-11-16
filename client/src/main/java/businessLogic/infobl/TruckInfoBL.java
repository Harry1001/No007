package businessLogic.infobl;

import data.InfoDataImpl;
import dataService.InfoDataService;
import po.infopo.TruckPO;
import typeDefinition.InfoType;
import vo.infovo.InfoVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/16.
 */
public class TruckInfoBL extends InfoBL {
    private ArrayList<TruckPO> truckPOs;

    public TruckInfoBL(){
        this(new InfoDataImpl());
    }

    public TruckInfoBL(InfoDataService infoData) {
        super(infoData);
        truckPOs=(ArrayList<TruckPO>)super.getList(InfoType.TRUCK);
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
