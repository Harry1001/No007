package po.infopo;

import java.sql.Date;

import typeDefinition.InfoType;
import vo.infovo.DriverVO;

public class DriverPO extends InfoPO {


	private static final long serialVersionUID = 1L;
	
	private String driverID;
	private String name;
	private Date birthday;
	private String IDNum;	//身份证号
	private String phoneNum;
	private String gender;
	private Date licenseLimit;//行驶证期限

	public DriverPO(String driverID,String name,Date birthday,
			String IDNum,String phoneNum,String gender,Date licenseLimit) {
		super(InfoType.DRIVER);
		// TODO Auto-generated constructor stub
		this.driverID=driverID;
		this.name=name;
		this.birthday=birthday;
		this.IDNum=IDNum;
		this.phoneNum=phoneNum;
		this.gender=gender;
		this.licenseLimit=licenseLimit;
	}

	public DriverPO(DriverVO d){
		super(InfoType.DRIVER);
		this.setDriverID(d.getDriverID());
		this.setName(d.getName());
		this.setBirthday(d.getBirthday());
		this.setIDNum(d.getIDNum());
		this.setPhoneNum(d.getPhoneNum());
		this.setGender(d.getGender());
		this.setLicenseLimit(d.getLicenseLimit());
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
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

	public Date getLicenseLimit() {
		return licenseLimit;
	}

	public void setLicenseLimit(Date licenseLimit) {
		this.licenseLimit = licenseLimit;
	}

}
