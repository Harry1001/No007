package vo.strategyvo;

import typeDefinition.Job;
import vo.strategyvo.StrategyVO;

public class SalaryVO extends StrategyVO {
	private Job position;
	private int baseSalary;
	private int allowance;
	private int workFrequency;
	public SalaryVO(Job p,int bs,int allowance,int workFrequency){
		super("Salary");
		this.position=p;
		this.baseSalary=bs;
		this.allowance=allowance;
		this.workFrequency=workFrequency;
	}
	public Job setJob(Job job){
		return job;
	}
	public Job getJob(){
		return position;
	}
	public int setBaseSalary(int bs){
		return bs;
	}
	public int getBaseSalary(){
		return baseSalary;
	}
	public int setAllowance(int al){
		return al;
	}
	public int getAllowance(){
		return allowance;
	}
	public int setWorkFrequency(int workFrequency){
		return workFrequency;
	}
	public int getWorkFrequency(){
		return workFrequency;
	}
}
