package po.logisticpo;

import typeDefinition.myTime;
import vo.logisticvo.LogisticVO;

public class LogisticPO {

	private String orderNum;
	private myTime arrivalTime;
	private String state;
	
	public LogisticPO(String orderNum,myTime arrivalTime,
			String state){
		this.orderNum=orderNum;
		this.arrivalTime=arrivalTime;
		this.state=state;
	}

	public LogisticPO(LogisticVO vo){
		this(vo.getOrderNum(), vo.getArrivalTime(), vo.getState());
	}
	
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getState() {
		return state;
	}

	public void setState(String state){
		this.state=state;
	}
	
	public myTime getArrivalTime() {
		return arrivalTime;
	}

}
