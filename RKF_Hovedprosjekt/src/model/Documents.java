package model;

/**
 * @author Sabba
 *
 */
public class Documents {
	String ActivityID;
	String url;

	public Documents() {
		this.ActivityID = "";
		this.url = "";
	}

	// set values

	public void setID(String newID) {
		this.ActivityID = newID;
	}

	public void setUrl(String newUrl) {
		this.url = newUrl;
	}

	// get values

	public String getID() {
		return ActivityID;
	}

	public String getUrl() {
		return url;
	}
}
