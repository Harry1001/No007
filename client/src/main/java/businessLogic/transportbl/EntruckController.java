package businessLogic.transportbl;

import businessLogicService.transportblservice.EntruckBLService;
import vo.receiptvo.EntruckReceiptVO;

public class EntruckController implements EntruckBLService{

	EntruckBL entruckbl=new EntruckBL();
	
	public boolean verify(EntruckReceiptVO vo) {
		return entruckbl.verify(vo);
	}

	public void submit(EntruckReceiptVO vo) {
		entruckbl.submit(vo);
	}

	public double calFee(EntruckReceiptVO vo) {
		return entruckbl.calFee(vo);
	}

}
