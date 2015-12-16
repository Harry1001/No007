package vo.receiptvo;

import java.util.Date;

import po.receiptpo.DepotInReceiptPO;
import typeDefinition.Location;
import typeDefinition.ReceiptType;

/**
 * Created by Harry on 2015/11/16.
 */
public class DepotInReceiptVO extends ReceiptVO {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String packID;
    private Date inTime;
    private String destination;
    private Location location;

    public DepotInReceiptVO(String packID, Date time, String desti, Location loc) {
        super(ReceiptType.DEPOTIN);
        this.packID=packID;
        this.inTime=time;
        this.destination=desti;
        this.location=loc;
    }

    public DepotInReceiptVO(DepotInReceiptPO po){
        this(po.getPackID(),po.getInTime(),po.getDestination(),po.getLocation());
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
