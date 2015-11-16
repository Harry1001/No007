package businessLogic.infobl;

import data.InfoDataImpl;
import dataService.InfoDataService;
import po.infopo.TruckPO;
import typeDefinition.InfoType;
import vo.infovo.InfoVO;
import vo.infovo.TruckVO;

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
        //truckPOs=(ArrayList<TruckPO>)super.getList(InfoType.TRUCK);
    }

    @Override
    public ArrayList<? extends InfoVO> getInfoList() {

        truckPOs=(ArrayList<TruckPO>)super.getList(InfoType.TRUCK);
        ArrayList<TruckVO> truckVOs=new ArrayList<TruckVO>();

        for(TruckPO po:truckPOs){
            truckVOs.add(new TruckVO(po));
        }

        return truckVOs;
    }

    @Override
    public boolean addInfo(InfoVO infoItem) {
        TruckVO vo=(TruckVO)infoItem;
        if(!containsInfo(vo.getTruckID())){//如果不含新加的车辆信息
            return super.add(new TruckPO(vo));
        }

        //todo 需要在界面层提示
        System.out.println("已包含该车辆");
        return false;
    }

    @Override
    public void deleteInfo(String id) {
        super.delete(InfoType.TRUCK, id);
    }

    @Override
    public boolean modifyInfo(String id, InfoVO infoItem) {
        TruckVO vo=(TruckVO)infoItem;
        if(!vo.getTruckID().equals(id)){//修改了原来的编号
            if(containsInfo(vo.getTruckID())){
                System.out.println("已包含该车辆");
                return false;
            }
        }
        //机构编号没变
        deleteInfo(id);

        return addInfo(infoItem);
    }

    private boolean containsInfo(String id){
        for(TruckPO po:truckPOs){
            if(po.getTruckID().equals(id))
                return true;
        }
        return false;
    }
}
