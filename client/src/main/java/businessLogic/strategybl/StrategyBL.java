package businessLogic.strategybl;


import businessLogicService.strategyblservice.StrategyBLService;
import typeDefinition.Job;

import vo.receiptvo.*;
import vo.strategyvo.CarriageFeeVO;
import vo.strategyvo.ExpressFeeVO;
import vo.strategyvo.SalaryVO;

public class StrategyBL implements StrategyBLService{
	
	public double calExpressFee(SendReceiptVO vo) {          
		// TODO Auto-generated method stub
		
		return 0;
	}

	public double calCarriage(ReceiptVO vo) {
		// TODO Auto-generated method stub
		
		return 0;
	}
	
	public double calSalary(Job job, int times) {
		// TODO Auto-generated method stub
		
		return 0;
		
	}

	public void setExpressFee(ExpressFeeVO vo) {
		// TODO Auto-generated method stub
		
	}

	public void setCarriage(CarriageFeeVO vo) {
		// TODO Auto-generated method stub
		
	}

	public void setSalary(SalaryVO vo) {
		// TODO Auto-generated method stub
		
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
		return null;
	}
	
}
