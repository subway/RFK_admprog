package interfaces;

import java.awt.*;
import javax.swing.*;
import model.*;

/**
 * @author Sabba
 * 
 */
public interface I_Control {

	/**
	 * adding a new activity into the DB. remember to add event to activity-log
	 * as well.
	 * 
	 * @param name
	 *            - name of the activity
	 * @param manager
	 *            - activity's manager
	 * @param adress
	 *            - where the activity will be held
	 * @param zipcode
	 *            - where the activity will be held
	 * @param city
	 *            - where the activity will be held
	 * @param seats
	 *            - the number of member's who can participate
	 * @param durationStart
	 *            - start date
	 * @param durationEnd
	 *            - end date
	 * @param allTF
	 *            - time from on all days
	 * @param allTT
	 *            - time to on all days
	 * @param mondayTF
	 *            - time from on Monday
	 * @param mondayTT
	 *            - time to on Monday
	 * @param tuesdayTF
	 *            - time from on Tuesday
	 * @param tuesdayTT
	 *            - time to on Tuesday
	 * @param wednesdayTF
	 *            - time from on Wednesday
	 * @param wednesdayTT
	 *            - time to on Wednesday
	 * @param thursdayTF
	 *            - time from on Thursday
	 * @param thursdayTT
	 *            - time to on Thursday
	 * @param fridayTF
	 *            - time from on Friday
	 * @param fridayTT
	 *            - time to on Friday
	 * @param saturdayTF
	 *            - time from on Saturday
	 * @param saturdayTT
	 *            - time to on Saturday
	 * @param sundayTF
	 *            - time from on Sunday
	 * @param sundayTT
	 *            - time to on Sunday
	 * @param description
	 *            - some keywords regarding the activity
	 * @param document
	 *            - a link to activity related document
	 * @return true if adding is completed, false otherwise
	 */
	public boolean addActivity(String name, String manager, String adress,
			String zipcode, String city, String seats, String durationStart,
			String durationEnd, String allTF, String allTT, String mondayTF,
			String mondayTT, String tuesdayTF, String tuesdayTT,
			String wednesdayTF, String wednesdayTT, String thursdayTF,
			String thursdayTT, String fridayTF, String fridayTT,
			String saturdayTF, String saturdayTT, String sundayTF,
			String sundayTT, String description, String document);

	/**
	 * add an attendance status to an active activity. remember to add event to
	 * activity-log as well.
	 * 
	 * @param id
	 *            - activityid in the DB
	 * @param duration
	 *            - of activity
	 * @param date
	 *            - of attendance-entry
	 * @param seats
	 *            - number of members
	 * @param comment
	 *            - optional comment for that day
	 * @return true if adding is completed, false otherwise
	 */
	public boolean addActivityAttendance(String id, String duration,
			String date, String seats, String comment);

	/**
	 * add an event regarding activity to an activity-log
	 * 
	 * @param id
	 *            - activityid in the database
	 * @param event
	 *            - to be stored (i.e adding/terminating/editing etc)
	 */
	public void addActivityLog(String id, String event);

	/**
	 * add an event regarding member to a member-log
	 * 
	 * @param id
	 *            - memberid in the database
	 * @param event
	 *            - to be stored (i.e adding/terminating/editing etc)
	 */
	public void addMemberLog(String id, String event);

	/**
	 * register a member to an activity remember to add event to activity- and
	 * member-log as well.
	 * 
	 * @param member
	 *            - to register
	 * @param activity
	 *            - registered on
	 * @return true if adding is completed, false otherwise
	 */
	public boolean addRegistration(String member, String activity);

	/**
	 * create a new user in DB
	 * 
	 * @param usernameInput
	 * @param passwordInput
	 * @param password2Input
	 * @param typeInput
	 * @param firstnameInput
	 * @param surnameInput
	 * @param dateOfBirthInput
	 * @param nrInput
	 * @return true if adding is completed, false otherwise
	 */
	public boolean addUser(JTextField usernameInput,
			JPasswordField passwordInput, JPasswordField password2Input,
			JComboBox typeInput, JTextField firstnameInput,
			JTextField surnameInput, JTextField dateOfBirthInput,
			JTextField nrInput);

