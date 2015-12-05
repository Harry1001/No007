package businessLogic.infobl.controller;

import businessLogic.infobl.bl.TruckInfoBL;
import businessLogicService.infoblservice.TruckBLService;
import myexceptions.InfoBLException;
import vo.infovo.TruckVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/21.
 */
public class TruckBLController implements TruckBLService {

    TruckInfoBL truckInfoBL;
    
    public TruckBLController() throws MalformedURLException, RemoteException, NotBoundException {
    	this.truckInfoBL = new TruckInfoBL();
    }

    public ArrayList<TruckVO> getTruckList() throws RemoteException, SQLException {
        return truckInfoBL.getTruckList();
    }

    public void addTruck(TruckVO vo) throws InfoBLException, RemoteException, SQLException {
        truckInfoBL.addTruck(vo);
    }

    public void deleteTruck(String id) throws RemoteException, SQLException {
        truckInfoBL.deleteTruck(id);
    }

    public void modifyTruck(String id, TruckVO vo) throws InfoBLException, RemoteException, SQLException {
        truckInfoBL.modifyTruck(id, vo);
    }
}
