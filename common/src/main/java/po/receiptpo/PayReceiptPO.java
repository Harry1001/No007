package po.receiptpo;

import typeDefinition.FeeType;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;
import vo.receiptvo.PayReceiptVO;

/**
 * Created by Harry on 2015/11/16.
 */
public class PayReceiptPO extends ReceiptPO {

    private myTime payTime;
    private double fee;
    private String payMan;//付款人
    private String payAccount;//付款帐号
    private FeeType payType;//付款条目

    public PayReceiptPO(myTime time, double money, String man, String account, FeeType type) {
        super(ReceiptType.PAY);
        this.payTime=time;
        this.fee=money;
        this.payMan=man;
        this.payAccount=account;
        this.payType=type;
    }

    public PayReceiptPO(PayReceiptVO vo){
        this(vo.getPayTime(),vo.getFee(),vo.getPayMan(),vo.getPayAccount(),vo.getPayType());
    }

    public myTime getPayTime() {
        return payTime;
    }

    public void setPayTime(myTime payTime) {
        this.payTime = payTime;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getPayMan() {
        return payMan;
    }

    public void setPayMan(String payMan) {
        this.payMan = payMan;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public FeeType getPayType() {
        return payType;
    }

    public void setPayType(FeeType payType) {
        this.payType = payType;
    }
}
