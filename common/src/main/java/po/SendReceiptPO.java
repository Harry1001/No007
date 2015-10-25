package po;

import typeDefinition.ReceiptType;

public class SendReceiptPO extends ReceiptPO{

	/**
	 * 寄件单
	 * ReceiptPO的子类
	 */
	private static final long serialVersionUID = 1L;

	private String senderName;//寄件人姓名
	private String senderLoc;//寄件人住址
	private String senderUnit;//寄件人单位
	private String senderPhone;//寄件人手机
	private String receiverName;//收件人姓名
	private String receiverLoc;//收件人住址
	private String receiverUnit;//收件人单位
	private String receiverPhone;//收件人手机
	
	public SendReceiptPO(ReceiptType type,String senderName,String senderLoc,String senderUnit,String senderPhone,
			String receiverName,String receiverLoc,String receiverUnit,String receiverPhone) {
		super(type);
		// TODO Auto-generated constructor stub
		this.setSenderName(senderName);
		this.setSenderLoc(senderLoc);
		this.setSenderUnit(senderUnit);
		this.setSenderPhone(senderPhone);
		this.setReceiverName(receiverName);
		this.setReceiverLoc(receiverLoc);
		this.setReceiverUnit(receiverUnit);
		this.setReceiverPhone(receiverPhone);
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderLoc() {
		return senderLoc;
	}

	public void setSenderLoc(String senderLoc) {
		this.senderLoc = senderLoc;
	}

	public String getSenderUnit() {
		return senderUnit;
	}

	public void setSenderUnit(String senderUnit) {
		this.senderUnit = senderUnit;
	}

	public String getSenderPhone() {
		return senderPhone;
	}

	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverLoc() {
		return receiverLoc;
	}

	public void setReceiverLoc(String receiverLoc) {
		this.receiverLoc = receiverLoc;
	}

	public String getReceiverUnit() {
		return receiverUnit;
	}

	public void setReceiverUnit(String receiverUnit) {
		this.receiverUnit = receiverUnit;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

}
