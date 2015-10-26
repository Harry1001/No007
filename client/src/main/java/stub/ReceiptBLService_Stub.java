package stub;

import java.util.ArrayList;

import businessLogic.BLImpl;
import businessLogicService.ReceiptBLService;
import typeDefinition.ReceiptState;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;
import vo.ReceiptVO;
import vo.SendReceiptVO;;

public class ReceiptBLService_Stub implements ReceiptBLService{
	
	ReceiptVO vo = new SendReceiptVO("张三", "中山路250号", "水利局", "13026547895","李四", "南京路50号", "物价局", "15854679204", 1, 0.5, 0.2, "书", 
			"经济快递", "快递袋", "0123456789", 5,new myTime(2015,10,25,0,0,0));;
	
	public ArrayList<ReceiptVO> getListByState(ReceiptState state) {
		// TODO Auto-generated method stub
		ArrayList<ReceiptVO>receiptList=new ArrayList<ReceiptVO>();
		//ReceiptType type=ReceiptType.SEND;
		
		state=ReceiptState.APPROVED;
		vo.setState(state);
		receiptList.add(vo);
		return receiptList;
	}

	public ArrayList<ReceiptVO> getListByTime(myTime fromTime, myTime toTime, ReceiptType type) {
		// TODO Auto-generated method stub
		ArrayList<ReceiptVO>receiptList=new ArrayList<ReceiptVO>();
		type=ReceiptType.SEND;
		if(vo.getType().equals(type)){
			receiptList.add(vo);
			
		}
		return receiptList;
	}

	public void approve(ArrayList<ReceiptVO> itemList) {
		// TODO Auto-generated method stub
		System.out.println("Approved!");
	}

	public void disapprove(ArrayList<ReceiptVO> itemList) {
		// TODO Auto-generated method stub
		System.out.println("Disapproved!");
	}

	public void createReceipt(ReceiptVO item) {
		// TODO Auto-generated method stub
		
	}

	public void register(BLImpl listener) {
		// TODO Auto-generated method stub
		
	}

}
