package businessLogic.financebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import blfactory.BLFactory;
import businessLogic.commoditybl.CommodityBL;
import businessLogic.infobl.bl.AgencyInfoBL;
import businessLogic.infobl.bl.BankAccountInfoBL;
import businessLogic.infobl.bl.DriverInfoBL;
import businessLogic.infobl.bl.StaffInfoBL;
import businessLogic.infobl.bl.TruckInfoBL;
import businessLogic.receiptbl.ReceiptBL;
import businessLogic.strategybl.StrategyBL;
import businessLogicService.financeblservice.FinanceBLService;
import businessLogicService.receiptblservice.ReceiptBLService;
import businessLogicService.strategyblservice.CalSalaryService;
import data.FinanceDataImpl;
import dataService.FinanceDataService;
import po.financepo.FinancePO;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;
import vo.commodityvo.CommodityVO;
import vo.financevo.AddUpResultVO;
import vo.financevo.FinanceVO;
import vo.financevo.ProfitVO;
import vo.infovo.AgencyVO;
import vo.infovo.BankAccountVO;
import vo.infovo.DriverVO;
import vo.infovo.StaffVO;
import vo.infovo.TruckVO;
import vo.receiptvo.ChargeReceiptVO;
import vo.receiptvo.ReceiptVO;
import vo.salaryfeevo.SalaryFeeVO;

public class FinanceBL implements FinanceBLService{

	double incomesum;
	myTime time;
	String storeNo;
	
	public void submit(ReceiptVO receiptInputVO) throws RemoteException{
		// TODO Auto-generated method stub
		ReceiptBLService receiptBL = BLFactory.getReceiptBLService();
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

	public ArrayList<SalaryFeeVO> calSalary() throws RemoteException{
		// TODO Auto-generated method stub
		CalSalaryService strategy=BLFactory.getCalSalaryService();
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

	public ArrayList<ReceiptVO> seeRecord(myTime fromTime, myTime toTime) throws RemoteException{
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

	public ArrayList<ReceiptVO> checkStore(myTime fromTime, myTime toTime, String StoreNum) throws RemoteException{
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
			profit.profit = profit.income.subtract(profit.outcome);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return profit;
	}

	public void makeCredit(int year) throws RemoteException{
		// TODO Auto-generated method stub
		FinanceDataService financeData = new FinanceDataImpl();
		CommodityBL commodityBL = new CommodityBL();
		AgencyInfoBL agencyInfoBL = new AgencyInfoBL();
		BankAccountInfoBL bankAccountInfoBL = new BankAccountInfoBL();
		DriverInfoBL driverInfoBL = new DriverInfoBL();
		StaffInfoBL staffInfoBL = new StaffInfoBL();
		TruckInfoBL truckInfoBL = new TruckInfoBL();
		ArrayList<CommodityVO> commodity = commodityBL.getTotal();
		ArrayList<AgencyVO> agency = agencyInfoBL.getAgencyList();
		ArrayList<BankAccountVO> bankaccount = 	bankAccountInfoBL.getBankAccountList();
		ArrayList<DriverVO> driver = driverInfoBL.getDriverList();
		ArrayList<StaffVO> staff = staffInfoBL.getStaffList();
		ArrayList<TruckVO> truck =truckInfoBL.getTruckList();
		
		FinanceVO finance = new FinanceVO(year);
		finance.setAgencies(agency);
		finance.setBankAccounts(bankaccount);
		finance.setCommodity(commodity);
		finance.setDrivers(driver);
		finance.setStaffs(staff);
		finance.setTrucks(truck);
		
		try {
			financeData.add(new FinancePO(finance));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
