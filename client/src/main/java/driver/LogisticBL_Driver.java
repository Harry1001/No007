package driver;

import businessLogicService.LogisticBLService;
import vo.LogisticVO;

public class LogisticBL_Driver {

	public void driver(LogisticBLService logisticBLService){
		
		String orderID="0000000001";
		LogisticVO vo=logisticBLService.getLogistic(orderID);
		if(vo!=null){
			System.out.println("查询物流信息成功！");
			System.out.print(orderID+":"+vo.getArrivalTime()+" "+vo.getState());
		}
	}
}
