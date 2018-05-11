package gui;

import java.awt.*;
import javax.swing.*;

import logic.Game;

/**
 * 
 * A játék fő frame-ét tartalmazó osztály. Benne találhatóak a a különböző
 * panelek, melyek a játék megjelenítéséért felelősek.
 * 
 * @author jgoldfisch
 *
 */
public class MainFrame extends JFrame {

	/**
	 * A játék panelt tartalmazó panel
	 */
	public GamePanel panel;

	/**
	 * Kontruktor az osztályhoz, mely inicializálja a rajta található elemeket
	 */
	MainFrame() {
		initComponents();
	}

	/**
	 * A Frame Ã©s a benne talÃ¡lhatÃ³ panelek inicializÃ¡lÃ¡sa
	 */
	private void initComponents() {
		// Alap beÃ¡llÃ­tÃ¡sok
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Killer Sokoban");

		// Teljes kÃ©penyÅ‘ tartva a kÃ©parÃ¡nyt.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		int totalBorder = (height - width * 9 / 16);

		// Ammenyiben a szél nem egész, akkor alul / felül növeljük eggyel.
		int borderTop, borderBot;
		if (totalBorder % 2 == 0)
			borderTop = borderBot = totalBorder / 2;
		else {
			borderTop = totalBorder / 2;
			borderBot = totalBorder / 2 + 1;
		}

		// Teljes képernyős mód beállítása
		setUndecorated(true);
		setExtendedState(MAXIMIZED_BOTH);

		// Panel méretének beállítása, képarányt tartva (és középre igazítása
		// border-rel)
		JPanel panels = new JPanel(new CardLayout());
		panels.setBorder(BorderFactory.createMatteBorder(borderTop, 0, borderBot, 0, Color.BLACK));
		panels.setPreferredSize(new Dimension(width, width * 9 / 16));

		// Panelek hozzÃ¡adÃ¡sa a layout-hoz
		panels.add(new MenuPanel(), "MAIN");
		panels.add(new EndPanel(), "END");
		panels.add(new LevelPanel(), "LEVELS");

		panel = new GamePanel();
		panels.add(panel, "GAME");

		// Panelek hozzÃ¡adÃ¡sa a frame-hez

		add(panels);
		pack();
	}

	// TODO: kitörölni, csak tesztelésre
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}
}
