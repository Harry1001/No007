package businessLogic.infobl.bl;

import data.InfoDataImpl;
import dataService.InfoDataService;
import myexceptions.InfoBLException;
import po.infopo.DriverPO;
import typeDefinition.InfoType;
import vo.infovo.DriverVO;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/16.
 */
public class DriverInfoBL extends InfoBL{
    private ArrayList<DriverPO> driverPOs;

    public DriverInfoBL(){
        this(new InfoDataImpl());
    }

    public DriverInfoBL(InfoDataService infoData) {
        super(infoData);
       // driverPOs=(ArrayList<DriverPO>)super.getList(InfoType.DRIVER);
    }


    public ArrayList<DriverVO> getDriverList() throws RemoteException{

        driverPOs=(ArrayList<DriverPO>)super.getList(InfoType.DRIVER);
        ArrayList<DriverVO> driverVOs=new ArrayList<DriverVO>();

        for(DriverPO po:driverPOs){
            driverVOs.add(new DriverVO(po));
        }

        return driverVOs;
    }

    public void addDriver(DriverVO vo) throws RemoteException, InfoBLException {
       super.add(new DriverPO(vo));
    }


    public void deleteDriver(String id) throws RemoteException {
        super.delete(InfoType.DRIVER, id);
    }


    public void modifyDriver(String id, DriverVO vo) throws RemoteException, InfoBLException {

        super.modify(id, new DriverPO(vo));
    }


}
