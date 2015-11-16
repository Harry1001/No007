package businessLogic.strategybl;

import data.StrategyDataImpl;
import po.strategypo.ExpressFeePO;
import typeDefinition.ReceiptType;
import vo.receiptvo.ReceiptVO;
import vo.receiptvo.SendReceiptVO;
import vo.receiptvo.TransferReceiptVO;
import vo.strategyvo.ExpressFeeVO;

public class FeeStrategyBL extends StrategyBL{
	StrategyDataImpl sd=new StrategyDataImpl();
	ExpressFeePO po=sd.getExpressFee();
	public double calExpressFee(SendReceiptVO vo) {          //修改
		// TODO Auto-generated method stub
		double totalPrice=0;
		double w=vo.getWeight();
		double v=vo.getVolume();
		double trueWeight=0;
		trueWeight=w>v/5000?w:v;     //判断体积与实际重量
		String s1=vo.getSenderLoc();
		String s2=vo.getReceiverLoc();
		double dis=getDistance(s1, s2);
		
		/*if(vo.getExpressType().equals("EconomicExpress")){
			
			double p=efvo.getPrice();
			totalPrice+=trueWeight*p*dis;
		}
		else if(vo.getExpressType().equals("StandardExpress")){
			
			double p=efvo.getPrice();
			totalPrice+=trueWeight*p*dis;
		}
		else if(vo.getExpressType().equals("SpecialExpress")){
			
			double p=efvo.getPrice();
			totalPrice+=trueWeight*p*dis;
		}*/
		
		if(vo.getPack().equals("bags")){
			totalPrice+=1;
		}else if(vo.getPack().equals("paperBox")){
			totalPrice+=5;
		}else if(vo.getPack().equals("woodenBox")){
			totalPrice+=10;
		}
		return totalPrice;
	}

	public double calCarriage(ReceiptVO vo) {
		// TODO Auto-generated method stub
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
				totalPrice+=50*20*dis;
			}else if(s1.equals("train")){
				totalPrice+=2000*0.2*dis;
			}else if(s1.equals("truck")){
				totalPrice+=10*2*dis;
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
