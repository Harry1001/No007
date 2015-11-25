package presentation;

import blfactory.BLFactory;
import businessLogicService.infoblservice.StaffBLService;
import vo.infovo.StaffVO;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/24.
 */
public class StaffPanel extends JPanel {
    StaffBLService staffBLService=BLFactory.getStaffBLService();
    private ArrayList<StaffVO> staffVOs;
    public StaffPanel() throws RemoteException {

       staffVOs=staffBLService.getStaffList();//may throw RemoteException

       String [] names={"工号","姓名","性别","出生年月","职位"};

    }
}
