package po.commoditypo;

import java.io.Serializable;
import java.util.Date;

import typeDefinition.Location;


public class CommodityPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String expressNumber;//快递编号
	private Date inTime;//入库日期
	private String destination;//目的地
	private Location storeloc;//存储位置
	
	
	public CommodityPO(String expressNumber,Date inTime,
			String destination,Location storeloc){
		this.expressNumber=expressNumber;
		this.inTime=inTime;
		this.destination=destination;
		this.setStoreloc(storeloc);
	}
	
	public CommodityPO(Location loc){
		expressNumber = null;
		inTime = new Date(System.currentTimeMillis());
		destination = null;
		setStoreloc(loc);
	}

	public String getExpressNumber() {
		return expressNumber;
	}
	
	public Date getInTime(){
		return inTime;
	}
	
	public String getDestination(){
		return destination;
	}

	public Location getStoreloc() {
		return storeloc;
	}

	public void setStoreloc(Location storeloc) {
		this.storeloc = storeloc;
	}
}
