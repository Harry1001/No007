package businessLogicService.transportblservice;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import myexceptions.TransportBLException;
import vo.receiptvo.TransferReceiptVO;

public interface TransferBLService {
	/**
	 * 检查中转单输入内容的正确性以决定页面是否跳转
	 * @throws TransportBLException 
	 */
	public boolean verify(TransferReceiptVO vo) throws TransportBLException;
	/**
	 * 中转单提交等待审批
	 * @throws RemoteException 
	 * @throws SQLException 
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public void submit(TransferReceiptVO vo) throws RemoteException, SQLException, MalformedURLException, NotBoundException;
	/**
	 * 输入参数获得运费
	 * @throws SQLException 
	 */
	public double calFee(TransferReceiptVO vo) throws RemoteException, SQLException;
}
