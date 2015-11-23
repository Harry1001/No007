package businessLogic.infobl.bl;

import businessLogicService.infoblservice.TruckBLService;
import data.InfoDataImpl;
import dataService.InfoDataService;
import myexceptions.InfoBLException;
import po.infopo.TruckPO;
import typeDefinition.InfoType;
import vo.infovo.InfoVO;
import vo.infovo.TruckVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/16.
 */
public class TruckInfoBL extends InfoBL{
    private ArrayList<TruckPO> truckPOs;

    public TruckInfoBL(){
        this(new InfoDataImpl());
    }

    public TruckInfoBL(InfoDataService infoData) {
        super(infoData);
        //truckPOs=(ArrayList<TruckPO>)super.getList(InfoType.TRUCK);
    }

    public ArrayList<TruckVO> getTruckList() throws RemoteException {

        truckPOs=(ArrayList<TruckPO>)super.getList(InfoType.TRUCK);
        ArrayList<TruckVO> truckVOs=new ArrayList<TruckVO>();

        for(TruckPO po:truckPOs){
            truckVOs.add(new TruckVO(po));
        }

        return truckVOs;
    }

    public void addTruck(TruckVO vo) throws RemoteException, InfoBLException {
       super.add(new TruckPO(vo));
    }

    public void deleteTuck(String id) throws RemoteException {
        super.delete(InfoType.TRUCK, id);
    }


    public void modifyTruck(String id, TruckVO vo) throws RemoteException, InfoBLException {

       super.modify(id, new TruckPO(vo));
    }

}
