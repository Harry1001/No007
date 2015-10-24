package typeDefinition;

import java.io.Serializable;

public class myTime implements Serializable{
	
	private int year;
	private int month;
	private int date;
	
	public myTime(int year,int month, int date) {
		this.setYear(year);
		this.setMonth(month);
		this.setDate(date);
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}
}
