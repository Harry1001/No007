package driver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataService.InfoDataService;
import po.InfoPO;
import typeDefinition.InfoType;

public class InfoData_Driver {

	public void driver(InfoDataService infoDataService) throws RemoteException{
		
		ArrayList<InfoPO> info1=infoDataService.getList(InfoType.STAFF);
		ArrayList<InfoPO> info2=infoDataService.getList(InfoType.AGENCY);

	}
}
