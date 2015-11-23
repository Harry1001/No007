package businessLogicService.transportblservice;

import java.rmi.RemoteException;

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
	 */
	public void submit(SendReceiptVO vo) throws RemoteException;
	/**
	 * 输入参数获得寄件费
	 */
	public double calFee(SendReceiptVO vo);
}
