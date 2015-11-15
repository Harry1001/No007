package businessLogic.strategybl;

import vo.strategyvo.CarriageFeeVO;
import vo.strategyvo.ExpressFeeVO;
import vo.strategyvo.SalaryVO;

public class StrategyController {
	StrategyBL strategybl=new StrategyBL();
	
	public void setExpressFee(ExpressFeeVO vo) {
		// TODO Auto-generated method stub
		strategybl.setExpressFee(vo);
	}

	public void setCarriage(CarriageFeeVO vo) {
		// TODO Auto-generated method stub
		strategybl.setCarriage(vo);
	}

	public void setSalary(SalaryVO vo) {
		// TODO Auto-generated method stub
		strategybl.setSalary(vo);
	}

	public ExpressFeeVO getExpressFee() {
		// TODO Auto-generated method stub
		strategybl.getExpressFee();
		return null;
	}

	public CarriageFeeVO getCarriage() {
		// TODO Auto-generated method stub
		strategybl.getCarriage();
		return null;
	}

	public SalaryVO getSalary() {
		// TODO Auto-generated method stub
		strategybl.getSalary();
		return null;
	}
}
