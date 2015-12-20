package businessLogic.transportbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import blfactory.BLFactory;
import businessLogicService.receiptblservice.StoreArrivalReceiptBLService;
import myexceptions.TransportBLException;
import vo.receiptvo.StoreArrivalReceiptVO;

public class ArriveStoreBL{

	StoreArrivalReceiptBLService receiptblservice;
	
	public boolean verify(StoreArrivalReceiptVO vo) throws TransportBLException{
		String s1=vo.getTransReceiptID();		
		Date date=null;
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		try {
			date=format.parse(s1.substring(6,14));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(date==null){
			throw new TransportBLException("中转单编号应该为6位营业厅编号＋8位日期＋5位数字！");
		}
		return true;	
	}
	
	public void submit(StoreArrivalReceiptVO vo) throws  MalformedURLException, NotBoundException, RemoteException, SQLException, TransportBLException {
		receiptblservice=BLFactory.getStoreArrivalReceiptBLService();
		receiptblservice.createReceipt(vo);
	}

}
