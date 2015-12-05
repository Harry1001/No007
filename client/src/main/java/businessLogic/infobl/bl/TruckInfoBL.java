package businessLogic.infobl.bl;

import myexceptions.InfoBLException;
import po.infopo.TruckPO;
import vo.infovo.TruckVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import businessLogicService.infoblservice.TruckBLService;
import dataService._RMI;
import dataService.infodataservice.TruckDataService;

/**
 * Created by Harry on 2015/11/16.
 */
public class TruckInfoBL implements TruckBLService{
    private ArrayList<TruckPO> truckPOs;
    private TruckDataService truckData;
    
    public TruckInfoBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url = "rmi://"+_RMI.getIP()+"/central_truck";
		this.truckData = (TruckDataService) Naming.lookup(url);
    }

    public ArrayList<TruckVO> getTruckList() throws RemoteException, SQLException {

        truckPOs = truckData.getList();
        ArrayList<TruckVO> truckVOs=new ArrayList<TruckVO>();

        for(TruckPO po:truckPOs){
            truckVOs.add(new TruckVO(po));
        }

        return truckVOs;
    }

    public void addTruck(TruckVO vo) throws RemoteException, InfoBLException, SQLException {
    	TruckPO po = new TruckPO(vo);
    	truckData.addItem(po);
    }

    public void deleteTruck(String id) throws RemoteException, SQLException {
        truckData.deleteItem(id);
    }


    public void modifyTruck(String id, TruckVO vo) throws RemoteException, InfoBLException, SQLException {
    	TruckPO item = new TruckPO(vo);
    	truckData.update(id, item);
    }
}
