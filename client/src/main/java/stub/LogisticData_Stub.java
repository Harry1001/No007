package stub;

import java.rmi.RemoteException;

import dataService.LogisticDataService;
import po.logisticpo.LogisticPO;
import typeDefinition.LogisticState;
import typeDefinition.myTime;

public class LogisticData_Stub implements LogisticDataService{

	/*
	 * 更新物流信息po
	 */
	public void update(LogisticPO po) throws RemoteException{
		
		System.out.println("update succeed!");
		
	}
	
	/*
	 * 根据num查找相应物流信息并返回，如果不存在则返回一个物流信息为“此单号不存在”的po
	 */
	public LogisticPO read(String num) throws RemoteException{
		
		LogisticPO po=new LogisticPO("0000000001",new myTime(),LogisticState.ASSTORE);
		return po;
		
	}
	
	/*
	 * 初始化物流信息持久化数据
	 */
	public void init() throws RemoteException{
		System.out.println("init succeed!");
		
	}
	
}
