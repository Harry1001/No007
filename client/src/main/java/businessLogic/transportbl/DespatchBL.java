package businessLogic.transportbl;

import businessLogic.logisticbl.LogisticBL;
import businessLogic.receiptbl.ReceiptController;
import businessLogicService.receiptblservice.ReceiptBLService;
import vo.receiptvo.DespatchReceiptVO;
import vo.receiptvo.ReceiptVO;

public class DespatchBL{

	public boolean verify(DespatchReceiptVO vo) {
		DespatchReceiptVO dvo=(DespatchReceiptVO)vo;
		String s=dvo.getOrderNum();
		if(s.length()!=10){
			return false;
		}
		return true;	
	}

	public void submit(DespatchReceiptVO vo) {
		ReceiptBLService receiptblservice=new ReceiptController();
		receiptblservice.createReceipt(vo);
		LogisticBL logisticbl=new LogisticBL();
		logisticbl.update(vo);
	}

	
}
