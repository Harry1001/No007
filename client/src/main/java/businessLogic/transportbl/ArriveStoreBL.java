package businessLogic.transportbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import blfactory.BLFactory;
import businessLogicService.logisticblservice.LogisticBLService;
import businessLogicService.receiptblservice.StoreArrivalReceiptBLService;
import myexceptions.TransportBLException;
import vo.receiptvo.StoreArrivalReceiptVO;

public class ArriveStoreBL{

	StoreArrivalReceiptBLService receiptblservice;
	LogisticBLService logisticblservice;
	
	public boolean verify(StoreArrivalReceiptVO vo) throws TransportBLException{
		String s1=vo.getTransReceiptID();		
		Date date=null;
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		try {
			date=format.parse(s1.substring(6,14));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(date==null){
			throw new TransportBLException("中转单编号应该为6位营业厅编号＋8位日期＋5位数字！");
		}
		return true;	
	}
	
	public void submit(StoreArrivalReceiptVO vo) throws  MalformedURLException, NotBoundException {
		try {
			receiptblservice=BLFactory.getStoreArrivalReceiptBLService();
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			receiptblservice.createReceipt(vo);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			logisticblservice=BLFactory.getLogisticBLService();
			logisticblservice.update(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
