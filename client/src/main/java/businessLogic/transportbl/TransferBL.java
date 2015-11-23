package businessLogic.transportbl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import businessLogic.receiptbl.ReceiptController;
import businessLogic.strategybl.StrategyBL;
import businessLogicService.receiptblservice.ReceiptBLService;
import vo.receiptvo.TransferReceiptVO;

public class TransferBL{

	public boolean verify(TransferReceiptVO vo) {
		String s1=vo.getTransferID();
		double fee=vo.getTransferFee();
		if(s1.length()!=19||fee==-1.0){
			return false;
		}
		ArrayList<String> as2=vo.getOrderID();
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
	
	public void submit(TransferReceiptVO vo) {
		ReceiptBLService receiptblservice=new ReceiptController();
		receiptblservice.createReceipt(vo);
	}
	
	public double calFee(TransferReceiptVO vo) {
		//TODO
		StrategyBL strategybl=new StrategyBL();
		return strategybl.calCarriage(vo);
	}
	
}
