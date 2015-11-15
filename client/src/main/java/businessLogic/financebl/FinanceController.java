package businessLogic.financebl;

import java.util.ArrayList;

import businessLogicService.financeblservice.FinanceBLService;
import typeDefinition.FeeType;
import typeDefinition.myTime;
import vo.financevo.FinanceVO;
import vo.financevo.ProfitVO;
import vo.receiptvo.ReceiptVO;
import vo.salaryfeevo.SalaryFeeVO;

public class FinanceController implements FinanceBLService{

	FinanceBL financeBL = new FinanceBL();
	
	public void submit(ReceiptVO receiptInputVO) {
		// TODO Auto-generated method stub
		financeBL.submit(receiptInputVO);
	}

	public FinanceVO getCredit(int year) {
		// TODO Auto-generated method stub
		FinanceVO financeVO = financeBL.getCredit(year);
		return financeVO;
	}

	public ArrayList<SalaryFeeVO> calSalary() {
		// TODO Auto-generated method stub
		ArrayList<SalaryFeeVO> salaryFeeVOs = financeBL.calSalary();
		return salaryFeeVOs;
	}

	public ArrayList<ReceiptVO> seeRecord(myTime fromTime, myTime toTime) {
		// TODO Auto-generated method stub
		ArrayList<ReceiptVO> receiptVOs = financeBL.seeRecord(fromTime, toTime);
		return receiptVOs;
	}

	public ArrayList<ReceiptVO> checkStore(myTime fromTime, myTime toTime, String StoreNum) {
		// TODO Auto-generated method stub
		ArrayList<ReceiptVO> receiptVOs = financeBL.checkStore(fromTime, toTime, StoreNum);
		return receiptVOs;
	}

	public double addUp() {
		// TODO Auto-generated method stub
		double sum = financeBL.addUp();
		return sum;
	}

	public ProfitVO checkProfit() {
		// TODO Auto-generated method stub
		ProfitVO profitVO = financeBL.checkProfit();
		return profitVO;
	}
}
