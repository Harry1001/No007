package businessLogicService.logisticblservice;

import vo.logisticvo.LogisticVO;
import vo.receiptvo.ReceiptVO;

public interface LogisticBLService {

	public LogisticVO getLogistic(String orderID);
	public void update(ReceiptVO vo);
	
}
