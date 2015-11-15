package driver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataService.InfoDataService;
import po.infopo.InfoPO;
import po.infopo.StaffPO;
import typeDefinition.InfoType;
import typeDefinition.Job;
import typeDefinition.myTime;

public class InfoData_Driver {

	public void driver(InfoDataService infoDataService) throws RemoteException{
		
		ArrayList<InfoPO> info1=infoDataService.getList(InfoType.STAFF);
		ArrayList<InfoPO> info2=infoDataService.getList(InfoType.AGENCY);
		System.out.println(info1);
		System.out.println(info2);
		
		InfoPO item1=new StaffPO("141250003","王五","男",new myTime(1989,10,10),Job.COURIER,6000);
		infoDataService.addItem(item1);
		
		String id="141250003";
		infoDataService.deleteItem(InfoType.STAFF, id);
		
		InfoPO item2=new StaffPO("141250003","王五","男",new myTime(1989,10,10),Job.ACCOUNTANT,3000);
		infoDataService.update(id, item2);

	}
}
