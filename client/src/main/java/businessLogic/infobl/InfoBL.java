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

public class InfoBL implements InfoBLService {
	
	
	private InfoDataService infoData;
	

	/*
	 * constructor
	 */
	public InfoBL(InfoDataService infoData){
		this.infoData=infoData;
	}

	public ArrayList<InfoVO> getInfoList(InfoType type) {
		// TODO Auto-generated method stub
		
		return null;
	}

	public boolean addInfo(InfoVO infoItem) {
		// TODO Auto-generated method stub
		return false;
	}

	public void deleteInfo(InfoType type, String id) {
		// TODO Auto-generated method stub

	}

	public boolean modifyInfo(InfoType type, String id, InfoVO infoItem) {
		// TODO Auto-generated method stub
		return false;
	}



}
