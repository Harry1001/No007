package vo;

import java.io.Serializable;
import java.util.ArrayList;

import po.DriverPO;
import po.StaffPO;

public class FinanceVO implements Serializable{

	int year;
	ArrayList<StaffVO> staffs;
	ArrayList<DriverVO> drivers;
	ArrayList<AgencyVO> agencies;
	ArrayList<TruckVO> trucks;
	ArrayList<CommodityVO> commodity;
	ArrayList<BankAccountVO> bankAccounts;
	
	public FinanceVO(int year) {
		// TODO Auto-generated constructor stub
		this.year = year;
		this.staffs = new ArrayList<StaffVO>();
		this.drivers = new ArrayList<DriverVO>();
		this.agencies = new ArrayList<AgencyVO>();
		this.trucks = new ArrayList<TruckVO>();
		this.commodity = new ArrayList<CommodityVO>();
		this.bankAccounts = new ArrayList<BankAccountVO>();
	}
		
}