package businessLogicService.receiptblservice;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import typeDefinition.ReceiptState;
import vo.receiptvo.PayReceiptVO;

public interface PayReceiptBLService {

	/**
	 * 返回对应时间段内对应type的单据列表
	 * @throws SQLException 
	 */
	public ArrayList<PayReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException;

	/**
	 * 给其他业务逻辑对象提供创建单据的接口，比如FinanceBL创建付款单，SendBL创建寄件单
	 * @param item
	 * @throws SQLException 
	 */
	public void createReceipt(PayReceiptVO item) throws RemoteException, SQLException;
	
	/**
	 * 按对应state返回相应PayReceiptPO列表用于审批单据
	 * @param state
	 * @return
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public ArrayList<PayReceiptVO> getListByState(ReceiptState state) throws RemoteException, SQLException;
	
	/**
	 * 审批后更新订单号对应单据的状态
	 * @param orderID
	 * @param state
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public void updateState(String orderID,ReceiptState state) throws RemoteException, SQLException;
	
}
