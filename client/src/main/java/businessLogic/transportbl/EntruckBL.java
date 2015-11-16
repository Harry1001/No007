package businessLogic.transportbl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import businessLogic.strategybl.StrategyBL;
import vo.receiptvo.EntruckReceiptVO;
import vo.receiptvo.ReceiptVO;

public class EntruckBL extends TransportBL{

	public boolean verify(ReceiptVO vo) {
		EntruckReceiptVO rvo=(EntruckReceiptVO)vo;
		String s1=rvo.getTransportID();
		ArrayList<String> as2=rvo.getOrderNum();
		String s3=rvo.getTruckID();
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
		else if(s3.length()!=9){
			return false;
		}
		else{
			for(String temp:as2){
				if(temp.length()!=10){
					return false;
				}
			}
		}
		return true;	
	}

	public double calFee(ReceiptVO vo) {
		StrategyBL strategybl=new StrategyBL();
		return strategybl.calCarriage(vo);
	}
	
}
