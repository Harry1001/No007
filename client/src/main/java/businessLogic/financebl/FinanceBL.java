package businessLogic.financebl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import blfactory.BLFactory;
import businessLogic.commoditybl.CommodityBL;
import businessLogicService.commodityblservice.CommodityBLService;
import businessLogicService.financeblservice.FinanceBLService;
import businessLogicService.infoblservice.AgencyBLService;
import businessLogicService.infoblservice.BankAccountBLService;
import businessLogicService.infoblservice.DriverBLService;
import businessLogicService.infoblservice.StaffBLService;
import businessLogicService.infoblservice.TruckBLService;
import businessLogicService.receiptblservice.ChargeReceiptBLService;
import businessLogicService.receiptblservice.PayReceiptBLService;
import businessLogicService.strategyblservice.CalSalaryService;
import data.FinanceDataImpl;
import dataService.FinanceDataService;
import dataService._RMI;
import myexceptions.InfoBLException;
import po.financepo.FinancePO;
import sun.management.snmp.jvmmib.JvmOSMBean;
import typeDefinition.Job;
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
	
	/**
	 * 构造器
	 * @throws NamingException
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public FinanceBL() throws NamingException, MalformedURLException, RemoteException, NotBoundException{
		String url = "rmi://"+_RMI.getIP()+"/central_finance";
		this.financeData = (FinanceDataService)Naming.lookup(url);
		
	}

	/**
	 * 增加银行收入
	 * @param vo
	 * @throws RemoteException
	 * @throws SQLException 
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public void submitIn(ChargeReceiptVO vo) throws RemoteException, SQLException, MalformedURLException, NotBoundException, InfoBLException {
		// TODO Auto-generated method stub
		ChargeReceiptBLService receiptBL = BLFactory.getChargeReceiptBLService();
		receiptBL.createReceipt(vo);
		financeData.addIncome(vo.getFee());
		updateBankBanlance(vo.getFee());
	}

	/**
	 * 成本管理
	 * @param vo
	 * @throws RemoteException
	 * @throws SQLException 
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public void submitOut(PayReceiptVO vo) throws RemoteException, SQLException, MalformedURLException, NotBoundException{
		PayReceiptBLService receiptBL = BLFactory.getPayReceiptBLService();
		receiptBL.createReceipt(vo);
		financeData.addOutcome(vo.getFee());
	}

	/**
	 *
	 * @param change 改变的金额，正数增加，负数减少
	 */
	private void updateBankBanlance( double change) throws RemoteException, NotBoundException, MalformedURLException, InfoBLException, SQLException {
		BankAccountBLService bankAccountBLService=BLFactory.getBankAccountBLService();
		ArrayList<BankAccountVO> vos = bankAccountBLService.getBankAccountList();
		if (vos.size()<=0){
			throw new InfoBLException("无银行账户，改变余额失败");
		}
		else {
			BankAccountVO vo=vos.get(0);
			String account=vo.getAccountUser();//去数据库中第一个银行作为默认银行
			bankAccountBLService.modifyBankAccount(account, change);
		}
	}

	public FinanceVO getCredit(int year) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		FinancePO credit = financeData.find(year);
		FinanceVO fVO = new FinanceVO(credit);
		return fVO;
	}

	public ArrayList<SalaryFeeVO> calSalary() throws RemoteException, SQLException, MalformedURLException, NotBoundException{
		// TODO Auto-generated method stub
		CalSalaryService strategy=BLFactory.getCalSalaryService();
		ArrayList<SalaryFeeVO> salaryList = new ArrayList<SalaryFeeVO>();
		StaffBLService staff = BLFactory.getStaffBLService();
		DriverBLService driverBLService=BLFactory.getDriverBLService();
		ArrayList<StaffVO> staffVOs=staff.getStaffList();
		ArrayList<DriverVO> driverVOs=driverBLService.getDriverList();
		for(StaffVO s : staffVOs){
			SalaryFeeVO salary = new SalaryFeeVO();
			salary.setName(s.getName());
			salary.setPosition(s.getPosition());
			salary.setStaffID(s.getStaffID());
			salary.setSalary(strategy.calSalary(s.getPosition(), s.getWorkFrequency()));
			try {
				staff.refreshWorkFrequency(s.getStaffID());
			} catch (InfoBLException e) {
				e.printStackTrace();
			}
			salaryList.add(salary);
		}
		for (DriverVO vo:driverVOs){
			SalaryFeeVO salaryFeeVO=new SalaryFeeVO();
			salaryFeeVO.setName(vo.getName());
			salaryFeeVO.setPosition(Job.DRIVER);
			salaryFeeVO.setStaffID(vo.getDriverID());
			salaryFeeVO.setSalary(strategy.calSalary(Job.DRIVER, 0));
			salaryList.add(salaryFeeVO);
		}

		return salaryList;
	}

	public ArrayList<ReceiptVO> seeRecord(Date fromTime, Date toTime) throws RemoteException, MalformedURLException, NotBoundException, SQLException{
		// TODO Auto-generated method stub
		ArrayList<ReceiptVO> result = new ArrayList<ReceiptVO>();
		ChargeReceiptBLService chargeReceiptBLService = BLFactory.getChargeReceiptBLService();
		ArrayList<ChargeReceiptVO> charges = chargeReceiptBLService.getListByTime(fromTime, toTime);
		PayReceiptBLService payReceiptBLService = BLFactory.getPayReceiptBLService();
		ArrayList<PayReceiptVO> pays = payReceiptBLService.getListByTime(fromTime, toTime);
		result.addAll(charges);
		result.addAll(pays);
		return result;
	}

	/**
	 *收入管理
	 * @throws SQLException 
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public ArrayList<ChargeReceiptVO> checkStore(Date fromTime, Date toTime, String StoreNum) throws RemoteException, SQLException, MalformedURLException, NotBoundException{
		// TODO Auto-generated method stub																						
		ChargeReceiptBLService chargeReceiptBLService = BLFactory.getChargeReceiptBLService();
		ArrayList<ChargeReceiptVO> charges = chargeReceiptBLService.getListByTime(fromTime, toTime);
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
		AddUpResultVO result = new AddUpResultVO();
		result.setIncomesum(incomesum);
		result.setStoreNo(storeNo);
		result.setTime(time);
		return result;
	}

	public ProfitVO checkProfit() throws RemoteException {
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
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public void makeCredit(int year) throws RemoteException, NamingException, SQLException, MalformedURLException, NotBoundException{
		FinanceDataService financeData = new FinanceDataImpl();
		CommodityBLService commodityBLService=null;
		try{
			commodityBLService = BLFactory.getCommodityBLService();
		}catch (Exception e){
			//todo 此处这是为了写界面时调试，后续需要删除trycatch!!!!!!!!!!!!!
		}

		AgencyBLService agencyInfoBL = BLFactory.getAgencyBLService();
		BankAccountBLService bankAccountInfoBL = BLFactory.getBankAccountBLService();
		DriverBLService driverInfoBL = BLFactory.getDriverBLService();
		StaffBLService staffInfoBL = BLFactory.getStaffBLService();
		TruckBLService truckInfoBL = BLFactory.getTruckBLService();
		ArrayList<CommodityVO> commodity = commodityBLService.getTotal();
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
			financeData.add(new FinancePO(finance),year);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
