package graf.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * 
 * Egy panel, egy háttérrel és saját betűtípussal rendelkező
 * osztály, mely tartalmaz egy új játék és egy kilépés gombot.
 * 
 * @author jgoldfisch
 *
 */
public class MenuPanel extends MainPanel {

	/**
	 * Konstruktor, meghívja a komponensek inicializálását
	 */
	public MenuPanel() {
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
		// New Game gomb létrehozása
		// ---------------------------------------------
		JButton newGame = new JButton("New Game");
		newGame.addActionListener((ActionEvent e) -> {
			Container parent = getParent();
			CardLayout cd = (CardLayout) parent.getLayout();
			cd.show(parent, "LEVELS");
		});
		createButton(newGame, 64.0f, new Color(197, 209, 227));

		// Kilépés gomb létrehozása
		// ---------------------------------------------
		JButton exit = new JButton("Exit");
		exit.addActionListener((ActionEvent e) -> {
			System.exit(0);
		});
		createButton(exit, 64.0f, new Color(197, 209, 227));

		// ---------------------------------------------
		// CÁM LÉTREHOZÁSA
		// ---------------------------------------------
		JLabel title = new JLabel("Killer Sokoban");
		title.setFont(getFont().deriveFont(128.0f * sizeMod));
		title.setForeground(new Color(0, 0, 0));
		title.setOpaque(false);
		title.setHorizontalAlignment(SwingConstants.CENTER);

		// ---------------------------------------------
		// PANELEK LÉTREHOZÁSA
		// ---------------------------------------------
		// Legfelső panel, border layouttal
		// ---------------------------------------------
		setLayout(new BorderLayout());
		int border = Math.round(50 * sizeMod);
		setBorder(new EmptyBorder(border, border, border, border));

		// Déli panel létrehozása, a vissza és az új játék gombhoz
		// ---------------------------------------------
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		rightPanel.add(newGame);
		rightPanel.add(Box.createRigidArea(new Dimension(0,  Math.round(25 * sizeMod))));
		rightPanel.add(exit);
		rightPanel.setOpaque(false);

		bottomPanel.setOpaque(false);
		bottomPanel.add(rightPanel);

		// Panelek hozzárendelése a legfelső panelhez
		// ---------------------------------------------
		add(title, BorderLayout.NORTH);
		add(bottomPanel, BorderLayout.SOUTH);
	}
}
