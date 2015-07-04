package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import model.Manager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import control.Control;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Sabba This class contains everything needed for the administrator to
 *         interact with the database using a graphical user interface
 */
public class AdministrationPage extends JFrame implements ActionListener,
		KeyListener, interfaces.I_AdministrationPage {

	private static final long serialVersionUID = 1L;

	private JMenuBar menubar;
	private JMenu fileMenu, helpMenu, closeMenu, toolsMenu;
	private JMenuItem shutdownMenuItem, logOffMenuItem, aboutMenuItem,
			helpMenuItem, settingsMenuItem;
	private JPanel panel, mainSidePanel, mainPanel, newUserSidePanel,
			newUserPanel, updateUserSidePanel, updateUserPanel,
			deleteUserSidePanel, deleteUserPanel, searchPanel, resultPanel,
			oneUpdatePanel;
	private JButton search, newUser, mainPagefromNew, updateUser, updateUser2,
			mainPagefromUpdate, updateSearch, deleteUser, delUser,
			mainPagefromDelete, deleteSearch;
	private JLabel dateLabel;
	private int DEFAULT_HEIGTH, DEFAULT_PANEL_X, DEFAULT_PANEL_Y,
			DEFAULT_PANEL_W, DEFAULT_PANEL_H;

	private Dimension dim;
	private FontMetrics fontmetrics;
	private Insets insets, frameinsets;
	final Control c = new Control();
	final Message msg;

	// component used for registration of a new user
	private JLabel newUserTitleLabel, usernameLabel, passwordLabel,
			password2Label, typeLabel, firstnameLabel, surnameLabel,
			dateOfBirthLabel, nrLabel;
	private JTextField usernameInput, firstnameInput, surnameInput,
			dateOfBirthInput, nrInput;
	private JPasswordField passwordInput, password2Input;
	private JComboBox typeInput;
	private JButton addUserButton, cancelAddUserButton;

	// component used for user account-search
	private JLabel searchTitleLabel, searchUsernameLabel, searchTypeLabel,
			searchFirstnameLabel, searchSurnameLabel, searchDateOfBirthLabel,
			searchNrLabel;
	private JTextField searchUsernameInput, searchFirstnameInput,
			searchSurnameInput, searchDateOfBirthInput, searchNrInput;
	private JComboBox searchTypeInput;
	private JButton searchButton, cancelSearchButton;
	private int searchHeigth;

	// component used for updateUserPanel
	private JLabel updateUserTitleLabel;
	private JComboBox updateComboBox;
	private JTextField updateInput;
	private JButton getUserButton, cancelGetUserButton;

	// components for oneUpdatePanel
	private JLabel oneUpdateTitleLabel, oneUpdateusernameLabel,
			oneUpdateusernameField, oneUpdatepasswordLabel, oneUpdatetypeLabel,
			oneUpdatefirstnameLabel, oneUpdatesurnameLabel,
			oneUpdatedateOfBirthLabel, oneUpdatenrLabel;
	private JTextField oneUpdatefirstnameInput, oneUpdatesurnameInput,
			oneUpdatedateOfBirthInput, oneUpdatenrInput;
	private JButton oneUpdatepasswordButton, oneUpdateButton,
			cancelOneUpdateButton;
	private JComboBox oneUpdatetypeInput;
	private int oneUpdatesearchHeigth;
	private model.User oneUpdateUser;

	// component used for deleteUserPanel
	private JPanel infoPanel;
	private JButton deleteUserButton, cancelDeleteUserButton;
	private JLabel deleteUserTitleLabel, infoUsernameLabel, infoTypeLabel,
			infoFirstnameLabel, infoSurnameLabel, infoDateOfBirthLabel,
			infoNrLabel, infoUsername, infoType, infoFirstname, infoSurname,
			infoDateOfBirth, infoNr;
	private JList usernameList;
	private JScrollPane usernameScroll;
	private model.User[] list;
	private int deleteW;
	private DefaultListModel listModel;

	// component used for resultPanel
	private int tableW;
	private JTable result;
	private JScrollPane resultScroll;

	public AdministrationPage(model.User user) {

		dim = Toolkit.getDefaultToolkit().getScreenSize();
		insets = Toolkit.getDefaultToolkit().getScreenInsets(
				getGraphicsConfiguration());
		JFrame f = new JFrame();
		f.pack();
		frameinsets = f.getInsets();

		this.setIconImage(Toolkit.getDefaultToolkit()
				.getImage("Image/LOGO.jpg"));

		c.user = user;
		new Manager(c.user);
		msg = new view.Message();
		fontmetrics = getFontMetrics(c.user.getFont());

		this.setTitle("Administratorside");
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
		createNewUserSidePanel();
		createNewUserPanel();
		createUpdateUserSidePanel();
		createUpdateUserPanel();
		createDeleteUserSidePanel();
		createDeleteUserPanel();
		createSearchPanel();
		createResultPanel(null);
		createOneUpdatePanel(null);

		panel = new JPanel();
		panel.setLayout(new GroupLayout());
		panel.addKeyListener(this);

		panel.add(mainSidePanel,
				new Constraints(
						new Leading(0, mainSidePanel.getWidth(), 10, 10),
						new Leading(0, mainSidePanel.getHeight(), 12, 12)));
		panel.add(mainPanel, new Constraints(new Leading(DEFAULT_PANEL_X
				+ mainSidePanel.getWidth(), mainPanel.getWidth(), 10, 10),
				new Leading(DEFAULT_PANEL_Y, mainPanel.getHeight(), 12, 12)));

		JScrollPane s = new JScrollPane(panel,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(s);

	}

	public void createMenu() {
		menubar = new JMenuBar();
		menubar.setBackground(c.user.getForegroundColor());
		menubar.setMargin(new Insets(2, 2, 2, 2));
		menubar.setSize(this.getWidth() - 5,
				fontmetrics.getHeight() + (menubar.getInsets().bottom * 2));

		// create JMenu
		fileMenu = new JMenu("Fil");
		toolsMenu = new JMenu("Verktøy");
		helpMenu = new JMenu("Hjelp");
		closeMenu = new JMenu("Avslutt");
		closeMenu.setBorder(BorderFactory.createLineBorder(c.user
				.getBackgroundColor()));

		// create JMenuItem
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

		// add to JMenu
		fileMenu.add(settingsMenuItem);
		fileMenu.add(closeMenu);

		toolsMenu.add(settingsMenuItem);

		closeMenu.add(logOffMenuItem);
		closeMenu.add(shutdownMenuItem);

		helpMenu.add(helpMenuItem);
		helpMenu.add(aboutMenuItem);

		// add to JMenuBar
		menubar.add(fileMenu);
		menubar.add(toolsMenu);
		menubar.add(helpMenu);

		// add actionlistener
		shutdownMenuItem.addActionListener(this);
		logOffMenuItem.addActionListener(this);
		aboutMenuItem.addActionListener(this);
		helpMenuItem.addActionListener(this);
		settingsMenuItem.addActionListener(this);
	}

	public void createMainSidePanel() {

		// create jpanel and add listener
		mainSidePanel = new JPanel();
		mainSidePanel.setLayout(new GroupLayout());
		mainSidePanel.setBackground(c.user.getBackgroundColor().darker());
		mainSidePanel.addKeyListener(this);

		// create jbuttons and add listeners
		newUser = new JButton("Legg til ny bruker");
		String longestButtonText = newUser.getText();
		newUser.addActionListener(this);
		newUser.addKeyListener(this);

		updateUser = new JButton("Endre bruker");
		if (longestButtonText.length() < updateUser.getText().length())
			longestButtonText = updateUser.getText();
		updateUser.addActionListener(this);
		updateUser.addKeyListener(this);

		deleteUser = new JButton("Slett bruker");
		if (longestButtonText.length() < deleteUser.getText().length())
			longestButtonText = deleteUser.getText();
		deleteUser.addActionListener(this);
		deleteUser.addKeyListener(this);

		search = new JButton("Søk etter en bruker");
		if (longestButtonText.length() < search.getText().length())
			longestButtonText = search.getText();
		search.addActionListener(this);
		search.addKeyListener(this);

		// create help-variables
		int buttonH = fontmetrics.getHeight()
				+ (new JButton().getInsets().bottom * 2);
		int buttonW = fontmetrics.stringWidth(longestButtonText)
				+ (new JButton().getInsets().left * 2);
		int height = 20;

		String title = "HOVEDMENY";

		mainSidePanel.setBorder(BorderFactory.createTitledBorder(null, title,
				TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null,
				c.user.getForegroundColor()));

		if (buttonW + 20 < fontmetrics.stringWidth(title))
			buttonW = fontmetrics.stringWidth(title);

		// add to panel
		mainSidePanel.add(newUser, new Constraints(new Leading(10, buttonW, 10,
				10), new Leading(height, buttonH, 12, 12)));

		height += 10 + buttonH;

		mainSidePanel.add(updateUser, new Constraints(new Leading(10, buttonW,
				10, 10), new Leading(height, buttonH, 12, 12)));

		height += 10 + buttonH;

		mainSidePanel.add(deleteUser, new Constraints(new Leading(10, buttonW,
				10, 10), new Leading(height, buttonH, 12, 12)));

		height += 10 + buttonH;

		mainSidePanel.add(search, new Constraints(new Leading(10, buttonW, 10,
				10), new Leading(height, buttonH, 12, 12)));

		// set panel size
		mainSidePanel.setSize(buttonW + 30, DEFAULT_HEIGTH);
	}

	public void createMainPanel() {
		// create panel and add listeners
		mainPanel = new JPanel();
		mainPanel.setLayout(new GroupLayout());
		mainPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		mainPanel.addKeyListener(this);

		// add to panel
		JLabel j = new JLabel("Du logget inn : ");
		mainPanel.add(j, new Constraints(new Leading(10, 10, 10), new Leading(
				10, 12, 12)));

		mainPanel.add(dateLabel, new Constraints(new Leading(10, 10, 10),
				new Leading(20 + fontmetrics.getHeight(), 12, 12)));

		// set panel size
		mainPanel
				.setSize(
						fontmetrics.stringWidth(dateLabel.getText()) + 20 > DEFAULT_PANEL_W
								- mainSidePanel.getWidth() ? fontmetrics
								.stringWidth(dateLabel.getText()) + 20
								: DEFAULT_PANEL_W - mainSidePanel.getWidth(),
						DEFAULT_PANEL_H);

	}

	public void createNewUserSidePanel() {
		// create panel and add listeners
		newUserSidePanel = new JPanel();
		newUserSidePanel.setLayout(new GroupLayout());
		newUserSidePanel.setBackground(c.user.getBackgroundColor().darker());
		newUserSidePanel.addKeyListener(this);

		// create jbutton and add listeners
		mainPagefromNew = new JButton("Hovedside");
		String longestButtonText = mainPagefromNew.getText();
		mainPagefromNew.addActionListener(this);
		mainPagefromNew.addKeyListener(this);

		// create help variables
		int buttonH = fontmetrics.getHeight()
				+ (new JButton().getInsets().bottom * 2);
		int buttonW = fontmetrics.stringWidth(longestButtonText)
				+ (new JButton().getInsets().left * 2);
		int height = 20;

		String title = "Legg til ny bruker ";

		newUserSidePanel.setBorder(BorderFactory.createTitledBorder(null,
				title, TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
				null, c.user.getForegroundColor()));

		if (buttonW + 20 < fontmetrics.stringWidth(title))
			buttonW = fontmetrics.stringWidth(title);

		// add to panel
		newUserSidePanel.add(mainPagefromNew, new Constraints(new Leading(10,
				buttonW, 10, 10), new Leading(height, buttonH, 12, 12)));

		// set panel size
		newUserSidePanel.setSize(buttonW + 30, DEFAULT_HEIGTH);
	}

	public void createNewUserPanel() {
		// create panel and add listeners
		newUserPanel = new JPanel();
		newUserPanel.setLayout(new GroupLayout());
		newUserPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		newUserPanel.addKeyListener(this);

		// create jlabels
		newUserTitleLabel = new JLabel("Fyll ut informasjon om brukeren");
		usernameLabel = new JLabel("Brukernavn");
		passwordLabel = new JLabel("Passord");
		password2Label = new JLabel("Gjenta passord");
		typeLabel = new JLabel("Brukertype");
		firstnameLabel = new JLabel("Fornavn");
		surnameLabel = new JLabel("Etternavn");
		dateOfBirthLabel = new JLabel("Fødselsdato");
		nrLabel = new JLabel("Telefon");

		// create input-fields
		usernameInput = new JTextField();
		firstnameInput = new JTextField();
		surnameInput = new JTextField();
		dateOfBirthInput = new JTextField("dd-mm-åååå");
		nrInput = new JTextField();

		passwordInput = new JPasswordField();
		password2Input = new JPasswordField();

		typeInput = new JComboBox();

		// create buttons
		addUserButton = new JButton("Lagre");
		cancelAddUserButton = new JButton("Avbryt");

		// add keylistener to all inputfield and buttons
		usernameInput.addKeyListener(this);
		firstnameInput.addKeyListener(this);
		surnameInput.addKeyListener(this);
		dateOfBirthInput.addKeyListener(this);
		nrInput.addKeyListener(this);

		passwordInput.addKeyListener(this);
		password2Input.addKeyListener(this);

		addUserButton.addKeyListener(this);
		cancelAddUserButton.addKeyListener(this);

		// add actionlistener to the buttons
		addUserButton.addActionListener(this);
		cancelAddUserButton.addActionListener(this);

		// set up the combobox
		typeInput.setModel(new DefaultComboBoxModel(new Object[] {
				"Standardbruker", "Administrator" }));
		typeInput.setSelectedIndex(0);
		typeInput.setSize(
				(int) (fontmetrics.stringWidth("Standardbruker") * 1.5),
				(int) (fontmetrics.getHeight() * 1.5));

		// increase the size and edit the style for the title
		newUserTitleLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize() + 2));

		// holds the component's Y-property
		searchHeigth = 15;
		// set inputfield-size
		int inputH = typeInput.getHeight();
		int inputW = typeInput.getWidth();

		// holds the longest labeltext
		String labeltext;

		// add labels to panel
		newUserPanel.add(newUserTitleLabel, new Constraints(new Leading(10, 10,
				10), new Leading(searchHeigth, 10, 10)));
		searchHeigth += 15 + inputH;

		newUserPanel.add(usernameLabel, new Constraints(
				new Leading(15, 10, 10), new Leading(searchHeigth, 10, 10)));
		searchHeigth += 15 + inputH;
		labeltext = usernameLabel.getText();

		newUserPanel.add(passwordLabel, new Constraints(
				new Leading(15, 10, 10), new Leading(searchHeigth, 10, 10)));
		searchHeigth += 15 + inputH;
		if (labeltext.length() < passwordLabel.getText().length())
			labeltext = passwordLabel.getText();

		newUserPanel.add(password2Label, new Constraints(
				new Leading(15, 10, 10), new Leading(searchHeigth, 10, 10)));
		searchHeigth += 15 + inputH;
		if (labeltext.length() < password2Label.getText().length())
			labeltext = password2Label.getText();

		newUserPanel.add(typeLabel, new Constraints(new Leading(15, 10, 10),
				new Leading(searchHeigth, 10, 10)));
		searchHeigth += 15 + inputH;
		if (labeltext.length() < typeLabel.getText().length())
			labeltext = typeLabel.getText();

		newUserPanel.add(firstnameLabel, new Constraints(
				new Leading(15, 10, 10), new Leading(searchHeigth, 10, 10)));
		searchHeigth += 15 + inputH;
		if (labeltext.length() < firstnameLabel.getText().length())
			labeltext = firstnameLabel.getText();

		newUserPanel.add(surnameLabel, new Constraints(new Leading(15, 10, 10),
				new Leading(searchHeigth, 10, 10)));
		searchHeigth += 15 + inputH;
		if (labeltext.length() < surnameLabel.getText().length())
			labeltext = surnameLabel.getText();

		newUserPanel.add(dateOfBirthLabel, new Constraints(new Leading(15, 10,
				10), new Leading(searchHeigth, 10, 10)));
		searchHeigth += 15 + inputH;
		if (labeltext.length() < dateOfBirthLabel.getText().length())
			labeltext = dateOfBirthLabel.getText();

		newUserPanel.add(nrLabel, new Constraints(new Leading(15, 10, 10),
				new Leading(searchHeigth, 10, 10)));
		searchHeigth += 15 + inputH;
		if (labeltext.length() < nrLabel.getText().length())
			labeltext = nrLabel.getText();

		// inputfields
		searchHeigth = 25 + inputH; // reset the searchHeigth to
									// usernameLabel-position
		int width = fontmetrics.stringWidth(labeltext) + 30;// set the input's
															// x-position

		// add input-fields to panel
		newUserPanel.add(usernameInput, new Constraints(new Leading(width,
				inputW, 10, 10), new Leading(searchHeigth, inputH, 10, 10)));
		searchHeigth += 15 + inputH;

		newUserPanel.add(passwordInput, new Constraints(new Leading(width,
				inputW, 10, 10), new Leading(searchHeigth, inputH, 10, 10)));
		searchHeigth += 15 + inputH;

		newUserPanel.add(password2Input, new Constraints(new Leading(width,
				inputW, 10, 10), new Leading(searchHeigth, inputH, 10, 10)));
		searchHeigth += 15 + inputH;

		newUserPanel.add(typeInput, new Constraints(new Leading(width, inputW,
				10, 10), new Leading(searchHeigth, inputH, 10, 10)));
		searchHeigth += 15 + inputH;

		newUserPanel.add(firstnameInput, new Constraints(new Leading(width,
				inputW, 10, 10), new Leading(searchHeigth, inputH, 10, 10)));
		searchHeigth += 15 + inputH;

		newUserPanel.add(surnameInput, new Constraints(new Leading(width,
				inputW, 10, 10), new Leading(searchHeigth, inputH, 10, 10)));
		searchHeigth += 15 + inputH;

		newUserPanel.add(dateOfBirthInput, new Constraints(new Leading(width,
				inputW, 10, 10), new Leading(searchHeigth, inputH, 10, 10)));
		searchHeigth += 15 + inputH;

		newUserPanel.add(nrInput, new Constraints(new Leading(width, inputW,
				10, 10), new Leading(searchHeigth, inputH, 10, 10)));
		searchHeigth += 25 + inputH;

		// add buttons to panel
		newUserPanel.add(cancelAddUserButton, new Constraints(new Trailing(15,
				10, 10), new Trailing(10, 10, 10)));
		int buttonW = (cancelAddUserButton.getInsets().left * 2) + 25
				+ fontmetrics.stringWidth(cancelAddUserButton.getText());
		newUserPanel.add(addUserButton, new Constraints(new Trailing(buttonW,
				10, 10), new Trailing(10, 10, 10)));

		searchHeigth += 10;

		// set panel size
		newUserPanel
				.setSize(30 + width + inputW > DEFAULT_PANEL_W
						- newUserSidePanel.getWidth() ? 30 + width + inputW
						: DEFAULT_PANEL_W - newUserSidePanel.getWidth(),
						searchHeigth > DEFAULT_PANEL_H ? searchHeigth
								: DEFAULT_PANEL_H);
	}

	public void createUpdateUserSidePanel() {
		// create panle and add listeners
		updateUserSidePanel = new JPanel();
		updateUserSidePanel.setLayout(new GroupLayout());
		updateUserSidePanel.setBackground(c.user.getBackgroundColor().darker());
		updateUserSidePanel.addKeyListener(this);

		// create buttons and add listeners
		mainPagefromUpdate = new JButton("Hovedside");
		String longestButtonText = mainPagefromUpdate.getText();
		mainPagefromUpdate.addActionListener(this);
		mainPagefromUpdate.addKeyListener(this);

		updateUser2 = new JButton("Endre bruker");
		if (fontmetrics.stringWidth(longestButtonText) < fontmetrics
				.stringWidth(updateUser2.getText()))
			longestButtonText = updateUser2.getText();
		updateUser2.addActionListener(this);
		updateUser2.addKeyListener(this);

		updateSearch = new JButton("Søk etter bruker");
		if (fontmetrics.stringWidth(longestButtonText) < fontmetrics
				.stringWidth(updateSearch.getText()))
			longestButtonText = updateSearch.getText();
		updateSearch.addActionListener(this);
		updateSearch.addKeyListener(this);

		// create help-variables
		int buttonH = fontmetrics.getHeight()
				+ (new JButton().getInsets().bottom * 2);
		int buttonW = fontmetrics.stringWidth(longestButtonText)
				+ (new JButton().getInsets().left * 2);
		int height = 20;

		String title = "Endre en bruker";

		updateUserSidePanel.setBorder(BorderFactory.createTitledBorder(null,
				title, TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
				null, c.user.getForegroundColor()));

		if (buttonW + 20 < fontmetrics.stringWidth(title))
			buttonW = fontmetrics.stringWidth(title);

		// add buttons to panel
		updateUserSidePanel.add(mainPagefromUpdate, new Constraints(
				new Leading(10, buttonW, 10, 10), new Leading(height, buttonH,
						12, 12)));

		height += 10 + buttonH;

		updateUserSidePanel.add(updateUser2, new Constraints(new Leading(10,
				buttonW, 10, 10), new Leading(height, buttonH, 12, 12)));

		height += 10 + buttonH;

		updateUserSidePanel.add(updateSearch, new Constraints(new Leading(10,
				buttonW, 10, 10), new Leading(height, buttonH, 12, 12)));

		// set panel size
		updateUserSidePanel.setSize(buttonW + 30, DEFAULT_HEIGTH);
	}

	public void createUpdateUserPanel() {
		// create panel and add listeners
		updateUserPanel = new JPanel();
		updateUserPanel.setLayout(new GroupLayout());
		updateUserPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		updateUserPanel.addKeyListener(this);

		// create jabel
		updateUserTitleLabel = new JLabel(
				"<html><b>Hurtigsøk etter brukerkontoen som skal endres</b><br>(Eller benytt søkemotoren fra sidemenyen)</html>)");
		updateUserTitleLabel.setFont(new Font(c.user.getFamily(), 0, c.user
				.getSize() + 2));

		// create jcombobox
		updateComboBox = new JComboBox();
		updateComboBox.setModel(new DefaultComboBoxModel(new Object[] {
				"Brukernavn", "Brukertype", "Fornavn", "Etternavn",
				"Fødselsdato", "Telefon" }));
		updateComboBox.setSelectedIndex(0);
		updateComboBox.setSize(
				(int) (fontmetrics.stringWidth(" Fødselsdato ") * 1.5),
				(int) (fontmetrics.getHeight() * 1.5));

		// create textfield
		updateInput = new JTextField();
		updateInput.setSize((int) (updateComboBox.getWidth() * 1.5),
				(int) (fontmetrics.getHeight() * 1.5));

		// create button
		getUserButton = new JButton("Hent bruker");
		cancelGetUserButton = new JButton("Avbryt");

		// set button size
		getUserButton.setSize(fontmetrics.stringWidth(getUserButton.getText())
				+ (getUserButton.getInsets().left * 2), fontmetrics.getHeight()
				+ (getUserButton.getInsets().bottom * 2));
		cancelGetUserButton.setSize(getUserButton.getWidth(),
				getUserButton.getHeight());

		// set set button margin
		getUserButton.setMargin(new Insets(2, 4, 2, 4));
		int margin = (int) ((fontmetrics.stringWidth(getUserButton.getText()) + 8 - fontmetrics
				.stringWidth(cancelGetUserButton.getText())) / 2);
		cancelGetUserButton.setMargin(new Insets(getUserButton.getMargin().top,
				margin, getUserButton.getMargin().bottom, margin));

		// add action/key listener
		getUserButton.addActionListener(this);
		getUserButton.addKeyListener(this);
		cancelGetUserButton.addActionListener(this);
		cancelGetUserButton.addKeyListener(this);
		updateInput.addKeyListener(this);

		// create help-variable
		int heigth = 10;

		// add to panel
		updateUserPanel.add(updateUserTitleLabel, new Constraints(new Leading(
				10, 10, 10), new Leading(heigth, 10, 10)));
		heigth += (fontmetrics.getHeight() * 3) + 20;

		updateUserPanel.add(updateInput, new Constraints(new Leading(10,
				updateInput.getWidth(), 10, 10), new Leading(heigth,
				updateInput.getHeight(), 10, 10)));

		updateUserPanel.add(updateComboBox, new Constraints(new Leading(
				10 + updateInput.getWidth() + 15, updateComboBox.getWidth(),
				10, 10),
				new Leading(heigth, updateComboBox.getHeight(), 10, 10)));

		heigth += (int) (updateComboBox.getHeight() * 1.5);
		updateUserPanel.add(cancelGetUserButton, new Constraints(new Trailing(
				10, 10, 10), new Trailing(10, 10, 10)));
		updateUserPanel.add(getUserButton, new Constraints(new Trailing(
				10 + cancelGetUserButton.getWidth(), 10, 10), new Trailing(10,
				10, 10)));
		heigth += fontmetrics.getHeight() + 14;

		int sizeW = updateInput.getWidth() + updateComboBox.getWidth()
				+ cancelGetUserButton.getWidth() + getUserButton.getWidth()
				+ 30;

		// set panel size
		updateUserPanel
				.setSize(
						sizeW > DEFAULT_PANEL_W
								- updateUserSidePanel.getWidth() ? sizeW
								: DEFAULT_PANEL_W
										- updateUserSidePanel.getWidth(),
						heigth > DEFAULT_PANEL_H ? heigth : DEFAULT_PANEL_H);

	}

	public void createDeleteUserSidePanel() {
		// create panel and add listeners
		deleteUserSidePanel = new JPanel();
		deleteUserSidePanel.setLayout(new GroupLayout());
		deleteUserSidePanel.setBackground(c.user.getBackgroundColor().darker());
		deleteUserSidePanel.addKeyListener(this);

		// create buttons and add listeners
		mainPagefromDelete = new JButton("Hovedside");
		String longestButtonText = mainPagefromDelete.getText();
		mainPagefromDelete.addActionListener(this);
		mainPagefromDelete.addKeyListener(this);

		delUser = new JButton("Slett bruker");
		delUser.addActionListener(this);
		delUser.addKeyListener(this);

		deleteSearch = new JButton("Søk etter en bruker");
		if (longestButtonText.length() < deleteSearch.getText().length())
			longestButtonText = deleteSearch.getText();
		deleteSearch.addActionListener(this);
		deleteSearch.addKeyListener(this);

		// create help-variables
		int buttonH = fontmetrics.getHeight()
				+ (new JButton().getInsets().bottom * 2);
		int buttonW = fontmetrics.stringWidth(longestButtonText)
				+ (new JButton().getInsets().left * 2);
		int height = 20;

		String title = "Slett en bruker";

		deleteUserSidePanel.setBorder(BorderFactory.createTitledBorder(null,
				title, TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
				null, c.user.getForegroundColor()));

		if (buttonW + 20 < fontmetrics.stringWidth(title))
			buttonW = fontmetrics.stringWidth(title);

		// add to panel
		deleteUserSidePanel.add(mainPagefromDelete, new Constraints(
				new Leading(10, buttonW, 10, 10), new Leading(height, buttonH,
						12, 12)));

		height += 10 + buttonH;

		deleteUserSidePanel.add(delUser, new Constraints(new Leading(10,
				buttonW, 10, 10), new Leading(height, buttonH, 12, 12)));

		height += 10 + buttonH;

		deleteUserSidePanel.add(deleteSearch, new Constraints(new Leading(10,
				buttonW, 10, 10), new Leading(height, buttonH, 12, 12)));

		// set panel size
		deleteUserSidePanel.setSize(buttonW + 30, DEFAULT_HEIGTH);
	}

	public void createDeleteUserPanel() {
		// create panel and add listeners
		deleteUserPanel = new JPanel();
		deleteUserPanel.setLayout(new GroupLayout());
		deleteUserPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		deleteUserPanel.addKeyListener(this);

		infoPanel = new JPanel();
		infoPanel.setLayout(new GroupLayout());
		infoPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));

		// create buttons
		deleteUserButton = new JButton("Slett");
		cancelDeleteUserButton = new JButton("Avbryt");

		// set button size
		cancelDeleteUserButton.setSize(
				fontmetrics.stringWidth(cancelDeleteUserButton.getText())
						+ (cancelDeleteUserButton.getInsets().left * 2),
				fontmetrics.getHeight()
						+ (cancelDeleteUserButton.getInsets().bottom * 2));
		deleteUserButton.setSize(cancelDeleteUserButton.getWidth(),
				cancelDeleteUserButton.getHeight());

		// add listeners
		deleteUserButton.addActionListener(this);
		deleteUserButton.addKeyListener(this);
		cancelDeleteUserButton.addActionListener(this);
		cancelDeleteUserButton.addKeyListener(this);

		// create labels
		infoUsernameLabel = new JLabel("Brukernavn");
		infoUsernameLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		infoUsernameLabel.setForeground(c.user.getForegroundColor().darker());
		String width = infoUsernameLabel.getText();

		infoTypeLabel = new JLabel("Brukertype");
		infoTypeLabel
				.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		infoTypeLabel.setForeground(c.user.getForegroundColor().darker());
		if (fontmetrics.stringWidth(width) < fontmetrics
				.stringWidth(infoTypeLabel.getText()))
			width = infoTypeLabel.getText();

		infoFirstnameLabel = new JLabel("Fornavn");
		infoFirstnameLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		infoFirstnameLabel.setForeground(c.user.getForegroundColor().darker());
		if (fontmetrics.stringWidth(width) < fontmetrics
				.stringWidth(infoFirstnameLabel.getText()))
			width = infoFirstnameLabel.getText();

		infoSurnameLabel = new JLabel("Etternavn");
		infoSurnameLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		infoSurnameLabel.setForeground(c.user.getForegroundColor().darker());
		if (fontmetrics.stringWidth(width) < fontmetrics
				.stringWidth(infoSurnameLabel.getText()))
			width = infoSurnameLabel.getText();

		infoDateOfBirthLabel = new JLabel("Fødselsdato");
		infoDateOfBirthLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize()));
		infoDateOfBirthLabel
				.setForeground(c.user.getForegroundColor().darker());
		if (fontmetrics.stringWidth(width) < fontmetrics
				.stringWidth(infoDateOfBirthLabel.getText()))
			width = infoDateOfBirthLabel.getText();

		infoNrLabel = new JLabel("Telefon");
		infoNrLabel.setFont(new Font(c.user.getFamily(), 1, c.user.getSize()));
		infoNrLabel.setForeground(c.user.getForegroundColor().darker());
		if (fontmetrics.stringWidth(width) < fontmetrics
				.stringWidth(infoNrLabel.getText()))
			width = infoNrLabel.getText();

		// create value labels
		infoUsername = new JLabel(c.user.getUsername());
		infoType = new JLabel(c.user.getType());
		infoFirstname = new JLabel(c.user.getFirstname());
		infoSurname = new JLabel(c.user.getSurname());
		infoDateOfBirth = new JLabel(c.user.getdateofBirth());
		infoNr = new JLabel(c.user.getPhonenumber());

		usernameScroll = new JScrollPane();

		// making the titlelabel
		deleteUserTitleLabel = new JLabel("Slett en konto");
		deleteUserTitleLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize() + 2));

		list = c.getUsernameList();

		// create the JList holding all the username's
		usernameList = new JList();
		listModel = new DefaultListModel();

		for (int i = 0; i < list.length; i++) {
			listModel.addElement(list[i].getUsername());
		}

		usernameList.setModel(listModel);
		usernameList.setFont(c.user.getFont());
		usernameList.setForeground(c.user.getForegroundColor());
		usernameList.setBackground(c.user.getBackgroundColor().brighter()
				.brighter());
		usernameList.setSelectionBackground(c.user.getForegroundColor());
		usernameList.setSelectionForeground(c.user.getBackgroundColor());
		usernameList.setSelectedValue(c.user.getUsername(), true);
		usernameList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				// the output
				infoUsername.setText(list[usernameList.getSelectedIndex()]
						.getUsername());
				infoType.setText(list[usernameList.getSelectedIndex()]
						.getType());
				infoFirstname.setText(list[usernameList.getSelectedIndex()]
						.getFirstname());
				infoSurname.setText(list[usernameList.getSelectedIndex()]
						.getSurname());
				infoDateOfBirth.setText(list[usernameList.getSelectedIndex()]
						.getdateofBirth());
				infoNr.setText(list[usernameList.getSelectedIndex()]
						.getPhonenumber());
			}
		});

		usernameScroll.setViewportView(usernameList);
		usernameScroll.setWheelScrollingEnabled(true);
		infoPanel.setBorder(BorderFactory.createTitledBorder(null,
				"Informasjon om brukeren", TitledBorder.LEADING,
				TitledBorder.DEFAULT_POSITION, new Font(c.user.getFamily(), 1,
						c.user.getSize() + 2), c.user.getForegroundColor()));

		int heigth = 10;

		// add labels to panel

		infoPanel.add(infoUsernameLabel, new Constraints(
				new Leading(10, 10, 10), new Leading(heigth, 10, 10)));
		heigth += fontmetrics.getHeight() + 10;

		infoPanel.add(infoTypeLabel, new Constraints(new Leading(10, 10, 10),
				new Leading(heigth, 10, 10)));
		heigth += fontmetrics.getHeight() + 10;

		infoPanel.add(infoFirstnameLabel, new Constraints(new Leading(10, 10,
				10), new Leading(heigth, 10, 10)));
		heigth += fontmetrics.getHeight() + 10;

		infoPanel.add(infoSurnameLabel, new Constraints(
				new Leading(10, 10, 10), new Leading(heigth, 10, 10)));
		heigth += fontmetrics.getHeight() + 10;

		infoPanel.add(infoDateOfBirthLabel, new Constraints(new Leading(10, 10,
				10), new Leading(heigth, 10, 10)));
		heigth += fontmetrics.getHeight() + 10;

		infoPanel.add(infoNrLabel, new Constraints(new Leading(10, 10, 10),
				new Leading(heigth, 10, 10)));

		// add information-labels to panel
		heigth = 10;
		infoPanel.add(infoUsername,
				new Constraints(new Leading(
						fontmetrics.stringWidth(width) + 25, 10, 10),
						new Leading(heigth, 10, 10)));
		heigth += fontmetrics.getHeight() + 10;

		infoPanel.add(infoType,
				new Constraints(new Leading(
						fontmetrics.stringWidth(width) + 25, 10, 10),
						new Leading(heigth, 10, 10)));
		heigth += fontmetrics.getHeight() + 10;

		infoPanel.add(infoFirstname,
				new Constraints(new Leading(
						fontmetrics.stringWidth(width) + 25, 10, 10),
						new Leading(heigth, 10, 10)));
		heigth += fontmetrics.getHeight() + 10;

		infoPanel.add(infoSurname,
				new Constraints(new Leading(
						fontmetrics.stringWidth(width) + 25, 10, 10),
						new Leading(heigth, 10, 10)));
		heigth += fontmetrics.getHeight() + 10;

		infoPanel.add(infoDateOfBirth,
				new Constraints(new Leading(
						fontmetrics.stringWidth(width) + 25, 10, 10),
						new Leading(heigth, 10, 10)));
		heigth += fontmetrics.getHeight() + 10;

		infoPanel.add(infoNr,
				new Constraints(new Leading(
						fontmetrics.stringWidth(width) + 25, 10, 10),
						new Leading(heigth, 10, 10)));
		heigth += fontmetrics.getHeight() + 10;

		usernameList.ensureIndexIsVisible(usernameList.getSelectedIndex());

		deleteUserPanel.add(deleteUserTitleLabel, new Constraints(new Leading(
				10, 10, 10), new Leading(10, 10, 10)));
		deleteUserPanel.add(usernameScroll, new Constraints(new Leading(10, 10,
				10), new Leading(fontmetrics.getHeight() + 20, 10, 10)));
		deleteUserPanel.add(
				infoPanel,
				new Constraints(new Leading(fontmetrics
						.stringWidth("Velg en brukerkonto") + 25, 10, 10),
						new Leading(fontmetrics.getHeight() + 20, 10, 10)));

		// add buttons to panel
		heigth += (fontmetrics.getHeight() * 2) + 50;
		deleteUserPanel.add(deleteUserButton, new Constraints(new Trailing(
				cancelDeleteUserButton.getWidth() + 20, 10, 10), new Trailing(
				10, 10, 10)));
		deleteUserPanel.add(cancelDeleteUserButton, new Constraints(
				new Trailing(10, 10, 10), new Trailing(10, 10, 10)));

		heigth += fontmetrics.getHeight() + 14;

		deleteW = fontmetrics.stringWidth(deleteUserTitleLabel.getText()) + 10
				+ 25 + (fontmetrics.stringWidth(width) * 2)
				+ (cancelDeleteUserButton.getWidth() * 2) + 20;

		// set panel size
		deleteUserPanel
				.setSize(
						deleteW > DEFAULT_PANEL_W
								- deleteUserSidePanel.getWidth() ? deleteW
								: DEFAULT_PANEL_W
										- deleteUserSidePanel.getWidth(),
						heigth > DEFAULT_PANEL_H ? heigth : DEFAULT_PANEL_H);
	}

	public void createSearchPanel() {
		// create panel and add listeners
		searchPanel = new JPanel();
		searchPanel.setLayout(new GroupLayout());
		searchPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		searchPanel.addKeyListener(this);

		// create jlabels
		searchTitleLabel = new JLabel("Søk etter en bruker");
		searchUsernameLabel = new JLabel("Brukernavn");
		searchTypeLabel = new JLabel("Brukertype");
		searchFirstnameLabel = new JLabel("Fornavn");
		searchSurnameLabel = new JLabel("Etternavn");
		searchDateOfBirthLabel = new JLabel("Fødselsdato");
		searchNrLabel = new JLabel("Telefon");

		// create input-fields
		searchUsernameInput = new JTextField();
		searchUsernameInput.addKeyListener(this);
		searchFirstnameInput = new JTextField();
		searchFirstnameInput.addKeyListener(this);
		searchSurnameInput = new JTextField();
		searchSurnameInput.addKeyListener(this);
		searchDateOfBirthInput = new JTextField("dd-mm-åååå");
		searchDateOfBirthInput.addKeyListener(this);
		searchNrInput = new JTextField();
		searchNrInput.addKeyListener(this);

		searchTypeInput = new JComboBox();

		// create buttons and add listeners
		searchButton = new JButton("Søk");
		searchButton.addKeyListener(this);
		searchButton.addActionListener(this);
		cancelSearchButton = new JButton("Avbryt");
		cancelSearchButton.addKeyListener(this);
		cancelSearchButton.addActionListener(this);

		// add keylistener to all inputfield and buttons
		searchUsernameInput.addKeyListener(this);
		searchFirstnameInput.addKeyListener(this);
		searchSurnameInput.addKeyListener(this);
		searchDateOfBirthInput.addKeyListener(this);
		searchNrInput.addKeyListener(this);
		searchButton.addKeyListener(this);
		cancelSearchButton.addKeyListener(this);

		// set up the combobox
		searchTypeInput.setModel(new DefaultComboBoxModel(new Object[] {
				"(Velg brukertype)", "Standardbruker", "Administrator" }));
		searchTypeInput.setSelectedIndex(0);
		searchTypeInput.setSize(
				(int) (fontmetrics.stringWidth("Standardbruker") * 1.5),
				(int) (fontmetrics.getHeight() * 1.5));

		// increase the size and edit the style for the title
		searchTitleLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize() + 2));

		// holds the component's Y-property
		int heigth = 15;
		// set inputfield-size
		int inputH = searchTypeInput.getHeight();
		int inputW = searchTypeInput.getWidth();

		// holds the longest labeltext
		String labeltext;

		// add labels to panel
		searchPanel.add(searchTitleLabel, new Constraints(new Leading(10, 10,
				10), new Leading(heigth, 10, 10)));
		heigth += 15 + inputH;

		searchPanel.add(searchUsernameLabel, new Constraints(new Leading(15,
				10, 10), new Leading(heigth, 10, 10)));
		heigth += 15 + inputH;
		labeltext = searchUsernameLabel.getText();

		searchPanel.add(searchTypeLabel, new Constraints(
				new Leading(15, 10, 10), new Leading(heigth, 10, 10)));
		heigth += 15 + inputH;
		if (labeltext.length() < searchTypeLabel.getText().length())
			labeltext = searchTypeLabel.getText();

		searchPanel.add(searchFirstnameLabel, new Constraints(new Leading(15,
				10, 10), new Leading(heigth, 10, 10)));
		heigth += 15 + inputH;
		if (labeltext.length() < searchFirstnameLabel.getText().length())
			labeltext = searchFirstnameLabel.getText();

		searchPanel.add(searchSurnameLabel, new Constraints(new Leading(15, 10,
				10), new Leading(heigth, 10, 10)));
		heigth += 15 + inputH;
		if (labeltext.length() < searchSurnameLabel.getText().length())
			labeltext = searchSurnameLabel.getText();

		searchPanel.add(searchDateOfBirthLabel, new Constraints(new Leading(15,
				10, 10), new Leading(heigth, 10, 10)));
		heigth += 15 + inputH;
		if (labeltext.length() < searchDateOfBirthLabel.getText().length())
			labeltext = searchDateOfBirthLabel.getText();

		searchPanel.add(searchNrLabel, new Constraints(new Leading(15, 10, 10),
				new Leading(heigth, 10, 10)));
		heigth += 15 + inputH;
		if (labeltext.length() < searchNrLabel.getText().length())
			labeltext = searchNrLabel.getText();

		// add input-fields to panel
		heigth = 25 + inputH; // reset the heigth to
								// usernameLabel-position
		int width = fontmetrics.stringWidth(labeltext) + 30;// set the input's
															// x-position

		searchPanel.add(searchUsernameInput, new Constraints(new Leading(width,
				inputW, 10, 10), new Leading(heigth, inputH, 10, 10)));
		heigth += 15 + inputH;

		searchPanel.add(searchTypeInput, new Constraints(new Leading(width,
				inputW, 10, 10), new Leading(heigth, inputH, 10, 10)));
		heigth += 15 + inputH;

		searchPanel.add(searchFirstnameInput, new Constraints(new Leading(
				width, inputW, 10, 10), new Leading(heigth, inputH, 10, 10)));
		heigth += 15 + inputH;

		searchPanel.add(searchSurnameInput, new Constraints(new Leading(width,
				inputW, 10, 10), new Leading(heigth, inputH, 10, 10)));
		heigth += 15 + inputH;

		searchPanel.add(searchDateOfBirthInput, new Constraints(new Leading(
				width, inputW, 10, 10), new Leading(heigth, inputH, 10, 10)));
		heigth += 15 + inputH;

		searchPanel.add(searchNrInput, new Constraints(new Leading(width,
				inputW, 10, 10), new Leading(heigth, inputH, 10, 10)));
		heigth += 25 + inputH;

		// add buttons to panel
		searchPanel.add(cancelSearchButton, new Constraints(new Trailing(10,
				10, 10), new Trailing(10, 10, 10)));
		int buttonW = (cancelSearchButton.getInsets().left * 2) + 20
				+ fontmetrics.stringWidth(cancelSearchButton.getText());
		searchPanel.add(searchButton, new Constraints(new Trailing(buttonW, 10,
				10), new Trailing(10, 10, 10)));

		heigth += fontmetrics.getHeight() + 14;

		// set panel size
		if (updateUserSidePanel.isVisible()) {
			searchPanel.setSize(
					DEFAULT_PANEL_W - updateUserSidePanel.getWidth() > 10
							+ width + inputW ? DEFAULT_PANEL_W
							- updateUserSidePanel.getWidth() : 10 + width
							+ inputW, heigth > DEFAULT_PANEL_H ? heigth
							: DEFAULT_PANEL_H);
		} else if (deleteUserSidePanel.isVisible()) {
			searchPanel.setSize(
					DEFAULT_PANEL_W - deleteUserSidePanel.getWidth() > 10
							+ width + inputW ? DEFAULT_PANEL_W
							- deleteUserSidePanel.getWidth() : 10 + width
							+ inputW, heigth > DEFAULT_PANEL_H ? heigth
							: DEFAULT_PANEL_H);
		} else {
			searchPanel.setSize(
					DEFAULT_PANEL_W - mainSidePanel.getWidth() > 10 + width
							+ inputW ? DEFAULT_PANEL_W
							- mainSidePanel.getWidth() : 10 + width + inputW,
					heigth > DEFAULT_PANEL_H ? heigth : DEFAULT_PANEL_H);
		}

	}

	public void createResultPanel(JTable table) {
		// check if table har content or not
		if (table != null) {
			result = table;
			result.setRowHeight(fontmetrics.getHeight() + 4);
			result.getTableHeader().setFont(c.user.getFont());
			result.getTableHeader().setForeground(c.user.getForegroundColor());
			result.getTableHeader().setBackground(
					c.user.getBackgroundColor().darker());
			result.setAutoCreateRowSorter(true);
			autoResizeColWidth(result);

			// check if update/delete user sidepanel is visible
			// and enable selection
			// if (updateUserSidePanel.isVisible()
			// | deleteUserSidePanel.isVisible()) {
			result.setRowSelectionAllowed(true);
			result.getSelectionModel().addListSelectionListener(
					new ListSelectionListener() {

						@Override
						public void valueChanged(ListSelectionEvent e) {
							int row = result.getSelectedRow();
							int col = 0, nameCol = 0, typeCol = 0, fNameCol = 0, sNameCol = 0, nrCol = 0, dateCol = 0;

							while (col != 6) {
								if (result.getColumnName(col).equals(
										" Brukernavn ")) {
									nameCol = col++;
								} else if (result.getColumnName(col).equals(
										" Brukertype ")) {
									typeCol = col++;
								} else if (result.getColumnName(col).equals(
										" Fornavn ")) {
									fNameCol = col++;
								} else if (result.getColumnName(col).equals(
										" Etternavn ")) {
									sNameCol = col++;
								} else if (result.getColumnName(col).equals(
										" Telefon ")) {
									nrCol = col++;
								} else if (result.getColumnName(col).equals(
										" Fødselsdato ")) {
									dateCol = col++;
								}
							}

							if (updateUserSidePanel.isVisible()) {
								if (msg.confirmDialog("Endre brukeren "
										+ result.getValueAt(row, nameCol)
												.toString() + "?") == 0) {

									model.User updateThis = new model.User();
									updateThis.setUsername(result.getValueAt(
											row, nameCol).toString());
									updateThis.setType(result.getValueAt(row,
											typeCol).toString());
									updateThis.setFirstname(result.getValueAt(
											row, fNameCol).toString());
									updateThis.setSurname(result.getValueAt(
											row, sNameCol).toString());
									updateThis.setdateofBirth(result
											.getValueAt(row, dateCol)
											.toString());
									updateThis.setPhonenumber(result
											.getValueAt(row, nrCol).toString());

									hideAllPanels();
									panel.removeAll();
									createUpdateUserSidePanel();
									createOneUpdatePanel(updateThis);

									panel.add(
											updateUserSidePanel,
											new Constraints(
													new Leading(
															0,
															updateUserSidePanel
																	.getWidth(),
															10, 10),
													new Leading(
															0,
															updateUserSidePanel
																	.getHeight(),
															12, 12)));

									panel.add(
											oneUpdatePanel,
											new Constraints(
													new Leading(
															DEFAULT_PANEL_X
																	+ updateUserSidePanel
																			.getWidth(),
															oneUpdatePanel
																	.getWidth(),
															10, 10),
													new Leading(
															DEFAULT_PANEL_Y,
															oneUpdatePanel
																	.getHeight(),
															12, 12)));
								}
							} else if (deleteUserSidePanel.isVisible()) {
								if (msg.confirmDialog("Slette brukeren "
										+ result.getValueAt(row, nameCol)
												.toString().trim() + "?") == 0) {
									if (c.deleteUser(result
											.getValueAt(row, nameCol)
											.toString().trim())) {

										hideAllPanels();
										DefaultTableModel t = (DefaultTableModel) result
												.getModel();
										t.removeRow(row);
										resultScroll.remove(result);
										resultPanel.remove(resultScroll);
										panel.removeAll();
										createDeleteUserSidePanel();
										createResultPanel(result);

										panel.add(
												deleteUserSidePanel,
												new Constraints(new Leading(0,
														deleteUserSidePanel
																.getWidth(),
														10, 10), new Leading(0,
														deleteUserSidePanel
																.getHeight(),
														12, 12)));

										panel.add(
												resultPanel,
												new Constraints(
														new Leading(
																DEFAULT_PANEL_X
																		+ deleteUserSidePanel
																				.getWidth(),
																resultPanel
																		.getWidth(),
																10, 10),
														new Leading(
																DEFAULT_PANEL_Y,
																resultPanel
																		.getHeight(),
																12, 12)));

									}
								}
							} else {
								UIManager.put("OptionPane.yesButtonText",
										"Endre");
								UIManager.put("OptionPane.noButtonText",
										"Slette");
								UIManager.put("OptionPane.cancelButtonText",
										"Avbryt");
								int option = JOptionPane.showConfirmDialog(
										null,
										"Hvilken handling ønsket du å utføre?",
										result.getValueAt(row, nameCol)
												.toString().trim(),
										JOptionPane.YES_NO_CANCEL_OPTION);
								
								if (option == JOptionPane.YES_OPTION) {
									UIManager.put("OptionPane.yesButtonText",
									"OK");
							UIManager.put("OptionPane.noButtonText",
									"Avbryt");
							UIManager.put("OptionPane.cancelButtonText",
									"Avbryt");
									if (msg.confirmDialog("Endre brukeren "
											+ result.getValueAt(row, nameCol)
													.toString() + "?") == 0) {

										model.User updateThis = new model.User();
										updateThis.setUsername(result
												.getValueAt(row, nameCol)
												.toString());
										updateThis.setType(result.getValueAt(
												row, typeCol).toString());
										updateThis.setFirstname(result
												.getValueAt(row, fNameCol)
												.toString());
										updateThis.setSurname(result
												.getValueAt(row, sNameCol)
												.toString());
										updateThis.setdateofBirth(result
												.getValueAt(row, dateCol)
												.toString());
										updateThis.setPhonenumber(result
												.getValueAt(row, nrCol)
												.toString());

										hideAllPanels();
										panel.removeAll();
										createUpdateUserSidePanel();
										createOneUpdatePanel(updateThis);

										panel.add(
												updateUserSidePanel,
												new Constraints(new Leading(0,
														updateUserSidePanel
																.getWidth(),
														10, 10), new Leading(0,
														updateUserSidePanel
																.getHeight(),
														12, 12)));

										panel.add(
												oneUpdatePanel,
												new Constraints(
														new Leading(
																DEFAULT_PANEL_X
																		+ updateUserSidePanel
																				.getWidth(),
																oneUpdatePanel
																		.getWidth(),
																10, 10),
														new Leading(
																DEFAULT_PANEL_Y,
																oneUpdatePanel
																		.getHeight(),
																12, 12)));
									}
								} else if (option == JOptionPane.NO_OPTION) {
									UIManager.put("OptionPane.yesButtonText",
									"OK");
							UIManager.put("OptionPane.noButtonText",
									"Avbryt");
							UIManager.put("OptionPane.cancelButtonText",
									"Avbryt");
									if (msg.confirmDialog("Slette brukeren "
											+ result.getValueAt(row, nameCol)
													.toString().trim() + "?") == 0) {
										if (c.deleteUser(result
												.getValueAt(row, nameCol)
												.toString().trim())) {

											hideAllPanels();
											DefaultTableModel t = (DefaultTableModel) result
													.getModel();
											t.removeRow(row);
											resultScroll.remove(result);
											resultPanel.remove(resultScroll);
											panel.removeAll();
											createDeleteUserSidePanel();
											createResultPanel(result);

											panel.add(
													deleteUserSidePanel,
													new Constraints(
															new Leading(
																	0,
																	deleteUserSidePanel
																			.getWidth(),
																	10, 10),
															new Leading(
																	0,
																	deleteUserSidePanel
																			.getHeight(),
																	12, 12)));

											panel.add(
													resultPanel,
													new Constraints(
															new Leading(
																	DEFAULT_PANEL_X
																			+ deleteUserSidePanel
																					.getWidth(),
																	resultPanel
																			.getWidth(),
																	10, 10),
															new Leading(
																	DEFAULT_PANEL_Y,
																	resultPanel
																			.getHeight(),
																	12, 12)));

										}
									}
								}
								UIManager.put("OptionPane.yesButtonText",
								"OK");
						UIManager.put("OptionPane.noButtonText",
								"Avbryt");
						UIManager.put("OptionPane.cancelButtonText",
								"Avbryt");
							}
						}
					});
			// } else {
			// result.setRowSelectionAllowed(false);
			// }

			// set table-size
			result.setSize(tableW + 5,
					((result.getRowCount() + 1) * result.getRowHeight()) + 1);

		} else {
			result = null;
		}
		// create panel and add listeners
		resultPanel = new JPanel();
		resultPanel.setLayout(new BorderLayout());
		resultPanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		resultPanel.addKeyListener(this);

		// Add the table to a scrolling pane
		resultScroll = new JScrollPane(result);
		resultScroll.setBackground(c.user.getBackgroundColor().brighter());
		resultPanel.add(resultScroll, BorderLayout.CENTER);

		// set panel size
		if (result == null)
			resultPanel.setSize(0, 0);
		else
			resultPanel.setSize(result.getWidth(), result.getHeight());
	}

	public void createOneUpdatePanel(model.User user) {
		if (user == null) {
			oneUpdateUser = new model.User();
		} else {
			oneUpdateUser = user;
		}

		// create panel and add listeners
		oneUpdatePanel = new JPanel();
		oneUpdatePanel.setLayout(new GroupLayout());
		oneUpdatePanel.setBorder(BorderFactory.createLineBorder(c.user
				.getForegroundColor()));
		oneUpdatePanel.addKeyListener(this);

		// create jlabels
		oneUpdateTitleLabel = new JLabel("Fyll ut endringene du vil utføre");
		oneUpdateusernameLabel = new JLabel("Brukernavn");
		oneUpdatepasswordLabel = new JLabel("Passord");
		oneUpdatetypeLabel = new JLabel("Brukertype");
		oneUpdatefirstnameLabel = new JLabel("Fornavn");
		oneUpdatesurnameLabel = new JLabel("Etternavn");
		oneUpdatedateOfBirthLabel = new JLabel("Fødselsdato");
		oneUpdatenrLabel = new JLabel("Telefon");
		oneUpdateusernameField = new JLabel(oneUpdateUser.getUsername());

		// create inputfields
		oneUpdatefirstnameInput = new JTextField(oneUpdateUser.getFirstname());
		oneUpdatesurnameInput = new JTextField(oneUpdateUser.getSurname());
		oneUpdatedateOfBirthInput = new JTextField(
				oneUpdateUser.getdateofBirth());
		oneUpdatenrInput = new JTextField(oneUpdateUser.getPhonenumber());

		oneUpdatetypeInput = new JComboBox();

		// create buttons
		oneUpdatepasswordButton = new JButton("Endre passordet");
		oneUpdateButton = new JButton("Lagre endring");
		cancelOneUpdateButton = new JButton("Avbryt");

		// add keylistener to all inputfield and buttons
		oneUpdatefirstnameInput.addKeyListener(this);
		oneUpdatesurnameInput.addKeyListener(this);
		oneUpdatedateOfBirthInput.addKeyListener(this);
		oneUpdatenrInput.addKeyListener(this);

		oneUpdatepasswordButton.addKeyListener(this);

		oneUpdateButton.addKeyListener(this);
		cancelOneUpdateButton.addKeyListener(this);

		// add actionlistener to the buttons
		oneUpdateButton.addActionListener(this);
		cancelOneUpdateButton.addActionListener(this);
		oneUpdatepasswordButton.addActionListener(this);

		// set up the combobox
		oneUpdatetypeInput.setModel(new DefaultComboBoxModel(new Object[] {
				"Standardbruker", "Administrator" }));
		oneUpdatetypeInput.setSelectedItem(oneUpdateUser.getType());
		oneUpdatetypeInput.setSize(
				(int) (fontmetrics.stringWidth("Standardbruker") * 1.5),
				(int) (fontmetrics.getHeight() * 1.5));

		// increase the size and edit the style for the title
		oneUpdateTitleLabel.setFont(new Font(c.user.getFamily(), 1, c.user
				.getSize() + 2));

		// holds the component's Y-property
		oneUpdatesearchHeigth = 15;
		// set inputfield-size
		int inputH = oneUpdatetypeInput.getHeight();
		int inputW = oneUpdatetypeInput.getWidth();

		// holds the longest labeltext
		String labeltext;

		// add labels to panel
		oneUpdatePanel.add(oneUpdateTitleLabel, new Constraints(new Leading(10,
				10, 10), new Leading(oneUpdatesearchHeigth, 10, 10)));
		oneUpdatesearchHeigth += 15 + inputH;

		oneUpdatePanel.add(oneUpdateusernameLabel, new Constraints(new Leading(
				15, 10, 10), new Leading(oneUpdatesearchHeigth, 10, 10)));
		oneUpdatesearchHeigth += 15 + inputH;
		labeltext = oneUpdateusernameLabel.getText();

		oneUpdatePanel.add(oneUpdatepasswordLabel, new Constraints(new Leading(
				15, 10, 10), new Leading(oneUpdatesearchHeigth, 10, 10)));
		oneUpdatesearchHeigth += 15 + inputH;
		if (labeltext.length() < oneUpdatepasswordLabel.getText().length())
			labeltext = oneUpdatepasswordLabel.getText();

		oneUpdatePanel.add(oneUpdatetypeLabel, new Constraints(new Leading(15,
				10, 10), new Leading(oneUpdatesearchHeigth, 10, 10)));
		oneUpdatesearchHeigth += 15 + inputH;
		if (labeltext.length() < oneUpdatetypeLabel.getText().length())
			labeltext = oneUpdatetypeLabel.getText();

		oneUpdatePanel.add(oneUpdatefirstnameLabel, new Constraints(
				new Leading(15, 10, 10), new Leading(oneUpdatesearchHeigth, 10,
						10)));
		oneUpdatesearchHeigth += 15 + inputH;
		if (labeltext.length() < oneUpdatefirstnameLabel.getText().length())
			labeltext = oneUpdatefirstnameLabel.getText();

		oneUpdatePanel.add(oneUpdatesurnameLabel, new Constraints(new Leading(
				15, 10, 10), new Leading(oneUpdatesearchHeigth, 10, 10)));
		oneUpdatesearchHeigth += 15 + inputH;
		if (labeltext.length() < oneUpdatesurnameLabel.getText().length())
			labeltext = oneUpdatesurnameLabel.getText();

		oneUpdatePanel.add(oneUpdatedateOfBirthLabel, new Constraints(
				new Leading(15, 10, 10), new Leading(oneUpdatesearchHeigth, 10,
						10)));
		oneUpdatesearchHeigth += 15 + inputH;
		if (labeltext.length() < oneUpdatedateOfBirthLabel.getText().length())
			labeltext = oneUpdatedateOfBirthLabel.getText();

		oneUpdatePanel.add(oneUpdatenrLabel, new Constraints(new Leading(15,
				10, 10), new Leading(oneUpdatesearchHeigth, 10, 10)));
		oneUpdatesearchHeigth += 15 + inputH;
		if (labeltext.length() < oneUpdatenrLabel.getText().length())
			labeltext = oneUpdatenrLabel.getText();

		// add inputfields to panel
		oneUpdatesearchHeigth = 25 + inputH; // reset the searchHeigth to
		// usernameLabel-position
		int width = fontmetrics.stringWidth(labeltext) + 30;// set the input's
															// x-position

		oneUpdatePanel.add(oneUpdateusernameField, new Constraints(new Leading(
				width, inputW, 10, 10), new Leading(oneUpdatesearchHeigth,
				inputH, 10, 10)));
		oneUpdatesearchHeigth += 15 + inputH;

		oneUpdatePanel.add(oneUpdatepasswordButton, new Constraints(
				new Leading(width, inputW, 10, 10), new Leading(
						oneUpdatesearchHeigth, inputH, 10, 10)));
		oneUpdatesearchHeigth += 15 + inputH;

		oneUpdatePanel.add(oneUpdatetypeInput, new Constraints(new Leading(
				width, inputW, 10, 10), new Leading(oneUpdatesearchHeigth,
				inputH, 10, 10)));
		oneUpdatesearchHeigth += 15 + inputH;

		oneUpdatePanel.add(oneUpdatefirstnameInput, new Constraints(
				new Leading(width, inputW, 10, 10), new Leading(
						oneUpdatesearchHeigth, inputH, 10, 10)));
		oneUpdatesearchHeigth += 15 + inputH;

		oneUpdatePanel.add(oneUpdatesurnameInput, new Constraints(new Leading(
				width, inputW, 10, 10), new Leading(oneUpdatesearchHeigth,
				inputH, 10, 10)));
		oneUpdatesearchHeigth += 15 + inputH;

		oneUpdatePanel.add(oneUpdatedateOfBirthInput, new Constraints(
				new Leading(width, inputW, 10, 10), new Leading(
						oneUpdatesearchHeigth, inputH, 10, 10)));
		oneUpdatesearchHeigth += 15 + inputH;

		oneUpdatePanel.add(oneUpdatenrInput, new Constraints(new Leading(width,
				inputW, 10, 10), new Leading(oneUpdatesearchHeigth, inputH, 10,
				10)));
		oneUpdatesearchHeigth += 25 + inputH;

		// add buttons to panel
		oneUpdatePanel.add(cancelOneUpdateButton, new Constraints(new Trailing(
				10, 10, 10), new Trailing(10, 10, 10)));
		int buttonW = (cancelOneUpdateButton.getInsets().left * 2) + 20
				+ fontmetrics.stringWidth(cancelOneUpdateButton.getText());
		oneUpdatePanel.add(oneUpdateButton, new Constraints(new Trailing(
				buttonW, 10, 10), new Trailing(10, 10, 10)));

		oneUpdatesearchHeigth += fontmetrics.getHeight() + 14;

		// set panel size
		oneUpdatePanel.setSize(10 + width + inputW > DEFAULT_PANEL_W
				- updateUserSidePanel.getWidth() ? 10 + width + inputW
				: DEFAULT_PANEL_W - updateUserSidePanel.getWidth(),
				oneUpdatesearchHeigth > DEFAULT_PANEL_H ? oneUpdatesearchHeigth
						: DEFAULT_PANEL_H);
	}

	public void autoResizeColWidth(JTable table) {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableW = 0;
		int margin = 5;

		for (int i = 0; i < table.getColumnCount(); i++) {
			int vColIndex = i;
			TableColumn col = table.getColumnModel().getColumn(vColIndex);
			int tableWidth = 0;

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
			tableW += tableWidth;
			// Set the tableWidth
			col.setPreferredWidth(tableWidth);
		}

		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(SwingConstants.LEFT);
	}

	public void clearRegistrationForm() {
		usernameInput.setText("");
		passwordInput.setText("");
		password2Input.setText("");
		typeInput.setSelectedIndex(0);
		firstnameInput.setText("");
		surnameInput.setText("");
		dateOfBirthInput.setText("dd-mm-åååå");
		nrInput.setText("");
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
			int DEFAULT_IMAGE_WIDTH = 130;
			int DEFAULT_IMAGE_HEIGTH = 170;
			int width = fontmetrics
					.stringWidth("(c) Rabea Kvinneforening, 2011. Alle rettigheter.") + 20;
			Picture rkflogo = new Picture("Image/LOGO.jpg",
					(int) (DEFAULT_IMAGE_WIDTH / 2),
					(int) (DEFAULT_IMAGE_HEIGTH / 2));
			Picture hiologo = new Picture("Image/Hio_Logo.png",
					DEFAULT_IMAGE_WIDTH, (int) (DEFAULT_IMAGE_HEIGTH / 2));

			JLabel label = new JLabel(
					"<html>Rabea Kvinneforenings<br/>Administrasjonsprogram<br/><br/>"
							+ "(c) Rabea Kvinneforening, 2011. Alle rettigheter.<br/>"
							+ "Produktet er laget av gruppe 14<br/>som et hovedprosjekt i data<br/>"
							+ "ved Høgskolen i Oslo, 2011</html>");

			JPanel p = new JPanel();
			p.setLayout(new GroupLayout());

			p.add(rkflogo, new Constraints(new Leading((int) (width / 4)
					- (int) (rkflogo.w / 2), rkflogo.w, 10, 10), new Leading(
					10, rkflogo.h, 12, 12)));
			p.add(hiologo, new Constraints(new Trailing((int) (width / 4)
					- (int) (hiologo.w / 2), hiologo.w, 10, 10), new Leading(
					10, hiologo.h, 12, 12)));
			p.add(label, new Constraints(new Leading(10, 10, 10), new Leading(
					20 + rkflogo.h, 12, 12)));

			JDialog about = new JDialog();
			about.add(p);
			about.pack();
			about.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			about.setTitle("Om programmet");
			about.setIconImage(Toolkit.getDefaultToolkit().getImage(
					"Image/LOGO.jpg"));
			about.setResizable(false);
			about.setAlwaysOnTop(true);
			about.setLocationRelativeTo(null);
			about.setVisible(true);

		} else if (e.getSource() == helpMenuItem) {

			try {
				java.awt.Desktop.getDesktop().open(new File("Documentation/AdminManual.pdf"));
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

		} else if (e.getSource() == newUser) {
			hideAllPanels();
			panel.removeAll();
			createNewUserSidePanel();
			createNewUserPanel();
			panel.add(newUserSidePanel, new Constraints(new Leading(0,
					newUserSidePanel.getWidth(), 10, 10), new Leading(0,
					newUserSidePanel.getHeight(), 12, 12)));

			panel.add(newUserPanel, new Constraints(new Leading(DEFAULT_PANEL_X
					+ newUserSidePanel.getWidth(), newUserPanel.getWidth(), 10,
					10), new Leading(DEFAULT_PANEL_Y, newUserPanel.getHeight(),
					12, 12)));

		} else if (e.getSource() == updateUser) {
			hideAllPanels();
			panel.removeAll();
			createUpdateUserSidePanel();
			createUpdateUserPanel();
			panel.add(updateUserSidePanel, new Constraints(new Leading(0,
					updateUserSidePanel.getWidth(), 10, 10), new Leading(0,
					updateUserSidePanel.getHeight(), 12, 12)));

			panel.add(updateUserPanel, new Constraints(new Leading(
					DEFAULT_PANEL_X + updateUserSidePanel.getWidth(),
					updateUserPanel.getWidth(), 10, 10), new Leading(
					DEFAULT_PANEL_Y, updateUserPanel.getHeight(), 12, 12)));

		} else if (e.getSource() == updateUser2) {
			hideAllPanels();
			panel.removeAll();
			createUpdateUserSidePanel();
			createUpdateUserPanel();
			panel.add(updateUserSidePanel, new Constraints(new Leading(0,
					updateUserSidePanel.getWidth(), 10, 10), new Leading(0,
					updateUserSidePanel.getHeight(), 12, 12)));

			panel.add(updateUserPanel, new Constraints(new Leading(
					DEFAULT_PANEL_X + updateUserSidePanel.getWidth(),
					updateUserPanel.getWidth(), 10, 10), new Leading(
					DEFAULT_PANEL_Y, updateUserPanel.getHeight(), 12, 12)));

		} else if (e.getSource() == deleteUser) {
			hideAllPanels();
			panel.removeAll();
			createDeleteUserSidePanel();
			createDeleteUserPanel();

			panel.add(deleteUserSidePanel, new Constraints(new Leading(0,
					deleteUserSidePanel.getWidth(), 10, 10), new Leading(0,
					deleteUserSidePanel.getHeight(), 12, 12)));

			panel.add(deleteUserPanel, new Constraints(new Leading(
					DEFAULT_PANEL_X + deleteUserSidePanel.getWidth(),
					deleteUserPanel.getWidth(), 10, 10), new Leading(
					DEFAULT_PANEL_Y, deleteUserPanel.getHeight(), 12, 12)));

		} else if (e.getSource() == mainPagefromNew) {
			if (!usernameInput.getText().equals("")
					| !new String(passwordInput.getPassword()).equals("")
					| !new String(password2Input.getPassword()).equals("")
					| !firstnameInput.getText().equals("")
					| !surnameInput.getText().equals("")
					| !dateOfBirthInput.getText().equals("dd-mm-åååå")
					| !nrInput.getText().equals("")) {
				int option = msg
						.confirmDialog("Er du sikker på at du vil tilbake til hovedsiden?\nDette vil tømme skjemaet.");
				if (option == 0) {
					// back to the main page
					hideAllPanels();
					panel.removeAll();
					createMainSidePanel();
					createMainPanel();

					panel.add(mainSidePanel, new Constraints(new Leading(0,
							mainSidePanel.getWidth(), 10, 10), new Leading(0,
							mainSidePanel.getHeight(), 12, 12)));

					panel.add(mainPanel, new Constraints(new Leading(
							DEFAULT_PANEL_X + mainSidePanel.getWidth(),
							mainPanel.getWidth(), 10, 10), new Leading(
							DEFAULT_PANEL_Y, mainPanel.getHeight(), 12, 12)));
				}
			} else {
				// the registration form is empty so we'll
				// go back to the main page
				hideAllPanels();
				panel.removeAll();
				createMainSidePanel();
				createMainPanel();

				panel.add(mainSidePanel, new Constraints(new Leading(0,
						mainSidePanel.getWidth(), 10, 10), new Leading(0,
						mainSidePanel.getHeight(), 12, 12)));

				panel.add(
						mainPanel,
						new Constraints(new Leading(DEFAULT_PANEL_X
								+ mainSidePanel.getWidth(), mainPanel
								.getWidth(), 10, 10), new Leading(
								DEFAULT_PANEL_Y, mainPanel.getHeight(), 12, 12)));
			}

		} else if (e.getSource() == mainPagefromUpdate) {
			if (oneUpdatePanel.isVisible()) {
				int option = msg
						.confirmDialog("Er du sikker på at du vil tilbake til hovedsiden?\nDette vil avbryte en eventuell bruker-oppdatering.");
				if (option == 0) {
					hideAllPanels();
					panel.removeAll();
					createMainSidePanel();
					createMainPanel();

					panel.add(mainSidePanel, new Constraints(new Leading(0,
							mainSidePanel.getWidth(), 10, 10), new Leading(0,
							mainSidePanel.getHeight(), 12, 12)));

					panel.add(mainPanel, new Constraints(new Leading(
							DEFAULT_PANEL_X + mainSidePanel.getWidth(),
							mainPanel.getWidth(), 10, 10), new Leading(
							DEFAULT_PANEL_Y, mainPanel.getHeight(), 12, 12)));
				}
			} else {
				hideAllPanels();
				panel.removeAll();
				createMainSidePanel();
				createMainPanel();

				panel.add(mainSidePanel, new Constraints(new Leading(0,
						mainSidePanel.getWidth(), 10, 10), new Leading(0,
						mainSidePanel.getHeight(), 12, 12)));

				panel.add(
						mainPanel,
						new Constraints(new Leading(DEFAULT_PANEL_X
								+ mainSidePanel.getWidth(), mainPanel
								.getWidth(), 10, 10), new Leading(
								DEFAULT_PANEL_Y, mainPanel.getHeight(), 12, 12)));
			}
		} else if (e.getSource() == mainPagefromDelete) {
			hideAllPanels();
			panel.removeAll();
			createMainSidePanel();
			createMainPanel();

			panel.add(mainSidePanel, new Constraints(new Leading(0,
					mainSidePanel.getWidth(), 10, 10), new Leading(0,
					mainSidePanel.getHeight(), 12, 12)));

			panel.add(
					mainPanel,
					new Constraints(new Leading(DEFAULT_PANEL_X
							+ mainSidePanel.getWidth(), mainPanel.getWidth(),
							10, 10), new Leading(DEFAULT_PANEL_Y, mainPanel
							.getHeight(), 12, 12)));
		} else if (e.getSource() == search) {
			hideAllPanels();
			panel.removeAll();
			createMainSidePanel();
			createSearchPanel();

			panel.add(mainSidePanel, new Constraints(new Leading(0,
					mainSidePanel.getWidth(), 10, 10), new Leading(0,
					mainSidePanel.getHeight(), 12, 12)));

			panel.add(searchPanel, new Constraints(
					new Leading(DEFAULT_PANEL_X + mainSidePanel.getWidth(),
							searchPanel.getWidth(), 10, 10), new Leading(
							DEFAULT_PANEL_Y, searchPanel.getHeight(), 12, 12)));

		} else if (e.getSource() == updateSearch) {
			hideAllPanels();
			panel.removeAll();
			createUpdateUserSidePanel();
			createSearchPanel();

			panel.add(updateUserSidePanel, new Constraints(new Leading(0,
					updateUserSidePanel.getWidth(), 10, 10), new Leading(0,
					updateUserSidePanel.getHeight(), 12, 12)));

			panel.add(
					searchPanel,
					new Constraints(new Leading(DEFAULT_PANEL_X
							+ updateUserSidePanel.getWidth(), searchPanel
							.getWidth(), 10, 10), new Leading(DEFAULT_PANEL_Y,
							searchPanel.getHeight(), 12, 12)));

		} else if (e.getSource() == deleteSearch) {
			hideAllPanels();
			panel.removeAll();
			createDeleteUserSidePanel();
			createSearchPanel();

			panel.add(deleteUserSidePanel, new Constraints(new Leading(0,
					deleteUserSidePanel.getWidth(), 10, 10), new Leading(0,
					deleteUserSidePanel.getHeight(), 12, 12)));

			panel.add(
					searchPanel,
					new Constraints(new Leading(DEFAULT_PANEL_X
							+ deleteUserSidePanel.getWidth(), searchPanel
							.getWidth(), 10, 10), new Leading(DEFAULT_PANEL_Y,
							searchPanel.getHeight(), 12, 12)));

		} else if (e.getSource() == addUserButton) {
			if (c.addUser(usernameInput, passwordInput, password2Input,
					typeInput, firstnameInput, surnameInput, dateOfBirthInput,
					nrInput)) {
				// adding completed
				// clear the registration form
				clearRegistrationForm();
			}
		} else if (e.getSource() == cancelAddUserButton) {
			// clear the registration form
			clearRegistrationForm();

		} else if (e.getSource() == searchButton) {

			result = c.searchUser(searchUsernameInput, searchTypeInput,
					searchFirstnameInput, searchSurnameInput,
					searchDateOfBirthInput, searchNrInput);
			if (result != null) {

				if (updateUserSidePanel.isVisible()) {
					hideAllPanels();
					panel.removeAll();
					createUpdateUserSidePanel();
					createResultPanel(result);

					panel.add(updateUserSidePanel, new Constraints(new Leading(
							0, updateUserSidePanel.getWidth(), 10, 10),
							new Leading(0, updateUserSidePanel.getHeight(), 12,
									12)));

					panel.add(resultPanel, new Constraints(new Leading(
							DEFAULT_PANEL_X + updateUserSidePanel.getWidth(),
							resultPanel.getWidth(), 10, 10), new Leading(
							DEFAULT_PANEL_Y, resultPanel.getHeight(), 12, 12)));

				} else if (deleteUserSidePanel.isVisible()) {
					hideAllPanels();
					panel.removeAll();
					createDeleteUserSidePanel();
					createResultPanel(result);

					panel.add(deleteUserSidePanel, new Constraints(new Leading(
							0, deleteUserSidePanel.getWidth(), 10, 10),
							new Leading(0, deleteUserSidePanel.getHeight(), 12,
									12)));
					panel.add(resultPanel, new Constraints(new Leading(
							DEFAULT_PANEL_X + deleteUserSidePanel.getWidth(),
							resultPanel.getWidth(), 10, 10), new Leading(
							DEFAULT_PANEL_Y, resultPanel.getHeight(), 12, 12)));

				} else {// mainsidepanel is visible
					hideAllPanels();
					panel.removeAll();
					createMainSidePanel();
					createResultPanel(result);

					panel.add(mainSidePanel, new Constraints(new Leading(0,
							mainSidePanel.getWidth(), 10, 10), new Leading(0,
							mainSidePanel.getHeight(), 12, 12)));

					panel.add(resultPanel, new Constraints(new Leading(
							DEFAULT_PANEL_X + mainSidePanel.getWidth(),
							resultPanel.getWidth(), 10, 10), new Leading(
							DEFAULT_PANEL_Y, resultPanel.getHeight(), 12, 12)));

				}
			}
		} else if (e.getSource() == cancelSearchButton) {
			hideAllPanels();
			panel.removeAll();
			createMainSidePanel();
			createMainPanel();

			panel.add(mainSidePanel, new Constraints(new Leading(0,
					mainSidePanel.getWidth(), 10, 10), new Leading(0,
					mainSidePanel.getHeight(), 12, 12)));
			panel.add(
					mainPanel,
					new Constraints(new Leading(DEFAULT_PANEL_X
							+ mainSidePanel.getWidth(), mainPanel.getWidth(),
							10, 10), new Leading(DEFAULT_PANEL_Y, mainPanel
							.getHeight(), 12, 12)));

		} else if (e.getSource() == deleteUserButton) {
			if (c.deleteUser(usernameList.getSelectedValue().toString())) {
				hideAllPanels();
				panel.removeAll();
				createDeleteUserSidePanel();
				createDeleteUserPanel();

				panel.add(deleteUserSidePanel, new Constraints(new Leading(0,
						deleteUserSidePanel.getWidth(), 10, 10), new Leading(0,
						deleteUserSidePanel.getHeight(), 12, 12)));

				panel.add(deleteUserPanel, new Constraints(new Leading(
						DEFAULT_PANEL_X + deleteUserSidePanel.getWidth(),
						deleteUserPanel.getWidth(), 10, 10), new Leading(
						DEFAULT_PANEL_Y, deleteUserPanel.getHeight(), 12, 12)));

			}
		} else if (e.getSource() == cancelDeleteUserButton) {
			hideAllPanels();
			panel.removeAll();
			createMainSidePanel();
			createMainPanel();

			panel.add(mainSidePanel, new Constraints(new Leading(0,
					mainSidePanel.getWidth(), 10, 10), new Leading(0,
					mainSidePanel.getHeight(), 12, 12)));
			panel.add(
					mainPanel,
					new Constraints(new Leading(DEFAULT_PANEL_X
							+ mainSidePanel.getWidth(), mainPanel.getWidth(),
							10, 10), new Leading(DEFAULT_PANEL_Y, mainPanel
							.getHeight(), 12, 12)));
		} else if (e.getSource() == getUserButton) {
			String[][] getUser = c.getUpdatePossibilities(
					updateInput.getText(), updateComboBox.getSelectedItem()
							.toString());
			if (getUser == null) {
				// empty result. do nothing
			} else if (getUser.length == 1) {
				// just one user result.
				// go to update page
				model.User returnedUser = new model.User();
				returnedUser.setUsername(getUser[0][0]);
				returnedUser.setType(getUser[0][1]);
				returnedUser.setFirstname(getUser[0][2]);
				returnedUser.setSurname(getUser[0][3]);
				returnedUser.setdateofBirth(getUser[0][4]);
				returnedUser.setPhonenumber(getUser[0][5]);

				hideAllPanels();
				panel.removeAll();
				createUpdateUserSidePanel();
				createOneUpdatePanel(returnedUser);

				panel.add(updateUserSidePanel, new Constraints(new Leading(0,
						updateUserSidePanel.getWidth(), 10, 10), new Leading(0,
						updateUserSidePanel.getHeight(), 12, 12)));

				panel.add(oneUpdatePanel, new Constraints(new Leading(
						DEFAULT_PANEL_X + updateUserSidePanel.getWidth(),
						oneUpdatePanel.getWidth(), 10, 10), new Leading(
						DEFAULT_PANEL_Y, oneUpdatePanel.getHeight(), 12, 12)));

			} else if (getUser.length > 1) {
				// more than one result.
				// we have to list up and let the user
				// pick the rigth useraccount
				result = c.searchUser(updateInput,
						updateComboBox.getSelectedIndex());
				if (result != null) {

					hideAllPanels();
					panel.removeAll();
					createUpdateUserSidePanel();
					createResultPanel(result);

					panel.add(updateUserSidePanel, new Constraints(new Leading(
							0, updateUserSidePanel.getWidth(), 10, 10),
							new Leading(0, updateUserSidePanel.getHeight(), 12,
									12)));

					panel.add(resultPanel, new Constraints(new Leading(
							DEFAULT_PANEL_X + updateUserSidePanel.getWidth(),
							resultPanel.getWidth(), 10, 10), new Leading(
							DEFAULT_PANEL_Y, resultPanel.getHeight(), 12, 12)));

				}
			}
		} else if (e.getSource() == cancelGetUserButton) {
			hideAllPanels();
			panel.removeAll();
			createMainSidePanel();
			createMainPanel();

			panel.add(mainSidePanel, new Constraints(new Leading(0,
					mainSidePanel.getWidth(), 10, 10), new Leading(0,
					mainSidePanel.getHeight(), 12, 12)));
			panel.add(
					mainPanel,
					new Constraints(new Leading(DEFAULT_PANEL_X
							+ mainSidePanel.getWidth(), mainPanel.getWidth(),
							10, 10), new Leading(DEFAULT_PANEL_Y, mainPanel
							.getHeight(), 12, 12)));
		} else if (e.getSource() == delUser) {
			hideAllPanels();
			panel.removeAll();
			createDeleteUserSidePanel();
			createDeleteUserPanel();

			panel.add(deleteUserSidePanel, new Constraints(new Leading(0,
					deleteUserSidePanel.getWidth(), 10, 10), new Leading(0,
					deleteUserSidePanel.getHeight(), 12, 12)));

			panel.add(deleteUserPanel, new Constraints(new Leading(
					DEFAULT_PANEL_X + deleteUserSidePanel.getWidth(),
					deleteUserPanel.getWidth(), 10, 10), new Leading(
					DEFAULT_PANEL_Y, deleteUserPanel.getHeight(), 12, 12)));

		} else if (e.getSource() == cancelOneUpdateButton) {
			// cancel the update and go back to updatePanel page

			if (msg.confirmDialog("Avbryte endringen?") == 0) {
				hideAllPanels();
				panel.removeAll();
				createUpdateUserSidePanel();
				createUpdateUserPanel();

				panel.add(updateUserSidePanel, new Constraints(new Leading(0,
						updateUserSidePanel.getWidth(), 10, 10), new Leading(0,
						updateUserSidePanel.getHeight(), 12, 12)));

				panel.add(updateUserPanel, new Constraints(new Leading(
						DEFAULT_PANEL_X + updateUserSidePanel.getWidth(),
						updateUserPanel.getWidth(), 10, 10), new Leading(
						DEFAULT_PANEL_Y, updateUserPanel.getHeight(), 12, 12)));

			}
		} else if (e.getSource() == oneUpdateButton) {
			// update user
			model.User uu = c.getUser(oneUpdateusernameField.getText());
			if (uu != null) {
				uu.setType(oneUpdatetypeInput.getSelectedItem().toString());
				uu.setFirstname(oneUpdatefirstnameInput.getText());
				uu.setSurname(oneUpdatesurnameInput.getText());
				uu.setdateofBirth(oneUpdatedateOfBirthInput.getText());
				uu.setPhonenumber(oneUpdatenrInput.getText());
				if (c.updateUser(uu)) {
					// ok update
					msg.messageDialog("Brukeren " + uu.getUsername()
							+ " er oppdatert", 1);
					hideAllPanels();
					panel.removeAll();
					createUpdateUserSidePanel();
					createUpdateUserPanel();

					panel.add(updateUserSidePanel, new Constraints(new Leading(
							0, updateUserSidePanel.getWidth(), 10, 10),
							new Leading(0, updateUserSidePanel.getHeight(), 12,
									12)));

					panel.add(updateUserPanel, new Constraints(new Leading(
							DEFAULT_PANEL_X + updateUserSidePanel.getWidth(),
							updateUserPanel.getWidth(), 10, 10), new Leading(
							DEFAULT_PANEL_Y, updateUserPanel.getHeight(), 12,
							12)));
				}
				else{
					msg.messageDialog(
							"Kunne ikke oppdatere bruker " + uu.getUsername(), 0);
				}
			}

		} else if (e.getSource() == oneUpdatepasswordButton) {
			c.changePassword(oneUpdateUser, "Admin sitt passord");
		}

		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	public void hideAllPanels() {
		mainSidePanel.setVisible(false);
		mainPanel.setVisible(false);
		newUserSidePanel.setVisible(false);
		newUserPanel.setVisible(false);
		updateUserSidePanel.setVisible(false);
		updateUserPanel.setVisible(false);
		oneUpdatePanel.setVisible(false);
		deleteUserSidePanel.setVisible(false);
		deleteUserPanel.setVisible(false);
		searchPanel.setVisible(false);
		resultPanel.setVisible(false);
	}

	@Override
	public void keyPressed(KeyEvent e) {

		// first check for all possible key's
		// that is legal regardless of the visible panels
		if (e.getKeyCode() == KeyEvent.VK_F1) {
			helpMenuItem.doClick();
		} else { // event depending on the visible panel
			if (newUserPanel.isVisible()) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (e.getSource() == cancelAddUserButton) {
						cancelAddUserButton.doClick();
					} else {
						// add user
						addUserButton.doClick();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (addUserButton.isFocusOwner()) {
						cancelAddUserButton.requestFocus();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (cancelAddUserButton.isFocusOwner()) {
						addUserButton.requestFocus();
					}
				}
			} else if (searchPanel.isVisible()) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (e.getSource() == cancelSearchButton) {
						cancelSearchButton.doClick();
					} else {
						searchButton.doClick();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (searchButton.isFocusOwner()) {
						cancelSearchButton.requestFocus();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (cancelSearchButton.isFocusOwner()) {
						searchButton.requestFocus();
					}
				}
			} else if (deleteUserPanel.isVisible()) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (e.getSource() == cancelDeleteUserButton) {
						cancelDeleteUserButton.doClick();
					} else {
						// delete user
						deleteUserButton.doClick();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (deleteUserButton.isFocusOwner()) {
						cancelDeleteUserButton.requestFocus();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (cancelDeleteUserButton.isFocusOwner()) {
						deleteUserButton.requestFocus();
					}
				}
			} else if (updateUserPanel.isVisible()) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (e.getSource() == cancelGetUserButton) {
						cancelGetUserButton.doClick();
					} else {
						getUserButton.doClick();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (getUserButton.isFocusOwner()) {
						cancelGetUserButton.requestFocus();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (cancelGetUserButton.isFocusOwner()) {
						getUserButton.requestFocus();
					}
				}
			} else if (oneUpdatePanel.isVisible()) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (e.getSource() == cancelOneUpdateButton) {
						// cancel the update and go back to updatePanel page
						cancelOneUpdateButton.doClick();
					} else if (e.getSource() == oneUpdatepasswordButton) {
						oneUpdatepasswordButton.doClick();
					} else {
						// update user
						oneUpdateButton.doClick();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (oneUpdateButton.isFocusOwner()) {
						cancelOneUpdateButton.requestFocus();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (cancelOneUpdateButton.isFocusOwner()) {
						oneUpdateButton.requestFocus();
					}
				}
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
