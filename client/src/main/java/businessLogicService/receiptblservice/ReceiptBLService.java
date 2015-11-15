package businessLogicService.receiptblservice;

import java.util.ArrayList;

import typeDefinition.ReceiptState;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;
import vo.ReceiptVO;

public interface ReceiptBLService {

	/*
	 * 根据传入的单据状态，返回对应state的单据列表
	 */
	public ArrayList<ReceiptVO> getListByState(ReceiptState state);	
	
	/*
	 * 返回对应时间段内对应type的单据列表
	 */
	public ArrayList<ReceiptVO> getListByTime(myTime fromTime, myTime toTime, 
			ReceiptType type);
	
	/*
	 * 待审批或未通过单据列表审批通过以更新对应单据持久化数据，并且通知监听者
	 */
	public void approve(ArrayList<ReceiptVO> itemList);
	
	/*
	 * 待审批或未通过单据列表审批不通过以更新对应单据持久化数据，并且通知监听者
	 */
	public void disapprove(ArrayList<ReceiptVO> itemList);
	
	/*
	 * 传入已计算费用的单据，新增单据以更新单据持久化数据
	 */
	public void createReceipt(ReceiptVO item);
	
}
