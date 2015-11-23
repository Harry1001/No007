package businessLogicService.receiptblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;
import vo.receiptvo.ReceiptVO;

public interface ReceiptBLService {

	/**
	 * 返回对应时间段内对应type的单据列表
	 */
	public ArrayList<? extends ReceiptVO> getListByTime(myTime fromTime, myTime toTime,
			ReceiptType type) throws RemoteException;

	/**
	 * 给其他业务逻辑对象提供创建单据的接口，比如FinanceBL创建付款单，SendBL创建寄件单
	 * @param item
	 */
	public void createReceipt(ReceiptVO item) throws RemoteException;
	
}
