package businessLogic.financebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businessLogicService.financeblservice.FinanceBLService;
import typeDefinition.FeeType;
import typeDefinition.Date;
import vo.financevo.AddUpResultVO;
import vo.financevo.FinanceVO;
import vo.financevo.ProfitVO;
import vo.receiptvo.ReceiptVO;
import vo.salaryfeevo.SalaryFeeVO;

public class FinanceController implements FinanceBLService{

	FinanceBL financeBL = new FinanceBL();
	
	public void submit(ReceiptVO receiptInputVO) throws RemoteException {
		// TODO Auto-generated method stub
		financeBL.submit(receiptInputVO);
	}

	public FinanceVO getCredit(int year) {
		// TODO Auto-generated method stub
		FinanceVO financeVO = financeBL.getCredit(year);
		return financeVO;
	}

	public ArrayList<SalaryFeeVO> calSalary() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<SalaryFeeVO> salaryFeeVOs = financeBL.calSalary();
		return salaryFeeVOs;
	}

	public ArrayList<ReceiptVO> seeRecord(Date fromTime, Date toTime) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<ReceiptVO> receiptVOs = financeBL.seeRecord(fromTime, toTime);
		return receiptVOs;
	}

	public ArrayList<ReceiptVO> checkStore(Date fromTime, Date toTime, String StoreNum) throws RemoteException{
		// TODO Auto-generated method stub
		ArrayList<ReceiptVO> receiptVOs = financeBL.checkStore(fromTime, toTime, StoreNum);
		return receiptVOs;
	}

	public AddUpResultVO addUp() {
		// TODO Auto-generated method stub
		AddUpResultVO sum = financeBL.addUp();
		return sum;
	}

	public ProfitVO checkProfit() {
		// TODO Auto-generated method stub
		ProfitVO profitVO = financeBL.checkProfit();
		return profitVO;
	}

	public void makeCredit(int year) throws RemoteException {
		// TODO Auto-generated method stub
		financeBL.makeCredit(year);
	}
}
