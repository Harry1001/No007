package businessLogic.transportbl;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import blfactory.BLFactory;
import businessLogic.strategybl.StrategyBL;
import businessLogicService.logisticblservice.LogisticBLService;
import businessLogicService.receiptblservice.ReceiptBLService;
import myexceptions.TransportBLException;
import vo.receiptvo.EntruckReceiptVO;

public class EntruckBL{

	public boolean verify(EntruckReceiptVO vo) throws TransportBLException{
		String s1=vo.getTransportID();
		String s3=vo.getTruckID();
		double fee=vo.getTransportFee();
		if(s1.length()!=19)
			throw new TransportBLException("汽运编号应该为19位！");
		if(s3.length()!=9)
			throw new TransportBLException("车辆代号应该为9位！");
		if(fee==-1.0)
			throw new TransportBLException("请计算运费！");
		ArrayList<String> as2=vo.getOrderNum();
		for(String temp:as2){
			if(temp.length()!=10){
				throw new TransportBLException("托运订单条形码号应该为10位！");
			}
		}
		Date date=null;
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		String temp=null;
		if(s1.substring(4, 5)=="2"){
			temp=s1.substring(4,12);
			try {
				date=format.parse(temp);
			} catch (ParseException e) {
				// TODO
				e.printStackTrace();
			}
			if(date==null){
				throw new TransportBLException("汽运编号应该为4位中转中心编号＋8位日期＋7位数字！");
			}
		}
		else{
			temp=s1.substring(6,14);
			try {
				date=format.parse(temp);
			} catch (ParseException e) {
				// TODO
				e.printStackTrace();
			}
			if(date==null){
				throw new TransportBLException("汽运编号应该为6位营业厅编号＋8位日期＋5位数字！");
			}		
		}
		return true;	
	}

	public void submit(EntruckReceiptVO vo) throws RemoteException {
		ReceiptBLService receiptblservice=BLFactory.getReceiptBLService();
		receiptblservice.createReceipt(vo);
		LogisticBLService logisticblservice=BLFactory.getLogisticBLService();
		logisticblservice.update(vo);
	}
	
	public double calFee(EntruckReceiptVO vo) {
		//TODO
		StrategyBL strategybl=new StrategyBL();
		return strategybl.calCarriage(vo);
	}
	
}
