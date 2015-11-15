package po.strategypo;

public class CarriageFeePO extends StrategyPO {
	
	private String transportType;
	private String departure;
	private String destination;
	private double distance;
	
	public CarriageFeePO(String tranType,String depa,String dest,double dis) {
		super("carriageFee");
		// TODO Auto-generated constructor stub
		this.transportType=tranType;
		this.departure=depa;
		this.destination=dest;
		this.distance=dis;
	}
	public String setTransportType(String tranType){
		return tranType;
	}
	public String setDeparture(String depa){
		return depa;
	}
	public String setDestination(String dest){
		return dest;
	}
	public double setDiatance(double dis){
		return dis;
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
	public double getDistance(){
		return distance;
	}
}
