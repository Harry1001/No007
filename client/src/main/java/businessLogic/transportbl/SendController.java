package businessLogic.transportbl;

import businessLogicService.transportblservice.SendBLService;
import vo.receiptvo.SendReceiptVO;

public class SendController implements SendBLService{

	SendBL sendbl=new SendBL();
	
	public boolean verify(SendReceiptVO vo) {
		return sendbl.verify(vo);
	}

	public void submit(SendReceiptVO vo) {
		sendbl.submit(vo);
	}

	public double calFee(SendReceiptVO vo) {
		return sendbl.calFee(vo);
	}

}
