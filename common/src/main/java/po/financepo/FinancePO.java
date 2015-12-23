package po.financepo;

import java.io.Serializable;
import java.util.ArrayList;

import po.commoditypo.CommodityPO;
import po.infopo.*;
import vo.commodityvo.CommodityVO;
import vo.financevo.FinanceVO;
import vo.infovo.*;

public class FinancePO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int year;
	private ArrayList<StaffPO> staffs;
	private ArrayList<DriverPO> drivers;
	private ArrayList<AgencyPO> agencies;
	private ArrayList<TruckPO> trucks;
	private ArrayList<CommodityPO> commodity;
	private ArrayList<BankAccountPO> bankAccounts;
	
	public FinancePO(int year) {
		// TODO Auto-generated constructor stub
		this.year = year;
		this.setStaffs(new ArrayList<StaffPO>());
		this.setDrivers(new ArrayList<DriverPO>());
		this.setAgencies(new ArrayList<AgencyPO>());
		this.setTrucks(new ArrayList<TruckPO>());
		this.setCommodity(new ArrayList<CommodityPO>());
		this.setBankAccounts(new ArrayList<BankAccountPO>());
	}
	
	public FinancePO(FinanceVO f){
		this.year = f.getYear();
		this.setStaffs(new ArrayList<StaffPO>());
		this.setDrivers(new ArrayList<DriverPO>());
		this.setAgencies(new ArrayList<AgencyPO>());
		this.setTrucks(new ArrayList<TruckPO>());
		this.setCommodity(new ArrayList<CommodityPO>());
		this.setBankAccounts(new ArrayList<BankAccountPO>());
		ArrayList<StaffVO> sVOs = f.getStaffs();
		for(StaffVO sVO: sVOs){
			this.staffs.add(new StaffPO(sVO));
		}
		for (DriverVO vo:f.getDrivers()){
			this.drivers.add(new DriverPO(vo));
		}
		for (AgencyVO vo:f.getAgencies()){
			this.agencies.add(new AgencyPO(vo));
		}
		for (TruckVO vo:f.getTrucks()){
			this.trucks.add(new TruckPO(vo));
		}
		for (CommodityVO vo:f.getCommodity()){
			this.commodity.add(new CommodityPO(vo));
		}
		for (BankAccountVO vo:f.getBankAccounts()){
			this.bankAccounts.add(new BankAccountPO(vo));
		}
		
	}

	//private StaffPO StaffPO(StaffVO sVO) {
	//	// TODO Auto-generated method stub
	//	return null;
	//}

	public ArrayList<StaffPO> getStaffs() {
		return staffs;
	}

	public void setStaffs(ArrayList<StaffPO> staffs) {
		this.staffs = staffs;
	}

	public ArrayList<DriverPO> getDrivers() {
		return drivers;
	}

	public void setDrivers(ArrayList<DriverPO> drivers) {
		this.drivers = drivers;
	}

	public ArrayList<AgencyPO> getAgencies() {
		return agencies;
	}

	public void setAgencies(ArrayList<AgencyPO> agencies) {
		this.agencies = agencies;
	}

	public ArrayList<CommodityPO> getCommodity() {
		return commodity;
	}

	public void setCommodity(ArrayList<CommodityPO> commodity) {
		this.commodity = commodity;
	}

	public ArrayList<BankAccountPO> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(ArrayList<BankAccountPO> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	public ArrayList<TruckPO> getTrucks() {
		return trucks;
	}

	public void setTrucks(ArrayList<TruckPO> trucks) {
		this.trucks = trucks;
	}
		
}