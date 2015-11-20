package businessLogic.transportbl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import businessLogic.infobl.TruckInfoBL;
import businessLogic.strategybl.StrategyBL;
import businessLogicService.infoblservice.GetTruckInfoBLService;
import vo.receiptvo.EntruckReceiptVO;
import vo.receiptvo.ReceiptVO;

public class EntruckBL extends TransportBL{

	public boolean verify(ReceiptVO vo) {
		EntruckReceiptVO rvo=(EntruckReceiptVO)vo;
		String s1=rvo.getTransportID();
		String s3=rvo.getTruckID();
		double fee=rvo.getTransportFee();
		if(s1.length()!=19||s3.length()!=9||fee==-1.0){
			return false;
		}
		ArrayList<String> as2=rvo.getOrderNum();
		for(String temp:as2){
			if(temp.length()!=10){
				return false;
			}
		}
		Date date=null;
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		String temp=null;
		if(s1.substring(4, 5)=="2")
			temp=s1.substring(4,12);
		else
			temp=s1.substring(6,14);
		try {
			date=format.parse(temp);
		} catch (ParseException e) {
			// TODO
			e.printStackTrace();
		}
		if(date==null){
			return false;
		}
		return true;	
	}

	public double calFee(ReceiptVO vo) {
		StrategyBL strategybl=new StrategyBL();
		return strategybl.calCarriage(vo);
	}
	
}
