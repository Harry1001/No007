package vo.salaryfeevo;

import typeDefinition.Job;

public class SalaryFeeVO {

	private String staffID;
	private String name;
	private Job position;
	private Double salary;
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
	public Job getPosition() {
		return position;
	}
	public void setPosition(Job position) {
		this.position = position;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
}
