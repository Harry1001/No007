package po.receiptpo;

import typeDefinition.PackArrivalState;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;
import vo.receiptvo.HubArrivalReceiptVO;

/**
 * Created by Harry on 2015/11/16.
 */
public class HubArrivalReceiptPO extends ReceiptPO {

    private String hubID;
    private myTime arriveTime;
    private  String transReceiptID;
    private  String fromPosition;
    private  PackArrivalState arriveState;

    public HubArrivalReceiptPO(String hubID, myTime arriveTime, String transReceiptID,
                               String fromPosition, PackArrivalState state) {
        super(ReceiptType.HUBARRIVAL);
        this.hubID=hubID;
        this.arriveTime=arriveTime;
        this.transReceiptID=transReceiptID;
        this.fromPosition=fromPosition;
        this.arriveState=state;
    }

    public HubArrivalReceiptPO(HubArrivalReceiptVO vo){
        this(vo.getHubID(),vo.getArrveTime(),vo.getTransReceiptID(),vo.getFromPosition(),
                vo.getArriveState());
    }

    public String getHubID() {
        return hubID;
    }

    public void setHubID(String hubID) {
        this.hubID = hubID;
    }

    public myTime getArrveTime() {
        return arriveTime;
    }

    public void setArrveTime(myTime arriveTime) {
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
