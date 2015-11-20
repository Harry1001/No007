package businessLogic.transportbl;

import vo.receiptvo.ReceiptVO;
import vo.receiptvo.ReceiveReceiptVO;

public class ReceiveBL extends TransportBL {

	public boolean verify(ReceiptVO vo) {
		ReceiveReceiptVO rvo=(ReceiveReceiptVO)vo;
		String s=rvo.getReceiveNum();
		if(s.length()!=10){
			return false;
		}
		return true;	
	}

	@Override
	public double calFee(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
