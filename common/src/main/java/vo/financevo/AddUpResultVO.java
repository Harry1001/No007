package vo.financevo;

import typeDefinition.myTime;

public class AddUpResultVO {

	private String storeNo;
	private myTime time;
	private double incomesum;
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public myTime getTime() {
		return time;
	}
	public void setTime(myTime time) {
		this.time = time;
	}
	public double getIncomesum() {
		return incomesum;
	}
	public void setIncomesum(double incomesum) {
		this.incomesum = incomesum;
	}
}
