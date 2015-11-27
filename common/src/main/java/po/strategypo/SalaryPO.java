package po.strategypo;

import po.strategypo.StrategyPO;
import vo.strategyvo.SalaryVO;



public class SalaryPO extends StrategyPO {
	private int mailerBaseSalary;
	private int driverBaseSalary;
	private int managerBaseSalary;
	private int accountantBaseSalary;
	private int storesalesmanBaseSalary;
	private int hubsalesmanBaseSalary;
	private int storekeeperBaseSalary;
	private int administerBaseSalary;
	private int mailerAllowance;
	private int driverAllowance;
	
	public SalaryPO(int acbs,int adbs,int dbs,int hbs,int mbs,int manbs,int skbs,int ssbs,int dal,int mal) {
		super("Salary");
		// TODO Auto-generated constructor stub
		this.mailerBaseSalary=mbs;
		this.driverBaseSalary=dbs;
		this.managerBaseSalary=manbs;
		this.accountantBaseSalary=acbs;
		this.storesalesmanBaseSalary=ssbs;
		this.hubsalesmanBaseSalary=hbs;
		this.storekeeperBaseSalary=skbs;
		this.administerBaseSalary=adbs;
		this.mailerAllowance=mal;
		this.driverAllowance=dal;
	}
	public SalaryPO(SalaryVO vo) {
		super("Salary");
		this.mailerBaseSalary=vo.getMailerBS();
		this.driverBaseSalary=vo.getDriverBS();
		this.managerBaseSalary=vo.getManagerBS();
		this.accountantBaseSalary=vo.getAccountantBS();
		this.storesalesmanBaseSalary=vo.getStoresalesmanBS();
		this.hubsalesmanBaseSalary=vo.getHubsalesmanBS();
		this.storekeeperBaseSalary=vo.getStorekeeperBS();
		this.administerBaseSalary=vo.getAdministerBS();
		this.mailerAllowance=vo.getMailerAl();
		this.driverAllowance=vo.getDriverAl();
	}
	
	public void setMailerBS(int mbs){
		this.mailerBaseSalary=mbs;
	}
	public int getMailerBS(){
		return mailerBaseSalary;
	}
	public void setDriverBS(int dbs){
		this.driverBaseSalary=dbs;
	}
	public int getDriverBS(){
		return driverBaseSalary;
	}
	public void setManagerBS(int manbs){
		this.managerBaseSalary=manbs;
	}
	public int getManagerBS(){
		return managerBaseSalary;
	}
	public void setAccountantBS(int acbs){
		this.accountantBaseSalary=acbs;
	}
	public int getAccountantBS(){
		return accountantBaseSalary;
	}
	public void setStoresalesmanBS(int ssbs){
		this.storesalesmanBaseSalary=ssbs;
	}
	public int getStoresalesmanBS(){
		return storesalesmanBaseSalary;
	}
	public void setHubsalesmanBS(int hbs){
		this.hubsalesmanBaseSalary=hbs;
	}
	public int getHubsalesmanBS(){
		return hubsalesmanBaseSalary;
	}
	public void setStorekeeperBS(int skbs){
		this.storekeeperBaseSalary=skbs;
	}
	public int getStorekeeperBS(){
		return storekeeperBaseSalary;
	}
	public void setAdministerBS(int adbs){
		this.administerBaseSalary=adbs;
	}
	public int getAdministerBS(){
		return administerBaseSalary;
	}
	public void setMailerAl(int mal){
		this.mailerAllowance=mal;
	}
	public int getMailerAl(){
		return mailerAllowance;
	}
	public void setDriverAl(int dal){
		this.driverAllowance=dal;
	}
	public int getDriverAl(){
		return driverAllowance;
	}
}
