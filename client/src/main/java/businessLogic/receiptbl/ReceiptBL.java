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
	

	public ArrayList<? extends ReceiptVO> getListByTime(myTime fromTime, myTime toTime){
		return null;
	}

	public void createReceipt(ReceiptVO item) {
		ReceiptType type = item.getType();
		try{
			switch (type){
				//todo
				case SEND:receiptData.addItem(new SendReceiptPO((SendReceiptVO)item));break;
				case DESPATCH:break;
				case ENTRUCK:break;
				case TRANSFER:break;
				case STOREARRIVAL:break;
				case HUBARRIVAL:break;
				case DEPOTIN:break;
				case DEPOTOUT:break;
				case CHARGE:break;
				case PAY:break;
				case RECEIVE:break;
			}
		}catch (RemoteException e){
			System.out.println("create receipt int data layer fail");
		}

	}

}
