package businessLogicService.receiptblservice;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import vo.receiptvo.HubArrivalReceiptVO;

public interface HubArrivalReceiptBLService {

	/**
	 * 返回对应时间段内对应type的单据列表
	 * @throws SQLException 
	 */
	public ArrayList<HubArrivalReceiptVO> getListByTime(Date fromTime, Date toTime) throws RemoteException, SQLException;

	/**
	 * 给其他业务逻辑对象提供创建单据的接口，比如FinanceBL创建付款单，SendBL创建寄件单
	 * @param item
	 * @throws SQLException 
	 */
	public void createReceipt(HubArrivalReceiptVO item) throws RemoteException, SQLException;

	public ArrayList<String> getOrderID(String transportID) throws SQLException, RemoteException;
	
}
