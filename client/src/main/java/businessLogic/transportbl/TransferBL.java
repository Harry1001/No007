package businessLogic.transportbl;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import blfactory.BLFactory;
import businessLogicService.receiptblservice.ReceiptBLService;
import businessLogicService.strategyblservice.CalCarriageService;
import myexceptions.TransportBLException;
import vo.receiptvo.TransferReceiptVO;

public class TransferBL{

	ReceiptBLService receiptblservice;
	CalCarriageService strategybl;
	
	public boolean verify(TransferReceiptVO vo) throws TransportBLException {
		String s1=vo.getTransferID();
		double fee=vo.getTransferFee();
		if(s1.length()!=19)
			throw new TransportBLException("中转单编号应该为19位！");
		if(fee==-1.0)
			throw new TransportBLException("请计算运费！");
		ArrayList<String> as2=vo.getOrderID();
		for(String temp:as2){
			if(temp.length()!=10){
				throw new TransportBLException("托运单号应该为10位！");
			}
		}
		Date date=null;
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		String temp=s1.substring(4,12);
		try {
			date=format.parse(temp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(date==null){
			throw new TransportBLException("中转单编号应该为4位中转中心编号＋8位日期＋7位数字！");
		}
		return true;	
	}
	
	public void submit(TransferReceiptVO vo) throws RemoteException{
		receiptblservice=BLFactory.getReceiptBLService();
		receiptblservice.createReceipt(vo);
	}
	
	public double calFee(TransferReceiptVO vo) throws RemoteException, SQLException{
		strategybl=BLFactory.getCalCarriageService();
		return strategybl.calCarriage(vo);
	}
	
}
