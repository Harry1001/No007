package businessLogic.infobl.controller;

import businessLogic.infobl.bl.StaffInfoBL;
import businessLogicService.infoblservice.StaffBLService;
import myexceptions.InfoBLException;
import vo.infovo.StaffVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/21.
 */
public class StaffBLController implements StaffBLService {

    StaffInfoBL staffInfoBL;
    
    public StaffBLController() throws MalformedURLException, RemoteException, NotBoundException {
    	this.staffInfoBL = new StaffInfoBL();
    }

    public ArrayList<StaffVO> getStaffList() throws RemoteException, SQLException {
        return staffInfoBL.getStaffList();
    }

    public void addStaff(StaffVO vo) throws InfoBLException, RemoteException, SQLException {
        staffInfoBL.addStaff(vo);
    }

    public void deleteStaff(String id) throws RemoteException, SQLException {
        staffInfoBL.deleteStaff(id);
    }

    public void modifyStaff(String id, StaffVO vo) throws InfoBLException, RemoteException, SQLException {
        staffInfoBL.modifyStaff(id, vo);
    }

    public void addWorkFrequency(String staffID) throws InfoBLException, SQLException, RemoteException {
        staffInfoBL.addWorkFrequency(staffID);
    }

    public void refreshWorkFrequency(String staffID) throws InfoBLException, SQLException, RemoteException {
        staffInfoBL.refreshWorkFrequency(staffID);
    }
}
