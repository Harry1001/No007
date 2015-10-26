package driver;

import java.rmi.RemoteException;

import dataService.LogisticDataService;
import po.LogisticPO;
import typeDefinition.LogisticState;
import typeDefinition.myTime;

public class LogisticData_Driver {

	public void drive(LogisticDataService logisticDataService) throws RemoteException{
		
		LogisticPO po1=new LogisticPO("0000000001",new myTime(),LogisticState.ASSTORE);
		logisticDataService.update(po1);
		
		String num="0000000001";
		LogisticPO po2=logisticDataService.read(num);
		System.out.println(po2.getOrderNum()+":"+po2.getArrivalTime()+" "+po2.getState());

		logisticDataService.init();
		
	}
}
