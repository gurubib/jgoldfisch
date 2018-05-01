import java.awt.*;
import java.awt.event.ActionEvent;

import java.io.InputStream;
import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LevelPanel extends JPanel {
	
	private JPanel parent;
	
	private Image backgroundImage;
	private Font font;
	
	public LevelPanel(JPanel parent) {
		
		// A kép és a betűtípus betöltése
		try {
			ClassLoader loader = getClass().getClassLoader();
			backgroundImage = ImageIO.read(loader.getResource("menuBackground.jpg"));
			
			InputStream is = loader.getResourceAsStream("WOODCUTTER STORM.ttf");
			font = Font.createFont(Font.TRUETYPE_FONT, is);
			
		} catch(Exception e) {
			System.exit(ERROR);
		}
			
		this.parent = parent;
		initComponents();
	}
	
	private void createButton(JButton button, float size, Color color) {	
		// Betűtípus bállítása
		button.setFont(font.deriveFont(size));
		button.setForeground(color);
		
		// Átlátszóvá tétel
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBorder(null);
	}
	
	private void initComponents() {		
		// Jungle gomb létrehozása
		JButton jungle = new JButton("Jungle Ruin");
		jungle.addActionListener((ActionEvent e) -> {
			// TODO: A jungle gomb megnyomásának lekezelése
		});
		createButton(jungle, 54.0f, new Color(197,209,227));
		jungle.setAlignmentX(CENTER_ALIGNMENT);
		
		// Temple gomb létrehozása
		JButton temple = new JButton("Temple of Doom");
		temple.addActionListener((ActionEvent e) -> {
			// TODO: A temple gomb megnyomásának lekezelése
		});
		createButton(temple, 54.0f, new Color(197,209,227));
		temple.setAlignmentX(CENTER_ALIGNMENT);
		
		
		// Dungeon gomb létrehozása
		JButton dungeon = new JButton("Underground dungeon");
		dungeon.addActionListener((ActionEvent e) -> {
			// TODO: A dungeon gomb megnyomásának lekezelése
		});
		createButton(dungeon, 54.0f, new Color(197,209,227));
		dungeon.setAlignmentX(CENTER_ALIGNMENT);
		
		
		// Back gomb létrehozása
		JButton back = new JButton("Back");
		back.addActionListener((ActionEvent e) -> {
			CardLayout cr = (CardLayout) parent.getLayout();	
			cr.show(parent, "MAIN");
		});
		createButton(back, 64.0f, new Color(197,209,227));
		
		// Cím létrehozása
		JLabel title = new JLabel("Select a map");
		title.setFont(font.deriveFont(96.0f));
		title.setForeground(new Color(197,209,227));
		title.setOpaque(false);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(30, 30, 30, 30));
		
		// A vissza gomb létrehozása
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));	
		JPanel backPanel = new JPanel();
		backPanel.setLayout(new BoxLayout(backPanel, BoxLayout.Y_AXIS));
		
		backPanel.add(back);
		backPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		backPanel.setOpaque(false);
		
		bottomPanel.setOpaque(false);
		bottomPanel.add(backPanel);
		
		// A pálya kiváasztás létrehozása
		JPanel centerPanel = new JPanel(new BorderLayout());	
		JPanel mapPanel = new JPanel();
		mapPanel.setLayout(new BoxLayout(mapPanel, BoxLayout.Y_AXIS));
		
		mapPanel.add(jungle);
		mapPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mapPanel.add(temple);
		mapPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mapPanel.add(dungeon);
		mapPanel.setOpaque(false);
		
		centerPanel.add(mapPanel, BorderLayout.SOUTH);
		centerPanel.setOpaque(false);
		
		add(title, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		
	}
}
