package model;

import java.awt.Color;
import java.awt.Font;

/**
 * @author Sabba
 *
 */
public class User {
	private String username;
	private String password;
	private String type;
	private int backgroundcolor;
	private int foregroundcolor;
	private Font font;
	private String firstname;
	private String surname;
	private String dateOfBirth;
	private String phonenumber;

	public User() {
		this.username = "";
		this.password = "";
		this.type = "";
		this.backgroundcolor = -16750849;
		this.foregroundcolor = -1;
		this.font = new Font("Verdana", 0, 14);
		this.firstname = "";
		this.surname = "";
		this.dateOfBirth = "";
		this.phonenumber = "";
	}

	// set values

	public void setUsername(String newUsername) {
		this.username = newUsername;
	}

	public void setPassword(String newPassword) {
		this.password = newPassword;
	}

	public void setType(String newType) {
		this.type = newType;
	}

	public void setBackgroundColor(int colorRGB) {
		if (colorRGB == 0) {
			this.backgroundcolor = -1118482;
		} else {
			this.backgroundcolor = colorRGB;
		}
	}

	public void setForegroundColor(int colorRGB) {
		if (colorRGB == 0) {
			this.foregroundcolor = -16777216;
		} else {
			this.foregroundcolor = colorRGB;
		}
	}

	public void setFont(String name, int style, int size) {
		if (name == null) {
			name = "Dialog";
		}
		if (size == 0) {
			size = 12;
		}
		this.font = new Font(name, style, size);
	}

	public void setFirstname(String name) {
		this.firstname = name;
	}

	public void setSurname(String name) {
		this.surname = name;
	}

	public void setdateofBirth(String dof) {
		this.dateOfBirth = dof;
	}

	public void setPhonenumber(String nr) {
		this.phonenumber = nr;
	}

	// get values

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getType() {
		return type;
	}

	public Color getBackgroundColor() {
		return new Color(backgroundcolor);
	}

	public Color getForegroundColor() {
		return new Color(foregroundcolor);
	}

	public Font getFont() {
		return font;
	}

	public String getFamily() {
		return font.getFamily();
	}

	public int getStyle() {
		return font.getStyle();
	}

	public int getSize() {
		return font.getSize();
	}

	public String getFirstname() {
		return firstname;
	}

	public String getSurname() {
		return surname;
	}

	public String getdateofBirth() {
		return dateOfBirth;
	}

	public String getPhonenumber() {
		return phonenumber;
	}
}
