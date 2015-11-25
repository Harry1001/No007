package businessLogic.strategybl;

import java.rmi.RemoteException;

import businessLogicService.strategyblservice.FeeStrategyBLService;
import businessLogicService.strategyblservice.SalaryStrategyBLService;
import data.StrategyDataImpl;
import dataService.StrategyDataService;
import po.strategypo.*;
import vo.strategyvo.CarriageFeeVO;
import vo.strategyvo.ExpressFeeVO;
import vo.strategyvo.SalaryVO;

public class StrategyBL implements FeeStrategyBLService,SalaryStrategyBLService{
	
	StrategyDataService sd=new StrategyDataImpl();
	public final void setExpressFee(ExpressFeeVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		ExpressFeePO po=new ExpressFeePO(vo);
		sd.updateExpressFeeStrategy(po);
	}

	public final void setCarriage(CarriageFeeVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		CarriageFeePO po=new CarriageFeePO(vo);
		sd.updateCarriageFeeStrategy(po);
	}

	public final void setSalary(SalaryVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		SalaryPO po=new SalaryPO(vo);
		sd.updateSalaryStrategy(po);
	}

	public final ExpressFeeVO getExpressFee() throws RemoteException {
		// TODO Auto-generated method stub
		ExpressFeePO po=sd.getExpressFee();
		ExpressFeeVO vo=new ExpressFeeVO(po);
		return vo;
	}

	public final CarriageFeeVO getCarriageFee() throws RemoteException {
		// TODO Auto-generated method stub
		CarriageFeePO po=sd.getCarriageFee();
		CarriageFeeVO vo=new CarriageFeeVO(po);
		return vo;
	}

	public final SalaryVO getSalary() throws RemoteException {
		// TODO Auto-generated method stub
		SalaryPO po=sd.getSalary();
		SalaryVO vo=new SalaryVO(po);
		return vo;
	}

	
	
}
