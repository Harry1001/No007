package businessLogic.strategybl;

import data.StrategyDataImpl;
import dataService.StrategyDataService;
import po.strategypo.SalaryPO;
import typeDefinition.Job;
import vo.strategyvo.SalaryVO;

import java.rmi.RemoteException;

public class SalaryStrategyBL extends StrategyBL{
	StrategyDataService sd;
	SalaryPO po;

	public SalaryStrategyBL(){
		this(new StrategyDataImpl());
	}

	public SalaryStrategyBL(StrategyDataService dataService){
		this.sd=dataService;
		initPO();
	}

	public double calSalary(Job job, int times) {
		// TODO Auto-generated method stub
		double salary=0;
		switch(job){
		case COURIER:salary=po.getMailerBS()+po.getMailerAl()*times;break;
		case DRIVER:salary=po.getDriverBS()+po.getDriverAl()*times;break;
		case MANAGER:salary=po.getManagerBS();break;
		case ACCOUNTANT:salary=po.getAccountantBS();break;
		case STORESALESMAN:salary=po.getStoresalesmanBS();break;
		case HUBSALESMAN:salary=po.getHubsalesmanBS();break;
		case STOREKEEPER:salary=po.getStorekeeperBS();break;
		case ADMINISTRATOR:salary=po.getAdministerBS();break;
		}
		
		
		return salary;
		
	}
	public void setSalary(SalaryVO vo) {
		// TODO Auto-generated method stub
		//SalaryPO po=new SalaryPO(vo);
	}
	public SalaryVO getSalary() {
		// TODO Auto-generated method stub
		
		return null;
	}

	private void initPO(){
		try{
			po=sd.getSalary();
		}catch (RemoteException e){
			System.out.println("load salarypo from data layer fail");
		}
	}
}
