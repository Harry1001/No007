package po.receiptpo;

import java.util.Date;

import typeDefinition.PackArrivalState;
import typeDefinition.ReceiptType;
import vo.receiptvo.HubArrivalReceiptVO;

/**
 * Created by Harry on 2015/11/16.
 */
public class HubArrivalReceiptPO extends ReceiptPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderID;
    private String hubID;
    private Date arriveTime;
    private  String transReceiptID;
    private  String fromPosition;
    private  PackArrivalState arriveState;

    public HubArrivalReceiptPO(String orderID,String hubID, Date arriveTime, String transReceiptID,
                               String fromPosition, PackArrivalState state) {
        super(ReceiptType.HUBARRIVAL);
        this.orderID=orderID;
        this.hubID=hubID;
        this.arriveTime=arriveTime;
        this.transReceiptID=transReceiptID;
        this.fromPosition=fromPosition;
        this.arriveState=state;
    }

    public HubArrivalReceiptPO(HubArrivalReceiptVO vo){
        this(vo.getOrderID(),vo.getHubID(),vo.getArriveTime(),vo.getTransReceiptID(),vo.getFromPosition(),
                vo.getArriveState());
    }

    public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getHubID() {
        return hubID;
    }

    public void setHubID(String hubID) {
        this.hubID = hubID;
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
