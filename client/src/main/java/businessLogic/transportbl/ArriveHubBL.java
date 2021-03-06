package businessLogic.transportbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import businessLogicService.receiptblservice.HubArrivalReceiptBLService;
import myexceptions.TransportBLException;
import vo.receiptvo.HubArrivalReceiptVO;
import blfactory.BLFactory;;

public class ArriveHubBL{

	HubArrivalReceiptBLService receiptblservice;

	public boolean verify(HubArrivalReceiptVO vo) throws TransportBLException{
		String s1=vo.getTransReceiptID();
		Date date=null;
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		try {
			date=format.parse(s1.substring(4,12));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(date==null){
			throw new TransportBLException("中转单编号应该为4位中转中心编号＋8位日期＋7位数字！");
		}
		return true;	
	}

	public void submit(HubArrivalReceiptVO vo) throws RemoteException, MalformedURLException, NotBoundException, SQLException, TransportBLException {
		receiptblservice=BLFactory.getHubArrivalReceiptBLService();
		receiptblservice.createReceipt(vo);
	}
	
}
