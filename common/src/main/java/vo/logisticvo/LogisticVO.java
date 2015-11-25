package vo.logisticvo;

import java.sql.Date;

import po.logisticpo.LogisticPO;

public class LogisticVO {

	private String orderNum;
	private Date arrivalTime;
	private String state;
	
	public LogisticVO(String orderNum,Date arrivalTime,
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
	
	public Date getArrivalTime() {
		return arrivalTime;
	}

}

