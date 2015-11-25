package businessLogicService.strategyblservice;

import java.rmi.RemoteException;

import vo.strategyvo.SalaryVO;

public interface SalaryStrategyBLService {
	/**
	 * 从界面层设置薪水
	 * @param vo
	 * @throws RemoteException
	 */
	public void setSalary(SalaryVO vo) throws RemoteException;
	/**
	 * 从界面层得到薪水
	 * @return
	 * @throws RemoteException
	 */
	public SalaryVO getSalary() throws RemoteException;
}
