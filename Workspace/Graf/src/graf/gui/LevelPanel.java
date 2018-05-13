package graf.gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * 
 * Egy panel, egy háttérrel és saját betűtípussal rendelkező osztály, mely
 * tartalmaz egy gombot, minden pályára és egy vissza gombot a MainPanel-re való
 * visszajutáshoz.
 * 
 * @author jgoldfisch
 *
 */
public class LevelPanel extends MainPanel {

	/**
	 * Konstruktor, meghívja a komponensek inicializálását
	 */
	public LevelPanel() {
		initComponents();
	}

	/**
	 * Segéd függvény egy gomb gyorsabb létrehozásához, szinezéséhez és
	 * hátterének átlátszóvá tételéhez.
	 * 
	 * @param button
	 *            referencia a gombra
	 * @param size
	 *            a gombon látható szöveg mérete
	 * @param color
	 *            a gombon látható szöveg színe
	 */
	private void createButton(JButton button, float size, Color color) {
		// Betűtípus bállítása
		button.setFont(getFont().deriveFont(size * sizeMod));
		button.setForeground(color);

		// Átlátszóvá tétel
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBorder(null);
	}

	/**
	 * A panelen található elemek és a panelhez tartozó layoutok létrehozása
	 * és inicializálása
	 */
	private void initComponents() {

		// ---------------------------------------------
		// GOMBOK LÉTREHOZÁSA
		// ---------------------------------------------
		// Jungle gomb létrehozása
		// ---------------------------------------------
		JButton jungle = new JButton("Jungle Ruin");
		jungle.addActionListener((ActionEvent e) -> {
			MainFrame frame = (MainFrame) SwingUtilities.getRoot(this);
			frame.panel.clearMap();
			
			MainFrame topFrame = (MainFrame)SwingUtilities.getWindowAncestor(this);
			topFrame.getController().startGame("map1.txt");
			
			Container parent = getParent();
			CardLayout cd = (CardLayout) parent.getLayout();
			cd.show(parent, "GAME");

			frame.panel.loadTextures("Jungle");
		});
		createButton(jungle, 54.0f, new Color(151, 170, 199));

		// Temple gomb létrehozása
		// ---------------------------------------------
		JButton temple = new JButton("Temple of Doom");
		temple.addActionListener((ActionEvent e) -> {
			MainFrame frame = (MainFrame) SwingUtilities.getRoot(this);
			frame.panel.clearMap();
			
			MainFrame topFrame = (MainFrame)SwingUtilities.getWindowAncestor(this);
			topFrame.getController().startGame("map2.txt");
			
			Container parent = getParent();
			CardLayout cd = (CardLayout) parent.getLayout();
			cd.show(parent, "GAME");

			frame.panel.loadTextures("Temple");
		});
		createButton(temple, 54.0f, new Color(151, 170, 199));

		// Dungeon gomb létrehozása
		// ---------------------------------------------
		JButton dungeon = new JButton("Underground dungeon");
		dungeon.addActionListener((ActionEvent e) -> {
			MainFrame frame = (MainFrame) SwingUtilities.getRoot(this);
			frame.panel.clearMap();
			
			MainFrame topFrame = (MainFrame)SwingUtilities.getWindowAncestor(this);
			topFrame.getController().startGame("map3.txt");
			
			Container parent = getParent();
			CardLayout cd = (CardLayout) parent.getLayout();
			cd.show(parent, "GAME");

			frame.panel.loadTextures("Dungeon");
		});
		createButton(dungeon, 54.0f, new Color(151, 170, 199));

		// Back gomb létrehozása
		// ---------------------------------------------
		JButton back = new JButton("Back");
		back.addActionListener((ActionEvent e) -> {
			Container parent = getParent();
			CardLayout cd = (CardLayout) parent.getLayout();
			cd.show(parent, "MAIN");
		});
		createButton(back, 64.0f, new Color(197, 209, 227));

		// ---------------------------------------------
		// CÁM LÉTREHOZÁSA
		// ---------------------------------------------
		JLabel title = new JLabel("Select a map");
		title.setFont(getFont().deriveFont(96.0f * sizeMod));
		title.setForeground(new Color(0, 0, 0));
		title.setOpaque(false);
		title.setHorizontalAlignment(SwingConstants.CENTER);

		// ---------------------------------------------
		// PANELEK LÉTREHOZÁSA
		// ---------------------------------------------
		// Legfelső panel, border layouttal
		// ---------------------------------------------
		setLayout(new BorderLayout());
		int border = Math.round(30 * sizeMod);
		setBorder(new EmptyBorder(border, border, border, border));

		// Déli panel létrehozása, a vissza gombhoz
		// ---------------------------------------------
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel backPanel = new JPanel();
		backPanel.setLayout(new BoxLayout(backPanel, BoxLayout.Y_AXIS));

		backPanel.add(back);
		backPanel.add(Box.createRigidArea(new Dimension(0, Math.round(25 * sizeMod))));
		backPanel.setOpaque(false);

		bottomPanel.setOpaque(false);
		bottomPanel.add(backPanel);

		// Középső panel létrehozása, a pályákat reprezentáló gombokhoz (border
		// layout)
		// Benne egy Flow Layout a jobbra igazításhoz
		// ---------------------------------------------
		JPanel centerPanel = new JPanel(new BorderLayout());
		JPanel mapPanel = new JPanel();
		mapPanel.setLayout(new BoxLayout(mapPanel, BoxLayout.Y_AXIS));

		mapPanel.add(jungle);
		mapPanel.add(Box.createRigidArea(new Dimension(0, Math.round(20 * sizeMod))));
		mapPanel.add(temple);
		mapPanel.add(Box.createRigidArea(new Dimension(0, Math.round(20 * sizeMod))));
		mapPanel.add(dungeon);
		mapPanel.add(Box.createRigidArea(new Dimension(0, Math.round(100 * sizeMod))));
		mapPanel.setOpaque(false);

		centerPanel.add(mapPanel, BorderLayout.SOUTH);
		centerPanel.setOpaque(false);

		// Panelek hozzárendelése a legfelső panelhez
		// ---------------------------------------------
		add(title, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
	}
}
