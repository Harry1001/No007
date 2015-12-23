package businessLogicService.transportblservice;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import myexceptions.TransportBLException;
import vo.receiptvo.SendReceiptVO;

public interface SendBLService {
	/**
	 * 检查寄件单输入内容的正确性以决定页面是否跳转
	 * @throws TransportBLException 
	 */
	public boolean verify(SendReceiptVO vo) throws TransportBLException;
	/**
	 * 寄件单提交等待审批
	 * @throws RemoteException 
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 * @throws SQLException 
	 */
	public void submit(SendReceiptVO vo) throws RemoteException, MalformedURLException, NotBoundException, SQLException;
	
	public SendReceiptVO getSendReceipt(String orderID) throws RemoteException, SQLException, MalformedURLException, NotBoundException;
}
