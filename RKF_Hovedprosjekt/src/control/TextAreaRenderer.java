package control;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

/**
 * @author Sabba
 * 
 */
public class TextAreaRenderer extends JTextArea implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	public TextAreaRenderer() {
		setLineWrap(true);
		setWrapStyleWord(true);
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
	}

	public Component getTableCellRendererComponent(JTable jTable, Object obj,
			boolean isSelected, boolean hasFocus, int row, int column) {
		setText((String) obj);
		return this;
	}
}