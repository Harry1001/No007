package vo.receiptvo;

import po.receiptpo.ChargeReceiptPO;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/16.
 */
public class ChargeReceiptVO extends ReceiptVO {
    private myTime chargeTime;//收款日期
    private double fee;
    private String courier;//TODO 收款快递员，应该名字和工号都需要，待改进
    private ArrayList<String> orderIDs;//所有订单条形码号

    public ChargeReceiptVO(myTime time, double money, String courier, ArrayList<String> orderList) {
        super(ReceiptType.CHARGE);
        this.chargeTime=time;
        this.fee=money;
        this.courier=courier;
        this.orderIDs=orderList;
    }

    public ChargeReceiptVO(ChargeReceiptPO po){
        this(po.getChargeTime(),po.getFee(),po.getCourier(),po.getOrderIDs());
    }

    public myTime getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(myTime chargeTime) {
        this.chargeTime = chargeTime;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public ArrayList<String> getOrderIDs() {
        return orderIDs;
    }

    public void setOrderIDs(ArrayList<String> orderIDs) {
        this.orderIDs = orderIDs;
    }
}