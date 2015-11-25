package businessLogicService.strategyblservice;

import java.rmi.RemoteException;

import vo.strategyvo.*;

public interface FeeStrategyBLService {
	/*
	 * 设置快递费的策略
	 */
	public void setExpressFee(ExpressFeeVO vo) throws RemoteException;
	/*
	 * 得到快递费的策略
	 */
	public ExpressFeeVO getExpressFee() throws RemoteException;
	/*
	 * 设置运费的策略
	 */
	public void setCarriage(CarriageFeeVO vo) throws RemoteException;
	/*
	 * 得到运费的策略
	 */
	public CarriageFeeVO getCarriageFee() throws RemoteException;
}
