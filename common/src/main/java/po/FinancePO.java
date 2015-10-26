package po;

import java.io.Serializable;
import java.util.ArrayList;

public class FinancePO implements Serializable{

	int year;
	ArrayList<StaffPO> staffs;
	ArrayList<DriverPO> drivers;
	ArrayList<AgencyPO> agencies;
	ArrayList<TruckPO> trucks;
	ArrayList<CommodityPO> commodity;
	ArrayList<BankAccountPO> bankAccounts;
	
	public FinancePO(int year) {
		// TODO Auto-generated constructor stub
		this.year = year;
		this.staffs = new ArrayList<StaffPO>();
		this.drivers = new ArrayList<DriverPO>();
		this.agencies = new ArrayList<AgencyPO>();
		this.trucks = new ArrayList<TruckPO>();
		this.commodity = new ArrayList<CommodityPO>();
		this.bankAccounts = new ArrayList<BankAccountPO>();
	}
		
}