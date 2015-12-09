package businessLogic.receiptbl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.StoreArrivalReceiptDataService;
import dataService._RMI;
import po.receiptpo.StoreArrivalReceiptPO;
import vo.receiptvo.StoreArrivalReceiptVO;

public class StoreArrivalReceiptBL {
	
	private StoreArrivalReceiptDataService storeArrivalReceiptData;
	
	public StoreArrivalReceiptBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url="rmi://"+_RMI.getIP()+"/central_storearrival";
		storeArrivalReceiptData=(StoreArrivalReceiptDataService)Naming.lookup(url);
	}
	
	public ArrayList<StoreArrivalReceiptVO> getListByTime(Date fromTime, Date toTime)throws RemoteException, SQLException {
		ArrayList<StoreArrivalReceiptVO> storeArrivalReceiptVOs=new ArrayList<StoreArrivalReceiptVO>();
		ArrayList<StoreArrivalReceiptPO> storeArrivalReceiptPOs=storeArrivalReceiptData.getList(fromTime, toTime);
		for(StoreArrivalReceiptPO po:storeArrivalReceiptPOs){
			storeArrivalReceiptVOs.add(new StoreArrivalReceiptVO(po));
		}
		return storeArrivalReceiptVOs;
	}

	public void createReceipt(StoreArrivalReceiptVO item) throws RemoteException, SQLException {
		storeArrivalReceiptData.addItem(new StoreArrivalReceiptPO(item));
	}

<<<<<<< HEAD
=======
	public ArrayList<String> getOrderID(String transportID) throws SQLException, RemoteException {
		ArrayList<String> orderIDs=new ArrayList<String>();
		orderIDs=transferReceiptData.getOrderID(transportID);
		return orderIDs;
	}
>>>>>>> origin/master
}
