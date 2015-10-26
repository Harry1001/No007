package stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataService.InfoDataService;
import po.AgencyPO;
import po.InfoPO;
import po.StaffPO;
import typeDefinition.InfoType;
import typeDefinition.Job;
import typeDefinition.myTime;

public class InfoData_Stub implements InfoDataService{

	/**
	 * 按照type类型返回对应Info列表
	 */
	public ArrayList<InfoPO> getList(InfoType type) throws RemoteException{
		
		ArrayList<InfoPO> info=new ArrayList<InfoPO>();
		InfoPO po1=new StaffPO("141250001","张三","男",new myTime(1990,10,10),Job.STROESALSMAN,3000);
		InfoPO po2=new StaffPO("141250002","李四","男",new myTime(1990,2,22),Job.STROESALSMAN,3000);
		InfoPO po3=new AgencyPO("栖霞","营业厅","025000000","南京市",200,8000);
		InfoPO po4=new AgencyPO("朝阳","中转中心","022000000","北京市",300,10000);

		if(type==InfoType.STAFF){
			info.add(po1);
			info.add(po2);
		}
		else if(type==InfoType.AGENCY){
			info.add(po3);
			info.add(po4);
		}
		return info;
		
	}
	
	/**
	 * 在持久化数据中增加一个po条目
	 */
	public void addItem(InfoPO item) throws RemoteException{
		System.out.println("已添加！");
	}
	
	/**
	 * 在持久化数据中删除一个po
	 */
	public void deleteItem(InfoType type, String id) throws RemoteException{
		System.out.println("已删除！");
	}
	
	/**
	 * 在持久化数据中更新一个po
	 */
	public void update(String id, InfoPO item) throws RemoteException{
		
	}
	
}
