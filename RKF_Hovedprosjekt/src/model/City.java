package model;

/**
 * @author Sabba
 *
 */
public class City {
	private String zipcode;
	private String city;

	public City() {
		this.zipcode = "";
		this.city = "";
	}

	// set values

	public void setZipcode(String newZipcode) {
		this.zipcode = newZipcode;
	}

	public void setCity(String newCity) {
		this.city = newCity;
	}

	// get values

	public String getZipcode() {
		return zipcode;
	}

	public String getCity() {
		return city;
	}
}
