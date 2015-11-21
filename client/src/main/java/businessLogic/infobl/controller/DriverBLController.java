package businessLogic.infobl.controller;

import businessLogicService.infoblservice.DriverBLService;
import myexceptions.InfoBLException;
import vo.infovo.DriverVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/21.
 */
public class DriverBLController implements DriverBLService {
    public ArrayList<DriverVO> getDriverList() throws InfoBLException {
        return null;
    }

    public void addDriver(DriverVO vo) throws InfoBLException {

    }

    public void deleteDriver(String id) throws InfoBLException {

    }

    public void modifyDriver(String id, DriverVO vo) throws InfoBLException {

    }
}
