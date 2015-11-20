package businessLogic.transportbl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import businessLogic.strategybl.StrategyBL;
import vo.receiptvo.ReceiptVO;
import vo.receiptvo.TransferReceiptVO;

public class TransferBL extends TransportBL {

	public boolean verify(ReceiptVO vo) {
		TransferReceiptVO tvo=(TransferReceiptVO)vo;
		String s1=tvo.getTransferID();
		double fee=tvo.getTransferFee();
		if(s1.length()!=19||fee==-1.0){
			return false;
		}
		ArrayList<String> as2=tvo.getOrderID();
		for(String temp:as2){
			if(temp.length()!=10){
				return false;
			}
		}
		Date date=null;
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		String temp=s1.substring(4,12);
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
