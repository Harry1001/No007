package businessLogic.transportbl;

import businessLogicService.transportblservice.DespatchBLService;
import vo.receiptvo.DespatchReceiptVO;

public class DespatchController implements DespatchBLService{

	DespatchBL despatchbl=new DespatchBL();
	public boolean verify(DespatchReceiptVO vo) {
		return despatchbl.verify(vo);
	}

	public void submit(DespatchReceiptVO vo) {
		despatchbl.submit(vo);
	}

}