	/**
	 * change password of an user-account
	 * 
	 * @param u
	 *            - the user who's password will be changed
	 * @param oldText - label before the old password-inputfield
	 */
	public void changePassword(User u, String oldText);

	/**
	 * close connection, statement and resultSet
	 */
	public void closeDatabase();

	/**
	 * delete an user from DB
	 * 
	 * @param username
	 *            - of the user to be deleted from the DB
	 * @return true if deleting was succesful, false otherwise
	 */
	public boolean deleteUser(String username);

	/**
	 * validate input for '--' used in sql-injections
	 * 
	 * @param input
	 * @return true if -- exists, false otherwise
	 */
	public boolean doubleLines(String input);

	/**
	 * edit details on an activity remember to add event to activity-log as well
	 * 
	 * @param id
	 *            - activityid
	 * @param name
	 *            - name of the activity
	 * @param manager
	 *            - activity's manager
	 * @param adress
	 *            - where the activity will be held
	 * @param zipcode
	 *            - where the activity will be held
	 * @param city
	 *            - where the activity will be held
	 * @param seats
	 *            - the number of member's who can participate
	 * @param durationStart
	 *            - start date
	 * @param durationEnd
	 *            - end date
	 * @param allTF
	 *            - time from on all days
	 * @param allTT
	 *            - time to on all days
	 * @param mondayTF
	 *            - time from on Monday
	 * @param mondayTT
	 *            - time to on Monday
	 * @param tuesdayTF
	 *            - time from on Tuesday
	 * @param tuesdayTT
	 *            - time to on Tuesday
	 * @param wednesdayTF
	 *            - time from on Wednesday
	 * @param wednesdayTT
	 *            - time to on Wednesday
	 * @param thursdayTF
	 *            - time from on Thursday
	 * @param thursdayTT
	 *            - time to on Thursday
	 * @param fridayTF
	 *            - time from on Friday
	 * @param fridayTT
	 *            - time to on Friday
	 * @param saturdayTF
	 *            - time from on Saturday
	 * @param saturdayTT
	 *            - time to on Saturday
	 * @param sundayTF
	 *            - time from on Sunday
	 * @param sundayTT
	 *            - time to on Sunday
	 * @param description
	 *            - some keywords regarding the activity
	 * @param path
	 *            - to activity related document
	 * @param oldname
	 *            - current name of activity
	 * @param oldmanager
	 *            - current manager
	 * @param oldadr
	 *            - current address
	 * @param oldzipcode
	 *            - current zipcode
	 * @param oldcity
	 *            - current city
	 * @param oldseats
	 *            - current capacity
	 * @param olddurationStart
	 *            - current start date
	 * @param olddurationEnd
	 *            - current end date
	 * @param olddesc
	 *            - current description
	 * @param oldpath
	 *            - current document-path
	 * @return true if activity is edited, false otherwise
	 */
	public Activity editActivity(String id, String name, String manager,
			String adr, String zipcode, String city, String seats,
			String durationStart, String durationEnd, String allTF,
			String allTT, String mondayTF, String mondayTT, String tuesdayTF,
			String tuesdayTT, String wednesdayTF, String wednesdayTT,
			String thursdayTF, String thursdayTT, String fridayTF,
			String fridayTT, String saturdayTF, String saturdayTT,
			String sundayTF, String sundayTT, String description, String path,
			String oldname, String oldmanager, String oldadr,
			String oldzipcode, String oldcity, String oldseats,
			String olddurationStart, String olddurationEnd, String olddesc,
			String oldpath);

	/**
	 * generates a MD5-hash of input-password
	 * 
	 * @param password
	 *            - input
	 * @return MD5-hash of input
	 */
	public String generatePassword(String password);

	/**
	 * get activity-object
	 * 
	 * @param activityID
	 * @return activity-object or null if not possible
	 */
	public Activity getActivity(String activityID);

	/**
	 * gets predefined statistics regarding activity from input-year
	 * 
	 * @param year
	 * @param fontmetrics
	 * @return a list of JTable-results
	 */
	public JTable[] getActivityStatistic(String year, FontMetrics fontmetrics);

	/**
	 * gets current date in output-format (31-12-2000)
	 * 
	 * @return a String-representation of current date
	 */
	public String getCurrentDate();

