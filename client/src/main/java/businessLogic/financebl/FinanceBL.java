package businessLogic.financebl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;

import businessLogic.infobl.InfoBL;
import businessLogic.infobl.StaffInfoBL;
import businessLogic.receiptbl.ReceiptBL;
import businessLogic.strategybl.StrategyBL;
import businessLogicService.financeblservice.FinanceBLService;
import businessLogicService.receiptblservice.ReceiptBLService;
import businessLogicService.strategyblservice.StrategyBLService;
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
		ReceiptBL receiptBL = new ReceiptBL();
		receiptBL.createReceipt(receiptInputVO);
	}

	public FinanceVO getCredit(int year) {
		// TODO Auto-generated method stub
		FinanceDataService financeDataService = new FinanceDataImpl();
		FinancePO credit = new FinancePO(null);
		try {
			credit = financeDataService.find(year);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FinanceVO fVO = new FinanceVO(credit);
		return fVO;
	}

	public ArrayList<SalaryFeeVO> calSalary() {
		// TODO Auto-generated method stub
		StrategyBL strategy = new StrategyBL();
		ArrayList<SalaryFeeVO> salaryList = new ArrayList<SalaryFeeVO>();
		
		return null;
	}

	public ArrayList<ReceiptVO> seeRecord(myTime fromTime, myTime toTime) {
		// TODO Auto-generated method stub
		ReceiptBL receipt = new ReceiptBL();

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
