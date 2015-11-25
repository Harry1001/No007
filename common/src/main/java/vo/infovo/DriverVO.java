package vo.infovo;

import java.util.Date;

import po.infopo.DriverPO;
import typeDefinition.InfoType;

public class DriverVO extends InfoVO {

private static final long serialVersionUID = 1L;
	
	private String driverID;
	private String name;
	private Date birthday;
	private String IDNum;	//身份证号
	private String phoneNum;
	private String gender;
	private Date licenseLimit;//行驶证期限

	public DriverVO(String driverID,String name,Date birthday,
			String IDNum,String phoneNum,String gender,Date licenseLimit) {
		super(InfoType.DRIVER);
		// TODO Auto-generated constructor stub
		this.setDriverID(driverID);
		this.setName(name);
		this.setBirthday(birthday);
		this.setIDNum(IDNum);
		this.setPhoneNum(phoneNum);
		this.setGender(gender);
		this.setLicenseLimit(licenseLimit);
	}
	
	public DriverVO(DriverPO d){
		this(d.getDriverID(),d.getName(),d.getBirthday(),d.getIDNum(),
				d.getPhoneNum(),d.getGender(),d.getLicenseLimit());
	}
	
	public DriverVO(InfoType type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	public Date getLicenseLimit() {
		return licenseLimit;
	}

	public void setLicenseLimit(Date licenseLimit) {
		this.licenseLimit = licenseLimit;
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

	public String getIDNum() {
		return IDNum;
	}

	public void setIDNum(String iDNum) {
		IDNum = iDNum;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDriverID() {
		return driverID;
	}

	public void setDriverID(String driverID) {
		this.driverID = driverID;
	}

}
