package businessLogicService.strategyblservice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import vo.receiptvo.SendReceiptVO;

public interface CalExpressfeeService {
	/*
	 * 计算快递费
	 */
	public double calExpressFee(SendReceiptVO vo) throws RemoteException, SQLException, FileNotFoundException, ClassNotFoundException, IOException, NotBoundException;
}
