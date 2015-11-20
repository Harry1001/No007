package businessLogic.transportbl;

import businessLogicService.transportblservice.ReceiveBLService;
import vo.receiptvo.ReceiveReceiptVO;

public class ReceiveController implements ReceiveBLService{

	ReceiveBL receivebl=new ReceiveBL();
	
	public boolean verify(ReceiveReceiptVO vo) {
		return receivebl.verify(vo);
	}

	public void submit(ReceiveReceiptVO vo) {
		receivebl.submit(vo);
	}

}
