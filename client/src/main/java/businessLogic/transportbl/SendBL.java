package businessLogic.transportbl;

import java.rmi.RemoteException;

import blfactory.BLFactory;
import businessLogic.strategybl.StrategyBL;
import businessLogicService.logisticblservice.LogisticBLService;
import businessLogicService.receiptblservice.ReceiptBLService;
import businessLogicService.strategyblservice.CalExpressfeeService;
import myexceptions.TransportBLException;
import vo.receiptvo.SendReceiptVO;

public class SendBL{
	
	public boolean verify(SendReceiptVO vo) throws TransportBLException {
		String s1=vo.getSenderPhone();
		String s2=vo.getReceiverPhone();
		String s3=vo.getExpressNumber();
		if(s1.length()!=11)
			throw new TransportBLException("寄件人手机号应该为11位！");
		if(s2.length()!=11)
			throw new TransportBLException("收件人手机号应该为11位！");
		if(s3.length()!=10)
			throw new TransportBLException("订单条形码号应该为10位！");
		if(vo.getNumber()<=0)
			throw new TransportBLException("原件数应该为正数！");
		if(vo.getWeight()<=0)
			throw new TransportBLException("重量应该为正数！");
		if(vo.getVolume()<=0)
			throw new TransportBLException("体积应该为正数！");
		if(vo.getMoney()==-1.0)
			throw new TransportBLException("请计算快递费！");
		return true;	
	}
	
	public void submit(SendReceiptVO vo) throws RemoteException{
		ReceiptBLService receiptblservice=BLFactory.getReceiptBLService();
		receiptblservice.createReceipt(vo);
		LogisticBLService logisticblservice=BLFactory.getLogisticBLService();
		logisticblservice.update(vo);
	}
	
	public double calFee(SendReceiptVO vo) throws RemoteException{
		//TODO
		CalExpressfeeService strategybl=BLFactory.getCalExpressfeeService();
		return strategybl.calExpressFee(vo);
	}
	
}
