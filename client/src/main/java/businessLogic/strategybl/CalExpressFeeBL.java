package businessLogic.strategybl;

import java.rmi.RemoteException;
import java.sql.SQLException;

import businessLogicService.strategyblservice.CalExpressfeeService;
import data.StrategyDataImpl;
import po.strategypo.ExpressFeePO;
import vo.receiptvo.SendReceiptVO;

public class CalExpressFeeBL extends StrategyBL implements CalExpressfeeService{
	StrategyDataImpl sd=new StrategyDataImpl();
	
	public double calExpressFee(SendReceiptVO vo) throws RemoteException, SQLException {
		ExpressFeePO po=sd.getExpressFee();
		double totalPrice=0;
		double w=vo.getWeight();
		double v=vo.getVolume();
		double trueWeight=0;
		trueWeight=w > v/5000 ? w:v;     //判断体积与实际重量
		String s1=vo.getSenderLoc();
		String s2=vo.getReceiverLoc();
		double dis=getDistance(s1, s2);
		
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

	
	public double getDistance(String depa,String dest){
		double dis=0;
		if((depa.equals("北京")&&dest.equals("上海"))||(depa.equals("上海")&&dest.equals("北京"))){
			dis=1064.7;
		}else if((depa.equals("北京")&&dest.equals("南京"))||(depa.equals("南京")&&dest.equals("北京"))){
		dis=900;
		}else if((depa.equals("北京")&&dest.equals("广州"))||(depa.equals("广州")&&dest.equals("北京"))){
			dis=1888.8;
		}else if((depa.equals("上海")&&dest.equals("南京"))||(depa.equals("南京")&&dest.equals("上海"))){
			dis=266;
		}else if((depa.equals("上海")&&dest.equals("广州"))||(depa.equals("广州")&&dest.equals("上海"))){
			dis=1213;
		}else if((depa.equals("南京")&&dest.equals("广州"))||(depa.equals("广州")&&dest.equals("南京"))){
			dis=1132;
		}
		return dis;
	}
	
}