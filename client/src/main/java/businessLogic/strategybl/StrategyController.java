package businessLogic.strategybl;

import java.rmi.RemoteException;

import vo.strategyvo.CarriageFeeVO;
import vo.strategyvo.ExpressFeeVO;
import vo.strategyvo.SalaryVO;

public class StrategyController {
	StrategyBL strategybl=new StrategyBL();
	
	public void setExpressFee(ExpressFeeVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		strategybl.setExpressFee(vo);
	}

	public void setCarriage(CarriageFeeVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		strategybl.setCarriage(vo);
	}

	public void setSalary(SalaryVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		strategybl.setSalary(vo);
	}

	public ExpressFeeVO getExpressFee() throws RemoteException {
		// TODO Auto-generated method stub
		strategybl.getExpressFee();
		return null;
	}

	public CarriageFeeVO getCarriage() throws RemoteException {
		// TODO Auto-generated method stub
		strategybl.getCarriageFee();
		return null;
	}

	public SalaryVO getSalary() throws RemoteException {
		// TODO Auto-generated method stub
		strategybl.getSalary();
		return null;
	}
}
