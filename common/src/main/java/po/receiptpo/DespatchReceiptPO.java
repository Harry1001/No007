package po.receiptpo;

import typeDefinition.ReceiptType;
import typeDefinition.myTime;
import vo.receiptvo.DespatchReceiptVO;

/*
 * 派件单
 * ReceiptPO的子类
 */
public class DespatchReceiptPO extends ReceiptPO {

	
	private static final long serialVersionUID = 1L;

	private myTime arrivalTime;
	private String orderNum;
	private String despatchMan;
	
	public DespatchReceiptPO(myTime arrival,String orderNum,
			String despatchMan) {
		super(ReceiptType.DESPATCH);
		// TODO Auto-generated constructor stub
		this.setArrivalTime(arrival);
		this.setOrderNum(orderNum);
		this.setDespatchMan(despatchMan);
	}

	public DespatchReceiptPO(DespatchReceiptVO vo){
		this(vo.getArrivalTime(),vo.getOrderNum(),vo.getDespatchMan());
	}

	public myTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(myTime arrivalTime) {
		this.arrivalTime = arrivalTime;
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
