package businessLogic.transportbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import blfactory.BLFactory;
import businessLogicService.logisticblservice.LogisticBLService;
import businessLogicService.receiptblservice.DespatchReceiptBLService;
import myexceptions.TransportBLException;
import vo.receiptvo.DespatchReceiptVO;

public class DespatchBL{

	DespatchReceiptBLService receiptblservice;
	LogisticBLService logisticblservice;
	
	public boolean verify(DespatchReceiptVO vo) throws TransportBLException {
		String s=vo.getOrderNum();
		if(s.length()!=10){
			throw new TransportBLException("托运订单条形码号应该为10位！");
		}
		return true;	
	}

	public void submit(DespatchReceiptVO vo) throws RemoteException, MalformedURLException, NotBoundException, SQLException{
		receiptblservice=BLFactory.getDespatchReceiptBLService();
		receiptblservice.createReceipt(vo);
		logisticblservice=BLFactory.getLogisticBLService();
		logisticblservice.update(vo);
	}
	
}
