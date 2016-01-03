package vo.financevo;

import po.infopo.*;
import typeDefinition.Job;
import vo.commodityvo.CommodityVO;
import vo.infovo.StaffVO;
import vo.infovo.TruckVO;
import vo.infovo.AgencyVO;
import vo.infovo.BankAccountVO;
import vo.infovo.DriverVO;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

import po.commoditypo.CommodityPO;
import po.financepo.FinancePO;

public class FinanceVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	public FinanceVO(FinancePO f){
		this.year = f.year;
		ArrayList<StaffVO> sVOs = new ArrayList<StaffVO>();
		for(StaffPO s: f.getStaffs()){
			StaffVO sVO = new StaffVO(s);
			sVOs.add(sVO);
		}
		this.setStaffs(sVOs);
		
		ArrayList<DriverVO> dVOs = new ArrayList<DriverVO>();
		for(DriverPO d: f.getDrivers()){
			DriverVO dVO = new DriverVO(d);
			dVOs.add(dVO);
		}
		this.setDrivers(dVOs);
		
		ArrayList<AgencyVO> aVOs = new ArrayList<AgencyVO>();
		for(AgencyPO a: f.getAgencies()){
			AgencyVO aVO = new AgencyVO(a);
			aVOs.add(aVO);
		}
		this.setAgencies(aVOs);
		
		ArrayList<TruckVO> tVOs = new ArrayList<TruckVO>();
		for(TruckPO t: f.getTrucks()){
			TruckVO tVO = new TruckVO(t);
			tVOs.add(tVO);
		}
		this.setTrucks(tVOs);
		
		ArrayList<CommodityVO> cVOs = new ArrayList<CommodityVO>();
		for(CommodityPO c: f.getCommodity()){
			CommodityVO cVO = new CommodityVO(c);
			cVOs.add(cVO);
		}
		this.setCommodity(cVOs);

		ArrayList<BankAccountVO> bVOs=new ArrayList<BankAccountVO>();
		for (BankAccountPO bpo: f.getBankAccounts()){
			BankAccountVO bvo=new BankAccountVO(bpo);
			bVOs.add(bvo);
		}
		this.setBankAccounts(bVOs);
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

	public String printStaff(){
		int staffNum=staffs.size();
		int [] explicitNum=new int[9];//对应Job枚举类中的每个职位
		for (int i=0;i<explicitNum.length;i++){
			explicitNum[i]=0;
		}
		for (StaffVO vo : staffs){//表驱动
			int index= vo.getPosition().ordinal();
			explicitNum[index]++;
		}
		//explicitNum[0]是寄件人，一直为0，不考虑
		String result="\r\n公司总人员数:"+staffNum+", 其中快递员:"+explicitNum[1]+", 营业厅业务员:"+explicitNum[2]+
				", 中转中心业务员:"+explicitNum[3]+", 财务人员"+explicitNum[4]+", 仓库管理员:"+explicitNum[5]+
				", 总经理:"+explicitNum[6]+", 系统管理员"+explicitNum[7]+", 司机:"+explicitNum[8]+"; ";
		return result;
	}

	public String printAgency(){
		int agencyNum=agencies.size();
		int store=0;
		int hub=0;
		long storeArea=0;
		long hubArea=0;
		for (AgencyVO vo:agencies){
			if (vo.getAgencyType().equals("中转中心")){
				hub++;
				hubArea+=vo.getArea();
			}
			else {
				store++;
				storeArea+=vo.getArea();
			}
		}
		String result="\r\n公司总机构数:"+agencyNum+", 其中中转中心:"+hub+", 营业厅:"+store+". 中转中心总面积:"+hubArea+
				", 营业厅总面积:"+storeArea+"; ";
		return result;
	}

	public String ptintTruck(){
		int truckNum=trucks.size();
		return "\r\n公司总车辆:"+truckNum+"; ";
	}

	public String printBankAccount(){
		DecimalFormat df=new DecimalFormat("#.00");
		int accNum=bankAccounts.size();
		String result="\r\n公司共有"+accNum+"个银行账户, 分别为:";
		for(BankAccountVO vo:bankAccounts){
			result=result+"帐号:"+vo.getAccountUser()+", 余额:"+df.format(vo.getBalance())+"; ";
		}
		return result;
	}
		
}