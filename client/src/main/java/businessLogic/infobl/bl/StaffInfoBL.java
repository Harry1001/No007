package businessLogic.infobl.bl;

import businessLogicService.infoblservice.StaffBLService;
import data.InfoDataImpl;
import dataService.InfoDataService;
import myexceptions.InfoBLException;
import po.infopo.StaffPO;
import typeDefinition.InfoType;
import vo.infovo.InfoVO;
import vo.infovo.StaffVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/15.
 */
public class StaffInfoBL extends InfoBL{


    private ArrayList<StaffPO> staffPOs;

    public StaffInfoBL(){
        this(new InfoDataImpl());
    }
    public StaffInfoBL(InfoDataService infoData) {
        super(infoData);


    }

    public ArrayList<StaffVO> getStaffList() throws RemoteException{

        staffPOs=(ArrayList<StaffPO>)super.getList(InfoType.STAFF);
        ArrayList<StaffVO> staffVOs=new ArrayList<StaffVO>();

        for(StaffPO po:staffPOs){
            staffVOs.add(new StaffVO(po));
        }

       return staffVOs;
    }

    public void addStaff(StaffVO vo) throws RemoteException, InfoBLException {

            super.add(new StaffPO(vo));

    }

    public void deleteStaff( String id) throws RemoteException {
        super.delete(InfoType.STAFF, id);
    }

    public void modifyStaff(String id, StaffVO vo) throws RemoteException, InfoBLException {
       super.modify(id, new StaffPO(vo));

    }

}
