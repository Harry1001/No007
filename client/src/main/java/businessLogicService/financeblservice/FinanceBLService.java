package businessLogicService.financeblservice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.NamingException;

import myexceptions.InfoBLException;
import vo.salaryfeevo.SalaryFeeVO;
import vo.financevo.AddUpResultVO;
import vo.financevo.FinanceVO;
import vo.financevo.ProfitVO;
import vo.receiptvo.ChargeReceiptVO;
import vo.receiptvo.PayReceiptVO;
import vo.receiptvo.ReceiptVO;

public interface FinanceBLService {

	/**
	 * 在一个收款回合中，新建收款单
	 * 更新总收入
	 * @param vo
	 * @throws RemoteException 
	 * @throws SQLException 
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public void submitIn(ChargeReceiptVO vo) throws RemoteException, SQLException, MalformedURLException, NotBoundException, InfoBLException;
	
	/**
	 * 在一个付款回合中，新建付款单
	 * 更新总支出
	 * @param vo
	 * @throws RemoteException 
	 * @throws SQLException 
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public void submitOut(PayReceiptVO vo) throws RemoteException, SQLException, MalformedURLException, NotBoundException;
	
	/**
	 * 期初建账后的账目查询
	 * @param year
	 * @return
	 * @throws RemoteException 
	 */
	public FinanceVO getCredit(int year) throws IOException, ClassNotFoundException;
	
	/**
	 *
	 * @return 计算收款（付款）金额
	 * @throws RemoteException 
	 * @throws SQLException 
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public ArrayList<SalaryFeeVO> calSalary() throws RemoteException, SQLException, MalformedURLException, NotBoundException;

	/**
	 * 
	 * @param fromTime
	 * @param toTime
	 * @return 返回该时间段内的所有收款单和付款单数据列表
	 * @throws RemoteException 
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 * @throws SQLException 
	 */
	public ArrayList<ReceiptVO> seeRecord(Date fromTime, Date toTime) throws RemoteException, MalformedURLException, NotBoundException, SQLException;

	/**
	 * 
	 * @param fromTime
	 * @param toTime
	 * @param StoreNum
	 * @return 返回指定营业厅指定日期的收款单数据列表
	 * @throws RemoteException 
	 * @throws SQLException 
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public ArrayList<ChargeReceiptVO> checkStore(Date fromTime, Date toTime, String StoreNum) throws RemoteException, SQLException, MalformedURLException, NotBoundException;

	/**
	 * 前置条件：已经获取收款单数据列表
	 * @return 返回所有收款单金额合计
	 */
	public AddUpResultVO addUp();
	
	/**
	 * 
	 * @return 返回截止当前日期的总收入、总支出、总利润
	 * @throws RemoteException 
	 */
	public ProfitVO checkProfit() throws RemoteException;
	
	/**
	 * 
	 * @param year
	 * @throws SQLException 
	 * @throws NamingException 
	 * @throws RemoteException 
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public void	makeCredit(int year) throws RemoteException, NamingException, SQLException, MalformedURLException, NotBoundException;
		
}
