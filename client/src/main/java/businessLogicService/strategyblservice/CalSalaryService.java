package businessLogicService.strategyblservice;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import typeDefinition.Job;

public interface CalSalaryService {
	/*
	 * 计算工资
	 */
	public double calSalary(Job job,int times) throws RemoteException, MalformedURLException, SQLException, NotBoundException;
}

