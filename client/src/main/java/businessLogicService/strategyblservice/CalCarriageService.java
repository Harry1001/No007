package businessLogicService.strategyblservice;

import java.rmi.RemoteException;

import vo.receiptvo.ReceiptVO;

public interface CalCarriageService {
	/*
	 * 计算运费
	 */
	public double calCarriage(ReceiptVO vo) throws RemoteException;
}
