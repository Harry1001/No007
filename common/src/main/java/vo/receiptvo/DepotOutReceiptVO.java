package vo.receiptvo;

import po.receiptpo.DepotOutReceiptPO;
import typeDefinition.ReceiptType;
import typeDefinition.Vehicle;
import typeDefinition.myTime;

/**
 * Created by Harry on 2015/11/16.
 */
public class DepotOutReceiptVO extends ReceiptVO {
    private String packID;
    private myTime outTime;
    private String destination;
    private Vehicle vehicle;//装运形式
    private String transID;//中转单编号或者汽运编号

    public DepotOutReceiptVO(String packID, myTime time, String desti, Vehicle ve, String transID) {
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

    public myTime getOutTime() {
        return outTime;
    }

    public void setOutTime(myTime outTime) {
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
