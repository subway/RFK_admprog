package view;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.plaf.ActionMapUIResource;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

/**
 * @author Sabba This class contain the code used to display different types of
 *         popup-boxes
 * 
 */
public class Message extends JFrame implements interfaces.I_Message {

	private static final long serialVersionUID = 1L;
	JTextArea area = new JTextArea();

	public Message() {
		area.setEditable(false);
		UIManager.put("OptionPane.windowBindings", new Object[] { "ESCAPE",
				"close", "LEFT", "left", "KP_LEFT", "left", "RIGHT", "right",
				"KP_RIGHT", "right" });
		ActionMap map = new ActionMapUIResource();
		map.put("close", new OptionPaneCloseAction());
		map.put("left", new OptionPaneArrowAction(false));
		map.put("right", new OptionPaneArrowAction(true));
		UIManager.getLookAndFeelDefaults().put("OptionPane.actionMap", map);
		UIManager.put("OptionPane.icon",
				Toolkit.getDefaultToolkit().getImage("Image/LOGO.jpg"));
	}

	public void messageDialog(String msg, int icon) {
		area.setText(msg);
		JOptionPane.showMessageDialog(null, area, null, icon);
	}

	public int confirmDialog(String msg) {
		area.setText(msg);
		return JOptionPane.showConfirmDialog(null, area, null,
				JOptionPane.YES_NO_OPTION, 3);
	}

	public String singlePasswordDialog() {

		JPanel panel = new JPanel();
		panel.setLayout(new GroupLayout());
		JPasswordField password = new JPasswordField(25);
		JLabel label = new JLabel("Passord");

		panel.add(label, new Constraints(new Leading(10, 10, 10), new Leading(
				10, 10, 10)));
		panel.add(
				password,
				new Constraints(new Leading(getFontMetrics(label.getFont())
						.stringWidth(label.getText()) + 15, (getFontMetrics(
						label.getFont()).stringWidth("*") * 27), 10, 10),
						new Leading(10, 10, 10)));

		int option = JOptionPane.showConfirmDialog(null, panel,
				"Bekreft med passordet", JOptionPane.OK_CANCEL_OPTION, -1);
		if (option == 0)
			return new String(password.getPassword()).trim();
		else
			return null;
	}

	public String[] changePasswordDialog(String old) {

		JPanel panel = new JPanel();
		panel.setLayout(new GroupLayout());
		JPasswordField newPassword = new JPasswordField(25);
		JPasswordField repeatPassword = new JPasswordField(25);
		JPasswordField oldPassword = new JPasswordField(25);
		JLabel newLabel = new JLabel("Nytt passord");
		JLabel repeatLabel = new JLabel("Gjenta nytt passord");
		JLabel oldLabel = new JLabel(old);

		int width = getFontMetrics(newLabel.getFont()).stringWidth(
				oldLabel.getText());

		panel.add(oldLabel, new Constraints(new Leading(10, 10, 10),
				new Leading(10, 10, 10)));
		panel.add(newLabel, new Constraints(new Leading(10, 10, 10),
				new Leading(
						getFontMetrics(oldLabel.getFont()).getHeight() + 20,
						10, 10)));
		width = width > getFontMetrics(newLabel.getFont()).stringWidth(
				newLabel.getText()) ? width
				: getFontMetrics(newLabel.getFont()).stringWidth(
						newLabel.getText());
		panel.add(repeatLabel,
				new Constraints(new Leading(10, 10, 10),
						new Leading((getFontMetrics(newLabel.getFont())
								.getHeight() * 2) + 30, 10, 10)));
		width = width > getFontMetrics(newLabel.getFont()).stringWidth(
				repeatLabel.getText()) ? width : getFontMetrics(
				newLabel.getFont()).stringWidth(repeatLabel.getText());

		panel.add(oldPassword, new Constraints(new Leading(width + 15,
				getFontMetrics(newLabel.getFont()).stringWidth("*") * 27, 10,
				10), new Leading(10, 10, 10)));
		panel.add(newPassword, new Constraints(new Leading(width + 15,
				getFontMetrics(newLabel.getFont()).stringWidth("*") * 27, 10,
				10), new Leading(
				getFontMetrics(oldLabel.getFont()).getHeight() + 20, 10, 10)));

		panel.add(repeatPassword, new Constraints(new Leading(width + 15,
				getFontMetrics(newLabel.getFont()).stringWidth("*") * 27, 10,
				10), new Leading((getFontMetrics(oldLabel.getFont())
				.getHeight() * 2) + 30, 10, 10)));

		UIManager.put("OptionPane.okButton", "Lagre");
		int option = JOptionPane.showConfirmDialog(null, panel,
				"Endre passordet", JOptionPane.OK_CANCEL_OPTION, -1);

		String[] s = new String[3];

		if (option == JOptionPane.OK_OPTION) {

			s[0] = new String(newPassword.getPassword());
			s[1] = new String(repeatPassword.getPassword());
			s[2] = new String(oldPassword.getPassword());
			return s;
		}
		return null;
	}

	public String inputDialog(String title, String inputname) {
		return JOptionPane.showInputDialog(null, inputname, title,
				JOptionPane.PLAIN_MESSAGE);
	}

	class OptionPaneCloseAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			JOptionPane optionPane = (JOptionPane) e.getSource();
			optionPane.setValue(JOptionPane.CLOSED_OPTION);
		}
	}

	class OptionPaneArrowAction extends AbstractAction {

		private static final long serialVersionUID = 1L;
		private boolean myMoveRight;

		OptionPaneArrowAction(boolean moveRight) {
			myMoveRight = moveRight;
		}

		public void actionPerformed(ActionEvent e) {
			JOptionPane optionPane = (JOptionPane) e.getSource();

			EventQueue eq = Toolkit.getDefaultToolkit().getSystemEventQueue();

			eq.postEvent(new KeyEvent(optionPane, KeyEvent.KEY_PRESSED, e
					.getWhen(), (myMoveRight) ? 0 : InputEvent.SHIFT_DOWN_MASK,
					KeyEvent.VK_TAB, KeyEvent.CHAR_UNDEFINED,
					KeyEvent.KEY_LOCATION_UNKNOWN));
		}
	}
}
