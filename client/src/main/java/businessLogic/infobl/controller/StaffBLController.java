package businessLogic.infobl.controller;

import businessLogicService.infoblservice.StaffBLService;
import myexceptions.InfoBLException;
import vo.infovo.StaffVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/21.
 */
public class StaffBLController implements StaffBLService {
    public ArrayList<StaffVO> getStaffList() throws InfoBLException {
        return null;
    }

    public void addStaff(StaffVO vo) throws InfoBLException {

    }

    public void deleteStaff(String id) throws InfoBLException {

    }

    public void modifyStaff(String id, StaffVO vo) throws InfoBLException {

    }
}
