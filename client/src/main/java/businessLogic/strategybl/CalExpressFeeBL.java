package businessLogic.strategybl;

import java.rmi.RemoteException;
import java.sql.SQLException;

import businessLogicService.strategyblservice.CalExpressfeeService;
import data.StrategyDataImpl;
import po.strategypo.ExpressFeePO;
import vo.receiptvo.SendReceiptVO;

public class CalExpressFeeBL extends StrategyBL implements CalExpressfeeService{
	
	StrategyDataImpl sd=new StrategyDataImpl();
	DistanceStrategyBL distance=new DistanceStrategyBL();
	
	public double calExpressFee(SendReceiptVO vo) throws RemoteException, SQLException {
		ExpressFeePO po=sd.getExpressFee();
		double totalPrice=0;
		double w=vo.getWeight();
		double v=vo.getVolume();
		double trueWeight=0;
		trueWeight=w > v/5000 ? w:v;     //判断体积与实际重量
		
		String s1=vo.getSenderLoc();     //计算距离
		String s11=s1.substring(0, 2);
		String s2=vo.getReceiverLoc();
		String s22=s2.substring(0, 2);
		double dis=distance.getDistance(s11, s22);
		
		if(vo.getExpressType().equals("EconomicExpress")){
			
			double p=po.getEcoPrice();
			totalPrice+=trueWeight*p*dis;
		}
		else if(vo.getExpressType().equals("StandardExpress")){
			
			double p=po.getStdPrice();
			totalPrice+=trueWeight*p*dis;
		}
		else if(vo.getExpressType().equals("SpecialExpress")){
			
			double p=po.getSpePrice();
			totalPrice+=trueWeight*p*dis;
		}
		
		if(vo.getPack().equals("bags")){
			totalPrice+=1;
		}else if(vo.getPack().equals("paperBox")){
			totalPrice+=5;
		}else if(vo.getPack().equals("woodenBox")){
			totalPrice+=10;
		}
		return totalPrice;
	}

	
	
	
}