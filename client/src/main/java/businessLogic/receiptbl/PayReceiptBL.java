package businessLogic.receiptbl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.PayReceiptDataService;
import dataService._RMI;
import po.receiptpo.PayReceiptPO;
import vo.receiptvo.PayReceiptVO;

public class PayReceiptBL {

	private PayReceiptDataService payReceiptData;
	
	public PayReceiptBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url="rmi://"+_RMI.getIP()+"/central_pay";
		payReceiptData=(PayReceiptDataService)Naming.lookup(url);
	}
	
	public ArrayList<PayReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException{	
		ArrayList<PayReceiptVO> payReceiptVOs=new ArrayList<PayReceiptVO>();
		ArrayList<PayReceiptPO> payReceiptPOs=payReceiptData.getList(fromTime, toTime);
		for(PayReceiptPO po:payReceiptPOs){
			payReceiptVOs.add(new PayReceiptVO(po));
		}
		return payReceiptVOs;
	}
	
	public void createReceipt(PayReceiptVO item) throws RemoteException, SQLException{
		payReceiptData.addItem(new PayReceiptPO(item));
	}
	
}
