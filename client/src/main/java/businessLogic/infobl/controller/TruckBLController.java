package businessLogic.infobl.controller;

import businessLogic.infobl.bl.TruckInfoBL;
import businessLogicService.infoblservice.TruckBLService;
import myexceptions.InfoBLException;
import vo.infovo.TruckVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/21.
 */
public class TruckBLController implements TruckBLService {

    TruckInfoBL truckInfoBL=new TruckInfoBL();

    public ArrayList<TruckVO> getTruckList() throws RemoteException {
        return truckInfoBL.getTruckList();
    }

    public void addTruck(TruckVO vo) throws InfoBLException, RemoteException {
        truckInfoBL.addTruck(vo);
    }

    public void deleteTruck(String id) throws RemoteException {
        truckInfoBL.deleteTuck(id);
    }

    public void modifyTruck(String id, TruckVO vo) throws InfoBLException, RemoteException {
        truckInfoBL.modifyTruck(id, vo);
    }
}
