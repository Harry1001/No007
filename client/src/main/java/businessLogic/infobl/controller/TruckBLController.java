package businessLogic.infobl.controller;

import businessLogicService.infoblservice.TruckBLService;
import myexceptions.InfoBLException;
import vo.infovo.TruckVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/21.
 */
public class TruckBLController implements TruckBLService {
    public ArrayList<TruckVO> getTruckList() throws InfoBLException {
        return null;
    }

    public void addTruck(TruckVO vo) throws InfoBLException {

    }

    public void deleteTruck(String id) throws InfoBLException {

    }

    public void modifyTruck(String id, TruckVO vo) throws InfoBLException {

    }
}
