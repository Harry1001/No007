package businessLogicService.strategyblservice;

import vo.receiptvo.ReceiptVO;
import vo.strategyvo.SalaryVO;

public interface StrategyBLService {
	/*
	 * 计算快递费
	 */
	public double calExpressFee(ReceiptVO vo);
	/*
	 * 计算运费
	 */
	public double calCarriage(ReceiptVO vo);
	/*
	 * 提供薪水策略
	 */
	public SalaryVO setSalary();
}
