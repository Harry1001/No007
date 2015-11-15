package vo.infovo;

import po.infopo.StaffPO;
import typeDefinition.InfoType;
import typeDefinition.Job;
import typeDefinition.myTime;
import vo.infovo.InfoVO;

public class StaffVO extends InfoVO {

	private String staffID;//工号
	private String name;//姓名
	private String gender;//性别
	private myTime birthday;
	private Job position;//所属部门
	private int basicSalary;//基本工资
	private int workFrequency;//只有司机和快递员这项数据有意义，其他人都是0
	
	public StaffVO(String staffID, String name, String gender, myTime birthday, Job position
			, int basicSalary, int workFrequency) {
		super(InfoType.STAFF);
		// TODO Auto-generated constructor stub
		this.staffID=staffID;
		this.name=name;
		this.gender=gender;
		this.birthday=birthday;
		this.position=position;
		this.basicSalary=basicSalary;
		this.workFrequency=workFrequency;
	}
	
	public StaffVO(StaffPO s){
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

	public myTime getBirthday() {
		return birthday;
	}

	public void setBirthday(myTime birthday) {
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