package businessLogic.strategybl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;

import businessLogic.recordbl.RecordBL;
import businessLogicService.strategyblservice.FeeStrategyBLService;
import businessLogicService.strategyblservice.SalaryStrategyBLService;
import dataService.StrategyDataService;
import dataService._RMI;
import po.strategypo.*;
import vo.recordvo.RecordVO;
import vo.strategyvo.CarriageFeeVO;
import vo.strategyvo.ExpressFeeVO;
import vo.strategyvo.SalaryVO;

public class StrategyBL implements FeeStrategyBLService,SalaryStrategyBLService{
	
	StrategyDataService sd;
	
	public StrategyBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url="rmi://"+_RMI.getIP()+"/central_strategy";
		sd=(StrategyDataService)Naming.lookup(url);
	}
	
	RecordBL rb=new RecordBL();
	
	public final void setExpressFee(ExpressFeeVO vo) throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		ExpressFeePO po=new ExpressFeePO(vo);
		sd.updateExpressFeeStrategy(po);
		RecordVO rvo=new RecordVO(new Date(),"总经理","修改快递费用策略");
		rb.add(rvo);
	}

	public final void setCarriage(CarriageFeeVO vo) throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		CarriageFeePO po=new CarriageFeePO(vo);
		sd.updateCarriageFeeStrategy(po);
		RecordVO rvo=new RecordVO(new Date(),"总经理","修改运费策略");
		rb.add(rvo);
	}

	public final void setSalary(SalaryVO vo) throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		SalaryPO po=new SalaryPO(vo);
		sd.updateSalaryStrategy(po);
		RecordVO rvo=new RecordVO(new Date(),"总经理","修改薪水策略");
		rb.add(rvo);
	}

	public final ExpressFeeVO getExpressFee() throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		ExpressFeePO po=sd.getExpressFee();
		ExpressFeeVO vo=new ExpressFeeVO(po);
		return vo;
	}

	public final CarriageFeeVO getCarriageFee() throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		CarriageFeePO po=sd.getCarriageFee();
		CarriageFeeVO vo=new CarriageFeeVO(po);
		return vo;
	}

	public final SalaryVO getSalary() throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		SalaryPO po=sd.getSalary();
		SalaryVO vo=new SalaryVO(po);
		return vo;
	}

}
