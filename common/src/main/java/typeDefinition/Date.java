package typeDefinition;

import java.io.Serializable;
import java.util.Calendar;

public class Date implements Serializable, Comparable{
	
	private int year;
	private int month;
	private int date;
	private int hour;
	private int minute;
	private int second;
	
	public Date(int year,int month, int date, int hour, int minute, int second) {
		this.setYear(year);
		this.setMonth(month);
		this.setDate(date);
		this.setHour(hour);
		this.setMinute(minute);
		this.setSecond(second);
	}
	
	public Date(){
		Calendar calendar = Calendar.getInstance();
		this.year = calendar.get(Calendar.YEAR);
		this.month = calendar.get(Calendar.MONTH);
		this.date = calendar.get(Calendar.DAY_OF_MONTH);
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);		
	}
	

	public Date(int year,int month, int date) {
		this.setYear(year);
		this.setMonth(month);
		this.setDate(date);
	}

	/**
	 * 比较方法不比较时分秒，只要日期相同则认为相等
	 */
	public int compareTo(Object oo) {
		Date o=(Date)oo;
		int result=-1;//默认时间比o的时间小
		if (this.getYear()>o.getYear()){
			return 1;
		}

		if (this.getYear()<o.getYear()){
			return -1;
		}

		if(this.getMonth()>o.getMonth())
			return 1;

		if (this.getMonth()<o.getMonth())
			return -1;

		if (this.getDate()>o.getDate())
			return 1;

		if (this.getDate()<o.getDate())
			return -1;

		return 0;
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
