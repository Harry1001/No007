package businessLogic.infobl.controller;

import businessLogic.infobl.bl.DriverInfoBL;
import businessLogicService.infoblservice.DriverBLService;
import myexceptions.InfoBLException;
import vo.infovo.DriverVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/21.
 */
public class DriverBLController implements DriverBLService {

    private DriverInfoBL driverInfoBL;
    
    public DriverBLController() throws MalformedURLException, RemoteException, NotBoundException {
    	this.driverInfoBL = new DriverInfoBL();
    }

    public ArrayList<DriverVO> getDriverList() throws RemoteException, SQLException {
        return driverInfoBL.getDriverList();
    }

    public void addDriver(DriverVO vo) throws InfoBLException, RemoteException, SQLException {
        driverInfoBL.addDriver(vo);
    }

    public void deleteDriver(String id) throws RemoteException, SQLException {
        driverInfoBL.deleteDriver(id);
    }

    public void modifyDriver(String id, DriverVO vo) throws InfoBLException, RemoteException, SQLException {
        driverInfoBL.modifyDriver(id, vo);
    }
}
