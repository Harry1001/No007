package businessLogic.infobl.controller;

import businessLogic.infobl.bl.DriverInfoBL;
import businessLogicService.infoblservice.DriverBLService;
import myexceptions.InfoBLException;
import vo.infovo.DriverVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/21.
 */
public class DriverBLController implements DriverBLService {

    private DriverInfoBL driverInfoBL=new DriverInfoBL();

    public ArrayList<DriverVO> getDriverList() throws RemoteException {
        return driverInfoBL.getDriverList();
    }

    public void addDriver(DriverVO vo) throws InfoBLException, RemoteException {
        driverInfoBL.addDriver(vo);
    }

    public void deleteDriver(String id) throws RemoteException {
        driverInfoBL.deleteDriver(id);
    }

    public void modifyDriver(String id, DriverVO vo) throws InfoBLException, RemoteException {
        driverInfoBL.modifyDriver(id, vo);
    }
}
