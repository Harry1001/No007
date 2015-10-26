package businessLogic;

import java.rmi.RemoteException;
import java.util.Observable;

import businessLogicService.ReceiptBLService;
import businessLogicService.TransportBLService;

import businessLogic.ReceiptBLImpl;
import businessLogic.StrategyBLImpl;
import dataService.ReceiptDataService;
import vo.ReceiptVO;
import vo.SendReceiptVO;
import po.ReceiptPO;
import typeDefinition.ReceiptType;
import typeDefinition.ReceiptState;

public class TransportBLImpl extends BLImpl implements TransportBLService {
	ReceiptType type;
	
	public TransportBLImpl(ReceiptBLService a){
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
		boolean result=verify(vo);
		if(result){
			vo.setState(ReceiptState.SUBMITTED);
			cal(vo);
			ReceiptBLImpl rbl=new ReceiptBLImpl(null);
			rbl.createReceipt(vo);
		}	
		else{
			
		}
	}

	public void cal(ReceiptVO vo) {
		// TODO Auto-generated method stub
		double expressFee;
		double carriage;
		type=vo.getType();
		if(type.equals(ReceiptType.SEND)){
			StrategyBLImpl sbl=new StrategyBLImpl();
			SendReceiptVO svo=(SendReceiptVO)vo;
			expressFee=sbl.calExpressFee(vo);
			svo.setMoney(expressFee);
		}else{
			
		}
		
	}

}
