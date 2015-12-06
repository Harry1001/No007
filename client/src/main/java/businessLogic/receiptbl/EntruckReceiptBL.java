package businessLogic.receiptbl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.EntruckReceiptDataService;
import dataService._RMI;
import po.receiptpo.EntruckReceiptPO;
import vo.receiptvo.EntruckReceiptVO;

public class EntruckReceiptBL {

	private EntruckReceiptDataService entruckReceiptData;
	
	public EntruckReceiptBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url="rmi://"+_RMI.getIP()+"/central_entruck";
		entruckReceiptData=(EntruckReceiptDataService)Naming.lookup(url);
	}
	
	public ArrayList<EntruckReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException {
		ArrayList<EntruckReceiptVO> entruckReceiptVOs=new ArrayList<EntruckReceiptVO>();
		ArrayList<EntruckReceiptPO> entruckReceiptPOs=entruckReceiptData.getList(fromTime, toTime);
		for(EntruckReceiptPO po:entruckReceiptPOs){
			entruckReceiptVOs.add(new EntruckReceiptVO(po));
		}
		return entruckReceiptVOs;
	}

	public void createReceipt(EntruckReceiptVO item) throws RemoteException, SQLException {
		entruckReceiptData.addItem(new EntruckReceiptPO(item));
	}
}
