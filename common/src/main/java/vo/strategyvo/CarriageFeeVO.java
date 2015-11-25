package vo.strategyvo;

import po.strategypo.CarriageFeePO;
import po.strategypo.StrategyPO;

public class CarriageFeeVO extends StrategyPO {
	private double planePrice;
	private double trainPrice;
	private double busPrice;
	
	
	public CarriageFeeVO(double pp,double tp,double bp) {
		super("carriageFee");
		this.planePrice=pp;
		this.trainPrice=tp;
		this.busPrice=bp;
		
	}
	
	public CarriageFeeVO(CarriageFeePO po){
		super("carriageFee");
		this.planePrice=po.getPlanePrice();
		this.trainPrice=po.getTrainPrice();
		this.busPrice=po.getBusPrice();
		
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