package graf.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class EndPanel extends MainPanel {

	/**
	 * 
	 * Egy panel, egy háttérrel és saját betűtípussal rendelkező osztály, mely
	 * tartalmaza a játékosok ponjait, a győztes személyét továbbá két gombot. Egyet
	 * a menübe való visszajutáshoz és egyet új játék kezdéséhez.
	 * 
	 * @author jgoldfisch
	 *
	 */

	private JLabel points, winLine;
	
	
	/**
	 * Konstruktor, meghÃ­vja a komponensek inicializÃ¡lÃ¡sÃ¡t
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
		// Back gomb lÃ©trehozÃ¡sa
		// ---------------------------------------------
		JButton back = new JButton("Back");
		back.addActionListener((ActionEvent e) -> {
			Container parent = getParent();
			CardLayout cd = (CardLayout) parent.getLayout();
			cd.show(parent, "MAIN");
		});
		createButton(back, 64.0f, new Color(197, 209, 227));

		// New Game gomb lÃ©trehozÃ¡sa
		// ---------------------------------------------
		JButton newGame = new JButton("New Game");
		newGame.addActionListener((ActionEvent e) -> {
			Container parent = getParent();
			CardLayout cd = (CardLayout) parent.getLayout();
			cd.show(parent, "LEVELS");
		});
		createButton(newGame, 64.0f, new Color(197, 209, 227));

		// ---------------------------------------------
		// FELIRATOK LÃ‰TREHOZÃ�SA
		// ---------------------------------------------
		// FÃ¶cÃ­m lÃ©trehozÃ¡sa, az, hogy ki a nyertes
		// ---------------------------------------------
		winLine = new JLabel();
		winLine.setFont(getFont().deriveFont(96.0f * sizeMod));
		winLine.setForeground(new Color(2, 11, 49));
		winLine.setOpaque(false);
		winLine.setHorizontalAlignment(SwingConstants.CENTER);

		// Az elsÅ‘ jÃ¡tÃ©kos pontjait jelÃ¶lÅ‘ szÃ¶veg
		// ---------------------------------------------
		points = new JLabel();
		points.setFont(getFont().deriveFont(64.0f * sizeMod));
		points.setForeground(new Color(2, 11, 49));
		points.setOpaque(false);
		points.setAlignmentX(CENTER_ALIGNMENT);
		

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

		backPanel.add(newGame);
		backPanel.add(Box.createRigidArea(new Dimension(0, Math.round(25 * sizeMod))));
		backPanel.add(back);
		backPanel.add(Box.createRigidArea(new Dimension(0, Math.round(15 * sizeMod))));
		backPanel.setOpaque(false);

		bottomPanel.setOpaque(false);
		bottomPanel.add(backPanel);

		// KÃ¶zÃ©psÅ‘ panel lÃ©trehozÃ¡sa, a jÃ¡tÃ©kosok pontjait megjelenÃ­tÅ‘ rÃ©sz.
		// ---------------------------------------------
		JPanel centerPanel = new JPanel(new BorderLayout());
		JPanel playerPanel = new JPanel();
		playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

		// A jÃ¡tÃ©kosok sorrendje attÃ³l fÃ¼ggÅ‘en, hogy kinek hÃ¡ny pontja lett.
		// ---------------------------------------------
		playerPanel.add(Box.createRigidArea(new Dimension(0, Math.round(25 * sizeMod))));
		playerPanel.add(points);
		playerPanel.setOpaque(false);

		centerPanel.add(playerPanel, BorderLayout.NORTH);
		centerPanel.setOpaque(false);

		// Panelek hozzÃ¡rendelÃ©se a legfelsÅ‘ panelhez
		// ---------------------------------------------
		add(winLine, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
	}
}
