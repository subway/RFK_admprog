package interfaces;

import javax.swing.*;
import model.*;

/**
 * @author Sabba
 *
 */
public interface I_UserPage {

		/**
		 * resizes each column-width in relation to it's content
		 * 
		 * @param table - to resize
		 * @param type - table-type (member-log, activity-result, etc)
		 */
		public void autoResizeColWidth(JTable table, String type);

		/**
		 * creates a panel with the 5 latest activity log-entries
		 */
		public void createActivityLogPanel();

		/**
		 * creates a panel with activity results from a search
		 * 
		 * @param table - containing result
		 */
		public void createActivityResultPanel(JTable table);

		/**
		 * creates the panel with the component's and listener's you need to
		 * show the sidepanel used when an active/valid activity is shown
		 */
		public void createActivitySidePanel();

		/**
		 * creates the panel with the component's and listener's you need to
		 * show activity-statistics and interact with user
		 * 
		 * @param table - containing result from user-input (year)
		 */
		public void createActivityStatisticsPanel(JTable[] table);

		/**
		 * creates the panel with the component's and listener's you need to
		 * create and add a new activity to the DB
		 */
		public void createAddActivityPanel();

		/**
		 * creates the panel with the component's and listener's you need to
		 * edit activity a
		 * @param a - the activity you want to edit
		 */
		public void createEditActivityPanel(Activity a);

		/**
		 * creates the panel with the component's and listener's you need to
		 * edit current member
		 */
		public void createEditMember();

		/**
		 * creates the panel with the component's and listener's you need to
		 * show main activity page
		 */
		public void createMainActivityPanel();

		/**
		 * creates the panel with the component's and listener's you need to
		 * show the sidepanel used when main/add/search activity page is visible
		 */
		public void createMainActivitySidePanel();

		/**
		 * creates the panel with the component's and listener's you need to
		 * show main member page
		 */
		public void createMainMemberPanel();

		/**
		 * creates the panel with the component's and listener's you need to
		 * show the sidepanel used when main/add/search member page is visible
		 */
		public void createMainMemberSidePanel();

		/**
		 * creates the panel with the component's and listener's you need to
		 * show main page
		 */
		public void createMainPanel();

		/**
		 * creates the panel with the component's and listener's you need to
		 * show the sidepanel used when main page is visible
		 */
		public void createMainSidePanel();

		/**
		 * creates the panel with the component's and listener's you need to
		 * show main statistics page
		 */
		public void createMainStatisticsPanel();

		/**
		 * creates the panel with the component's and listener's you need to
		 * show the sidepanel used when statistics related pages are visible
		 */
		public void createMainStatisticsSidePanel();
		
		/**
		 * creates a panel with the 5 latest member log-entries
		 */
		public void createMemberLogPanel();

		/**
		 * creates a panel with member results from a search
		 * 
		 * @param table - containing result
		 */
		public void createMemberResultPanel(JTable table);

		/**
		 * creates the panel with the component's and listener's you need to
		 * show the sidepanel used when an active/non-terminated member is shown
		 */
		public void createMemberSidePanel();

		/**
		 * creates the panel with the component's and listener's you need to
		 * show member-statistics and interact with user
		 * 
		 * @param table - containing result from user-input (year)
		 */
		public void createMemberStatisticsPanel(JTable[] table);

		/**
		 * creates the menu on the titlebar
		 */
		public void createMenu();

		/**
		 * creates the panel with the component's and listener's you need to
		 * create and add a new member to the DB
		 */
		public void createNewMemberPanel();

		/**
		 * creates the panel with the component's and listener's you need to
		 * show attendance page and interact with user
		 */
		public void createRegAttendancePanel();

		/**
		 * creates the panel with the component's and listener's you need to
		 * show activity-search page and enable user input
		 */
		public void createSearchActivityPanel();

		/**
		 * creates the panel with the component's and listener's you need to
		 * show member-search page and enable user input
		 */
		public void createSearchMemberPanel();

		/**
		 * creates the panel with the component's and listener's you need to
		 * show a detailed view of activity 
		 * 
		 * @param a - the activity to be shown
		 */
		public void createShowActivityPanel(Activity a);

		/**
		 * creates the panel with the component's and listener's you need to
		 * show a detailed view of member
		 * 
		 * @param m - the member to be shown
		 */
		public void createShowMember(Member m);

		/**
		 * set's all panels to visible = false
		 */
		public void hideAllPanels();

}
