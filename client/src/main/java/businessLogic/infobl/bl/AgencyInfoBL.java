package businessLogic.infobl.bl;

import myexceptions.InfoBLException;
import po.infopo.AgencyPO;
import vo.infovo.AgencyVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import businessLogicService.infoblservice.AgencyBLService;
import dataService._RMI;
import dataService.infodataservice.AgencyDataService;

/**
 * Created by Harry on 2015/11/16.
 * Updated by Han Xiao on 2015/12/5.
 */
public class AgencyInfoBL implements AgencyBLService {

    private ArrayList<AgencyPO> agencyPOs;
    private AgencyDataService agencyData;
    
    public AgencyInfoBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url = "rmi://"+_RMI.getIP()+"/central_agency";
		this.agencyData = (AgencyDataService) Naming.lookup(url);
    }

    public ArrayList<AgencyVO> getAgencyList() throws RemoteException, SQLException{

        agencyPOs = agencyData.getList();
        ArrayList<AgencyVO> agencyVOs=new ArrayList<AgencyVO>();

        for(AgencyPO po:agencyPOs){
            agencyVOs.add(new AgencyVO(po));
        }
        return agencyVOs;
    }

    public void addAgency(AgencyVO vo) throws RemoteException, InfoBLException, SQLException{
    	AgencyPO po = new AgencyPO(vo);
    	agencyData.addItem(po);
    }


    public void deleteAgency(String id) throws RemoteException, SQLException{
    	agencyData.deleteItem(id);
    }


    public void modifyAgency(String id, AgencyVO vo) throws RemoteException, InfoBLException, SQLException{
    	AgencyPO po = new AgencyPO(vo);
    	agencyData.update(id, po);
    }


}
