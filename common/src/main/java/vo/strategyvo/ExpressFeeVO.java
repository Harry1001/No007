package vo.strategyvo;

public class ExpressFeeVO extends StrategyVO {
	private double weight;
	private double volumn;
	private double distance;
	private String expressType;
	private String packType;
	public ExpressFeeVO(double w,double v,double dis,String eType,String pType) {
		super("expressFee");
		// TODO Auto-generated constructor stub
		this.weight=w;
		this.volumn=v;
		this.distance=dis;
		this.expressType=eType;
		this.packType=pType;
	}
	public double getWeight(){
		return weight;
	}
	public double getVolumn(){
		return  volumn;
	}
	public double getDistance(){
		return distance;
	}
	public String getExpressType(){
		return expressType;
	}
	public String getPackType(){
		return packType;
	}
	public double setWeight(double w){
		return w;
	}
	public double setVolumn(double v){
		return v;
	}
	public double setDistance(double dis){
		return dis;
	}
	public String setExpressType(String eType){
		return eType;
	}
	public String setPackType(String pType){
		return pType;
	}
}
