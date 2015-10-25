package businessLogicService;

import businessLogic.BLImpl;
import vo.ReceiptVO;
import vo.SalaryVO;

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
	/*
	 * 向监听者注册，记录策略发生的变化
	 */
	public void register(BLImpl listener);
}
