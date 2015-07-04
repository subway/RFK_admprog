package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import javax.swing.*;

/**
 * @author Sabba This class paints the component given as parameter i.e.
 *         membershipcard
 */
public class Printer implements Printable {

	JPanel utskrift;

	public Printer(JPanel p) {
		utskrift = p;
		utskrift.setSize(330, 200);
	}

	public int print(Graphics g, PageFormat pf, int pageIndex) {
		if (pageIndex != 0)
			return NO_SUCH_PAGE;
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(new Font("Serif", Font.PLAIN, 36));
		g2.setPaint(Color.black);
		utskrift.paint(g2);
		return PAGE_EXISTS;
	}

}
