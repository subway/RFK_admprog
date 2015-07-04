package model;

/**
 * @author Sabba
 * 
 */
public class Phonenumber {
	String type;
	String number;

	public Phonenumber(String type, String number) {
		this.type = type;
		this.number = number;
	}

	// set values

	public void setPhonenumberrType(String newType) {
		this.type = newType;
	}

	public void setPhonenumber(String newNr) {
		this.number = newNr;
	}

	// get values

	public String getType() {
		return type;
	}

	public String getNumber() {
		return number;
	}
}
