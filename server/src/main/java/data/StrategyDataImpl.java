package data;

import java.rmi.RemoteException;

import dataService.StrategyDataService;
import po.strategypo.CarriageFeePO;
import po.strategypo.ExpressFeePO;
import po.strategypo.SalaryPO;

public class StrategyDataImpl implements StrategyDataService{

	public ExpressFeePO getExpressFee() /*throws RemoteException*/ {
		// TODO Auto-generated method stub
		return null;
	}

	public CarriageFeePO getCarriageFee() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateExpressFeeStrategy(ExpressFeePO efpo) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void updateCarriageFeeStrategy(CarriageFeePO cfpo) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public SalaryPO getSalary() /*throws RemoteException*/ {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateSalaryStrategy(SalaryPO po) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
