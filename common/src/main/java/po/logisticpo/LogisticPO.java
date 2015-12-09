package po.logisticpo;

import java.io.Serializable;
import java.util.Date;

import vo.logisticvo.LogisticVO;

public class LogisticPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderNum;
	private Date arrivalTime;
	private String state;
	
	public LogisticPO(String orderNum,Date arrivalTime,
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
	
	public Date getArrivalTime() {
		return arrivalTime;
	}

}
