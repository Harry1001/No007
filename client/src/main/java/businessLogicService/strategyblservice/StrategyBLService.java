package businessLogicService.strategyblservice;

import typeDefinition.Job;
import vo.receiptvo.ReceiptVO;
import vo.strategyvo.CarriageFeeVO;
import vo.strategyvo.ExpressFeeVO;
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
	 * 计算薪水
	 */
	public double calSalary(Job job,int times);
	/*
	 * 设置快递费用策略
	 */
	public void setExpressFee(ExpressFeeVO vo);
	/*
	 * 设置运费策略
	 */
	public void setCarriage(CarriageFeeVO vo);
	/*
	 * 设置薪水策略
	 */
	public void setSalary(SalaryVO vo);
	/*
	 * 提供快递费用策略
	 */
	public ExpressFeeVO getExpressFee();
	/*
	 * 提供运费策略
	 */
	public CarriageFeeVO getCarriage();
	/*
	 * 提供薪水策略
	 */
	public SalaryVO getSalary();
}
