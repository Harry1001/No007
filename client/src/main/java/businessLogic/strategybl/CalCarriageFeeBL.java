package businessLogic.strategybl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import businessLogicService.strategyblservice.CalCarriageService;

import dataService.StrategyDataService;
import dataService._RMI;
import po.strategypo.CarriageFeePO;
import typeDefinition.ReceiptType;
import typeDefinition.Vehicle;
import vo.receiptvo.ReceiptVO;
import vo.receiptvo.TransferReceiptVO;

public class CalCarriageFeeBL extends StrategyBL implements CalCarriageService{
	
	StrategyDataService sd;
	public CalCarriageFeeBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url="rmi://"+_RMI.getIP()+"/central_strategy";
		sd=(StrategyDataService)Naming.lookup(url);
	}
	DistanceStrategyBL distance=new DistanceStrategyBL();
	
	public double calCarriage(ReceiptVO vo) throws SQLException, FileNotFoundException, ClassNotFoundException, IOException {
		
		CarriageFeePO po=sd.getCarriageFee();
		double totalPrice=0;
		ReceiptType rt=vo.getType();
		if(rt.equals(ReceiptType.ENTRUCK)){
			totalPrice=30*10*2;
		}else if(rt.equals(ReceiptType.TRANSFER)){
			TransferReceiptVO trvo=(TransferReceiptVO) vo;
			Vehicle s1=trvo.getTransferType();
			
			String s2=trvo.getDepartLoc();
			String s22=s2.substring(0, 2);
			String s3=trvo.getArriveLoc();
			String s33=s3.substring(0, 2);
			double dis=distance.getDistance(s22, s33);
			
			if(s1.equals("plane")){
				totalPrice+=50*dis*po.getPlanePrice();
			}else if(s1.equals("train")){
				totalPrice+=2000*dis*po.getTrainPrice();
			}else if(s1.equals("truck")){
				totalPrice+=10*dis*po.getBusPrice();
			}
		}
		return totalPrice;
	}
	
}