package vo.receiptvo;

import java.util.Date;

import constent.Constent;
import po.receiptpo.PayReceiptPO;
import typeDefinition.FeeType;
import typeDefinition.ReceiptState;
import typeDefinition.ReceiptType;


/**
 * Created by Harry on 2015/11/16.
 */
public class PayReceiptVO extends ReceiptVO {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date payTime;
    private double fee;
    private String payMan;//付款人
    private String payAccount;//付款帐号
    private FeeType payType;//付款条目
    private ReceiptState state;//单据状态
    private String id;//付款单编号

    public PayReceiptVO(Date time, double money, String man, String account, FeeType type,ReceiptState state,String id) {
        super(ReceiptType.PAY);
        this.payTime=time;
        this.fee=money;
        this.payMan=man;
        this.payAccount=account;
        this.payType=type;
        this.state=state;
        this.id=id;
    }

    public PayReceiptVO(PayReceiptPO po){
        this(po.getPayTime(),po.getFee(),po.getPayMan(),po.getPayAccount(),po.getPayType(),po.getState(),po.getId());
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

    public String print(){
        String s="付款原因:"+ Constent.FEE_TYPE_STR[payType.ordinal()]+"，付款金额:"+fee+"元，付款日期："
                +Constent.DATE_FORMAT.format(payTime);
        return s;
    }

}
