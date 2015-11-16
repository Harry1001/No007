package po.receiptpo;

import po.receiptpo.ReceiptPO;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;
import vo.receiptvo.ReceiveReceiptVO;

public class ReceiveReceiptPO extends ReceiptPO {

	/**
	 * 收件单
	 */


	private String receiveNum;
	private String receiver;
	private myTime receiveTime;
	
	public ReceiveReceiptPO(String receiveNum,String receiver,myTime receiveTime) {
		super(ReceiptType.RECEIVE);
		// TODO Auto-generated constructor stub
		this.setReceiveNum(receiveNum);
		this.setReceiver(receiver);
		this.setReceiveTime(receiveTime);
	}

	public ReceiveReceiptPO(ReceiveReceiptVO vo){
		this(vo.getReceiveNum(),vo.getReceiver(),vo.getReceiveTime());
	}


	public String getReceiveNum() {
		return receiveNum;
	}

	public void setReceiveNum(String receiveNum) {
		this.receiveNum = receiveNum;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public myTime getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(myTime receiveTime) {
		this.receiveTime = receiveTime;
	}

}
