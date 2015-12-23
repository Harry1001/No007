package dataService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

import po.financepo.FinancePO;

public interface FinanceDataService extends Remote {

	/**
	 * 在账目数据库中增加一个持久化对象
	 * @param financePO
	 * @throws RemoteException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void add(FinancePO financePO, int year) throws RemoteException, FileNotFoundException, IOException;

	/**
	 *
	 * @param year
	 * @return 根据年份信息返回账目数据库中的一个持久化对象
	 * @throws RemoteException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 */
	public FinancePO find(int year) throws RemoteException, FileNotFoundException, ClassNotFoundException, IOException;

	/**
	 * 更新数据端的总收入数据
	 * @param income
	 * @throws RemoteException
	 */
	public void addIncome(double income) throws RemoteException;

	/**
	 * 初始化数据端的总收入数据
	 * @throws RemoteException
	 */
	public void renewIncome() throws RemoteException;

	/**
	 *
	 * @return 返回账目数据库中的总收入数据
	 * @throws RemoteException
	 */
	public BigDecimal getIncome() throws RemoteException;

	/**
	 * 更新数据端的总支出数据
	 * @param outcome
	 * @throws RemoteException
	 */
	public void addOutcome(double outcome) throws RemoteException;

	/**
	 * 初始化账目数据端的总支出数据
	 * @throws RemoteException
	 */
	public void renewOutcome() throws RemoteException;

	/**
	 *
	 * @return 返回账目数据库中的总支出数据
	 * @throws RemoteException
	 */
	public BigDecimal getOutcome() throws RemoteException;
}

