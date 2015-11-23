package businessLogic.transportbl;

import java.rmi.RemoteException;

import blfactory.BLFactory;
import businessLogicService.logisticblservice.LogisticBLService;
import businessLogicService.receiptblservice.ReceiptBLService;
import myexceptions.TransportBLException;
import vo.receiptvo.DespatchReceiptVO;

public class DespatchBL{

	public boolean verify(DespatchReceiptVO vo) throws TransportBLException {
		String s=vo.getOrderNum();
		if(s.length()!=10){
			throw new TransportBLException("托运订单条形码号应该为10位！");
		}
		return true;	
	}

	public void submit(DespatchReceiptVO vo) throws RemoteException{
		ReceiptBLService receiptblservice=BLFactory.getReceiptBLService();
		receiptblservice.createReceipt(vo);
		LogisticBLService logisticblservice=BLFactory.getLogisticBLService();
		logisticblservice.update(vo);
	}
	
}
