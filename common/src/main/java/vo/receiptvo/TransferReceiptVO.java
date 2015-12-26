package vo.receiptvo;

import po.receiptpo.TransferReceiptPO;
import typeDefinition.ReceiptState;
import typeDefinition.ReceiptType;
import typeDefinition.Vehicle;

import java.util.Date;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/16.
 */
public class TransferReceiptVO extends ReceiptVO {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

    public TransferReceiptVO(Vehicle transferType,Date transferDate,
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

    public TransferReceiptVO(TransferReceiptPO po){
        this(po.getTransferType(),po.getTransferDate(),po.getTransferID(),po.getVehicleID(),
                po.getDepartLoc(),po.getArriveLoc(),po.getCounterID(),po.getOrderID(),po.getTransferFee(),po.getState(),po.getId());
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
