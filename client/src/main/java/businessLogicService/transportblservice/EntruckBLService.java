package businessLogicService.transportblservice;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
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
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public void submit(EntruckReceiptVO vo) throws RemoteException, SQLException, MalformedURLException, NotBoundException;
	
}
