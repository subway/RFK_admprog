package view;

import control.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 * 
 * @author Sabba This class contains the view-layer from startup to after the
 *         user is logged in
 * 
 */
public class Startup extends JFrame implements ActionListener, FocusListener,
		KeyListener {

	private static final long serialVersionUID = -6731842512335494680L;

	private JDialog logInDialog, firstPage;
	private JButton logInOKButton, logInCancelButton;
	private JTextField inputUsername;
	private JPasswordField inputPassword;
	private JLabel logInLabel, logInUsernameLabel, logInPasswordLabel;

	Control c;
	model.User user = new model.User();
	model.Manager m = new model.Manager(user);

	public Startup(String action) {
		super("Administrasjonsverktøy");
		super.setExtendedState(MAXIMIZED_BOTH);
		super.getContentPane().setBackground(Color.GRAY);
		super.setUndecorated(true);
		super.addFocusListener(this);
		super.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"Image/LOGO.jpg"));

		// If this is first time, then initializing is required
		if (action.equalsIgnoreCase("start")) {
			firstPage = new JDialog();
			firstPage.add(new Picture("Image/LOGO.jpg", 720, 560));
			firstPage.setUndecorated(true);
			firstPage.setSize(720, 560);
			firstPage.setResizable(false);
			firstPage.setTitle("Rabea Kvinneforening");
			firstPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			firstPage.setLocationRelativeTo(null);
			firstPage.setIconImage(Toolkit.getDefaultToolkit().getImage(
					"Image/LOGO.jpg"));
			firstPage.setVisible(true);
			firstPage.requestFocus();
			try {
				Thread.sleep(1500); // do nothing for 1500 miliseconds

				// this is where the upload of DB and required files should
				// happen. When finished, we'll go ahead and "close" this Frame
				c = new Control();
				firstPage.dispose();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			c = new Control();
		}
		logInDialog();
		setVisible(true);

	}

	public void logInDialog() {

		logInDialog = new JDialog();
		logInDialog.setLayout(new FlowLayout());

		logInDialog.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"Image/LOGO.jpg"));
		logInDialog.setSize(215, 100);
		logInDialog.setLocationRelativeTo(null);
		logInDialog.setResizable(false);
		logInDialog.setTitle("Rabea Kvinneforening");
		logInLabel = new JLabel("Administrasjonsprogram");
		logInOKButton = new JButton("Logg inn");
		logInCancelButton = new JButton("Avslutt");

		logInOKButton.addKeyListener(this);
		logInCancelButton.addKeyListener(this);

		logInOKButton.addActionListener(this);
		logInCancelButton.addActionListener(this);

		GroupLayout layout = new GroupLayout(logInDialog.getContentPane());
		logInDialog.getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(logInLabel)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.CENTER)
												.addComponent(logInOKButton))
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.CENTER)
												.addComponent(logInCancelButton))));

		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addComponent(logInLabel)
				.addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(logInOKButton)
								.addComponent(logInCancelButton)));

		logInDialog.add(logInOKButton);
		logInDialog.add(logInCancelButton);
		logInDialog.setVisible(true);
		logInDialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeProgram();
			}
		});
	}

	public void closeProgram() {
		int option = JOptionPane.showConfirmDialog(null, new JLabel(
				"Avslutte programmet?"), null, JOptionPane.YES_NO_OPTION);
		if (option == 0) {
			System.exit(0);
		} else {
			logInDialog.dispose();
			logInDialog();
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void focusGained(FocusEvent e) {

		if (logInDialog.isVisible()) {
			logInDialog.show();
		}

	}

	@Override
	public void focusLost(FocusEvent e) {
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logInOKButton) {
			onEnter();
		} else if (e.getSource() == logInCancelButton) {
			onCancel();
		}
	}

	public void onCancel() {
		if (logInCancelButton.getText() == "Avslutt") {
			closeProgram();
		} else {
			logInDialog.dispose();
			logInDialog();
		}
	}

	public void onEnter() {
		if (!logInDialog.getTitle().equalsIgnoreCase("Logg inn")) {
			logInDialog.setTitle("Logg inn");
			logInDialog.dispose();
			logInDialog.setSize(300, 130);
			logInDialog.setLocationRelativeTo(null);
			logInDialog.remove(logInLabel);

			inputUsername = new JTextField();
			inputPassword = new JPasswordField();
			logInUsernameLabel = new JLabel("Brukernavn");
			logInPasswordLabel = new JLabel("Passord");
			logInCancelButton.setText("Avbryt");

			inputUsername.addKeyListener(this);
			inputPassword.addKeyListener(this);

			GroupLayout layout = new GroupLayout(logInDialog.getContentPane());
			logInDialog.getContentPane().setLayout(layout);
			layout.setAutoCreateGaps(true);
			layout.setAutoCreateContainerGaps(true);

			layout.setHorizontalGroup(layout
					.createSequentialGroup()
					.addGroup(
							layout.createParallelGroup(
									GroupLayout.Alignment.LEADING)
									.addComponent(logInUsernameLabel)
									.addComponent(logInPasswordLabel))
					.addGroup(
							layout.createParallelGroup(
									GroupLayout.Alignment.LEADING)
									.addComponent(inputUsername)
									.addComponent(inputPassword)
									.addGroup(
											layout.createSequentialGroup()
													.addGroup(
															layout.createParallelGroup(
																	GroupLayout.Alignment.LEADING)
																	.addComponent(
																			logInOKButton))
													.addGroup(
															layout.createParallelGroup(
																	GroupLayout.Alignment.LEADING)
																	.addComponent(
																			logInCancelButton)))));

			layout.setVerticalGroup(layout
					.createSequentialGroup()
					.addGroup(
							layout.createParallelGroup(
									GroupLayout.Alignment.CENTER)
									.addComponent(logInUsernameLabel)
									.addComponent(inputUsername))
					.addGroup(
							layout.createParallelGroup(
									GroupLayout.Alignment.CENTER)
									.addComponent(logInPasswordLabel)
									.addComponent(inputPassword))
					.addGroup(
							layout.createParallelGroup(
									GroupLayout.Alignment.BASELINE)
									.addComponent(logInOKButton)
									.addComponent(logInCancelButton)));
			logInDialog.setVisible(true);

		} else {
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			model.User validate = c.validateLogIn(inputUsername, inputPassword);
			if (validate != null) {
				if (validate.getType().equals("Administrator")) {
					// administrator is logging in
					AdministrationPage admin = new AdministrationPage(validate);
					admin.setVisible(true);
					logInDialog.dispose();
					this.dispose();
				} else {// normal user is logging in
					UserPage userPage = new UserPage(validate);
					userPage.setVisible(true);
					logInDialog.dispose();
					this.dispose();
				}
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (logInOKButton.isFocusOwner()) {
				onEnter();
			} else if (logInCancelButton.isFocusOwner()) {
				onCancel();
			} else {
				onEnter();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			onCancel();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (logInOKButton.isFocusOwner()) {
				logInCancelButton.requestFocus();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (logInCancelButton.isFocusOwner()) {
				logInOKButton.requestFocus();
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
