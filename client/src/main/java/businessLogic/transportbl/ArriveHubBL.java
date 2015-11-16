package businessLogic.transportbl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import vo.receiptvo.HubArrivalReceiptVO;
import vo.receiptvo.ReceiptVO;

public class ArriveHubBL extends TransportBL {

	public boolean verify(ReceiptVO vo) {
		HubArrivalReceiptVO havo=(HubArrivalReceiptVO)vo;
		String s1=havo.getTransReceiptID();
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
	
}
