package businessLogic.transportbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import blfactory.BLFactory;
import businessLogicService.logisticblservice.LogisticBLService;
import businessLogicService.receiptblservice.ReceiptBLService;
import myexceptions.TransportBLException;
import vo.receiptvo.ReceiveReceiptVO;

public class ReceiveBL{

	ReceiptBLService receiptblservice;
	LogisticBLService logisticblservice;
	
	public boolean verify(ReceiveReceiptVO vo) throws TransportBLException {
		String s=vo.getReceiveNum();
		if(s.length()!=10){
			throw new TransportBLException("收件编号应该为10位！");
		}
		return true;	
	}

	public void submit(ReceiveReceiptVO vo) throws RemoteException, MalformedURLException, NotBoundException {
		receiptblservice=BLFactory.getReceiptBLService();
		receiptblservice.createReceipt(vo);
		logisticblservice=BLFactory.getLogisticBLService();
		logisticblservice.update(vo);
	}
	
}
