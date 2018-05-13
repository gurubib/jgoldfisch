package graf.gui;

import java.awt.*;

import javax.swing.*;


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
	public EndPanel endPanel;
	
	private Controller controller = new Controller();

	/**
	 * Kontruktor az osztályhoz, mely inicializálja a rajta található elemeket
	 */
	public MainFrame() {
		initComponents();
		
		controller.setFrame(this);
	}
	
	/**
	 * Szokványos getter a controller attribútumhoz
	 * 
	 * @return A controller attribútum
	 */
	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public GamePanel getPanel() {
		return panel;
	}

	/**
	 * Szokványos setter a panel attribútumhoz
	 * 
	 * @param panel A beállítandó panel
	 */
	public void setPanel(GamePanel panel) {
		this.panel = panel;
	}

	/**
	 * A Frame és a benne találhatÃ³ panelek inicializálása
	 */
	private void initComponents() {
		// Alap beállÃ­tások
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Killer Sokoban");
		setFocusable(true);

		// Teljes képenyő tartva a képarányt.
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
		
		MenuPanel menuPanel = new MenuPanel();
		menuPanel.setBackgroundImage(menuPanel.getBackgroundImage().getScaledInstance(width, -1, Image.SCALE_FAST));
		
		endPanel = new EndPanel();
		endPanel.setBackgroundImage(endPanel.getBackgroundImage().getScaledInstance(width, -1, Image.SCALE_FAST));
		
		LevelPanel levelPanel = new LevelPanel();
		levelPanel.setBackgroundImage(levelPanel.getBackgroundImage().getScaledInstance(width, -1, Image.SCALE_FAST));
		
		KeyboardEventHandler eventHandler = new KeyboardEventHandler();
		eventHandler.setController(controller);
		controller.setKeyboardHandler(eventHandler);
		addKeyListener(eventHandler);
		
		// Panelek hozzáadása a layout-hoz
		panels.add(menuPanel, "MAIN");
		panels.add(endPanel, "END");
		panels.add(levelPanel, "LEVELS");
		
		panel = new GamePanel();
		panels.add(panel, "GAME");

		
		// Panelek hozzáadása a frame-hez

		add(panels);
		pack();
	}
}
