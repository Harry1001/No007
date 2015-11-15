package businessLogicService.receiptblservice;

import java.util.ArrayList;

import typeDefinition.ReceiptState;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;
import vo.receiptvo.ReceiptVO;

public interface ReceiptBLService {

	/*
	 * 返回对应时间段内对应type的单据列表
	 */
	public ArrayList<ReceiptVO> getListByTime(myTime fromTime, myTime toTime, 
			ReceiptType type);
	
}
