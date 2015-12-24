package businessLogic.strategybl;

import blfactory.StrategyFactory;
import businessLogicService.strategyblservice.StrategyPatternService.SalaryStrategyPatternService;
import dataService.StrategyDataService;
import dataService._RMI;
import po.strategypo.SalaryPO;
import typeDefinition.Job;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import businessLogicService.strategyblservice.CalSalaryService;

public class CalSalaryBL implements CalSalaryService {

	/**
	 * 利用策略模式计算人员工资
	 * @param job 人员职位
	 * @param times 工作次数，目前仅快递员和司机此项信息有用
	 * @return
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws SQLException
	 * @throws NotBoundException
	 */
	public double calSalary(Job job, int times) throws RemoteException, MalformedURLException, SQLException, NotBoundException {
		SalaryStrategyPatternService salaryStrategyService = StrategyFactory.getSalaryService(job);
		return salaryStrategyService.getSalary(times);
	}
}

