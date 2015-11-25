package po.strategypo;

import vo.strategyvo.CarriageFeeVO;

public class CarriageFeePO extends StrategyPO {
	private double planePrice;
	private double trainPrice;
	private double busPrice;
	
	
	public CarriageFeePO(double pp,double tp,double bp) {
		super("carriageFee");
		this.planePrice=pp;
		this.trainPrice=tp;
		this.busPrice=bp;
		
	}
	
	public CarriageFeePO(CarriageFeeVO vo){
		super("carriageFee");
		this.planePrice=vo.getPlanePrice();
		this.trainPrice=vo.getTrainPrice();
		this.busPrice=vo.getBusPrice();
		
	}
	
	public void setPlanePrice(double pp){
		this.planePrice=pp;
	}
	
	public double getPlanePrice(){
		return planePrice;
	}
	public void setTrainPrice(double tp){
		this.trainPrice=tp;
	}
	
	public double getTrainPrice(){
		return trainPrice;
	
	}
	
	
	public void setBusPrice(double bp){
		this.busPrice=bp;
	}
	
	public double getBusPrice(){
		return busPrice;
	}
}