package po.infopo;


import po.infopo.InfoPO;
import typeDefinition.InfoType;
import typeDefinition.myTime;

public class TruckPO extends InfoPO {

	private static final long serialVersionUID = 1L;
	
	private String truckID;		//车辆代号
	private String licenceID;	//车牌号
	private String engineID;	//发动机号
	private String chassisID;	//底盘号
	private myTime buyTime;		//购买时间
	private int serveTime;	//服役时间,以年计算	
	
	public TruckPO(String truckID,String licenceID,String engineID,
			String chassisID,myTime buyTime,int serveTime) {
		super(InfoType.TRUCK);
		// TODO Auto-generated constructor stub
		this.setTruckID(truckID);
		this.setLicenceID(licenceID);
		this.setEngineID(engineID);
		this.setChassisID(chassisID);
		this.setBuyTime(buyTime);
		this.setServeTime(serveTime);
	}

	public String getTruckID() {
		return truckID;
	}

	public void setTruckID(String truckID) {
		this.truckID = truckID;
	}

	public String getLicenceID() {
		return licenceID;
	}

	public void setLicenceID(String licenceID) {
		this.licenceID = licenceID;
	}

	public String getEngineID() {
		return engineID;
	}

	public void setEngineID(String engineID) {
		this.engineID = engineID;
	}

	public String getChassisID() {
		return chassisID;
	}

	public void setChassisID(String chassisID) {
		this.chassisID = chassisID;
	}

	public myTime getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(myTime buyTime) {
		this.buyTime = buyTime;
	}

	public int getServeTime() {
		return serveTime;
	}

	public void setServeTime(int serveTime) {
		this.serveTime = serveTime;
	}
	
	

}
