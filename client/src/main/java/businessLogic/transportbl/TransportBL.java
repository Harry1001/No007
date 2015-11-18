package businessLogic.transportbl;

import businessLogicService.transportblservice.TransportBLService;
import businessLogic.logisticbl.LogisticBL;
import businessLogic.receiptbl.ReceiptBL;
import vo.receiptvo.ReceiptVO;
import typeDefinition.ReceiptType;

public class TransportBL implements TransportBLService {
	ReceiptType type;
	
	public boolean verify(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	public void submit(ReceiptVO vo) {
		// TODO Auto-generated method stub
		ReceiptBL receiptbl=new ReceiptBL();
		receiptbl.createReceipt(vo);
		LogisticBL logisticbl=new LogisticBL();
		logisticbl.update(vo);
	}

	public double calFee(ReceiptVO vo) {
		// TODO Auto-generated method stub		
		return 0;
		
	}

}
