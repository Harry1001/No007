package po.receiptpo;

import po.receiptpo.ReceiptPO;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;
import vo.receiptvo.SendReceiptVO;

public class SendReceiptPO extends ReceiptPO {

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
	private int number;//原件数
	private double weight;//实际重量
	private double volume;//体积
	private String name;//内件品名
	private String expressType;//快递类型
	private String pack;//包装种类
	private String expressNumber;//订单条形码号
	private double money;//金额
	private myTime predictTime;//预计到达时间
	
	public SendReceiptPO(String senderName,String senderLoc,String senderUnit,String senderPhone,
			String receiverName,String receiverLoc,String receiverUnit,String receiverPhone, int number,
			double weight, double volume, String name, String expressType, String pack,
			String expressNumber, double money, myTime predictTime) {
		super(ReceiptType.SEND);
		// TODO Auto-generated constructor stub
		this.setSenderName(senderName);
		this.setSenderLoc(senderLoc);
		this.setSenderUnit(senderUnit);
		this.setSenderPhone(senderPhone);
		this.setReceiverName(receiverName);
		this.setReceiverLoc(receiverLoc);
		this.setReceiverUnit(receiverUnit);
		this.setReceiverPhone(receiverPhone);
		this.setNumber(number);
		this.setWeight(weight);
		this.setVolume(volume);
		this.setName(name);
		this.setExpressType(expressType);
		this.setPack(pack);
		this.setExpressNumber(expressNumber);
		this.setMoney(money);
		this.setPredictTime(predictTime);
	}

	public SendReceiptPO(SendReceiptVO v){
		this(v.getSenderName(),v.getSenderLoc(),v.getSenderUnit(),v.getSenderPhone(),v.getReceiverName(),
				v.getReceiverLoc(),v.getReceiverUnit(),v.getReceiverPhone(),v.getNumber(),v.getWeight(),v.getVolume(),
				v.getName(),v.getExpressType(),v.getPack(),v.getExpressNumber(),v.getMoney(),v.getPredictTime());
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getExpressType() {
		return expressType;
	}

	public void setExpressType(String expressType) {
		this.expressType = expressType;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public String getExpressNumber() {
		return expressNumber;
	}

	public void setExpressNumber(String expressNumber) {
		this.expressNumber = expressNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public myTime getPredictTime() {
		return predictTime;
	}

	public void setPredictTime(myTime predictTime) {
		this.predictTime = predictTime;
	}

}
