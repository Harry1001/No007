package businessLogic.strategybl;

import java.rmi.RemoteException;
import java.util.Date;

import businessLogic.recordbl.RecordBL;
import businessLogicService.strategyblservice.DistanceService;
import data.StrategyDataImpl;
import dataService.StrategyDataService;
import po.strategypo.DistancePO;
import vo.recordvo.RecordVO;
import vo.strategyvo.DistanceVO;

public class DistanceStrategyBL implements DistanceService{

	StrategyDataService sd=new StrategyDataImpl();
	RecordBL rb=new RecordBL();
	public void setDistance(DistanceVO vo) throws RemoteException{
		DistancePO po=new DistancePO(vo);
		sd.updataDistanceStrategy(po);
		RecordVO rvo=new RecordVO(new Date(),"总经理","制定/修改城市间距离");
		rb.add(rvo);
	}

	public double getDistance(String city1, String city2) throws RemoteException {
		// TODO Auto-generated method stub
		double dis=sd.getDistance(city1, city2);
		return dis;
	}
	
	//界面层显示距离策略
	public DistanceVO getAll() throws RemoteException{
		DistancePO po=sd.getDistanceStrategy();
		DistanceVO vo=new DistanceVO(po);
		return vo;
	}
}