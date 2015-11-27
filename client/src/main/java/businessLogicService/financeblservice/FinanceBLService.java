package businessLogicService.financeblservice;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.NamingException;

import typeDefinition.FeeType;
import vo.salaryfeevo.SalaryFeeVO;
import vo.financevo.AddUpResultVO;
import vo.financevo.FinanceVO;
import vo.financevo.ProfitVO;
import vo.receiptvo.ChargeReceiptVO;
import vo.receiptvo.PayReceiptVO;
import vo.receiptvo.ReceiptVO;
import vo.strategyvo.SalaryVO;

public interface FinanceBLService {

	/**
	 * 在一个收款回合中，新建收款单
	 * 更新总收入
	 * @param receiptInputVO
	 * @throws RemoteException 
	 */
	public void submitIn(ChargeReceiptVO vo) throws RemoteException;
	
	/**
	 * 在一个付款回合中，新建付款单
	 * 更新总支出
	 * @param vo
	 * @throws RemoteException 
	 */
	public void submitOut(PayReceiptVO vo) throws RemoteException;
	
	/**
	 * 期初建账后的账目查询
	 * @param year
	 * @return
	 * @throws RemoteException 
	 */
	public FinanceVO getCredit(int year) throws RemoteException;
	
	/**
	 * 
	 * @param feetype
	 * @param receiptInputVO
	 * @return 计算收款（付款）金额
	 * @throws RemoteException 
	 */
	public ArrayList<SalaryFeeVO> calSalary() throws RemoteException;

	/**
	 * 
	 * @param fromTime
	 * @param toTime
	 * @return 返回该时间段内的所有收款单和付款单数据列表
	 * @throws RemoteException 
	 */
	public ArrayList<ReceiptVO> seeRecord(Date fromTime, Date toTime) throws RemoteException;

	/**
	 * 
	 * @param fromTime
	 * @param toTime
	 * @param StoreNum
	 * @return 返回指定营业厅指定日期的收款单数据列表
	 * @throws RemoteException 
	 */
	public ArrayList<ReceiptVO> checkStore(Date fromTime, Date toTime, String StoreNum) throws RemoteException;

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
	 */
	public void	makeCredit(int year) throws RemoteException, NamingException, SQLException;
		
}
