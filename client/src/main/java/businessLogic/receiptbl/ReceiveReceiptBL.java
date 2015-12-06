package businessLogic.receiptbl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.ReceiveReceiptDataService;
import dataService._RMI;
import po.receiptpo.ReceiveReceiptPO;
import vo.receiptvo.ReceiveReceiptVO;

public class ReceiveReceiptBL {

	private ReceiveReceiptDataService receiveReceiptData;
	
	public ReceiveReceiptBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url="rmi://"+_RMI.getIP()+"/central_receive";
		receiveReceiptData=(ReceiveReceiptDataService)Naming.lookup(url);
	}
	
	public ArrayList<ReceiveReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException{
		ArrayList<ReceiveReceiptVO> receiveReceiptVOs=new ArrayList<ReceiveReceiptVO>();
		ArrayList<ReceiveReceiptPO> receiveReceiptPOs=receiveReceiptData.getList(fromTime, toTime);
		for(ReceiveReceiptPO po:receiveReceiptPOs){
			receiveReceiptVOs.add(new ReceiveReceiptVO(po));
		}
		return receiveReceiptVOs;
	}
	
	public void createReceipt(ReceiveReceiptVO item) throws RemoteException, SQLException{
		receiveReceiptData.addItem(new ReceiveReceiptPO(item));
	}
}
