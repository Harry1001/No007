package businessLogicService.strategyblservice;

import java.rmi.RemoteException;
import java.sql.SQLException;

import vo.strategyvo.SalaryVO;

public interface SalaryStrategyBLService {
	/**
	 * 从界面层设置薪水
	 * @param vo
	 * @throws RemoteException
	 * @throws SQLException 
	 */
	public void setSalary(SalaryVO vo) throws RemoteException, SQLException;
	/**
	 * 从界面层得到薪水
	 * @return
	 * @throws RemoteException
	 * @throws SQLException 
	 */
	public SalaryVO getSalary() throws RemoteException, SQLException;
}
