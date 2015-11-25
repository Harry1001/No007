package businessLogic.strategybl;

import data.StrategyDataImpl;
import dataService.StrategyDataService;
import po.strategypo.SalaryPO;
import typeDefinition.Job;

import java.rmi.RemoteException;

import businessLogicService.strategyblservice.CalSalaryService;

public class CalSalaryBL extends StrategyBL implements CalSalaryService{
	StrategyDataService sd;
	SalaryPO po;

	public CalSalaryBL(){
		this(new StrategyDataImpl());
	}

	public CalSalaryBL(StrategyDataService dataService){
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
	

	private void initPO(){
		try{
			po=sd.getSalary();
		}catch (RemoteException e){
			System.out.println("load salarypo from data layer fail");
		}
	}
}
