package vo.infovo;

import po.infopo.AgencyPO;
import typeDefinition.InfoType;

public class AgencyVO extends InfoVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String agencyName;
	private String agencyType;
	private String agencyID;
	private String location;
	private int area;
	private int rent;

	public AgencyVO(String agencyName,String agencyType,
			String agencyID,String location,int area,int rent) {
		super(InfoType.AGENCY);
		// TODO Auto-generated constructor stub
		this.agencyName=agencyName;
		this.setAgencyType(agencyType);
		this.setAgencyID(agencyID);
		this.setLocation(location);
		this.setArea(area);
		this.setRent(rent);
	}

	public AgencyVO(AgencyPO po){
		this(po.getAgencyName(),po.getAgencyType(),po.getAgencyID(),po.getLocation(),po.getArea(),po.getRent());
	}


	public String getAgencyName(){
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public String getAgencyID() {
		return agencyID;
	}
	public void setAgencyID(String agencyID) {
		this.agencyID = agencyID;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public int getRent() {
		return rent;
	}
	public void setRent(int rent) {
		this.rent = rent;
	}
	public String getAgencyType() {
		return agencyType;
	}
	public void setAgencyType(String agencyType) {
		this.agencyType = agencyType;
	}


}
