package businessLogicService.transportblservice;

import java.rmi.RemoteException;
import java.sql.SQLException;

import myexceptions.TransportBLException;
import vo.receiptvo.EntruckReceiptVO;

public interface EntruckBLService {
	/**
	 * 检查装车单输入内容的正确性以决定页面是否跳转
	 * @throws TransportBLException 
	 */
	public boolean verify(EntruckReceiptVO vo) throws TransportBLException ;
	/**
	 * 装车单提交等待审批
	 * @throws RemoteException 
	 * @throws SQLException 
	 */
	public void submit(EntruckReceiptVO vo) throws RemoteException, SQLException;
	/**
	 * 输入参数获得运费
	 * @throws SQLException 
	 */
	public double calFee(EntruckReceiptVO vo) throws RemoteException, SQLException;
}
