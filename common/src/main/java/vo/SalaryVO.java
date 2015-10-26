package vo;

import typeDefinition.Job;

public class SalaryVO extends StrategyVO{
	private Job position;
	private int baseSalary;
	private int workFrequency;
	
	public SalaryVO(Job p,int bs,int workFrequency){
		super("Salary");
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
