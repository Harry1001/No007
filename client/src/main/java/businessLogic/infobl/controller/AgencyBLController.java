package businessLogic.infobl.controller;

import businessLogic.infobl.bl.AgencyInfoBL;
import businessLogicService.infoblservice.AgencyBLService;
import myexceptions.InfoBLException;
import vo.infovo.AgencyVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/20.
 */
public class AgencyBLController implements AgencyBLService {

    private AgencyInfoBL agencyInfoBL;
    
    public AgencyBLController() throws MalformedURLException, RemoteException, NotBoundException {
    	this.agencyInfoBL = new AgencyInfoBL();
    }

    public ArrayList<AgencyVO> getAgencyList() throws RemoteException, SQLException{
        return agencyInfoBL.getAgencyList();
    }

    public void addAgency(AgencyVO vo) throws InfoBLException,RemoteException, SQLException {
        agencyInfoBL.addAgency(vo);
    }

    public void deleteAgency(String id) throws RemoteException, SQLException {
        agencyInfoBL.deleteAgency(id);
    }

    public void modifyAgency(String id, AgencyVO vo) throws InfoBLException, RemoteException, SQLException {
        agencyInfoBL.modifyAgency(id, vo);
    }
    
	public String getAgengcy(String agencyID) throws RemoteException, SQLException{
		return agencyInfoBL.getAgengcy(agencyID);
	}
}
