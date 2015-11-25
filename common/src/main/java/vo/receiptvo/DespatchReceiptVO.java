package vo.receiptvo;

import java.sql.Date;

import po.receiptpo.DespatchReceiptPO;
import typeDefinition.ReceiptType;

/**
 * Created by Harry on 2015/11/15.
 */
public class DespatchReceiptVO extends ReceiptVO {

    private Date arrivalTime;
    private String orderNum;
    private String despatchMan;

    public DespatchReceiptVO(Date arrival,String orderNum,
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

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
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
