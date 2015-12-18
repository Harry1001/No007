package po.strategypo;

import java.io.Serializable;
import java.util.Vector;

import vo.strategyvo.DistanceVO;

public class DistancePO extends StrategyPO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<String> cities;
	private Vector<Vector<Object>> data;
	
	public DistancePO(Vector<String> cities,Vector<Vector<Object>> data){
		super("distance");
		this.cities=cities;
		this.data=data;
	}
	
	public DistancePO(DistanceVO vo){
		super("distance");
		this.cities=vo.getCities();
		this.data=vo.getData();
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
	
}
