package po.receiptpo;

import typeDefinition.ReceiptType;
import typeDefinition.myTime;
import vo.receiptvo.ChargeReceiptVO;
import vo.receiptvo.ReceiptVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/16.
 */
public class ChargeReceiptPO extends ReceiptPO {

    private myTime chargeTime;//收款日期
    private double fee;
    private String courier;//TODO 收款快递员，应该名字和工号都需要，待改进
    private ArrayList<String> orderIDs;//所有订单条形码号

    public ChargeReceiptPO(myTime time, double money, String courier, ArrayList<String> orderList) {
        super(ReceiptType.CHARGE);
        this.chargeTime=time;
        this.fee=money;
        this.courier=courier;
        this.orderIDs=orderList;
    }

    public ChargeReceiptPO(ChargeReceiptVO vo){
        this(vo.getChargeTime(),vo.getFee(),vo.getCourier(),vo.getOrderIDs());
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
