package po;

import typeDefinition.InfoType;
import typeDefinition.myTime;

public class DriverPO extends InfoPO {


	private static final long serialVersionUID = 1L;
	
	private String driverID;
	private String name;
	private myTime birthday;
	private String IDNum;	//身份证号
	private String phoneNum;
	private String gender;
	private myTime licenseLimit;//行驶证期限

	public DriverPO(InfoType type,String driverID,String name,myTime birthday,
			String IDNum,String phoneNum,String gender,myTime licenseLimit) {
		super(type);
		// TODO Auto-generated constructor stub
		this.driverID=driverID;
		this.name=name;
		this.birthday=birthday;
		this.IDNum=IDNum;
		this.phoneNum=phoneNum;
		this.gender=gender;
		this.licenseLimit=licenseLimit;
	}

	public String getDriverID() {
		return driverID;
	}

	public void setDriverID(String driverID) {
		this.driverID = driverID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public myTime getBirthday() {
		return birthday;
	}

	public void setBirthday(myTime birthday) {
		this.birthday = birthday;
	}

	public String getIDNum() {
		return IDNum;
	}

	public void setIDNum(String iDNum) {
		IDNum = iDNum;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public myTime getLicenseLimit() {
		return licenseLimit;
	}

	public void setLicenseLimit(myTime licenseLimit) {
		this.licenseLimit = licenseLimit;
	}

}
