package businessLogicService.transportblservice;

import java.rmi.RemoteException;

import myexceptions.TransportBLException;
import vo.receiptvo.ReceiveReceiptVO;

public interface ReceiveBLService {
	/**
	 * 检查收件单输入内容的正确性以决定页面是否跳转
	 * @throws TransportBLException 
	 */
	public boolean verify(ReceiveReceiptVO vo) throws TransportBLException;
	/**
	 * 收件单提交等待审批
	 * @throws RemoteException 
	 */
	public void submit(ReceiveReceiptVO vo) throws RemoteException;

}