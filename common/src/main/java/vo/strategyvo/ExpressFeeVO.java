package vo.strategyvo;

public class ExpressFeeVO extends StrategyVO {
	private double weight;
	private double volumn;
	//private double distance;
	private String expressType;
	private String packType;
	private double price;
	public ExpressFeeVO(double w,double v,String eType,String pType,double price) {
		super("expressFee");
		// TODO Auto-generated constructor stub
		this.weight=w;
		this.volumn=v;
		//this.distance=dis;
		this.expressType=eType;
		this.packType=pType;
		this.price=price;
	}
	public double getWeight(){
		return weight;
	}
	public double getVolumn(){
		return  volumn;
	}
	/*public double getDistance(){
		return distance;
	}*/
	public String getExpressType(){
		return expressType;
	}
	public String getPackType(){
		return packType;
	}
	public double getPrice(){
		return price;
	}
	public void setWeight(double w){
		this.weight= w;
	}
	public void setVolumn(double v){
		this.volumn= v;
	}
	/*public void setDistance(double dis){
		this.distance= dis;
	}*/
	public void setExpressType(String eType){
		this.expressType= eType;
	}
	public void setPackType(String pType){
		this.packType= pType;
	}
	public void setPrice(double price){
		this.price=price;
	}
}
