package businessLogic.receiptbl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dataService.TransferReceiptDataService;
import dataService._RMI;
import po.receiptpo.TransferReceiptPO;
import typeDefinition.ReceiptState;
import vo.receiptvo.TransferReceiptVO;

public class TransferReceiptBL {

	private TransferReceiptDataService transferReceiptData;
	
	public TransferReceiptBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url="rmi://"+_RMI.getIP()+"/central_transfer";
		transferReceiptData=(TransferReceiptDataService)Naming.lookup(url);
	}
	
	public ArrayList<TransferReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException {
		ArrayList<TransferReceiptVO> transferReceiptVOs=new ArrayList<TransferReceiptVO>();
		ArrayList<TransferReceiptPO> transferReceiptPOs=transferReceiptData.getList(fromTime, toTime);
		for(TransferReceiptPO po:transferReceiptPOs){
			transferReceiptVOs.add(new TransferReceiptVO(po));
		}
		return transferReceiptVOs;
	}

	public void createReceipt(TransferReceiptVO item) throws RemoteException, SQLException {
		transferReceiptData.addItem(new TransferReceiptPO(item));
	}
	
	public ArrayList<TransferReceiptVO> getListByState(ReceiptState state) throws RemoteException, SQLException {
		ArrayList<TransferReceiptVO> transferReceiptVOs=new ArrayList<TransferReceiptVO>();
		ArrayList<TransferReceiptPO> transferReceiptPOs=transferReceiptData.getListByState(state);
		for(TransferReceiptPO po:transferReceiptPOs){
			transferReceiptVOs.add(new TransferReceiptVO(po));
		}
		return transferReceiptVOs;
	}
	
	public void updateState(String orderID, ReceiptState state) throws RemoteException, SQLException {
		transferReceiptData.updateItem(orderID,state);
	}
	
}
