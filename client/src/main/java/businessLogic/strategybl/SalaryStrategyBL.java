package businessLogic.strategybl;

import data.StrategyDataImpl;
import po.strategypo.SalaryPO;
import typeDefinition.Job;
import vo.strategyvo.SalaryVO;

public class SalaryStrategyBL extends StrategyBL{
	StrategyDataImpl sd=new StrategyDataImpl();
	SalaryPO po=sd.getSalary();
	public double calSalary(Job job, int times) {
		// TODO Auto-generated method stub
		double salary=0;
		switch(job){
		case COURIER:salary=po.getMailerBS()+po.getMailerAl()*times;
		case DRIVER:salary=po.getDriverBS()+po.getDriverAl()*times;
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
}
