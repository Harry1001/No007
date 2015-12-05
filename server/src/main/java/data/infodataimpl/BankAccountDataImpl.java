package data.infodataimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import dataService.infodataservice.BankAccountDataService;
import database.BankAccountDBManager;
import myexceptions.InfoBLException;
import po.infopo.BankAccountPO;

public class BankAccountDataImpl extends UnicastRemoteObject implements BankAccountDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BankAccountDBManager bankAccountDBManager;
	
	public BankAccountDataImpl() throws RemoteException {
		super();
		this.bankAccountDBManager = new BankAccountDBManager();
	}

	public ArrayList<BankAccountPO> getList() throws RemoteException, SQLException {
		ArrayList<BankAccountPO> bankAccountPOs = bankAccountDBManager.getAll();
		return bankAccountPOs;
	}

	public void addItem(BankAccountPO item) throws RemoteException, InfoBLException, SQLException {
		bankAccountDBManager.addBankAccount(item);
	}

	public void deleteItem(String id) throws RemoteException, SQLException {
		bankAccountDBManager.deleteBankAccount(id);	
	}

	public void update(String id, double change) throws RemoteException, InfoBLException, SQLException {
		bankAccountDBManager.updateBankAccount(id, change);	
	}
}
