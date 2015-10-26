package stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataService.CommodityDataService;
import po.CommodityPO;

public class CommodityData_Stub implements CommodityDataService{

	public ArrayList<CommodityPO> check(String TransferNum) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<CommodityPO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public void renew() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void update(CommodityPO commodityPO) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
