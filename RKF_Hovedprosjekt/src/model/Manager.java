package model;

import java.awt.Color;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.UIManager;

/**
 * @author Sabba
 *
 */
public class Manager {

	@SuppressWarnings("static-access")
	public Manager(User user) {
		// Create UIManager object
		UIManager manager = new UIManager();

		// Create linked list that will store all gradient information
		LinkedList<Object> a = new LinkedList<Object>();
		a.add(0.3);
		a.add(0.3);

		a.add(user.getForegroundColor());

		a.add(user.getForegroundColor().brighter().brighter().brighter());

		a.add(user.getForegroundColor().brighter().brighter().brighter()
				.brighter().brighter());

		// Button values
		manager.put("Button.gradient", a); // backgroundcolor
		manager.put("Button.foreground", user.getBackgroundColor());
		manager.put("Button.font", user.getFont());
		manager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);

		// Menu values
		manager.put("Menu.font", user.getFont());
		manager.put("Menu.foreground", user.getBackgroundColor());
		manager.put("Menu.background", user.getForegroundColor());
		manager.put("Menu.selectionBackground", user.getBackgroundColor());
		manager.put("Menu.selectionForeground", user.getForegroundColor());

		// MenuIten values
		manager.put("MenuItem.font", user.getFont());
		manager.put("MenuItem.foreground", user.getBackgroundColor());
		manager.put("MenuItem.background", user.getForegroundColor());
		manager.put("MenuItem.selectionBackground", user.getBackgroundColor());
		manager.put("MenuItem.selectionForeground", user.getForegroundColor());

		// Panel values
		manager.put("Panel.background", user.getBackgroundColor());

		// Checkbox values
		manager.put("CheckBox.background", user.getBackgroundColor().darker());
		manager.put("CheckBox.foreground", user.getForegroundColor());
		manager.put("Checkbox.font", user.getFont());

		// Label values
		manager.put("Label.foreground", user.getForegroundColor());
		manager.put("Label.background", user.getBackgroundColor());
		manager.put("Label.font", user.getFont());
		//manager.put("Label.border", BorderFactory.createLineBorder(user.getForegroundColor()));

		// Bordertitle values
		manager.put("TitledBorder.foreground", user.getForegroundColor());
		manager.put("TitledBorder.background", user.getBackgroundColor());
		manager.put("TitledBorder.color", user.getForegroundColor());
		manager.put("TitledBorder.font", user.getFont());

		// Frame values
		manager.put("Frame.background", user.getBackgroundColor().darker());

		// TextField values
		manager.put("TextField.foreground", Color.BLACK);
		manager.put("TextField.font", user.getFont());
		manager.put("TextField.border",
				BorderFactory.createLineBorder(user.getForegroundColor()));

		// Textarea values
		manager.put("TextArea.foreground", user.getForegroundColor());
		manager.put("TextArea.background", user.getBackgroundColor());
		manager.put("TextArea.font", user.getFont());

		// PasswordField values
		manager.put("PasswordField.foreground", Color.BLACK);
		manager.put("PasswordField.font", user.getFont());
		manager.put("PasswordField.border",
				BorderFactory.createLineBorder(user.getForegroundColor()));

		// Combobox values
		manager.put("ComboBox.foreground", user.getForegroundColor());
		manager.put("ComboBox.background", user.getBackgroundColor());
		manager.put("ComboBox.font", user.getFont());

		// Table values
		manager.put("Table.font", user.getFont());
		manager.put("Table.foreground", Color.BLACK);

		// OptionPane value
		manager.put("OptionPane.yesButtonText", "OK");
		manager.put("OptionPane.noButtonText", "Avbryt");
		manager.put("OptionPane.cancelButtonText", "Avbryt");
		manager.put("OptionPane.background", user.getBackgroundColor());
		manager.put("OptionPane.font", user.getFont());

		// Filechooser values
		manager.put("FileChooser.background", user.getBackgroundColor().darker());
		manager.put("FileChooser.foreground", user.getForegroundColor());
		manager.put("FileChooser.lookInLabelText", "Bibliotek");
		manager.put("FileChooser.filesOfTypeLabelText", "Filtype:");
		manager.put("FileChooser.upFolderToolTipText", "Flytt en mappe opp");
		manager.put("FileChooser.fileNameLabelText", "Filnavn:");
		manager.put("FileChooser.homeFolderToolTipText", "Skrivebord");
		manager.put("FileChooser.newFolderToolTipText", "Ny mappe");
		manager.put("FileChooser.listViewButtonToolTipText", "Liste");
		manager.put("FileChooser.detailsViewButtonToolTipText", "Detaljer");
		manager.put("FileChooser.saveButtonText", "Lagre");
		manager.put("FileChooser.openButtonText", "Åpne");
		manager.put("FileChooser.cancelButtonText", "Avbryt");
		manager.put("FileChooser.updateButtonText", "Oppdater");
		manager.put("FileChooser.helpButtonText", "Hjelp");
		manager.put("FileChooser.saveButtonToolTipText", "Lagre");
		manager.put("FileChooser.openButtonToolTipText", "Åpne");
		manager.put("FileChooser.cancelButtonToolTipText", "Avbryt");
		manager.put("FileChooser.updateButtonToolTipText", "Oppdater");
		manager.put("FileChooser.helpButtonToolTipText", "Hjelp");
		manager.put("FileChooser.fileNameHeaderText", "Navn");
		manager.put("FileChooser.fileTypeHeaderText", "Type");
		manager.put("FileChooser.fileSizeHeaderText", "Størrelse");
		manager.put("FileChooser.fileDateHeaderText", "Sist endret"); 
	}
}
