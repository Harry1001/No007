package businessLogic.receiptbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businessLogicService.receiptblservice.ReceiptBLService;
import data.ReceiptDataImpl;
import dataService.ReceiptDataService;
import po.receiptpo.ReceiptPO;
import po.receiptpo.SendReceiptPO;
import vo.receiptvo.ReceiptVO;
import vo.receiptvo.SendReceiptVO;
import typeDefinition.ReceiptState;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;

public class ReceiptBL{


	private ReceiptDataService receiptData;

	/*
	 * constructor
	 */
	public ReceiptBL(){
		this(new ReceiptDataImpl());
	}
	public ReceiptBL(ReceiptDataService receiptData){
		this.receiptData=receiptData;
	}
	

	public ArrayList<ReceiptVO> getListByTime(myTime fromTime, myTime toTime){
		return null;
	}

	public void createReceipt(ReceiptVO item) {

	}

}
