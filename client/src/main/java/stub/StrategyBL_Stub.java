package stub;

import businessLogic.BLImpl;
import businessLogicService.StrategyBLService;
import typeDefinition.Job;
import vo.ReceiptVO;
import vo.SalaryVO;

public class StrategyBL_Stub implements StrategyBLService{

	public double calExpressFee(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return 5;
	}

	public double calCarriage(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return 50;
	}

	public SalaryVO setSalary() {
		// TODO Auto-generated method stub
		SalaryVO svo=new SalaryVO(Job.ADMINISTRATOR,3000,0);
		return svo;
	}

	public void register(BLImpl listener) {
		// TODO Auto-generated method stub
		
	}

}
