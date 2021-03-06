package po.receiptpo;

import java.util.Date;

import typeDefinition.FeeType;
import typeDefinition.ReceiptState;
import typeDefinition.ReceiptType;
import vo.receiptvo.PayReceiptVO;

/**
 * Created by Harry on 2015/11/16.
 */
public class PayReceiptPO extends ReceiptPO {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date payTime;
    private double fee;
    private String payMan;//付款人
    private String payAccount;//付款帐号
    private FeeType payType;//付款条目
    private ReceiptState state;
    private String id;
    
    public PayReceiptPO(Date time, double money, String man, String account, FeeType type,ReceiptState state,String id) {
        super(ReceiptType.PAY);
        this.payTime=time;
        this.fee=money;
        this.payMan=man;
        this.payAccount=account;
        this.payType=type;
        this.state=state;
        this.id=id;
    }

    public PayReceiptPO(PayReceiptVO vo){
        this(vo.getPayTime(),vo.getFee(),vo.getPayMan(),vo.getPayAccount(),vo.getPayType(),vo.getState(),vo.getId());
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
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
