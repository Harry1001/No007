package po.receiptpo;

import java.util.Date;

import typeDefinition.ReceiptType;
import vo.receiptvo.DespatchReceiptVO;

/*
 * 派件单
 * ReceiptPO的子类
 */
public class DespatchReceiptPO extends ReceiptPO {

	
	private static final long serialVersionUID = 1L;

	private Date arrivalTime;
	private String orderNum;
	private String despatchMan;
	
	public DespatchReceiptPO(Date arrival,String orderNum,
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

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
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
