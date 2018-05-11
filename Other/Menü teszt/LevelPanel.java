package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import logic.Game;

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
	 * Konstruktor, meghÃ­vja a komponensek inicializÃ¡lÃ¡sÃ¡t
	 */
	public LevelPanel() {
		initComponents();
	}

	/**
	 * SegÃ©d fÃ¼ggvÃ©ny egy gomb gyorsabb lÃ©trehozÃ¡sÃ¡hoz, szinezÃ©sÃ©hez Ã©s
	 * hÃ¡tterÃ©nek Ã¡tlÃ¡tszÃ³vÃ¡ tÃ©telÃ©hez.
	 * 
	 * @param button
	 *            referencia a gombra
	 * @param size
	 *            a gombon lÃ¡thatÃ³ szÃ¶veg mÃ©rete
	 * @param color
	 *            a gombon lÃ¡thatÃ³ szÃ¶veg szÃ­ne
	 */
	private void createButton(JButton button, float size, Color color) {
		// BetÅ±tÃ­pus bÃ¡llÃ­tÃ¡sa
		button.setFont(getFont().deriveFont(size * sizeMod));
		button.setForeground(color);

		// Ã�tlÃ¡tszÃ³vÃ¡ tÃ©tel
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBorder(null);
	}

	/**
	 * A panelen talÃ¡lhatÃ³ elemek Ã©s a panelhez tartozÃ³ layoutok lÃ©trehozÃ¡sa
	 * Ã©s inicializÃ¡lÃ¡sa
	 */
	private void initComponents() {

		// ---------------------------------------------
		// GOMBOK LÃ‰TREHOZÃ�SA
		// ---------------------------------------------
		// Jungle gomb lÃ©trehozÃ¡sa
		// ---------------------------------------------
		JButton jungle = new JButton("Jungle Ruin");
		jungle.addActionListener((ActionEvent e) -> {
			// TODO: A jungle gomb megnyomÃ¡sÃ¡nak lekezelÃ©se
			// TODO: Felesleges tesztelÃ©si utasÃ­tÃ¡sok kitÃ¶rlÃ©se
			Container parent = getParent();
			CardLayout cd = (CardLayout) parent.getLayout();
			cd.show(parent, "END");
		});
		createButton(jungle, 54.0f, new Color(151, 170, 199));

		// Temple gomb lÃ©trehozÃ¡sa
		// ---------------------------------------------
		JButton temple = new JButton("Temple of Doom");
		temple.addActionListener((ActionEvent e) -> {
			// TODO: Átnézni, mert lehet meg lehet oldani másképpen is
			Game g = Game.getInstance();
			g.startGame("map2.txt");
			Container parent = getParent();
			CardLayout cd = (CardLayout) parent.getLayout();
			cd.show(parent, "GAME");
			
			MainFrame frame = (MainFrame) SwingUtilities.getRoot(this);
			frame.panel.loadTextures("Temple");
		});
		createButton(temple, 54.0f, new Color(151, 170, 199));

		// Dungeon gomb lÃ©trehozÃ¡sa
		// ---------------------------------------------
		JButton dungeon = new JButton("Underground dungeon");
		dungeon.addActionListener((ActionEvent e) -> {
			Game g = Game.getInstance();
			g.startGame("map3.txt");
			Container parent = getParent();
			CardLayout cd = (CardLayout) parent.getLayout();
			cd.show(parent, "GAME");
			
			MainFrame frame = (MainFrame) SwingUtilities.getRoot(this);
			frame.panel.loadTextures("Dungeon");
		});
		createButton(dungeon, 54.0f, new Color(151, 170, 199));

		// Back gomb lÃ©trehozÃ¡sa
		// ---------------------------------------------
		JButton back = new JButton("Back");
		back.addActionListener((ActionEvent e) -> {
			Container parent = getParent();
			CardLayout cd = (CardLayout) parent.getLayout();
			cd.show(parent, "MAIN");
		});
		createButton(back, 64.0f, new Color(197, 209, 227));

		// ---------------------------------------------
		// CÃ�M LÃ‰TREHOZÃ�SA
		// ---------------------------------------------
		JLabel title = new JLabel("Select a map");
		title.setFont(getFont().deriveFont(96.0f * sizeMod));
		title.setForeground(new Color(2, 11, 49));
		title.setOpaque(false);
		title.setHorizontalAlignment(SwingConstants.CENTER);

		// ---------------------------------------------
		// PANELEK LÃ‰TREHOZÃ�SA
		// ---------------------------------------------
		// LegfelsÅ‘ panel, border layouttal
		// ---------------------------------------------
		setLayout(new BorderLayout());
		int border = Math.round(30 * sizeMod);
		setBorder(new EmptyBorder(border, border, border, border));

		// DÃ©li panel lÃ©trehozÃ¡sa, a vissza gombhoz
		// ---------------------------------------------
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel backPanel = new JPanel();
		backPanel.setLayout(new BoxLayout(backPanel, BoxLayout.Y_AXIS));

		backPanel.add(back);
		backPanel.add(Box.createRigidArea(new Dimension(0, Math.round(25 * sizeMod))));
		backPanel.setOpaque(false);

		bottomPanel.setOpaque(false);
		bottomPanel.add(backPanel);

		// KÃ¶zÃ©psÅ‘ panel lÃ©trehozÃ¡sa, a pÃ¡lyÃ¡kat reprezentÃ¡lÃ³ gombokhoz (border
		// layout)
		// Benne egy Flow Layout a jobbra igazÃ­tÃ¡shoz
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

		// Panelek hozzÃ¡rendelÃ©se a legfelsÅ‘ panelhez
		// ---------------------------------------------
		add(title, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
	}
}
