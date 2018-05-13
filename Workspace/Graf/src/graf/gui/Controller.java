package graf.gui;

import java.awt.CardLayout;
import java.util.List;

import graf.logic.Direction;
import graf.logic.Game;
import graf.logic.Worker;

/**
 * Az MVC mintában a controller-t megvalósító osztály. Az egyes inputok alapján végzi az egész működés irányítását.
 * A bemenetek a billentyűzet, illetve a különböző képeken (panel-ek) lévő gombok használatából keletkeznek.
 * 
 * @author jgoldfisch
 *
 */
public class Controller {
	
	/**
	 * A főablak, ez tartalmazza a legfőbb JPanelt.
	 */
	private MainFrame frame;
	
	/**
	 * A modellben lévő játék példánya
	 */
	private Game game = Game.getInstance();
	
	/**
	 * A billentyűzet eseménykezelő
	 */
	private KeyboardEventHandler keyboardHandler;
	
	/**
	 * Konstruktor, beállítja a modellben lévő játéknak önmagát vezérlőnek
	 */
	public Controller() {
		game.setController(this);
	}
	
	/**
	 * Szokványos getter, a frame attribútumhoz
	 * 
	 * @return A frame attribútum
	 */
	public MainFrame getFrame() {
		return frame;
	}

	/**
	 * Szokványos setter a frame attribútumhoz
	 * 
	 * @param frame A beállítandó frame attribútum
	 */
	public void setFrame(MainFrame frame) {
		this.frame = frame;
	}

	/**
	 * Szokványos getter, a game attribútumhoz
	 * 
	 * @return A game attribútum
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * Szokványos setter a game attribútumhoz
	 * 
	 * @param game A beállítandó game attribútum
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * Szokványos getter, a keyBoardHandler attribútumhoz
	 * 
	 * @return A keyBoardHandler attribútum
	 */
	public KeyboardEventHandler getKeyboardHandler() {
		return keyboardHandler;
	}

	/**
	 * Szokványos setter a keyBoardHandler attribútumhoz
	 * 
	 * @param keyBoardHandler A beállítandó keyBoardHandler attribútum
	 */
	public void setKeyboardHandler(KeyboardEventHandler keyboardHandler) {
		this.keyboardHandler = keyboardHandler;
	}

	/**
	 * Játékos mozgatását kezdeményező függvény, meghívja a modellben a felelős függvényt
	 * 
	 * @param workerID A mozgatandó játékos
	 * @param d A mozgatás iránya
	 */
	public void workerControlled(int workerID, Direction d) {
		List<Worker> workers = game.getWorkers();
		
		Worker controlledWorker = null;
		
		for (Worker w : workers) {
			if (w.getId() == workerID)
				controlledWorker = w;
		}
		
		if (controlledWorker != null) {
			controlledWorker.control(d);
			
			int score = controlledWorker.getPoints();
			frame.panel.setScore(workerID, score);
		}
		
		updateView();
	}
	
	/**
	 * Speciális elem (olaj) lerakását indító függvény, meghívja a modellben ezt a tevékenységet végző függvényt
	 * 
	 * @param workerID Az irányítandó játékos
	 */
	public void oilDropped(int workerID) {
		Worker currentWorker = null;
		for(Worker w : game.getWorkers()) {
			if(w.getId() == workerID)
				currentWorker = w;
		}
		
		if(currentWorker != null) {
			currentWorker.placeOil();
		}
		updateView();
	}
	
	/**
	 * Speciális elem (méz) lerakását indító függvény, meghívja a modellben ezt a tevékenységet végző függvényt.
	 * 
	 * @param workerID Az irányítandó játékos
	 */
	public void honeyDropped(int workerID) {
		Worker currentWorker = null;
		for(Worker w : game.getWorkers()) {
			if(w.getId() == workerID)
				currentWorker = w;
		}
		
		if(currentWorker != null) {
			currentWorker.placeHoney();
		}
		updateView();
	}
	
	/**
	 * A view-t frissítő függvény, hatására az egész view újrarajzolódik
	 */
	public void updateView() {
		frame.getPanel().drawDynamicView();
	}
	
	/**
	 * Új játék kezdeményezését indító függvény, inicializálja a játékot (Game),
	 * továbbá betölti a textúrákat, kirajzolja a játékképet (GamePanel) és megjeleníti azt
	 * 
	 * @param mapFile A térképfájl neve
	 */
	public void startGame(String mapFile) {
		game.startGame(mapFile);
		keyboardHandler.setGaming(true);
	}
	
	/**
	 * Játék vége esetén hívó függvény, ekkor megjeleníti a záróképet (EndPanel), a pontokkal
	 * 
	 * @param score1 Első játékos pontja
	 * @param score2 Második játékos pontja
	 */
	public void endGame(int score1, int score2) {
		frame.endPanel.setScores(score1, score2);
		CardLayout cd = (CardLayout) frame.endPanel.getParent().getLayout();
		cd.show(frame.endPanel.getParent(), "END");
	}
}
