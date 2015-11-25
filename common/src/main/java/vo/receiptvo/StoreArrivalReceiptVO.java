package vo.receiptvo;

import java.sql.Date;

import po.receiptpo.StoreArrivalReceiptPO;
import typeDefinition.PackArrivalState;
import typeDefinition.ReceiptType;

/**
 * Created by Harry on 2015/11/16.
 */
public class StoreArrivalReceiptVO extends ReceiptVO {
	private String orderID;
	private Date arriveTime;
    private String transReceiptID;
    private String fromPosition;
    private PackArrivalState arriveState;

    public StoreArrivalReceiptVO(String orderID,Date time, String transID, String fromPosi, PackArrivalState state) {
        super(ReceiptType.STOREARRIVAL);
        this.orderID=orderID;
        this.arriveTime=time;
        this.transReceiptID=transID;
        this.fromPosition=fromPosi;
        this.arriveState=state;
    }

    public StoreArrivalReceiptVO(StoreArrivalReceiptPO po){
        this(po.getOrderID(),po.getArriveTime(),po.getTransReceiptID(),po.getFromPosition(),po.getArriveState());
    }

    public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getTransReceiptID() {
        return transReceiptID;
    }

    public void setTransReceiptID(String transReceiptID) {
        this.transReceiptID = transReceiptID;
    }

    public String getFromPosition() {
        return fromPosition;
    }

    public void setFromPosition(String fromPosition) {
        this.fromPosition = fromPosition;
    }

    public PackArrivalState getArriveState() {
        return arriveState;
    }

    public void setArriveState(PackArrivalState arriveState) {
        this.arriveState = arriveState;
    }
}
