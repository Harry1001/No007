package stub;

import java.rmi.RemoteException;

import dataService.FinanceDataService;
import po.financepo.FinancePO;

public class FinanceData_Stub implements FinanceDataService{

	public void add(FinancePO financePO) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public FinancePO find(int year) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateIn(int income) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void renewIn() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public long getIn() throws RemoteException {
		// TODO Auto-generated method stub
		return 200;
	}

	public void updateOut(int outcome) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void renewOut() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public long getOut() throws RemoteException {
		// TODO Auto-generated method stub
		return 150;
	}

}
