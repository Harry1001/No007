package businessLogic.infobl.bl;

import dataService._RMI;
import dataService.infodataservice.DriverDataService;
import myexceptions.InfoBLException;
import po.infopo.DriverPO;
import vo.infovo.DriverVO;
import vo.recordvo.RecordVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import blfactory.BLFactory;
import businessLogicService.infoblservice.DriverBLService;
import businessLogicService.recordblservice.RecordBLService;

/**
 * Created by Harry on 2015/11/16.
 */
public class DriverInfoBL implements DriverBLService {
    private ArrayList<DriverPO> driverPOs;
    private DriverDataService driverData;

    public DriverInfoBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url = "rmi://"+_RMI.getIP()+"/central_driver";
		this.driverData = (DriverDataService) Naming.lookup(url);
    }
    
    public ArrayList<DriverVO> getDriverList() throws RemoteException, SQLException{

        driverPOs = driverData.getList();
        ArrayList<DriverVO> driverVOs=new ArrayList<DriverVO>();

        for(DriverPO po:driverPOs){
            driverVOs.add(new DriverVO(po));
        }

        return driverVOs;
    }

    RecordBLService rb=BLFactory.getRecordBLService();
    
    public void addDriver(DriverVO vo) throws RemoteException, InfoBLException, SQLException {
       DriverPO po = new DriverPO(vo);
       driverData.addItem(po);
       RecordVO rvo=new RecordVO(new Date(),"营业厅业务员","添加司机信息");
   	   rb.add(rvo);
    }


    public void deleteDriver(String id) throws RemoteException, SQLException {
       driverData.deleteItem(id);
    }


    public void modifyDriver(String id, DriverVO vo) throws RemoteException, InfoBLException, SQLException {
    	DriverPO po = new DriverPO(vo);
    	driverData.update(id, po);
    }
}
