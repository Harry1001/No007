package businessLogic.transportbl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import vo.receiptvo.ReceiptVO;
import vo.receiptvo.StoreArrivalReceiptVO;

public class ArriveStoreBL extends TransportBL {

	public boolean verify(ReceiptVO vo) {
		StoreArrivalReceiptVO savo=(StoreArrivalReceiptVO)vo;
		String s1=savo.getTransReceiptID();		
		if(s1.length()!=19){
			return false;
		}
		Date date=null;
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		try {
			date=format.parse(s1.substring(6,14));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(date==null){
			return false;
		}
		return true;	
	}

	@Override
	public double calFee(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
