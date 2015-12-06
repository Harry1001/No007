package businessLogic.receiptbl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.DepotOutReceiptDataService;
import dataService._RMI;
import po.receiptpo.DepotOutReceiptPO;
import vo.receiptvo.DepotOutReceiptVO;

public class DepotOutReceiptBL {
	
	private DepotOutReceiptDataService depotOutReceiptData;
	
	public DepotOutReceiptBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url="rmi://"+_RMI.getIP()+"/central_depotout";
		depotOutReceiptData=(DepotOutReceiptDataService)Naming.lookup(url);
	}

	public ArrayList<DepotOutReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException {
		ArrayList<DepotOutReceiptVO> depotOutReceiptVOs=new ArrayList<DepotOutReceiptVO>();
		ArrayList<DepotOutReceiptPO> depotOutReceiptPOs=depotOutReceiptData.getList(fromTime, toTime);
		for(DepotOutReceiptPO po:depotOutReceiptPOs){
			depotOutReceiptVOs.add(new DepotOutReceiptVO(po));
		}
		return depotOutReceiptVOs;
	}

	public void createReceipt(DepotOutReceiptVO item) throws RemoteException, SQLException {
		depotOutReceiptData.addItem(new DepotOutReceiptPO(item));
	}

}
