package businessLogic.transportbl;

import vo.receiptvo.DespatchReceiptVO;
import vo.receiptvo.ReceiptVO;

public class DespatchBL extends TransportBL{

	public boolean verify(ReceiptVO vo) {
		DespatchReceiptVO dvo=(DespatchReceiptVO)vo;
		String s=dvo.getOrderNum();
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
