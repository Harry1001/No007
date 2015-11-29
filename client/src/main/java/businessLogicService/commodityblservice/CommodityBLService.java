package businessLogicService.commodityblservice;

import java.rmi.RemoteException;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import vo.commodityvo.CheckResultVO;
import vo.commodityvo.CommodityVO;
import vo.receiptvo.DepotInReceiptVO;
import vo.receiptvo.DepotOutReceiptVO;

public interface CommodityBLService {

	/**
	 * 输入入库单单据的时候创建单据
	 * @param vo
	 * @throws RemoteException
	 * @throws NamingException
	 * @throws SQLException 
	 */
	public void submitIn(DepotInReceiptVO vo) throws RemoteException, NamingException, SQLException;
	
	/**
	 * 输入出库单单据的时候创建单据
	 * @param vo
	 * @throws RemoteException
	 * @throws NamingException
	 * @throws SQLException 
	 */
	public void submitOut(DepotOutReceiptVO vo) throws RemoteException, NamingException, SQLException;
	
	/**
	 * 获取指定中转中心的所有现存的数据
	 * @param transferNum
	 * @return
	 * @throws RemoteException
	 * @throws NamingException
	 * @throws SQLException 
	 */
	public ArrayList<CommodityVO> getList(String transferNum) throws RemoteException, NamingException, SQLException;
	
	/**
	 * 获取指定中转中心在指定时间内的所有出入库单据
	 * @param transferNum
	 * @param fromTime
	 * @param toTime
	 * @return
	 * @throws RemoteException
	 * @throws NamingException
	 */
	public CheckResultVO getList(String transferNum,Date fromTime,Date toTime)throws RemoteException, NamingException;
	
	/**
	 * 获取所有中转中心当前的库存数据
	 * @return
	 * @throws RemoteException
	 * @throws NamingException
	 * @throws SQLException 
	 */
	public ArrayList<CommodityVO> getTotal()throws RemoteException, NamingException, SQLException;
	
	/**
	 * 清除指定中转中心当前的库存数据
	 * @throws RemoteException
	 * @throws NamingException
	 * @throws SQLException 
	 */
	public void renew(String transferNum)throws RemoteException, NamingException, SQLException;
	
}
