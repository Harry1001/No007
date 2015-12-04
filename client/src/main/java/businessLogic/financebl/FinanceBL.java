package businessLogic.financebl;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.Enumeration;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;

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
import vo.receiptvo.PayReceiptVO;
import vo.receiptvo.ReceiptVO;
import vo.salaryfeevo.SalaryFeeVO;

public class FinanceBL implements FinanceBLService{

	double incomesum;
	Date time;
	String storeNo;
	
	private FinanceDataService financeData;
	
	public FinanceBL() throws NamingException{
		Context namingContext = new InitialContext();
		
		System.out.println("RMI registry bindings:");
		Enumeration<NameClassPair> e = namingContext.list("rmi://localhost/");
		while(e.hasMoreElements())
			System.out.println(e.nextElement().getName());
		
		String url = "rmi://localhost/central_finance";
		this.financeData = (FinanceDataService)namingContext.lookup(url);
		
	}

	/**
	 * 增加银行收入
	 * @param vo
	 * @throws RemoteException
	 */
	public void submitIn(ChargeReceiptVO vo) throws RemoteException{
		// TODO Auto-generated method stub
		ReceiptBLService receiptBL = BLFactory.getReceiptBLService();
		receiptBL.createReceipt(vo);
		financeData.addIncome(vo.getFee());
	}

	/**
	 * 成本管理
	 * @param vo
	 * @throws RemoteException
	 */
	public void submitOut(PayReceiptVO vo) throws RemoteException{
		ReceiptBLService receiptBL = BLFactory.getReceiptBLService();
		receiptBL.createReceipt(vo);
		financeData.addOutcome(vo.getFee());
	}

	public FinanceVO getCredit(int year) throws RemoteException {
		// TODO Auto-generated method stub
		FinancePO credit = financeData.find(year);
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

	public ArrayList<ReceiptVO> seeRecord(Date fromTime, Date toTime) throws RemoteException{
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

	/**
	 *收入管理
	 */
	public ArrayList<ChargeReceiptVO> checkStore(Date fromTime, Date toTime, String StoreNum) throws RemoteException{
		// TODO Auto-generated method stub																						
		ReceiptBL receipt = new ReceiptBL();
		ArrayList<ChargeReceiptVO> charges = (ArrayList<ChargeReceiptVO>)
						receipt.getListByTime(fromTime, toTime, ReceiptType.CHARGE);
		ArrayList<ChargeReceiptVO> result = new ArrayList<ChargeReceiptVO>();
		incomesum = 0;
		storeNo = StoreNum;
		time = fromTime;
		for(ChargeReceiptVO r: charges){
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

	public ProfitVO checkProfit() throws RemoteException {
		// TODO Auto-generated method stub
		ProfitVO profit = new ProfitVO();
		profit.income = financeData.getIncome();
		profit.outcome = financeData.getOutcome();
		profit.profit = profit.income.subtract(profit.outcome);
		return profit;
	}

	/**
	 * 期初建账
	 * @param year
	 * @throws RemoteException
	 * @throws NamingException
	 * @throws SQLException
	 */
	public void makeCredit(int year) throws RemoteException, NamingException, SQLException{
		// TODO Auto-generated method stub
		FinanceDataService financeData = new FinanceDataImpl();
		CommodityBL commodityBL=null;
		try{
			commodityBL = new CommodityBL();
		}catch (Exception e){
			//todo 此处这是为了写界面时调试，后续需要删除trycatch!!!!!!!!!!!!!
		}

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
