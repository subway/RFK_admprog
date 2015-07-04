package model;

import java.awt.image.BufferedImage;

import javax.swing.*;
import java.awt.*;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.Leading;

import view.Picture;

/**
 * @author Sabba
 * 
 */
public class MembershipCard extends JFrame {
	
	private static final long serialVersionUID = 1L;
	BufferedImage bgImage, fgImage;
	JPanel p;
	JLabel id, name, date;

	public MembershipCard(String imageURL, String id, String name, String date,
			Color color) {

		Picture card = new Picture("Image/MembershipCard.jpg", 220, 145);
		
		// make labels
		this.id = new JLabel("Medlemsnr. " + id);
		this.name = new JLabel(name);
		this.date = new JLabel("Gyldig til " + date);

		// add font
		this.id.setFont(new Font("Verdana", 0, 10));
		this.name.setFont(new Font("Verdana", 0, 10));
		this.date.setFont(new Font("Verdana", 0, 10));

		// add foregroundcolor
		this.id.setForeground(Color.BLACK);
		this.name.setForeground(Color.BLACK);
		this.date.setForeground(Color.BLACK);

		// create panel
		p = new JPanel();
		p.setLayout(new GroupLayout());

		int h = 93;

		// add components to panel
		p.add(this.id, new Constraints(new Leading(110, 10, 10), new Leading(
				h, 10, 10)));
		h += 10 + getFontMetrics(this.id.getFont()).getHeight();
		p.add(this.name, new Constraints(new Leading(110, 10, 10), new Leading(
				h, 10, 10)));
		h += 10 + getFontMetrics(this.id.getFont()).getHeight();
		p.add(this.date, new Constraints(new Leading(110, 10, 10), new Leading(
				h, 10, 10)));
		if (!imageURL.equalsIgnoreCase("Image/Unknown.jpg")) {
			Picture photo = new Picture(imageURL, 57, 68);
			p.add(photo, new Constraints(new Leading(31, photo.w, 10, 10),
					new Leading(92, photo.h, 10, 10)));
		}
		p.add(card, new Constraints(new Leading(25, 322, 322, 322),
				new Leading(25, 192, 192, 192)));
		

		p.setSize(230, 155);
		p.setBackground(Color.WHITE);
		p.setVisible(true);
		this.setLayout(new GroupLayout());
		this.setVisible(true);
		this.add(p, new Constraints(new Leading(0, 10, 10), new Leading(0, 10,
				10)));
		this.setSize(300, 230);
		this.setLocationRelativeTo(null);
		this.setTitle("Medlemskort");
		this.setIconImage(Toolkit.getDefaultToolkit()
				.getImage("Image/LOGO.jpg"));
		this.setAlwaysOnTop(true);

	}

	public JPanel getCard() {
		return p;
	}
}
