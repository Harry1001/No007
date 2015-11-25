package businessLogicService.strategyblservice;

import java.rmi.RemoteException;

import typeDefinition.Job;

public interface CalSalaryService {
	/*
	 * 计算工资
	 */
	public double calSalary(Job job,int times) throws RemoteException;
}

