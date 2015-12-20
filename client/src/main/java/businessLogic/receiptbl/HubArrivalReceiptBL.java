package businessLogic.receiptbl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.HubArrivalReceiptDataService;
import dataService._RMI;
import myexceptions.TransportBLException;
import po.receiptpo.HubArrivalReceiptPO;
import vo.receiptvo.HubArrivalReceiptVO;

public class HubArrivalReceiptBL {
	
	private HubArrivalReceiptDataService hubArrivalReceiptData;

	public HubArrivalReceiptBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url="rmi://"+_RMI.getIP()+"/central_hubarrival";
		hubArrivalReceiptData=(HubArrivalReceiptDataService)Naming.lookup(url);
	}
	
	public ArrayList<HubArrivalReceiptVO> getListByTime(Date fromTime, Date toTime)throws RemoteException, SQLException {
		ArrayList<HubArrivalReceiptVO> hubArrivalReceiptVOs=new ArrayList<HubArrivalReceiptVO>();
		ArrayList<HubArrivalReceiptPO> hubArrivalReceiptPOs=hubArrivalReceiptData.getList(fromTime, toTime);
		for(HubArrivalReceiptPO po:hubArrivalReceiptPOs){
			hubArrivalReceiptVOs.add(new HubArrivalReceiptVO(po));
		}
		return hubArrivalReceiptVOs;
	}

	public void createReceipt(HubArrivalReceiptVO item) throws RemoteException, SQLException, TransportBLException {
		hubArrivalReceiptData.addItem(new HubArrivalReceiptPO(item));		
	}

}
