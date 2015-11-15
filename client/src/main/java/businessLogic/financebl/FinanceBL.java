package businessLogic.financebl;

import java.util.ArrayList;
import java.util.Observable;

import businessLogicService.financeblservice.FinanceBLService;
import typeDefinition.FeeType;
import typeDefinition.myTime;
import vo.FinanceVO;
import vo.ProfitVO;
import vo.ReceiptVO;

public class FinanceBL implements FinanceBLService{

	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	public void submit(ReceiptVO receiptInputVO) {
		// TODO Auto-generated method stub
		
	}

	public FinanceVO getCredit(int year) {
		// TODO Auto-generated method stub
		return null;
	}

	public double calFee(FeeType feetype, ReceiptVO receiptInputVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<ReceiptVO> seeRecord(myTime fromTime, myTime toTime) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<ReceiptVO> checkStore(myTime fromTime, myTime toTime, String StoreNum) {
		// TODO Auto-generated method stub
		return null;
	}

	public String addUp() {
		// TODO Auto-generated method stub
		return null;
	}

	public ProfitVO checkProfit() {
		// TODO Auto-generated method stub
		return null;
	}

}