	/**
	 * gets current datetime in output-format (31-12-2000 23:59:00)
	 * 
	 * @return a String-representation of current datetime
	 */
	public String getCurrentDateTime();

	/**
	 * gets a list of members registered for current activity
	 * 
	 * @param activityID
	 *            - id of current activity
	 * @return a list of memberid and names, or null if no one is registered
	 */
	public String[] getInterestList(String activityID);

	/**
	 * gets a list of all activity current member has registered for
	 * 
	 * @param memberID
	 *            id of current member
	 * @return a list of activityid and names, or null if the member isn't
	 *         registered for anything
	 */
	public String[] getM2Alist(String memberID);

	/**
	 * gets the 5 latest activity log-inputs
	 * 
	 * @return a JTable containing the 5 latest activity log-inputs
	 */
	public JTable getMainActivityLog();

	/**
	 * gets the 5 latest member log-inputs
	 * 
	 * @return a JTable containing the 5 latest member log-inputs
	 */
	public JTable getMainMemberLog();

	/**
	 * gets predefined system-statistics
	 * 
	 * @return system uptime, registered user details, new entries today, most
	 *         active user last week/month
	 */
	public String[] getMainStatistic();

	/**
	 * gets a member-object defined by the input id
	 * 
	 * @param memberID
	 *            - id of the member to get
	 * @return member-object if found, null otherwise
	 */
	public Member getMember(int memberID);

	/**
	 * gets all active members who can be registered up to an activity
	 * 
	 * @return a list of all active members
	 */
	public String[] getMemberList();

	/**
	 * gets all predefined member-statistics from input-year
	 * 
	 * @param year
	 * @param fontmetrics
	 * @return a list of JTables containing statistics
	 */
	public JTable[] getMemberStatistic(String year, FontMetrics fontmetrics);

	/**
	 * converts a sqlDateTime (2000-12-31 23:59:00) to output-format (31-12-2000
	 * 23:59:00)
	 * 
	 * @param sqlDateTime
	 * @return a string representation of date time
	 */
	public String getOutputDateTime(String sqlDateTime);

	/**
	 * converts a inputDate (31-12-2000) to sql-format (2000-12-31)
	 * 
	 * @param inputDate
	 * @return sql-format of input date
	 */
	public String getSqlDateTime(String inputDate);

	/**
	 * used to quick-search for a user
	 * 
	 * @param value
	 *            - input
	 * @param field
	 *            - i.e username, phonenumber, date of birth etc
	 * @return uservalues or null if no match in DB
	 */
	public String[][] getUpdatePossibilities(String value, String field);

	/**
	 * get user-object
	 * 
	 * @param name
	 *            - username of the user
	 * @return user-object or null if not found
	 */
	public User getUser(String name);

	/**
	 * gets a list of all user's in DB
	 * 
	 * @return A list of user-objects
	 */
	public User[] getUsernameList();

	/**
	 * initialize DB. start mysqld.exe if not running
	 */
	public void initializeDatabase();

	/**
	 * check if mysqld.exe is running
	 * 
	 * @param process
	 *            - mysqld.exe
	 * @return true if running, false otherwise
	 */
	public boolean isRunning(String process);

	/**
	 * check if password input is valid meaning at least 1 uppercase letter, 1
	 * lowercase letter, 1 number
	 * 
	 * @param password
	 * @return true if valid password, false otherwise
	 */
	public boolean legalPassword(String password);

	/**
	 * used to logoff a user from the program without terminating it
	 */
	public void logOff();

	/**
	 * adds a new member to the DB. remember to add event to member log
	 * 
	 * @param firstname
	 * @param surname
	 * @param addresse
	 * @param zipcode
	 * @param city
	 * @param date
	 * @param gender
	 * @param type
	 * @param email
	 * @param mobilNr
	 * @param privateNr
	 * @param workNr
	 * @param url
	 *            - of a picture if uploaded
	 * @return true if added, false otherwise
	 */
	public boolean newMember(String firstname, String surname, String addresse,
			String zipcode, String city, String date, String gender,
			String type, String email, String mobilNr, String privateNr,
			String workNr, String url);

