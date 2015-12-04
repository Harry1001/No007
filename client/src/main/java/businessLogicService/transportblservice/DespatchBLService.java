package businessLogicService.transportblservice;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import myexceptions.TransportBLException;
import vo.receiptvo.DespatchReceiptVO;

public interface DespatchBLService {
	/**
	 * 检查派件单输入内容的正确性以决定页面是否跳转
	 * @throws TransportBLException 
	 */
	public boolean verify(DespatchReceiptVO vo) throws TransportBLException ;
	/**
	 * 派件单提交等待审批
	 * @throws RemoteException 
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 * @throws SQLException 
	 */
	public void submit(DespatchReceiptVO vo) throws RemoteException, MalformedURLException, NotBoundException, SQLException;

}
