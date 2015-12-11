package businessLogic.transportbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import blfactory.BLFactory;
import businessLogicService.receiptblservice.ReceiveReceiptBLService;
import myexceptions.TransportBLException;
import vo.receiptvo.ReceiveReceiptVO;

public class ReceiveBL{

	ReceiveReceiptBLService receiptblservice;
	
	public boolean verify(ReceiveReceiptVO vo) throws TransportBLException {
		String s=vo.getReceiveNum();
		if(s.length()!=10){
			throw new TransportBLException("收件编号应该为10位！");
		}
		return true;	
	}

	public void submit(ReceiveReceiptVO vo) throws RemoteException, MalformedURLException, NotBoundException, SQLException {
		receiptblservice=BLFactory.getReceiveReceiptBLService();
		receiptblservice.createReceipt(vo);
	}
	
}
