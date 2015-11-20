package businessLogic.transportbl;

import businessLogic.receiptbl.ReceiptController;
import businessLogicService.receiptblservice.ReceiptBLService;
import vo.receiptvo.ReceiveReceiptVO;

public class ReceiveBL{

	public boolean verify(ReceiveReceiptVO vo) {
		ReceiveReceiptVO rvo=(ReceiveReceiptVO)vo;
		String s=rvo.getReceiveNum();
		if(s.length()!=10){
			return false;
		}
		return true;	
	}

	public void submit(ReceiveReceiptVO vo) {
		ReceiptBLService receiptblservice=new ReceiptController();
		receiptblservice.createReceipt(vo);
	}
	
}
