package vo.strategyvo;

import java.util.Vector;

import po.strategypo.DistancePO;

public class DistanceVO extends StrategyVO{

	private Vector<String> cities;
	private Vector<Vector<Object>> data;
	
	public DistanceVO(Vector<String> cities,Vector<Vector<Object>> data){
		super("distance");
		this.cities=cities;
		this.data=data;
	}
	
	public DistanceVO(DistancePO po){
		super("distance");
		this.cities=po.getCities();
		this.data=po.getData();
	}
	public void setCities(Vector<String> cities){
		this.cities=cities;
	}
	
	public Vector<String> getCities(){
		return cities;
	}
	
	public void setData(Vector<Vector<Object>> data){
		this.data=data;
	}
	
	public Vector<Vector<Object>> getData(){
		return data;
	}
	/*private String city1;
	private String city2;
	private double distance;
	
	public DistanceVO(String city1,String city2,double distance){
		super("distance");
		this.city1=city1;
		this.city2=city2;
		this.distance=distance;
	}
	
	public DistanceVO(DistancePO po){
		super("distance");
		this.city1=po.getCity1();
		this.city2=po.getCity2();
		this.distance=po.getDistance();
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
	}*/
}
