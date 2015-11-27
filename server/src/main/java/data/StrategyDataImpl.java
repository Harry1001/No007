package data;

import java.rmi.RemoteException;
import java.sql.SQLException;

import dataService.StrategyDataService;
import database.CarriageStrategyDBManager;
import database.ExpressFeeStrategyDBManager;
import database.SalaryStrategyDBManager;
import po.strategypo.CarriageFeePO;
import po.strategypo.ExpressFeePO;
import po.strategypo.SalaryPO;

public class StrategyDataImpl implements StrategyDataService{

	
	public ExpressFeePO getExpressFee() throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		ExpressFeeStrategyDBManager efs=new ExpressFeeStrategyDBManager();
		ExpressFeePO po=efs.getAll();
		return po;
	}

	public CarriageFeePO getCarriageFee() throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		CarriageStrategyDBManager cfs=new CarriageStrategyDBManager();
		CarriageFeePO po=cfs.get();
		return po;
	}

	public void updateExpressFeeStrategy(ExpressFeePO efpo) throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		ExpressFeeStrategyDBManager efs=new ExpressFeeStrategyDBManager();
		efs.addExpressFeeStrategy(efpo);
	}

	public void updateCarriageFeeStrategy(CarriageFeePO cfpo) throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		CarriageStrategyDBManager cfs=new CarriageStrategyDBManager();
		cfs.addCarriageStrategy(cfpo);
	}

	public SalaryPO getSalary() throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		SalaryStrategyDBManager s=new SalaryStrategyDBManager();
		SalaryPO po=s.getAll();
		return po;
	}

	public void updateSalaryStrategy(SalaryPO po) throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		SalaryStrategyDBManager s=new SalaryStrategyDBManager();
		s.addSalaryStrategy(po);
	}

}
