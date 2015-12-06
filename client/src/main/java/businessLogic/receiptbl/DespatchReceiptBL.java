package businessLogic.receiptbl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.DespatchReceiptDataService;
import dataService._RMI;
import po.receiptpo.DespatchReceiptPO;
import vo.receiptvo.DespatchReceiptVO;

public class DespatchReceiptBL {

	private DespatchReceiptDataService despatchReceiptData;
	
	public DespatchReceiptBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url="rmi://"+_RMI.getIP()+"/central_despatch";
		despatchReceiptData=(DespatchReceiptDataService)Naming.lookup(url);
	}
	
	public ArrayList<DespatchReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException {
		//TODO 如果起始时间小于终止时间，报错
		ArrayList<DespatchReceiptVO> despatchReceiptVOs=new ArrayList<DespatchReceiptVO>();
		ArrayList<DespatchReceiptPO> despatchReceiptPOs=despatchReceiptData.getList(fromTime, toTime);
		for(DespatchReceiptPO po:despatchReceiptPOs){
			despatchReceiptVOs.add(new DespatchReceiptVO(po));
		}
		return despatchReceiptVOs;
	}

	public void createReceipt(DespatchReceiptVO item) throws RemoteException, SQLException {
		despatchReceiptData.addItem(new DespatchReceiptPO(item));		
	}

}
