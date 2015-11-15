package stub;

import businessLogicService.logisticblservice.LogisticBLService;
import typeDefinition.LogisticState;
import typeDefinition.myTime;
import vo.LogisticVO;

public class LogisticBL_Stub implements LogisticBLService{

	public LogisticVO getLogistic(String orderID){
		LogisticVO vo=new LogisticVO(orderID,new myTime(2015,10,26,8,0,0),
				LogisticState.RECEIVE);
		return vo;
	}
}
