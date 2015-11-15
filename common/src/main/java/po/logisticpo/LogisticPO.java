package po.logisticpo;

import java.util.ArrayList;

import typeDefinition.LogisticState;
import vo.logisticvo.LogisticVO;

public class LogisticPO {

	private String orderNum;
	private ArrayList<String> history;
	private LogisticState state;
	
	public LogisticPO(String orderNum,ArrayList<String> history,LogisticState state){
		this.setOrderNum(orderNum);
		this.setHistory(history);
		this.state=state;
	}

	public LogisticPO(LogisticVO vo){
		this(vo.getOrderNum(), vo.getHistory(), vo.getState());
	}
	
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public LogisticState getState() {
		return state;
	}

	public ArrayList<String> getHistory() {
		return history;
	}

	public void setHistory(ArrayList<String> history) {
		this.history=history;
	}

}
