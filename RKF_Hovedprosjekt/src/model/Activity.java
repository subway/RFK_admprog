package model;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author Sabba
 *
 */
public class Activity {
	String ID;
	String name;
	String address;
	City city;
	String manager;
	String description;
	String startDate;
	String endDate;
	String totalSeats;
	private JTable log; // includes 5 last changes made to the object
	private JTable allLog; // includes all changes made to the object

	Days[] days;
	Attendance[] attendance;
	Documents document;

	public Activity() {
		this.ID = "";
		this.name = "";
		this.address = "";
		this.manager = "";
		this.description = "";
		this.startDate = "";
		this.endDate = "17-12-2005";
		this.totalSeats = "";
		this.document = new Documents();

		this.city = new City();
		this.days = new Days[7];

		for (int i = 0; i < days.length; i++) {
			days[i] = new Days();
		}

		this.attendance = null;

		String[] columnName = { "Resultat" };
		String[][] values = { { "Ingen oppføringer tilgjengelig" } };
		log = new JTable(new DefaultTableModel(values, columnName)) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}
		};
		allLog = new JTable(new DefaultTableModel(values, columnName)) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}
		};

	}

	// set values

	public void setID(String newID) {
		this.ID = newID;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public void setAddress(String newAdr) {
		this.address = newAdr;
	}

	public void setZipcode(String newZipcode) {
		this.city.setZipcode(newZipcode);
	}

	public void setCity(String newCity) {
		this.city.setCity(newCity);
	}

	public void setManager(String newManager) {
		this.manager = newManager;
	}

	public void setDescription(String newDesc) {
		this.description = newDesc;
	}

	public void setStartDate(String newStartDate) {
		this.startDate = newStartDate;
	}

	public void setEndDate(String newEndDate) {
		this.endDate = newEndDate;
	}

	public void setTotalSeats(String newTSeats) {
		this.totalSeats = newTSeats;
	}

	public void setDocument(String newDoc) {
		this.document.url = newDoc;
	}

	public void setDays(Days[] newDays) {
		this.days = newDays;
	}

	public void setAttendance(Attendance[] newStat) {
		this.attendance = newStat;
	}

	public void setLog(JTable log) {
		this.log = log;
	}

	public void setAllLog(JTable alllog) {
		this.allLog = alllog;
	}

	// get values

	public String getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getZipcode() {
		return city.getZipcode();
	}

	public String getCity() {
		return city.getCity();
	}

	public String getManager() {
		return manager;
	}

	public String getDescription() {
		return description;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getTotalSeats() {
		return totalSeats;
	}

	public Days[] getDays() {
		return days;
	}

	public Attendance[] getAttendance() {
		return attendance;
	}

	public String getDocument() {
		if (document.url.length() != 0) {
			int i = document.url.length() - 1;
			while (document.url.charAt(i) != '/')
				i--;
			return document.url.substring(i + 1);
		}
		return document.url;
	}

	public String getPath() {
		return document.url;
	}

	public JTable getLog() {
		return log;
	}

	public JTable getAllLog() {
		return allLog;
	}
}
