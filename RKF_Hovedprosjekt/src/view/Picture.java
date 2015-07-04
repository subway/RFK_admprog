package view;

import java.awt.*;
import java.io.File;

import javax.swing.*;

/**
 * @author Sabba This class creates and paint the picture that is used in
 *         userpage.java
 * 
 */
public class Picture extends JPanel {

	private static final long serialVersionUID = 1L;
	public ImageIcon image;
	public String url;
	public int w, h;

	public Picture(String url, int w, int h) {

		this.url = url;
		this.w = w;
		this.h = h;
	}

	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		if (!new File(url).isFile()) {
			url = "Image/Unknown.jpg";
		}
		image = new ImageIcon(url);
		Image img = image.getImage();
		Image newimg = img.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);
		newIcon.paintIcon(this, g, 0, 0);
	}

}
