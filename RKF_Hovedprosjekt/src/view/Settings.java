package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.colorchooser.*;
import javax.swing.event.*;
import model.*;
import org.dyno.visual.swing.layouts.*;
import org.dyno.visual.swing.layouts.GroupLayout;

/**
 * @author Sabba This class contains the frame and component's that allow a user
 *         to change settings such as color and font on the program
 */
public class Settings extends JFrame implements ActionListener, ItemListener {

	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private JPanel topPanel, colorPanel, mainFontPanel, fontPanel,
			fontTypePanel, fontSizePanel, effectPanel, previewPanel;
	private JButton backgroundColorButton, fontColorButton, colorSaveButton,
			colorCancelButton, fontSaveButton, fontCancelButton,
			colorDefaultButton, fontDefaultButton;
	private JLabel colorPreviewLabel, fontPreviewLabel;
	private JComboBox fontSizeComboBox;
	private JCheckBox boldCheckBox, italicCheckBox;
	private JList fontTypeList, fontSizeList;
	private JScrollPane fontTypeScroll, fontSizeScroll;
	private model.User u;
	private FontMetrics fontMetrics;
	private control.Control c;
	private String bigFont;
	private Dimension dim;
	private int sizeH;

	public Settings(User u, control.Control c) {
		this.u = u;
		this.c = c;
		dim = Toolkit.getDefaultToolkit().getScreenSize();

		this.setIconImage(Toolkit.getDefaultToolkit()
				.getImage("Image/LOGO.jpg"));

		setTitle("Innstillinger");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setEnabled(false);
				resetSettings();
				Frame frame = getFrame("Bakgrunnsfarge");
				if (frame != null) {
					frame.setVisible(false);
					frame.setEnabled(false);
				}
				frame = getFrame("Tekstfarge");
				if (frame != null) {
					frame.setVisible(false);
					frame.setEnabled(false);
				}
			}
		});

		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		String[] fonts = ge.getAvailableFontFamilyNames();
		if (u.getSize() < 14)
			fontMetrics = getFontMetrics(new Font(u.getFamily(), u.getStyle(),
					14));
		else if (u.getSize() >= 14 && u.getSize() <= 26)
			fontMetrics = getFontMetrics(u.getFont());
		else
			fontMetrics = getFontMetrics(new Font(u.getFamily(), u.getStyle(),
					26));

		bigFont = new String(fonts[0]);
		for (int i = 1; i < fonts.length; i++) {

			if (bigFont.length() < fonts[i].length()) {
				bigFont = fonts[i];
			}
		}

		int sizeW = fontMetrics.stringWidth(bigFont + bigFont);
		sizeH = sizeW;

		if (sizeW < 500) {
			sizeW = 500 + fontMetrics.stringWidth(" Avbryt ");
			sizeH = sizeW + 50;
		} else if (sizeW > dim.width) {
			sizeW = dim.width - 25;
		}

		if (sizeH > dim.height) {
			sizeH = dim.height
					- Toolkit.getDefaultToolkit().getScreenInsets(
							getGraphicsConfiguration()).bottom;
		} else if (sizeH < 500) {
			sizeH = 500;
		}
		setSize(sizeW, sizeH);

		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);

		topPanel = new JPanel();
		topPanel.setBackground(u.getBackgroundColor().darker());
		topPanel.setLayout(new BorderLayout());

		// Create the tab pages
		createPage1();
		createPage2();

		setSize(sizeW, sizeH);

		// Create a tabbed pane
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Bakgrunn", colorPanel);
		tabbedPane.addTab("Font", mainFontPanel);
		topPanel.add(tabbedPane, BorderLayout.CENTER);
		getContentPane().add(topPanel);
	}

	// create the color chooser view
	public void createPage1() {

		// backgroundcolor button
		backgroundColorButton = new JButton("Bakgrunnsfarge");
		backgroundColorButton.addActionListener(this);
		backgroundColorButton.setFont(fontMetrics.getFont());
		int backWidth = fontMetrics
				.stringWidth(backgroundColorButton.getText());
		int buttonHeight = fontMetrics.getHeight();
		backgroundColorButton.setSize(backWidth, buttonHeight);

		// fontcolor button
		fontColorButton = new JButton("Fontfarge");
		fontColorButton.addActionListener(this);
		fontColorButton.setFont(fontMetrics.getFont());
		int fontWidth = fontMetrics.stringWidth(fontColorButton.getText());
		int margin = ((backWidth - fontWidth) / 2)
				+ backgroundColorButton.getMargin().left;
		fontColorButton.setMargin(new Insets(2, margin, 2, margin));

		// default button
		colorDefaultButton = new JButton("Standardinnstillinger");
		colorDefaultButton.addActionListener(this);
		colorDefaultButton.setFont(fontMetrics.getFont());

		// cancel button
		colorCancelButton = new JButton("Avbryt");
		colorCancelButton.addActionListener(this);
		colorCancelButton.setFont(fontMetrics.getFont());
		int cancelWidth = fontMetrics.stringWidth(colorCancelButton.getText());
		colorCancelButton.setSize(cancelWidth, buttonHeight);

		// save button
		colorSaveButton = new JButton("Lagre");
		colorSaveButton.addActionListener(this);
		colorSaveButton.setFont(fontMetrics.getFont());
		int saveWidth = fontMetrics.stringWidth(colorSaveButton.getText());
		int saveMargin = ((cancelWidth - saveWidth) / 2)
				+ backgroundColorButton.getMargin().left;
		colorSaveButton.setMargin(new Insets(2, saveMargin, 2, saveMargin));

		// preview label
		colorPreviewLabel = new JLabel("  Forhåndsvisning  ");
		colorPreviewLabel.setOpaque(true);

		// Create new custom border for JLabel
		colorPreviewLabel.setBorder(BorderFactory.createLineBorder(u
				.getForegroundColor()));

		// initialize and add component to colorPanel
		colorPanel = new JPanel();
		colorPanel.setBackground(u.getBackgroundColor().darker());

		colorPanel.setLayout(new GroupLayout());

		colorPanel.add(colorCancelButton, new Constraints(new Trailing(34, 12,
				12), new Trailing(34, 12, 12)));
		colorPanel.add(colorSaveButton, new Constraints(new Trailing(
				80 + colorCancelButton.getSize().width, 10, 10), new Trailing(
				34, 12, 12)));
		colorPanel.add(backgroundColorButton, new Constraints(new Leading(34,
				10, 10), new Leading(55, 12, 12)));
		colorPanel.add(fontColorButton, new Constraints(
				new Leading(34, 10, 10),
				new Leading((75 + (backgroundColorButton.getSize().height)),
						12, 12)));
		colorPanel.add(colorPreviewLabel, new Constraints(new Trailing(34, 10,
				10), new Leading(55 + backgroundColorButton.getSize().height,
				10, 10)));
		colorPanel.add(colorDefaultButton, new Constraints(new Leading(34, 12,
				12), new Trailing(34, 12, 12)));
	}

	// create the font-settings view
	public void createPage2() {

		mainFontPanel = new JPanel();
		mainFontPanel.setBackground(u.getBackgroundColor().darker());

		// add layout and component to the fontPanel
		// which holds the type/size for the font
		fontPanel = new JPanel();
		fontPanel.setBackground(u.getBackgroundColor().darker());

		// the panel which includes the fonttype
		fontTypePanel = new JPanel();
		fontTypePanel.setBackground(u.getBackgroundColor().darker());
		fontTypeScroll = new JScrollPane();

		// making the label
		fontPreviewLabel = new JLabel(u.getFont().getFamily());

		// adding fonts
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		String[] fonts = ge.getAvailableFontFamilyNames();

		int biggestFont = fontMetrics.stringWidth(bigFont);

		fontTypeList = new JList();
		DefaultListModel listModel = new DefaultListModel();

		for (int i = 0; i < fonts.length; i++) {
			listModel.addElement(fonts[i]);
		}

		fontTypeList.setModel(listModel);
		fontTypeList.setFont(fontMetrics.getFont());
		fontTypeList.setForeground(u.getForegroundColor());
		fontTypeList
				.setBackground(u.getBackgroundColor().brighter().brighter());
		fontTypeList.setSelectionBackground(u.getForegroundColor());
		fontTypeList.setSelectionForeground(u.getBackgroundColor());

		fontTypeList.setSelectedValue(fontPreviewLabel.getFont().getFamily()
				.toString(), true);
		fontTypeList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				fontPreviewLabel.setFont(new Font((String) fontTypeList
						.getSelectedValue(), fontPreviewLabel.getFont()
						.getStyle(), fontPreviewLabel.getFont().getSize()));
				fontPreviewLabel
						.setText(fontPreviewLabel.getFont().getFamily());

			}
		});

		fontTypeScroll.setViewportView(fontTypeList);
		fontTypeScroll.setWheelScrollingEnabled(true);

		fontTypePanel.setBorder(BorderFactory.createTitledBorder(null,
				"Skrifttype", TitledBorder.LEADING,
				TitledBorder.DEFAULT_POSITION, fontMetrics.getFont(),
				u.getForegroundColor()));

		fontTypeList.ensureIndexIsVisible(fontTypeList.getSelectedIndex());

		fontTypePanel.setLayout(new GroupLayout());
		fontTypePanel.add(fontTypeScroll, new Constraints(new Leading(4,
				biggestFont + new JScrollBar().getPreferredSize().width + 10,
				10, 10), new Leading(-3, (fontMetrics.getHeight() + 4) * 3, 10,
				10)));
		fontTypePanel.setSize(biggestFont
				+ new JScrollBar().getPreferredSize().width,
				(fontMetrics.getHeight() + 4) * 3);

		// the panel in fontPanel which includes the size
		fontSizePanel = new JPanel();
		fontSizePanel.setBackground(u.getBackgroundColor().darker());
		fontSizeList = new JList();
		DefaultListModel sizeListModel = new DefaultListModel();

		for (int i = 12; i <= 36;) {
			sizeListModel.addElement(i);
			i += 2;
		}

		fontSizeList.setModel(sizeListModel);
		fontSizeList.setFont(fontMetrics.getFont());
		fontSizeList.setForeground(u.getForegroundColor());
		fontSizeList
				.setBackground(u.getBackgroundColor().brighter().brighter());

		fontSizeList.setSelectionBackground(u.getForegroundColor());
		fontSizeList.setSelectionForeground(u.getBackgroundColor());

		fontSizeList.setSelectedValue(fontPreviewLabel.getFont().getSize(),
				true);
		fontSizeList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				fontPreviewLabel.setFont(new Font(fontPreviewLabel.getFont()
						.getFamily(), fontPreviewLabel.getFont().getStyle(),
						(Integer) fontSizeList.getSelectedValue()));

			}
		});

		int fontesizeH = fontMetrics.getHeight();

		fontSizeList.setSize(fontMetrics.stringWidth("Størrelse"), 50);
		fontSizeScroll = new JScrollPane();
		fontSizeScroll.setViewportView(fontSizeList);
		fontSizeScroll.setWheelScrollingEnabled(true);
		fontSizeScroll.setSize(fontMetrics.stringWidth("Størrelse"),
				(fontesizeH * 3));

		fontSizeList.ensureIndexIsVisible(fontSizeList.getSelectedIndex());

		fontTypeList.setPrototypeCellValue(fontPreviewLabel.getText());

		fontSizePanel.setBorder(BorderFactory.createTitledBorder(null,
				"Størrelse", TitledBorder.LEADING,
				TitledBorder.DEFAULT_POSITION, fontMetrics.getFont(),
				u.getForegroundColor()));
		fontSizePanel.setLayout(new GroupLayout());
		fontSizePanel.add(fontSizeScroll, new Constraints(new Leading(4,
				fontMetrics.stringWidth("Størrelse"), 10, 10), new Leading(-3,
				(fontMetrics.getHeight() + 4) * 3, 12, 12)));

		fontPanel.setBorder(BorderFactory.createTitledBorder(null, "Font",
				TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
				fontMetrics.getFont(), u.getForegroundColor()));
		fontPanel.setLayout(new GroupLayout());
		fontPanel.add(
				fontSizePanel,
				new Constraints(new Trailing(10, fontMetrics
						.stringWidth("Størrelse")
						+ new JScrollBar().getPreferredSize().width, 10, 10),
						new Leading(0,
								(fontTypeList.getFixedCellHeight() * 4) + 10,
								12, 12)));
		fontPanel.add(fontTypePanel, new Constraints(new Leading(10,
				fontTypePanel.getWidth()
						+ new JScrollBar().getPreferredSize().width + 10, 10,
				10), new Leading(0,
				(fontTypeList.getFixedCellHeight() * 4) + 10, 12, 12)));

		fontPanel
				.setSize(getWidth() - 68,
						(fontesizeH * 2)
								+ (fontTypeList.getFixedCellHeight() * 4) + 10);

		// Effect panel for bold/italic/plain
		effectPanel = new JPanel();
		effectPanel.setBackground(u.getBackgroundColor().darker());

		boldCheckBox = new JCheckBox();
		boldCheckBox.setText("Fet");
		boldCheckBox.setFont(fontMetrics.getFont());
		boldCheckBox.addItemListener(this);
		italicCheckBox = new JCheckBox();
		italicCheckBox.setText("Kursiv");
		italicCheckBox.setFont(fontMetrics.getFont());
		italicCheckBox.addItemListener(this);

		effectPanel.setBorder(BorderFactory.createTitledBorder(null,
				"Effekter", TitledBorder.LEADING,
				TitledBorder.DEFAULT_POSITION, fontMetrics.getFont(),
				u.getForegroundColor()));
		effectPanel.setLayout(new GroupLayout());
		effectPanel.add(boldCheckBox, new Constraints(new Leading(50, 10, 10),
				new Leading(0, 10, 10)));
		effectPanel.add(italicCheckBox, new Constraints(
				new Trailing(50, 10, 10), new Leading(0, 10, 10)));
		effectPanel.setSize(fontPanel.getWidth(),
				(int) (fontMetrics.getHeight() * 3) + 5);

		// setup for preview-panel

		if (fontPreviewLabel.getFont().getStyle() == 1) {
			boldCheckBox.setSelected(true);
		} else if (fontPreviewLabel.getFont().getStyle() == 2) {
			italicCheckBox.setSelected(true);
		} else if (fontPreviewLabel.getFont().getStyle() == 3) {
			boldCheckBox.setSelected(true);
			italicCheckBox.setSelected(true);
		}

		previewPanel = new JPanel();
		previewPanel.setBackground(u.getBackgroundColor().darker());

		previewPanel.setBorder(BorderFactory.createTitledBorder(null,
				"Forhåndsvisning", TitledBorder.LEADING,
				TitledBorder.DEFAULT_POSITION, fontMetrics.getFont(),
				u.getForegroundColor()));

		previewPanel.setLayout(new GroupLayout());
		previewPanel.add(fontPreviewLabel, new Constraints(new Bilateral(0, 0,
				212), new Leading(-5, 58, 10, 10)));

		fontDefaultButton = new JButton("Standardinnstillinger");
		fontDefaultButton.addActionListener(this);
		fontDefaultButton.setFont(fontMetrics.getFont());

		fontCancelButton = new JButton("Avbryt");
		fontCancelButton.addActionListener(this);
		fontCancelButton.setFont(fontMetrics.getFont());
		int Width = fontMetrics.stringWidth(fontCancelButton.getText());
		int Height = fontMetrics.getHeight();
		fontCancelButton.setSize(Width, Height);

		fontSaveButton = new JButton("Lagre");
		fontSaveButton.addActionListener(this);
		fontSaveButton.setFont(fontMetrics.getFont());
		int saveWidth = fontMetrics.stringWidth(fontSaveButton.getText());
		int saveMargin = ((Width - saveWidth) / 2)
				+ fontCancelButton.getMargin().left;
		fontSaveButton.setMargin(new Insets(2, saveMargin, 2, saveMargin));

		// add layout and component to the "main" panel
		mainFontPanel.setLayout(new GroupLayout());
		int y = 32;
		mainFontPanel.add(fontPanel,
				new Constraints(new Leading(34, fontPanel.getWidth(), 10, 10),
						new Leading(y, fontPanel.getHeight(), 10, 10)));
		y += fontPanel.getHeight() + 20;
		mainFontPanel.add(effectPanel,
				new Constraints(
						new Leading(34, effectPanel.getWidth(), 12, 12),
						new Leading(y, effectPanel.getHeight(), 12, 12)));
		y += effectPanel.getHeight() + 20;
		mainFontPanel.add(previewPanel, new Constraints(new Leading(34,
				fontPanel.getWidth(), 12, 12), new Leading(y, 12, 12)));

		y += 200 + (fontMetrics.getHeight() * 2);
		mainFontPanel.add(fontCancelButton, new Constraints(new Trailing(34,
				12, 12), new Trailing(34, 12, 12)));
		mainFontPanel.add(fontSaveButton, new Constraints(new Trailing(
				80 + fontCancelButton.getSize().width, 10, 10), new Trailing(
				34, 12, 12)));
		mainFontPanel.add(fontDefaultButton, new Constraints(new Leading(34,
				12, 12), new Trailing(34, 12, 12)));

		sizeH = sizeH > y ? sizeH : y;
		mainFontPanel.setSize(getWidth(), sizeH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setCursor(new Cursor(Cursor.WAIT_CURSOR));

		if (e.getSource() == backgroundColorButton) {
			Frame[] f = JFrame.getFrames();
			boolean opened = false;
			for (Frame frames : f) {
				if (frames.getTitle().equals("Bakgrunnsfarge")
						&& frames.isEnabled()) {

					frames.setLocation((dim.width / 2)
							- (frames.getWidth() / 2), (dim.height / 2)
							- (frames.getHeight() / 2));
					frames.setVisible(true);
					frames.setState(Frame.NORMAL);
					frames.requestFocus();
					opened = true;
					break;
				}
			}
			if (!opened) {
				new ColorPickerBackground(this.u);
			}
		} else if (e.getSource() == fontColorButton) {
			Frame[] f = JFrame.getFrames();
			boolean opened = false;
			for (Frame frames : f) {
				if (frames.getTitle().equals("Tekstfarge")
						&& frames.isEnabled()) {

					frames.setLocation((dim.width / 2)
							- (frames.getWidth() / 2), (dim.height / 2)
							- (frames.getHeight() / 2));
					frames.setVisible(true);
					frames.setState(Frame.NORMAL);
					frames.requestFocus();
					opened = true;
					break;
				}
			}
			if (!opened) {
				new ColorPickerForeground(this.u);
			}
		} else if (e.getSource() == colorSaveButton) {
			save();
		} else if (e.getSource() == colorCancelButton) {
			resetSettings();
		} else if (e.getSource() == fontSaveButton) {
			save();
		} else if (e.getSource() == fontCancelButton) {
			resetSettings();
		} else if (e.getSource() == colorDefaultButton) {
			colorPreviewLabel.setBackground(new Color(-16750849));
			colorPreviewLabel.setForeground(new Color(-1));
		} else if (e.getSource() == fontDefaultButton) {
			fontPreviewLabel.setFont(new Font("Verdana", 0, 14));
			// set fontpanel changes
			fontTypeList.setSelectedValue("Verdana", true);
			fontSizeList.setSelectedValue(14, true);
			boldCheckBox.setSelected(false);
			italicCheckBox.setSelected(false);
		}
		// change font size
		else if (e.getSource() == fontSizeComboBox) {
			Font currentFont = fontPreviewLabel.getFont();
			int newSize = Integer.parseInt(fontSizeComboBox.getSelectedItem()
					.toString());
			fontPreviewLabel.setFont(new Font(currentFont.getFontName(),
					currentFont.getStyle(), newSize));

		}
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	public void resetSettings() {
		// set settings back to usersettings
		colorPreviewLabel.setBackground(u.getBackgroundColor());
		colorPreviewLabel.setForeground(u.getForegroundColor());

		fontPreviewLabel.setBackground(u.getBackgroundColor());
		fontPreviewLabel.setForeground(u.getForegroundColor());
		fontPreviewLabel.setFont(new Font(u.getFamily(), u.getStyle(), u
				.getSize()));
		fontTypeList.setSelectedValue(u.getFamily(), true);
		fontSizeList.setSelectedValue(u.getSize(), true);

		if (u.getStyle() == 1) {
			boldCheckBox.setSelected(true);
			italicCheckBox.setSelected(false);
		} else if (u.getStyle() == 2) {
			boldCheckBox.setSelected(false);
			italicCheckBox.setSelected(true);
		} else if (u.getStyle() == 3) {
			boldCheckBox.setSelected(true);
			italicCheckBox.setSelected(true);
		} else {
			boldCheckBox.setSelected(false);
			italicCheckBox.setSelected(false);
		}

		tabbedPane.setSelectedIndex(0);
		// "close" open windows
		this.setVisible(false);
		Frame frame;
		frame = getFrame("Bakgrunnsfarge");
		if (frame != null)
			frame.setVisible(false);
		frame = getFrame("Tekstfarge");
		if (frame != null)
			frame.setVisible(false);
	}

	public Frame getFrame(String frameTitle) {
		Frame[] f = JFrame.getFrames();

		for (Frame frame : f) {
			if (frame.getTitle().equals(frameTitle)) {
				frame.requestFocus();
				return frame;
			}
		}
		return null;
	}

	public void save() {
		int option = c.msg
				.confirmDialog("Programmet må starte på nytt for \nat innstillingene skal tre i kraft.\nStarte på nytt nå?");
		if (option == 0) {
			u.setBackgroundColor(colorPreviewLabel.getBackground().getRGB());
			u.setForegroundColor(colorPreviewLabel.getForeground().getRGB());
			u.setFont(fontPreviewLabel.getFont().getFamily(), fontPreviewLabel
					.getFont().getStyle(), fontPreviewLabel.getFont().getSize());
			if (!c.updateUser(u))
				c.msg.messageDialog(
						"Kunne ikke legge inn de nye innstillingene.", 0);
			c.logOff();
		} else {
			resetSettings();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Font currentFont = fontPreviewLabel.getFont();
		int style = (boldCheckBox.isSelected() ? Font.BOLD : Font.PLAIN)
				| (italicCheckBox.isSelected() ? Font.ITALIC : Font.PLAIN);
		fontPreviewLabel.setFont(new Font(currentFont.getFamily(), style,
				currentFont.getSize()));
	}

	class ColorPickerBackground extends JFrame implements ActionListener {

		private static final long serialVersionUID = 1L;
		private JButton changeColorButton, cancelColorchangeButton;
		public Color newBackgroundColor;
		private JPanel buttonPanel;

		public ColorPickerBackground(User u) {

			this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setTitle("Bakgrunnsfarge");
			this.setIconImage(Toolkit.getDefaultToolkit().getImage(
					"Image/LOGO.jpg"));

			Container contentPane = this.getContentPane();

			changeColorButton = new JButton("Lagre");
			changeColorButton.setForeground(u.getBackgroundColor());
			changeColorButton.setBackground(u.getForegroundColor());
			changeColorButton.addActionListener(this);
			cancelColorchangeButton = new JButton("Avbryt");
			cancelColorchangeButton.setForeground(u.getBackgroundColor());
			cancelColorchangeButton.setBackground(u.getForegroundColor());
			cancelColorchangeButton.addActionListener(this);

			buttonPanel = new JPanel();
			buttonPanel.setBackground(u.getBackgroundColor().darker());

			buttonPanel.add(changeColorButton, new FlowLayout());
			buttonPanel.add(cancelColorchangeButton, new FlowLayout());
			contentPane.add(buttonPanel, BorderLayout.SOUTH);

			final JColorChooser colorChooser = new JColorChooser(
					colorPreviewLabel.getBackground());
			colorChooser.setBorder(BorderFactory.createTitledBorder(null,
					"Velg bakgrunnsfarge", TitledBorder.LEADING,
					TitledBorder.DEFAULT_POSITION, fontMetrics.getFont(),
					u.getForegroundColor()));
			colorChooser.setBackground(u.getBackgroundColor().darker());

			ColorSelectionModel model = colorChooser.getSelectionModel();
			ChangeListener changeListener = new ChangeListener() {
				public void stateChanged(ChangeEvent changeEvent) {
					newBackgroundColor = colorChooser.getColor();
				}
			};
			model.addChangeListener(changeListener);
			contentPane.add(colorChooser, BorderLayout.CENTER);

			this.pack();
			this.setVisible(true);
			Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation((screenDim.width / 2) - (getWidth() / 2),
					(screenDim.height / 2) - (getHeight() / 2));
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == changeColorButton) {
				// set chosen color as previewLabel's backgroundcolor
				colorPreviewLabel.setBackground(newBackgroundColor);
				this.dispose();

			}
			// dispose on cancel
			else if (e.getSource() == cancelColorchangeButton) {
				this.dispose();
			}
		}
	}

	class ColorPickerForeground extends JFrame implements ActionListener {

		private static final long serialVersionUID = 1L;
		private JButton changeColorButton, cancelColorchangeButton;
		public Color newForegroundColor;
		private JPanel buttonPanel;

		public ColorPickerForeground(User u) {

			this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setTitle("Tekstfarge");
			this.setIconImage(Toolkit.getDefaultToolkit().getImage(
					"Image/LOGO.jpg"));

			Container contentPane = this.getContentPane();

			changeColorButton = new JButton("Lagre");
			changeColorButton.setForeground(u.getBackgroundColor());
			changeColorButton.setBackground(u.getForegroundColor());
			changeColorButton.addActionListener(this);

			cancelColorchangeButton = new JButton("Avbryt");
			cancelColorchangeButton.setForeground(u.getBackgroundColor());
			cancelColorchangeButton.setBackground(u.getForegroundColor());
			cancelColorchangeButton.addActionListener(this);

			buttonPanel = new JPanel();
			buttonPanel.setBackground(u.getBackgroundColor().darker());

			buttonPanel.add(changeColorButton, new FlowLayout());
			buttonPanel.add(cancelColorchangeButton, new FlowLayout());
			contentPane.add(buttonPanel, BorderLayout.SOUTH);

			final JColorChooser colorChooser = new JColorChooser(
					colorPreviewLabel.getForeground());
			colorChooser.setBorder(BorderFactory.createTitledBorder(null,
					"Velg farge på teksten", TitledBorder.LEADING,
					TitledBorder.DEFAULT_POSITION, fontMetrics.getFont(),
					u.getForegroundColor()));
			colorChooser.setBackground(u.getBackgroundColor().darker());

			ColorSelectionModel model = colorChooser.getSelectionModel();
			ChangeListener changeListener = new ChangeListener() {
				public void stateChanged(ChangeEvent changeEvent) {
					newForegroundColor = colorChooser.getColor();
				}
			};
			model.addChangeListener(changeListener);
			contentPane.add(colorChooser, BorderLayout.CENTER);

			this.pack();
			this.setVisible(true);
			Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation((screenDim.width / 2) - (getWidth() / 2),
					(screenDim.height / 2) - (getHeight() / 2));
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == changeColorButton) {
				// set chosen color as previewLabel's foregroundcolor
				colorPreviewLabel.setForeground(newForegroundColor);
				this.dispose();

			}
			// dispose on cancel
			else if (e.getSource() == cancelColorchangeButton) {
				this.dispose();
			}
		}
	}
}
