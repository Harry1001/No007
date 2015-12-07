package businessLogic.strategybl;

import dataService.StrategyDataService;
import dataService._RMI;
import po.strategypo.SalaryPO;
import typeDefinition.Job;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import businessLogicService.strategyblservice.CalSalaryService;

public class CalSalaryBL extends StrategyBL implements CalSalaryService{
	StrategyDataService sd;
	SalaryPO po;
	
	public CalSalaryBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url="rmi://"+_RMI.getIP()+"/central_strategy";
		sd=(StrategyDataService)Naming.lookup(url);
	}
	

	public CalSalaryBL(StrategyDataService dataService)throws MalformedURLException, RemoteException, NotBoundException{
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
