package businessLogic.infobl.bl;

import businessLogicService.infoblservice.DriverBLService;
import data.InfoDataImpl;
import dataService.InfoDataService;
import po.infopo.DriverPO;
import typeDefinition.InfoType;
import vo.infovo.DriverVO;
import vo.infovo.InfoVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/16.
 */
public class DriverInfoBL extends InfoBL implements DriverBLService {
    private ArrayList<DriverPO> driverPOs;

    public DriverInfoBL(){
        this(new InfoDataImpl());
    }

    public DriverInfoBL(InfoDataService infoData) {
        super(infoData);
       // driverPOs=(ArrayList<DriverPO>)super.getList(InfoType.DRIVER);
    }

    @Override
    public ArrayList<DriverVO> getInfoList() {

        driverPOs=(ArrayList<DriverPO>)super.getList(InfoType.DRIVER);
        ArrayList<DriverVO> driverVOs=new ArrayList<DriverVO>();

        for(DriverPO po:driverPOs){
            driverVOs.add(new DriverVO(po));
        }

        return driverVOs;
    }

    @Override
    public boolean addInfo(InfoVO infoItem) {

       DriverVO vo=(DriverVO)infoItem;
        if(!containsInfo(vo.getDriverID())){//如果不含新加的员工
            return super.add(new DriverPO(vo));
        }

        //todo 需要在界面层提示
        System.out.println("已包含该员工");
        return false;
    }

    @Override
    public void deleteInfo(String id) {
        super.delete(InfoType.DRIVER, id);
    }

    @Override
    public boolean modifyInfo(String id, InfoVO infoItem) {

        DriverVO vo=(DriverVO)infoItem;
        if(!vo.getDriverID().equals(id)){//修改了原来的工号
            if(containsInfo(vo.getDriverID())){
                System.out.println("已包含该员工");
                return false;
            }
        }
        //工号没变
        deleteInfo(id);

        return addInfo(infoItem);
    }

    private boolean containsInfo(String id){
        for(DriverPO po:driverPOs){
            if(id.equals(po.getDriverID()))
                return true;
        }
        return false;
    }
}
