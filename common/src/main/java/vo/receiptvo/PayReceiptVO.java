package vo.receiptvo;

import po.receiptpo.PayReceiptPO;
import typeDefinition.FeeType;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;

/**
 * Created by Harry on 2015/11/16.
 */
public class PayReceiptVO extends ReceiptVO {
    private myTime payTime;
    private double fee;
    private String payMan;//付款人
    private String payAccount;//付款帐号
    private FeeType payType;//付款条目

    public PayReceiptVO(myTime time, double money, String man, String account, FeeType type) {
        super(ReceiptType.PAY);
        this.payTime=time;
        this.fee=money;
        this.payMan=man;
        this.payAccount=account;
        this.payType=type;
    }

    public PayReceiptVO(PayReceiptPO po){
        this(po.getPayTime(),po.getFee(),po.getPayMan(),po.getPayAccount(),po.getPayType());
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