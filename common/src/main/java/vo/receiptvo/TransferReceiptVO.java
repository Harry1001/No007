package vo.receiptvo;

import po.receiptpo.TransferReceiptPO;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/16.
 */
public class TransferReceiptVO extends ReceiptVO {
    private String transferType;//中转方式
    private myTime transferDate;//中转日期
    private String transferID;//中转单编号
    private String vehicleID;//飞机／铁路）班次或者汽车的车牌号
    private String departLoc;//出发地
    private String arriveLoc;//到达地
    private int counterID;//货柜号
    private ArrayList<String> orderID;//本次装箱所有货物的单号

    public TransferReceiptVO(String transferType,myTime transferDate,
                             String transferID,String vehicleID,String departLoc,String arriveLoc,
                             int counterID,ArrayList<String> orderID) {
        super(ReceiptType.TRANSFER);
        // TODO Auto-generated constructor stub
        this.transferType=transferType;
        this.transferDate=transferDate;
        this.transferID=transferID;
        this.vehicleID=vehicleID;
        this.departLoc=departLoc;
        this.arriveLoc=arriveLoc;
        this.counterID=counterID;
        this.orderID=orderID;
    }

    public TransferReceiptVO(TransferReceiptPO po){
        this(po.getTransferType(),po.getTransferDate(),po.getTransferID(),po.getVehicleID(),
                po.getDepartLoc(),po.getArriveLoc(),po.getCounterID(),po.getOrderID());
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public myTime getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(myTime transferDate) {
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

}