	/**
	 * converts sql-date (2000-12-31) to output-format (31-12-2000)
	 * 
	 * @param sqlDate
	 * @return string representation of output-date
	 */
	public String outputDateFormat(String sqlDate);

	/**
	 * register a member-contingent remember to add event to member-log
	 * 
	 * @param id
	 *            - memberid
	 * @param date
	 *            - current date
	 * @return true if registered, false otherwise
	 */
	public boolean regContingent(String id, String date);

	/**
	 * remove everything but the numbers from input-date
	 * 
	 * @param date
	 *            - input
	 * @return return a string representation of the input with just the numbers
	 */
	public String removeDateLine(String date);

	/**
	 * removes a member from an the registration-list of an activity remember to
	 * add event to activity- and member-log
	 * 
	 * @param member
	 *            - the member to be removed
	 * @param activity
	 *            - the activity to remove from
	 * @return true if registration is removed, false otherwise
	 */
	public boolean removeRegistration(String member, String activity);

	/**
	 * search for an activity. if all fields are empty, make a SELECT * FROM
	 * ACTIVITY statement
	 * 
	 * @param name
	 * @param manager
	 * @param adr
	 * @param zipcode
	 * @param city
	 * @param seats
	 * @param status
	 * @param durationStart
	 * @param durationEnd
	 * @param doc
	 * @param all
	 * @param monday
	 * @param tuesday
	 * @param wednesday
	 * @param thursday
	 * @param friday
	 * @param saturday
	 * @param sunday
	 * @param fontmetrics
	 * @return a JTable with search result or null if search have no result
	 */
	public JTable searchActivity(String name, String manager, String adr,
			String zipcode, String city, String seats, String status,
			String durationStart, String durationEnd, String doc,
			JCheckBox all, JCheckBox monday, JCheckBox tuesday,
			JCheckBox wednesday, JCheckBox thursday, JCheckBox friday,
			JCheckBox saturday, JCheckBox sunday, FontMetrics fontmetrics);

	/**
	 * search for a member. if all fields are empty, make a SELECT * FROM 
	 * MEMBER statement
	 * 
	 * @param id
	 * @param firstname
	 * @param surname
	 * @param address
	 * @param zipcode
	 * @param city
	 * @param date
	 * @param gender
	 * @param type
	 * @param email
	 * @param nr
	 * @param status
	 * @return a JTable with search result or null if search have no result
	 */
	public JTable searchMember(String id, String firstname, String surname,
			String address, String zipcode, String city, String date,
			String gender, String type, String email, String nr, String status);

	/**
	 * quick-search user. makes a call on the other searchUser-method when 
	 * validation is finished in this one
	 * 
	 * @param input 
	 * @param index - JComboBox, selected index
	 * @return a JTable with search result or null if search have no result
	 */
	public JTable searchUser(JTextField input, int index);

	/**
	 * user-search method
	 * 
	 * @param searchUsernameInput
	 * @param searchTypeInput
	 * @param searchFirstnameInput
	 * @param searchSurnameInput
	 * @param searchDateOfBirthInput
	 * @param searchNrInput
	 * @return a JTable with search result or null if search have no result
	 */
	public JTable searchUser(JTextField searchUsernameInput,
			JComboBox searchTypeInput, JTextField searchFirstnameInput,
			JTextField searchSurnameInput, JTextField searchDateOfBirthInput,
			JTextField searchNrInput);

	/**
	 * open the settings-window
	 * 
	 * @param username - of the online-user
	 */
	public void settings(String username);

	/**
	 * handles everything that must be done before the system can shutdown
	 */
	public void shutdown();

	/**
	 * validate input for special character that are not allowed (",' etc)
	 * 
	 * @param input - to validate
	 * @param type - name, address, etc
	 * @return true if input contains special character, false otherwise
	 */
	public boolean specialCharacters(String input, String type);

	/**
	 * converts inputDate (31-12-2000) to sql-format (2000-12-31)
	 * 
	 * @param inputDate
	 * @return string representation of sql-date
	 */
	public String sqlDateFormat(String inputDate);

	/**
	 * terminate/end current activity on current date
	 * 
	 * @param id - current activityid
	 * @param comment
	 * @return (if possible) an updated version of current activity, null otherwise
	 */
	public Activity terminateActivity(String id, String comment);

