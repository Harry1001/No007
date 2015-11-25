package businessLogic.infobl.bl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import dataService.InfoDataService;
import myexceptions.InfoBLException;
import po.infopo.InfoPO;
import typeDefinition.InfoType;

public abstract class InfoBL {
	
	
	InfoDataService infoData;
	

	/*
	 * constructor
	 */
	public InfoBL(InfoDataService infoData){
		this.infoData=infoData;
	}

	//public abstract ArrayList<? extends InfoVO> getInfoList();

	protected ArrayList<? extends InfoPO> getList(InfoType type) throws RemoteException{
		// TODO Auto-generated method stub
			return infoData.getList(type);
	}

	//public abstract boolean addInfo(InfoVO infoItem);

	protected void add(InfoPO infoItem) throws RemoteException, InfoBLException{
		// TODO Auto-generated method stub
			infoData.addItem(infoItem);
	}

	//public abstract void deleteInfo(String id);

	protected void delete(InfoType type, String id) throws RemoteException{
		// TODO Auto-generated method stub
		try{
			infoData.deleteItem(type,id);
		}catch (RemoteException e){
			System.out.println("delete info from data layer fail");
		}
	}

	//public abstract boolean modifyInfo(String id, InfoVO infoItem);

	protected void modify(String id, InfoPO infoItem) throws RemoteException, InfoBLException{
		// TODO Auto-generated method stub
		//try{
			infoData.update(id, infoItem);
			//return true;
		//}catch (RemoteException e){
		//	System.out.println("modify info at data layer fail");
		//	return false;
	//	}

	}



}
