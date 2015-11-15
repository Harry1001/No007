package businessLogic.strategybl;

import java.util.Observable;

import businessLogicService.strategyblservice.StrategyBLService;
import typeDefinition.Job;
import vo.receiptvo.ReceiptVO;
import vo.strategyvo.CarriageFeeVO;
import vo.strategyvo.ExpressFeeVO;
import vo.strategyvo.SalaryVO;

public class StrategyBL implements StrategyBLService{
	StrategyBL strategybl=new StrategyBL();
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
	}

	public double calExpressFee(ReceiptVO vo) {
		// TODO Auto-generated method stub
		double totalPrice=0;
		
		return totalPrice;
	}

	public double calCarriage(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public double calSalary(Job job, int times) {
		// TODO Auto-generated method stub
		double salary=0;
		
		if(job.equals(Job.SENDER)){
			SalaryVO svo=strategybl.getSalary();
			int wf=svo.getWorkFrequency();
			int baseSalary=svo.getBaseSalary();
			salary=baseSalary+wf*times;
			
		}else if(job.equals(Job.DRIVER)){
			SalaryVO svo=strategybl.getSalary();
			int wf1=svo.getWorkFrequency();
			int baseSalary=svo.getBaseSalary();
			salary=baseSalary+wf1*times;
			
		}else if(job.equals(Job.MANAGER)){
			SalaryVO svo=strategybl.getSalary();
			salary=svo.getBaseSalary();
		}else if(job.equals(Job.ACCOUNTANT)){
			SalaryVO svo=strategybl.getSalary();
			salary=svo.getBaseSalary();
		}else{
			SalaryVO svo=strategybl.getSalary();
			salary=svo.getBaseSalary();
		}
		
		return salary;
		
	}

	public void setExpressFee(ExpressFeeVO vo) {
		// TODO Auto-generated method stub
		double dis=vo.getDistance();
		
		if(vo.getExpressType().equals("EcnomicExpress")){
			vo.setPrice(dis/1000*18);
		}else if(vo.getExpressType().equals("StandardExpress")){
			vo.setPrice(dis/1000*23);
		}else if(vo.getExpressType().equals("SpecialExpress")){
			vo.setPrice(dis/1000*25);
		}
	}

	public void setCarriage(CarriageFeeVO vo) {
		// TODO Auto-generated method stub
		
	}

	public void setSalary(SalaryVO vo) {
		// TODO Auto-generated method stub
		Job job=vo.getJob();
		if(job.equals(Job.SENDER)){
			vo.setBaseSalary(3000);
			vo.setAllowance(10);
		}else if(job.equals(Job.DRIVER)){
			vo.setBaseSalary(3500);
			vo.setAllowance(20);
		}else if(job.equals(Job.MANAGER)){
			vo.setBaseSalary(8000);
		}else if(job.equals(Job.ACCOUNTANT)){
			vo.setBaseSalary(5000);
		}else{
			vo.setBaseSalary(3000);
		}
	}

	public ExpressFeeVO getExpressFee() {
		// TODO Auto-generated method stub
		return null;
	}

	public CarriageFeeVO getCarriage() {
		// TODO Auto-generated method stub
		return null;
	}

	public SalaryVO getSalary() {
		// TODO Auto-generated method stub
		int bs=0;
		int wf=0;
		int al=0;
		Job job=null;
		SalaryVO svo=new SalaryVO(job,bs,al,wf);
		bs=svo.getBaseSalary();
		return svo;
	}
	
}
