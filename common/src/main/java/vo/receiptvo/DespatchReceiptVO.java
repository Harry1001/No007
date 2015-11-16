package vo.receiptvo;

import po.receiptpo.DespatchReceiptPO;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;

/**
 * Created by Harry on 2015/11/15.
 */
public class DespatchReceiptVO extends ReceiptVO {

    private myTime arrivalTime;
    private String orderNum;
    private String despatchMan;

    public DespatchReceiptVO(myTime arrival,String orderNum,
                             String despatchMan) {
        super(ReceiptType.DESPATCH);
        // TODO Auto-generated constructor stub
        this.setArrivalTime(arrival);
        this.setOrderNum(orderNum);
        this.setDespatchMan(despatchMan);
    }

    public DespatchReceiptVO(DespatchReceiptPO po){
        this(po.getArrivalTime(),po.getOrderNum(),po.getDespatchMan());
    }

    public myTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(myTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getDespatchMan() {
        return despatchMan;
    }

    public void setDespatchMan(String despatchMan) {
        this.despatchMan = despatchMan;
    }
}
