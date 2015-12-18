package businessLogic.strategybl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import businessLogicService.strategyblservice.CalExpressfeeService;
import dataService.StrategyDataService;
import dataService._RMI;
import po.strategypo.ExpressFeePO;
import vo.receiptvo.SendReceiptVO;

public class CalExpressFeeBL extends StrategyBL implements CalExpressfeeService{
	
	StrategyDataService sd;
	public CalExpressFeeBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url="rmi://"+_RMI.getIP()+"/central_strategy";
		sd=(StrategyDataService)Naming.lookup(url);
	}
	DistanceStrategyBL distance=new DistanceStrategyBL();
	
	public double calExpressFee(SendReceiptVO vo) throws SQLException, FileNotFoundException, ClassNotFoundException, IOException {
		ExpressFeePO po=sd.getExpressFee();
		double totalPrice = 0;
		double w = vo.getWeight();
		double v = vo.getVolume();
		double trueWeight = 0;
		trueWeight = w > v/5000 ? w:v;     //判断体积与实际重量
		
		String s1=vo.getSenderLoc();     //计算距离
		String s11=s1.substring(0, 2);
		String s2=vo.getReceiverLoc();
		String s22=s2.substring(0, 2);
		double dis=distance.getDistance(s11, s22);
		
		if(vo.getExpressType().equals("经济快递")){
			
			double p=po.getEcoPrice();
			totalPrice+=trueWeight*p*dis;
		}
		else if(vo.getExpressType().equals("标准快递")){
			
			double p=po.getStdPrice();
			totalPrice+=trueWeight*p*dis;
		}
		else if(vo.getExpressType().equals("特快快递")){
			
			double p=po.getSpePrice();
			totalPrice+=trueWeight*p*dis;
		}
		
		if(vo.getPack().equals("快递袋")){
			totalPrice+=1;
		}else if(vo.getPack().equals("纸箱")){
			totalPrice+=5;
		}else if(vo.getPack().equals("木箱")){
			totalPrice+=10;
		}
		return totalPrice;
	}

	
	
	
}