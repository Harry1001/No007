package vo.logisticvo;

import java.util.ArrayList;

import po.logisticpo.LogisticPO;
import typeDefinition.LogisticState;

public class LogisticVO {

	private String orderNum;
	private ArrayList<String> history;
	private LogisticState state;
	
	public LogisticVO(String orderNum,ArrayList<String> history,LogisticState state){
		this.setOrderNum(orderNum);
		this.setHistory(history);
		this.state=state;
	}

	public LogisticVO(LogisticPO po){
		this(po.getOrderNum(), po.getHistory(), po.getState());
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

