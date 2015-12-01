package businessLogic.strategybl;

import java.rmi.RemoteException;
import java.sql.SQLException;

import businessLogicService.strategyblservice.CalCarriageService;
import data.StrategyDataImpl;
import po.strategypo.CarriageFeePO;
import typeDefinition.ReceiptType;
import typeDefinition.Vehicle;
import vo.receiptvo.ReceiptVO;
import vo.receiptvo.TransferReceiptVO;

public class CalCarriageFeeBL extends StrategyBL implements CalCarriageService{
	
	StrategyDataImpl sd=new StrategyDataImpl();
	DistanceStrategyBL distance=new DistanceStrategyBL();
	
	public double calCarriage(ReceiptVO vo) throws RemoteException, SQLException {
		
		CarriageFeePO po=sd.getCarriageFee();
		double totalPrice=0;
		ReceiptType rt=vo.getType();
		if(rt.equals(ReceiptType.ENTRUCK)){
			totalPrice=30*10*2;
		}else if(rt.equals(ReceiptType.TRANSFER)){
			TransferReceiptVO trvo=(TransferReceiptVO) vo;
			Vehicle s1=trvo.getTransferType();
			
			String s2=trvo.getDepartLoc();
			String s3=trvo.getArriveLoc();
			double dis=distance.getDistance(s2, s3);
			
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