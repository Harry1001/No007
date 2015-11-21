package businessLogic.transportbl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import businessLogic.receiptbl.ReceiptController;
import businessLogicService.receiptblservice.ReceiptBLService;
import myexceptions.TransportBLException;
import vo.receiptvo.HubArrivalReceiptVO;

public class ArriveHubBL{

	public boolean verify(HubArrivalReceiptVO vo){
		String s1=vo.getTransReceiptID();
		if(s1.length()!=19){
			return false;
		}
		Date date=null;
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		try {
			date=format.parse(s1.substring(4,12));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(date==null){
			return false;
		}
		return true;	
	}

	public void submit(HubArrivalReceiptVO vo) {
		ReceiptBLService receiptblservice=new ReceiptController();
		receiptblservice.createReceipt(vo);
	}
	
}
