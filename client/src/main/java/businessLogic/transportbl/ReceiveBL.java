package businessLogic.transportbl;

import businessLogic.logisticbl.LogisticController;
import businessLogic.receiptbl.ReceiptController;
import businessLogicService.logisticblservice.LogisticBLService;
import businessLogicService.receiptblservice.ReceiptBLService;
import vo.receiptvo.ReceiveReceiptVO;

public class ReceiveBL{

	public boolean verify(ReceiveReceiptVO vo) {
		String s=vo.getReceiveNum();
		if(s.length()!=10){
			return false;
		}
		return true;	
	}

	public void submit(ReceiveReceiptVO vo) {
		ReceiptBLService receiptblservice=new ReceiptController();
		receiptblservice.createReceipt(vo);
		LogisticBLService logisticblservice=new LogisticController();
		logisticblservice.update(vo);
	}
	
}
