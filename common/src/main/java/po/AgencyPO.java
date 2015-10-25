package po;

import typeDefinition.InfoType;

public class AgencyPO extends InfoPO {
	
	private String agencyName;
	private String agencyID;
	private String location;
	private int area;
	private int rent;

	public AgencyPO(InfoType type,String agencyName,String agencyID,
			String location,int area,int rent) {
		super(type);
		// TODO Auto-generated constructor stub
		this.agencyName=agencyName;
		this.setAgencyID(agencyID);
		this.setLocation(location);
		this.setArea(area);
		this.setRent(rent);
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
	

}
