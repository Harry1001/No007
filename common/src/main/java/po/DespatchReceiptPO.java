package po;

import typeDefinition.ReceiptType;
import typeDefinition.myTime;
/*
 * 派件单
 * ReceiptPO的子类
 */
public class DespatchReceiptPO extends ReceiptPO{

	
	private static final long serialVersionUID = 1L;

	private myTime arrival;
	private String orderNum;
	private String despatchMan;
	
	public DespatchReceiptPO(myTime arrival,String orderNum,
			String despatchMan) {
		super(ReceiptType.DESPATCH);
		// TODO Auto-generated constructor stub
		this.setArrival(arrival);
		this.setOrderNum(orderNum);
		this.setDespatchMan(despatchMan);
	}

	public myTime getArrival() {
		return arrival;
	}

	public void setArrival(myTime arrival) {
		this.arrival = arrival;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getDespatchMan() {
		return despatchMan;
	}

	public void setDespatchMan(String despatchMan) {
		this.despatchMan = despatchMan;
	}

}
