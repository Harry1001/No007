package businessLogicService.transportblservice;

import java.rmi.RemoteException;

import myexceptions.TransportBLException;
import vo.receiptvo.HubArrivalReceiptVO;

public interface ArriveHubBLService {
	/**
	 * 检查中转中心到达单输入内容的正确性以决定页面是否跳转
	 * @throws TransportBLException 
	 */
	public boolean verify(HubArrivalReceiptVO vo) throws TransportBLException ;
	/**
	 * 中转中心到达单提交等待审批
	 * @throws RemoteException 
	 */
	public void submit(HubArrivalReceiptVO vo) throws RemoteException;
}
