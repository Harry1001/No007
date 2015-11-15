package po.strategypo;

import po.strategypo.StrategyPO;
import typeDefinition.Job;

public class SalaryStrategyPO extends StrategyPO {
	private Job position;
	private int baseSalary;
	private int workFrequency;
	private int allowance;
	public SalaryStrategyPO(Job p,int bs,int workFrequency,int allowance) {
		super("Salary");
		// TODO Auto-generated constructor stub
		this.position=p;
		this.baseSalary=bs;
		this.workFrequency=workFrequency;
		this.allowance=allowance;
	}
	public void setJob(Job job){
		this.position=job;
	}
	public Job getJob(){
		return position;
	}
	public void setBaseSalary(int bs){
		this.baseSalary=bs;
	}
	public int getBaseSalary(){
		return baseSalary;
	}
	public void setWorkFrequency(int wf){
		this.workFrequency=wf;
	}
	public int getWorkFrequency(){
		return workFrequency;
	}
	public void setAllowance(int al){
		this.allowance=al;
	}
	public int getAllowance(){
		return allowance;
	}
}
