package po;

import typeDefinition.Job;

public class SalaryStrategyPO extends StrategyPO{
	private Job position;
	private int baseSalary;
	private int workFrequency;
	public SalaryStrategyPO(Job p,int bs,int workFrequency) {
		super("Salary");
		// TODO Auto-generated constructor stub
		this.position=p;
		this.baseSalary=bs;
		this.workFrequency=workFrequency;
	}
	public Job getJob(){
		return position;
	}
	public int getBaseSalary(){
		return baseSalary;
	}
	public int getWorkFrequency(){
		return workFrequency;
	}
}
