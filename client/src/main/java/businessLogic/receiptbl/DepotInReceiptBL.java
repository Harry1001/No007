package businessLogic.receiptbl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.DepotInReceiptDataService;
import dataService._RMI;
import po.receiptpo.DepotInReceiptPO;
import vo.receiptvo.DepotInReceiptVO;

public class DepotInReceiptBL {

	private DepotInReceiptDataService depotInReceiptData;
	
	public DepotInReceiptBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url="rmi://"+_RMI.getIP()+"/central_depotin";
		depotInReceiptData=(DepotInReceiptDataService)Naming.lookup(url);
	}
	
	public ArrayList<DepotInReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException {
		ArrayList<DepotInReceiptVO> depotInReceiptVOs=new ArrayList<DepotInReceiptVO>();
		ArrayList<DepotInReceiptPO> depotInReceiptPOs=depotInReceiptData.getList(fromTime, toTime);
		for(DepotInReceiptPO po:depotInReceiptPOs){
			depotInReceiptVOs.add(new DepotInReceiptVO(po));
		}
		return depotInReceiptVOs;
	}

	public void createReceipt(DepotInReceiptVO item) throws RemoteException, SQLException {
		depotInReceiptData.addItem(new DepotInReceiptPO(item));
	}
	
}
