package vo.receiptvo;

import po.receiptpo.EntruckReceiptPO;
import typeDefinition.ReceiptType;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/16.
 */
public class EntruckReceiptVO extends ReceiptVO {
    private Date entruckDate;//装车日期
    private String transportID;//营业厅／中转中心汽运编号
    private String arriveLoc;//到达地
    private String truckID;//车辆代号
    private ArrayList<String> orderNum;//本次装箱所有订单条形码号
    private double transportFee;//运费

    public EntruckReceiptVO(Date entruckDate,String transportID,String arriveLoc,
                            String truckID,ArrayList<String> orderNum,double transportFee) {
        super(ReceiptType.ENTRUCK);
        // TODO Auto-generated constructor stub
        this.setEntruckDate(entruckDate);
        this.setTransportID(transportID);
        this.setArriveLoc(arriveLoc);
        this.setTruckID(truckID);
        this.setOrderNum(orderNum);
        this.setTransportFee(transportFee);
    }

    public EntruckReceiptVO(EntruckReceiptPO po){
        this(po.getEntruckDate(),po.getTransportID(),po.getArriveLoc(),
                po.getTruckID(),po.getOrderNum(),po.getTransportFee());
    }

    public Date getEntruckDate() {
        return entruckDate;
    }

    public void setEntruckDate(Date entruckDate) {
        this.entruckDate = entruckDate;
    }

    public String getTransportID() {
        return transportID;
    }

    public void setTransportID(String transportID) {
        this.transportID = transportID;
    }

    public String getArriveLoc() {
        return arriveLoc;
    }

    public void setArriveLoc(String arriveLoc) {
        this.arriveLoc = arriveLoc;
    }

    public String getTruckID() {
        return truckID;
    }

    public void setTruckID(String truckID) {
        this.truckID = truckID;
    }

    public ArrayList<String> getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(ArrayList<String> orderNum) {
        this.orderNum = orderNum;
    }

    public double getTransportFee() {
        return transportFee;
    }

    public void setTransportFee(double transportFee) {
        this.transportFee = transportFee;
    }

}
