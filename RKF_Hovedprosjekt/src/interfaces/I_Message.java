package interfaces;

/**
 * @author Sabba
 *
 */
public interface I_Message {
	
	/**
	 * Used to change password
	 * @param old - title for old input
	 * @return return an array of input
	 */
	public String[] changePasswordDialog(String old);
	
	/**
	 * Display a confirm-JOptionPane
	 * @param msg - the question asked
	 * @return - value of the chosen button
	 */
	public int confirmDialog(String msg);

	/**
	 * @param title - JOptionPane frame-title
	 * @param inputname - explaining what kind 
	 * of input we expect
	 * @return userinput
	 */
	public String inputDialog(String title, String inputname);

	/**
	 * Display a message-popup JOptionPane
	 * @param msg - the message to display
	 * @param icon - messagetype 
	 * (ERROR_MESSAGE, PLAIN_MESSAGE, WARNING_MESSAGE, INFORMATION_MESSAGE)
	 */
	public void messageDialog(String msg, int icon);

	/**
	 * Used for verfication
	 * @return - userinput (i.e. password)
	 */
	public String singlePasswordDialog();
}
