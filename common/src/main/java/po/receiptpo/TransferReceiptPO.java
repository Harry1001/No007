package po.receiptpo;

import java.util.Date;
import java.util.ArrayList;

import po.receiptpo.ReceiptPO;
import typeDefinition.ReceiptState;
import typeDefinition.ReceiptType;
import typeDefinition.Vehicle;
import vo.receiptvo.TransferReceiptVO;

public class TransferReceiptPO extends ReceiptPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 中转单
	 */


	private Vehicle transferType;//中转方式
	private Date transferDate;//中转日期
	private String transferID;//中转单编号
	private String vehicleID;//飞机／铁路）班次或者汽车的车牌号
	private String departLoc;//出发地
	private String arriveLoc;//到达地
	private int counterID;//货柜号
	private ArrayList<String> orderID;//本次装箱所有货物的单号
	private double transferFee;
	private ReceiptState state;
    private String id;
	
	public TransferReceiptPO(Vehicle transferType,Date transferDate,
			String transferID,String vehicleID,String departLoc,String arriveLoc,
			int counterID,ArrayList<String> orderID,double transferFee,ReceiptState state,String id) {
		super(ReceiptType.TRANSFER);
		
		this.transferType=transferType;
		this.transferDate=transferDate;
		this.transferID=transferID;
		this.vehicleID=vehicleID;
		this.departLoc=departLoc;
		this.arriveLoc=arriveLoc;
		this.counterID=counterID;
		this.orderID=orderID;
		this.transferFee=transferFee;
		this.state=state;
        this.id=id;
	}

	public TransferReceiptPO(TransferReceiptVO vo){
		this(vo.getTransferType(),vo.getTransferDate(),vo.getTransferID(),vo.getVehicleID(),
				vo.getDepartLoc(),vo.getArriveLoc(),vo.getCounterID(),vo.getOrderID(),vo.getTransferFee(),vo.getState(),vo.getId());
	}

	public Vehicle getTransferType() {
		return transferType;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public String getTransferID() {
		return transferID;
	}

	public void setTransferID(String transferID) {
		this.transferID = transferID;
	}

	public String getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(String vehicleID) {
		this.vehicleID = vehicleID;
	}

	public String getDepartLoc() {
		return departLoc;
	}

	public void setDepartLoc(String departLoc) {
		this.departLoc = departLoc;
	}

	public String getArriveLoc() {
		return arriveLoc;
	}

	public void setArriveLoc(String arriveLoc) {
		this.arriveLoc = arriveLoc;
	}

	public int getCounterID() {
		return counterID;
	}

	public void setCounterID(int counterID) {
		this.counterID = counterID;
	}

	public ArrayList<String> getOrderID() {
		return orderID;
	}

	public void setOrderID(ArrayList<String> orderID) {
		this.orderID = orderID;
	}

	public double getTransferFee() {
		return transferFee;
	}

	public void setTransferFee(double transferFee) {
		this.transferFee = transferFee;
	}

	public ReceiptState getState() {
		return state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
