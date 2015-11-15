package businessLogic.transportbl;

import java.rmi.RemoteException;
import java.util.Observable;

import businessLogicService.receiptblservice.ReceiptBLService;
import businessLogicService.transportblservice.TransportBLService;
import businessLogic.receiptbl.ReceiptBL;
import businessLogic.strategybl.StrategyBL;
import dataService.ReceiptDataService;
import vo.ReceiptVO;
import vo.SendReceiptVO;
import po.ReceiptPO;
import typeDefinition.ReceiptType;
import typeDefinition.ReceiptState;

public class TransportBL implements TransportBLService {
	ReceiptType type;
	
	public TransportBL(ReceiptBLService a){
	//	this.
	}
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	public boolean verify(ReceiptVO vo) {
		// TODO Auto-generated method stub
		type=vo.getType();
		if(type.equals(ReceiptType.SEND)){
			SendReceiptVO svo=(SendReceiptVO)vo;
			String s1=svo.getSenderPhone();
			String s2=svo.getReceiverPhone();
			String s3=svo.getExpressNumber();
			if(s1.length()!=11||s2.length()!=11||s3.length()!=10)
				return false;
		}
		return true;
	}

	public void submit(ReceiptVO vo) {
		// TODO Auto-generated method stub
		//boolean result=verify(vo);
		//if(result){
			vo.setState(ReceiptState.SUBMITTED);
			//cal(vo);
			ReceiptBL rbl=new ReceiptBL(null);
			rbl.createReceipt(vo);
		//}	
		//else{
			
		//}
	}

	public void cal(ReceiptVO vo) {
		// TODO Auto-generated method stub
		double expressFee;
		double carriage;
		type=vo.getType();
		if(type.equals(ReceiptType.SEND)){
			StrategyBL sbl=new StrategyBL();
			SendReceiptVO svo=(SendReceiptVO)vo;
			expressFee=sbl.calExpressFee(vo);
			svo.setMoney(expressFee);
			
		}else{
			
		}
		
	}

}
