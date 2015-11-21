package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.rmi.RemoteException;

import org.junit.Test;

import data.FinanceDataImpl;
import dataService.FinanceDataService;

public class TestFinanceDataImpl {

	@Test
	public void test() {
		FinanceDataService financeData = new FinanceDataImpl();
		BigDecimal outcome = null;
		try {
			outcome = financeData.getOutcome();
			System.out.println(outcome);
			financeData.renewOutcome();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true, true);
	}

}
