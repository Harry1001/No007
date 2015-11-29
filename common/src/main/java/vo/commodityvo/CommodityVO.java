package vo.commodityvo;

import java.util.Date;

import po.commoditypo.CommodityPO;
import typeDefinition.Location;

public class CommodityVO {

	private String expressNumber;//快递编号
	private Date inTime;//入库日期
	private String destination;//目的地
	private Location storeloc;//存储位置
	
	
	public CommodityVO(String expressNumber,Date inTime,
			String destination,Location storeloc){
		this.expressNumber=expressNumber;
		this.inTime=inTime;
		this.destination=destination;
		this.setStoreloc(storeloc);
	}
	
	public CommodityVO(CommodityPO c){
		this.expressNumber = c.getExpressNumber();
		this.inTime = c.getInTime();
		this.destination = c.getDestination();
		this.setStoreloc(c.getStoreloc());
	}
	
	public CommodityVO(Location loc){
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
