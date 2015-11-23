package businessLogic.transportbl;

import businessLogicService.transportblservice.TransferBLService;
import vo.receiptvo.TransferReceiptVO;

public class TransferController implements TransferBLService{

	TransferBL transferbl=new TransferBL();
	public boolean verify(TransferReceiptVO vo) {
		return transferbl.verify(vo);
	}

	public void submit(TransferReceiptVO vo) {
		transferbl.submit(vo);
	}

	public double calFee(TransferReceiptVO vo) {
		return transferbl.calFee(vo);
	}

}
