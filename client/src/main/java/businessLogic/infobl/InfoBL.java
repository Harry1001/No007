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
	
	private ArrayList<? extends InfoPO> staffPOLsit;
	private ArrayList<? extends InfoPO> agencyPOLsit;
	//待补全！！！！！！！！！！！！！！！！
	
	
	/*
	 * constructor
	 */
	public InfoBL(InfoDataService infoData){
		this.infoData=infoData;
	}
	
	/*
	 * 生成InfoBLIm对象时就需要从数据层载入info数据，载入失败则说明网络有问题，此时展示曾应该给出提示
	 */
	public boolean initializ(){
		try {
			//此处要载入所有infoPO的列表
			staffPOLsit=infoData.getList(InfoType.STAFF);
			agencyPOLsit=infoData.getList(InfoType.AGENCY);
			//待补全！！！！！！！！！
			
			return true;
		} catch (RemoteException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	public ArrayList<InfoVO> getInfoList(InfoType type) {
		// TODO Auto-generated method stub
		
		return null;
	}

	public boolean addInfo(InfoVO infoItem) {
		// TODO Auto-generated method stub
		return false;
	}

	public void deleteInfo(InfoVO infoItem) {
		// TODO Auto-generated method stub

	}

	public boolean modifyInfo(InfoType type, String id, InfoVO infoItem) {
		// TODO Auto-generated method stub
		return false;
	}

	public Job verifyPassword(String id, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
