package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import model.*;
import control.Control;

/**
 * @author Sabba This class contains everything needed for the user to interact
 *         with the database using a graphical user interface
 * 
 */
public class UserPage extends JFrame implements ActionListener, KeyListener,
		interfaces.I_UserPage {

	private static final long serialVersionUID = 1L;

	private final Control c = new Control();
	private final Message msg;
	private final Dimension dim;
	private final Insets insets, frameinsets;
	private final FontMetrics fontmetrics;

	private JMenuBar menubar;
	private JMenu fileMenu, helpMenu, closeMenu, newMenu, toolsMenu;
	private JMenuItem shutdownMenuItem, logOffMenuItem, aboutMenuItem,
			helpMenuItem, settingsMenuItem, newMemberMenuItem,
			newActivityMenuItem, passwordMenuItem;
	private JPanel panel;
	private int logWidth;
	private int DEFAULT_HEIGTH, DEFAULT_PANEL_X, DEFAULT_PANEL_Y,
			DEFAULT_PANEL_W, DEFAULT_PANEL_H;

	// main sidepanel components
	private JPanel mainSidePanel;
	private JButton mainMember, mainActivity, mainStatistics;

	// mainPanel components
	private JPanel mainPanel;
	private JLabel titleLabel, dateLabel;

	// memberLogPanel components
	private JPanel memberLogPanel;
	private JLabel memberLogTitleLabel;
	private JTable memberLogMainTable;
	private JScrollPane memberLogScroll;
	private int memberLogMainTableWidth;

	// activityLogPanel components
	private JPanel activityLogPanel;
	private JLabel activityLogTitleLabel;
	private JTable activityLogMainTable;
	private JScrollPane activityLogScroll;
	private int activityLogMainTableWidth;

	// showMemberPanel components
	private JPanel showMemberPanel;
	private JLabel memberIDLabel, statusLabel, typeLabel, firstnameLabel,
			surnameLabel, addressLabel, zipcodeLabel, cityLabel,
			dateOfBirthLabel, genderLabel, mobileNrLabel, privateNrLabel,
			workNrLabel, emailLabel, enrollmentDateLabel, dateStatusLabel,
			memberIDValue, statusValue, typeValue, firstnameValue,
			surnameValue, addressValue, zipcodeValue, cityValue,
			dateOfBirthValue, genderValue, mobileNrValue, privateNrValue,
			workNrValue, emailValue, enrollmentDateValue, dateStatusValue;
	private JTable showMemberLogTable;
	private JButton allLogEntries;
	private Picture showMemberImage; // custom Imageclass
	private int DEFAULT_IMAGE_WIDTH = 130;
	private int DEFAULT_IMAGE_HEIGTH = 170;
	private int showMemberLogW;
	private int position;
	private String imageURL;
	private boolean all = false; // show all log-entries or not

	// mainMemberSidePanel Component's
	private JPanel mainMemberSidePanel;
	private JButton mainPagefromMainMemberSidePanel,
			mainMemberPagefromMainMemberSidePanel, addMember, searchMember;

	// memberSidepanel
	private JPanel memberSidePanel;
	private JButton mainPageFromMemberSidePanel,
			mainMemberpageFromMemberSidePanel, regMemberContingent, printCard,
			editMember, terminateMembership, m2aStatus;
	private JDialog m2aDialog = null;

	// membermainPanel
	private JPanel mainMemberPanel;

	// searchMemberPanel
	private JPanel searchMemberPanel;
	private JLabel searchMemberTitle, searchIdLabel, searchFirstnameLabel,
			searchSurnameLabel, searchAddressLabel, searchZipcodeLabel,
			searchCityLabel, searchDateOfBirthLabel, searchGenderLabel,
			searchEmailLabel, searchMembershipTypeLabel, searchNrLabel,
			searchStatusLabel;
	private JButton OKsearchMember, cancelsearchMember;
	private JTextField searchIdInput, searchFirstnameInput, searchSurnameInput,
			searchAddressInput, searchZipcodeInput, searchCityInput,
			searchDateOfBirthInput, searchEmailInput, searchNrInput;
	private JComboBox searchGenderInput, searchMembershipTypeInput,
			searchStatusInput;

	// editMemberPanel
	private JPanel editMemberPanel;
	private JLabel editTitle, editImageRemove, editFirstnameLabel,
			editSurnameLabel, editAddressLabel, editZipcodeLabel,
			editCityLabel, editDateOfBirthLabel, editGenderLabel,
			editEmailLabel, editMembershipTypeLabel, editMobileLabel,
			editPrivateLabel, editWorkLabel;
	private JButton OKeditMember, cancelEditMember, editMemberUploadImage;
	private JTextField editFirstnameInput, editSurnameInput, editAddressInput,
			editZipcodeInput, editCityInput, editDateOfBirthInput,
			editEmailInput, editMobileInput, editPrivateInput, editWorkInput;
	private JComboBox editGenderInput, editMembershipTypeInput;
	private Picture editMemberImage;
	private String editImageURL;

	// newMemberPanel
	private JPanel newMemberPanel;
	private JLabel newTitle, newImageRemove, newFirstnameLabel,
			newSurnameLabel, newAddressLabel, newZipcodeLabel, newCityLabel,
			newDateOfBirthLabel, newGenderLabel, newEmailLabel,
			newMembershipTypeLabel, newMobileLabel, newPrivateLabel,
			newWorkLabel;
	private JButton OKnewMember, cancelnewMember, newMemberUploadImage;
	private JTextField newFirstnameInput, newSurnameInput, newAddressInput,
			newZipcodeInput, newCityInput, newDateOfBirthInput, newEmailInput,
			newMobileInput, newPrivateInput, newWorkInput;
	private JComboBox newGenderInput, newMembershipTypeInput;
	private Picture newMemberImage;
	private String newImageURL;

	// memberResultPanel
	private JPanel memberResultPanel;
	private JLabel memberResultTitleLabel;
	private JScrollPane memberResultScroll;
	private JTable memberResultTable;
	private int memberResultTableWidth;

	// mainActivityPanel component's
	private JPanel mainActivityPanel;

	// mainActivitySidePanel component's
	private JPanel mainActivitySidePanel;
	private JButton mainPageFromMainActivity,
			mainActivityPageFromMainActivitySidePanel, addActivity,
			searchActivity;

	// addActivityPanel component's
	private JPanel addActivityPanel;
	private JLabel addActivityTitleLabel, addANameLabel, addAManagerLabel,
			addAAdressLabel, addAZipcodeLabel, addACityLabel, addASeatsLabel,
			addADurationLabel, addATime, addADescriptionLabel, addAallCB,
			addAmondayCB, addAtuesdayCB, addAwednesdayCB, addAthursdayCB,
			addAfridayCB, addAsaturdayCB, addAsundayCB;
	private JTextField addANameInput, addAManagerInput, addAAdressInput,
			addAZipcodeInput, addACityInput, addASeatsInput,
			addADurationStartInput, addADurationEndInput, addAallTF, addAallTT,
			addAmondayTF, addAmondayTT, addAtuesdayTF, addAtuesdayTT,
			addAwednesdayTF, addAwednesdayTT, addAthursdayTF, addAthursdayTT,
			addAfridayTF, addAfridayTT, addAsaturdayTF, addAsaturdayTT,
			addAsundayTF, addAsundayTT, addAuploadPath;
	private JTextArea addADescriptionInput;
	private JButton addAok, addAcancel, addAuploadFile;
	private Picture addAstartCal, addAendCal;

	// searchActivity component's
	private JPanel searchActivityPanel;
	private JLabel searchATitleLabel, searchANameLabel, searchAManagerLabel,
			searchAAdressLabel, searchAZipcodeLabel, searchACityLabel,
			searchADurationLabel, searchASeatsLabel, searchADay,
			searchAdocLabel, searchAstatusLabel;
	private JComboBox searchAstatusBox;
	private JCheckBox searchAallCB, searchAmondayCB, searchAtuesdayCB,
			searchAwednesdayCB, searchAthursdayCB, searchAfridayCB,
			searchAsaturdayCB, searchAsundayCB;
	private JTextField searchANameInput, searchAManagerInput,
			searchAAdressInput, searchAZipcodeInput, searchACityInput,
			searchASeatsInput, searchADurationStartInput,
			searchADurationEndInput, searchAdocName;
	private JButton searchAok, searchAcancel;
	private Picture searchAstartCal, searchAendCal;

	// showActivityPanel
	private JPanel showActivityPanel;
	private JLabel showAtitleLabel, showAdoc, showAmanagerLabel,
			showAdurationLabel, showATime, showAadrLabel, showAtotalSeatsLabel,
			showAmanagerValue, showAdurationValue, showAadrValue,
			showAtotalSeatsValue;
	private JTextArea showAdesc, showATimeValue;
	private JButton showAallLogEntries, showAattendance;
	private JTable showActivityLogTable;
	private int showActivityLogW, showAposition, showAattW;
	boolean showAallEntries = false; // show all log-entries or not
	private String showApath;

	// activityResultPanel
	private JPanel activityResultPanel;
	private JLabel activityResultTitleLabel;
	private JScrollPane activityResultScroll;
	private JTable activityResultTable;
	private int activityResultTableWidth;

	// activitySidePanel
	private JPanel activitySidePanel;
	private JButton mainPageFromActivitySidePanel,
			mainActivityPageFromActivitySidePanel, regInterest, removeInterest,
			terminateActivity, editActivity, regAttendance;
	private JDialog memberlistDialog = null, removeInterestDialog = null;

	// editActivityPanel
	private JPanel editActivityPanel;
	private JLabel editActivityTitleLabel, editANameLabel, editAManagerLabel,
			editAAdressLabel, editAZipcodeLabel, editACityLabel,
			editASeatsLabel, editADurationLabel, editATime,
			editADescriptionLabel, editAallCB, editAmondayCB, editAtuesdayCB,
			editAwednesdayCB, editAthursdayCB, editAfridayCB, editAsaturdayCB,
			editAsundayCB;
	private JTextField editANameInput, editAManagerInput, editAAdressInput,
			editAZipcodeInput, editACityInput, editASeatsInput,
			editADurationStartInput, editADurationEndInput, editAallTF,
			editAallTT, editAmondayTF, editAmondayTT, editAtuesdayTF,
			editAtuesdayTT, editAwednesdayTF, editAwednesdayTT,
			editAthursdayTF, editAthursdayTT, editAfridayTF, editAfridayTT,
			editAsaturdayTF, editAsaturdayTT, editAsundayTF, editAsundayTT,
			editAuploadPath;
	private JTextArea editADescriptionInput;
	private JButton editAok, editAcancel, editAuploadFile;
	private Picture editAstartCal, editAendCal;

	// regAttendancePanel
	private JPanel regAttendancePanel;
	private JLabel regAttTitleLabel, regAttDateLabel, regAttNrLabel,
			regAttCommentLabel;
	private JTextField regAttDateInput, regAttNrInput;
	private JTextArea regAttCommentInput;
	private JButton regAttOK, regAttCancel;
	private Picture regAttDatePicker;

	// mainStatisticsSidePanel component's
	private JPanel mainStatisticsSidePanel;
	private JButton mainPageFromMainStatistics,
			mainStatisticPageFromMainStatistics, mainMemberStatistics,
			mainActivityStatistics;

	// mainStatisticsPanel component's
	private JPanel mainStatisticsPanel;
	private JLabel mainStatTitle, mainStatUptimeLabel, mainStatEntryLabel,
			mainStatUserLink, mainStatMostActiveLabel, mainStatUptimeValue,
			mainStatEntryValue, mainStatMostActiveValue;

	// memberStatisticsPanel component's
	private JPanel memberStatisticsPanel;
	private JLabel mStatTitleLabel;
	private JTextField mStatInput;
	private JButton mStatButton;
	private int memberStatWidth, totalMstatW, mstatYear = 0;

	// activityStatisticsPanel component's
	private JPanel activityStatisticsPanel;
	private JLabel aStatTitleLabel;
	private JTextField aStatInput;
	private JButton aStatButton;
	private int activityStatWidth, totalAstatW, astatYear = 0;

	public UserPage(User user) {

		c.user = user;
		new Manager(c.user);
		msg = new Message();
		fontmetrics = getFontMetrics(user.getFont());

		dim = Toolkit.getDefaultToolkit().getScreenSize();
		insets = Toolkit.getDefaultToolkit().getScreenInsets(
				getGraphicsConfiguration());

		this.setIconImage(Toolkit.getDefaultToolkit()
				.getImage("Image/LOGO.jpg"));

		JFrame f = new JFrame();
		f.pack();
		frameinsets = f.getInsets();

		this.setTitle("Rabea Kvinneforening");
		this.setSize((int) (dim.width), (int) (dim.height - insets.bottom));
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				shutdownMenuItem.doClick();
			}
		});
		this.addKeyListener(this);

		createMenu();

		this.setFocusable(true);
		this.setJMenuBar(menubar);

		DEFAULT_HEIGTH = this.getHeight() - menubar.getHeight()
				- frameinsets.top - frameinsets.bottom - 10
				- new JScrollBar().getPreferredSize().width;
		DEFAULT_PANEL_X = frameinsets.left
				+ new JScrollBar().getPreferredSize().width; // +sidepanel.width
		DEFAULT_PANEL_Y = new JScrollBar().getPreferredSize().width;
		DEFAULT_PANEL_W = (int) (dim.getWidth() - frameinsets.right
				- frameinsets.left - 10 - (new JScrollBar().getPreferredSize().width * 2));
		DEFAULT_PANEL_H = DEFAULT_HEIGTH - DEFAULT_PANEL_Y;

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat day = new SimpleDateFormat("EEEEEE");
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
		dateLabel = new JLabel(day.format(calendar.getTime()).toUpperCase()
				+ " - " + date.format(calendar.getTime()) + " - "
				+ time.format(calendar.getTime()));

		createMainSidePanel();
		createMainPanel();

		createMemberSidePanel();
		createMainMemberSidePanel();
		createShowMember(new Member());
		createMainMemberPanel();
		createNewMemberPanel();
		createSearchMemberPanel();
		editImageURL = imageURL;
		createEditMember();
		createMemberResultPanel(null);

		createMainActivitySidePanel();
		createMainActivityPanel();
		createActivitySidePanel();
		createShowActivityPanel(new Activity());
		createAddActivityPanel();
		createSearchActivityPanel();
		createEditActivityPanel(new Activity());
		createRegAttendancePanel();
		createActivityResultPanel(null);

		createMainStatisticsSidePanel();
		createMainStatisticsPanel();
		createMemberStatisticsPanel(null);
		createActivityStatisticsPanel(null);

		panel = new JPanel();
		panel.setLayout(new GroupLayout());
		panel.addKeyListener(this);
		panel.setSize(DEFAULT_PANEL_W, DEFAULT_HEIGTH);

		panel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));

		panel.add(mainSidePanel,
				new Constraints(
						new Leading(0, mainSidePanel.getWidth(), 10, 10),
						new Leading(0, mainSidePanel.getHeight(), 12, 12)));
		mainMemberPanel.removeAll();
		mainActivityPanel.removeAll();
		createMainPanel();
		panel.add(mainPanel,
				new Constraints(new Leading(mainSidePanel.getWidth()
						+ DEFAULT_PANEL_X, mainPanel.getWidth(), 10, 10),
						new Leading(DEFAULT_PANEL_Y, mainPanel.getHeight(), 12,
								12)));

		JScrollPane s = new JScrollPane(panel,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.add(s);
	}

	public void createMenu() {
		// create jmenubar
		menubar = new JMenuBar();
		menubar.setBackground(c.user.getForegroundColor());
		menubar.setMargin(new Insets(2, 2, 2, 2));
		menubar.setSize(this.getWidth() - 5,
				fontmetrics.getHeight() + (menubar.getInsets().bottom * 2));

		// create jmenu
		fileMenu = new JMenu(" Fil ");
		closeMenu = new JMenu("Avslutt");
		closeMenu.setBorder(BorderFactory.createLineBorder(c.user
				.getBackgroundColor()));
		newMenu = new JMenu("Ny");
		newMenu.setBorder(BorderFactory.createLineBorder(c.user
				.getBackgroundColor()));
		toolsMenu = new JMenu(" Verktøy ");
		helpMenu = new JMenu(" Hjelp ");

		// create jmenuitem
		settingsMenuItem = new JMenuItem("Innstillinger");
		settingsMenuItem.setBorder(BorderFactory.createLineBorder(c.user
				.getBackgroundColor()));
		shutdownMenuItem = new JMenuItem("Avslutte");
		shutdownMenuItem.setBorder(BorderFactory.createLineBorder(c.user
				.getBackgroundColor()));
		logOffMenuItem = new JMenuItem("Logg av");
		logOffMenuItem.setBorder(BorderFactory.createLineBorder(c.user
				.getBackgroundColor()));
		aboutMenuItem = new JMenuItem("Om programmet");
		aboutMenuItem.setBorder(BorderFactory.createLineBorder(c.user
				.getBackgroundColor()));
		helpMenuItem = new JMenuItem("Hjelpesentralen");
		helpMenuItem.setBorder(BorderFactory.createLineBorder(c.user
				.getBackgroundColor()));
		newMemberMenuItem = new JMenuItem("Medlem");
		newMemberMenuItem.setBorder(BorderFactory.createLineBorder(c.user
				.getBackgroundColor()));
		newActivityMenuItem = new JMenuItem("Aktivitet");
		newActivityMenuItem.setBorder(BorderFactory.createLineBorder(c.user
				.getBackgroundColor()));

		passwordMenuItem = new JMenuItem("Endre passord");
		passwordMenuItem.setBorder(BorderFactory.createLineBorder(c.user
				.getBackgroundColor()));

		// add to jmenu
		fileMenu.add(newMenu);
		fileMenu.add(closeMenu);

		newMenu.add(newMemberMenuItem);
		newMenu.add(newActivityMenuItem);

		closeMenu.add(logOffMenuItem);
		closeMenu.add(shutdownMenuItem);

		toolsMenu.add(settingsMenuItem);
		toolsMenu.add(passwordMenuItem);

		helpMenu.add(helpMenuItem);
		helpMenu.add(aboutMenuItem);

		// add to jmenubar
		menubar.add(fileMenu);
		menubar.add(toolsMenu);
		menubar.add(helpMenu);

		// add listeners
		shutdownMenuItem.addActionListener(this);
		logOffMenuItem.addActionListener(this);
		aboutMenuItem.addActionListener(this);
		helpMenuItem.addActionListener(this);
		settingsMenuItem.addActionListener(this);
		passwordMenuItem.addActionListener(this);
		newMemberMenuItem.addActionListener(this);
		newActivityMenuItem.addActionListener(this);
	}

	public void createMainSidePanel() {
		// create panel
		mainSidePanel = new JPanel();
		mainSidePanel.setLayout(new GroupLayout());
		mainSidePanel.setBackground(c.user.getBackgroundColor().darker());
		mainSidePanel.addKeyListener(this);

		// create buttons and add listeners
		mainMember = new JButton("Medlem");
		int longestButton = fontmetrics.stringWidth(mainMember.getText());
		mainMember.addActionListener(this);
		mainMember.addKeyListener(this);

		mainActivity = new JButton("Aktivitet");
		if (longestButton < fontmetrics.stringWidth(mainActivity.getText()))
			longestButton = fontmetrics.stringWidth(mainActivity.getText());
		mainActivity.addActionListener(this);
		mainActivity.addKeyListener(this);

		mainStatistics = new JButton("Statistikk");
		if (longestButton < fontmetrics.stringWidth(mainStatistics.getText()))
			longestButton = fontmetrics.stringWidth(mainStatistics.getText());
		mainStatistics.addActionListener(this);
		mainStatistics.addKeyListener(this);

		// create help-variables
		int buttonH = fontmetrics.getHeight()
				+ (new JButton().getInsets().bottom * 2);
		int buttonW = longestButton + (new JButton().getInsets().left * 2);
		int height = 20;

		String title = "HOVEDMENY";

		mainSidePanel.setBorder(BorderFactory.createTitledBorder(null, title,
				TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null,
				c.user.getForegroundColor()));

		if (buttonW + 20 < fontmetrics.stringWidth(title))
			buttonW = fontmetrics.stringWidth(title);

		// add to panel
		mainSidePanel.add(mainMember, new Constraints(new Leading(10, buttonW,
				10, 10), new Leading(height, buttonH, 12, 12)));

		height += 10 + buttonH;

		mainSidePanel.add(mainActivity, new Constraints(new Leading(10,
				buttonW, 10, 10), new Leading(height, buttonH, 12, 12)));

		height += 10 + buttonH;

		mainSidePanel.add(mainStatistics, new Constraints(new Leading(10,
				buttonW, 10, 10), new Leading(height, buttonH, 12, 12)));

		// set sidepanel size
		mainSidePanel.setSize(buttonW + 30, DEFAULT_HEIGTH);
	}

	public void createMainPanel() {

		createMemberLogPanel();
		createActivityLogPanel();

		// create panel
		mainPanel = new JPanel();
		mainPanel.setLayout(new GroupLayout());
		mainPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		mainPanel.addKeyListener(this);

		// create label
		titleLabel = new JLabel("Du logget inn : ");
		mainPanel.add(titleLabel, new Constraints(new Leading(10, 10, 10),
				new Leading(10, 12, 12)));

		// create help-variables and add to panel
		int heigth = 10;

		mainPanel.add(titleLabel, new Constraints(new Leading(10, 10, 10),
				new Leading(heigth, 12, 12)));

		heigth += 10 + fontmetrics.getHeight();

		mainPanel.add(dateLabel, new Constraints(new Leading(10, 10, 10),
				new Leading(heigth, 12, 12)));

		heigth += 20 + fontmetrics.getHeight();

		mainPanel.add(memberLogPanel,
				new Constraints(
						new Leading(10, memberLogMainTableWidth, 10, 10),
						new Leading(heigth, memberLogMainTable.getHeight()
								+ memberLogMainTable.getRowHeight(), 12, 12)));

		heigth += 10 + memberLogMainTable.getHeight()
				+ memberLogMainTable.getRowHeight();

		mainPanel
				.add(activityLogPanel, new Constraints(new Leading(10,
						activityLogMainTableWidth, 10, 10), new Leading(heigth,
						activityLogMainTable.getSize().height
								+ activityLogMainTable.getRowHeight(), 12, 12)));

		heigth += 10 + activityLogMainTable.getHeight()
				+ activityLogMainTable.getRowHeight();

		// Set panel size
		mainPanel
				.setSize(
						DEFAULT_PANEL_W - mainSidePanel.getWidth() > logWidth ? DEFAULT_PANEL_W
								- mainSidePanel.getWidth()
								: logWidth,
						DEFAULT_PANEL_H > heigth ? DEFAULT_PANEL_H : heigth);

	}

	public void createMemberLogPanel() {
		// create panel
		memberLogPanel = new JPanel();
		memberLogPanel.setLayout(new BorderLayout());
		memberLogPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		memberLogPanel.addKeyListener(this);

		// create label
		memberLogTitleLabel = new JLabel("Medlemslogg");
		memberLogTitleLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize() + 1));
		memberLogTitleLabel.setForeground(c.user.getForegroundColor().darker());

		// create table
		memberLogMainTable = c.getMainMemberLog();
		memberLogMainTable.setAutoCreateRowSorter(true);
		memberLogMainTable.getTableHeader().setReorderingAllowed(false);
		memberLogMainTable.getTableHeader().setFont(c.user.getFont());
		memberLogMainTable.getTableHeader().setForeground(
				c.user.getForegroundColor());
		memberLogMainTable.getTableHeader().setBackground(
				c.user.getBackgroundColor().darker());

		memberLogMainTableWidth = 0;
		autoResizeColWidth(memberLogMainTable, "memberlog");
		memberLogMainTable.setRowHeight(fontmetrics.getHeight() + 4);

		if (!memberLogMainTable.getValueAt(0, 0).toString().trim()
				.equalsIgnoreCase("Ingen oppføringer tilgjengelig")) {
			memberLogMainTable.getSelectionModel().addListSelectionListener(
					new ListSelectionListener() {

						public void valueChanged(ListSelectionEvent e) {
							memberLogMainTable.setRowSelectionAllowed(true);
							int row = memberLogMainTable.getSelectedRow();
							int column = 0;

							while (!memberLogMainTable.getColumnName(column)
									.equals(" ID "))
								column++;

							if (msg.confirmDialog("Gå til medlem "
									+ memberLogMainTable
											.getValueAt(row, column).toString()
											.trim() + "?") == 0) {
								String termDate = c.getMember(
										Integer.parseInt(memberLogMainTable
												.getValueAt(row, column)
												.toString().trim()))
										.getTerminationDate();

								Calendar calendar = Calendar.getInstance();
								Calendar memberDate = Calendar.getInstance();
								memberDate.set(Integer.parseInt(termDate
										.substring(6)),
										Integer.parseInt(termDate.substring(3,
												5)) - 1, Integer
												.parseInt(termDate.substring(0,
														2)));
								calendar.set(calendar.get(Calendar.YEAR),
										calendar.get(Calendar.MONTH),
										calendar.get(Calendar.DAY_OF_MONTH));

								if (calendar.before(memberDate)) {
									hideAllPanels();
									panel.removeAll();
									showMemberPanel.removeAll();
									memberSidePanel.removeAll();
									createMemberSidePanel();
									createShowMember(c.getMember(Integer
											.parseInt(memberLogMainTable
													.getValueAt(row, column)
													.toString().trim())));

									panel.add(
											memberSidePanel,
											new Constraints(
													new Leading(
															0,
															memberSidePanel
																	.getWidth(),
															10, 10),
													new Leading(
															0,
															memberSidePanel
																	.getHeight(),
															12, 12)));

									panel.add(
											showMemberPanel,
											new Constraints(
													new Leading(
															position
																	+ DEFAULT_PANEL_X,
															showMemberPanel
																	.getWidth(),
															10, 10),
													new Leading(
															DEFAULT_PANEL_Y,
															showMemberPanel
																	.getHeight(),
															10, 10)));
								} else {
									hideAllPanels();
									panel.removeAll();
									showMemberPanel.removeAll();
									mainMemberSidePanel.removeAll();
									createMainMemberSidePanel();
									createShowMember(c.getMember(Integer
											.parseInt(memberLogMainTable
													.getValueAt(row, column)
													.toString().trim())));

									panel.add(
											mainMemberSidePanel,
											new Constraints(
													new Leading(
															0,
															mainMemberSidePanel
																	.getWidth(),
															10, 10),
													new Leading(
															0,
															mainMemberSidePanel
																	.getHeight(),
															12, 12)));

									panel.add(
											showMemberPanel,
											new Constraints(
													new Leading(
															position
																	+ DEFAULT_PANEL_X,
															showMemberPanel
																	.getWidth(),
															10, 10),
													new Leading(
															DEFAULT_PANEL_Y,
															showMemberPanel
																	.getHeight(),
															10, 10)));
								}
							}

						}
					});
		}

		// set table size
		memberLogMainTable.setSize(
				memberLogMainTableWidth,
				memberLogMainTable.getRowHeight()
						* (memberLogMainTable.getRowCount() + 1));
		memberLogScroll = new JScrollPane(memberLogMainTable);
		memberLogScroll.setBackground(c.user.getBackgroundColor().brighter());

		// add to panel
		memberLogPanel.add(memberLogTitleLabel, BorderLayout.BEFORE_FIRST_LINE);
		memberLogPanel.add(memberLogScroll, BorderLayout.CENTER);

	}

	public void createActivityLogPanel() {
		// create panel
		activityLogPanel = new JPanel();
		activityLogPanel.setLayout(new BorderLayout());
		activityLogPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		activityLogPanel.addKeyListener(this);

		// create label
		activityLogTitleLabel = new JLabel("Aktivitetslogg");
		activityLogTitleLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize() + 1));
		activityLogTitleLabel.setForeground(c.user.getForegroundColor()
				.darker());

		// create table
		activityLogMainTable = c.getMainActivityLog();
		activityLogMainTable.setAutoCreateRowSorter(true);
		activityLogMainTable.getTableHeader().setReorderingAllowed(false);
		activityLogMainTable.getTableHeader().setFont(c.user.getFont());
		activityLogMainTable.getTableHeader().setForeground(
				c.user.getForegroundColor());
		activityLogMainTable.getTableHeader().setBackground(
				c.user.getBackgroundColor().darker());

		activityLogMainTableWidth = 0;
		autoResizeColWidth(activityLogMainTable, "activitylog");
		activityLogMainTable.setRowHeight(fontmetrics.getHeight() + 4);

		if (!activityLogMainTable.getValueAt(0, 0).toString().trim()
				.equalsIgnoreCase("Ingen oppføringer tilgjengelig")) {
			activityLogMainTable.getSelectionModel().addListSelectionListener(
					new ListSelectionListener() {

						@Override
						public void valueChanged(ListSelectionEvent e) {
							activityLogMainTable.setRowSelectionAllowed(true);
							int row = activityLogMainTable.getSelectedRow();
							int column = 0;

							while (!activityLogMainTable.getColumnName(column)
									.equals(" ID "))
								column++;

							if (msg.confirmDialog("Gå til aktivitet "
									+ activityLogMainTable
											.getValueAt(row, column).toString()
											.trim() + "?") == 0) {

								Activity a = c.getActivity(activityLogMainTable
										.getValueAt(row, column).toString()
										.trim());
								Calendar calendar = Calendar.getInstance();
								Calendar date = Calendar.getInstance();
								date.set(Integer.parseInt(a.getEndDate()
										.substring(6)), Integer.parseInt(a
										.getEndDate().substring(3, 5)) - 1,
										Integer.parseInt(a.getEndDate()
												.substring(0, 2)));
								calendar.set(calendar.get(Calendar.YEAR),
										calendar.get(Calendar.MONTH),
										calendar.get(Calendar.DAY_OF_MONTH));

								if (calendar.before(date)) {

									hideAllPanels();
									panel.removeAll();
									showActivityPanel.removeAll();
									activitySidePanel.removeAll();
									createActivitySidePanel();
									showAallEntries = false;
									createShowActivityPanel(a);

									panel.add(
											activitySidePanel,
											new Constraints(
													new Leading(
															0,
															activitySidePanel
																	.getWidth(),
															10, 10),
													new Leading(
															0,
															activitySidePanel
																	.getHeight(),
															12, 12)));

									panel.add(
											showActivityPanel,
											new Constraints(
													new Leading(
															showAposition
																	+ DEFAULT_PANEL_X,
															showActivityPanel
																	.getWidth(),
															10, 10),
													new Leading(
															DEFAULT_PANEL_Y,
															showActivityPanel
																	.getHeight(),
															10, 10)));

								} else {

									hideAllPanels();
									panel.removeAll();
									showActivityPanel.removeAll();
									mainActivitySidePanel.removeAll();
									createMainActivitySidePanel();
									showAallEntries = false;
									createShowActivityPanel(a);

									panel.add(
											mainActivitySidePanel,
											new Constraints(
													new Leading(
															0,
															mainActivitySidePanel
																	.getWidth(),
															10, 10),
													new Leading(
															0,
															mainActivitySidePanel
																	.getHeight(),
															12, 12)));

									panel.add(
											showActivityPanel,
											new Constraints(
													new Leading(
															showAposition
																	+ DEFAULT_PANEL_X,
															showActivityPanel
																	.getWidth(),
															10, 10),
													new Leading(
															DEFAULT_PANEL_Y,
															showActivityPanel
																	.getHeight(),
															10, 10)));
								}
							}
						}
					});
		}
		// set table size
		activityLogMainTable.setSize(
				activityLogMainTableWidth,
				activityLogMainTable.getRowHeight()
						* (activityLogMainTable.getRowCount() + 1));
		activityLogScroll = new JScrollPane(activityLogMainTable);
		activityLogScroll.setBackground(c.user.getBackgroundColor().brighter());
		// add to panel
		activityLogPanel.add(activityLogTitleLabel,
				BorderLayout.BEFORE_FIRST_LINE);
		activityLogPanel.add(activityLogScroll, BorderLayout.CENTER);
	}

	public void createMainMemberSidePanel() {
		// create panel
		mainMemberSidePanel = new JPanel();
		mainMemberSidePanel.setLayout(new GroupLayout());
		mainMemberSidePanel.setBackground(c.user.getBackgroundColor().darker());
		mainMemberSidePanel.addKeyListener(this);

		mainMemberSidePanel.setBorder(BorderFactory.createTitledBorder(null,
				"MEDLEMSMENY", TitledBorder.LEADING,
				TitledBorder.DEFAULT_POSITION, null,
				c.user.getForegroundColor()));

		// create buttons and add listeners
		mainPagefromMainMemberSidePanel = new JButton("Hovedmenyen");
		mainPagefromMainMemberSidePanel.addActionListener(this);
		mainPagefromMainMemberSidePanel.addKeyListener(this);
		int longestButton = fontmetrics
				.stringWidth(mainPagefromMainMemberSidePanel.getText());

		mainMemberPagefromMainMemberSidePanel = new JButton("Medlemssiden");
		mainMemberPagefromMainMemberSidePanel.addActionListener(this);
		mainMemberPagefromMainMemberSidePanel.addKeyListener(this);
		if (longestButton < fontmetrics
				.stringWidth(mainMemberPagefromMainMemberSidePanel.getText()))
			longestButton = fontmetrics
					.stringWidth(mainMemberPagefromMainMemberSidePanel
							.getText());

		addMember = new JButton("Legg til medlem");
		addMember.addActionListener(this);
		addMember.addKeyListener(this);
		if (longestButton < fontmetrics.stringWidth(addMember.getText()))
			longestButton = fontmetrics.stringWidth(addMember.getText());

		searchMember = new JButton("Søk etter medlem");
		searchMember.addActionListener(this);
		searchMember.addKeyListener(this);
		if (longestButton < fontmetrics.stringWidth(searchMember.getText()))
			longestButton = fontmetrics.stringWidth(searchMember.getText());

		// create help-variables
		int buttonH = fontmetrics.getHeight()
				+ (new JButton().getInsets().bottom * 2);
		int buttonW = longestButton + (new JButton().getInsets().left * 2);
		int height = 20;

		// add to panel
		mainMemberSidePanel.add(mainPagefromMainMemberSidePanel,
				new Constraints(new Leading(10, buttonW, 10, 10), new Leading(
						height, buttonH, 10, 10)));

		height += buttonH + 10;

		mainMemberSidePanel.add(mainMemberPagefromMainMemberSidePanel,
				new Constraints(new Leading(10, buttonW, 10, 10), new Leading(
						height, buttonH, 10, 10)));

		height += buttonH + 10;

		mainMemberSidePanel.add(addMember, new Constraints(new Leading(10,
				buttonW, 10, 10), new Leading(height, buttonH, 10, 10)));

		height += buttonH + 10;

		mainMemberSidePanel.add(searchMember, new Constraints(new Leading(10,
				buttonW, 10, 10), new Leading(height, buttonH, 10, 10)));

		// set sidepanel size
		mainMemberSidePanel.setSize(buttonW + 30, DEFAULT_HEIGTH);

	}

	public void createMainMemberPanel() {
		// create panel
		mainMemberPanel = new JPanel();
		mainMemberPanel.setLayout(new GroupLayout());
		mainMemberPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		mainMemberPanel.addKeyListener(this);

		mainPanel.removeAll();
		createMemberLogPanel();

		// add memberlog to panel
		mainMemberPanel.add(memberLogPanel,
				new Constraints(
						new Leading(10, memberLogMainTableWidth, 10, 10),
						new Leading(10, memberLogMainTable.getHeight()
								+ memberLogMainTable.getRowHeight(), 10, 10)));
		// set panel size
		mainMemberPanel
				.setSize(
						DEFAULT_PANEL_W - mainMemberSidePanel.getWidth() > memberLogMainTableWidth ? DEFAULT_PANEL_W
								- mainMemberSidePanel.getWidth()
								: memberLogMainTableWidth,
						DEFAULT_PANEL_H > memberLogMainTable.getHeight() ? DEFAULT_PANEL_H
								: memberLogMainTable.getHeight());
	}

	public void createMemberSidePanel() {
		// create panel
		memberSidePanel = new JPanel();
		memberSidePanel.setLayout(new GroupLayout());
		memberSidePanel.setBackground(c.user.getBackgroundColor().darker());
		memberSidePanel.addKeyListener(this);

		String title = "MEDLEMSDETALJER";
		memberSidePanel.setBorder(BorderFactory.createTitledBorder(null, title,
				TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null,
				c.user.getForegroundColor()));

		// create buttons and add listeners
		mainPageFromMemberSidePanel = new JButton("Hovedmeny");
		mainPageFromMemberSidePanel.addActionListener(this);
		mainPageFromMemberSidePanel.addKeyListener(this);
		int longestButton = fontmetrics.stringWidth(mainPageFromMemberSidePanel
				.getText());

		mainMemberpageFromMemberSidePanel = new JButton("Medlemsmeny");
		mainMemberpageFromMemberSidePanel.addActionListener(this);
		mainMemberpageFromMemberSidePanel.addKeyListener(this);
		if (longestButton < fontmetrics
				.stringWidth(mainMemberpageFromMemberSidePanel.getText()))
			longestButton = fontmetrics
					.stringWidth(mainMemberpageFromMemberSidePanel.getText());

		editMember = new JButton("Endre medlemmet");
		editMember.addActionListener(this);
		editMember.addKeyListener(this);
		if (longestButton < fontmetrics.stringWidth(editMember.getText()))
			longestButton = fontmetrics.stringWidth(editMember.getText());

		regMemberContingent = new JButton("Registrer kontingent");
		regMemberContingent.addActionListener(this);
		regMemberContingent.addKeyListener(this);
		if (longestButton < fontmetrics.stringWidth(regMemberContingent
				.getText()))
			longestButton = fontmetrics.stringWidth(regMemberContingent
					.getText());

		printCard = new JButton("Skriv ut medlemskort");
		printCard.addActionListener(this);
		printCard.addKeyListener(this);
		if (longestButton < fontmetrics.stringWidth(printCard.getText()))
			longestButton = fontmetrics.stringWidth(printCard.getText());

		terminateMembership = new JButton("Terminer medlemskap");
		terminateMembership.addActionListener(this);
		terminateMembership.addKeyListener(this);
		if (longestButton < fontmetrics.stringWidth(terminateMembership
				.getText()))
			longestButton = fontmetrics.stringWidth(terminateMembership
					.getText());

		m2aStatus = new JButton("Aktivitetsoppmelding");
		m2aStatus.addActionListener(this);
		m2aStatus.addKeyListener(this);
		if (longestButton < fontmetrics.stringWidth(m2aStatus.getText()))
			longestButton = fontmetrics.stringWidth(m2aStatus.getText());

		// create help-variables
		int buttonH = fontmetrics.getHeight()
				+ (new JButton().getInsets().bottom * 2);
		int buttonW = longestButton + (new JButton().getInsets().left * 2);
		int heigth = 20;

		// add to panel
		memberSidePanel.add(mainPageFromMemberSidePanel, new Constraints(
				new Leading(10, buttonW, 10, 10), new Leading(heigth, 10, 10)));

		heigth += 10 + buttonH;

		memberSidePanel.add(mainMemberpageFromMemberSidePanel, new Constraints(
				new Leading(10, buttonW, 10, 10), new Leading(heigth, 10, 10)));

		heigth += 10 + buttonH;

		memberSidePanel.add(m2aStatus, new Constraints(new Leading(10, buttonW,
				10, 10), new Leading(heigth, 10, 10)));

		heigth += 10 + buttonH;

		memberSidePanel.add(regMemberContingent, new Constraints(new Leading(
				10, buttonW, 10, 10), new Leading(heigth, 10, 10)));

		heigth += 10 + buttonH;

		memberSidePanel.add(printCard, new Constraints(new Leading(10, buttonW,
				10, 10), new Leading(heigth, 10, 10)));

		heigth += 10 + buttonH;

		memberSidePanel.add(editMember, new Constraints(new Leading(10,
				buttonW, 10, 10), new Leading(heigth, 10, 10)));

		heigth += 10 + buttonH;

		memberSidePanel.add(terminateMembership, new Constraints(new Leading(
				10, buttonW, 10, 10), new Leading(heigth, 10, 10)));

		// set sidepanel size
		memberSidePanel
				.setSize(
						buttonW + 30 > fontmetrics.stringWidth(title.trim()) + 20 ? buttonW + 30
								: fontmetrics.stringWidth(title.trim()) + 20,
						DEFAULT_HEIGTH);

	}

	public void createSearchMemberPanel() {
		FontMetrics fontmetrics = getFontMetrics(new Font(c.user.getFamily(),
				1, c.user.getSize()));

		// create panel
		searchMemberPanel = new JPanel();
		searchMemberPanel.setLayout(new GroupLayout());
		searchMemberPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		searchMemberPanel.addKeyListener(this);

		// create jlabels
		searchMemberTitle = new JLabel("Søk etter et medlem");
		searchMemberTitle.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize() + 1));
		searchMemberTitle.setForeground(c.user.getForegroundColor().darker());

		searchIdLabel = new JLabel("Medlemsnummer");
		searchIdLabel
				.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));

		int longestLabel = fontmetrics.stringWidth(searchIdLabel.getText());

		searchStatusLabel = new JLabel("Status");
		searchStatusLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));

		searchFirstnameLabel = new JLabel("Fornavn");
		searchFirstnameLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(searchFirstnameLabel
				.getText()))
			longestLabel = fontmetrics.stringWidth(searchFirstnameLabel
					.getText());

		searchSurnameLabel = new JLabel("Etternavn");
		searchSurnameLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics
				.stringWidth(searchSurnameLabel.getText()))
			longestLabel = fontmetrics
					.stringWidth(searchSurnameLabel.getText());

		searchAddressLabel = new JLabel("Adresse");
		searchAddressLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics
				.stringWidth(searchAddressLabel.getText()))
			longestLabel = fontmetrics
					.stringWidth(searchAddressLabel.getText());

		searchZipcodeLabel = new JLabel("Postnr");
		searchZipcodeLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));

		searchCityLabel = new JLabel("Poststed");
		searchCityLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(searchCityLabel.getText()))
			longestLabel = fontmetrics.stringWidth(searchCityLabel.getText());

		searchDateOfBirthLabel = new JLabel("Fødselsdato");
		searchDateOfBirthLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(searchDateOfBirthLabel
				.getText()))
			longestLabel = fontmetrics.stringWidth(searchDateOfBirthLabel
					.getText());

		searchGenderLabel = new JLabel("Kjønn");
		searchGenderLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));

		searchMembershipTypeLabel = new JLabel("Medlemskapstype");
		searchMembershipTypeLabel.setFont(new Font(c.user.getFamily(), 1,
				c.user.getSize()));
		if (longestLabel < fontmetrics.stringWidth(searchMembershipTypeLabel
				.getText()))
			longestLabel = fontmetrics.stringWidth(searchMembershipTypeLabel
					.getText());

		searchEmailLabel = new JLabel("Epostadresse");
		searchEmailLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(searchEmailLabel.getText()))
			longestLabel = fontmetrics.stringWidth(searchEmailLabel.getText());

		searchNrLabel = new JLabel("Telefonnummer");
		searchNrLabel
				.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		if (longestLabel < fontmetrics.stringWidth(searchNrLabel.getText()))
			longestLabel = fontmetrics.stringWidth(searchNrLabel.getText());

		// create textbox and combobox
		searchIdInput = new JTextField();
		searchFirstnameInput = new JTextField();
		searchSurnameInput = new JTextField();
		searchAddressInput = new JTextField();
		searchZipcodeInput = new JTextField();
		searchCityInput = new JTextField();
		searchDateOfBirthInput = new JTextField("dd-mm-åååå");
		searchEmailInput = new JTextField();
		searchNrInput = new JTextField();

		searchGenderInput = new JComboBox(new Object[] { " ( Velg ) ",
				"Kvinne", "Mann" });
		searchGenderInput.setSelectedIndex(0);

		searchStatusInput = new JComboBox(new Object[] { " ( Velg ) ", "Aktiv",
				"Inaktiv" });
		searchStatusInput.setSelectedIndex(0);

		searchMembershipTypeInput = new JComboBox(new Object[] { " ( Velg ) ",
				"Vanlig medlem", "Støttemedlem" });
		searchMembershipTypeInput.setSelectedIndex(0);

		// add keylistener
		searchIdInput.addKeyListener(this);
		searchFirstnameInput.addKeyListener(this);
		searchSurnameInput.addKeyListener(this);
		searchAddressInput.addKeyListener(this);
		searchZipcodeInput.addKeyListener(this);
		searchCityInput.addKeyListener(this);
		searchDateOfBirthInput.addKeyListener(this);
		searchEmailInput.addKeyListener(this);
		searchNrInput.addKeyListener(this);

		// create buttons and add listeners
		OKsearchMember = new JButton("Søk");
		OKsearchMember.addActionListener(this);
		OKsearchMember.addKeyListener(this);

		cancelsearchMember = new JButton("Avbryt");
		cancelsearchMember.addActionListener(this);
		cancelsearchMember.addKeyListener(this);

		// create help-variables and add labels to panel
		int x = 10;
		int y = 10;
		int w = fontmetrics.stringWidth("*") * 15 + 20
				+ fontmetrics.stringWidth("Status")
				+ (fontmetrics.stringWidth(" ( Velg ) ") * 2);
		int h = getFontMetrics(searchMemberTitle.getFont()).getHeight() + 4;

		searchGenderInput.setSize(w - (fontmetrics.stringWidth("*") * 16)
				- fontmetrics.stringWidth(searchGenderLabel.getText()) - 20, h);

		searchStatusInput.setSize(w - (fontmetrics.stringWidth("*") * 15)
				- fontmetrics.stringWidth(searchStatusLabel.getText()) - 20, h);

		// add labels to panel
		searchMemberPanel.add(searchMemberTitle, new Constraints(new Leading(x,
				10, 10), new Leading(y, 12, 12)));

		y += 10 + h;

		searchMemberPanel.add(searchIdLabel, new Constraints(new Leading(x,
				longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		searchMemberPanel.add(searchStatusLabel, new Constraints(new Leading(x
				+ longestLabel + (fontmetrics.stringWidth("*") * 15) + 20,
				fontmetrics.stringWidth(searchStatusLabel.getText()), 10, 10),
				new Leading(y, h, 12, 12)));

		y += 10 + h;

		searchMemberPanel.add(searchFirstnameLabel,
				new Constraints(new Leading(x, longestLabel, 10, 10),
						new Leading(y, h, 12, 12)));

		y += 10 + h;

		searchMemberPanel.add(searchSurnameLabel, new Constraints(new Leading(
				x, longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		searchMemberPanel.add(searchAddressLabel, new Constraints(new Leading(
				x, longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		searchMemberPanel.add(searchZipcodeLabel, new Constraints(new Leading(
				x, longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		searchMemberPanel.add(searchCityLabel, new Constraints(new Leading(x
				+ longestLabel + (fontmetrics.stringWidth("*") * 8) + 20,
				fontmetrics.stringWidth(searchCityLabel.getText()), 10, 10),
				new Leading(y, h, 12, 12)));

		y += 10 + h;

		searchMemberPanel.add(searchDateOfBirthLabel,
				new Constraints(new Leading(x, longestLabel, 10, 10),
						new Leading(y, h, 12, 12)));

		searchMemberPanel.add(searchGenderLabel, new Constraints(new Leading(x
				+ longestLabel + (fontmetrics.stringWidth("*") * 16) + 20,
				fontmetrics.stringWidth(searchGenderLabel.getText()), 10, 10),
				new Leading(y, h, 12, 12)));

		y += 10 + h;

		searchMemberPanel.add(searchMembershipTypeLabel,
				new Constraints(new Leading(x, longestLabel, 10, 10),
						new Leading(y, h, 12, 12)));

		y += 10 + h;

		searchMemberPanel.add(searchEmailLabel, new Constraints(new Leading(x,
				longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		searchMemberPanel.add(searchNrLabel, new Constraints(new Leading(x,
				longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		// add input to panel
		x += 10 + longestLabel;
		y = 25 + getFontMetrics(searchMemberTitle.getFont()).getHeight();

		searchMemberPanel.add(searchIdInput, new Constraints(new Leading(x,
				fontmetrics.stringWidth("*") * 15, 10, 10), new Leading(y, h,
				12, 12)));

		searchMemberPanel.add(
				searchStatusInput,
				new Constraints(new Leading(x
						+ (fontmetrics.stringWidth("*") * 15)
						+ fontmetrics.stringWidth(searchStatusLabel.getText())
						+ 20, searchStatusInput.getWidth(), 10, 10),
						new Leading(y, h, 12, 12)));

		y += 10 + h;

		searchMemberPanel.add(searchFirstnameInput, new Constraints(
				new Leading(x, w, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		searchMemberPanel.add(searchSurnameInput, new Constraints(new Leading(
				x, w, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		searchMemberPanel.add(searchAddressInput, new Constraints(new Leading(
				x, w, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		searchMemberPanel.add(searchZipcodeInput, new Constraints(new Leading(
				x, fontmetrics.stringWidth("*") * 8, 10, 10), new Leading(y, h,
				12, 12)));

		searchMemberPanel.add(
				searchCityInput,
				new Constraints(new Leading(x
						+ (fontmetrics.stringWidth("*") * 8)
						+ fontmetrics.stringWidth(searchCityLabel.getText())
						+ 20, w - (fontmetrics.stringWidth("*") * 8)
						- fontmetrics.stringWidth(searchCityLabel.getText())
						- 20, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		searchMemberPanel.add(searchDateOfBirthInput, new Constraints(
				new Leading(x, fontmetrics.stringWidth("*") * 16, 10, 10),
				new Leading(y, h, 12, 12)));

		searchMemberPanel.add(
				searchGenderInput,
				new Constraints(new Leading(x
						+ (fontmetrics.stringWidth("*") * 16)
						+ fontmetrics.stringWidth(searchGenderLabel.getText())
						+ 20, searchGenderInput.getWidth(), 10, 10),
						new Leading(y, h, 12, 12)));

		y += 10 + h;

		searchMemberPanel.add(searchMembershipTypeInput, new Constraints(
				new Leading(x, w, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		searchMemberPanel.add(searchEmailInput, new Constraints(new Leading(x,
				w, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		searchMemberPanel.add(searchNrInput, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 12, 12)));

		y += 40 + (h * 2);

		// add buttons to panel
		searchMemberPanel.add(
				cancelsearchMember,
				new Constraints(new Trailing(10, fontmetrics
						.stringWidth("Avbryt")
						+ (new JButton().getInsets().left * 2), 10, 10),
						new Trailing(10, h, 12, 12)));

		searchMemberPanel.add(
				OKsearchMember,
				new Constraints(new Trailing(20
						+ fontmetrics.stringWidth("Avbryt")
						+ (new JButton().getInsets().left * 2), fontmetrics
						.stringWidth("Avbryt")
						+ (new JButton().getInsets().left * 2), 10, 10),
						new Trailing(10, h, 12, 12)));

		// set panel size
		searchMemberPanel.setSize(longestLabel + w + 30 > DEFAULT_PANEL_W
				- mainMemberSidePanel.getWidth() ? longestLabel + w + 30
				: DEFAULT_PANEL_W - mainMemberSidePanel.getWidth(),
				y > DEFAULT_PANEL_H ? y : DEFAULT_PANEL_H);

	}

	public void createShowMember(Member m) {
		// create help-variables
		FontMetrics fontmetrics = getFontMetrics(new Font(c.user.getFamily(),
				1, c.user.getSize()));
		int labelW = 0;
		int heigth = 0;
		imageURL = m.getImageURL();

		// create informationLabel
		memberIDLabel = new JLabel("Medlemsnr: ");
		memberIDLabel.setFont(new Font(c.user.getFamily(), 1,
				c.user.getSize() + 1));

		statusLabel = new JLabel("Status: ");
		statusLabel.setFont(new Font(c.user.getFamily(), 1,
				c.user.getSize() + 1));

		typeLabel = new JLabel("Medlemstype: ");
		typeLabel
				.setFont(new Font(c.user.getFamily(), 1, c.user.getSize() + 1));

		firstnameLabel = new JLabel("Fornavn: ");
		firstnameLabel
				.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		labelW = fontmetrics.stringWidth(firstnameLabel.getText());

		surnameLabel = new JLabel("Etternavn: ");
		surnameLabel.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		labelW = labelW > fontmetrics.stringWidth(surnameLabel.getText()) ? labelW
				: fontmetrics.stringWidth(surnameLabel.getText());

		addressLabel = new JLabel("Adresse: ");
		addressLabel.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		labelW = labelW > fontmetrics.stringWidth(addressLabel.getText()) ? labelW
				: fontmetrics.stringWidth(addressLabel.getText());

		zipcodeLabel = new JLabel("Postnr: ");
		zipcodeLabel.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		labelW = labelW > fontmetrics.stringWidth(zipcodeLabel.getText()) ? labelW
				: fontmetrics.stringWidth(zipcodeLabel.getText());

		cityLabel = new JLabel("Poststed: ");
		cityLabel.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));

		dateOfBirthLabel = new JLabel("Fødselsdato: ");
		dateOfBirthLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		labelW = labelW > fontmetrics.stringWidth(dateOfBirthLabel.getText()) ? labelW
				: fontmetrics.stringWidth(dateOfBirthLabel.getText());

		genderLabel = new JLabel("Kjønn: ");
		genderLabel.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));

		mobileNrLabel = new JLabel("Mobilnr.: ");
		mobileNrLabel
				.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		labelW = labelW > fontmetrics.stringWidth(mobileNrLabel.getText()) ? labelW
				: fontmetrics.stringWidth(mobileNrLabel.getText());

		privateNrLabel = new JLabel("Privatnr.: ");
		privateNrLabel
				.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		labelW = labelW > fontmetrics.stringWidth(privateNrLabel.getText()) ? labelW
				: fontmetrics.stringWidth(privateNrLabel.getText());

		workNrLabel = new JLabel("Jobbnr.: ");
		workNrLabel.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		labelW = labelW > fontmetrics.stringWidth(workNrLabel.getText()) ? labelW
				: fontmetrics.stringWidth(workNrLabel.getText());

		emailLabel = new JLabel("Epostadresse: ");
		emailLabel.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		labelW = labelW > fontmetrics.stringWidth(emailLabel.getText()) ? labelW
				: fontmetrics.stringWidth(emailLabel.getText());

		enrollmentDateLabel = new JLabel("Innmeldingsdato: ");
		enrollmentDateLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		labelW = labelW > fontmetrics
				.stringWidth(enrollmentDateLabel.getText()) ? labelW
				: fontmetrics.stringWidth(enrollmentDateLabel.getText());

		// create contentLabels

		memberIDValue = new JLabel(m.getID());
		memberIDValue.setFont(new Font(c.user.getFamily(), 1,
				c.user.getSize() + 1));

		typeValue = new JLabel(m.getType());
		typeValue
				.setFont(new Font(c.user.getFamily(), 1, c.user.getSize() + 1));

		firstnameValue = new JLabel(m.getFirstname());
		surnameValue = new JLabel(m.getSurname());
		addressValue = new JLabel(m.getAddress());
		zipcodeValue = new JLabel(m.getZipcode());
		cityValue = new JLabel(m.getCity());
		dateOfBirthValue = new JLabel(m.getDateOfBirth());
		genderValue = new JLabel(m.getGender());
		mobileNrValue = new JLabel(m.getMobilenr());
		privateNrValue = new JLabel(m.getPrivatenr());
		workNrValue = new JLabel(m.getWorknr());
		emailValue = new JLabel(m.getEmail());
		enrollmentDateValue = new JLabel(m.getEnrollmentDate());

		// get the right date for validity/termination

		Calendar calendar = Calendar.getInstance();
		Calendar memberDate = Calendar.getInstance();
		memberDate.set(Integer.parseInt(m.getTerminationDate().substring(6)),
				Integer.parseInt(m.getTerminationDate().substring(3, 5)) - 1,
				Integer.parseInt(m.getTerminationDate().substring(0, 2)));
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));

		if (calendar.before(memberDate)) {
			statusValue = new JLabel("Aktiv");
			dateStatusLabel = new JLabel("Gyldig til: ");
			dateStatusValue = new JLabel(m.getValidityDate());
			position = memberSidePanel.getWidth();
		} else {
			statusValue = new JLabel("Inaktiv");
			dateStatusLabel = new JLabel("Medlemskap avsluttet: ");
			dateStatusValue = new JLabel(m.getTerminationDate());
			position = mainMemberSidePanel.getWidth();
		}

		statusValue.setFont(new Font(c.user.getFamily(), 1,
				c.user.getSize() + 1));
		dateStatusLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		labelW = labelW > fontmetrics.stringWidth(dateStatusLabel.getText()) ? labelW
				: fontmetrics.stringWidth(dateStatusLabel.getText());

		// get image
		showMemberImage = new Picture(imageURL, DEFAULT_IMAGE_WIDTH,
				DEFAULT_IMAGE_HEIGTH);
		showMemberImage.addMouseListener(new Hyperlink());

		// get table
		if (all) {
			showMemberLogTable = m.getAllLog();

			// create button
			allLogEntries = new JButton("Vis siste 5 loggoppføringer");

		} else {
			showMemberLogTable = m.getLog();

			// create button
			allLogEntries = new JButton("Vis alle loggoppføringer");

		}
		// add listeners
		allLogEntries.addActionListener(this);
		allLogEntries.addKeyListener(this);

		// customize table
		showMemberLogTable.setAutoCreateRowSorter(true);
		showMemberLogTable.getTableHeader().setReorderingAllowed(false);
		showMemberLogTable.getTableHeader().setFont(c.user.getFont());
		showMemberLogTable.getTableHeader().setForeground(
				c.user.getForegroundColor());
		showMemberLogTable.getTableHeader().setBackground(
				c.user.getBackgroundColor().darker());

		showMemberLogW = 0;
		autoResizeColWidth(showMemberLogTable, "showmember");
		showMemberLogTable.setRowHeight(fontmetrics.getHeight() + 4);

		// create panel
		showMemberPanel = new JPanel();
		showMemberPanel.setLayout(new GroupLayout());
		showMemberPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		showMemberPanel.addKeyListener(this);

		// add to panel
		heigth = 10;

		showMemberPanel.add(showMemberImage, new Constraints(new Leading(10,
				DEFAULT_IMAGE_WIDTH, 130, 130), new Leading(heigth,
				DEFAULT_IMAGE_HEIGTH, 170, 170)));

		showMemberPanel
				.add(memberIDLabel, new Constraints(new Leading(
						20 + DEFAULT_IMAGE_WIDTH, 10, 10), new Leading(heigth,
						10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel
				.add(statusLabel, new Constraints(new Leading(
						20 + DEFAULT_IMAGE_WIDTH, 10, 10), new Leading(heigth,
						10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel
				.add(typeLabel, new Constraints(new Leading(
						20 + DEFAULT_IMAGE_WIDTH, 10, 10), new Leading(heigth,
						10, 10)));

		heigth = 25 + (DEFAULT_IMAGE_HEIGTH > (heigth + fontmetrics.getHeight()) ? DEFAULT_IMAGE_HEIGTH
				: (heigth + fontmetrics.getHeight()));

		showMemberPanel.add(firstnameLabel, new Constraints(new Leading(10, 10,
				10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(surnameLabel, new Constraints(new Leading(10, 10,
				10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(addressLabel, new Constraints(new Leading(10, 10,
				10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(zipcodeLabel, new Constraints(new Leading(10, 10,
				10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(cityLabel, new Constraints(new Leading(10, 10, 10),
				new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(dateOfBirthLabel, new Constraints(new Leading(10,
				10, 10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(genderLabel, new Constraints(
				new Leading(10, 10, 10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(emailLabel, new Constraints(
				new Leading(10, 10, 10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(mobileNrLabel, new Constraints(new Leading(10, 10,
				10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(privateNrLabel, new Constraints(new Leading(10, 10,
				10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(workNrLabel, new Constraints(
				new Leading(10, 10, 10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(enrollmentDateLabel, new Constraints(new Leading(
				10, 10, 10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(dateStatusLabel, new Constraints(new Leading(10,
				10, 10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(allLogEntries, new Constraints(new Leading(10, 10,
				10), new Leading(heigth, 10, 10)));

		// add values to panel

		heigth = 10;
		showMemberPanel.add(memberIDValue,
				new Constraints(new Leading(30 + DEFAULT_IMAGE_WIDTH
						+ fontmetrics.stringWidth(memberIDLabel.getText()), 10,
						10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(statusValue,
				new Constraints(new Leading(30 + DEFAULT_IMAGE_WIDTH
						+ fontmetrics.stringWidth(statusLabel.getText()), 10,
						10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(
				typeValue,
				new Constraints(
						new Leading(30 + DEFAULT_IMAGE_WIDTH
								+ fontmetrics.stringWidth(typeLabel.getText()),
								10, 10), new Leading(heigth, 10, 10)));

		heigth = 25 + (DEFAULT_IMAGE_HEIGTH > (heigth + fontmetrics.getHeight()) ? DEFAULT_IMAGE_HEIGTH
				: (heigth + fontmetrics.getHeight()));
		labelW += 10;

		showMemberPanel.add(firstnameValue, new Constraints(new Leading(labelW,
				10, 10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(surnameValue, new Constraints(new Leading(labelW,
				10, 10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(addressValue, new Constraints(new Leading(labelW,
				10, 10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(zipcodeValue, new Constraints(new Leading(labelW,
				10, 10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(cityValue, new Constraints(new Leading(labelW, 10,
				10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(dateOfBirthValue, new Constraints(new Leading(
				labelW, 10, 10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(genderValue, new Constraints(new Leading(labelW,
				10, 10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(emailValue, new Constraints(new Leading(labelW, 10,
				10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(mobileNrValue, new Constraints(new Leading(labelW,
				10, 10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(privateNrValue, new Constraints(new Leading(labelW,
				10, 10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(workNrValue, new Constraints(new Leading(labelW,
				10, 10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(enrollmentDateValue, new Constraints(new Leading(
				labelW, 10, 10), new Leading(heigth, 10, 10)));

		heigth += fontmetrics.getHeight() + 10;

		showMemberPanel.add(dateStatusValue, new Constraints(new Leading(
				labelW, 10, 10), new Leading(heigth, 10, 10)));

		heigth += ((fontmetrics.getHeight() + 20) * 2)
				+ allLogEntries.getInsets().top
				+ allLogEntries.getInsets().bottom;

		// set table size and add to panel
		showMemberLogTable.setSize(
				showMemberLogW,
				showMemberLogTable.getRowHeight()
						* (showMemberLogTable.getRowCount() + 1));

		JScrollPane scroll = new JScrollPane(showMemberLogTable);
		scroll.setBackground(c.user.getBackgroundColor().brighter());
		JPanel p = new JPanel(new BorderLayout());
		p.add(scroll, BorderLayout.CENTER);
		showMemberPanel.add(p, new Constraints(new Leading(10, showMemberLogW,
				10, 10), new Leading(heigth,
				showMemberLogTable.getSize().height, 10, 10)));

		// set panel size
		if (statusValue.getText().equals("Aktiv")) {
			showMemberPanel
					.setSize(
							DEFAULT_PANEL_W - memberSidePanel.getWidth() > showMemberLogW + 20 ? DEFAULT_PANEL_W
									- memberSidePanel.getWidth()
									: showMemberLogW + 20,
							DEFAULT_PANEL_H > heigth
									+ showMemberLogTable.getHeight() + 10 ? DEFAULT_PANEL_H
									: heigth + showMemberLogTable.getHeight()
											+ 10);
		} else {
			showMemberPanel
					.setSize(
							DEFAULT_PANEL_W - mainMemberSidePanel.getWidth() > showMemberLogW ? DEFAULT_PANEL_W
									- mainMemberSidePanel.getWidth()
									: showMemberLogW,
							DEFAULT_PANEL_H > heigth
									+ showMemberLogTable.getHeight()
									+ showMemberLogTable.getRowHeight() ? DEFAULT_PANEL_H
									: heigth + showMemberLogTable.getHeight()
											+ showMemberLogTable.getRowHeight());
		}
	}

	public void createEditMember() {
		FontMetrics fontmetrics = getFontMetrics(new Font(c.user.getFamily(),
				1, c.user.getSize()));
		// create panel
		editMemberPanel = new JPanel();
		editMemberPanel.setLayout(new GroupLayout());
		editMemberPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		editMemberPanel.addKeyListener(this);

		// create label
		editTitle = new JLabel("Endre medlem " + memberIDValue.getText());
		editTitle
				.setFont(new Font(c.user.getFamily(), 1, c.user.getSize() + 1));
		editTitle.setForeground(c.user.getForegroundColor().darker());

		editImageRemove = new JLabel("<html><u>Fjern bilde</u></html>");
		editImageRemove.setFont(new Font(c.user.getFamily(), 3, c.user
				.getSize()));
		editImageRemove.addMouseListener(new Hyperlink());

		editFirstnameLabel = new JLabel("Fornavn");
		editFirstnameLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		int longestLabel = fontmetrics
				.stringWidth(editFirstnameLabel.getText());

		editSurnameLabel = new JLabel("Etternavn");
		editSurnameLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(editSurnameLabel.getText()))
			longestLabel = fontmetrics.stringWidth(editSurnameLabel.getText());

		editAddressLabel = new JLabel("Adresse");
		editAddressLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(editAddressLabel.getText()))
			longestLabel = fontmetrics.stringWidth(editAddressLabel.getText());

		editZipcodeLabel = new JLabel("Postnr");
		editZipcodeLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(editZipcodeLabel.getText()))
			longestLabel = fontmetrics.stringWidth(editZipcodeLabel.getText());

		editCityLabel = new JLabel("Poststed");
		editCityLabel
				.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		if (longestLabel < fontmetrics.stringWidth(editCityLabel.getText()))
			longestLabel = fontmetrics.stringWidth(editCityLabel.getText());

		editDateOfBirthLabel = new JLabel("Fødselsdato");
		editDateOfBirthLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(editDateOfBirthLabel
				.getText()))
			longestLabel = fontmetrics.stringWidth(editDateOfBirthLabel
					.getText());

		editGenderLabel = new JLabel("Kjønn");
		editGenderLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(editGenderLabel.getText()))
			longestLabel = fontmetrics.stringWidth(editGenderLabel.getText());

		editMembershipTypeLabel = new JLabel("Medlemskapstype");
		editMembershipTypeLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(editMembershipTypeLabel
				.getText()))
			longestLabel = fontmetrics.stringWidth(editMembershipTypeLabel
					.getText());

		editEmailLabel = new JLabel("Epostadresse");
		editEmailLabel
				.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		if (longestLabel < fontmetrics.stringWidth(editEmailLabel.getText()))
			longestLabel = fontmetrics.stringWidth(editEmailLabel.getText());

		editMobileLabel = new JLabel("Mobilnr.");
		editMobileLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(editMobileLabel.getText()))
			longestLabel = fontmetrics.stringWidth(editMobileLabel.getText());

		editPrivateLabel = new JLabel("Privatnr.");
		editPrivateLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(editPrivateLabel.getText()))
			longestLabel = fontmetrics.stringWidth(editPrivateLabel.getText());

		editWorkLabel = new JLabel("Jobbnr.");
		editWorkLabel
				.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		if (longestLabel < fontmetrics.stringWidth(editWorkLabel.getText()))
			longestLabel = fontmetrics.stringWidth(editWorkLabel.getText());

		// create textbox and combobox
		editFirstnameInput = new JTextField(firstnameValue.getText());
		editSurnameInput = new JTextField(surnameValue.getText());
		editAddressInput = new JTextField(addressValue.getText());
		editZipcodeInput = new JTextField(zipcodeValue.getText());
		editCityInput = new JTextField(cityValue.getText());
		editDateOfBirthInput = new JTextField(dateOfBirthValue.getText());
		editEmailInput = new JTextField(emailValue.getText());
		editMobileInput = new JTextField(mobileNrValue.getText());
		editPrivateInput = new JTextField(privateNrValue.getText());
		editWorkInput = new JTextField(workNrValue.getText());

		editGenderInput = new JComboBox(new Object[] { "Kvinne", "Mann" });
		editGenderInput.setSelectedItem(genderValue.getText());

		editMembershipTypeInput = new JComboBox(new Object[] { "Vanlig medlem",
				"Støttemedlem" });
		editMembershipTypeInput.setSelectedItem(typeValue.getText());

		// add keylistener
		editFirstnameInput.addKeyListener(this);
		editSurnameInput.addKeyListener(this);
		editAddressInput.addKeyListener(this);
		editZipcodeInput.addKeyListener(this);
		editCityInput.addKeyListener(this);
		editDateOfBirthInput.addKeyListener(this);
		editEmailInput.addKeyListener(this);
		editMobileInput.addKeyListener(this);
		editPrivateInput.addKeyListener(this);
		editWorkInput.addKeyListener(this);

		// create buttons and add listeners
		OKeditMember = new JButton("Lagre");
		OKeditMember.addActionListener(this);
		OKeditMember.addKeyListener(this);

		cancelEditMember = new JButton("Avbryt");
		cancelEditMember.addActionListener(this);
		cancelEditMember.addKeyListener(this);

		editMemberUploadImage = new JButton("Last opp fil...");
		editMemberUploadImage.addActionListener(this);
		editMemberUploadImage.addKeyListener(this);

		// get image
		editMemberImage = new Picture(imageURL, DEFAULT_IMAGE_WIDTH,
				DEFAULT_IMAGE_HEIGTH);

		// create help-variables
		int x = 10;
		int y = 10;
		int w = fontmetrics.stringWidth("*") * 15 + 20
				+ fontmetrics.stringWidth("Status")
				+ (fontmetrics.stringWidth(" ( Velg ) ") * 2);
		int h = getFontMetrics(editTitle.getFont()).getHeight() + 4;

		editGenderInput.setSize(w - (fontmetrics.stringWidth("*") * 15)
				- fontmetrics.stringWidth(editGenderLabel.getText()) - 20, h);

		// add labels to panel
		editMemberPanel.add(editTitle, new Constraints(new Leading(x, 10, 10),
				new Leading(y, 12, 12)));

		y += 10 + getFontMetrics(editTitle.getFont()).getHeight();

		editMemberPanel.add(editMemberImage, new Constraints(new Leading(x,
				DEFAULT_IMAGE_WIDTH, 130, 130), new Leading(y,
				DEFAULT_IMAGE_HEIGTH, 170, 170)));

		y = 30 + DEFAULT_IMAGE_HEIGTH + new JTextField().getInsets().top
				+ getFontMetrics(editTitle.getFont()).getHeight();

		editMemberPanel.add(
				editImageRemove,
				new Constraints(new Leading(x, getFontMetrics(
						editImageRemove.getFont()).stringWidth(
						editImageRemove.getText()), 10, 10), new Leading(y, 12,
						12)));

		y += 10 + getFontMetrics(editImageRemove.getFont()).getHeight();

		editMemberPanel.add(editFirstnameLabel, new Constraints(new Leading(x,
				longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		editMemberPanel.add(editSurnameLabel, new Constraints(new Leading(x,
				longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		editMemberPanel.add(editAddressLabel, new Constraints(new Leading(x,
				longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		editMemberPanel.add(editZipcodeLabel, new Constraints(new Leading(x,
				longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		editMemberPanel.add(editCityLabel, new Constraints(new Leading(x
				+ longestLabel + (fontmetrics.stringWidth("*") * 8) + 20,
				fontmetrics.stringWidth(editCityLabel.getText()), 10, 10),
				new Leading(y, h, 12, 12)));

		y += 10 + h;

		editMemberPanel.add(editDateOfBirthLabel, new Constraints(new Leading(
				x, longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		editMemberPanel.add(editGenderLabel, new Constraints(new Leading(x
				+ longestLabel + (fontmetrics.stringWidth("*") * 15) + 20,
				fontmetrics.stringWidth(editGenderLabel.getText()), 10, 10),
				new Leading(y, h, 12, 12)));

		y += 10 + h;

		editMemberPanel.add(editMembershipTypeLabel,
				new Constraints(new Leading(x, longestLabel, 10, 10),
						new Leading(y, h, 12, 12)));

		y += 10 + h;

		editMemberPanel.add(editEmailLabel, new Constraints(new Leading(x,
				longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		editMemberPanel.add(editMobileLabel, new Constraints(new Leading(x,
				longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		editMemberPanel.add(editPrivateLabel, new Constraints(new Leading(x,
				longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		editMemberPanel.add(editWorkLabel, new Constraints(new Leading(x,
				longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		// add input to panel
		x += 10 + DEFAULT_IMAGE_WIDTH;
		y = 25 + getFontMetrics(editTitle.getFont()).getHeight();

		editMemberPanel.add(editMemberUploadImage, new Constraints(new Leading(
				x, 10, 10), new Leading(y, 12, 12)));

		x = 20 + longestLabel;
		y = 40 + DEFAULT_IMAGE_HEIGTH + new JTextField().getInsets().top
				+ getFontMetrics(editTitle.getFont()).getHeight()
				+ getFontMetrics(editImageRemove.getFont()).getHeight();

		editMemberPanel.add(editFirstnameInput, new Constraints(new Leading(x,
				w, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		editMemberPanel.add(editSurnameInput, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		editMemberPanel.add(editAddressInput, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		editMemberPanel.add(editZipcodeInput, new Constraints(new Leading(x,
				fontmetrics.stringWidth("*") * 8, 10, 10), new Leading(y, h,
				12, 12)));

		editMemberPanel.add(
				editCityInput,
				new Constraints(new Leading(
						x
								+ (fontmetrics.stringWidth("*") * 8)
								+ fontmetrics.stringWidth(editCityLabel
										.getText()) + 20, w
								- (fontmetrics.stringWidth("*") * 8)
								- fontmetrics.stringWidth(editCityLabel
										.getText()) - 20, 10, 10), new Leading(
						y, h, 12, 12)));

		y += 10 + h;

		editMemberPanel.add(editDateOfBirthInput, new Constraints(new Leading(
				x, fontmetrics.stringWidth("*") * 15, 10, 10), new Leading(y,
				h, 12, 12)));

		editMemberPanel.add(
				editGenderInput,
				new Constraints(new Leading(x
						+ (fontmetrics.stringWidth("*") * 15)
						+ fontmetrics.stringWidth(editGenderLabel.getText())
						+ 20, editGenderInput.getWidth(), 10, 10), new Leading(
						y, h, 12, 12)));

		y += 10 + h;

		editMemberPanel.add(editMembershipTypeInput, new Constraints(
				new Leading(x, w, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		editMemberPanel.add(editEmailInput, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		editMemberPanel.add(editMobileInput, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		editMemberPanel.add(editPrivateInput, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		editMemberPanel.add(editWorkInput, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 12, 12)));

		y += 40 + (h * 2);

		// add buttons to panel
		editMemberPanel.add(cancelEditMember, new Constraints(new Trailing(10,
				fontmetrics.stringWidth("Avbryt")
						+ (new JButton().getInsets().left * 2), 10, 10),
				new Trailing(10, h, 12, 12)));

		editMemberPanel.add(
				OKeditMember,
				new Constraints(new Trailing(20
						+ fontmetrics.stringWidth("Avbryt")
						+ (new JButton().getInsets().left * 2), fontmetrics
						.stringWidth("Avbryt")
						+ (new JButton().getInsets().left * 2), 10, 10),
						new Trailing(10, h, 12, 12)));

		// set panel size
		editMemberPanel.setSize(longestLabel + w + 30 > DEFAULT_PANEL_W
				- memberSidePanel.getWidth() ? longestLabel + w + 30
				: DEFAULT_PANEL_W - memberSidePanel.getWidth(),
				y > DEFAULT_PANEL_H ? y : DEFAULT_PANEL_H);
	}

	public void createNewMemberPanel() {
		FontMetrics fontmetrics = getFontMetrics(new Font(c.user.getFamily(),
				1, c.user.getSize()));
		// create panel
		newMemberPanel = new JPanel();
		newMemberPanel.setLayout(new GroupLayout());
		newMemberPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		newMemberPanel.addKeyListener(this);

		// create jlabels
		newTitle = new JLabel("Opprett et nytt medlemskap");
		newTitle.setFont(new Font(c.user.getFamily(), 1, c.user.getSize() + 1));
		newTitle.setForeground(c.user.getForegroundColor().darker());

		newImageRemove = new JLabel("<html><u>Fjern bilde</u></html>");
		newImageRemove
				.setFont(new Font(c.user.getFamily(), 3, c.user.getSize()));
		newImageRemove.addMouseListener(new Hyperlink());

		newFirstnameLabel = new JLabel("Fornavn");
		newFirstnameLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		int longestLabel = fontmetrics.stringWidth(newFirstnameLabel.getText());

		newSurnameLabel = new JLabel("Etternavn");
		newSurnameLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(newSurnameLabel.getText()))
			longestLabel = fontmetrics.stringWidth(newSurnameLabel.getText());

		newAddressLabel = new JLabel("Adresse");
		newAddressLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(newAddressLabel.getText()))
			longestLabel = fontmetrics.stringWidth(newAddressLabel.getText());

		newZipcodeLabel = new JLabel("Postnr");
		newZipcodeLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(newZipcodeLabel.getText()))
			longestLabel = fontmetrics.stringWidth(newZipcodeLabel.getText());

		newCityLabel = new JLabel("Poststed");
		newCityLabel.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		if (longestLabel < fontmetrics.stringWidth(newCityLabel.getText()))
			longestLabel = fontmetrics.stringWidth(newCityLabel.getText());

		newDateOfBirthLabel = new JLabel("Fødselsdato");
		newDateOfBirthLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(newDateOfBirthLabel
				.getText()))
			longestLabel = fontmetrics.stringWidth(newDateOfBirthLabel
					.getText());

		newGenderLabel = new JLabel("Kjønn");
		newGenderLabel
				.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		if (longestLabel < fontmetrics.stringWidth(newGenderLabel.getText()))
			longestLabel = fontmetrics.stringWidth(newGenderLabel.getText());

		newMembershipTypeLabel = new JLabel("Medlemskapstype");
		newMembershipTypeLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(newMembershipTypeLabel
				.getText()))
			longestLabel = fontmetrics.stringWidth(newMembershipTypeLabel
					.getText());

		newEmailLabel = new JLabel("Epostadresse");
		newEmailLabel
				.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		if (longestLabel < fontmetrics.stringWidth(newEmailLabel.getText()))
			longestLabel = fontmetrics.stringWidth(newEmailLabel.getText());

		newMobileLabel = new JLabel("Mobilnr.");
		newMobileLabel
				.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		if (longestLabel < fontmetrics.stringWidth(newMobileLabel.getText()))
			longestLabel = fontmetrics.stringWidth(newMobileLabel.getText());

		newPrivateLabel = new JLabel("Privatnr.");
		newPrivateLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(newPrivateLabel.getText()))
			longestLabel = fontmetrics.stringWidth(newPrivateLabel.getText());

		newWorkLabel = new JLabel("Jobbnr.");
		newWorkLabel.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		if (longestLabel < fontmetrics.stringWidth(newWorkLabel.getText()))
			longestLabel = fontmetrics.stringWidth(newWorkLabel.getText());

		// create textbox and combobox
		newFirstnameInput = new JTextField();
		newSurnameInput = new JTextField();
		newAddressInput = new JTextField();
		newZipcodeInput = new JTextField();
		newCityInput = new JTextField();
		newDateOfBirthInput = new JTextField("dd-mm-åååå");
		newEmailInput = new JTextField();
		newMobileInput = new JTextField();
		newPrivateInput = new JTextField();
		newWorkInput = new JTextField();

		newGenderInput = new JComboBox(new Object[] { " ( Velg ) ", "Kvinne",
				"Mann" });
		newGenderInput.setSelectedIndex(0);

		newMembershipTypeInput = new JComboBox(new Object[] { " ( Velg ) ",
				"Vanlig medlem", "Støttemedlem" });
		newMembershipTypeInput.setSelectedIndex(0);

		// add keylistener
		newFirstnameInput.addKeyListener(this);
		newSurnameInput.addKeyListener(this);
		newAddressInput.addKeyListener(this);
		newZipcodeInput.addKeyListener(this);
		newCityInput.addKeyListener(this);
		newDateOfBirthInput.addKeyListener(this);
		newEmailInput.addKeyListener(this);
		newMobileInput.addKeyListener(this);
		newPrivateInput.addKeyListener(this);
		newWorkInput.addKeyListener(this);

		// create buttons and add listeners
		OKnewMember = new JButton("Lagre");
		OKnewMember.addActionListener(this);
		OKnewMember.addKeyListener(this);

		cancelnewMember = new JButton("Avbryt");
		cancelnewMember.addActionListener(this);
		cancelnewMember.addKeyListener(this);

		newMemberUploadImage = new JButton("Last opp fil...");
		newMemberUploadImage.addActionListener(this);
		newMemberUploadImage.addKeyListener(this);

		// get image
		newImageURL = "Image/Unknown.jpg";
		newMemberImage = new Picture(newImageURL, DEFAULT_IMAGE_WIDTH,
				DEFAULT_IMAGE_HEIGTH);

		// create help-variables and add labels to panel
		int x = 10;
		int y = 10;
		int w = fontmetrics.stringWidth("*") * 15 + 20
				+ fontmetrics.stringWidth("Status")
				+ (fontmetrics.stringWidth(" ( Velg ) ") * 2);
		int h = getFontMetrics(newTitle.getFont()).getHeight() + 4;

		newGenderInput.setSize(w - (fontmetrics.stringWidth("*") * 16)
				- fontmetrics.stringWidth(newGenderLabel.getText()) - 20, h);

		// add labels to panel
		newMemberPanel.add(newTitle, new Constraints(new Leading(x, 10, 10),
				new Leading(y, 12, 12)));

		y += 10 + getFontMetrics(newTitle.getFont()).getHeight();

		newMemberPanel.add(newMemberImage, new Constraints(new Leading(x,
				DEFAULT_IMAGE_WIDTH, 130, 130), new Leading(y,
				DEFAULT_IMAGE_HEIGTH, 170, 170)));

		y = 30 + DEFAULT_IMAGE_HEIGTH + new JTextField().getInsets().top
				+ getFontMetrics(newTitle.getFont()).getHeight();

		newMemberPanel.add(newImageRemove, new Constraints(new Leading(x,
				longestLabel, 10, 10), new Leading(y, 12, 12)));

		y += 10 + getFontMetrics(newImageRemove.getFont()).getHeight();

		newMemberPanel.add(newFirstnameLabel, new Constraints(new Leading(x,
				longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		newMemberPanel.add(newSurnameLabel, new Constraints(new Leading(x,
				longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		newMemberPanel.add(newAddressLabel, new Constraints(new Leading(x,
				longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		newMemberPanel.add(newZipcodeLabel, new Constraints(new Leading(x,
				longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		newMemberPanel.add(newCityLabel, new Constraints(new Leading(x
				+ longestLabel + (fontmetrics.stringWidth("*") * 8) + 20,
				fontmetrics.stringWidth(newCityLabel.getText()), 10, 10),
				new Leading(y, h, 12, 12)));

		y += 10 + h;

		newMemberPanel.add(newDateOfBirthLabel, new Constraints(new Leading(x,
				longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		newMemberPanel.add(newGenderLabel, new Constraints(new Leading(x
				+ longestLabel + (fontmetrics.stringWidth("*") * 16) + 20,
				fontmetrics.stringWidth(newGenderLabel.getText()), 10, 10),
				new Leading(y, h, 12, 12)));

		y += 10 + h;

		newMemberPanel.add(newMembershipTypeLabel, new Constraints(new Leading(
				x, longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		newMemberPanel.add(newEmailLabel, new Constraints(new Leading(x,
				longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		newMemberPanel.add(newMobileLabel, new Constraints(new Leading(x,
				longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		newMemberPanel.add(newPrivateLabel, new Constraints(new Leading(x,
				longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		newMemberPanel.add(newWorkLabel, new Constraints(new Leading(x,
				longestLabel, 10, 10), new Leading(y, h, 12, 12)));

		// add input to panel
		x += 10 + longestLabel;
		y = 25 + getFontMetrics(newTitle.getFont()).getHeight();

		newMemberPanel.add(newMemberUploadImage, new Constraints(new Leading(
				20 + DEFAULT_IMAGE_WIDTH, 10, 10), new Leading(y, 12, 12)));

		x = 20 + longestLabel;
		y = 40 + DEFAULT_IMAGE_HEIGTH + new JTextField().getInsets().top
				+ getFontMetrics(newTitle.getFont()).getHeight()
				+ getFontMetrics(newImageRemove.getFont()).getHeight();

		newMemberPanel.add(newFirstnameInput, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		newMemberPanel.add(newSurnameInput, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		newMemberPanel.add(newAddressInput, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		newMemberPanel.add(newZipcodeInput, new Constraints(new Leading(x,
				fontmetrics.stringWidth("*") * 8, 10, 10), new Leading(y, h,
				12, 12)));

		newMemberPanel.add(
				newCityInput,
				new Constraints(new Leading(x
						+ (fontmetrics.stringWidth("*") * 8)
						+ fontmetrics.stringWidth(newCityLabel.getText()) + 20,
						w
								- (fontmetrics.stringWidth("*") * 8)
								- fontmetrics.stringWidth(newCityLabel
										.getText()) - 20, 10, 10), new Leading(
						y, h, 12, 12)));

		y += 10 + h;

		newMemberPanel.add(newDateOfBirthInput, new Constraints(new Leading(x,
				fontmetrics.stringWidth("*") * 16, 10, 10), new Leading(y, h,
				12, 12)));

		newMemberPanel.add(
				newGenderInput,
				new Constraints(new Leading(x
						+ (fontmetrics.stringWidth("*") * 16)
						+ fontmetrics.stringWidth(newGenderLabel.getText())
						+ 20, newGenderInput.getWidth(), 10, 10), new Leading(
						y, h, 12, 12)));

		y += 10 + h;

		newMemberPanel.add(newMembershipTypeInput, new Constraints(new Leading(
				x, w, 10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		newMemberPanel.add(newEmailInput, new Constraints(new Leading(x, w, 10,
				10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		newMemberPanel.add(newMobileInput, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		newMemberPanel.add(newPrivateInput, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 12, 12)));

		y += 10 + h;

		newMemberPanel.add(newWorkInput, new Constraints(new Leading(x, w, 10,
				10), new Leading(y, h, 12, 12)));

		y += 40 + (h * 2);

		// add buttons to panel
		newMemberPanel.add(cancelnewMember, new Constraints(new Trailing(10,
				fontmetrics.stringWidth("Avbryt")
						+ (new JButton().getInsets().left * 2), 10, 10),
				new Trailing(10, h, 12, 12)));

		newMemberPanel.add(
				OKnewMember,
				new Constraints(new Trailing(20
						+ fontmetrics.stringWidth("Avbryt")
						+ (new JButton().getInsets().left * 2), fontmetrics
						.stringWidth("Avbryt")
						+ (new JButton().getInsets().left * 2), 10, 10),
						new Trailing(10, h, 12, 12)));

		// set panel size
		newMemberPanel.setSize(longestLabel + w + 30 > DEFAULT_PANEL_W
				- mainMemberSidePanel.getWidth() ? longestLabel + w + 30
				: DEFAULT_PANEL_W - mainMemberSidePanel.getWidth(),
				y > DEFAULT_PANEL_H ? y : DEFAULT_PANEL_H);
	}

	public void createMemberResultPanel(JTable table) {
		// create panel
		memberResultPanel = new JPanel();
		memberResultPanel.setLayout(new GroupLayout());
		memberResultPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		memberResultPanel.addKeyListener(this);

		// create title label
		memberResultTitleLabel = new JLabel("Resultater fra medlemssøk");
		memberResultTitleLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize() + 1));
		memberResultTitleLabel.setForeground(c.user.getForegroundColor()
				.darker());

		if (table != null) {
			// create memberresult table and customize
			memberResultTable = table;
			memberResultTable.setAutoCreateRowSorter(true);
			memberResultTable.getTableHeader().setReorderingAllowed(false);
			memberResultTable.getTableHeader().setFont(c.user.getFont());
			memberResultTable.getTableHeader().setForeground(
					c.user.getForegroundColor());
			memberResultTable.getTableHeader().setBackground(
					c.user.getBackgroundColor().darker());

			memberResultTableWidth = 0;
			autoResizeColWidth(memberResultTable, "memberresult");
			memberResultTable.setRowHeight(fontmetrics.getHeight() + 4);

			memberResultTable.getSelectionModel().addListSelectionListener(
					new ListSelectionListener() {

						public void valueChanged(ListSelectionEvent e) {
							memberResultTable.setRowSelectionAllowed(true);
							int row = memberResultTable.getSelectedRow();
							int column = 0;

							while (!memberResultTable.getColumnName(column)
									.equals(" ID "))
								column++;

							if (msg.confirmDialog("Gå til medlem "
									+ memberResultTable.getValueAt(row, column)
											.toString().trim() + "?") == 0) {
								String termDate = c.getMember(
										Integer.parseInt(memberResultTable
												.getValueAt(row, column)
												.toString().trim()))
										.getTerminationDate();

								Calendar calendar = Calendar.getInstance();
								Calendar memberDate = Calendar.getInstance();
								memberDate.set(Integer.parseInt(termDate
										.substring(6)),
										Integer.parseInt(termDate.substring(3,
												5)) - 1, Integer
												.parseInt(termDate.substring(0,
														2)));
								calendar.set(calendar.get(Calendar.YEAR),
										calendar.get(Calendar.MONTH),
										calendar.get(Calendar.DAY_OF_MONTH));

								if (calendar.before(memberDate)) {
									hideAllPanels();
									panel.removeAll();
									showMemberPanel.removeAll();
									memberSidePanel.removeAll();
									createMemberSidePanel();
									createShowMember(c.getMember(Integer
											.parseInt(memberResultTable
													.getValueAt(row, column)
													.toString().trim())));

									panel.add(
											memberSidePanel,
											new Constraints(
													new Leading(
															0,
															memberSidePanel
																	.getWidth(),
															10, 10),
													new Leading(
															0,
															memberSidePanel
																	.getHeight(),
															12, 12)));

									panel.add(
											showMemberPanel,
											new Constraints(
													new Leading(
															position
																	+ DEFAULT_PANEL_X,
															showMemberPanel
																	.getWidth(),
															10, 10),
													new Leading(
															DEFAULT_PANEL_Y,
															showMemberPanel
																	.getHeight(),
															10, 10)));
								} else {
									hideAllPanels();
									panel.removeAll();
									showMemberPanel.removeAll();
									mainMemberSidePanel.removeAll();
									createMainMemberSidePanel();
									createShowMember(c.getMember(Integer
											.parseInt(memberResultTable
													.getValueAt(row, column)
													.toString().trim())));

									panel.add(
											mainMemberSidePanel,
											new Constraints(
													new Leading(
															0,
															mainMemberSidePanel
																	.getWidth(),
															10, 10),
													new Leading(
															0,
															mainMemberSidePanel
																	.getHeight(),
															12, 12)));

									panel.add(
											showMemberPanel,
											new Constraints(
													new Leading(
															position
																	+ DEFAULT_PANEL_X,
															showMemberPanel
																	.getWidth(),
															10, 10),
													new Leading(
															DEFAULT_PANEL_Y,
															showMemberPanel
																	.getHeight(),
															10, 10)));
								}
							}

						}
					});

			// set table size and add to panel
			memberResultTable.setSize(
					memberResultTableWidth,
					memberResultTable.getRowHeight()
							* (memberResultTable.getRowCount() + 1));

			memberResultScroll = new JScrollPane(memberResultTable);
			memberResultScroll.setBackground(c.user.getBackgroundColor()
					.brighter());

			JPanel p = new JPanel();
			p.setLayout(new BorderLayout());
			p.add(memberResultScroll, BorderLayout.PAGE_START);

			memberResultPanel.add(memberResultTitleLabel, new Constraints(
					new Leading(10, 10, 10), new Leading(10, 10, 10)));

			memberResultPanel.add(p, new Constraints(new Leading(10,
					memberResultTableWidth, 10, 10), new Leading(
					20 + fontmetrics.getHeight(),
					memberResultTable.getHeight(), 10, 10)));

			// set panel size
			memberResultPanel
					.setSize(
							DEFAULT_PANEL_W - mainMemberSidePanel.getWidth() > memberResultTableWidth + 20 ? DEFAULT_PANEL_W
									- mainMemberSidePanel.getWidth()
									: memberResultTableWidth + 20,
							DEFAULT_PANEL_H > memberResultTable.getHeight() + 20 ? DEFAULT_PANEL_H
									: memberResultTable.getHeight() + 20);
		}
	}

	public void createMainActivitySidePanel() {
		// create panel
		mainActivitySidePanel = new JPanel();
		mainActivitySidePanel.setLayout(new GroupLayout());
		mainActivitySidePanel.setBackground(c.user.getBackgroundColor()
				.darker());
		mainActivitySidePanel.addKeyListener(this);

		mainActivitySidePanel.setBorder(BorderFactory.createTitledBorder(null,
				"AKTIVITETSMENY", TitledBorder.LEADING,
				TitledBorder.DEFAULT_POSITION, null,
				c.user.getForegroundColor()));

		// create buttons and add listeners
		mainPageFromMainActivity = new JButton("Hovedside");
		mainPageFromMainActivity.addActionListener(this);
		mainPageFromMainActivity.addKeyListener(this);
		int longestButton = fontmetrics.stringWidth(mainPageFromMainActivity
				.getText());

		mainActivityPageFromMainActivitySidePanel = new JButton(
				"Aktivitetssiden");
		mainActivityPageFromMainActivitySidePanel.addActionListener(this);
		mainActivityPageFromMainActivitySidePanel.addKeyListener(this);
		longestButton = longestButton > fontmetrics
				.stringWidth(mainActivityPageFromMainActivitySidePanel
						.getText()) ? longestButton : fontmetrics
				.stringWidth(mainActivityPageFromMainActivitySidePanel
						.getText());

		addActivity = new JButton("Legg til aktivitet");
		addActivity.addActionListener(this);
		addActivity.addKeyListener(this);
		longestButton = longestButton > fontmetrics.stringWidth(addActivity
				.getText()) ? longestButton : fontmetrics
				.stringWidth(addActivity.getText());

		searchActivity = new JButton("Søk etter aktivitet");
		searchActivity.addActionListener(this);
		searchActivity.addKeyListener(this);
		longestButton = longestButton > fontmetrics.stringWidth(searchActivity
				.getText()) ? longestButton : fontmetrics
				.stringWidth(searchActivity.getText());

		// create help-variables
		int buttonH = fontmetrics.getHeight()
				+ (new JButton().getInsets().bottom * 2);
		int buttonW = longestButton + (new JButton().getInsets().left * 2);
		int heigth = 20;

		// add to panel
		mainActivitySidePanel.add(mainPageFromMainActivity, new Constraints(
				new Leading(10, buttonW, 10, 10), new Leading(heigth, buttonH,
						12, 12)));

		heigth += 10 + buttonH;

		mainActivitySidePanel.add(mainActivityPageFromMainActivitySidePanel,
				new Constraints(new Leading(10, buttonW, 10, 10), new Leading(
						heigth, buttonH, 12, 12)));

		heigth += 10 + buttonH;

		mainActivitySidePanel.add(addActivity, new Constraints(new Leading(10,
				buttonW, 10, 10), new Leading(heigth, buttonH, 12, 12)));

		heigth += 10 + buttonH;

		mainActivitySidePanel.add(searchActivity, new Constraints(new Leading(
				10, buttonW, 10, 10), new Leading(heigth, buttonH, 12, 12)));

		// set sidepanel size
		mainActivitySidePanel.setSize(buttonW + 30, DEFAULT_HEIGTH);

	}

	public void createMainActivityPanel() {
		// create panel
		mainActivityPanel = new JPanel();
		mainActivityPanel.setLayout(new GroupLayout());
		mainActivityPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		mainActivityPanel.addKeyListener(this);

		mainPanel.removeAll();
		createActivityLogPanel();

		// add to panel
		mainActivityPanel.add(activityLogPanel,
				new Constraints(new Leading(10, activityLogMainTableWidth, 10,
						10), new Leading(10, activityLogMainTable.getHeight()
						+ activityLogMainTable.getRowHeight(), 10, 10)));
		// set panel size
		mainActivityPanel
				.setSize(
						DEFAULT_PANEL_W - mainActivitySidePanel.getWidth() > activityLogMainTableWidth ? DEFAULT_PANEL_W
								- mainActivitySidePanel.getWidth()
								: activityLogMainTableWidth,
						DEFAULT_PANEL_H > activityLogMainTable.getHeight() ? DEFAULT_PANEL_H
								: activityLogMainTable.getHeight());
	}

	public void createActivitySidePanel() {
		// create panel
		activitySidePanel = new JPanel();
		activitySidePanel.setLayout(new GroupLayout());
		activitySidePanel.setBackground(c.user.getBackgroundColor().darker());
		activitySidePanel.addKeyListener(this);

		String title = "AKTIVITETSDETALJER";
		activitySidePanel.setBorder(BorderFactory.createTitledBorder(null,
				title, TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
				null, c.user.getForegroundColor()));

		// create buttons and add listeners
		mainPageFromActivitySidePanel = new JButton("Hovedmeny");
		mainPageFromActivitySidePanel.addActionListener(this);
		mainPageFromActivitySidePanel.addKeyListener(this);
		int longestButton = fontmetrics
				.stringWidth(mainPageFromActivitySidePanel.getText());

		mainActivityPageFromActivitySidePanel = new JButton("Aktivitetsmeny");
		mainActivityPageFromActivitySidePanel.addActionListener(this);
		mainActivityPageFromActivitySidePanel.addKeyListener(this);
		if (longestButton < fontmetrics
				.stringWidth(mainActivityPageFromActivitySidePanel.getText()))
			longestButton = fontmetrics
					.stringWidth(mainActivityPageFromActivitySidePanel
							.getText());

		regInterest = new JButton("Oppmelding");
		regInterest.addActionListener(this);
		regInterest.addKeyListener(this);
		if (longestButton < fontmetrics.stringWidth(regInterest.getText()))
			longestButton = fontmetrics.stringWidth(regInterest.getText());

		removeInterest = new JButton("Fjern oppmelding");
		removeInterest.addActionListener(this);
		removeInterest.addKeyListener(this);
		if (longestButton < fontmetrics.stringWidth(removeInterest.getText()))
			longestButton = fontmetrics.stringWidth(removeInterest.getText());

		regAttendance = new JButton("Registrer oppmøte");
		regAttendance.addActionListener(this);
		regAttendance.addKeyListener(this);
		if (longestButton < fontmetrics.stringWidth(regAttendance.getText()))
			longestButton = fontmetrics.stringWidth(regAttendance.getText());

		editActivity = new JButton("Endre aktivitet");
		editActivity.addActionListener(this);
		editActivity.addKeyListener(this);
		if (longestButton < fontmetrics.stringWidth(editActivity.getText()))
			longestButton = fontmetrics.stringWidth(editActivity.getText());

		terminateActivity = new JButton("Terminer aktivitet");
		terminateActivity.addActionListener(this);
		terminateActivity.addKeyListener(this);
		if (longestButton < fontmetrics
				.stringWidth(terminateActivity.getText()))
			longestButton = fontmetrics
					.stringWidth(terminateActivity.getText());

		// create help-variables
		int buttonH = fontmetrics.getHeight()
				+ (new JButton().getInsets().bottom * 2);
		int buttonW = longestButton + (new JButton().getInsets().left * 2);
		int heigth = 20;

		// add to panel
		activitySidePanel.add(mainPageFromActivitySidePanel, new Constraints(
				new Leading(10, buttonW, 10, 10), new Leading(heigth, 10, 10)));

		heigth += 10 + buttonH;

		activitySidePanel.add(mainActivityPageFromActivitySidePanel,
				new Constraints(new Leading(10, buttonW, 10, 10), new Leading(
						heigth, 10, 10)));

		heigth += 10 + buttonH;

		activitySidePanel.add(regInterest, new Constraints(new Leading(10,
				buttonW, 10, 10), new Leading(heigth, 10, 10)));

		heigth += 10 + buttonH;

		activitySidePanel.add(removeInterest, new Constraints(new Leading(10,
				buttonW, 10, 10), new Leading(heigth, 10, 10)));

		heigth += 10 + buttonH;

		activitySidePanel.add(regAttendance, new Constraints(new Leading(10,
				buttonW, 10, 10), new Leading(heigth, 10, 10)));

		heigth += 10 + buttonH;

		activitySidePanel.add(editActivity, new Constraints(new Leading(10,
				buttonW, 10, 10), new Leading(heigth, 10, 10)));

		heigth += 10 + buttonH;

		activitySidePanel.add(terminateActivity, new Constraints(new Leading(
				10, buttonW, 10, 10), new Leading(heigth, 10, 10)));

		// set sidepanel size
		activitySidePanel
				.setSize(
						buttonW + 30 > fontmetrics.stringWidth(title.trim()) + 20 ? buttonW + 30
								: fontmetrics.stringWidth(title.trim()) + 20,
						DEFAULT_HEIGTH);
	}

	public void createAddActivityPanel() {
		FontMetrics fontmetrics = getFontMetrics(new Font(c.user.getFamily(),
				1, c.user.getSize()));
		// create panel
		addActivityPanel = new JPanel();
		addActivityPanel.setLayout(new GroupLayout());
		addActivityPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		addActivityPanel.addKeyListener(this);

		// create labels
		addActivityTitleLabel = new JLabel("Opprett en ny aktivitet");
		addActivityTitleLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize() + 1));
		addActivityTitleLabel.setForeground(c.user.getForegroundColor()
				.darker());

		addANameLabel = new JLabel("Navn på aktivitet ");
		addANameLabel
				.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		int longestLabel = fontmetrics.stringWidth(addANameLabel.getText());

		addAManagerLabel = new JLabel("Ansvarshavende ");
		addAManagerLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(addAManagerLabel.getText()))
			longestLabel = fontmetrics.stringWidth(addAManagerLabel.getText());

		addAAdressLabel = new JLabel("Adresse ");
		addAAdressLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(addAAdressLabel.getText()))
			longestLabel = fontmetrics.stringWidth(addAAdressLabel.getText());

		addAZipcodeLabel = new JLabel("Postnr. ");
		addAZipcodeLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(addAZipcodeLabel.getText()))
			longestLabel = fontmetrics.stringWidth(addAZipcodeLabel.getText());

		addACityLabel = new JLabel("Poststed ");
		addACityLabel
				.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));

		addASeatsLabel = new JLabel("Antall plasser");
		addASeatsLabel
				.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		if (longestLabel < fontmetrics.stringWidth(addASeatsLabel.getText()))
			longestLabel = fontmetrics.stringWidth(addASeatsLabel.getText());

		addADurationLabel = new JLabel("Varighet ");
		addADurationLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(addADurationLabel.getText()))
			longestLabel = fontmetrics.stringWidth(addADurationLabel.getText());

		addATime = new JLabel("Tidspunkt ");
		addATime.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		if (longestLabel < fontmetrics.stringWidth(addATime.getText()))
			longestLabel = fontmetrics.stringWidth(addATime.getText());

		addADescriptionLabel = new JLabel("Beskrivelse ");
		addADescriptionLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(addADescriptionLabel
				.getText()))
			longestLabel = fontmetrics.stringWidth(addADescriptionLabel
					.getText());

		// create inputfields
		addANameInput = new JTextField();
		addAManagerInput = new JTextField();
		addAAdressInput = new JTextField();
		addAZipcodeInput = new JTextField();
		addACityInput = new JTextField();
		addASeatsInput = new JTextField();
		addADurationStartInput = new JTextField();
		addADurationEndInput = new JTextField();
		addAstartCal = new Picture("Image/Calendar.png", getFontMetrics(
				addActivityTitleLabel.getFont()).getHeight() + 4,
				getFontMetrics(addActivityTitleLabel.getFont()).getHeight() + 4);
		addAstartCal.setToolTipText("Kalender");
		addAendCal = new Picture("Image/Calendar.png", getFontMetrics(
				addActivityTitleLabel.getFont()).getHeight() + 4,
				getFontMetrics(addActivityTitleLabel.getFont()).getHeight() + 4);
		addAendCal.setToolTipText("Kalender");
		addAallTF = new JTextField();
		addAallTT = new JTextField();
		addAmondayTF = new JTextField();
		addAmondayTT = new JTextField();
		addAtuesdayTF = new JTextField();
		addAtuesdayTT = new JTextField();
		addAwednesdayTF = new JTextField();
		addAwednesdayTT = new JTextField();
		addAthursdayTF = new JTextField();
		addAthursdayTT = new JTextField();
		addAfridayTF = new JTextField();
		addAfridayTT = new JTextField();
		addAsaturdayTF = new JTextField();
		addAsaturdayTT = new JTextField();
		addAsundayTF = new JTextField();
		addAsundayTT = new JTextField();
		addAuploadPath = new JTextField();

		addADescriptionInput = new JTextArea(5, 1);
		addADescriptionInput.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		addADescriptionInput.setBackground(Color.WHITE);

		addADescriptionInput.setForeground(Color.BLACK);

		addAallCB = new JLabel("Alle dager");
		addAmondayCB = new JLabel("Mandag");
		addAtuesdayCB = new JLabel("Tirsdag");
		addAwednesdayCB = new JLabel("Onsdag");
		addAthursdayCB = new JLabel("Torsdag");
		addAfridayCB = new JLabel("Fredag");
		addAsaturdayCB = new JLabel("Lørdag");
		addAsundayCB = new JLabel("Søndag");

		// create buttons
		addAok = new JButton("Lagre");
		addAcancel = new JButton("Avbryt");
		addAuploadFile = new JButton("Last opp fil");
		if (longestLabel < fontmetrics.stringWidth(addAuploadFile.getText())
				+ (new JButton().getInsets().left * 2))
			longestLabel = fontmetrics.stringWidth(addAuploadFile.getText())
					+ (new JButton().getInsets().left * 2);

		// add actionlistener
		addAok.addActionListener(this);
		addAcancel.addActionListener(this);
		addAuploadFile.addActionListener(this);

		// add mouselistener
		addAstartCal.addMouseListener(new Hyperlink());
		addAendCal.addMouseListener(new Hyperlink());

		// add keylistener
		addANameInput.addKeyListener(this);
		addAManagerInput.addKeyListener(this);
		addAAdressInput.addKeyListener(this);
		addAZipcodeInput.addKeyListener(this);
		addACityInput.addKeyListener(this);
		addASeatsInput.addKeyListener(this);
		addADurationStartInput.addKeyListener(this);
		addADurationEndInput.addKeyListener(this);
		addAallTF.addKeyListener(this);
		addAallTT.addKeyListener(this);
		addAmondayTF.addKeyListener(this);
		addAmondayTT.addKeyListener(this);
		addAtuesdayTF.addKeyListener(this);
		addAtuesdayTT.addKeyListener(this);
		addAwednesdayTF.addKeyListener(this);
		addAwednesdayTT.addKeyListener(this);
		addAthursdayTF.addKeyListener(this);
		addAthursdayTT.addKeyListener(this);
		addAfridayTF.addKeyListener(this);
		addAfridayTT.addKeyListener(this);
		addAsaturdayTF.addKeyListener(this);
		addAsaturdayTT.addKeyListener(this);
		addAsundayTF.addKeyListener(this);
		addAsundayTT.addKeyListener(this);

		// create help-variables and add to panel
		int w = longestLabel + 10;
		int h = getFontMetrics(addActivityTitleLabel.getFont()).getHeight() + 4;
		int x = 10;
		int y = 10;

		// add labels to panel
		addActivityPanel.add(addActivityTitleLabel, new Constraints(
				new Leading(x, 10, 10), new Leading(y, 10, 10)));

		y += 10 + h;

		addActivityPanel.add(addANameLabel, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		addActivityPanel.add(addAManagerLabel, new Constraints(new Leading(x,
				w, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		addActivityPanel.add(addAAdressLabel, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		addActivityPanel.add(addAZipcodeLabel, new Constraints(new Leading(x,
				w, 10, 10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(addACityLabel, new Constraints(new Leading(x + w
				+ (fontmetrics.stringWidth("*") * 8) + 10, 10, 10),
				new Leading(y, h, 10, 10)));

		y += 10 + h;

		addActivityPanel.add(addASeatsLabel, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		addActivityPanel.add(addADurationLabel, new Constraints(new Leading(x,
				w, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		addActivityPanel.add(addATime, new Constraints(
				new Leading(x, w, 10, 10), new Leading(y, h, 10, 10)));

		x += w;
		w = fontmetrics.stringWidth("*") * 40;
		y = 20 + h;

		// add input to panel
		addActivityPanel.add(addANameInput, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		addActivityPanel.add(addAManagerInput, new Constraints(new Leading(x,
				w, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		addActivityPanel.add(addAAdressInput, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		addActivityPanel.add(addAZipcodeInput, new Constraints(new Leading(x,
				(fontmetrics.stringWidth("*") * 8), 10, 10), new Leading(y, h,
				10, 10)));

		addActivityPanel.add(
				addACityInput,
				new Constraints(new Leading(x
						+ fontmetrics.stringWidth(addACityLabel.getText())
						+ (fontmetrics.stringWidth("*") * 8) + 20,
						fontmetrics.stringWidth("*")
								* 32
								- fontmetrics.stringWidth(addACityLabel
										.getText()) - 20, 10, 10), new Leading(
						y, h, 10, 10)));

		y += 10 + h;

		addActivityPanel.add(addASeatsInput, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		addActivityPanel.add(addADurationStartInput,
				new Constraints(
						new Leading(x, (int) (w / 2) - h - 5
								- (int) (fontmetrics.stringWidth(" - ") * 0.5),
								10, 10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(
				addAstartCal,
				new Constraints(new Leading(x
						+ ((int) (w / 2) - h - (int) (fontmetrics
								.stringWidth(" - ") * 0.5)), h, 10, 10),
						new Leading(y, h, 10, 10)));

		addActivityPanel.add(new JLabel(" - "), new Constraints(new Leading(x
				+ (int) (w / 2) - (int) (fontmetrics.stringWidth(" - ") * 0.5),
				10, 10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(
				addADurationEndInput,
				new Constraints(new Leading(x + (int) (w / 2)
						+ (int) (fontmetrics.stringWidth(" - ") * 0.5),
						(int) (w / 2) - h - 5
								- (int) (fontmetrics.stringWidth(" - ") * 0.5),
						10, 10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(addAendCal, new Constraints(new Leading(x + w - h,
				h, 10, 10), new Leading(y, h, 10, 10)));

		int timeW = fontmetrics.stringWidth(addAallCB.getText());
		y += 10 + h;

		addActivityPanel.add(addAallCB, new Constraints(new Leading(x, timeW,
				10, 10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(addAallTF, new Constraints(new Leading(x + timeW
				+ 10, (int) (fontmetrics.stringWidth("*") * 7.5), 10, 10),
				new Leading(y, h, 10, 10)));

		addActivityPanel.add(new JLabel(" - "), new Constraints(new Leading(x
				+ timeW + 10 + (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(
				addAallTT,
				new Constraints(new Leading(x + timeW + 10
						+ (int) (fontmetrics.stringWidth("*") * 7.5)
						+ fontmetrics.stringWidth(" - "), (int) (fontmetrics
						.stringWidth("*") * 7.5), 10, 10), new Leading(y, h,
						10, 10)));

		y += 10 + h;

		addActivityPanel.add(addAmondayCB, new Constraints(new Leading(x,
				timeW, 10, 10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(addAmondayTF, new Constraints(new Leading(x
				+ timeW + 10, (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(new JLabel(" - "), new Constraints(new Leading(x
				+ timeW + 10 + (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(
				addAmondayTT,
				new Constraints(new Leading(x + timeW + 10
						+ (int) (fontmetrics.stringWidth("*") * 7.5)
						+ fontmetrics.stringWidth(" - "), (int) (fontmetrics
						.stringWidth("*") * 7.5), 10, 10), new Leading(y, h,
						10, 10)));

		y += 10 + h;

		addActivityPanel.add(addAtuesdayCB, new Constraints(new Leading(x,
				timeW, 10, 10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(addAtuesdayTF, new Constraints(new Leading(x
				+ timeW + 10, (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(new JLabel(" - "), new Constraints(new Leading(x
				+ timeW + 10 + (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(
				addAtuesdayTT,
				new Constraints(new Leading(x + timeW + 10
						+ (int) (fontmetrics.stringWidth("*") * 7.5)
						+ fontmetrics.stringWidth(" - "), (int) (fontmetrics
						.stringWidth("*") * 7.5), 10, 10), new Leading(y, h,
						10, 10)));

		y += 10 + h;

		addActivityPanel.add(addAwednesdayCB, new Constraints(new Leading(x,
				timeW, 10, 10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(addAwednesdayTF, new Constraints(new Leading(x
				+ timeW + 10, (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(new JLabel(" - "), new Constraints(new Leading(x
				+ timeW + 10 + (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(
				addAwednesdayTT,
				new Constraints(new Leading(x + timeW + 10
						+ (int) (fontmetrics.stringWidth("*") * 7.5)
						+ fontmetrics.stringWidth(" - "), (int) (fontmetrics
						.stringWidth("*") * 7.5), 10, 10), new Leading(y, h,
						10, 10)));

		y += 10 + h;

		addActivityPanel.add(addAthursdayCB, new Constraints(new Leading(x,
				timeW, 10, 10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(addAthursdayTF, new Constraints(new Leading(x
				+ timeW + 10, (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(new JLabel(" - "), new Constraints(new Leading(x
				+ timeW + 10 + (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(
				addAthursdayTT,
				new Constraints(new Leading(x + timeW + 10
						+ (int) (fontmetrics.stringWidth("*") * 7.5)
						+ fontmetrics.stringWidth(" - "), (int) (fontmetrics
						.stringWidth("*") * 7.5), 10, 10), new Leading(y, h,
						10, 10)));

		y += 10 + h;

		addActivityPanel.add(addAfridayCB, new Constraints(new Leading(x,
				timeW, 10, 10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(addAfridayTF, new Constraints(new Leading(x
				+ timeW + 10, (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(new JLabel(" - "), new Constraints(new Leading(x
				+ timeW + 10 + (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(
				addAfridayTT,
				new Constraints(new Leading(x + timeW + 10
						+ (int) (fontmetrics.stringWidth("*") * 7.5)
						+ fontmetrics.stringWidth(" - "), (int) (fontmetrics
						.stringWidth("*") * 7.5), 10, 10), new Leading(y, h,
						10, 10)));

		y += 10 + h;

		addActivityPanel.add(addAsaturdayCB, new Constraints(new Leading(x,
				timeW, 10, 10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(addAsaturdayTF, new Constraints(new Leading(x
				+ timeW + 10, (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(new JLabel(" - "), new Constraints(new Leading(x
				+ timeW + 10 + (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(
				addAsaturdayTT,
				new Constraints(new Leading(x + timeW + 10
						+ (int) (fontmetrics.stringWidth("*") * 7.5)
						+ fontmetrics.stringWidth(" - "), (int) (fontmetrics
						.stringWidth("*") * 7.5), 10, 10), new Leading(y, h,
						10, 10)));

		y += 10 + h;

		addActivityPanel.add(addAsundayCB, new Constraints(new Leading(x,
				timeW, 10, 10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(addAsundayTF, new Constraints(new Leading(x
				+ timeW + 10, (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(new JLabel(" - "), new Constraints(new Leading(x
				+ timeW + 10 + (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(
				addAsundayTT,
				new Constraints(new Leading(x + timeW + 10
						+ (int) (fontmetrics.stringWidth("*") * 7.5)
						+ fontmetrics.stringWidth(" - "), (int) (fontmetrics
						.stringWidth("*") * 7.5), 10, 10), new Leading(y, h,
						10, 10)));

		y += 10 + h;

		addActivityPanel.add(new JScrollPane(addADescriptionInput),
				new Constraints(new Leading(x, w, 10, 10), new Leading(y, 10,
						10)));

		addActivityPanel.add(addADescriptionLabel, new Constraints(new Leading(
				10, w, 10, 10), new Leading(y, h, 10, 10)));

		y += (h * 5);

		addActivityPanel.add(addAuploadFile, new Constraints(new Leading(10,
				longestLabel, 10, 10), new Leading(y, h, 10, 10)));

		addActivityPanel.add(addAuploadPath, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 10, 10)));

		y += 40 + (h * 2);
		// add buttons to panel
		addActivityPanel.add(addAcancel, new Constraints(new Trailing(10,
				fontmetrics.stringWidth("Avbryt")
						+ (new JButton().getInsets().left * 2), 10, 10),
				new Trailing(10, h, 12, 12)));

		addActivityPanel.add(
				addAok,
				new Constraints(new Trailing(20
						+ fontmetrics.stringWidth("Avbryt")
						+ (new JButton().getInsets().left * 2), fontmetrics
						.stringWidth("Avbryt")
						+ (new JButton().getInsets().left * 2), 10, 10),
						new Trailing(10, h, 12, 12)));

		// set panel size
		addActivityPanel.setSize(longestLabel + w + 30 > DEFAULT_PANEL_W
				- mainActivitySidePanel.getWidth() ? longestLabel + w + 30
				: DEFAULT_PANEL_W - mainActivitySidePanel.getWidth(),
				y > DEFAULT_PANEL_H ? y : DEFAULT_PANEL_H);
	}

	public void createEditActivityPanel(Activity a) {
		FontMetrics fontmetrics = getFontMetrics(new Font(c.user.getFamily(),
				1, c.user.getSize()));
		// create panel
		editActivityPanel = new JPanel();
		editActivityPanel.setLayout(new GroupLayout());
		editActivityPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		editActivityPanel.addKeyListener(this);

		// create labels
		editActivityTitleLabel = new JLabel("Endre aktivitet: " + a.getName());
		editActivityTitleLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize() + 1));
		editActivityTitleLabel.setForeground(c.user.getForegroundColor()
				.darker());

		editANameLabel = new JLabel("Navn på aktivitet ");
		editANameLabel
				.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		int longestLabel = fontmetrics.stringWidth(editANameLabel.getText());

		editAManagerLabel = new JLabel("Ansvarshavende ");
		editAManagerLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(editAManagerLabel.getText()))
			longestLabel = fontmetrics.stringWidth(editAManagerLabel.getText());

		editAAdressLabel = new JLabel("Adresse ");
		editAAdressLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(editAAdressLabel.getText()))
			longestLabel = fontmetrics.stringWidth(editAAdressLabel.getText());

		editAZipcodeLabel = new JLabel("Postnr. ");
		editAZipcodeLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(editAZipcodeLabel.getText()))
			longestLabel = fontmetrics.stringWidth(editAZipcodeLabel.getText());

		editACityLabel = new JLabel("Poststed ");
		editACityLabel
				.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));

		editASeatsLabel = new JLabel("Antall plasser");
		editASeatsLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(editASeatsLabel.getText()))
			longestLabel = fontmetrics.stringWidth(editASeatsLabel.getText());

		editADurationLabel = new JLabel("Varighet ");
		editADurationLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics
				.stringWidth(editADurationLabel.getText()))
			longestLabel = fontmetrics
					.stringWidth(editADurationLabel.getText());

		editATime = new JLabel("Tidspunkt ");
		editATime.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		if (longestLabel < fontmetrics.stringWidth(editATime.getText()))
			longestLabel = fontmetrics.stringWidth(editATime.getText());

		editADescriptionLabel = new JLabel("Beskrivelse ");
		editADescriptionLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(editADescriptionLabel
				.getText()))
			longestLabel = fontmetrics.stringWidth(editADescriptionLabel
					.getText());

		// create inputfields
		editANameInput = new JTextField(a.getName());
		editAManagerInput = new JTextField(a.getManager());
		editAAdressInput = new JTextField(a.getAddress());
		editAZipcodeInput = new JTextField(a.getZipcode());
		editACityInput = new JTextField(a.getCity());
		editASeatsInput = new JTextField(a.getTotalSeats());
		editADurationStartInput = new JTextField(a.getStartDate());
		editADurationEndInput = new JTextField(a.getEndDate());
		editAstartCal = new Picture("Image/Calendar.png", getFontMetrics(
				addActivityTitleLabel.getFont()).getHeight() + 4,
				getFontMetrics(addActivityTitleLabel.getFont()).getHeight() + 4);
		editAstartCal.setToolTipText("Kalender");
		editAendCal = new Picture("Image/Calendar.png", getFontMetrics(
				addActivityTitleLabel.getFont()).getHeight() + 4,
				getFontMetrics(addActivityTitleLabel.getFont()).getHeight() + 4);
		editAendCal.setToolTipText("Kalender");

		Days[] editDays = a.getDays();
		if (editDays[0].getDay().equals("Alle dager")) {
			editAallTF = new JTextField(editDays[0].getStartTime());
			editAallTT = new JTextField(editDays[0].getEndTime());

			editAmondayTF = new JTextField();
			editAmondayTT = new JTextField();
			editAtuesdayTF = new JTextField();
			editAtuesdayTT = new JTextField();
			editAwednesdayTF = new JTextField();
			editAwednesdayTT = new JTextField();
			editAthursdayTF = new JTextField();
			editAthursdayTT = new JTextField();
			editAfridayTF = new JTextField();
			editAfridayTT = new JTextField();
			editAsaturdayTF = new JTextField();
			editAsaturdayTT = new JTextField();
			editAsundayTF = new JTextField();
			editAsundayTT = new JTextField();
		} else {
			editAallTF = new JTextField();
			editAallTT = new JTextField();

			editAmondayTF = new JTextField(editDays[0].getStartTime());
			editAmondayTT = new JTextField(editDays[0].getEndTime());
			editAtuesdayTF = new JTextField(editDays[1].getStartTime());
			editAtuesdayTT = new JTextField(editDays[1].getEndTime());
			editAwednesdayTF = new JTextField(editDays[2].getStartTime());
			editAwednesdayTT = new JTextField(editDays[2].getEndTime());
			editAthursdayTF = new JTextField(editDays[3].getStartTime());
			editAthursdayTT = new JTextField(editDays[3].getEndTime());
			editAfridayTF = new JTextField(editDays[4].getStartTime());
			editAfridayTT = new JTextField(editDays[4].getEndTime());
			editAsaturdayTF = new JTextField(editDays[5].getStartTime());
			editAsaturdayTT = new JTextField(editDays[5].getEndTime());
			editAsundayTF = new JTextField(editDays[6].getStartTime());
			editAsundayTT = new JTextField(editDays[6].getEndTime());
		}
		editAuploadPath = new JTextField(a.getPath());

		editADescriptionInput = new JTextArea(5, 1);
		editADescriptionInput.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		editADescriptionInput.setBackground(Color.WHITE);
		editADescriptionInput.setForeground(Color.BLACK);
		editADescriptionInput.setText(a.getDescription());

		editAallCB = new JLabel("Alle dager");
		editAmondayCB = new JLabel("Mandag");
		editAtuesdayCB = new JLabel("Tirsdag");
		editAwednesdayCB = new JLabel("Onsdag");
		editAthursdayCB = new JLabel("Torsdag");
		editAfridayCB = new JLabel("Fredag");
		editAsaturdayCB = new JLabel("Lørdag");
		editAsundayCB = new JLabel("Søndag");

		// create buttons
		editAok = new JButton("Lagre");
		editAcancel = new JButton("Avbryt");
		editAuploadFile = new JButton("Last opp fil");
		if (longestLabel < fontmetrics.stringWidth(editAuploadFile.getText())
				+ (new JButton().getInsets().left * 2))
			longestLabel = fontmetrics.stringWidth(editAuploadFile.getText())
					+ (new JButton().getInsets().left * 2);

		// edit actionlistener
		editAok.addActionListener(this);
		editAcancel.addActionListener(this);
		editAuploadFile.addActionListener(this);

		// edit mouselistener
		editAstartCal.addMouseListener(new Hyperlink());
		editAendCal.addMouseListener(new Hyperlink());

		// edit keylistener
		editANameInput.addKeyListener(this);
		editAManagerInput.addKeyListener(this);
		editAAdressInput.addKeyListener(this);
		editAZipcodeInput.addKeyListener(this);
		editACityInput.addKeyListener(this);
		editASeatsInput.addKeyListener(this);
		editADurationStartInput.addKeyListener(this);
		editADurationEndInput.addKeyListener(this);
		editAallTF.addKeyListener(this);
		editAallTT.addKeyListener(this);
		editAmondayTF.addKeyListener(this);
		editAmondayTT.addKeyListener(this);
		editAtuesdayTF.addKeyListener(this);
		editAtuesdayTT.addKeyListener(this);
		editAwednesdayTF.addKeyListener(this);
		editAwednesdayTT.addKeyListener(this);
		editAthursdayTF.addKeyListener(this);
		editAthursdayTT.addKeyListener(this);
		editAfridayTF.addKeyListener(this);
		editAfridayTT.addKeyListener(this);
		editAsaturdayTF.addKeyListener(this);
		editAsaturdayTT.addKeyListener(this);
		editAsundayTF.addKeyListener(this);
		editAsundayTT.addKeyListener(this);

		// create help-variables
		int w = longestLabel + 10;
		int h = getFontMetrics(editActivityTitleLabel.getFont()).getHeight() + 4;
		int x = 10;
		int y = 10;

		// add labels to panel
		editActivityPanel.add(editActivityTitleLabel, new Constraints(
				new Leading(x, 10, 10), new Leading(y, 10, 10)));

		y += 10 + h;

		editActivityPanel.add(editANameLabel, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		editActivityPanel.add(editAManagerLabel, new Constraints(new Leading(x,
				w, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		editActivityPanel.add(editAAdressLabel, new Constraints(new Leading(x,
				w, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		editActivityPanel.add(editAZipcodeLabel, new Constraints(new Leading(x,
				w, 10, 10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(editACityLabel, new Constraints(new Leading(x + w
				+ (fontmetrics.stringWidth("*") * 8) + 10, 10, 10),
				new Leading(y, h, 10, 10)));

		y += 10 + h;

		editActivityPanel.add(editASeatsLabel, new Constraints(new Leading(x,
				w, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		editActivityPanel.add(editADurationLabel, new Constraints(new Leading(
				x, w, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		editActivityPanel.add(editATime, new Constraints(new Leading(x, w, 10,
				10), new Leading(y, h, 10, 10)));

		x += w;
		w = fontmetrics.stringWidth("*") * 40;
		y = 20 + h;
		// add inputfields to panel
		editActivityPanel.add(editANameInput, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		editActivityPanel.add(editAManagerInput, new Constraints(new Leading(x,
				w, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		editActivityPanel.add(editAAdressInput, new Constraints(new Leading(x,
				w, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		editActivityPanel.add(editAZipcodeInput, new Constraints(new Leading(x,
				(fontmetrics.stringWidth("*") * 8), 10, 10), new Leading(y, h,
				10, 10)));

		editActivityPanel.add(
				editACityInput,
				new Constraints(new Leading(x
						+ fontmetrics.stringWidth(editACityLabel.getText())
						+ (fontmetrics.stringWidth("*") * 8) + 20, fontmetrics
						.stringWidth("*")
						* 32
						- fontmetrics.stringWidth(editACityLabel.getText())
						- 20, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		editActivityPanel.add(editASeatsInput, new Constraints(new Leading(x,
				w, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		editActivityPanel.add(editADurationStartInput,
				new Constraints(
						new Leading(x, (int) (w / 2) - h - 5
								- (int) (fontmetrics.stringWidth(" - ") * 0.5),
								10, 10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(
				editAstartCal,
				new Constraints(new Leading(x
						+ ((int) (w / 2) - h - (int) (fontmetrics
								.stringWidth(" - ") * 0.5)), h, 10, 10),
						new Leading(y, h, 10, 10)));

		editActivityPanel.add(new JLabel(" - "), new Constraints(new Leading(x
				+ (int) (w / 2) - (int) (fontmetrics.stringWidth(" - ") * 0.5),
				10, 10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(
				editADurationEndInput,
				new Constraints(new Leading(x + (int) (w / 2)
						+ (int) (fontmetrics.stringWidth(" - ") * 0.5),
						(int) (w / 2) - h - 5
								- (int) (fontmetrics.stringWidth(" - ") * 0.5),
						10, 10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(editAendCal, new Constraints(new Leading(x + w
				- h, h, 10, 10), new Leading(y, h, 10, 10)));

		int timeW = fontmetrics.stringWidth(editAallCB.getText());
		y += 10 + h;

		editActivityPanel.add(editAallCB, new Constraints(new Leading(x, timeW,
				10, 10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(editAallTF, new Constraints(new Leading(x + timeW
				+ 10, (int) (fontmetrics.stringWidth("*") * 7.5), 10, 10),
				new Leading(y, h, 10, 10)));

		editActivityPanel.add(new JLabel(" - "), new Constraints(new Leading(x
				+ timeW + 10 + (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(
				editAallTT,
				new Constraints(new Leading(x + timeW + 10
						+ (int) (fontmetrics.stringWidth("*") * 7.5)
						+ fontmetrics.stringWidth(" - "), (int) (fontmetrics
						.stringWidth("*") * 7.5), 10, 10), new Leading(y, h,
						10, 10)));

		y += 10 + h;

		editActivityPanel.add(editAmondayCB, new Constraints(new Leading(x,
				timeW, 10, 10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(editAmondayTF, new Constraints(new Leading(x
				+ timeW + 10, (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(new JLabel(" - "), new Constraints(new Leading(x
				+ timeW + 10 + (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(
				editAmondayTT,
				new Constraints(new Leading(x + timeW + 10
						+ (int) (fontmetrics.stringWidth("*") * 7.5)
						+ fontmetrics.stringWidth(" - "), (int) (fontmetrics
						.stringWidth("*") * 7.5), 10, 10), new Leading(y, h,
						10, 10)));

		y += 10 + h;

		editActivityPanel.add(editAtuesdayCB, new Constraints(new Leading(x,
				timeW, 10, 10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(editAtuesdayTF, new Constraints(new Leading(x
				+ timeW + 10, (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(new JLabel(" - "), new Constraints(new Leading(x
				+ timeW + 10 + (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(
				editAtuesdayTT,
				new Constraints(new Leading(x + timeW + 10
						+ (int) (fontmetrics.stringWidth("*") * 7.5)
						+ fontmetrics.stringWidth(" - "), (int) (fontmetrics
						.stringWidth("*") * 7.5), 10, 10), new Leading(y, h,
						10, 10)));

		y += 10 + h;

		editActivityPanel.add(editAwednesdayCB, new Constraints(new Leading(x,
				timeW, 10, 10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(editAwednesdayTF, new Constraints(new Leading(x
				+ timeW + 10, (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(new JLabel(" - "), new Constraints(new Leading(x
				+ timeW + 10 + (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(
				editAwednesdayTT,
				new Constraints(new Leading(x + timeW + 10
						+ (int) (fontmetrics.stringWidth("*") * 7.5)
						+ fontmetrics.stringWidth(" - "), (int) (fontmetrics
						.stringWidth("*") * 7.5), 10, 10), new Leading(y, h,
						10, 10)));

		y += 10 + h;

		editActivityPanel.add(editAthursdayCB, new Constraints(new Leading(x,
				timeW, 10, 10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(editAthursdayTF, new Constraints(new Leading(x
				+ timeW + 10, (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(new JLabel(" - "), new Constraints(new Leading(x
				+ timeW + 10 + (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(
				editAthursdayTT,
				new Constraints(new Leading(x + timeW + 10
						+ (int) (fontmetrics.stringWidth("*") * 7.5)
						+ fontmetrics.stringWidth(" - "), (int) (fontmetrics
						.stringWidth("*") * 7.5), 10, 10), new Leading(y, h,
						10, 10)));

		y += 10 + h;

		editActivityPanel.add(editAfridayCB, new Constraints(new Leading(x,
				timeW, 10, 10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(editAfridayTF, new Constraints(new Leading(x
				+ timeW + 10, (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(new JLabel(" - "), new Constraints(new Leading(x
				+ timeW + 10 + (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(
				editAfridayTT,
				new Constraints(new Leading(x + timeW + 10
						+ (int) (fontmetrics.stringWidth("*") * 7.5)
						+ fontmetrics.stringWidth(" - "), (int) (fontmetrics
						.stringWidth("*") * 7.5), 10, 10), new Leading(y, h,
						10, 10)));

		y += 10 + h;

		editActivityPanel.add(editAsaturdayCB, new Constraints(new Leading(x,
				timeW, 10, 10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(editAsaturdayTF, new Constraints(new Leading(x
				+ timeW + 10, (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(new JLabel(" - "), new Constraints(new Leading(x
				+ timeW + 10 + (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(
				editAsaturdayTT,
				new Constraints(new Leading(x + timeW + 10
						+ (int) (fontmetrics.stringWidth("*") * 7.5)
						+ fontmetrics.stringWidth(" - "), (int) (fontmetrics
						.stringWidth("*") * 7.5), 10, 10), new Leading(y, h,
						10, 10)));

		y += 10 + h;

		editActivityPanel.add(editAsundayCB, new Constraints(new Leading(x,
				timeW, 10, 10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(editAsundayTF, new Constraints(new Leading(x
				+ timeW + 10, (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(new JLabel(" - "), new Constraints(new Leading(x
				+ timeW + 10 + (int) (fontmetrics.stringWidth("*") * 7.5), 10,
				10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(
				editAsundayTT,
				new Constraints(new Leading(x + timeW + 10
						+ (int) (fontmetrics.stringWidth("*") * 7.5)
						+ fontmetrics.stringWidth(" - "), (int) (fontmetrics
						.stringWidth("*") * 7.5), 10, 10), new Leading(y, h,
						10, 10)));

		y += 10 + h;

		editActivityPanel.add(new JScrollPane(editADescriptionInput),
				new Constraints(new Leading(x, w, 10, 10), new Leading(y, 10,
						10)));

		editActivityPanel.add(editADescriptionLabel, new Constraints(
				new Leading(10, w, 10, 10), new Leading(y, h, 10, 10)));

		y += (h * 5);

		editActivityPanel.add(editAuploadFile, new Constraints(new Leading(10,
				longestLabel, 10, 10), new Leading(y, h, 10, 10)));

		editActivityPanel.add(editAuploadPath, new Constraints(new Leading(x,
				w, 10, 10), new Leading(y, h, 10, 10)));

		y += 40 + (h * 2);

		// add buttons to panel
		editActivityPanel.add(editAcancel, new Constraints(new Trailing(10,
				fontmetrics.stringWidth("Avbryt")
						+ (new JButton().getInsets().left * 2), 10, 10),
				new Trailing(10, h, 12, 12)));

		editActivityPanel.add(
				editAok,
				new Constraints(new Trailing(20
						+ fontmetrics.stringWidth("Avbryt")
						+ (new JButton().getInsets().left * 2), fontmetrics
						.stringWidth("Avbryt")
						+ (new JButton().getInsets().left * 2), 10, 10),
						new Trailing(10, h, 12, 12)));

		// set panel size
		editActivityPanel.setSize(longestLabel + w + 30 > DEFAULT_PANEL_W
				- mainActivitySidePanel.getWidth() ? longestLabel + w + 30
				: DEFAULT_PANEL_W - mainActivitySidePanel.getWidth(),
				y > DEFAULT_PANEL_H ? y : DEFAULT_PANEL_H);
	}

	public void createRegAttendancePanel() {

		FontMetrics fontmetrics = getFontMetrics(new Font(c.user.getFamily(),
				1, c.user.getSize()));

		// create panel
		regAttendancePanel = new JPanel();
		regAttendancePanel.setLayout(new GroupLayout());
		regAttendancePanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		regAttendancePanel.addKeyListener(this);

		// make the labels
		regAttTitleLabel = new JLabel("Legg til oppmøte for "
				+ showAtitleLabel.getText().trim());
		regAttTitleLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize() + 1));
		regAttTitleLabel.setForeground(c.user.getForegroundColor().darker());

		regAttDateLabel = new JLabel("Dato ");
		regAttDateLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		int longestLabel = fontmetrics.stringWidth(regAttDateLabel.getText());

		regAttNrLabel = new JLabel("Antall oppmøtte ");
		regAttNrLabel
				.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		if (longestLabel < fontmetrics.stringWidth(regAttNrLabel.getText()))
			longestLabel = fontmetrics.stringWidth(regAttNrLabel.getText());

		regAttCommentLabel = new JLabel("Oppsummering ");
		regAttCommentLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics
				.stringWidth(regAttCommentLabel.getText()))
			longestLabel = fontmetrics
					.stringWidth(regAttCommentLabel.getText());

		// make the input-fields
		regAttDateInput = new JTextField();
		regAttNrInput = new JTextField();
		regAttCommentInput = new JTextArea(5, 1);
		regAttCommentInput.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		regAttCommentInput.setBackground(Color.WHITE);
		regAttCommentInput.setForeground(Color.BLACK);

		// make the buttons
		regAttOK = new JButton("Lagre");
		regAttCancel = new JButton("Avbryt");

		// add actionlistener
		regAttOK.addActionListener(this);
		regAttCancel.addActionListener(this);

		// add keylistener
		regAttDateInput.addKeyListener(this);
		regAttNrInput.addKeyListener(this);

		// create help-variables
		int x = 10;
		int y = 10;
		int w = longestLabel + 10;
		int h = getFontMetrics(regAttTitleLabel.getFont()).getHeight() + 4;

		// make the datepicker
		regAttDatePicker = new Picture("Image/Calendar.png", h, h);
		regAttDatePicker.setToolTipText("Kalender");

		// add mouselistener
		regAttDatePicker.addMouseListener(new Hyperlink());

		// add to panel
		regAttendancePanel.add(regAttTitleLabel, new Constraints(new Leading(x,
				10, 10), new Leading(y, h, 10, 10)));

		y += 15 + h;

		regAttendancePanel.add(regAttDateLabel, new Constraints(new Leading(x,
				w, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		regAttendancePanel.add(regAttNrLabel, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		regAttendancePanel.add(regAttCommentLabel, new Constraints(new Leading(
				x, w, 10, 10), new Leading(y, h, 10, 10)));

		x += w;
		w = (fontmetrics.stringWidth("*") * 40) - h - 5;
		y = 25 + h;
		// add input to panel
		regAttendancePanel.add(regAttDateInput, new Constraints(new Leading(x,
				w, 10, 10), new Leading(y, h, 10, 10)));

		regAttendancePanel.add(regAttDatePicker, new Constraints(new Leading(x
				+ (w + 5), h, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		regAttendancePanel.add(regAttNrInput, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		regAttendancePanel.add(new JScrollPane(regAttCommentInput),
				new Constraints(new Leading(x, w, 10, 10), new Leading(y, 10,
						10)));

		y += 40 + (h * 10);

		regAttendancePanel.add(regAttCancel, new Constraints(new Trailing(10,
				fontmetrics.stringWidth("Avbryt")
						+ (new JButton().getInsets().left * 2), 10, 10),
				new Trailing(10, h, 12, 12)));

		regAttendancePanel.add(
				regAttOK,
				new Constraints(new Trailing(20
						+ fontmetrics.stringWidth("Avbryt")
						+ (new JButton().getInsets().left * 2), fontmetrics
						.stringWidth("Avbryt")
						+ (new JButton().getInsets().left * 2), 10, 10),
						new Trailing(10, h, 12, 12)));

		// set panelsize
		regAttendancePanel.setSize(
				DEFAULT_PANEL_W - activitySidePanel.getWidth() > longestLabel
						+ w + h + 40 ? DEFAULT_PANEL_W
						- activitySidePanel.getWidth() : longestLabel + w + h
						+ 40, DEFAULT_PANEL_H > y ? DEFAULT_PANEL_H : y);
	}

	public void createSearchActivityPanel() {
		FontMetrics fontmetrics = getFontMetrics(new Font(c.user.getFamily(),
				1, c.user.getSize()));
		// create panel
		searchActivityPanel = new JPanel();
		searchActivityPanel.setLayout(new GroupLayout());
		searchActivityPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		searchActivityPanel.addKeyListener(this);

		// create labels
		searchATitleLabel = new JLabel("Søk etter en aktvitet");
		searchATitleLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize() + 1));
		searchATitleLabel.setForeground(c.user.getForegroundColor().darker());

		searchANameLabel = new JLabel("Navn på aktivitet ");
		searchANameLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		int longestLabel = fontmetrics.stringWidth(searchANameLabel.getText());

		searchAManagerLabel = new JLabel("Ansvarshavende ");
		searchAManagerLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(searchAManagerLabel
				.getText()))
			longestLabel = fontmetrics.stringWidth(searchAManagerLabel
					.getText());

		searchAAdressLabel = new JLabel("Adresse ");
		searchAAdressLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics
				.stringWidth(searchAAdressLabel.getText()))
			longestLabel = fontmetrics
					.stringWidth(searchAAdressLabel.getText());

		searchAZipcodeLabel = new JLabel("Postnr. ");
		searchAZipcodeLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(searchAZipcodeLabel
				.getText()))
			longestLabel = fontmetrics.stringWidth(searchAZipcodeLabel
					.getText());

		searchACityLabel = new JLabel("Poststed ");
		searchACityLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));

		searchASeatsLabel = new JLabel("Antall plasser");
		searchASeatsLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(searchASeatsLabel.getText()))
			longestLabel = fontmetrics.stringWidth(searchASeatsLabel.getText());

		searchAstatusLabel = new JLabel("Status");
		searchAstatusLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));

		searchADurationLabel = new JLabel("Varighet ");
		searchADurationLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(searchADurationLabel
				.getText()))
			longestLabel = fontmetrics.stringWidth(searchADurationLabel
					.getText());

		searchADay = new JLabel("Tidspunkt ");
		searchADay.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		if (longestLabel < fontmetrics.stringWidth(searchADay.getText()))
			longestLabel = fontmetrics.stringWidth(searchADay.getText());

		searchAdocLabel = new JLabel("Dokumentnavn ");
		searchAdocLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(searchAdocLabel.getText()))
			longestLabel = fontmetrics.stringWidth(searchAdocLabel.getText());

		// create jtextfields
		searchANameInput = new JTextField();
		searchAManagerInput = new JTextField();
		searchAAdressInput = new JTextField();
		searchAZipcodeInput = new JTextField();
		searchACityInput = new JTextField();
		searchASeatsInput = new JTextField();
		searchADurationStartInput = new JTextField();
		searchADurationEndInput = new JTextField();
		searchAstartCal = new Picture("Image/Calendar.png", getFontMetrics(
				addActivityTitleLabel.getFont()).getHeight() + 4,
				getFontMetrics(addActivityTitleLabel.getFont()).getHeight() + 4);
		searchAstartCal.setToolTipText("Kalender");
		searchAendCal = new Picture("Image/Calendar.png", getFontMetrics(
				addActivityTitleLabel.getFont()).getHeight() + 4,
				getFontMetrics(addActivityTitleLabel.getFont()).getHeight() + 4);
		searchAendCal.setToolTipText("Kalender");
		searchAdocName = new JTextField();

		// create jcheckbox
		searchAallCB = new JCheckBox("Alle dager");
		searchAallCB.setBackground(c.user.getBackgroundColor());
		searchAallCB.setFont(c.user.getFont());
		searchAmondayCB = new JCheckBox("Mandag");
		searchAmondayCB.setBackground(c.user.getBackgroundColor());
		searchAmondayCB.setFont(c.user.getFont());
		searchAtuesdayCB = new JCheckBox("Tirsdag");
		searchAtuesdayCB.setBackground(c.user.getBackgroundColor());
		searchAtuesdayCB.setFont(c.user.getFont());
		searchAwednesdayCB = new JCheckBox("Onsdag");
		searchAwednesdayCB.setBackground(c.user.getBackgroundColor());
		searchAwednesdayCB.setFont(c.user.getFont());
		searchAthursdayCB = new JCheckBox("Torsdag");
		searchAthursdayCB.setBackground(c.user.getBackgroundColor());
		searchAthursdayCB.setFont(c.user.getFont());
		searchAfridayCB = new JCheckBox("Fredag");
		searchAfridayCB.setBackground(c.user.getBackgroundColor());
		searchAfridayCB.setFont(c.user.getFont());
		searchAsaturdayCB = new JCheckBox("Lørdag");
		searchAsaturdayCB.setBackground(c.user.getBackgroundColor());
		searchAsaturdayCB.setFont(c.user.getFont());
		searchAsundayCB = new JCheckBox("Søndag");
		searchAsundayCB.setBackground(c.user.getBackgroundColor());
		searchAsundayCB.setFont(c.user.getFont());

		// create jcombobox
		searchAstatusBox = new JComboBox(new Object[] { " ( Velg ) ", "Aktiv",
				"Inaktiv" });
		searchAstatusBox.setSelectedIndex(0);

		// create buttons
		searchAok = new JButton("Søk");
		searchAcancel = new JButton("Avbryt");

		// add actionlistener
		searchAok.addActionListener(this);
		searchAcancel.addActionListener(this);

		// add mouselistener
		searchAstartCal.addMouseListener(new Hyperlink());
		searchAendCal.addMouseListener(new Hyperlink());

		// add keylistener
		searchANameInput.addKeyListener(this);
		searchAManagerInput.addKeyListener(this);
		searchAAdressInput.addKeyListener(this);
		searchAZipcodeInput.addKeyListener(this);
		searchACityInput.addKeyListener(this);
		searchASeatsInput.addKeyListener(this);
		searchADurationStartInput.addKeyListener(this);
		searchADurationEndInput.addKeyListener(this);
		searchAdocName.addKeyListener(this);

		// create help-variables
		int w = longestLabel + 10;
		int h = getFontMetrics(searchATitleLabel.getFont()).getHeight() + 4;
		int x = 10;
		int y = 10;

		// add labels to panel
		searchActivityPanel.add(searchATitleLabel, new Constraints(new Leading(
				x, 10, 10), new Leading(y, 10, 10)));

		y += 10 + h;

		searchActivityPanel.add(searchANameLabel, new Constraints(new Leading(
				x, w, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		searchActivityPanel.add(searchAManagerLabel, new Constraints(
				new Leading(x, w, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		searchActivityPanel.add(searchAAdressLabel, new Constraints(
				new Leading(x, w, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		searchActivityPanel.add(searchAZipcodeLabel, new Constraints(
				new Leading(x, w, 10, 10), new Leading(y, h, 10, 10)));

		searchActivityPanel.add(searchACityLabel, new Constraints(new Leading(x
				+ w + (fontmetrics.stringWidth("*") * 8) + 10, 10, 10),
				new Leading(y, h, 10, 10)));

		y += 10 + h;

		searchActivityPanel.add(searchASeatsLabel, new Constraints(new Leading(
				x, w, 10, 10), new Leading(y, h, 10, 10)));

		searchActivityPanel.add(searchAstatusLabel, new Constraints(
				new Leading(x + w + (fontmetrics.stringWidth("*") * 8) + 10, w,
						10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		searchActivityPanel.add(searchADurationLabel, new Constraints(
				new Leading(x, w, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		searchActivityPanel.add(searchADay, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 10, 10)));

		x += w;
		w = fontmetrics.stringWidth("*") * 40;
		y = 20 + h;
		// add input to panel
		searchActivityPanel.add(searchANameInput, new Constraints(new Leading(
				x, w, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		searchActivityPanel.add(searchAManagerInput, new Constraints(
				new Leading(x, w, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		searchActivityPanel.add(searchAAdressInput, new Constraints(
				new Leading(x, w, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		searchActivityPanel.add(searchAZipcodeInput, new Constraints(
				new Leading(x, (fontmetrics.stringWidth("*") * 8), 10, 10),
				new Leading(y, h, 10, 10)));

		searchActivityPanel.add(
				searchACityInput,
				new Constraints(new Leading(x
						+ fontmetrics.stringWidth(searchACityLabel.getText())
						+ (fontmetrics.stringWidth("*") * 8) + 20, fontmetrics
						.stringWidth("*")
						* 32
						- fontmetrics.stringWidth(searchACityLabel.getText())
						- 20, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		searchActivityPanel.add(searchASeatsInput, new Constraints(new Leading(
				x, (fontmetrics.stringWidth("*") * 8), 10, 10), new Leading(y,
				h, 10, 10)));

		searchActivityPanel.add(
				searchAstatusBox,
				new Constraints(new Leading(x
						+ (fontmetrics.stringWidth("*") * 8)
						+ fontmetrics.stringWidth(searchAstatusLabel.getText())
						+ 20, (fontmetrics.stringWidth("*") * 32)
						- fontmetrics.stringWidth(searchAstatusLabel.getText())
						- 20, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		searchActivityPanel.add(searchADurationStartInput,
				new Constraints(
						new Leading(x, (int) (w / 2) - h - 5
								- (int) (fontmetrics.stringWidth(" - ") * 0.5),
								10, 10), new Leading(y, h, 10, 10)));

		searchActivityPanel.add(
				searchAstartCal,
				new Constraints(new Leading(x
						+ ((int) (w / 2) - h - (int) (fontmetrics
								.stringWidth(" - ") * 0.5)), h, 10, 10),
						new Leading(y, h, 10, 10)));

		searchActivityPanel.add(
				new JLabel(" - "),
				new Constraints(
						new Leading(x + (int) (w / 2)
								- (int) (fontmetrics.stringWidth(" - ") * 0.5),
								10, 10), new Leading(y, h, 10, 10)));

		searchActivityPanel.add(
				searchADurationEndInput,
				new Constraints(new Leading(x + (int) (w / 2)
						+ (int) (fontmetrics.stringWidth(" - ") * 0.5),
						(int) (w / 2) - h - 5
								- (int) (fontmetrics.stringWidth(" - ") * 0.5),
						10, 10), new Leading(y, h, 10, 10)));

		searchActivityPanel.add(searchAendCal, new Constraints(new Leading(x
				+ w - h, h, 10, 10), new Leading(y, h, 10, 10)));

		int timeW = (fontmetrics.stringWidth("*") * 20) - 5;

		y += 10 + h;

		searchActivityPanel.add(searchAallCB, new Constraints(new Leading(x,
				10, 10), new Leading(y, h, 10, 10)));

		searchActivityPanel.add(searchAmondayCB, new Constraints(new Leading(x
				+ timeW + 10, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		searchActivityPanel.add(searchAtuesdayCB, new Constraints(new Leading(
				x, 10, 10), new Leading(y, h, 10, 10)));

		searchActivityPanel
				.add(searchAwednesdayCB, new Constraints(new Leading(x + timeW
						+ 10, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		searchActivityPanel.add(searchAthursdayCB, new Constraints(new Leading(
				x, 10, 10), new Leading(y, h, 10, 10)));

		searchActivityPanel.add(searchAfridayCB, new Constraints(new Leading(x
				+ timeW + 10, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		searchActivityPanel.add(searchAsaturdayCB, new Constraints(new Leading(
				x, 10, 10), new Leading(y, h, 10, 10)));

		searchActivityPanel.add(searchAsundayCB, new Constraints(new Leading(x
				+ timeW + 10, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		searchActivityPanel.add(searchAdocLabel, new Constraints(new Leading(
				10, longestLabel, 10, 10), new Leading(y, h, 10, 10)));

		searchActivityPanel.add(searchAdocName, new Constraints(new Leading(x,
				w, 10, 10), new Leading(y, h, 10, 10)));

		y += 40 + (h * 2);

		// add buttons to panel
		searchActivityPanel.add(searchAcancel, new Constraints(new Trailing(10,
				fontmetrics.stringWidth("Avbryt")
						+ (new JButton().getInsets().left * 2), 10, 10),
				new Trailing(10, h, 12, 12)));

		searchActivityPanel.add(
				searchAok,
				new Constraints(new Trailing(20
						+ fontmetrics.stringWidth("Avbryt")
						+ (new JButton().getInsets().left * 2), fontmetrics
						.stringWidth("Avbryt")
						+ (new JButton().getInsets().left * 2), 10, 10),
						new Trailing(10, h, 12, 12)));

		// set panel size
		searchActivityPanel
				.setSize(
						DEFAULT_PANEL_W - mainActivitySidePanel.getWidth() > w ? DEFAULT_PANEL_W
								- mainActivitySidePanel.getWidth()
								: w, DEFAULT_PANEL_H > y ? DEFAULT_PANEL_H : y);
	}

	public void createShowActivityPanel(Activity a) {

		FontMetrics fontmetrics = getFontMetrics(new Font(c.user.getFamily(),
				1, c.user.getSize()));
		// create panel
		showApath = a.getPath();
		showActivityPanel = new JPanel();
		showActivityPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		showActivityPanel.setLayout(new GroupLayout());
		showActivityPanel.addKeyListener(this);
		// create labels
		showAtitleLabel = new JLabel(a.getID() + " : " + a.getName());
		showAtitleLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize() + 1));
		showAtitleLabel.setForeground(c.user.getForegroundColor().darker());

		showAmanagerLabel = new JLabel("Ansvarshavende ");
		showAmanagerLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		int longestLabel = fontmetrics.stringWidth(showAmanagerLabel.getText());

		showAdurationLabel = new JLabel("Varighet ");
		showAdurationLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics
				.stringWidth(showAdurationLabel.getText()))
			longestLabel = fontmetrics
					.stringWidth(showAdurationLabel.getText());

		showATime = new JLabel("Tidspunkt ");
		showATime.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		if (longestLabel < fontmetrics.stringWidth(showATime.getText()))
			longestLabel = fontmetrics.stringWidth(showATime.getText());

		showAadrLabel = new JLabel("Adresse ");
		showAadrLabel
				.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		if (longestLabel < fontmetrics.stringWidth(showAadrLabel.getText()))
			longestLabel = fontmetrics.stringWidth(showAadrLabel.getText());

		showAtotalSeatsLabel = new JLabel("Antall plasser ");
		showAtotalSeatsLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth(showAtotalSeatsLabel
				.getText()))
			longestLabel = fontmetrics.stringWidth(showAtotalSeatsLabel
					.getText());

		// create information from ActivityObject

		showAdesc = new JTextArea(a.getDescription());
		showAdesc.setFont(new Font(c.user.getFamily(), 2, c.user.getSize()));
		showAdesc.setEditable(false);
		showAdesc.setLineWrap(true);
		showAdesc.setWrapStyleWord(true);
		showAdesc.setColumns(31);

		showAmanagerValue = new JLabel(a.getManager());

		// check for activity enddate
		Calendar calendar = Calendar.getInstance();
		Calendar date = Calendar.getInstance();
		date.set(Integer.parseInt(a.getEndDate().substring(6)),
				Integer.parseInt(a.getEndDate().substring(3, 5)) - 1,
				Integer.parseInt(a.getEndDate().substring(0, 2)));
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));

		if (calendar.before(date)) {
			showAdurationValue = new JLabel(
					a.getStartDate()
							+ " - "
							+ a.getEndDate()
							+ " ("
							+ (date.get(Calendar.DAY_OF_YEAR) - calendar
									.get(Calendar.DAY_OF_YEAR))
							+ " dager igjen)");

			showAposition = activitySidePanel.getWidth();
		} else {
			showAdurationValue = new JLabel(a.getStartDate() + " - "
					+ a.getEndDate() + "  (Avsluttet)");
			showAposition = mainActivitySidePanel.getWidth();
		}

		Days[] d = a.getDays();
		StringBuilder s = new StringBuilder();

		for (int i = 0; i < d.length; i++) {

			Days tmpDay = d[i];
			if (!tmpDay.getDay().equals("")) {
				s.append(tmpDay.getDay() + "\t" + tmpDay.getStartTime() + " - "
						+ tmpDay.getEndTime() + "\t\n");
			}
		}

		showATimeValue = new JTextArea(s.toString());
		showATimeValue
				.setFont(new Font(c.user.getFamily(), 2, c.user.getSize()));
		showATimeValue.setEditable(false);

		showAadrValue = new JLabel("<html>" + a.getAddress() + "<br/>"
				+ a.getZipcode() + " " + a.getCity() + "</html>");

		showAtotalSeatsValue = new JLabel(a.getTotalSeats());

		// create buttons

		showAattendance = new JButton("Oppmøteliste");
		showAattendance.addActionListener(this);

		// get table

		if (showAallEntries) {
			showActivityLogTable = a.getAllLog();

			// create button and add listener's
			showAallLogEntries = new JButton("Vis siste 5 loggoppføringer");

		} else {
			showActivityLogTable = a.getLog();

			// create button and add listener's
			showAallLogEntries = new JButton("Vis alle loggoppføringer");

		}

		showAallLogEntries.addActionListener(this);
		showAallLogEntries.addKeyListener(this);

		showActivityLogTable.setAutoCreateRowSorter(true);
		showActivityLogTable.getTableHeader().setReorderingAllowed(false);
		showActivityLogTable.getTableHeader().setFont(c.user.getFont());
		showActivityLogTable.getTableHeader().setForeground(
				c.user.getForegroundColor());
		showActivityLogTable.getTableHeader().setBackground(
				c.user.getBackgroundColor().darker());

		showActivityLogW = 0;
		autoResizeColWidth(showActivityLogTable, "showactivity");
		showActivityLogTable.setRowHeight(fontmetrics.getHeight() + 4);

		// create help-variables
		int x = 10;
		int y = 10;
		int h = getFontMetrics(showAtitleLabel.getFont()).getHeight();
		int w = longestLabel + 10;
		int timeRow = showATimeValue.getText().split("\n").length
				* (h + showATimeValue.getInsets().top + showATimeValue
						.getInsets().bottom) == 0 ? h : showATimeValue
				.getText().split("\n").length
				* (h + showATimeValue.getInsets().top + showATimeValue
						.getInsets().bottom);

		// add labels to panel

		showActivityPanel.add(showAtitleLabel, new Constraints(new Leading(x,
				10, 10), new Leading(y, h, 10, 10)));

		y += h + 20;

		if (a.getDocument().length() != 0) {
			
			showAdoc = new JLabel("<html><u>" + a.getDocument() + "</u></html>");
			showAdoc.setFont(new Font(c.user.getFamily(), 3,
					c.user.getSize() - 1));
			showAdoc.addMouseListener(new Hyperlink());

			showActivityPanel.add(showAdoc, new Constraints(new Leading(x, this.fontmetrics.stringWidth(showAdoc.getText()),
					10, 10), new Leading(y, h, 10, 10)));

			y += h + 10;
		}

		showActivityPanel.add(showAdesc, new Constraints(
				new Leading(x, 10, 10), new Leading(y, 10, 10)));

		int row = (int) (showAdesc.getColumns() / 30) > showAdesc.getText()
				.split("\n").length ? (int) (showAdesc.getColumns() / 30)
				: showAdesc.getText().split("\n").length;
		y += (row * (h + showAdesc.getInsets().top + showAdesc.getInsets().bottom)) + 10;

		showActivityPanel.add(showAmanagerLabel, new Constraints(new Leading(x,
				w, 10, 10), new Leading(y, h, 10, 10)));
		int longestValue = fontmetrics.stringWidth(showAmanagerValue.getText());

		y += h + 10;

		showActivityPanel.add(showAdurationLabel, new Constraints(new Leading(
				x, w, 10, 10), new Leading(y, h, 10, 10)));
		if (longestValue < fontmetrics
				.stringWidth(showAdurationValue.getText())) {
			longestValue = fontmetrics
					.stringWidth(showAdurationValue.getText());
		}

		y += h + 10;

		showActivityPanel.add(showATime, new Constraints(new Leading(x, w, 10,
				10), new Leading(y, h, 10, 10)));

		y += timeRow + 10;

		showActivityPanel.add(showAadrLabel, new Constraints(new Leading(x, w,
				10, 10), new Leading(y, h, 10, 10)));
		if (longestValue < fontmetrics.stringWidth(showAadrValue.getText()
				.split("<br/>")[0])) {
			longestValue = fontmetrics.stringWidth(showAadrValue.getText()
					.split("<br/>")[0]);
		}
		if (longestValue < fontmetrics.stringWidth(showAadrValue.getText()
				.split("<br/>")[1])) {
			longestValue = fontmetrics.stringWidth(showAadrValue.getText()
					.split("<br/>")[1]);
		}

		y += (h * 2) + 10;

		showActivityPanel.add(showAtotalSeatsLabel, new Constraints(
				new Leading(x, w, 10, 10), new Leading(y, h, 10, 10)));

		// add buttons to panel

		y += h + 15;

		showActivityPanel.add(showAallLogEntries, new Constraints(new Leading(
				x, fontmetrics.stringWidth(showAallLogEntries.getText())
						+ showAallLogEntries.getInsets().left
						+ showAallLogEntries.getInsets().right, 10, 10),
				new Leading(y, h + 4, 10, 10)));

		showActivityPanel.add(
				showAattendance,
				new Constraints(new Leading(x + 10
						+ fontmetrics.stringWidth(showAallLogEntries.getText())
						+ showAallLogEntries.getInsets().left
						+ showAallLogEntries.getInsets().right, fontmetrics
						.stringWidth(showAallLogEntries.getText())
						+ showAallLogEntries.getInsets().left
						+ showAallLogEntries.getInsets().right, 10, 10),
						new Leading(y, h + 4, 10, 10)));

		// add values to panel
		x += w;
		if (a.getDocument().length() != 0) {

			y = 50
					+ (h * 2)
					+ (row * (h + showAdesc.getInsets().top + showAdesc
							.getInsets().bottom));
		} else {
			y = 40
					+ h
					+ (row * (h + showAdesc.getInsets().top + showAdesc
							.getInsets().bottom));
		}
		showActivityPanel.add(showAmanagerValue, new Constraints(new Leading(x,
				10, 10), new Leading(y, h, 10, 10)));

		y += h + 10;

		showActivityPanel.add(showAdurationValue, new Constraints(new Leading(
				x, 10, 10), new Leading(y, h, 10, 10)));

		y += h + 10;

		showActivityPanel.add(showATimeValue, new Constraints(new Leading(x,
				10, 10), new Leading(y, timeRow, 10, 10)));

		y += timeRow + 10;

		showActivityPanel.add(showAadrValue, new Constraints(new Leading(x, 10,
				10), new Leading(y, 10, 10)));

		y += (h * 2) + 10;

		showActivityPanel.add(showAtotalSeatsValue, new Constraints(
				new Leading(x, 10, 10), new Leading(y, h, 10, 10)));

		y += ((this.fontmetrics.getHeight() + 20) * 2)
				+ showAallLogEntries.getInsets().top
				+ showAallLogEntries.getInsets().bottom;

		showActivityLogTable.setSize(
				showActivityLogW,
				showActivityLogTable.getRowHeight()
						* (showActivityLogTable.getRowCount() + 1));

		JScrollPane scroll = new JScrollPane(showActivityLogTable);
		scroll.setBackground(c.user.getBackgroundColor().brighter());
		JPanel p = new JPanel(new BorderLayout());

		// add table to panel p and panel p to showActivityPanel
		p.add(scroll, BorderLayout.CENTER);
		showActivityPanel.add(p,
				new Constraints(new Leading(10, showActivityLogW, 10, 10),
						new Leading(y, showActivityLogTable.getSize().height,
								10, 10)));

		y += ((showActivityLogTable.getRowCount() + 1) * showActivityLogTable
				.getRowHeight()) + 10;

		// set size
		longestValue = (longestValue + w > fontmetrics
				.stringWidth(showAallLogEntries.getText())
				+ showAallLogEntries.getInsets().left
				+ showAallLogEntries.getInsets().right + 10 ? longestValue + w
				: fontmetrics.stringWidth(showAallLogEntries.getText())
						+ showAallLogEntries.getInsets().left
						+ showAallLogEntries.getInsets().right + 10);
		w = longestValue > showActivityLogW ? longestValue : showActivityLogW;
		showActivityPanel.setSize(
				DEFAULT_PANEL_W - showAposition > w + 20 ? DEFAULT_PANEL_W
						- showAposition : w + 20,
				DEFAULT_PANEL_H > y ? DEFAULT_PANEL_H : y);

	}

	public void createActivityResultPanel(JTable table) {
		// create panel
		activityResultPanel = new JPanel();
		activityResultPanel.setLayout(new GroupLayout());
		activityResultPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		activityResultPanel.addKeyListener(this);

		// create jlabel title
		activityResultTitleLabel = new JLabel("Resultater fra aktivitetsøk");
		activityResultTitleLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize() + 1));
		activityResultTitleLabel.setForeground(c.user.getForegroundColor()
				.darker());

		if (table != null) {
			// create result table and customize
			activityResultTable = table;
			activityResultTable.setAutoCreateRowSorter(true);
			activityResultTable.getTableHeader().setReorderingAllowed(false);
			activityResultTable.getTableHeader().setFont(c.user.getFont());
			activityResultTable.getTableHeader().setForeground(
					c.user.getForegroundColor());
			activityResultTable.getTableHeader().setBackground(
					c.user.getBackgroundColor().darker());

			activityResultTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			activityResultTableWidth = 0;
			autoResizeColWidth(activityResultTable, "activityresult");

			int resultH = 0;
			for (int row = 0; row < table.getRowCount(); row++) {
				activityResultTable
						.setRowHeight(
								row,
								((fontmetrics.getHeight()
										+ table.getInsets().top + table
										.getInsets().bottom) * (activityResultTable
										.getValueAt(row, 9).toString()
										.split("\n").length)));
				resultH += ((fontmetrics.getHeight() + table.getInsets().top + table
						.getInsets().bottom) * (activityResultTable
						.getValueAt(row, 9).toString().split("\n").length));
			}

			activityResultTable.getSelectionModel().addListSelectionListener(
					new ListSelectionListener() {

						public void valueChanged(ListSelectionEvent e) {
							activityResultTable.setRowSelectionAllowed(true);
							int row = activityResultTable.getSelectedRow();
							int column = 0;

							while (!activityResultTable.getColumnName(column)
									.equals(" Navn "))
								column++;

							if (msg.confirmDialog("Gå til \n"
									+ activityResultTable
											.getValueAt(row, column).toString()
											.trim() + "?") == 0) {
								column = 0;
								while (!activityResultTable.getColumnName(
										column).equals(" ID "))
									column++;
								String termDate = c.getActivity(
										activityResultTable
												.getValueAt(row, column)
												.toString().trim())
										.getEndDate();

								Calendar calendar = Calendar.getInstance();
								Calendar activityDate = Calendar.getInstance();
								activityDate.set(Integer.parseInt(termDate
										.substring(6)),
										Integer.parseInt(termDate.substring(3,
												5)) - 1, Integer
												.parseInt(termDate.substring(0,
														2)));
								calendar.set(calendar.get(Calendar.YEAR),
										calendar.get(Calendar.MONTH),
										calendar.get(Calendar.DAY_OF_MONTH));

								if (calendar.before(activityDate)) {
									hideAllPanels();
									panel.removeAll();
									showActivityPanel.removeAll();
									activitySidePanel.removeAll();
									createActivitySidePanel();
									showAallEntries = false;
									createShowActivityPanel(c
											.getActivity(activityResultTable
													.getValueAt(row, column)
													.toString().trim()));

									panel.add(
											activitySidePanel,
											new Constraints(
													new Leading(
															0,
															activitySidePanel
																	.getWidth(),
															10, 10),
													new Leading(
															0,
															activitySidePanel
																	.getHeight(),
															12, 12)));

									panel.add(
											showActivityPanel,
											new Constraints(
													new Leading(
															showAposition
																	+ DEFAULT_PANEL_X,
															showActivityPanel
																	.getWidth(),
															10, 10),
													new Leading(
															DEFAULT_PANEL_Y,
															showActivityPanel
																	.getHeight(),
															10, 10)));

								} else {
									hideAllPanels();
									panel.removeAll();
									showActivityPanel.removeAll();
									mainActivitySidePanel.removeAll();
									createMainActivitySidePanel();
									showAallEntries = false;
									createShowActivityPanel(c
											.getActivity(activityResultTable
													.getValueAt(row, column)
													.toString().trim()));

									panel.add(
											mainActivitySidePanel,
											new Constraints(
													new Leading(
															0,
															mainActivitySidePanel
																	.getWidth(),
															10, 10),
													new Leading(
															0,
															mainActivitySidePanel
																	.getHeight(),
															12, 12)));

									panel.add(
											showActivityPanel,
											new Constraints(
													new Leading(
															showAposition
																	+ DEFAULT_PANEL_X,
															showActivityPanel
																	.getWidth(),
															10, 10),
													new Leading(
															DEFAULT_PANEL_Y,
															showActivityPanel
																	.getHeight(),
															10, 10)));

								}
							}

						}
					});

			// set table size and add to panel
			activityResultTable.setSize(activityResultTableWidth, resultH
					+ fontmetrics.getHeight() + 4);

			activityResultScroll = new JScrollPane(activityResultTable);
			activityResultScroll.setBackground(c.user.getBackgroundColor()
					.brighter());

			JPanel p = new JPanel();
			p.setLayout(new BorderLayout());
			p.add(activityResultScroll, BorderLayout.PAGE_START);

			activityResultPanel.add(activityResultTitleLabel, new Constraints(
					new Leading(10, 10, 10), new Leading(10, 10, 10)));

			activityResultPanel.add(p,
					new Constraints(new Leading(10, activityResultTableWidth,
							10, 10), new Leading(20 + fontmetrics.getHeight(),
							activityResultTable.getHeight(), 10, 10)));

			// set panel size
			activityResultPanel
					.setSize(
							DEFAULT_PANEL_W - mainActivitySidePanel.getWidth() > activityResultTableWidth + 20 ? DEFAULT_PANEL_W
									- mainActivitySidePanel.getWidth()
									: activityResultTableWidth + 20,
							DEFAULT_PANEL_H > activityResultTable.getHeight() + 20 ? DEFAULT_PANEL_H
									: activityResultTable.getHeight() + 20);
		}
	}

	public void createMainStatisticsSidePanel() {
		// create panel
		mainStatisticsSidePanel = new JPanel();
		mainStatisticsSidePanel.setLayout(new GroupLayout());
		mainStatisticsSidePanel.setBackground(c.user.getBackgroundColor()
				.darker());
		mainStatisticsSidePanel.addKeyListener(this);

		String title = "STATISTIKKMENY";

		mainStatisticsSidePanel.setBorder(BorderFactory.createTitledBorder(
				null, title, TitledBorder.LEADING,
				TitledBorder.DEFAULT_POSITION, null,
				c.user.getForegroundColor()));

		// create buttons and add listeners
		mainPageFromMainStatistics = new JButton("Hovedmeny");
		mainPageFromMainStatistics.addActionListener(this);
		mainPageFromMainStatistics.addKeyListener(this);
		int longestButton = fontmetrics.stringWidth(mainPageFromMainStatistics
				.getText());

		mainStatisticPageFromMainStatistics = new JButton("Statistikksiden");
		mainStatisticPageFromMainStatistics.addActionListener(this);
		mainStatisticPageFromMainStatistics.addKeyListener(this);
		if (longestButton < fontmetrics
				.stringWidth(mainStatisticPageFromMainStatistics.getText()))
			longestButton = fontmetrics
					.stringWidth(mainStatisticPageFromMainStatistics.getText());

		mainMemberStatistics = new JButton("Medlemsstatistikk");
		mainMemberStatistics.addActionListener(this);
		mainMemberStatistics.addKeyListener(this);
		if (longestButton < fontmetrics.stringWidth(mainMemberStatistics
				.getText()))
			longestButton = fontmetrics.stringWidth(mainMemberStatistics
					.getText());

		mainActivityStatistics = new JButton("Aktivitetsstatistikk");
		mainActivityStatistics.addActionListener(this);
		mainActivityStatistics.addKeyListener(this);
		if (longestButton < fontmetrics.stringWidth(mainActivityStatistics
				.getText()))
			longestButton = fontmetrics.stringWidth(mainActivityStatistics
					.getText());

		// create help-variables
		int buttonH = fontmetrics.getHeight()
				+ (new JButton().getInsets().bottom * 2);
		int buttonW = longestButton + (new JButton().getInsets().left * 2);
		int height = 10;

		// add to panel
		mainStatisticsSidePanel.add(mainPageFromMainStatistics,
				new Constraints(new Leading(10, buttonW, 10, 10), new Leading(
						height, buttonH, 12, 12)));

		height += buttonH + 10;

		mainStatisticsSidePanel.add(mainStatisticPageFromMainStatistics,
				new Constraints(new Leading(10, buttonW, 10, 10), new Leading(
						height, buttonH, 12, 12)));

		height += buttonH + 10;

		mainStatisticsSidePanel.add(mainMemberStatistics, new Constraints(
				new Leading(10, buttonW, 10, 10), new Leading(height, buttonH,
						12, 12)));

		height += buttonH + 10;

		mainStatisticsSidePanel.add(mainActivityStatistics, new Constraints(
				new Leading(10, buttonW, 10, 10), new Leading(height, buttonH,
						12, 12)));

		height += buttonH + 10;

		// set sidepanelSize
		mainStatisticsSidePanel.setSize(buttonW + 30, DEFAULT_HEIGTH);
	}

	public void createMainStatisticsPanel() {
		FontMetrics fontmetrics = getFontMetrics(new Font(c.user.getFamily(),
				1, c.user.getSize()));

		// create panel
		mainStatisticsPanel = new JPanel();
		mainStatisticsPanel.setLayout(new GroupLayout());
		mainStatisticsPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		mainStatisticsPanel.addKeyListener(this);

		// create labels
		mainStatTitle = new JLabel("Systemstatistikk");
		mainStatTitle.setFont(new Font(c.user.getFamily(), 1,
				c.user.getSize() + 1));
		mainStatTitle.setForeground(c.user.getForegroundColor().darker());

		mainStatUptimeLabel = new JLabel("Systemets oppetid ");
		mainStatUptimeLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		int longestLabel = fontmetrics.stringWidth(mainStatUptimeLabel
				.getText());

		mainStatEntryLabel = new JLabel("Nye oppføringer i dag ");
		mainStatEntryLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics
				.stringWidth(mainStatEntryLabel.getText()))
			longestLabel = fontmetrics
					.stringWidth(mainStatEntryLabel.getText());

		mainStatUserLink = new JLabel("<html><i>Innlogget som </i><u>"
				+ c.user.getUsername().trim() + "</u></html>");
		mainStatUserLink.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		mainStatUserLink.addMouseListener(new Hyperlink());
		if (longestLabel < fontmetrics.stringWidth("Innlogget som "
				+ c.user.getUsername()))
			longestLabel = fontmetrics.stringWidth("Innlogget som "
					+ c.user.getUsername());

		mainStatMostActiveLabel = new JLabel(
				"<html><b>Mest aktive bruker</b><br/><i>&nbsp Forrige uke <br/>&nbsp Forrige måned </i></html>");
		mainStatMostActiveLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		if (longestLabel < fontmetrics.stringWidth("Mest aktive bruker "))
			longestLabel = fontmetrics.stringWidth("Mest aktive bruker ");

		String[] values = c.getMainStatistic();

		// get uptimeValue
		String current = c.removeDateLine(c.getCurrentDateTime());
		String logintime = c.removeDateLine(dateLabel.getText().trim());

		Calendar curCal = Calendar.getInstance();
		Calendar loginCal = Calendar.getInstance();
		curCal.set(Integer.parseInt(current.substring(4, 8)),
				(Integer.parseInt(current.substring(2, 4)) - 1),
				Integer.parseInt(current.substring(0, 3)),
				Integer.parseInt(current.substring(8, 10)),
				Integer.parseInt(current.substring(10, 12)));
		loginCal.set(Integer.parseInt(logintime.substring(4, 8)),
				(Integer.parseInt(logintime.substring(2, 4)) - 1),
				Integer.parseInt(logintime.substring(0, 3)),
				Integer.parseInt(logintime.substring(8, 10)),
				Integer.parseInt(logintime.substring(10, 12)));

		long diff = curCal.getTimeInMillis() - loginCal.getTimeInMillis();
		long diffDays = diff / (24 * 60 * 60 * 1000);
		long diffHours = (diff - (diffDays * 24)) / (60 * 60 * 1000);
		long diffMinutes = (diff - (diffDays * 24 * 60 * 60 * 1000) - (diffHours * 60 * 60 * 1000))
				/ (60 * 1000);

		mainStatUptimeValue = new JLabel(diffDays + " dag(er), " + diffHours
				+ " time(r), " + diffMinutes + " minutt(er)");
		mainStatEntryValue = new JLabel(values[0]);
		mainStatMostActiveValue = new JLabel("<html>" + values[1] + "<br/>"
				+ values[2] + "</html>");

		// create help-variables
		int x = 10;
		int y = 10;
		int w = longestLabel + 10;
		int h = getFontMetrics(mainStatTitle.getFont()).getHeight();

		// add label to panel
		mainStatisticsPanel.add(mainStatTitle, new Constraints(new Leading(10,
				10, 10), new Leading(10, 10, 10)));

		y += 15 + h;

		mainStatisticsPanel.add(mainStatUptimeLabel, new Constraints(
				new Leading(x, w, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		mainStatisticsPanel.add(mainStatUserLink, new Constraints(new Leading(
				x, w, 10, 10), new Leading(y, h, 10, 10)));

		y += 15 + h;

		mainStatisticsPanel.add(mainStatEntryLabel, new Constraints(
				new Leading(x, w, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + h;

		mainStatisticsPanel.add(mainStatMostActiveLabel, new Constraints(
				new Leading(x, w, 10, 10), new Leading(y, 10, 10)));

		x = w;
		y = 25 + h;

		// add values to panel
		mainStatisticsPanel.add(mainStatUptimeValue, new Constraints(
				new Leading(x, 10, 10), new Leading(y, h, 10, 10)));

		y += 25 + (h * 2);

		mainStatisticsPanel.add(mainStatEntryValue, new Constraints(
				new Leading(x, 10, 10), new Leading(y, h, 10, 10)));

		y += 10 + (h * 2);

		mainStatisticsPanel.add(mainStatMostActiveValue, new Constraints(
				new Leading(x, 10, 10), new Leading(y, 10, 10)));

		// set panel size
		mainStatisticsPanel.setSize(
				DEFAULT_PANEL_W - mainStatisticsSidePanel.getWidth(),
				DEFAULT_PANEL_H);

	}

	public void createMemberStatisticsPanel(JTable[] table) {
		// create panel
		memberStatisticsPanel = new JPanel();
		memberStatisticsPanel.setLayout(new GroupLayout());
		memberStatisticsPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		memberStatisticsPanel.addKeyListener(this);

		// create label, textfield and button
		mStatTitleLabel = new JLabel(
				"<html><b>Medlemstatistikk</b><br/><br/>Velg år </html>");

		mStatInput = new JTextField();
		mStatInput.setSize(fontmetrics.stringWidth("*") * 8,
				fontmetrics.getHeight() + 4);
		mStatInput.addKeyListener(this);

		mStatButton = new JButton(" Hent ");
		mStatButton.addActionListener(this);
		mStatButton.addKeyListener(this);

		// create help-variables
		int x = 10;
		int y = 10;
		int h = fontmetrics.getHeight() + 4;

		// add to panel
		memberStatisticsPanel.add(mStatTitleLabel, new Constraints(new Leading(
				x, 10, 10), new Leading(y, 10, 10)));

		x += 10 + fontmetrics.stringWidth("Velg år ");
		y += (h * 2) - 8;

		memberStatisticsPanel.add(mStatInput, new Constraints(new Leading(x,
				mStatInput.getWidth(), 10, 10), new Leading(y, h, 10, 10)));

		x += 10 + mStatInput.getWidth();

		memberStatisticsPanel.add(mStatButton, new Constraints(new Leading(x,
				10, 10), new Leading(y, h, 10, 10)));

		if (table != null) {
			y += 20 + h;
			totalMstatW = 0;
			memberStatisticsPanel.add(new JLabel("<html><b>Statistikk for "
					+ mstatYear + "</b></html>"), new Constraints(new Leading(
					10, 10, 10), new Leading(y, h, 10, 10)));

			y += 10 + h;
			memberStatisticsPanel.add(new JLabel(
					"Oversikt over endringer i medlemsskap"), new Constraints(
					new Leading(10, 10, 10), new Leading(y, h, 10, 10)));

			for (int i = 0; i < table.length; i = i + 2) {

				y += 5 + h;
				JTable result = table[i];

				result.setAutoCreateRowSorter(true);
				result.getTableHeader().setReorderingAllowed(false);
				result.getTableHeader().setFont(c.user.getFont());
				result.getTableHeader().setForeground(
						c.user.getForegroundColor());
				result.getTableHeader().setBackground(
						c.user.getBackgroundColor().darker());

				memberStatWidth = 0;
				autoResizeColWidth(result, "memberstat");
				result.setRowHeight(fontmetrics.getHeight() + 4);
				int tmp = memberStatWidth;
				result.setSize(memberStatWidth,
						result.getRowHeight() * (result.getRowCount() + 1));

				JScrollPane Scroll = new JScrollPane(result);
				Scroll.setBackground(c.user.getBackgroundColor().brighter());

				memberStatisticsPanel.add(Scroll,
						new Constraints(
								new Leading(10, memberStatWidth, 10, 10),
								new Leading(y, result.getHeight(), 10, 10)));

				JTable result2 = table[i + 1];

				result2.setAutoCreateRowSorter(true);
				result2.getTableHeader().setReorderingAllowed(false);
				result2.getTableHeader().setFont(c.user.getFont());
				result2.getTableHeader().setForeground(
						c.user.getForegroundColor());
				result2.getTableHeader().setBackground(
						c.user.getBackgroundColor().darker());

				memberStatWidth = 0;
				autoResizeColWidth(result2, "memberstat");
				result2.setRowHeight(fontmetrics.getHeight() + 4);

				result2.setSize(memberStatWidth, result2.getRowHeight()
						* (result2.getRowCount() + 1));

				JScrollPane Scroll2 = new JScrollPane(result2);
				Scroll2.setBackground(c.user.getBackgroundColor().brighter());

				memberStatisticsPanel.add(Scroll2, new Constraints(new Leading(
						20 + tmp, memberStatWidth, 10, 10), new Leading(y,
						result.getHeight(), 10, 10)));

				tmp += memberStatWidth + 10;
				totalMstatW = totalMstatW > tmp ? totalMstatW : tmp;

				y += 10 + table[i].getHeight();
				if (i == 0) {
					memberStatisticsPanel.add(new JLabel(
							"Oversikt over aldersfordelingen"),
							new Constraints(new Leading(10, 10, 10),
									new Leading(y, h, 10, 10)));
				} else if (i == 2) {
					memberStatisticsPanel.add(
							new JLabel("Yngste/eldste medlem"),
							new Constraints(new Leading(10, 10, 10),
									new Leading(y, h, 10, 10)));
				}

			}
		}
		// set size
		memberStatisticsPanel
				.setSize(
						DEFAULT_PANEL_W - mainStatisticsSidePanel.getWidth() > totalMstatW + 20 ? DEFAULT_PANEL_W
								- mainStatisticsSidePanel.getWidth()
								: totalMstatW + 20,
						DEFAULT_PANEL_H > y ? DEFAULT_PANEL_H : y);
	}

	public void createActivityStatisticsPanel(JTable[] table) {
		// create panel
		activityStatisticsPanel = new JPanel();
		activityStatisticsPanel.setLayout(new GroupLayout());
		activityStatisticsPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		activityStatisticsPanel.addKeyListener(this);

		// create label, textfield and button
		aStatTitleLabel = new JLabel(
				"<html><b>Aktivitetstatistikk</b><br/><br/>Velg år </html>");

		aStatInput = new JTextField();
		aStatInput.setSize(fontmetrics.stringWidth("*") * 8,
				fontmetrics.getHeight() + 4);
		aStatInput.addKeyListener(this);

		aStatButton = new JButton(" Hent ");
		aStatButton.addActionListener(this);
		aStatButton.addKeyListener(this);

		// create help-variables
		int x = 10;
		int y = 10;
		int h = fontmetrics.getHeight() + 4;

		// add to panel
		activityStatisticsPanel.add(aStatTitleLabel, new Constraints(
				new Leading(x, 10, 10), new Leading(y, 10, 10)));

		x += 10 + fontmetrics.stringWidth("Velg år ");
		y += (h * 2) - 8;

		activityStatisticsPanel.add(aStatInput, new Constraints(new Leading(x,
				aStatInput.getWidth(), 10, 10), new Leading(y, h, 10, 10)));

		x += 10 + aStatInput.getWidth();

		activityStatisticsPanel.add(aStatButton, new Constraints(new Leading(x,
				10, 10), new Leading(y, h, 10, 10)));

		if (table != null) {
			y += 20 + h;
			totalAstatW = 0;
			activityStatisticsPanel.add(new JLabel("<html><b>Statistikk for "
					+ astatYear + "</b></html>"), new Constraints(new Leading(
					10, 10, 10), new Leading(y, h, 10, 10)));

			JTable values = table[0];
			y += 10 + h;
			activityStatisticsPanel.add(new JLabel("Antall aktiviteter"),
					new Constraints(new Leading(10, 10, 10), new Leading(y, h,
							10, 10)));

			activityStatisticsPanel.add(
					new JLabel(values.getValueAt(0, 0).toString()),
					new Constraints(new Leading(20 + fontmetrics
							.stringWidth(" Antall aktiviteter "), 10, 10),
							new Leading(y, h, 10, 10)));

			y += h;
			activityStatisticsPanel.add(new JLabel("  Opprettet "),
					new Constraints(new Leading(10, 10, 10), new Leading(y, h,
							10, 10)));

			activityStatisticsPanel.add(
					new JLabel(values.getValueAt(1, 0).toString()),
					new Constraints(new Leading(20 + fontmetrics
							.stringWidth(" Antall aktiviteter "), 10, 10),
							new Leading(y, h, 10, 10)));

			y += h;
			activityStatisticsPanel.add(new JLabel("  Avsluttet "),
					new Constraints(new Leading(10, 10, 10), new Leading(y, h,
							10, 10)));

			activityStatisticsPanel.add(
					new JLabel(values.getValueAt(2, 0).toString()),
					new Constraints(new Leading(20 + fontmetrics
							.stringWidth(" Antall aktiviteter "), 10, 10),
							new Leading(y, h, 10, 10)));

			y += 20 + h;
			activityStatisticsPanel.add(new JLabel(
					"Kapasitet, etterspørsel og oppmøte"), new Constraints(
					new Leading(10, 10, 10), new Leading(y, h, 10, 10)));

			for (int i = 1; i < table.length; i++) {

				y += 5 + h;
				JTable result = table[i];

				result.setAutoCreateRowSorter(true);
				result.getTableHeader().setReorderingAllowed(false);
				result.getTableHeader().setFont(c.user.getFont());
				result.getTableHeader().setForeground(
						c.user.getForegroundColor());
				result.getTableHeader().setBackground(
						c.user.getBackgroundColor().darker());

				activityStatWidth = 0;
				autoResizeColWidth(result, "activitystat");
				result.setRowHeight(fontmetrics.getHeight() + 4);
				result.setSize(activityStatWidth, result.getRowHeight()
						* (result.getRowCount() + 1));

				JScrollPane Scroll = new JScrollPane(result);
				Scroll.setBackground(c.user.getBackgroundColor().brighter());

				activityStatisticsPanel.add(Scroll, new Constraints(
						new Leading(10, activityStatWidth, 10, 10),
						new Leading(y, result.getHeight(), 10, 10)));

				totalAstatW = totalAstatW > activityStatWidth + 10 ? totalAstatW
						: activityStatWidth + 10;

				y += 10 + table[i].getHeight();
				if (i == 1) {
					activityStatisticsPanel
							.add(new JLabel("Oversikt over aktiviteter"),
									new Constraints(new Leading(10, 10, 10),
											new Leading(y, h, 10, 10)));
				}
			}
		}
		// set size
		activityStatisticsPanel
				.setSize(
						DEFAULT_PANEL_W - mainStatisticsSidePanel.getWidth() > totalAstatW + 20 ? DEFAULT_PANEL_W
								- mainStatisticsSidePanel.getWidth()
								: totalAstatW + 20,
						DEFAULT_PANEL_H > y ? DEFAULT_PANEL_H : y);
	}

	public void autoResizeColWidth(JTable table, String type) {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		int margin = 5;
		int tableWidth = 0;

		for (int i = 0; i < table.getColumnCount(); i++) {

			int vColIndex = i;
			TableColumn col = table.getColumnModel().getColumn(vColIndex);

			// Get tableWidth of column header
			TableCellRenderer renderer = col.getHeaderRenderer();

			if (renderer == null) {
				renderer = table.getTableHeader().getDefaultRenderer();
			}

			Component comp = renderer.getTableCellRendererComponent(table,
					col.getHeaderValue(), false, false, 0, 0);

			tableWidth = Math.max(comp.getMinimumSize().width,
					comp.getPreferredSize().width);

			// Get maximum tableWidth of column data
			for (int r = 0; r < table.getRowCount(); r++) {
				renderer = table.getCellRenderer(r, vColIndex);
				comp = renderer.getTableCellRendererComponent(table,
						table.getValueAt(r, vColIndex), false, false, r,
						vColIndex);
				tableWidth = Math.max(tableWidth, comp.getMinimumSize().width);
			}

			// Add margin
			tableWidth += 2 * margin;
			if (type.equals("memberlog")) {
				memberLogMainTableWidth += tableWidth;
			} else if (type.equals("activitylog")) {
				activityLogMainTableWidth += tableWidth;
			} else if (type.equals("showmember")) {
				showMemberLogW += tableWidth;
			} else if (type.equals("memberresult")) {
				memberResultTableWidth += tableWidth;
			} else if (type.equals("showactivity")) {
				showActivityLogW += tableWidth;
			} else if (type.equals("attendance")) {
				showAattW += tableWidth;
			} else if (type.equals("activityresult")) {
				activityResultTableWidth += tableWidth;
			} else if (type.equals("memberstat")) {
				memberStatWidth += tableWidth;
			} else if (type.equals("activitystat")) {
				activityStatWidth += tableWidth;
			}
			// Set the tableWidth
			col.setPreferredWidth(tableWidth);
		}
		if (type.equals("memberlog")) {
			memberLogMainTableWidth += 5;
		} else if (type.equals("activitylog")) {
			activityLogMainTableWidth += 5;
		} else if (type.equals("showmember")) {
			showMemberLogW += 5;
		} else if (type.equals("memberresult")) {
			memberResultTableWidth += 5;
		} else if (type.equals("showactivity")) {
			showActivityLogW += 5;
		} else if (type.equals("attendance")) {
			showAattW += 5;
		} else if (type.equals("activityresult")) {
			activityResultTableWidth += 5;
		} else if (type.equals("memberstat")) {
			memberStatWidth += 5;
		} else if (type.equals("activitystat")) {
			activityStatWidth += 5;
		}

		logWidth = (memberLogMainTableWidth > activityLogMainTableWidth ? memberLogMainTableWidth
				: activityLogMainTableWidth) + 20;

		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void hideAllPanels() {
		mainSidePanel.setVisible(false);
		mainPanel.setVisible(false);
		memberLogPanel.setVisible(false);
		activityLogPanel.setVisible(false);
		showMemberPanel.setVisible(false);
		memberSidePanel.setVisible(false);
		mainMemberSidePanel.setVisible(false);
		mainMemberPanel.setVisible(false);
		mainActivitySidePanel.setVisible(false);
		mainActivityPanel.setVisible(false);
		addActivityPanel.setVisible(false);
		searchActivityPanel.setVisible(false);
		newMemberPanel.setVisible(false);
		searchMemberPanel.setVisible(false);
		mainStatisticsSidePanel.setVisible(false);
		mainStatisticsPanel.setVisible(false);
		editMemberPanel.setVisible(false);
		showActivityPanel.setVisible(false);
		activitySidePanel.setVisible(false);
		editActivityPanel.setVisible(false);
		regAttendancePanel.setVisible(false);
		memberStatisticsPanel.setVisible(false);
		activityStatisticsPanel.setVisible(false);
	}

	public void actionPerformed(ActionEvent e) {

		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		if (e.getSource() == shutdownMenuItem) {

			if (msg.confirmDialog("Avslutte programmet?") == 0)
				c.shutdown();

		} else if (e.getSource() == logOffMenuItem) {

			if (msg.confirmDialog("Logge av?") == 0)
				c.logOff();

		} else if (e.getSource() == aboutMenuItem) {
			int width = fontmetrics.stringWidth("(c) Rabea Kvinneforening, 2011. Alle rettigheter.") + 20;
			Picture rkflogo = new Picture("Image/LOGO.jpg", (int)(DEFAULT_IMAGE_WIDTH/2), (int)(DEFAULT_IMAGE_HEIGTH/2));
			Picture hiologo = new Picture("Image/Hio_Logo.png", DEFAULT_IMAGE_WIDTH, (int)(DEFAULT_IMAGE_HEIGTH/2));
			
			JLabel label = new JLabel("<html>Rabea Kvinneforenings<br/>Administrasjonsprogram<br/><br/>"
					+ "(c) Rabea Kvinneforening, 2011. Alle rettigheter.<br/>"
					+ "Produktet er laget av gruppe 14<br/>som et hovedprosjekt i data<br/>"
					+ "ved Høgskolen i Oslo, 2011</html>");
			
			JPanel p = new JPanel();
			p.setLayout(new GroupLayout());
			
			p.add(rkflogo, new Constraints(new Leading((int)(width/4) - (int)(rkflogo.w/2), rkflogo.w, 10, 10), new Leading(10, rkflogo.h, 12, 12)));
			p.add(hiologo, new Constraints(new Trailing((int)(width/4) - (int)(hiologo.w/2), hiologo.w, 10, 10), new Leading(10, hiologo.h, 12, 12)));
			p.add(label, new Constraints(new Leading(10, 10, 10), new Leading(20 + rkflogo.h, 12, 12)));
			
			JDialog about = new JDialog();
			about.add(p);
			about.pack();
			about.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			about.setTitle("Om programmet");
			about.setIconImage(Toolkit.getDefaultToolkit()
					.getImage("Image/LOGO.jpg"));
			about.setResizable(false);
			about.setAlwaysOnTop(true);
			about.setLocationRelativeTo(null);
			about.setVisible(true);
			
		} else if (e.getSource() == helpMenuItem) {

			try {
				java.awt.Desktop.getDesktop().open(new File("Documentation/UserManual.pdf"));
			} catch (IOException io) {
				msg.messageDialog("Brukermanualen finnes ikke lenger", 0);
			} catch (IllegalArgumentException ie) {
				msg.messageDialog("Brukermanualen finnes ikke lenger", 0);
			}

		} else if (e.getSource() == settingsMenuItem) {

			Frame[] f = JFrame.getFrames();
			boolean opened = false;
			for (Frame frame : f) {
				if (frame.getTitle().equals("Innstillinger")
						&& frame.isEnabled()) {
					frame.setVisible(true);
					frame.requestFocus();
					opened = true;
					break;
				}
			}
			if (!opened) {
				view.Settings s = new view.Settings(c.user, c);
				s.setVisible(true);
			}

		} else if (e.getSource() == passwordMenuItem) {

			c.changePassword(c.user, "Gammelt passord");

		} else if (e.getSource() == newMemberMenuItem) {

			addMember.doClick();

		} else if (e.getSource() == newActivityMenuItem) {

			addActivity.doClick();

		}
		// mainSidePanel events
		else if (e.getSource() == mainMember) {

			hideAllPanels();
			panel.removeAll();
			mainMemberPanel.removeAll();
			mainMemberSidePanel.removeAll();
			createMainMemberPanel();
			createMainMemberSidePanel();

			panel.add(mainMemberSidePanel, new Constraints(new Leading(0,
					mainMemberSidePanel.getWidth(), 10, 10), new Leading(0,
					mainMemberSidePanel.getHeight(), 12, 12)));

			panel.add(mainMemberPanel, new Constraints(new Leading(
					mainMemberSidePanel.getWidth() + DEFAULT_PANEL_X,
					mainMemberPanel.getWidth(), 10, 10), new Leading(
					DEFAULT_PANEL_Y, mainMemberPanel.getHeight(), 12, 12)));

		} else if (e.getSource() == mainActivity) {

			hideAllPanels();
			panel.removeAll();
			mainActivityPanel.removeAll();
			mainActivitySidePanel.removeAll();
			createMainActivityPanel();
			createMainActivitySidePanel();

			panel.add(mainActivitySidePanel, new Constraints(new Leading(0,
					mainActivitySidePanel.getWidth(), 10, 10), new Leading(0,
					mainActivitySidePanel.getHeight(), 12, 12)));

			panel.add(mainActivityPanel, new Constraints(new Leading(
					mainActivitySidePanel.getWidth() + DEFAULT_PANEL_X,
					mainActivityPanel.getWidth(), 10, 10), new Leading(
					DEFAULT_PANEL_Y, mainActivityPanel.getHeight(), 12, 12)));

		} else if (e.getSource() == mainStatistics) {

			hideAllPanels();
			panel.removeAll();
			mainStatisticsPanel.removeAll();
			mainStatisticsSidePanel.removeAll();
			createMainStatisticsSidePanel();
			createMainStatisticsPanel();

			panel.add(mainStatisticsSidePanel, new Constraints(new Leading(0,
					mainStatisticsSidePanel.getWidth(), 10, 10), new Leading(0,
					mainStatisticsSidePanel.getHeight(), 12, 12)));

			panel.add(mainStatisticsPanel, new Constraints(new Leading(
					DEFAULT_PANEL_X + mainStatisticsSidePanel.getWidth(),
					mainStatisticsPanel.getWidth(), 10, 10), new Leading(
					DEFAULT_PANEL_Y, mainStatisticsPanel.getHeight(), 12, 12)));

		}
		// mainMemberSidePanel events
		else if (e.getSource() == mainPagefromMainMemberSidePanel) {

			hideAllPanels();
			panel.removeAll();
			memberLogPanel.removeAll();
			activityLogPanel.removeAll();
			mainPanel.removeAll();
			mainSidePanel.removeAll();

			createMainSidePanel();
			createMainPanel();

			panel.add(mainSidePanel, new Constraints(new Leading(0,
					mainSidePanel.getWidth(), 10, 10), new Leading(0,
					mainSidePanel.getHeight(), 12, 12)));

			panel.add(mainPanel,
					new Constraints(new Leading(mainSidePanel.getWidth()
							+ DEFAULT_PANEL_X, mainPanel.getWidth(), 10, 10),
							new Leading(DEFAULT_PANEL_Y, mainPanel.getHeight(),
									12, 12)));

		} else if (e.getSource() == mainMemberPagefromMainMemberSidePanel) {

			hideAllPanels();
			panel.removeAll();
			mainMemberPanel.removeAll();
			mainMemberSidePanel.removeAll();
			createMainMemberSidePanel();
			createMainMemberPanel();

			panel.add(mainMemberSidePanel, new Constraints(new Leading(0,
					mainMemberSidePanel.getWidth(), 10, 10), new Leading(0,
					mainMemberSidePanel.getHeight(), 12, 12)));

			panel.add(mainMemberPanel, new Constraints(new Leading(
					mainMemberSidePanel.getWidth() + DEFAULT_PANEL_X,
					mainMemberPanel.getWidth(), 10, 10), new Leading(
					DEFAULT_PANEL_Y, mainMemberPanel.getHeight(), 12, 12)));

		} else if (e.getSource() == addMember) {

			hideAllPanels();
			panel.removeAll();

			mainMemberSidePanel.removeAll();
			newMemberPanel.removeAll();

			createMainMemberSidePanel();
			createNewMemberPanel();
			newMemberPanel.setVisible(true);
			mainMemberSidePanel.setVisible(true);

			panel.add(mainMemberSidePanel, new Constraints(new Leading(0,
					mainMemberSidePanel.getWidth(), 10, 10), new Leading(0,
					mainMemberSidePanel.getHeight(), 12, 12)));

			panel.add(newMemberPanel, new Constraints(new Leading(
					mainMemberSidePanel.getWidth() + DEFAULT_PANEL_X,
					newMemberPanel.getWidth(), 10, 10), new Leading(
					DEFAULT_PANEL_Y, newMemberPanel.getHeight(), 10, 10)));

		} else if (e.getSource() == searchMember) {

			hideAllPanels();
			panel.removeAll();

			mainMemberSidePanel.removeAll();
			searchMemberPanel.removeAll();

			createMainMemberSidePanel();
			createSearchMemberPanel();
			searchMemberPanel.setVisible(true);
			mainMemberSidePanel.setVisible(true);

			panel.add(mainMemberSidePanel, new Constraints(new Leading(0,
					mainMemberSidePanel.getWidth(), 10, 10), new Leading(0,
					mainMemberSidePanel.getHeight(), 12, 12)));

			panel.add(searchMemberPanel, new Constraints(new Leading(
					mainMemberSidePanel.getWidth() + DEFAULT_PANEL_X,
					searchMemberPanel.getWidth(), 10, 10), new Leading(
					DEFAULT_PANEL_Y, searchMemberPanel.getHeight(), 10, 10)));

		}
		// memberSidePanel events
		else if (e.getSource() == mainPageFromMemberSidePanel) {
			hideAllPanels();
			panel.removeAll();
			memberLogPanel.removeAll();
			activityLogPanel.removeAll();
			mainPanel.removeAll();
			mainSidePanel.removeAll();

			createMainSidePanel();
			createMainPanel();

			panel.add(mainSidePanel, new Constraints(new Leading(0,
					mainSidePanel.getWidth(), 10, 10), new Leading(0,
					mainSidePanel.getHeight(), 12, 12)));

			panel.add(mainPanel,
					new Constraints(new Leading(mainSidePanel.getWidth()
							+ DEFAULT_PANEL_X, mainPanel.getWidth(), 10, 10),
							new Leading(DEFAULT_PANEL_Y, mainPanel.getHeight(),
									12, 12)));

		} else if (e.getSource() == mainMemberpageFromMemberSidePanel) {

			hideAllPanels();
			panel.removeAll();
			mainMemberPanel.removeAll();
			mainMemberSidePanel.removeAll();
			createMainMemberSidePanel();
			createMainMemberPanel();

			panel.add(mainMemberSidePanel, new Constraints(new Leading(0,
					mainMemberSidePanel.getWidth(), 10, 10), new Leading(0,
					mainMemberSidePanel.getHeight(), 12, 12)));

			panel.add(mainMemberPanel, new Constraints(new Leading(
					mainMemberSidePanel.getWidth() + DEFAULT_PANEL_X,
					mainMemberPanel.getWidth(), 10, 10), new Leading(
					DEFAULT_PANEL_Y, mainMemberPanel.getHeight(), 12, 12)));

		} else if (e.getSource() == m2aStatus) {
			String[] m2alist = c.getM2Alist(memberIDValue.getText().trim());
			if (m2aDialog == null) {
				if (m2alist != null) {
					JScrollPane scroll = new JScrollPane();
					final JList list = new JList();
					DefaultListModel listModel = new DefaultListModel();
					for (int i = 0; i < m2alist.length; i++) {
						listModel.addElement(m2alist[i]);
					}

					list.setModel(listModel);
					list.setFont(c.user.getFont());
					list.setForeground(c.user.getForegroundColor());
					list.setBackground(c.user.getBackgroundColor().brighter()
							.brighter());
					list.setSelectionBackground(c.user.getForegroundColor());
					list.setSelectionForeground(c.user.getBackgroundColor());

					scroll.setViewportView(list);
					scroll.setWheelScrollingEnabled(true);

					m2aDialog = new JDialog();
					m2aDialog.setIconImage(Toolkit.getDefaultToolkit()
							.getImage("Image/LOGO.jpg"));
					m2aDialog.setTitle(firstnameValue.getText().trim()
							+ " sin aktivitetsliste");
					m2aDialog.add(scroll);
					m2aDialog.pack();
					m2aDialog.setLocationRelativeTo(null);
					m2aDialog
							.setSize(
									list.getWidth()
											+ frameinsets.left
											+ frameinsets.left
											+ new JScrollBar()
													.getPreferredSize().width
											+ 10, (frameinsets.top
											+ frameinsets.bottom + (fontmetrics
											.getHeight() + 4) * 5));
					m2aDialog.setVisible(true);

					m2aDialog.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							m2aDialog.dispose();
							m2aDialog = null;
						}
					});
				}
			} else {
				m2aDialog.setLocationRelativeTo(null);
				m2aDialog.setVisible(true);
			}

		} else if (e.getSource() == regMemberContingent) {

			if (msg.confirmDialog("Registrere kontingentinnbetaling for\n"
					+ firstnameValue.getText() + " " + surnameValue.getText()
					+ "?") == 0) {
				if (c.regContingent(memberIDValue.getText(),
						dateStatusValue.getText())) {
					String termDate = c.getMember(
							Integer.parseInt(memberIDValue.getText()))
							.getTerminationDate();

					Calendar calendar = Calendar.getInstance();
					Calendar memberDate = Calendar.getInstance();
					memberDate.set(Integer.parseInt(termDate.substring(6)),
							Integer.parseInt(termDate.substring(3, 5)) - 1,
							Integer.parseInt(termDate.substring(0, 2)));
					calendar.set(calendar.get(Calendar.YEAR),
							calendar.get(Calendar.MONTH),
							calendar.get(Calendar.DAY_OF_MONTH));

					if (calendar.before(memberDate)) {
						hideAllPanels();
						panel.removeAll();
						showMemberPanel.removeAll();
						memberSidePanel.removeAll();
						createMemberSidePanel();
						createShowMember(c.getMember(Integer
								.parseInt(memberIDValue.getText())));

						panel.add(memberSidePanel, new Constraints(new Leading(
								0, memberSidePanel.getWidth(), 10, 10),
								new Leading(0, memberSidePanel.getHeight(), 12,
										12)));

						panel.add(
								showMemberPanel,
								new Constraints(new Leading(position
										+ DEFAULT_PANEL_X, showMemberPanel
										.getWidth(), 10, 10), new Leading(
										DEFAULT_PANEL_Y, showMemberPanel
												.getHeight(), 10, 10)));
					} else {
						hideAllPanels();
						panel.removeAll();
						showMemberPanel.removeAll();
						mainMemberSidePanel.removeAll();
						createMainMemberSidePanel();
						createShowMember(c.getMember(Integer
								.parseInt(memberIDValue.getText())));

						panel.add(mainMemberSidePanel, new Constraints(
								new Leading(0, mainMemberSidePanel.getWidth(),
										10, 10),
								new Leading(0, mainMemberSidePanel.getHeight(),
										12, 12)));

						panel.add(
								showMemberPanel,
								new Constraints(new Leading(position
										+ DEFAULT_PANEL_X, showMemberPanel
										.getWidth(), 10, 10), new Leading(
										DEFAULT_PANEL_Y, showMemberPanel
												.getHeight(), 10, 10)));
					}
				}
			}

		} else if (e.getSource() == printCard) {

			PrinterJob pj = PrinterJob.getPrinterJob();

			pj.setJobName("Medlemskort");
			pj.setPrintable(new Printer(new MembershipCard(imageURL,
					memberIDValue.getText(), firstnameValue.getText() + " "
							+ surnameValue.getText(),
					dateStatusValue.getText(), c.user.getBackgroundColor())
					.getCard()));

			if (pj.printDialog()) {
				try {
					pj.print();
				} catch (PrinterException print) {
					System.out.println(print);
				}
				c.addMemberLog(memberIDValue.getText(),
						"Skrevet ut medlemskort");

			}

			Frame[] f = JFrame.getFrames();
			for (Frame frame : f) {
				if (frame.getTitle().equals("Medlemskort")) {
					frame.dispose();
				}
			}
			hideAllPanels();
			panel.removeAll();
			showMemberPanel.removeAll();
			memberSidePanel.removeAll();
			createMemberSidePanel();
			createShowMember(c.getMember(Integer.parseInt(memberIDValue
					.getText())));

			panel.add(memberSidePanel, new Constraints(new Leading(0,
					memberSidePanel.getWidth(), 10, 10), new Leading(0,
					memberSidePanel.getHeight(), 12, 12)));

			panel.add(showMemberPanel, new Constraints(new Leading(position
					+ DEFAULT_PANEL_X, showMemberPanel.getWidth(), 10, 10),
					new Leading(DEFAULT_PANEL_Y, showMemberPanel.getHeight(),
							10, 10)));

		} else if (e.getSource() == editMember) {

			hideAllPanels();
			panel.removeAll();
			memberSidePanel.removeAll();
			createMemberSidePanel();
			createEditMember();

			panel.add(memberSidePanel, new Constraints(new Leading(0,
					memberSidePanel.getWidth(), 10, 10), new Leading(0,
					memberSidePanel.getHeight(), 12, 12)));

			panel.add(editMemberPanel, new Constraints(new Leading(
					DEFAULT_PANEL_X + memberSidePanel.getWidth(),
					editMemberPanel.getWidth(), 10, 10), new Leading(
					DEFAULT_PANEL_Y, editMemberPanel.getHeight(), 10, 10)));

		} else if (e.getSource() == terminateMembership) {

			if (msg.confirmDialog("Terminere medlemsskapet til\n"
					+ firstnameValue.getText() + " " + surnameValue.getText()
					+ " ?") == 0) {

				Member m = c.terminateMembership(memberIDValue.getText());
				if (m != null) {
					hideAllPanels();
					panel.removeAll();
					showMemberPanel.removeAll();
					mainMemberSidePanel.removeAll();
					createMainMemberSidePanel();
					createShowMember(m);

					panel.add(mainMemberSidePanel, new Constraints(new Leading(
							0, mainMemberSidePanel.getWidth(), 10, 10),
							new Leading(0, mainMemberSidePanel.getHeight(), 12,
									12)));

					panel.add(
							showMemberPanel,
							new Constraints(new Leading(position
									+ DEFAULT_PANEL_X, showMemberPanel
									.getWidth(), 10, 10), new Leading(
									DEFAULT_PANEL_Y, showMemberPanel
											.getHeight(), 10, 10)));
				}
			}

		}
		// newMemberPanel
		else if (e.getSource() == newMemberUploadImage) {
			JFrame jf = new JFrame();
			jf.setIconImage(Toolkit.getDefaultToolkit().createImage(
					"Image/LOGO.jpg"));
			JFileChooser filechooser = new JFileChooser();
			filechooser.setDialogTitle("Last opp fil");
			filechooser.setMultiSelectionEnabled(false);

			filechooser.showOpenDialog(jf);
			File f = filechooser.getSelectedFile();
			String url;
			if (f != null) {
				url = f.getPath().replaceAll("[\\\\]", "/");
				if (c.validImageURL(url)) {
					newImageURL = url;

					hideAllPanels();
					newMemberPanel.remove(newMemberImage);
					newMemberImage = new Picture(newImageURL,
							DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGTH);

					newMemberPanel.add(newMemberImage, new Constraints(
							new Leading(10, DEFAULT_IMAGE_WIDTH, 130, 130),
							new Leading(20 + getFontMetrics(newTitle.getFont())
									.getHeight(), DEFAULT_IMAGE_HEIGTH, 170,
									170)));

					mainMemberSidePanel.setVisible(true);
					newMemberPanel.setVisible(true);
				}
			}
		} else if (e.getSource() == OKnewMember) {

			if (c.newMember(newFirstnameInput.getText().trim(), newSurnameInput
					.getText().trim(), newAddressInput.getText().trim(),
					newZipcodeInput.getText().trim(), newCityInput.getText()
							.trim(), newDateOfBirthInput.getText().trim(),
					newGenderInput.getSelectedItem().toString().trim(),
					newMembershipTypeInput.getSelectedItem().toString().trim(),
					newEmailInput.getText().trim(), newMobileInput.getText()
							.trim(), newPrivateInput.getText().trim(),
					newWorkInput.getText().trim(), newImageURL)) {

				msg.messageDialog("Medlemsskapet er opprettet", 1);
				hideAllPanels();
				panel.removeAll();
				mainMemberPanel.removeAll();
				mainMemberSidePanel.removeAll();
				createMainMemberSidePanel();
				createMainMemberPanel();

				panel.add(mainMemberSidePanel, new Constraints(new Leading(0,
						mainMemberSidePanel.getWidth(), 10, 10), new Leading(0,
						mainMemberSidePanel.getHeight(), 12, 12)));

				panel.add(mainMemberPanel, new Constraints(new Leading(
						mainMemberSidePanel.getWidth() + DEFAULT_PANEL_X,
						mainMemberPanel.getWidth(), 10, 10), new Leading(
						DEFAULT_PANEL_Y, mainMemberPanel.getHeight(), 12, 12)));
			}

		} else if (e.getSource() == cancelnewMember) {

			if (msg.confirmDialog("Vil du avbryte?\nDette vil tømme skjemaet") == 0) {
				hideAllPanels();
				panel.removeAll();
				mainMemberPanel.removeAll();
				mainMemberSidePanel.removeAll();
				createMainMemberPanel();
				createMainMemberSidePanel();

				panel.add(mainMemberSidePanel, new Constraints(new Leading(0,
						mainMemberSidePanel.getWidth(), 10, 10), new Leading(0,
						mainMemberSidePanel.getHeight(), 12, 12)));

				panel.add(mainMemberPanel, new Constraints(new Leading(
						mainMemberSidePanel.getWidth() + DEFAULT_PANEL_X,
						mainMemberPanel.getWidth(), 10, 10), new Leading(
						DEFAULT_PANEL_Y, mainMemberPanel.getHeight(), 12, 12)));
			}
		}
		// searchMemberPanel
		else if (e.getSource() == OKsearchMember) {

			JTable table = c.searchMember(searchIdInput.getText().trim(),
					searchFirstnameInput.getText().trim(), searchSurnameInput
							.getText().trim(), searchAddressInput.getText()
							.trim(), searchZipcodeInput.getText().trim(),
					searchCityInput.getText().trim(), searchDateOfBirthInput
							.getText().trim(), searchGenderInput
							.getSelectedItem().toString().trim(),
					searchMembershipTypeInput.getSelectedItem().toString()
							.trim(), searchEmailInput.getText().trim(),
					searchNrInput.getText().trim(), searchStatusInput
							.getSelectedItem().toString().trim());
			if (table != null) {
				if (table.getRowCount() == 1) {
					int id = Integer.parseInt(table.getValueAt(0, 0).toString()
							.trim());
					Member m = c.getMember(id);

					String termDate = m.getTerminationDate();

					Calendar calendar = Calendar.getInstance();
					Calendar memberDate = Calendar.getInstance();
					memberDate.set(Integer.parseInt(termDate.substring(6)),
							Integer.parseInt(termDate.substring(3, 5)) - 1,
							Integer.parseInt(termDate.substring(0, 2)));
					calendar.set(calendar.get(Calendar.YEAR),
							calendar.get(Calendar.MONTH),
							calendar.get(Calendar.DAY_OF_MONTH));

					if (calendar.before(memberDate)) {
						hideAllPanels();
						panel.removeAll();
						showMemberPanel.removeAll();
						memberSidePanel.removeAll();
						createMemberSidePanel();
						createShowMember(m);

						panel.add(memberSidePanel, new Constraints(new Leading(
								0, memberSidePanel.getWidth(), 10, 10),
								new Leading(0, memberSidePanel.getHeight(), 12,
										12)));

						panel.add(
								showMemberPanel,
								new Constraints(new Leading(position
										+ DEFAULT_PANEL_X, showMemberPanel
										.getWidth(), 10, 10), new Leading(
										DEFAULT_PANEL_Y, showMemberPanel
												.getHeight(), 10, 10)));
					} else {
						hideAllPanels();
						panel.removeAll();
						showMemberPanel.removeAll();
						mainMemberSidePanel.removeAll();
						createMainMemberSidePanel();
						createShowMember(m);

						panel.add(mainMemberSidePanel, new Constraints(
								new Leading(0, mainMemberSidePanel.getWidth(),
										10, 10),
								new Leading(0, mainMemberSidePanel.getHeight(),
										12, 12)));

						panel.add(
								showMemberPanel,
								new Constraints(new Leading(position
										+ DEFAULT_PANEL_X, showMemberPanel
										.getWidth(), 10, 10), new Leading(
										DEFAULT_PANEL_Y, showMemberPanel
												.getHeight(), 10, 10)));
					}

				} else {
					hideAllPanels();
					panel.removeAll();
					memberResultPanel.removeAll();
					mainMemberSidePanel.removeAll();
					createMemberResultPanel(table);
					createMainMemberSidePanel();

					panel.add(mainMemberSidePanel, new Constraints(new Leading(
							0, mainMemberSidePanel.getWidth(), 10, 10),
							new Leading(0, mainMemberSidePanel.getHeight(), 12,
									12)));

					panel.add(memberResultPanel, new Constraints(new Leading(
							mainMemberSidePanel.getWidth() + DEFAULT_PANEL_X,
							memberResultPanel.getWidth(), 10, 10), new Leading(
							DEFAULT_PANEL_Y, mainMemberPanel.getHeight(), 12,
							12)));
				}
			}

		} else if (e.getSource() == cancelsearchMember) {

			hideAllPanels();
			panel.removeAll();
			mainMemberPanel.removeAll();
			mainMemberSidePanel.removeAll();
			createMainMemberPanel();
			createMainMemberSidePanel();

			panel.add(mainMemberSidePanel, new Constraints(new Leading(0,
					mainMemberSidePanel.getWidth(), 10, 10), new Leading(0,
					mainMemberSidePanel.getHeight(), 12, 12)));

			panel.add(mainMemberPanel, new Constraints(new Leading(
					mainMemberSidePanel.getWidth() + DEFAULT_PANEL_X,
					mainMemberPanel.getWidth(), 10, 10), new Leading(
					DEFAULT_PANEL_Y, mainMemberPanel.getHeight(), 12, 12)));

		}
		// showMemberPanel
		else if (e.getSource() == allLogEntries) {
			all = !all;
			hideAllPanels();
			panel.remove(showMemberPanel);
			showMemberPanel.removeAll();
			createShowMember(c.getMember(Integer.parseInt(memberIDValue
					.getText())));

			if (position == mainMemberSidePanel.getWidth()) {
				mainMemberSidePanel.setVisible(true);
			} else {
				memberSidePanel.setVisible(true);
			}
			panel.add(showMemberPanel, new Constraints(new Leading(position
					+ DEFAULT_PANEL_X, showMemberPanel.getWidth(), 10, 10),
					new Leading(DEFAULT_PANEL_Y, showMemberPanel.getHeight(),
							10, 10)));
		}
		// editMemberPanel
		else if (e.getSource() == editMemberUploadImage) {

			JFrame jf = new JFrame();
			jf.setIconImage(Toolkit.getDefaultToolkit().createImage(
					"Image/LOGO.jpg"));
			JFileChooser filechooser = new JFileChooser();

			filechooser.setDialogTitle("Last opp fil");
			filechooser.setMultiSelectionEnabled(false);

			filechooser.showOpenDialog(jf);
			File f = filechooser.getSelectedFile();
			String url; // = filechooser.getSelectedFile().getPath();
			if (f != null) {
				url = f.getPath().replaceAll("[\\\\]", "/");
				if (c.validImageURL(url)) {
					editImageURL = url;

					hideAllPanels();
					editMemberPanel.remove(editMemberImage);
					editMemberImage = new Picture(editImageURL,
							DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGTH);

					editMemberPanel.add(editMemberImage, new Constraints(
							new Leading(10, DEFAULT_IMAGE_WIDTH, 130, 130),
							new Leading(
									20 + getFontMetrics(editTitle.getFont())
											.getHeight(), DEFAULT_IMAGE_HEIGTH,
									170, 170)));

					memberSidePanel.setVisible(true);
					editMemberPanel.setVisible(true);
				}
			}

		} else if (e.getSource() == OKeditMember) {

			Member m = c
					.updateMember(Integer.parseInt(memberIDValue.getText()
							.trim()), editFirstnameInput.getText().trim(),
							editSurnameInput.getText().trim(), editAddressInput
									.getText().trim(), editZipcodeInput
									.getText().trim(), editCityInput.getText()
									.trim(), editDateOfBirthInput.getText()
									.trim(), editGenderInput.getSelectedItem()
									.toString().trim(), editMembershipTypeInput
									.getSelectedItem().toString().trim(),
							editEmailInput.getText().trim(), editMobileInput
									.getText().trim(), editPrivateInput
									.getText().trim(), editWorkInput.getText()
									.trim(), firstnameValue.getText().trim(),
							surnameValue.getText().trim(), addressValue
									.getText().trim(), zipcodeValue.getText()
									.trim(), cityValue.getText().trim(),
							dateOfBirthValue.getText().trim(), genderValue
									.getText().trim(), typeValue.getText()
									.trim(), emailValue.getText().trim(),
							mobileNrValue.getText().trim(), privateNrValue
									.getText().trim(), workNrValue.getText()
									.trim(), imageURL.trim(), editImageURL
									.trim());
			if (m != null) {

				hideAllPanels();
				panel.remove(showMemberPanel);
				showMemberPanel.removeAll();
				createShowMember(m);

				memberSidePanel.setVisible(true);

				panel.add(
						showMemberPanel,
						new Constraints(new Leading(position + DEFAULT_PANEL_X,
								showMemberPanel.getWidth(), 10, 10),
								new Leading(DEFAULT_PANEL_Y, showMemberPanel
										.getHeight(), 10, 10)));
			}

		} else if (e.getSource() == cancelEditMember) {

			editImageURL = imageURL;
			hideAllPanels();
			panel.removeAll();
			memberSidePanel.removeAll();
			showMemberPanel.removeAll();
			createMemberSidePanel();
			createShowMember(c.getMember(Integer.parseInt(memberIDValue
					.getText())));

			panel.add(memberSidePanel, new Constraints(new Leading(0,
					memberSidePanel.getWidth(), 10, 10), new Leading(0,
					memberSidePanel.getHeight(), 12, 12)));

			panel.add(showMemberPanel, new Constraints(new Leading(position
					+ DEFAULT_PANEL_X, showMemberPanel.getWidth(), 10, 10),
					new Leading(DEFAULT_PANEL_Y, showMemberPanel.getHeight(),
							10, 10)));

		}
		// mainActivitySidePanel
		else if (e.getSource() == mainPageFromMainActivity) {

			hideAllPanels();
			panel.removeAll();
			memberLogPanel.removeAll();
			activityLogPanel.removeAll();
			mainPanel.removeAll();
			mainSidePanel.removeAll();

			createMainSidePanel();
			createMainPanel();

			panel.add(mainSidePanel, new Constraints(new Leading(0,
					mainSidePanel.getWidth(), 10, 10), new Leading(0,
					mainSidePanel.getHeight(), 12, 12)));

			panel.add(mainPanel,
					new Constraints(new Leading(mainSidePanel.getWidth()
							+ DEFAULT_PANEL_X, mainPanel.getWidth(), 10, 10),
							new Leading(DEFAULT_PANEL_Y, mainPanel.getHeight(),
									12, 12)));

		} else if (e.getSource() == mainActivityPageFromMainActivitySidePanel) {

			hideAllPanels();
			panel.removeAll();
			mainActivityPanel.removeAll();
			mainActivitySidePanel.removeAll();
			createMainActivitySidePanel();
			createMainActivityPanel();

			panel.add(mainActivitySidePanel, new Constraints(new Leading(0,
					mainActivitySidePanel.getWidth(), 10, 10), new Leading(0,
					mainActivitySidePanel.getHeight(), 12, 12)));

			panel.add(mainActivityPanel, new Constraints(new Leading(
					mainActivitySidePanel.getWidth() + DEFAULT_PANEL_X,
					mainActivityPanel.getWidth(), 10, 10), new Leading(
					DEFAULT_PANEL_Y, mainActivityPanel.getHeight(), 12, 12)));

		} else if (e.getSource() == addActivity) {
			hideAllPanels();
			panel.removeAll();
			mainActivitySidePanel.removeAll();
			addActivityPanel.removeAll();

			createMainActivitySidePanel();
			createAddActivityPanel();

			panel.add(mainActivitySidePanel, new Constraints(new Leading(0,
					mainActivitySidePanel.getWidth(), 10, 10), new Leading(0,
					mainActivitySidePanel.getHeight(), 12, 12)));

			panel.add(addActivityPanel, new Constraints(new Leading(
					mainActivitySidePanel.getWidth() + DEFAULT_PANEL_X,
					addActivityPanel.getWidth(), 10, 10), new Leading(
					DEFAULT_PANEL_Y, addActivityPanel.getHeight(), 12, 12)));

		} else if (e.getSource() == searchActivity) {

			hideAllPanels();
			panel.removeAll();
			mainActivitySidePanel.removeAll();
			searchActivityPanel.removeAll();

			createMainActivitySidePanel();
			createSearchActivityPanel();

			panel.add(mainActivitySidePanel, new Constraints(new Leading(0,
					mainActivitySidePanel.getWidth(), 10, 10), new Leading(0,
					mainActivitySidePanel.getHeight(), 12, 12)));

			panel.add(searchActivityPanel, new Constraints(new Leading(
					mainActivitySidePanel.getWidth() + DEFAULT_PANEL_X,
					searchActivityPanel.getWidth(), 10, 10), new Leading(
					DEFAULT_PANEL_Y, searchActivityPanel.getHeight(), 12, 12)));
		}
		// addActivityPanel
		else if (e.getSource() == addAok) {
			if (c.addActivity(addANameInput.getText().trim(), addAManagerInput
					.getText().trim(), addAAdressInput.getText().trim(),
					addAZipcodeInput.getText().trim(), addACityInput.getText()
							.trim(), addASeatsInput.getText().trim(),
					addADurationStartInput.getText().trim(),
					addADurationEndInput.getText().trim(), addAallTF.getText()
							.trim(), addAallTT.getText().trim(), addAmondayTF
							.getText().trim(), addAmondayTT.getText().trim(),
					addAtuesdayTF.getText().trim(), addAtuesdayTT.getText()
							.trim(), addAwednesdayTF.getText().trim(),
					addAwednesdayTT.getText().trim(), addAthursdayTF.getText()
							.trim(), addAthursdayTT.getText().trim(),
					addAfridayTF.getText().trim(), addAfridayTT.getText()
							.trim(), addAsaturdayTF.getText().trim(),
					addAsaturdayTT.getText().trim(), addAsundayTF.getText()
							.trim(), addAsundayTT.getText().trim(),
					addADescriptionInput.getText().trim(), addAuploadPath
							.getText().trim())) {

				hideAllPanels();
				panel.removeAll();
				mainActivityPanel.removeAll();
				mainActivitySidePanel.removeAll();
				createMainActivitySidePanel();
				createMainActivityPanel();

				panel.add(mainActivitySidePanel, new Constraints(new Leading(0,
						mainActivitySidePanel.getWidth(), 10, 10), new Leading(
						0, mainActivitySidePanel.getHeight(), 12, 12)));

				panel.add(
						mainActivityPanel,
						new Constraints(new Leading(mainActivitySidePanel
								.getWidth() + DEFAULT_PANEL_X,
								mainActivityPanel.getWidth(), 10, 10),
								new Leading(DEFAULT_PANEL_Y, mainActivityPanel
										.getHeight(), 12, 12)));

			}
		} else if (e.getSource() == addAcancel) {
			if (msg.confirmDialog("Vil du avbryte?\nDette vil tømme skjemaet") == 0) {
				hideAllPanels();
				panel.removeAll();
				mainActivityPanel.removeAll();
				mainActivitySidePanel.removeAll();
				createMainActivityPanel();
				createMainActivitySidePanel();

				panel.add(mainActivitySidePanel, new Constraints(new Leading(0,
						mainActivitySidePanel.getWidth(), 10, 10), new Leading(
						0, mainActivitySidePanel.getHeight(), 12, 12)));

				panel.add(
						mainActivityPanel,
						new Constraints(new Leading(mainActivitySidePanel
								.getWidth() + DEFAULT_PANEL_X,
								mainActivityPanel.getWidth(), 10, 10),
								new Leading(DEFAULT_PANEL_Y, mainActivityPanel
										.getHeight(), 12, 12)));
			}
		} else if (e.getSource() == addAuploadFile) {
			JFrame f = new JFrame();
			f.setIconImage(Toolkit.getDefaultToolkit().createImage(
					"Image/LOGO.jpg"));
			JFileChooser filechooser = new JFileChooser();

			filechooser.setDialogTitle("Last opp fil");
			filechooser.setMultiSelectionEnabled(false);

			if (filechooser.showOpenDialog(f) == JFileChooser.APPROVE_OPTION) {
				File valgt = filechooser.getSelectedFile();
				System.out.println(valgt.getPath());
				addAuploadPath.setText(valgt.getPath()
						.replaceAll("[\\\\]", "/"));
				addActivityPanel.revalidate();
			}
		}
		// showActivityPanel
		else if (e.getSource() == showAallLogEntries) {
			showAallEntries = !showAallEntries;
			hideAllPanels();
			panel.remove(showActivityPanel);
			showActivityPanel.removeAll();
			createShowActivityPanel(c.getActivity(showAtitleLabel.getText()
					.split(" ")[0].toString()));

			if (showAposition == mainActivitySidePanel.getWidth()) {
				mainActivitySidePanel.setVisible(true);
			} else {
				activitySidePanel.setVisible(true);
			}
			panel.add(
					showActivityPanel,
					new Constraints(new Leading(
							showAposition + DEFAULT_PANEL_X, showActivityPanel
									.getWidth(), 10, 10), new Leading(
							DEFAULT_PANEL_Y, showActivityPanel.getHeight(), 10,
							10)));
		} else if (e.getSource() == showAattendance) {
			Attendance[] a = c.getActivity(
					showAtitleLabel.getText().split(" ")[0].toString())
					.getAttendance();

			JTable table;
			if (a != null) {
				String[] columnName = { "Dato", "Oppmøte", "Kommentar" };
				String[][] result = new String[a.length][3];
				for (int i = 0; i < a.length; i++) {
					result[i][0] = a[i].getDate();
					result[i][1] = a[i].getTotal();
					result[i][2] = a[i].getComment();
				}

				table = new JTable(new DefaultTableModel(result, columnName)) {
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int rowIndex, int colIndex) {
						return false; // Disallow the editing of any cell
					}
				};

			} else {
				String[] columnName = { " " };
				String[][] result = new String[1][1];
				result[0][0] = "Ingen oppføringer tilgjengelig";
				table = new JTable(new DefaultTableModel(result, columnName)) {
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int rowIndex, int colIndex) {
						return false; // Disallow the editing of any cell
					}
				};
			}

			final JDialog f = new JDialog();
			f.setTitle("Oppmøteliste");
			table.setAutoCreateRowSorter(true);
			table.setRowHeight(fontmetrics.getHeight() + 4);
			table.getTableHeader().setReorderingAllowed(false);
			table.getTableHeader().setFont(c.user.getFont());
			table.getTableHeader().setForeground(c.user.getForegroundColor());
			table.getTableHeader().setBackground(
					c.user.getBackgroundColor().darker());

			showAattW = 0;
			autoResizeColWidth(table, "attendance");

			JScrollPane scroll = new JScrollPane(table);
			f.add(new JPanel().add(scroll));
			f.setIconImage(Toolkit.getDefaultToolkit().getImage(
					"Image/LOGO.jpg"));
			f.pack();
			f.setSize(showAattW + frameinsets.left + frameinsets.right,
					((table.getRowCount() + 1) * table.getRowHeight())
							+ frameinsets.top + frameinsets.bottom);

			f.setVisible(true);
			f.setAlwaysOnTop(true);
			f.setLocationRelativeTo(null);
			f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		}
		// searchActivityPanel
		else if (e.getSource() == searchAok) {
			JTable table = c.searchActivity(searchANameInput.getText().trim(),
					searchAManagerInput.getText().trim(), searchAAdressInput
							.getText().trim(), searchAZipcodeInput.getText()
							.trim(), searchACityInput.getText().trim(),
					searchASeatsInput.getText().trim(), searchAstatusBox
							.getSelectedItem().toString().trim(),
					searchADurationStartInput.getText().trim(),
					searchADurationEndInput.getText().trim(), searchAdocName
							.getText().trim(), searchAallCB, searchAmondayCB,
					searchAtuesdayCB, searchAwednesdayCB, searchAthursdayCB,
					searchAfridayCB, searchAsaturdayCB, searchAsundayCB,
					this.fontmetrics);
			if (table != null) {
				if (table.getRowCount() == 1) {
					Activity a = c.getActivity(table.getValueAt(0, 0)
							.toString().trim());
					if (table.getValueAt(0, 8).toString().trim()
							.equals("Aktiv")) {
						hideAllPanels();
						panel.removeAll();
						showActivityPanel.removeAll();
						activitySidePanel.removeAll();
						createActivitySidePanel();
						showAallEntries = false;
						createShowActivityPanel(a);

						panel.add(activitySidePanel, new Constraints(
								new Leading(0, activitySidePanel.getWidth(),
										10, 10), new Leading(0,
										activitySidePanel.getHeight(), 12, 12)));

						panel.add(
								showActivityPanel,
								new Constraints(new Leading(showAposition
										+ DEFAULT_PANEL_X, showActivityPanel
										.getWidth(), 10, 10), new Leading(
										DEFAULT_PANEL_Y, showActivityPanel
												.getHeight(), 10, 10)));

					} else {
						hideAllPanels();
						panel.removeAll();
						showActivityPanel.removeAll();
						mainActivitySidePanel.removeAll();
						createMainActivitySidePanel();
						showAallEntries = false;
						createShowActivityPanel(a);

						panel.add(mainActivitySidePanel, new Constraints(
								new Leading(0,
										mainActivitySidePanel.getWidth(), 10,
										10), new Leading(0,
										mainActivitySidePanel.getHeight(), 12,
										12)));

						panel.add(
								showActivityPanel,
								new Constraints(new Leading(showAposition
										+ DEFAULT_PANEL_X, showActivityPanel
										.getWidth(), 10, 10), new Leading(
										DEFAULT_PANEL_Y, showActivityPanel
												.getHeight(), 10, 10)));

					}

				} else {
					hideAllPanels();
					panel.removeAll();
					activityResultPanel.removeAll();
					mainActivitySidePanel.removeAll();
					createActivityResultPanel(table);
					createMainActivitySidePanel();

					panel.add(mainActivitySidePanel, new Constraints(
							new Leading(0, mainActivitySidePanel.getWidth(),
									10, 10), new Leading(0,
									mainActivitySidePanel.getHeight(), 12, 12)));

					panel.add(
							activityResultPanel,
							new Constraints(new Leading(mainActivitySidePanel
									.getWidth() + DEFAULT_PANEL_X,
									activityResultPanel.getWidth(), 10, 10),
									new Leading(DEFAULT_PANEL_Y,
											mainActivityPanel.getHeight(), 12,
											12)));
				}
			}
		} else if (e.getSource() == searchAcancel) {
			hideAllPanels();
			panel.removeAll();
			mainActivityPanel.removeAll();
			mainActivitySidePanel.removeAll();
			createMainActivityPanel();
			createMainActivitySidePanel();

			panel.add(mainActivitySidePanel, new Constraints(new Leading(0,
					mainActivitySidePanel.getWidth(), 10, 10), new Leading(0,
					mainActivitySidePanel.getHeight(), 12, 12)));

			panel.add(mainActivityPanel, new Constraints(new Leading(
					mainActivitySidePanel.getWidth() + DEFAULT_PANEL_X,
					mainActivityPanel.getWidth(), 10, 10), new Leading(
					DEFAULT_PANEL_Y, mainActivityPanel.getHeight(), 12, 12)));
		}
		// activitySidePanel
		else if (e.getSource() == mainPageFromActivitySidePanel) {
			hideAllPanels();
			panel.removeAll();
			memberLogPanel.removeAll();
			activityLogPanel.removeAll();
			mainPanel.removeAll();
			mainSidePanel.removeAll();

			createMainSidePanel();
			createMainPanel();

			panel.add(mainSidePanel, new Constraints(new Leading(0,
					mainSidePanel.getWidth(), 10, 10), new Leading(0,
					mainSidePanel.getHeight(), 12, 12)));

			panel.add(mainPanel,
					new Constraints(new Leading(mainSidePanel.getWidth()
							+ DEFAULT_PANEL_X, mainPanel.getWidth(), 10, 10),
							new Leading(DEFAULT_PANEL_Y, mainPanel.getHeight(),
									12, 12)));
		} else if (e.getSource() == mainActivityPageFromActivitySidePanel) {
			hideAllPanels();
			panel.removeAll();
			mainActivityPanel.removeAll();
			mainActivitySidePanel.removeAll();
			createMainActivitySidePanel();
			createMainActivityPanel();

			panel.add(mainActivitySidePanel, new Constraints(new Leading(0,
					mainActivitySidePanel.getWidth(), 10, 10), new Leading(0,
					mainActivitySidePanel.getHeight(), 12, 12)));

			panel.add(mainActivityPanel, new Constraints(new Leading(
					mainActivitySidePanel.getWidth() + DEFAULT_PANEL_X,
					mainActivityPanel.getWidth(), 10, 10), new Leading(
					DEFAULT_PANEL_Y, mainActivityPanel.getHeight(), 12, 12)));
		} else if (e.getSource() == regInterest) {
			if (memberlistDialog == null) {
				String[] memberlist = c.getMemberList();
				if (memberlist != null) {
					JScrollPane scroll = new JScrollPane();
					final JList list = new JList();
					DefaultListModel listModel = new DefaultListModel();
					for (int i = 0; i < memberlist.length; i++) {
						listModel.addElement(memberlist[i]);
					}

					list.setModel(listModel);
					list.setFont(c.user.getFont());
					list.setForeground(c.user.getForegroundColor());
					list.setBackground(c.user.getBackgroundColor().brighter()
							.brighter());
					list.setSelectionBackground(c.user.getForegroundColor());
					list.setSelectionForeground(c.user.getBackgroundColor());

					scroll.setViewportView(list);
					scroll.setWheelScrollingEnabled(true);

					memberlistDialog = new JDialog();
					memberlistDialog.setIconImage(Toolkit.getDefaultToolkit()
							.getImage("Image/LOGO.jpg"));
					memberlistDialog.setTitle("Medlemsliste");
					memberlistDialog.add(scroll);
					memberlistDialog.pack();
					memberlistDialog.setLocationRelativeTo(null);
					memberlistDialog
							.setSize(
									list.getWidth()
											+ frameinsets.left
											+ frameinsets.left
											+ new JScrollBar()
													.getPreferredSize().width
											+ 10, (frameinsets.top
											+ frameinsets.bottom + (fontmetrics
											.getHeight() + 4) * 5));
					memberlistDialog.setVisible(true);

					memberlistDialog.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							memberlistDialog.dispose();
							memberlistDialog = null;
						}
					});
					list.addListSelectionListener(new ListSelectionListener() {

						@Override
						public void valueChanged(ListSelectionEvent e) {
							if (msg.confirmDialog("Melde på "
									+ list.getSelectedValue().toString() + "?") == 0) {
								if (c.addRegistration(list.getSelectedValue()
										.toString(), showAtitleLabel.getText())) {
									memberlistDialog.dispose();
									memberlistDialog = null;
									hideAllPanels();
									panel.removeAll();
									showActivityPanel.removeAll();
									activitySidePanel.removeAll();
									createActivitySidePanel();
									showAallEntries = false;
									createShowActivityPanel(c
											.getActivity(showAtitleLabel
													.getText().split(" ")[0]));

									panel.add(
											activitySidePanel,
											new Constraints(
													new Leading(
															0,
															activitySidePanel
																	.getWidth(),
															10, 10),
													new Leading(
															0,
															activitySidePanel
																	.getHeight(),
															12, 12)));

									panel.add(
											showActivityPanel,
											new Constraints(
													new Leading(
															showAposition
																	+ DEFAULT_PANEL_X,
															showActivityPanel
																	.getWidth(),
															10, 10),
													new Leading(
															DEFAULT_PANEL_Y,
															showActivityPanel
																	.getHeight(),
															10, 10)));
								}
							}
						}
					});
				}
			} else {
				memberlistDialog.setLocationRelativeTo(null);
				memberlistDialog.setVisible(true);
			}

		} else if (e.getSource() == removeInterest) {
			if (removeInterestDialog == null) {
				String[] memberlist = c.getInterestList(showAtitleLabel
						.getText().split(" ")[0]);
				if (memberlist != null) {
					JScrollPane scroll = new JScrollPane();
					final JList list = new JList();
					DefaultListModel listModel = new DefaultListModel();
					for (int i = 0; i < memberlist.length; i++) {
						listModel.addElement(memberlist[i]);
					}

					list.setModel(listModel);
					list.setFont(c.user.getFont());
					list.setForeground(c.user.getForegroundColor());
					list.setBackground(c.user.getBackgroundColor().brighter()
							.brighter());
					list.setSelectionBackground(c.user.getForegroundColor());
					list.setSelectionForeground(c.user.getBackgroundColor());

					scroll.setViewportView(list);
					scroll.setWheelScrollingEnabled(true);

					removeInterestDialog = new JDialog();
					removeInterestDialog.setIconImage(Toolkit
							.getDefaultToolkit().getImage("Image/LOGO.jpg"));
					removeInterestDialog.setTitle("Medlemsliste");
					removeInterestDialog.add(scroll);
					removeInterestDialog.pack();
					removeInterestDialog.setLocationRelativeTo(null);
					removeInterestDialog
							.setSize(
									list.getWidth()
											+ frameinsets.left
											+ frameinsets.left
											+ new JScrollBar()
													.getPreferredSize().width
											+ 10, (frameinsets.top
											+ frameinsets.bottom + (fontmetrics
											.getHeight() + 4) * 5));
					removeInterestDialog.setVisible(true);

					removeInterestDialog.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							removeInterestDialog.dispose();
							removeInterestDialog = null;
						}
					});
					list.addListSelectionListener(new ListSelectionListener() {

						@Override
						public void valueChanged(ListSelectionEvent e) {
							if (msg.confirmDialog("Avmelde "
									+ list.getSelectedValue().toString() + "?") == 0) {
								if (c.removeRegistration(list
										.getSelectedValue().toString(),
										showAtitleLabel.getText())) {
									removeInterestDialog.dispose();
									removeInterestDialog = null;
									hideAllPanels();
									panel.removeAll();
									showActivityPanel.removeAll();
									activitySidePanel.removeAll();
									createActivitySidePanel();
									showAallEntries = false;
									createShowActivityPanel(c
											.getActivity(showAtitleLabel
													.getText().split(" ")[0]));

									panel.add(
											activitySidePanel,
											new Constraints(
													new Leading(
															0,
															activitySidePanel
																	.getWidth(),
															10, 10),
													new Leading(
															0,
															activitySidePanel
																	.getHeight(),
															12, 12)));

									panel.add(
											showActivityPanel,
											new Constraints(
													new Leading(
															showAposition
																	+ DEFAULT_PANEL_X,
															showActivityPanel
																	.getWidth(),
															10, 10),
													new Leading(
															DEFAULT_PANEL_Y,
															showActivityPanel
																	.getHeight(),
															10, 10)));
								}
							}
						}
					});
				}
			} else {
				removeInterestDialog.setLocationRelativeTo(null);
				removeInterestDialog.setVisible(true);
			}
		} else if (e.getSource() == regAttendance) {

			hideAllPanels();
			panel.removeAll();
			regAttendancePanel.removeAll();
			activitySidePanel.removeAll();
			createActivitySidePanel();
			createRegAttendancePanel();

			panel.add(activitySidePanel, new Constraints(new Leading(0,
					activitySidePanel.getWidth(), 10, 10), new Leading(0,
					activitySidePanel.getHeight(), 12, 12)));

			panel.add(regAttendancePanel, new Constraints(new Leading(
					activitySidePanel.getWidth() + DEFAULT_PANEL_X,
					regAttendancePanel.getWidth(), 10, 10), new Leading(
					DEFAULT_PANEL_Y, regAttendancePanel.getHeight(), 12, 12)));

		} else if (e.getSource() == editActivity) {
			String[] name = showAtitleLabel.getText().split(" ");
			hideAllPanels();
			panel.removeAll();
			editActivityPanel.removeAll();
			activitySidePanel.removeAll();
			createEditActivityPanel(c.getActivity(name[0]));
			createActivitySidePanel();

			panel.add(activitySidePanel, new Constraints(new Leading(0,
					activitySidePanel.getWidth(), 10, 10), new Leading(0,
					activitySidePanel.getHeight(), 12, 12)));

			panel.add(editActivityPanel, new Constraints(new Leading(
					activitySidePanel.getWidth() + DEFAULT_PANEL_X,
					editActivityPanel.getWidth(), 10, 10), new Leading(
					DEFAULT_PANEL_Y, editActivityPanel.getHeight(), 12, 12)));

		} else if (e.getSource() == terminateActivity) {
			String[] name = showAtitleLabel.getText().split(" : ");
			if (msg.confirmDialog("Avslutte aktiviteten\n"
					+ name[name.length - 1] + " ?") == 0) {
				String comment = msg.inputDialog("Avslutt aktivitet",
						"Kommentar");
				if (comment != null) {
					Activity a = c.terminateActivity(name[0], comment);
					if (a != null) {
						hideAllPanels();
						panel.removeAll();
						showActivityPanel.removeAll();
						mainActivitySidePanel.removeAll();
						createMainActivitySidePanel();
						createShowActivityPanel(a);

						panel.add(mainActivitySidePanel, new Constraints(
								new Leading(0,
										mainActivitySidePanel.getWidth(), 10,
										10), new Leading(0,
										mainActivitySidePanel.getHeight(), 12,
										12)));

						panel.add(
								showActivityPanel,
								new Constraints(new Leading(showAposition
										+ DEFAULT_PANEL_X, showActivityPanel
										.getWidth(), 10, 10), new Leading(
										DEFAULT_PANEL_Y, showActivityPanel
												.getHeight(), 10, 10)));
					}
				}
			}

		}
		// regAttendancePanel
		else if (e.getSource() == regAttOK) {

			if (c.addActivityAttendance(showAtitleLabel.getText().split(" ")[0]
					.trim(), showAdurationValue.getText().trim(),
					regAttDateInput.getText().trim(), regAttNrInput.getText()
							.trim(), regAttCommentInput.getText().trim())) {
				regAttCancel.doClick();
			}

		} else if (e.getSource() == regAttCancel) {
			showAallEntries = false;
			hideAllPanels();
			panel.remove(showActivityPanel);
			showActivityPanel.removeAll();
			createShowActivityPanel(c.getActivity(showAtitleLabel.getText()
					.split(" ")[0].toString()));

			activitySidePanel.setVisible(true);

			panel.add(
					showActivityPanel,
					new Constraints(new Leading(
							showAposition + DEFAULT_PANEL_X, showActivityPanel
									.getWidth(), 10, 10), new Leading(
							DEFAULT_PANEL_Y, showActivityPanel.getHeight(), 10,
							10)));
		}
		// editActivityPanel
		else if (e.getSource() == editAuploadFile) {

			JFrame f = new JFrame();
			f.setIconImage(Toolkit.getDefaultToolkit().createImage(
					"Image/LOGO.jpg"));
			JFileChooser filechooser = new JFileChooser();

			filechooser.setDialogTitle("Last opp fil");
			filechooser.setMultiSelectionEnabled(false);

			if (filechooser.showOpenDialog(f) == JFileChooser.APPROVE_OPTION) {
				File valgt = filechooser.getSelectedFile();
				editAuploadPath.setText(valgt.getPath().replaceAll("[\\\\]",
						"/"));
				editActivityPanel.revalidate();
			}
		} else if (e.getSource() == editAok) {
			String[] name = showAtitleLabel.getText().split(" : ");
			String[] adr = showAadrValue.getText().split("<br/>");
			String[] duration = showAdurationValue.getText().split(" - ");

			Activity a = c.editActivity(name[0], editANameInput.getText()
					.trim(), editAManagerInput.getText().trim(),
					editAAdressInput.getText().trim(), editAZipcodeInput
							.getText().trim(), editACityInput.getText().trim(),
					editASeatsInput.getText().trim(), editADurationStartInput
							.getText().trim(), editADurationEndInput.getText()
							.trim(), editAallTF.getText().trim(), editAallTT
							.getText().trim(), editAmondayTF.getText().trim(),
					editAmondayTT.getText().trim(), editAtuesdayTF.getText()
							.trim(), editAtuesdayTT.getText().trim(),
					editAwednesdayTF.getText().trim(), editAwednesdayTT
							.getText().trim(),
					editAthursdayTF.getText().trim(), editAthursdayTT.getText()
							.trim(), editAfridayTF.getText().trim(),
					editAfridayTT.getText().trim(), editAsaturdayTF.getText()
							.trim(), editAsaturdayTT.getText().trim(),
					editAsundayTF.getText().trim(), editAsundayTT.getText()
							.trim(), editADescriptionInput.getText().trim(),
					editAuploadPath.getText().trim(), name[name.length - 1],
					showAmanagerValue.getText().trim(), adr[0].substring(6),
					adr[1].substring(0, 4),
					adr[1].substring(5, adr[1].length() - 7).trim(),
					showAtotalSeatsValue.getText().trim(), duration[0].trim(),
					duration[1].substring(0, 10).trim(), showAdesc.getText()
							.trim(), showApath);
			if (a != null) {
				hideAllPanels();
				panel.removeAll();
				activitySidePanel.removeAll();
				showActivityPanel.removeAll();
				createActivitySidePanel();

				createShowActivityPanel(c.getActivity(name[0]));

				panel.add(activitySidePanel, new Constraints(new Leading(0,
						activitySidePanel.getWidth(), 10, 10), new Leading(0,
						activitySidePanel.getHeight(), 12, 12)));

				panel.add(
						showActivityPanel,
						new Constraints(new Leading(showAposition
								+ DEFAULT_PANEL_X,
								showActivityPanel.getWidth(), 10, 10),
								new Leading(DEFAULT_PANEL_Y, showActivityPanel
										.getHeight(), 10, 10)));
			}
		} else if (e.getSource() == editAcancel) {
			hideAllPanels();
			panel.removeAll();
			activitySidePanel.removeAll();
			showActivityPanel.removeAll();
			createActivitySidePanel();

			String[] name = showAtitleLabel.getText().split(" ");

			createShowActivityPanel(c.getActivity(name[0]));

			panel.add(activitySidePanel, new Constraints(new Leading(0,
					activitySidePanel.getWidth(), 10, 10), new Leading(0,
					activitySidePanel.getHeight(), 12, 12)));

			panel.add(
					showActivityPanel,
					new Constraints(new Leading(
							showAposition + DEFAULT_PANEL_X, showActivityPanel
									.getWidth(), 10, 10), new Leading(
							DEFAULT_PANEL_Y, showActivityPanel.getHeight(), 10,
							10)));
		}
		// mainstatistiscsSidePanel
		else if (e.getSource() == mainPageFromMainStatistics) {

			hideAllPanels();
			panel.removeAll();
			memberLogPanel.removeAll();
			activityLogPanel.removeAll();
			mainPanel.removeAll();
			mainSidePanel.removeAll();

			createMainSidePanel();
			createMainPanel();

			panel.add(mainSidePanel, new Constraints(new Leading(0,
					mainSidePanel.getWidth(), 10, 10), new Leading(0,
					mainSidePanel.getHeight(), 12, 12)));

			panel.add(mainPanel,
					new Constraints(new Leading(mainSidePanel.getWidth()
							+ DEFAULT_PANEL_X, mainPanel.getWidth(), 10, 10),
							new Leading(DEFAULT_PANEL_Y, mainPanel.getHeight(),
									12, 12)));

		} else if (e.getSource() == mainStatisticPageFromMainStatistics) {

			hideAllPanels();
			panel.removeAll();
			mainStatisticsPanel.removeAll();
			mainStatisticsSidePanel.removeAll();
			createMainStatisticsSidePanel();
			createMainStatisticsPanel();

			panel.add(mainStatisticsSidePanel, new Constraints(new Leading(0,
					mainStatisticsSidePanel.getWidth(), 10, 10), new Leading(0,
					mainStatisticsSidePanel.getHeight(), 12, 12)));

			panel.add(mainStatisticsPanel, new Constraints(new Leading(
					DEFAULT_PANEL_X + mainStatisticsSidePanel.getWidth(),
					mainStatisticsPanel.getWidth(), 10, 10), new Leading(
					DEFAULT_PANEL_Y, mainStatisticsPanel.getHeight(), 12, 12)));

		} else if (e.getSource() == mainMemberStatistics) {

			hideAllPanels();
			panel.removeAll();
			mainStatisticsSidePanel.removeAll();
			memberStatisticsPanel.removeAll();
			createMainStatisticsSidePanel();
			createMemberStatisticsPanel(null);

			panel.add(
					memberStatisticsPanel,
					new Constraints(new Leading(DEFAULT_PANEL_X
							+ mainStatisticsSidePanel.getWidth(),
							memberStatisticsPanel.getWidth(), 10, 10),
							new Leading(DEFAULT_PANEL_Y, memberStatisticsPanel
									.getHeight(), 12, 12)));

			panel.add(mainStatisticsSidePanel, new Constraints(new Leading(0,
					mainStatisticsSidePanel.getWidth(), 10, 10), new Leading(0,
					mainStatisticsSidePanel.getHeight(), 12, 12)));

		} else if (e.getSource() == mainActivityStatistics) {

			hideAllPanels();
			panel.removeAll();
			mainStatisticsSidePanel.removeAll();
			activityStatisticsPanel.removeAll();
			createMainStatisticsSidePanel();
			createActivityStatisticsPanel(null);

			panel.add(activityStatisticsPanel, new Constraints(new Leading(
					DEFAULT_PANEL_X + mainStatisticsSidePanel.getWidth(),
					activityStatisticsPanel.getWidth(), 10, 10), new Leading(
					DEFAULT_PANEL_Y, activityStatisticsPanel.getHeight(), 12,
					12)));

			panel.add(mainStatisticsSidePanel, new Constraints(new Leading(0,
					mainStatisticsSidePanel.getWidth(), 10, 10), new Leading(0,
					mainStatisticsSidePanel.getHeight(), 12, 12)));

		}
		// memberStatisticsPanel
		else if (e.getSource() == mStatButton) {
			JTable[] result = c.getMemberStatistic(mStatInput.getText().trim(),
					fontmetrics);
			if (result != null) {
				mstatYear = Integer.parseInt(mStatInput.getText().trim());
				hideAllPanels();
				panel.removeAll();
				mainStatisticsSidePanel.removeAll();
				memberStatisticsPanel.removeAll();
				createMainStatisticsSidePanel();
				createMemberStatisticsPanel(result);

				panel.add(memberStatisticsPanel, new Constraints(new Leading(
						DEFAULT_PANEL_X + mainStatisticsSidePanel.getWidth(),
						memberStatisticsPanel.getWidth(), 10, 10), new Leading(
						DEFAULT_PANEL_Y, memberStatisticsPanel.getHeight(), 12,
						12)));

				panel.add(mainStatisticsSidePanel, new Constraints(new Leading(
						0, mainStatisticsSidePanel.getWidth(), 10, 10),
						new Leading(0, mainStatisticsSidePanel.getHeight(), 12,
								12)));
			}

		}
		// activityStatisticsPanel
		else if (e.getSource() == aStatButton) {
			JTable[] result = c.getActivityStatistic(aStatInput.getText()
					.trim(), fontmetrics);
			if (result != null) {
				astatYear = Integer.parseInt(aStatInput.getText().trim());
				hideAllPanels();
				panel.removeAll();
				mainStatisticsSidePanel.removeAll();
				activityStatisticsPanel.removeAll();
				createMainStatisticsSidePanel();
				createActivityStatisticsPanel(result);

				panel.add(activityStatisticsPanel,
						new Constraints(new Leading(DEFAULT_PANEL_X
								+ mainStatisticsSidePanel.getWidth(),
								activityStatisticsPanel.getWidth(), 10, 10),
								new Leading(DEFAULT_PANEL_Y,
										activityStatisticsPanel.getHeight(),
										12, 12)));

				panel.add(mainStatisticsSidePanel, new Constraints(new Leading(
						0, mainStatisticsSidePanel.getWidth(), 10, 10),
						new Leading(0, mainStatisticsSidePanel.getHeight(), 12,
								12)));
			}

		}
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	public void keyPressed(KeyEvent e) {
		// first check for all possible key's
		// that is legal regardless of the visible panels
		if (e.getKeyCode() == KeyEvent.VK_F1) {
			helpMenuItem.doClick();
		} else { // event depending on the visible panel
			if (editMemberPanel.isVisible()) {
				if (e.getSource() == cancelEditMember) {
					cancelEditMember.doClick();
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					OKeditMember.doClick();
				}
			} else if (newMemberPanel.isVisible()) {
				if (e.getSource() == cancelnewMember) {
					cancelnewMember.doClick();
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					OKnewMember.doClick();
				}
			} else if (searchMemberPanel.isVisible()) {
				if (e.getSource() == cancelsearchMember) {
					cancelsearchMember.doClick();
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					OKsearchMember.doClick();
				}
			} else if (addActivityPanel.isVisible()) {
				if (e.getSource() == addAcancel) {
					addAcancel.doClick();
				} else if (e.getSource() == addAuploadFile) {
					addAuploadFile.doClick();
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					addAok.doClick();
				}
			} else if (searchActivityPanel.isVisible()) {
				if (e.getSource() == searchAcancel) {
					searchAcancel.doClick();
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					searchAok.doClick();
				}
			} else if (editActivityPanel.isVisible()) {
				if (e.getSource() == editAcancel) {
					editAcancel.doClick();
				} else if (e.getSource() == editAuploadFile) {
					editAuploadFile.doClick();
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					editAok.doClick();
				}
			} else if (regAttendancePanel.isVisible()) {
				if (e.getSource() == regAttCancel) {
					regAttCancel.doClick();
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					regAttOK.doClick();
				}
			} else if (memberStatisticsPanel.isVisible()) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					mStatButton.doClick();
				}
			} else if (activityStatisticsPanel.isVisible()) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					aStatButton.doClick();
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public class Hyperlink implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == showMemberImage
					| e.getSource() == editMemberImage) {
				final JFrame frame = new JFrame();
				ImageIcon icon = new ImageIcon(imageURL);
				int w = (int) (icon.getIconWidth() + 6 < dim.getWidth() ? icon
						.getIconWidth() : dim.getWidth() - 6);
				int h = (int) (icon.getIconHeight()
						+ 28
						+ Toolkit.getDefaultToolkit().getScreenInsets(
								getGraphicsConfiguration()).bottom < dim
						.getHeight() ? icon.getIconHeight() : dim.getHeight()
						- 28
						- Toolkit.getDefaultToolkit().getScreenInsets(
								getGraphicsConfiguration()).bottom);
				frame.add(new Picture(imageURL, w, h));
				frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
						"Image/LOGO.jpg"));
				frame.setSize(w + 6, h + 28);
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);
				frame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						frame.dispose();
					}
				});
				frame.setVisible(true);
			} else if (e.getSource() == editImageRemove) {
				editImageURL = "Image/Unknown.jpg";

				hideAllPanels();
				editMemberPanel.remove(editMemberImage);
				editMemberImage = new Picture(editImageURL,
						DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGTH);

				editMemberPanel.add(editMemberImage, new Constraints(
						new Leading(10, DEFAULT_IMAGE_WIDTH, 130, 130),
						new Leading(20 + getFontMetrics(editTitle.getFont())
								.getHeight(), DEFAULT_IMAGE_HEIGTH, 170, 170)));

				memberSidePanel.setVisible(true);
				editMemberPanel.setVisible(true);
			} else if (e.getSource() == newImageRemove) {
				newImageURL = "Image/Unknown.jpg";

				hideAllPanels();
				newMemberPanel.remove(newMemberImage);
				newMemberImage = new Picture(newImageURL, DEFAULT_IMAGE_WIDTH,
						DEFAULT_IMAGE_HEIGTH);

				newMemberPanel.add(newMemberImage, new Constraints(new Leading(
						10, DEFAULT_IMAGE_WIDTH, 130, 130), new Leading(
						20 + getFontMetrics(newTitle.getFont()).getHeight(),
						DEFAULT_IMAGE_HEIGTH, 170, 170)));

				mainMemberSidePanel.setVisible(true);
				newMemberPanel.setVisible(true);
			} else if (e.getSource() == showAdoc) {
				try {
					java.awt.Desktop.getDesktop().open(new File(showApath));
				} catch (IOException io) {
					msg.messageDialog("Filen finnes ikke lenger", 0);
				} catch (IllegalArgumentException ie) {
					msg.messageDialog("Filen finnes ikke lenger", 0);
				}
			} else if (e.getSource() == regAttDatePicker) {
				regAttDateInput.setText(new DatePicker(fontmetrics)
						.setPickedDate());
			} else if (e.getSource() == mainStatUserLink) {
				JDialog dialog = new JDialog();
				dialog.setTitle("Brukerinformasjon");
				dialog.setIconImage(Toolkit.getDefaultToolkit().getImage(
						"Image/LOGO.jpg"));
				dialog.setAlwaysOnTop(true);
				JPanel p = new JPanel();
				p.setLayout(new GroupLayout());
				int y = 10;
				JLabel username = new JLabel("Brukernavn ");
				username.setFont(new Font(c.user.getFamily(), 1, c.user
						.getSize()));
				p.add(username, new Constraints(new Leading(10, 10, 10),
						new Leading(y, 10, 10)));

				y += 10 + getFontMetrics(
						new Font(c.user.getFamily(), 1, c.user.getSize()))
						.getHeight();
				JLabel firstname = new JLabel("Fornavn ");
				firstname.setFont(new Font(c.user.getFamily(), 1, c.user
						.getSize()));
				p.add(firstname, new Constraints(new Leading(10, 10, 10),
						new Leading(y, 10, 10)));

				y += 10 + getFontMetrics(
						new Font(c.user.getFamily(), 1, c.user.getSize()))
						.getHeight();
				JLabel surname = new JLabel("Etternavn ");
				surname.setFont(new Font(c.user.getFamily(), 1, c.user
						.getSize()));
				p.add(surname, new Constraints(new Leading(10, 10, 10),
						new Leading(y, 10, 10)));

				y += 10 + getFontMetrics(
						new Font(c.user.getFamily(), 1, c.user.getSize()))
						.getHeight();
				JLabel date = new JLabel("Fødselsdato ");
				date.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
				p.add(date, new Constraints(new Leading(10, 10, 10),
						new Leading(y, 10, 10)));

				y += 10 + getFontMetrics(
						new Font(c.user.getFamily(), 1, c.user.getSize()))
						.getHeight();
				JLabel tlf = new JLabel("Telefon ");
				tlf.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
				p.add(tlf, new Constraints(new Leading(10, 10, 10),
						new Leading(y, 10, 10)));

				y = 10;
				int x = getFontMetrics(
						new Font(c.user.getFamily(), 1, c.user.getSize()))
						.stringWidth(" Fødselsdato ") + 10;
				p.add(new JLabel(c.user.getUsername()), new Constraints(
						new Leading(x, 10, 10), new Leading(y, 10, 10)));
				y += 10 + getFontMetrics(
						new Font(c.user.getFamily(), 1, c.user.getSize()))
						.getHeight();
				p.add(new JLabel(c.user.getFirstname()), new Constraints(
						new Leading(x, 10, 10), new Leading(y, 10, 10)));
				y += 10 + getFontMetrics(
						new Font(c.user.getFamily(), 1, c.user.getSize()))
						.getHeight();
				p.add(new JLabel(c.user.getSurname()), new Constraints(
						new Leading(x, 10, 10), new Leading(y, 10, 10)));
				y += 10 + getFontMetrics(
						new Font(c.user.getFamily(), 1, c.user.getSize()))
						.getHeight();
				p.add(new JLabel(c.user.getdateofBirth()), new Constraints(
						new Leading(x, 10, 10), new Leading(y, 10, 10)));
				y += 10 + getFontMetrics(
						new Font(c.user.getFamily(), 1, c.user.getSize()))
						.getHeight();
				p.add(new JLabel(c.user.getPhonenumber()), new Constraints(
						new Leading(x, 10, 10), new Leading(y, 10, 10)));

				dialog.add(p);
				dialog.pack();
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);

			} else if (e.getSource() == addAstartCal) {
				addADurationStartInput.setText(new DatePicker(fontmetrics)
						.setPickedDate());
			} else if (e.getSource() == addAendCal) {
				addADurationEndInput.setText(new DatePicker(fontmetrics)
						.setPickedDate());
			} else if (e.getSource() == searchAstartCal) {
				searchADurationStartInput.setText(new DatePicker(fontmetrics)
						.setPickedDate());
			} else if (e.getSource() == searchAendCal) {
				searchADurationEndInput.setText(new DatePicker(fontmetrics)
						.setPickedDate());
			} else if (e.getSource() == editAstartCal) {
				editADurationStartInput.setText(new DatePicker(fontmetrics)
						.setPickedDate());
			} else if (e.getSource() == editAendCal) {
				editADurationEndInput.setText(new DatePicker(fontmetrics)
						.setPickedDate());
			}

		}

		public void mouseEntered(MouseEvent e) {
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		}

		public void mouseExited(MouseEvent e) {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

		}

		public void mousePressed(MouseEvent e) {

		}

		public void mouseReleased(MouseEvent e) {

		}

	}

}
