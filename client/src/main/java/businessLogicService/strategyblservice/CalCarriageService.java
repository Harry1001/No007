package businessLogicService.strategyblservice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import vo.receiptvo.ReceiptVO;

public interface CalCarriageService {
	/*
	 * 计算运费
	 */
	public double calCarriage(ReceiptVO vo) throws RemoteException, SQLException, FileNotFoundException, ClassNotFoundException, IOException;
}
