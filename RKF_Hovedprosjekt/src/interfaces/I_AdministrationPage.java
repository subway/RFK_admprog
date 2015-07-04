package interfaces;

import javax.swing.*;
import model.*;

/**
 * @author Sabba
 * 
 */
public interface I_AdministrationPage {

	/**
	 * resizes each column-width in relation to it's content
	 * 
	 * @param table
	 */
	public void autoResizeColWidth(JTable table);

	/**
	 * clears the registration form used for new user
	 */
	public void clearRegistrationForm();

	/**
	 * creates the panel with the component's and listener's you need to delete
	 * a user
	 */
	public void createDeleteUserPanel();

	/**
	 * creates the panel with the component's and listener's you need to show
	 * the sidepanel used when delete user related panel's are visible
	 */
	public void createDeleteUserSidePanel();

	/**
	 * creates the panel with the component's and listener's you need to show
	 * the mainpage for administrator
	 */
	public void createMainPanel();

	/**
	 * creates the panel with the component's and listener's you need to show
	 * the sidepanel used when mainpage is visible
	 */
	public void createMainSidePanel();

	/**
	 * creates the menu on the titlebar
	 */
	public void createMenu();

	/**
	 * creates the panel with the component's and listener's you need to add a
	 * new user
	 */
	public void createNewUserPanel();

	/**
	 * creates the panel with the component's and listener's you need to show
	 * the side-panel used when newUser-panel is visible
	 */
	public void createNewUserSidePanel();

	/**
	 * creates the panel with the component's and listener's you need to enable
	 * editing of the chosen user
	 * 
	 * @param user
	 *            - the one to update
	 */
	public void createOneUpdatePanel(User user);

	/**
	 * creates the panel with the component's and listener's you need to display
	 * search-results
	 * 
	 * @param table
	 *            - contains search-results
	 */
	public void createResultPanel(JTable table);

	/**
	 * creates the panel with the component's and listener's you need to enable
	 * the search-function
	 */
	public void createSearchPanel();

	/**
	 * creates the panel with the component's and listener's you need to
	 * quick-search for a user you want to update
	 */
	public void createUpdateUserPanel();

	/**
	 * creates the panel with the component's and listener's you need to show
	 * the side-panel used when user-updating related panels are visible
	 */
	public void createUpdateUserSidePanel();

	/**
	 * set's all panels to visible = false
	 */
	public void hideAllPanels();
	
}