package model;

/**
 * @author Sabba
 *
 */
public class Attendance {
	String ActivityID;
	String date;
	String total;
	String comment;

	public Attendance() {
		this.ActivityID = "";
		this.date = "";
		this.total = "";
		this.comment = "";
	}
	
	public Attendance(String id, String date, String total, String comment) {
		this.ActivityID = id;
		this.date = date;
		this.total = total;
		this.comment = comment;
	}

	// set values

	public void setID(String newID) {
		this.ActivityID = newID;
	}

	public void setDate(String newDate) {
		this.date = newDate;
	}

	public void setTotal(String newTotal) {
		this.total = newTotal;
	}

	public void setComment(String newComment) {
		this.comment = newComment;
	}

	// get values

	public String getID() {
		return ActivityID;
	}

	public String getDate() {
		return date;
	}

	public String getTotal() {
		return total;
	}

	public String getComment() {
		return comment;
	}
}
