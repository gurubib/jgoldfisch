package graf.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import graf.logic.Direction;

/**
 * A billentyűzet kezelését végző osztály, implementálja a KeyListener interfészt.
 * 
 * @author jgoldfisch
 *
 */
public class KeyboardEventHandler implements KeyListener {

	/**
	 * Az MVC logika Controller része
	 */
	private Controller controller;
	
	/**
	 * Konkrét játékmenet közben igaz, a billentyűzetkezelés csak ott működjön
	 */
	private boolean gaming = false;
	
	/**
	 * Szokványos getter, a controller attribútumra
	 * 
	 * @return A controller
	 */
	public Controller getController() {
		return controller;
	}

	/**
	 * Szokványos setter a controller attribútumra
	 * 
	 * @param controller A beállítandó controller
	 */
	public void setController(Controller controller) {
		this.controller = controller;
	}

	/**
	 * Szokványos getter a gaming attribútumra
	 * 
	 * @return A gaming attribútum értéke
	 */
	public boolean isGaming() {
		return gaming;
	}

	/**
	 * Szokványos setter a gaming attribútumra
	 * 
	 * @param gaming A gaminf beállítandó értéke
	 */
	public void setGaming(boolean gaming) {
		this.gaming = gaming;
	}

	/**
	 * Billentyűzet lenyomás eseménykezelő függvény
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (gaming) {
			switch(arg0.getKeyChar()) {
			case 'w': 
				controller.workerControlled(1, Direction.UP);
				break;
			case 'a': 
				controller.workerControlled(1, Direction.LEFT);
				break;
			case 's': 
				controller.workerControlled(1, Direction.DOWN);
				break;
			case 'd': 
				controller.workerControlled(1, Direction.RIGHT);
				break;
			case 'q':
				controller.honeyDropped(1);
				break;
			case 'e':
				controller.oilDropped(1);
				break;
			case 'k':
				controller.honeyDropped(2);
				break;
			case 'l':
				controller.oilDropped(2);
				break;
			}
			
			switch(arg0.getKeyCode()) {
			case KeyEvent.VK_UP: 
				controller.workerControlled(2, Direction.UP);
				break;
			case KeyEvent.VK_LEFT: 
				controller.workerControlled(2, Direction.LEFT);
				break;
			case KeyEvent.VK_DOWN: 
				controller.workerControlled(2, Direction.DOWN);
				break;
			case KeyEvent.VK_RIGHT: 
				controller.workerControlled(2, Direction.RIGHT);
				break;
			}
		}
		

	}

	/**
	 * Billentyűzet felengedés eseménykezelő függvény
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * Billentyűzet eseménykezelő függvény
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
