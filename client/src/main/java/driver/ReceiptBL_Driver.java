package driver;

import java.util.ArrayList;

import businessLogicService.ReceiptBLService;
import typeDefinition.ReceiptState;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;
import vo.ReceiptVO;
import vo.SendReceiptVO;

public class ReceiptBL_Driver {

	public void change(ReceiptBLService receiptBLService){
		
		ArrayList<ReceiptVO> receiptList1=receiptBLService.getListByState(ReceiptState.SUBMITTED);
		
		ArrayList<ReceiptVO> receiptList2=receiptBLService.getListByTime(new myTime(2015,10,24), 
				new myTime(2015,10,26), ReceiptType.SEND);
		
		receiptBLService.approve(receiptList1);
		
		receiptBLService.disapprove(receiptList2);
		
		ReceiptVO receiptList3=new SendReceiptVO("张三", "中山路250号", "水利局", "13026547895","李四", "南京路50号", "物价局", "15854679204", 1, 0.5, 0.2, "书", 
				"经济快递", "快递袋", "0000000001", 5,new myTime(2015,10,25,0,0,0));
		receiptBLService.createReceipt(receiptList3);

	}
}