	/**
	 * terminate a membership
	 * 
	 * @param id - memberid for the membership to be terminated
	 * @return updated member if terminated, null otherwise
	 */
	public Member terminateMembership(String id);

	/**
	 * update a member
	 * 
	 * @param id
	 * @param firstname
	 * @param surname
	 * @param addresse
	 * @param zipcode
	 * @param city
	 * @param date
	 * @param gender
	 * @param type
	 * @param email
	 * @param mobilNr
	 * @param privateNr
	 * @param workNr
	 * @param oldfirstname
	 * @param oldsurname
	 * @param oldaddresse
	 * @param oldzipcode
	 * @param oldcity
	 * @param olddate
	 * @param oldgender
	 * @param oldtype
	 * @param oldemail
	 * @param oldmobilNr
	 * @param oldprivateNr
	 * @param oldworkNr
	 * @param oldURL
	 * @param url
	 * @return updated member if updated, null otherwise
	 */
	public Member updateMember(int id, String firstname, String surname,
			String addresse, String zipcode, String city, String date,
			String gender, String type, String email, String mobilNr,
			String privateNr, String workNr, String oldfirstname,
			String oldsurname, String oldaddresse, String oldzipcode,
			String oldcity, String olddate, String oldgender, String oldtype,
			String oldemail, String oldmobilNr, String oldprivateNr,
			String oldworkNr, String oldURL, String url);

	/**
	 * update a user
	 * 
	 * @param u - user to update
	 * @return true if updated, false otherwise
	 */
	public boolean updateUser(User u);

	/**
	 * validate input for a new activity
	 * 
	 * @param name
	 * @param manager
	 * @param adress
	 * @param zipcode
	 * @param city
	 * @param seats
	 * @param durationStart
	 * @param durationEnd
	 * @param allTF
	 * @param allTT
	 * @param mondayTF
	 * @param mondayTT
	 * @param tuesdayTF
	 * @param tuesdayTT
	 * @param wednesdayTF
	 * @param wednesdayTT
	 * @param thursdayTF
	 * @param thursdayTT
	 * @param fridayTF
	 * @param fridayTT
	 * @param saturdayTF
	 * @param saturdayTT
	 * @param sundayTF
	 * @param sundayTT
	 * @return true if valid, false otherwise
	 */
	public boolean validateAddActivity(String name, String manager,
			String adress, String zipcode, String city, String seats,
			String durationStart, String durationEnd, String allTF,
			String allTT, String mondayTF, String mondayTT, String tuesdayTF,
			String tuesdayTT, String wednesdayTF, String wednesdayTT,
			String thursdayTF, String thursdayTT, String fridayTF,
			String fridayTT, String saturdayTF, String saturdayTT,
			String sundayTF, String sundayTT);

	/**
	 * validate input for attendance-object
	 * 
	 * @param duration
	 * @param date
	 * @param seats
	 * @param comment
	 * @return true if valid, false otherwise
	 */
	public boolean validateAddActivityAttendance(String duration, String date,
			String seats, String comment);

	/**
	 * validate input for activity-editing
	 * 
	 * @param name
	 * @param manager
	 * @param adr
	 * @param zipcode
	 * @param city
	 * @param seats
	 * @param durationStart
	 * @param durationEnd
	 * @param allTF
	 * @param allTT
	 * @param mondayTF
	 * @param mondayTT
	 * @param tuesdayTF
	 * @param tuesdayTT
	 * @param wednesdayTF
	 * @param wednesdayTT
	 * @param thursdayTF
	 * @param thursdayTT
	 * @param fridayTF
	 * @param fridayTT
	 * @param saturdayTF
	 * @param saturdayTT
	 * @param sundayTF
	 * @param sundayTT
	 * @param description
	 * @return true if valid, false otherwise
	 */
	public boolean validateEditActivity(String name, String manager,
			String adr, String zipcode, String city, String seats,
			String durationStart, String durationEnd, String allTF,
			String allTT, String mondayTF, String mondayTT, String tuesdayTF,
			String tuesdayTT, String wednesdayTF, String wednesdayTT,
			String thursdayTF, String thursdayTT, String fridayTF,
			String fridayTT, String saturdayTF, String saturdayTT,
			String sundayTF, String sundayTT, String description);

