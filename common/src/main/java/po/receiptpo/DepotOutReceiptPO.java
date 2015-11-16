package po.receiptpo;

import typeDefinition.ReceiptType;
import typeDefinition.Vehicle;
import typeDefinition.myTime;
import vo.receiptvo.DepotOutReceiptVO;

/**
 * Created by Harry on 2015/11/16.
 */
public class DepotOutReceiptPO extends ReceiptPO {

    private String packID;
    private myTime outTime;
    private String destination;
    private Vehicle vehicle;//装运形式
    private String transID;//中转单编号或者汽运编号

    public DepotOutReceiptPO(String packID, myTime time, String desti, Vehicle ve, String transID) {
        super(ReceiptType.DEPOTOUT);
        this.packID=packID;
        this.outTime=time;
        this.destination=desti;
        this.vehicle=ve;
        this.transID=transID;
    }

    public DepotOutReceiptPO(DepotOutReceiptVO vo){
        this(vo.getPackID(),vo.getOutTime(),vo.getDestination(),vo.getVehicle(),vo.getTransID());
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
