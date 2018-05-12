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
	 * Konstruktor, meghÃ­vja a komponensek inicializÃ¡lÃ¡sÃ¡t
	 */
	public MenuPanel() {
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
		// New Game gomb lÃ©trehozÃ¡sa
		// ---------------------------------------------
		JButton newGame = new JButton("New Game");
		newGame.addActionListener((ActionEvent e) -> {
			Container parent = getParent();
			CardLayout cd = (CardLayout) parent.getLayout();
			cd.show(parent, "LEVELS");
		});
		createButton(newGame, 64.0f, new Color(197, 209, 227));

		// KilÃ©pÃ©s gomb lÃ©trehozÃ¡sa
		// ---------------------------------------------
		JButton exit = new JButton("Exit");
		exit.addActionListener((ActionEvent e) -> {
			System.exit(0);
		});
		createButton(exit, 64.0f, new Color(197, 209, 227));

		// ---------------------------------------------
		// CÃ�M LÃ‰TREHOZÃ�SA
		// ---------------------------------------------
		JLabel title = new JLabel("Killer Sokoban");
		title.setFont(getFont().deriveFont(128.0f * sizeMod));
		title.setForeground(new Color(2, 11, 49));
		title.setOpaque(false);
		title.setHorizontalAlignment(SwingConstants.CENTER);

		// ---------------------------------------------
		// PANELEK LÃ‰TREHOZÃ�SA
		// ---------------------------------------------
		// LegfelsÅ‘ panel, border layouttal
		// ---------------------------------------------
		setLayout(new BorderLayout());
		int border = Math.round(50 * sizeMod);
		setBorder(new EmptyBorder(border, border, border, border));

		// DÃ©li panel lÃ©trehozÃ¡sa, a vissza Ã©s az Ãºj jÃ¡tÃ©k gombhoz
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

		// Panelek hozzÃ¡rendelÃ©se a legfelsÅ‘ panelhez
		// ---------------------------------------------
		add(title, BorderLayout.NORTH);
		add(bottomPanel, BorderLayout.SOUTH);
	}
}
