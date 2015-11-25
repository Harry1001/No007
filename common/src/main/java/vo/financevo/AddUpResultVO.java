package vo.financevo;

import typeDefinition.Date;

public class AddUpResultVO {

	private String storeNo;
	private Date time;
	private double incomesum;
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public double getIncomesum() {
		return incomesum;
	}
	public void setIncomesum(double incomesum) {
		this.incomesum = incomesum;
	}
}
