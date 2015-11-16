package vo.receiptvo;

import po.receiptpo.StoreArrivalReceiptPO;
import typeDefinition.PackArrivalState;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;

/**
 * Created by Harry on 2015/11/16.
 */
public class StoreArrivalReceiptVO extends ReceiptVO {
    private myTime arriveTime;
    private String transReceiptID;
    private String fromPosition;
    private PackArrivalState arriveState;

    public StoreArrivalReceiptVO(myTime time, String transID, String fromPosi, PackArrivalState state) {
        super(ReceiptType.STOREARRIVAL);
        this.arriveTime=time;
        this.transReceiptID=transID;
        this.fromPosition=fromPosi;
        this.arriveState=state;
    }

    public StoreArrivalReceiptVO(StoreArrivalReceiptPO po){
        this(po.getArriveTime(),po.getTransReceiptID(),po.getFromPosition(),po.getArriveState());
    }

    public myTime getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(myTime arriveTime) {
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
