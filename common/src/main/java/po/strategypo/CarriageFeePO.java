package po.strategypo;

import vo.strategyvo.CarriageFeeVO;

public class CarriageFeePO extends StrategyPO {
	
	private String transportType;
	private String departure;
	private String destination;
	//private double distance;
	private double price;
	
	public CarriageFeePO(String tranType,String depa,String dest,double p) {
		super("carriageFee");
		// TODO Auto-generated constructor stub
		this.transportType=tranType;
		this.departure=depa;
		this.destination=dest;
		//this.distance=dis;
		this.price=p;
	}
	public CarriageFeePO(CarriageFeeVO vo){
		super("carriageFee");
		this.transportType=vo.getTransportType();
		this.departure=vo.getDeparture();
		this.destination=vo.getDestination();
		//this.distance=dis;
		this.price=vo.getPrice();
	}
	public void setTransportType(String tranType){
		this.transportType= tranType;
	}
	public void setDeparture(String depa){
		this.departure= depa;
	}
	public void setDestination(String dest){
		this.destination= dest;
	}
	/*public double setDiatance(double dis){
		return dis;
	}*/
	public void setPrice(double p){
		this.price= p;
	}
	public String getTransportType(){
		return transportType;
	}
	public String getDeparture(){
		return departure;
	}
	public String getDestination(){
		return destination;
	}
	/*public double getDistance(){
		return distance;
	}*/
	public double getPrice(){
		return price;
	}
}
