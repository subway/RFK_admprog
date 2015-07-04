package model;

/**
 * @author Sabba
 *
 */
public class Days {
	String day;
	String startTime;
	String endTime;

	public Days() {
		this.day = "";
		this.startTime = "";
		this.endTime = "";
	}

	public Days(String day, String start, String end) {
		this.day = day;
		this.startTime = start;
		this.endTime = end;
	}

	// set values

	public void setDay(String d) {
		this.day = d;
	}

	public void setStartTime(String time) {
		this.startTime = time;
	}

	public void setEndTime(String time) {
		this.endTime = time;
	}

	// get values

	public String getDay() {
		return day;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

}
