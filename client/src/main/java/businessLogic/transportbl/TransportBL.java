package businessLogic.transportbl;

import businessLogicService.transportblservice.TransportBLService;
import businessLogic.logisticbl.LogisticBL;
import businessLogic.receiptbl.ReceiptBL;
import vo.receiptvo.ReceiptVO;
import typeDefinition.ReceiptType;

public abstract class TransportBL implements TransportBLService {

	public abstract boolean verify(ReceiptVO vo);

	public void submit(ReceiptVO vo) {
		// TODO 
		ReceiptBL receiptbl=new ReceiptBL();
		receiptbl.createReceipt(vo);	
	}

	public abstract double calFee(ReceiptVO vo);

}
