package stub;

import businessLogicService.strategyblservice.StrategyBLService;
import typeDefinition.Job;
import vo.receiptvo.ReceiptVO;
import vo.receiptvo.SendReceiptVO;
import vo.strategyvo.CarriageFeeVO;
import vo.strategyvo.ExpressFeeVO;
import vo.strategyvo.SalaryVO;

public class StrategyBL_Stub implements StrategyBLService{

	public double calExpressFee(SendReceiptVO vo) {
		// TODO Auto-generated method stub
		return 5;
	}

	public double calCarriage(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return 50;
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
