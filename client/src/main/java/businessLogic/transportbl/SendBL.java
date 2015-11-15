package businessLogic.transportbl;

import businessLogic.strategybl.StrategyBL;
import vo.receiptvo.ReceiptVO;

public class SendBL extends TransportBL{
	
	public boolean verify(ReceiptVO vo) {
		
		return false;	
	}
	
	public double calFee(ReceiptVO vo) {
		StrategyBL strategybl=new StrategyBL();
		return strategybl.calExpressFee(vo);
	}
	
}
