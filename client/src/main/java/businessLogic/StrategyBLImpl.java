package businessLogic;

import java.util.Observable;

import businessLogicService.StrategyBLService;
import vo.ReceiptVO;
import vo.SalaryVO;

public class StrategyBLImpl extends BLImpl implements StrategyBLService{

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
