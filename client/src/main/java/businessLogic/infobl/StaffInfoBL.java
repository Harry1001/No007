package businessLogic.infobl;

import data.InfoDataImpl;
import dataService.InfoDataService;
import po.infopo.InfoPO;
import po.infopo.StaffPO;
import typeDefinition.InfoType;
import typeDefinition.Job;
import vo.infovo.InfoVO;
import vo.infovo.StaffVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/15.
 */
public class StaffInfoBL extends InfoBL {


    private ArrayList<StaffPO> staffPOs;

    public StaffInfoBL(){
        this(new InfoDataImpl());
    }
    public StaffInfoBL(InfoDataService infoData) {
        super(infoData);

        //只要创建该bl就先从data层读取员工信息列表
        staffPOs=(ArrayList<StaffPO>)super.getList(InfoType.STAFF);
    }

    //@override
    public ArrayList<StaffVO> getInfoList(){
        ArrayList<StaffVO> staffVOs=new ArrayList<StaffVO>();

        for(StaffPO po:staffPOs){
            staffVOs.add(new StaffVO(po));
        }

       return staffVOs;
    }

    public boolean addInfo(InfoVO o){
        StaffVO vo=(StaffVO)o;
        if(!containsInfo(vo.getStaffID())){//如果不含新加的员工
            return super.add(new StaffPO(vo));
        }

        //todo 需要在界面层提示
        System.out.println("已包含该员工");
        return false;
    }

    public void deleteInfo( String id){
        super.delete(InfoType.STAFF, id);
    }

    public boolean modifyInfo(String id, InfoVO infoItem){
        StaffVO vo=(StaffVO)infoItem;
        if(!vo.getStaffID().equals(id)){//修改了原来的工号
            if(containsInfo(vo.getStaffID())){
                System.out.println("已包含该员工");
                return false;
            }
        }
        //工号没变
        deleteInfo(id);

        return addInfo(infoItem);

    }

    //私有方法，仅自己使用
    private boolean containsInfo(String id){
        for(StaffPO po:staffPOs){
            if(id.equals(po.getStaffID()))
                return true;
        }
        return false;
    }

}
