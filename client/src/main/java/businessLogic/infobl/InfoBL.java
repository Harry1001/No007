package businessLogic.infobl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;

import businessLogicService.infoblservice.InfoBLService;
import dataService.InfoDataService;
import po.infopo.InfoPO;
import typeDefinition.InfoType;
import typeDefinition.Job;
import vo.infovo.InfoVO;

public abstract class InfoBL {
	
	
	InfoDataService infoData;
	

	/*
	 * constructor
	 */
	public InfoBL(InfoDataService infoData){
		this.infoData=infoData;
	}

	public abstract ArrayList<? extends InfoVO> getInfoList();

	protected ArrayList<? extends InfoPO> getList(InfoType type) {
		// TODO Auto-generated method stub
		try{
			return infoData.getList(type);
		}catch (RemoteException e){
			System.out.println("get info list from data layer fail");
			return null;
		}

	}

	public abstract boolean addInfo(InfoVO infoItem);

	protected boolean add(InfoPO infoItem) {
		// TODO Auto-generated method stub
		try{
			infoData.addItem(infoItem);
			return true;
		}catch (RemoteException e){
			System.out.println("add info to data layer fail");
			return false;
		}
	}

	public abstract void deleteInfo(String id);

	protected void delete(InfoType type, String id) {
		// TODO Auto-generated method stub
		try{
			infoData.deleteItem(type,id);
		}catch (RemoteException e){
			System.out.println("delete info from data layer fail");
		}
	}

	public abstract boolean modifyInfo(String id, InfoVO infoItem);

	protected boolean modify(String id, InfoPO infoItem) {
		// TODO Auto-generated method stub
		try{
			infoData.update(id, infoItem);
			return true;
		}catch (RemoteException e){
			System.out.println("modify info at data layer fail");
			return false;
		}

	}



}
