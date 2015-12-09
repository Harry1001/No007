package data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import dataService.FinanceDataService;
import database.FinanceSerial;
import database.ProfitDBManager;
import po.financepo.FinancePO;

public class FinanceDataImpl extends UnicastRemoteObject implements FinanceDataService{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FinanceDataImpl() throws RemoteException {
		super();
	}

	private ProfitDBManager profitDBManager = new ProfitDBManager();
	private FinanceSerial financeSerial=new FinanceSerial();
	
	public void add(FinancePO financePO, int year) throws FileNotFoundException, IOException {
		financeSerial.serialize(financePO, year);
		
	}

	public FinancePO find(int year) throws FileNotFoundException, ClassNotFoundException, IOException {
		FinancePO po = financeSerial.read(year);
		return po;
	}

	public void addIncome(double income) throws RemoteException {
		profitDBManager.addIncome(income);
	}

	public void renewIncome() throws RemoteException {
		profitDBManager.renewIncome();
	}

	public BigDecimal getIncome() throws RemoteException {
		BigDecimal income = profitDBManager.getIncome();
		return income;
	}

	public void addOutcome(double outcome) throws RemoteException {
		profitDBManager.addOutcome(outcome);
	}

	public void renewOutcome() throws RemoteException {
		profitDBManager.renewOutcome();		
	}

	public BigDecimal getOutcome() throws RemoteException {
		BigDecimal outcome = profitDBManager.getOutcome();
		return outcome;
	}



}
