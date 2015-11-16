package businessLogic.infobl;

import data.InfoDataImpl;
import dataService.InfoDataService;
import po.infopo.InfoPO;
import po.infopo.StaffPO;
import typeDefinition.InfoType;
import typeDefinition.Job;
import vo.infovo.StaffVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/15.
 */
public class StaffInfoBL extends InfoBL {

    ArrayList<StaffVO> staffVOs;

    public StaffInfoBL(){
        this(new InfoDataImpl());
    }
    public StaffInfoBL(InfoDataService infoData) {
        super(infoData);
    }

    public ArrayList<StaffVO> getStaffList(){
        staffVOs=new ArrayList<StaffVO>();
        try{
            ArrayList<InfoPO> staffPOs=infoData.getList(InfoType.STAFF);
            for(InfoPO po: staffPOs){
                staffVOs.add(new StaffVO((StaffPO)po));
            }
        }catch(RemoteException e){
            System.out.println("get staff list from data layer fail");
            return null;
        }
       return staffVOs;
    }


}
