package vo.financevo;

import vo.commodityvo.CommodityVO;
import vo.infovo.StaffVO;
import vo.infovo.TruckVO;
import vo.infovo.AgencyVO;
import vo.infovo.BankAccountVO;
import vo.infovo.DriverVO;

import java.io.Serializable;
import java.util.ArrayList;

public class FinanceVO implements Serializable{

	private int year;
	private ArrayList<StaffVO> staffs;
	private ArrayList<DriverVO> drivers;
	private ArrayList<AgencyVO> agencies;
	private ArrayList<TruckVO> trucks;
	private ArrayList<CommodityVO> commodity;
	private ArrayList<BankAccountVO> bankAccounts;
	
	public FinanceVO(int year) {
		// TODO Auto-generated constructor stub
		this.setYear(year);
		this.setStaffs(new ArrayList<StaffVO>());
		this.setDrivers(new ArrayList<DriverVO>());
		this.setAgencies(new ArrayList<AgencyVO>());
		this.setTrucks(new ArrayList<TruckVO>());
		this.setCommodity(new ArrayList<CommodityVO>());
		this.setBankAccounts(new ArrayList<BankAccountVO>());
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public ArrayList<StaffVO> getStaffs() {
		return staffs;
	}

	public void setStaffs(ArrayList<StaffVO> staffs) {
		this.staffs = staffs;
	}

	public ArrayList<DriverVO> getDrivers() {
		return drivers;
	}

	public void setDrivers(ArrayList<DriverVO> drivers) {
		this.drivers = drivers;
	}

	public ArrayList<AgencyVO> getAgencies() {
		return agencies;
	}

	public void setAgencies(ArrayList<AgencyVO> agencies) {
		this.agencies = agencies;
	}

	public ArrayList<TruckVO> getTrucks() {
		return trucks;
	}

	public void setTrucks(ArrayList<TruckVO> trucks) {
		this.trucks = trucks;
	}

	public ArrayList<CommodityVO> getCommodity() {
		return commodity;
	}

	public void setCommodity(ArrayList<CommodityVO> commodity) {
		this.commodity = commodity;
	}

	public ArrayList<BankAccountVO> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(ArrayList<BankAccountVO> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}
		
}