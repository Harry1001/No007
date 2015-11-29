package po.receiptpo;

import java.util.Date;

import typeDefinition.PackArrivalState;
import typeDefinition.ReceiptType;
import vo.receiptvo.StoreArrivalReceiptVO;

public class StoreArrivalReceiptPO extends ReceiptPO{

	private String orderID;
	private Date arriveTime;
    private String transReceiptID;
    private String fromPosition;
    private PackArrivalState arriveState;

    public StoreArrivalReceiptPO(String orderID,Date time, String transID, String fromPosi, PackArrivalState state) {
        super(ReceiptType.STOREARRIVAL);
        this.orderID=orderID;
        this.arriveTime=time;
        this.transReceiptID=transID;
        this.fromPosition=fromPosi;
        this.arriveState=state;
    }

    public StoreArrivalReceiptPO(StoreArrivalReceiptVO vo){
        this(vo.getOrderID(),vo.getArriveTime(),vo.getTransReceiptID(),vo.getFromPosition(),vo.getArriveState());
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