	/**
	 * validate input for log in
	 * 
	 * @param username
	 * @param password
	 * @return user-object if found, null otherwise
	 */
	public User validateLogIn(JTextField username, JPasswordField password);

	/**
	 * validate input for new member
	 * 
	 * @param firstname
	 * @param surname
	 * @param addresse
	 * @param zipcode
	 * @param city
	 * @param date
	 * @param gender
	 * @param type
	 * @param email
	 * @param mobilNr
	 * @param privateNr
	 * @param workNr
	 * @return true if valid, false otherwise
	 */
	public boolean validateNewMember(String firstname, String surname,
			String addresse, String zipcode, String city, String date,
			String gender, String type, String email, String mobilNr,
			String privateNr, String workNr);

	/**
	 * validate input for new user
	 * 
	 * @param username
	 * @param password
	 * @param password2
	 * @param type
	 * @param firstname
	 * @param surname
	 * @param date
	 * @param tlf
	 * @return true if valid, false otherwise
	 */
	public boolean validateNewUser(String username, String password,
			String password2, String type, String firstname, String surname,
			String date, String tlf);

	/**
	 * validate input for activity-search
	 * 
	 * @param name
	 * @param manager
	 * @param adr
	 * @param zipcode
	 * @param city
	 * @param seats
	 * @param durationStart
	 * @param durationEnd
	 * @param doc
	 * @return true if valid, false otherwise
	 */
	public boolean validateSearchActivity(String name, String manager,
			String adr, String zipcode, String city, String seats,
			String durationStart, String durationEnd, String doc);

	/**
	 * validate input for member-search
	 * 
	 * @param id
	 * @param firstname
	 * @param surname
	 * @param address
	 * @param zipcode
	 * @param city
	 * @param date
	 * @param email
	 * @param nr
	 * @return true if valid, false otherwise
	 */
	public boolean validateSearchMember(String id, String firstname,
			String surname, String address, String zipcode, String city,
			String date, String email, String nr);

	/**
	 * validate input for user-search
	 * 
	 * @param username
	 * @param firstname
	 * @param surname
	 * @param date
	 * @param tlf
	 * @return true if valid, false otherwise
	 */
	public boolean validateSearchUser(String username, String firstname,
			String surname, String date, String tlf);

	/**
	 * validate input for member-update
	 * 
	 * @param firstname
	 * @param surname
	 * @param addresse
	 * @param zipcode
	 * @param city
	 * @param date
	 * @param gender
	 * @param type
	 * @param email
	 * @param mobilNr
	 * @param privateNr
	 * @param workNr
	 * @return true if valid, false otherwise
	 */
	public boolean validateUpdateMember(String firstname, String surname,
			String addresse, String zipcode, String city, String date,
			String gender, String type, String email, String mobilNr,
			String privateNr, String workNr);

	/**
	 * validate input for user-update
	 * 
	 * @param type
	 * @param firstname
	 * @param surname
	 * @param date
	 * @param tlf
	 * @return true if valid, false otherwise
	 */
	public boolean validateUpdateUser(String type,
			String firstname, String surname, String date, String tlf);

	/**
	 * validate comment-input
	 * 
	 * @param comment
	 * @return true if valid, false otherwise
	 */
	public boolean validComment(String comment);

	/**
	 * validate date-input
	 * 
	 * @param input
	 * @return true if valid, false otherwise
	 */
	public int validDate(String input);

	/**
	 * validate email-input
	 * 
	 * @param email
	 * @return true if valid, false otherwise
	 */
	public boolean validEmail(String email);

	/**
	 * validate image-url
	 * 
	 * @param input
	 * @return true if valid, false otherwise
	 */
	public boolean validImageURL(String input);

	/**
	 * validate name-input
	 * 
	 * @param input
	 * @return true if valid, false otherwise
	 */
	public int validName(String input);

	/**
	 * validate nr input
	 * 
	 * @param input
	 * @return true if valid, false otherwise
	 */
	public boolean validNr(String input);

	/**
	 * validate time-input
	 * 
	 * @param time
	 * @return true if valid, false otherwise
	 */
	public boolean validTime(String time);

}
