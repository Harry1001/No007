package businessLogic.strategybl;

import java.rmi.RemoteException;
import java.sql.SQLException;

import vo.strategyvo.CarriageFeeVO;
import vo.strategyvo.ExpressFeeVO;
import vo.strategyvo.SalaryVO;

public class StrategyController {
	StrategyBL strategybl;
	
	public void setExpressFee(ExpressFeeVO vo) throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		strategybl.setExpressFee(vo);
	}

	public void setCarriage(CarriageFeeVO vo) throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		strategybl.setCarriage(vo);
	}

	public void setSalary(SalaryVO vo) throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		strategybl.setSalary(vo);
	}

	public ExpressFeeVO getExpressFee() throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		ExpressFeeVO vo=strategybl.getExpressFee();
		return vo;
	}

	public CarriageFeeVO getCarriage() throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		CarriageFeeVO vo=strategybl.getCarriageFee();
		return vo;
	}

	public SalaryVO getSalary() throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		SalaryVO vo=strategybl.getSalary();
		return vo;
	}
}
