package businessLogic.transportbl;

import businessLogicService.transportblservice.TransportBLService;
import businessLogic.receiptbl.ReceiptBL;
import vo.receiptvo.ReceiptVO;
import vo.receiptvo.SendReceiptVO;
import typeDefinition.ReceiptType;

public class TransportBL implements TransportBLService {
	ReceiptType type;
	
	public boolean verify(ReceiptVO vo) {
		// TODO Auto-generated method stub
		type=vo.getType();
		if(type.equals(ReceiptType.SEND)){
			SendReceiptVO svo=(SendReceiptVO)vo;
			String s1=svo.getSenderPhone();
			String s2=svo.getReceiverPhone();
			String s3=svo.getExpressNumber();
			if(s1.length()!=11||s2.length()!=11||s3.length()!=10)
				return false;
		}
		return true;
	}

	public void submit(ReceiptVO vo) {
		// TODO Auto-generated method stub
		ReceiptBL receiptbl=new ReceiptBL();
		receiptbl.createReceipt(vo);
	}

	public double calFee(ReceiptVO vo) {
		// TODO Auto-generated method stub		
		return 0;
		
	}

}
