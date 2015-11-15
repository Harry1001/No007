package businessLogic.financebl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;

import businessLogic.receiptbl.ReceiptBL;
import businessLogicService.financeblservice.FinanceBLService;
import data.FinanceDataImpl;
import data.ReceiptDataImpl;
import dataService.FinanceDataService;
import dataService.ReceiptDataService;
import po.financepo.FinancePO;
import po.infopo.AgencyPO;
import typeDefinition.FeeType;
import typeDefinition.myTime;
import vo.financevo.FinanceVO;
import vo.financevo.ProfitVO;
import vo.receiptvo.ReceiptVO;
import vo.salaryfeevo.SalaryFeeVO;

public class FinanceBL implements FinanceBLService{

	public void submit(ReceiptVO receiptInputVO) {
		// TODO Auto-generated method stub
		ReceiptDataService receiptData = new ReceiptDataImpl();
		ReceiptBL receiptBL = new ReceiptBL(receiptData);
		receiptBL.createReceipt(receiptInputVO);
	}

	public FinanceVO getCredit(int year) {
		// TODO Auto-generated method stub
		FinanceDataService financeDataService = new FinanceDataImpl();
		FinancePO credit;
		FinanceVO cVO = new FinanceVO(year);
		try {
			credit = financeDataService.find(year);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return cVO;
	}

	public ArrayList<SalaryFeeVO> calSalary() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<ReceiptVO> seeRecord(myTime fromTime, myTime toTime) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<ReceiptVO> checkStore(myTime fromTime, myTime toTime, String StoreNum) {
		// TODO Auto-generated method stub
		return null;
	}

	public double addUp() {
		// TODO Auto-generated method stub
		return 0;
	}

	public ProfitVO checkProfit() {
		// TODO Auto-generated method stub
		return null;
	}


}
