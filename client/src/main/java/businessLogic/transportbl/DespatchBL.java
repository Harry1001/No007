package businessLogic.transportbl;

import businessLogic.logisticbl.LogisticController;
import businessLogic.receiptbl.ReceiptController;
import businessLogicService.logisticblservice.LogisticBLService;
import businessLogicService.receiptblservice.ReceiptBLService;
import vo.receiptvo.DespatchReceiptVO;

public class DespatchBL{

	public boolean verify(DespatchReceiptVO vo) {
		String s=vo.getOrderNum();
		if(s.length()!=10){
			return false;
		}
		return true;	
	}

	public void submit(DespatchReceiptVO vo) {
		ReceiptBLService receiptblservice=new ReceiptController();
		receiptblservice.createReceipt(vo);
		LogisticBLService logisticblservice=new LogisticController();
		logisticblservice.update(vo);
	}
	
}
