package businessLogicService.transportblservice;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import myexceptions.TransportBLException;
import vo.receiptvo.StoreArrivalReceiptVO;

public interface ArriveStoreBLService {
	/**
	 * 检查营业厅到达单输入内容的正确性以决定页面是否跳转
	 * @throws TransportBLException 
	 */
	public boolean verify(StoreArrivalReceiptVO vo) throws TransportBLException;
	/**
	 * 营业厅到达单提交等待审批
	 * @throws RemoteException 
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 * @throws SQLException 
	 * @throws TransportBLException 
	 */
	public void submit(StoreArrivalReceiptVO vo) throws RemoteException, MalformedURLException, NotBoundException, SQLException, TransportBLException;
}
