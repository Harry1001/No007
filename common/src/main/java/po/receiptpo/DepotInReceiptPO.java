package po.receiptpo;

import java.sql.Date;

import typeDefinition.Location;
import typeDefinition.ReceiptType;
import vo.receiptvo.DepotInReceiptVO;

/**
 * Created by Harry on 2015/11/16.
 */
public class DepotInReceiptPO extends ReceiptPO {

    private String packID;
    private Date inTime;
    private String destination;
    private Location location;

    public DepotInReceiptPO(String packID, Date time, String desti, Location loc) {
        super(ReceiptType.DEPOTIN);
        this.packID=packID;
        this.inTime=time;
        this.destination=desti;
        this.location=loc;
    }

    public DepotInReceiptPO(DepotInReceiptVO vo){
        this(vo.getPackID(),vo.getInTime(),vo.getDestination(),vo.getLocation());
    }

    public String getPackID() {
        return packID;
    }

    public void setPackID(String packID) {
        this.packID = packID;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
