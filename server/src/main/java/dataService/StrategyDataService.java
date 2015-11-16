package dataService;

import java.rmi.RemoteException;

import po.strategypo.CarriageFeePO;
import po.strategypo.ExpressFeePO;
import po.strategypo.SalaryPO;

public interface StrategyDataService {
    /*
     * 提供快递费策略
     */
	public ExpressFeePO getExpressFee() throws  RemoteException;
	/*
	 * 提供运费策略
	 */
	public CarriageFeePO getCarriageFee() throws  RemoteException;
	/*
	 * 更新快递策略
	 */
	public void updateExpressFeeStrategy(ExpressFeePO efpo) throws  RemoteException;
	/*
	 * 更新运费策略
	 */
	public void updateCarriageFeeStrategy(CarriageFeePO cfpo)throws  RemoteException;
	/*
	 * 提供薪水策略
	 */
	public SalaryPO getSalary() throws  RemoteException;
	/*
	 * 更新薪水策略
	 */
	public void updateSalaryStrategy(SalaryPO po) throws  RemoteException;
}