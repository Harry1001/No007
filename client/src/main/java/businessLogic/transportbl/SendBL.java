package businessLogic.transportbl;

import businessLogic.logisticbl.LogisticController;
import businessLogic.receiptbl.ReceiptController;
import businessLogic.strategybl.StrategyBL;
import businessLogicService.logisticblservice.LogisticBLService;
import businessLogicService.receiptblservice.ReceiptBLService;
import vo.receiptvo.SendReceiptVO;

public class SendBL{
	
	public boolean verify(SendReceiptVO vo) {
		String s1=vo.getSenderPhone();
		String s2=vo.getReceiverPhone();
		String s3=vo.getExpressNumber();
		if(s1.length()!=11||s2.length()!=11||s3.length()!=10)
			return false;
		else if(vo.getNumber()<=0||vo.getWeight()<=0||vo.getVolume()<=0||vo.getMoney()==-1.0)
			return false;
		else
			return true;	
	}
	
	public void submit(SendReceiptVO vo) {
		ReceiptBLService receiptblservice=new ReceiptController();
		receiptblservice.createReceipt(vo);
		LogisticBLService logisticblservice=new LogisticController();
		logisticblservice.update(vo);
	}
	
	public double calFee(SendReceiptVO vo) {
		//TODO
		StrategyBL strategybl=new StrategyBL();
		return strategybl.calExpressFee(vo);
	}
	
}
