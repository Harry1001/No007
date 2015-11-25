package po.infopo;

import java.util.Date;

import typeDefinition.InfoType;
import typeDefinition.Job;
import vo.infovo.StaffVO;

public class StaffPO extends InfoPO {

	private String staffID;
	private String name;
	private String gender;
	private Date birthday;
	private Job position;
	private int basicSalary;
	private int workFrequency;//只有司机和快递员这项数据有意义，其他人都是0
	
	public StaffPO(String staffID,String name,String gender,
			Date birthday,Job position,int basicSalary) {
		super(InfoType.STAFF);
		// TODO Auto-generated constructor stub
		this.staffID=staffID;
		this.name=name;
		this.gender=gender;
		this.birthday=birthday;
		this.position=position;
		this.basicSalary=basicSalary;
		this.workFrequency=0;
	}

	public StaffPO(StaffVO s){
		super(InfoType.STAFF);
		this.staffID = s.getStaffID();
		this.name = s.getName();
		this.gender = s.getGender();
		this.birthday = s.getBirthday();
		this.position = s.getPosition();
		this.basicSalary = s.getBasicSalary();
		this.workFrequency = s.getWorkFrequency();
	}
	
	public String getStaffID() {
		return staffID;
	}

	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Job getPosition() {
		return position;
	}

	public void setPosition(Job position) {
		this.position = position;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(int basicSalary) {
		this.basicSalary = basicSalary;
	}

	public int getWorkFrequency() {
		return workFrequency;
	}

	public void setWorkFrequency(int workFrequency) {
		this.workFrequency = workFrequency;
	}
	
	//快递员每完成一单业务，司机每运货一次就加一
	public void addWorkFrequency() {
		this.workFrequency++;
	}

}
