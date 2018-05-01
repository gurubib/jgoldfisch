import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame{	
	
	GameFrame() {
		initComponents();
	}
	
	/**
	 * A Frame és a benne található panelek inicializálása
	 */
	private void initComponents() {
		// Alap beállítások
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Killer Sokoban");
		
		// Teljes képenyő
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);
		
		// Panelek létrehozása
		JPanel cards = new JPanel(new CardLayout());
		MainPanel mainPanel = new MainPanel(cards);
		LevelPanel levelPanel = new LevelPanel(cards);
		
		// Panelek hozzáadása a layout-hoz
		cards.add(mainPanel, "MAIN");
		cards.add(levelPanel, "LEVELS");
		
		// Panelek hozzáadása a frame-hez
		cards.setOpaque(true);
		add(cards);
		pack();
	}
	
	public static void main(String[] args) {
		GameFrame frame = new GameFrame();
		frame.setVisible(true);
	}
}
