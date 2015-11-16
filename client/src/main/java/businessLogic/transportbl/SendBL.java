package businessLogic.transportbl;

import businessLogic.strategybl.StrategyBL;
import vo.receiptvo.ReceiptVO;
import vo.receiptvo.SendReceiptVO;

public class SendBL extends TransportBL{
	
	public boolean verify(ReceiptVO vo) {
		SendReceiptVO svo=(SendReceiptVO)vo;
		String s1=svo.getSenderPhone();
		String s2=svo.getReceiverPhone();
		String s3=svo.getExpressNumber();
		if(s1.length()!=11||s2.length()!=11||s3.length()!=10)
			return false;
		else if(svo.getNumber()<=0||svo.getWeight()<=0||svo.getVolume()<=0||svo.getMoney()==-1.0)
			return false;
		else
			return true;	
	}
	
	public double calFee(ReceiptVO vo) {
		StrategyBL strategybl=new StrategyBL();
		SendReceiptVO svo=(SendReceiptVO)vo;
		return strategybl.calExpressFee(svo);
	}
	
}
