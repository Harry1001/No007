package businessLogic.logisticbl;

import businessLogicService.logisticblservice.LogisticBLService;
import vo.logisticvo.LogisticVO;
import vo.receiptvo.ReceiptVO;

public class LogisticController implements LogisticBLService{
	
	LogisticBL logisticbl=new LogisticBL();
	
	public LogisticVO getLogistic(String orderID) {	
		return logisticbl.getLogistic(orderID);
	}

	public void update(ReceiptVO vo) {
		logisticbl.update(vo);		
	}

}
