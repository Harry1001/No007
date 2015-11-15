package businessLogic.strategybl;

import java.util.Observable;

import businessLogicService.strategyblservice.StrategyBLService;
import vo.ReceiptVO;
import vo.SalaryVO;

public class StrategyBL implements StrategyBLService{

	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	public double calExpressFee(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public double calCarriage(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public SalaryVO setSalary() {
		// TODO Auto-generated method stub
		return null;
	}

}
