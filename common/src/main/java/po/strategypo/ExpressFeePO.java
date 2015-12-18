package po.strategypo;

import vo.strategyvo.ExpressFeeVO;

public class ExpressFeePO extends StrategyPO {

	private static final long serialVersionUID = 1L;

	private double economicPrice;//经济快递的单价
	private double standardPrice;//标准快递的单价
	private double specialPrice;//次晨特快的单价
	public ExpressFeePO(double ep,double stdp,double sp){
		super("expressFee");
		this.economicPrice=ep;
		this.standardPrice=stdp;
		this.specialPrice=sp;
	}
	
	public ExpressFeePO(ExpressFeeVO vo){
		super("expressFee");
		this.economicPrice=vo.getEcoPrice();
		this.standardPrice=vo.getStdPrice();
		this.specialPrice=vo.getSpePrice();
	}
	public void setEcoPrice(double ep){
		this.economicPrice=ep;
	}
	
	public double getEcoPrice(){
		return economicPrice;
	}
	
	public void setStdPrice(double stdp){
		this.standardPrice=stdp;
	}
	
	public double getStdPrice(){
		return standardPrice;
	}
	
	public void setSpePrice(double sp){
		this.specialPrice=sp;
	}
	
	public double getSpePrice(){
		return specialPrice;
	}
}
