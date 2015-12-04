package businessLogic.receiptbl;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import data.HubArrivalReceiptDataImpl;
import dataService.HubArrivalReceiptDataService;
import po.receiptpo.HubArrivalReceiptPO;
import vo.receiptvo.HubArrivalReceiptVO;

public class HubArrivalReceiptBL {
	
	private HubArrivalReceiptDataService hubArrivalReceiptData=new HubArrivalReceiptDataImpl();

	public ArrayList<HubArrivalReceiptVO> getListByTime(Date fromTime, Date toTime)throws RemoteException, SQLException {
		ArrayList<HubArrivalReceiptVO> hubArrivalReceiptVOs=new ArrayList<HubArrivalReceiptVO>();
		ArrayList<HubArrivalReceiptPO> hubArrivalReceiptPOs=hubArrivalReceiptData.getList(fromTime, toTime);
		for(HubArrivalReceiptPO po:hubArrivalReceiptPOs){
			hubArrivalReceiptVOs.add(new HubArrivalReceiptVO(po));
		}
		return hubArrivalReceiptVOs;
	}

	public void createReceipt(HubArrivalReceiptVO item) throws RemoteException, SQLException {
		hubArrivalReceiptData.addItem(new HubArrivalReceiptPO(item));		
	}

}
