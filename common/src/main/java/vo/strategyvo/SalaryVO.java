package vo.strategyvo;

import po.strategypo.SalaryStrategyPO;
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
	public SalaryVO(SalaryStrategyPO po){
		super("Salary");
		this.position=po.getJob();
		this.baseSalary=po.getBaseSalary();
		this.workFrequency=po.getWorkFrequency();
		this.allowance=po.getAllowance();
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
	public void setAllowance(int al){
		this.allowance=al;
	}
	public int getAllowance(){
		return allowance;
	}
	public void setWorkFrequency(int workFrequency){
		this.workFrequency=workFrequency;
	}
	public int getWorkFrequency(){
		return workFrequency;
	}
}
