package businessLogicService.financeblservice;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

import vo.salaryfeevo.SalaryFeeVO;
import vo.financevo.AddUpResultVO;
import vo.financevo.FinanceVO;
import vo.financevo.ProfitVO;
import vo.receiptvo.ReceiptVO;

public interface FinanceBLService {

	/**
	 * 在一个付款（收款）回合中，新建付款（收款）单
	 * 并且更新总收入，总支出数据
	 * @param receiptInputVO
	 */
	public void submit(ReceiptVO receiptInputVO) throws RemoteException;
	
	/**
	 * 期初建账后的账目查询
	 * @param year
	 * @return
	 */
	public FinanceVO getCredit(int year);
	
	/**
	 * 
	 * @param feetype
	 * @param receiptInputVO
	 * @return 计算收款（付款）金额
	 */
	public ArrayList<SalaryFeeVO> calSalary() throws RemoteException;

	/**
	 * 
	 * @param fromTime
	 * @param toTime
	 * @return 返回该时间段内的所有收款单和付款单数据列表
	 */
	public ArrayList<ReceiptVO> seeRecord(Date fromTime, Date toTime) throws RemoteException;

	/**
	 * 
	 * @param fromTime
	 * @param toTime
	 * @param StoreNum
	 * @return 返回指定营业厅指定日期的收款单数据列表
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
	 */
	public ProfitVO checkProfit();
	
	/**
	 * 
	 * @param year
	 */
	public void	makeCredit(int year) throws RemoteException;
		
}
