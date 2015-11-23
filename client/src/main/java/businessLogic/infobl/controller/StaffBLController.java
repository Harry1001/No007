package businessLogic.infobl.controller;

import businessLogic.infobl.bl.StaffInfoBL;
import businessLogicService.infoblservice.StaffBLService;
import myexceptions.InfoBLException;
import vo.infovo.StaffVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/21.
 */
public class StaffBLController implements StaffBLService {

    StaffInfoBL staffInfoBL=new StaffInfoBL();

    public ArrayList<StaffVO> getStaffList() throws RemoteException {
        return staffInfoBL.getStaffList();
    }

    public void addStaff(StaffVO vo) throws InfoBLException, RemoteException {
        staffInfoBL.addStaff(vo);
    }

    public void deleteStaff(String id) throws RemoteException {
        staffInfoBL.deleteStaff(id);
    }

    public void modifyStaff(String id, StaffVO vo) throws InfoBLException, RemoteException {
        staffInfoBL.modifyStaff(id, vo);
    }
}
