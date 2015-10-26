package stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businessLogicService.FinanceBLService;
import businessLogicService.ReceiptBLService;
import dataService.FinanceDataService;
import po.FinancePO;
import typeDefinition.FeeType;
import typeDefinition.myTime;
import vo.AgencyVO;
import vo.FinanceVO;
import vo.ProfitVO;
import vo.ReceiptVO;

public class FinanceBL_Stub implements FinanceBLService{
	
	ReceiptBLService rbs;
	FinanceDataService fds = new FinanceData_Stub();

	public void submit(ReceiptVO receiptInputVO) {
		// TODO Auto-generated method stub
		
	}

	public FinanceVO getCredit(int year) {
		// TODO Auto-generated method stub
		FinanceVO f = new FinanceVO(2015);
		ArrayList<AgencyVO> a = new ArrayList<AgencyVO>();
		a.add(new AgencyVO(new String("鼓楼营业厅"),new String("营业厅"),new String("025001"),new String("南京市鼓楼区**路**号"),
				15, 2500));
		f.setAgencies(a);
		return f;
	}

	public double calFee(FeeType feetype, ReceiptVO receiptInputVO) {
		// TODO Auto-generated method stub
		return 20;
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
		return "100";
	}

	public ProfitVO checkProfit() {
		// TODO Auto-generated method stub
		return null;
	}
}
