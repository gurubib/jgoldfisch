
import java.awt.*;
import java.awt.event.ActionEvent;

import java.io.InputStream;
import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class MainPanel extends JPanel {
	
	private JPanel parent;
	
	private Image backgroundImage;
	private Font font;
	
	public MainPanel(JPanel parent) {
		
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
	
	private void initComponents( ) {		
		// Új játék gomb létrehozása
		JButton newGame = new JButton("New Game");
		newGame.addActionListener((ActionEvent e) -> {
			CardLayout cd = (CardLayout) parent.getLayout();
			cd.show(parent, "LEVELS");
		});
		createButton(newGame, 64.0f, new Color(197,209,227));
		
		// Kilépés gomb létrehozása
		JButton exit = new JButton("Exit");
		exit.addActionListener((ActionEvent e) -> {
			System.exit(0);
		});
		createButton(exit, 64.0f, new Color(197,209,227));
		
		// Cím létrehozása
		JLabel title = new JLabel("Killer Sokoban");
		title.setFont(font.deriveFont(128.0f));
		title.setForeground(new Color(197,209,227));
		title.setOpaque(false);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Külső layout létrehozása
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(50,50,50,50));
		
		// Benti gombokhoz lévő panelek és layoutok létrehozása
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));	
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		rightPanel.add(newGame);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		rightPanel.add(exit);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		rightPanel.setOpaque(false);
		
		bottomPanel.setOpaque(false);
		bottomPanel.add(rightPanel);
		
		// Belső panelek hozzáadása az egészhez
		add(title, BorderLayout.NORTH);
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		
	}
}
