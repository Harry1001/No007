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
import po.infopo.StaffPO;
import typeDefinition.FeeType;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;
import vo.financevo.AddUpResultVO;
import vo.financevo.FinanceVO;
import vo.financevo.ProfitVO;
import vo.infovo.StaffVO;
import vo.receiptvo.ChargeReceiptVO;
import vo.receiptvo.PayReceiptVO;
import vo.receiptvo.ReceiptVO;
import vo.salaryfeevo.SalaryFeeVO;

public class FinanceBL implements FinanceBLService{

	double incomesum;
	myTime time;
	String storeNo;
	
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
		StaffInfoBL staff = new StaffInfoBL();
		for(StaffVO s : staff.getStaffList()){
			SalaryFeeVO salary = new SalaryFeeVO();
			salary.setName(s.getName());
			salary.setPosition(s.getPosition());
			salary.setStaffID(s.getStaffID());
			salary.setSalary(strategy.calSalary(s.getPosition(), s.getWorkFrequency()));
			salaryList.add(salary);
		}
		return salaryList;
	}

	public ArrayList<ReceiptVO> seeRecord(myTime fromTime, myTime toTime) {
		// TODO Auto-generated method stub
		ReceiptBL receipt = new ReceiptBL();
		ArrayList<ReceiptVO> charges = (ArrayList<ReceiptVO>)
						receipt.getListByTime(fromTime, toTime, ReceiptType.CHARGE);
		ArrayList<ReceiptVO> pays = (ArrayList<ReceiptVO>)
						receipt.getListByTime(fromTime, toTime, ReceiptType.PAY);
		ArrayList<ReceiptVO> result = charges;
		result.addAll(pays);
		return result;
	}

	public ArrayList<ReceiptVO> checkStore(myTime fromTime, myTime toTime, String StoreNum) {
		// TODO Auto-generated method stub																						
		ReceiptBL receipt = new ReceiptBL();
		ArrayList<ReceiptVO> charges = (ArrayList<ReceiptVO>)
						receipt.getListByTime(fromTime, toTime, ReceiptType.CHARGE);
		ArrayList<ReceiptVO> result = new ArrayList<ReceiptVO>();
		incomesum = 0;
		storeNo = StoreNum;
		time = fromTime;
		for(ReceiptVO r: charges){
			ChargeReceiptVO c = (ChargeReceiptVO)r;
			String courierID = c.getCourier();
//快递员工号前6位为营业厅编号
			String sNum = courierID.substring(0, 6);
			if(sNum.equals(StoreNum)){
				incomesum += c.getFee();
				result.add(r);
			}
		}
		return result;
	}

	public AddUpResultVO addUp() {
		// TODO Auto-generated method stub
		AddUpResultVO result = new AddUpResultVO();
		result.setIncomesum(incomesum);
		result.setStoreNo(storeNo);
		result.setTime(time);
		return result;
	}

	public ProfitVO checkProfit() {
		// TODO Auto-generated method stub
		FinanceDataService finance = new FinanceDataImpl();
		ProfitVO profit = new ProfitVO();
		try {
			profit.income = finance.getIn();
			profit.outcome = finance.getOut();
			profit.profit = profit.income - profit.outcome;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return profit;
	}

	public void makeCredit(int year) {
		// TODO Auto-generated method stub
		
	}


}
