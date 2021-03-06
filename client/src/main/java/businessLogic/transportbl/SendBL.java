package businessLogic.transportbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import blfactory.BLFactory;
import businessLogicService.receiptblservice.SendReceiptBLService;
import myexceptions.TransportBLException;
import po.receiptpo.SendReceiptPO;
import vo.receiptvo.SendReceiptVO;

public class SendBL{
	
	SendReceiptBLService receiptblservice;
	
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
	
	public void submit(SendReceiptVO vo) throws RemoteException, MalformedURLException, NotBoundException, SQLException{
		receiptblservice=BLFactory.getSendReceiptBLService();
		receiptblservice.createReceipt(vo);
	}
	
	public SendReceiptVO getSendReceipt(String orderID) throws RemoteException, SQLException, MalformedURLException, NotBoundException {
		receiptblservice=BLFactory.getSendReceiptBLService();
		SendReceiptPO po=receiptblservice.getSendReceipt(orderID);

		return po==null?null:new SendReceiptVO(po);
	}
}
