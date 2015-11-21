package businessLogic.infobl.controller;

import businessLogicService.infoblservice.AgencyBLService;
import myexceptions.InfoBLException;
import vo.infovo.AgencyVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/20.
 */
public class AgencyBLController implements AgencyBLService {


    public ArrayList<AgencyVO> getAgencyList() throws RemoteException{
        return null;
    }

    public void addAgency(AgencyVO vo) throws InfoBLException,RemoteException {

    }

    public void deleteAgency(String id) throws RemoteException {

    }

    public void modifyAgency(String id, AgencyVO vo) throws InfoBLException, RemoteException {

    }
}
