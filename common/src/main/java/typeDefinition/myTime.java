package typeDefinition;

import java.io.Serializable;
import java.util.Calendar;

public class myTime implements Serializable{
	
	private int year;
	private int month;
	private int date;
	private int hour;
	private int minute;
	private int second;
	
	public myTime(int year,int month, int date, int hour, int minute, int second) {
		this.setYear(year);
		this.setMonth(month);
		this.setDate(date);
		this.setHour(hour);
		this.setMinute(minute);
		this.setSecond(second);
	}
	
	public myTime(){
		Calendar calendar = Calendar.getInstance();
		this.year = calendar.get(Calendar.YEAR);
		this.month = calendar.get(Calendar.MONTH);
		this.date = calendar.get(Calendar.DAY_OF_MONTH);
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);		
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



	public int getHour() {
		return hour;
	}



	public void setHour(int hour) {
		this.hour = hour;
	}



	public int getMinute() {
		return minute;
	}



	public void setMinute(int minute) {
		this.minute = minute;
	}



	public int getSecond() {
		return second;
	}



	public void setSecond(int second) {
		this.second = second;
	}
}
