package vo.commodityvo;

import po.commoditypo.CommodityPO;
import typeDefinition.Location;
import typeDefinition.myTime;

public class CommodityVO {

	private String expressNumber;//快递编号
	private myTime inTime;//入库日期
	private String destination;//目的地
	private Location storeloc;//存储位置
	
	
	public CommodityVO(String expressNumber,myTime inTime,
			String destination,Location storeloc){
		this.expressNumber=expressNumber;
		this.inTime=inTime;
		this.destination=destination;
		this.storeloc=storeloc;
	}
	
	public CommodityVO(CommodityPO c){
		this.expressNumber = c.getExpressNumber();
		this.inTime = c.getInTime();
		this.destination = c.getDestination();
		this.storeloc = c.getStoreloc();
	}
	
	public CommodityVO(Location loc){
		expressNumber = null;
		inTime = new myTime();
		destination = null;
		storeloc = loc;
	}

	public String getExpressNumber() {
		return expressNumber;
	}
	
	public myTime getInTime(){
		return inTime;
	}
	
	public String getDestination(){
		return destination;
	}

}
