package businessLogic.infobl.bl;

import dataService._RMI;
import dataService.infodataservice.StaffDataService;
import myexceptions.InfoBLException;
import po.infopo.StaffPO;
import vo.infovo.StaffVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import businessLogicService.infoblservice.StaffBLService;

/**
 * Created by Harry on 2015/11/15.
 */
public class StaffInfoBL implements StaffBLService {


    private ArrayList<StaffPO> staffPOs;
    private StaffDataService staffData;
    
    public StaffInfoBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url = "rmi://"+_RMI.getIP()+"/central_staff";
		this.staffData = (StaffDataService) Naming.lookup(url);
    }

    public ArrayList<StaffVO> getStaffList() throws RemoteException, SQLException{

        staffPOs = staffData.getList();
        ArrayList<StaffVO> staffVOs=new ArrayList<StaffVO>();

        for(StaffPO po:staffPOs){
            staffVOs.add(new StaffVO(po));
        }

       return staffVOs;
    }

    public void addStaff(StaffVO vo) throws RemoteException, InfoBLException, SQLException {
    	StaffPO po = new StaffPO(vo);
    	staffData.addItem(po);
    }

    public void deleteStaff( String id) throws RemoteException, SQLException {
        staffData.deleteItem(id);
    }

    public void modifyStaff(String id, StaffVO vo) throws RemoteException, InfoBLException, SQLException {
    	StaffPO po = new StaffPO(vo);
    	staffData.update(id, po);
    }

    public void addWorkFrequency(String staffID) throws InfoBLException, SQLException, RemoteException {
        staffData.addWorkFrequency(staffID);
    }

    public void refreshWorkFrequency(String staffID) throws InfoBLException, SQLException, RemoteException {
        staffData.refreshWorkFreqeuncy(staffID);
    }
}
