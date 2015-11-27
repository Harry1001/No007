package businessLogic.strategybl;

import java.rmi.RemoteException;
import java.sql.SQLException;

import businessLogicService.strategyblservice.CalCarriageService;
import data.StrategyDataImpl;
import po.strategypo.CarriageFeePO;
import typeDefinition.ReceiptType;
import vo.receiptvo.ReceiptVO;
import vo.receiptvo.TransferReceiptVO;

public class CalCarriageFeeBL extends StrategyBL implements CalCarriageService{
	StrategyDataImpl sd=new StrategyDataImpl();
	public double calCarriage(ReceiptVO vo) throws RemoteException, SQLException {
		
		CarriageFeePO po=sd.getCarriageFee();
		double totalPrice=0;
		ReceiptType rt=vo.getType();
		if(rt.equals(ReceiptType.ENTRUCK)){
			totalPrice=30*10*2;
		}else if(rt.equals(ReceiptType.TRANSFER)){
			TransferReceiptVO trvo=(TransferReceiptVO) vo;
			String s1=trvo.getTransferType();
			String s2=trvo.getDepartLoc();
			String s3=trvo.getArriveLoc();
			double dis=getDistance(s2, s3);
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