package vo.receiptvo;

import po.receiptpo.HubArrivalReceiptPO;
import typeDefinition.PackArrivalState;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;

/**
 * Created by Harry on 2015/11/16.
 */
public class HubArrivalReceiptVO extends ReceiptVO {
    private String hubID;
    private myTime arriveTime;
    private  String transReceiptID;
    private  String fromPosition;
    private PackArrivalState arriveState;

    public HubArrivalReceiptVO(String hubID, myTime arriveTime, String transReceiptID,
                               String fromPosition, PackArrivalState state) {
        super(ReceiptType.HUBARRIVAL);
        this.hubID=hubID;
        this.arriveTime=arriveTime;
        this.transReceiptID=transReceiptID;
        this.fromPosition=fromPosition;
        this.arriveState=state;
    }

    public HubArrivalReceiptVO(HubArrivalReceiptPO po){
        this(po.getHubID(),po.getArrveTime(),po.getTransReceiptID(),po.getFromPosition(),
                po.getArriveState());
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
