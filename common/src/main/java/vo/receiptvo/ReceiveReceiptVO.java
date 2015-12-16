package vo.receiptvo;

import java.util.Date;

import po.receiptpo.ReceiveReceiptPO;
import typeDefinition.ReceiptType;

/**
 * Created by Harry on 2015/11/16.
 */
public class ReceiveReceiptVO extends ReceiptVO {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String receiveNum;
    private String receiver;
    private Date receiveTime;

    public ReceiveReceiptVO(String receiveNum,String receiver,Date receiveTime) {
        super(ReceiptType.RECEIVE);
        // TODO Auto-generated constructor stub
        this.setReceiveNum(receiveNum);
        this.setReceiver(receiver);
        this.setReceiveTime(receiveTime);
    }

    public ReceiveReceiptVO(ReceiveReceiptPO po){
        this(po.getReceiveNum(),po.getReceiver(),po.getReceiveTime());
    }

    public String getReceiveNum() {
        return receiveNum;
    }

    public void setReceiveNum(String receiveNum) {
        this.receiveNum = receiveNum;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }
}
