package vo.logisticvo;

import po.logisticpo.LogisticPO;
import typeDefinition.myTime;

public class LogisticVO {

	private String orderNum;
	private myTime arrivalTime;
	private String state;
	
	public LogisticVO(String orderNum,myTime arrivalTime,
			String state){
		this.orderNum=orderNum;
		this.arrivalTime=arrivalTime;
		this.state=state;
	}

	public LogisticVO(LogisticPO po){
		this(po.getOrderNum(), po.getArrivalTime(), po.getState());
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

