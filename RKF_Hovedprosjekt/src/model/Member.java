package model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import view.Picture;

/**
 * @author Sabba
 *
 */
public class Member {
	private String ID;
	private String type;
	private String imageURL; // URL to memberPicture
	private String firstname;
	private String surname;
	private String address;
	private City city; // Contains zipcode & city
	private String dateOfBirth;
	private String gender;
	private Phonenumber[] phone; // Contains all nr added to this member
							// i.e work, mobile, private and so on
	private String email;
	private String enrollmentDate;
	private String validityDate;
	private String terminationDate;// equals validityDate by default
	private JTable log; // includes 5 last changes made to the object
	private JTable allLog; // includes all changes made to the object

	/**
	 * empty constructor with default values
	 */
	public Member() {
		this.ID = "";
		this.type = "";
		this.imageURL = "Image/Unknown.jpg";
		this.firstname = "";
		this.surname = "";
		this.address = "";
		this.city = new City();
		this.dateOfBirth = "";
		this.gender = "";
		this.phone = new Phonenumber[3];
		this.phone[0] = new Phonenumber("Mobil", "");
		this.phone[1] = new Phonenumber("Privat", "");
		this.phone[2] = new Phonenumber("Jobb", "");
		this.email = "";
		this.enrollmentDate = "";
		this.validityDate = "";
		this.terminationDate = "17-12-2005";
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

	public void setType(String newType) {
		this.type = newType;
	}

	public void setImage(String url) {
		this.imageURL = url;
	}

	public void setFirstname(String newFirstname) {
		this.firstname = newFirstname;
	}

	public void setSurname(String newSurname) {
		this.surname = newSurname;
	}

	public void setDateOfBirth(String newDateOfBirth) {
		this.dateOfBirth = newDateOfBirth;
	}

	public void setGender(String newGender) {
		this.gender = newGender;
	}

	public void setAddress(String newAddress) {
		this.address = newAddress;
	}

	public void setZipcode(String newZipcode) {
		this.city.setZipcode(newZipcode);
	}

	public void setCity(String newCity) {
		this.city.setCity(newCity);
	}

	public void setMobilenr(String newNr) {
		this.phone[0].setPhonenumber(newNr);
	}

	public void setPrivatenr(String newNr) {
		this.phone[1].setPhonenumber(newNr);
	}

	public void setWorknr(String newNr) {
		this.phone[2].setPhonenumber(newNr);
	}

	public void setEmail(String newEmail) {
		this.email = newEmail;
	}

	public void setEnrollmentDate(String newEnrollmentDate) {
		this.enrollmentDate = newEnrollmentDate;
	}

	public void setValidityDate(String newValidityDate) {
		this.validityDate = newValidityDate;
	}

	public void setTerminationDate(String newTerminationDate) {
		this.terminationDate = newTerminationDate;
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

	public String getType() {
		return type;
	}

	public Picture getImage() {
		return new Picture(imageURL, 130, 170);
	}

	public String getImageURL() {
		return imageURL;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getSurname() {
		return surname;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getGender() {
		return gender;
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

	public String getMobilenr() {
		return phone[0].getNumber();
	}

	public String getPrivatenr() {
		return phone[1].getNumber();
	}

	public String getWorknr() {
		return phone[2].getNumber() ;
	}

	public String getEmail() {
		return email;
	}

	public String getEnrollmentDate() {
		return enrollmentDate;
	}

	public String getValidityDate() {
		return validityDate;
	}

	public String getTerminationDate() {
		return terminationDate;
	}

	public JTable getLog() {
		return log;
	}

	public JTable getAllLog() {
		return allLog;
	}

}
