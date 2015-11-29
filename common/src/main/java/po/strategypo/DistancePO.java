package po.strategypo;

import vo.strategyvo.DistanceVO;

public class DistancePO extends StrategyPO{
	private String city1;
	private String city2;
	private double distance;
	
	public DistancePO(String city1,String city2,double distance){
		super("distance");
		this.city1=city1;
		this.city2=city2;
		this.distance=distance;
	}
	
	public DistancePO(DistanceVO vo){
		super("distance");
		this.city1=vo.getCity1();
		this.city2=vo.getCity2();
		this.distance=vo.getDistance();
	}
	public void setCity1(String c1){
		this.city1=c1;
	}
	
	public String getCity1(){
		return city1;
	}
	
	public void setCity2(String c2){
		this.city2=c2;
	}
	
	public String getCity2(){
		return city2;
	}
	
	public void setDistance(double dis){
		this.distance=dis;
	}
	
	public double getDistance(){
		return distance;
	}
}
