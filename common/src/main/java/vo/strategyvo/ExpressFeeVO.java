package vo.strategyvo;

import po.strategypo.ExpressFeePO;

public class ExpressFeeVO extends StrategyVO {
	private double economicPrice;//经济快递的单价
	private double standardPrice;//标准快递的单价
	private double specialPrice;//次晨特快的单价
	public ExpressFeeVO(double ep,double stdp,double sp){
		super("expressFee");
		this.economicPrice=ep;
		this.standardPrice=stdp;
		this.specialPrice=sp;
	}
	
	public ExpressFeeVO(ExpressFeePO po){
		super("expressFee");
		this.economicPrice=po.getEcoPrice();
		this.standardPrice=po.getStdPrice();
		this.specialPrice=po.getSpePrice();
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
