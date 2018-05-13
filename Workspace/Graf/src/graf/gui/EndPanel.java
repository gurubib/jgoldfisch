package graf.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * 
 * Egy panel, egy háttérrel és saját betűtípussal rendelkező osztály, mely
 * tartalmaza a játékosok ponjait, a győztes személyét továbbá két gombot. Egyet
 * a menübe való visszajutáshoz és egyet új játék kezdéséhez.
 * 
 * @author jgoldfisch
 *
 */
public class EndPanel extends MainPanel {

	private JLabel points, winLine;
	
	
	/**
	 * Konstruktor, meghívja a komponensek inicializálását
	 */
	public EndPanel() {
		initComponents();
	}

	/**
	 * Beállítja a két játékos pontjait
	 * 
	 * @param p1
	 *            az első játékos pontja
	 * @param p2
	 *            a második játékos pontja
	 */
	public void setScores(int score1, int score2) {		
		if (score1 > score2)
			winLine.setText("First player won");
		else if (score1 == score2)
			winLine.setText("Its a draw!");
		else
			winLine.setText("Second player won");
		
		if (score1 >= score2)
			points.setText("Player 1 : " + score1 + "    Player 2 : " + score2);
		else
			points.setText("Player 2 : " + score2 + "    Player 1 : " + score1);
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
		// Back gomb létrehozása
		// ---------------------------------------------
		JButton back = new JButton("Back");
		back.addActionListener((ActionEvent e) -> {
			Container parent = getParent();
			CardLayout cd = (CardLayout) parent.getLayout();
			cd.show(parent, "MAIN");
		});
		createButton(back, 64.0f, new Color(197, 209, 227));

		// New Game gomb létrehozása
		// ---------------------------------------------
		JButton newGame = new JButton("New Game");
		newGame.addActionListener((ActionEvent e) -> {
			Container parent = getParent();
			CardLayout cd = (CardLayout) parent.getLayout();
			cd.show(parent, "LEVELS");
		});
		createButton(newGame, 64.0f, new Color(197, 209, 227));

		// ---------------------------------------------
		// FELIRATOK LÉTREHOZÁSA
		// ---------------------------------------------
		// Föcím létrehozása, az, hogy ki a nyertes
		// ---------------------------------------------
		winLine = new JLabel();
		winLine.setFont(getFont().deriveFont(96.0f * sizeMod));
		winLine.setForeground(new Color(0, 0, 0));
		winLine.setOpaque(false);
		winLine.setHorizontalAlignment(SwingConstants.CENTER);

		// Az első játékos pontjait jelölő szöveg
		// ---------------------------------------------
		points = new JLabel();
		points.setFont(getFont().deriveFont(64.0f * sizeMod));
		points.setForeground(new Color(0, 0, 0));
		points.setOpaque(false);
		points.setAlignmentX(CENTER_ALIGNMENT);
		

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

		backPanel.add(newGame);
		backPanel.add(Box.createRigidArea(new Dimension(0, Math.round(25 * sizeMod))));
		backPanel.add(back);
		backPanel.add(Box.createRigidArea(new Dimension(0, Math.round(15 * sizeMod))));
		backPanel.setOpaque(false);

		bottomPanel.setOpaque(false);
		bottomPanel.add(backPanel);

		// Középső panel létrehozása, a játékosok pontjait megjelenítő rész.
		// ---------------------------------------------
		JPanel centerPanel = new JPanel(new BorderLayout());
		JPanel playerPanel = new JPanel();
		playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

		// A játékosok sorrendje attól függően, hogy kinek hány pontja lett.
		// ---------------------------------------------
		playerPanel.add(Box.createRigidArea(new Dimension(0, Math.round(25 * sizeMod))));
		playerPanel.add(points);
		playerPanel.setOpaque(false);

		centerPanel.add(playerPanel, BorderLayout.NORTH);
		centerPanel.setOpaque(false);

		// Panelek hozzárendelése a legfelső panelhez
		// ---------------------------------------------
		add(winLine, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
	}
}
