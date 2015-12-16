package vo.receiptvo;

import java.util.Date;

import po.receiptpo.DepotOutReceiptPO;
import typeDefinition.ReceiptType;
import typeDefinition.Vehicle;

/**
 * Created by Harry on 2015/11/16.
 */
public class DepotOutReceiptVO extends ReceiptVO {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String packID;
    private Date outTime;
    private String destination;
    private Vehicle vehicle;//装运形式
    private String transID;//中转单编号或者汽运编号

    public DepotOutReceiptVO(String packID, Date time, String desti, Vehicle ve, String transID) {
        super(ReceiptType.DEPOTOUT);
        this.packID=packID;
        this.outTime=time;
        this.destination=desti;
        this.vehicle=ve;
        this.transID=transID;
    }

    public DepotOutReceiptVO(DepotOutReceiptPO po){
        this(po.getPackID(),po.getOutTime(),po.getDestination(),po.getVehicle(),po.getTransID());
    }

    public String getPackID() {
        return packID;
    }

    public void setPackID(String packID) {
        this.packID = packID;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getTransID() {
        return transID;
    }

    public void setTransID(String transID) {
        this.transID = transID;
    }
}
