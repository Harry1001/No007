package businessLogic.logisticbl;

import businessLogicService.logisticblservice.LogisticBLService;
import vo.logisticvo.LogisticVO;

public class LogisticController implements LogisticBLService{

	public LogisticVO getLogistic(String orderID) {
		// TODO Auto-generated method stub
		LogisticBL logisticbl=new LogisticBL();
		return logisticbl.getLogistic(orderID);
	}

}
