package graf.logic;

import java.util.ArrayList;
import java.util.List;

import graf.gui.Controller;

/**
 * 
 * A játék alapvető mechanizmusaiért felel és tárolja a nyeréshez, vagy vesztéshez szükséges
 * adatokat. Továbbá tárolja a még életben lévő Worker-öket, és a Map-et.
 * 
 * @author jgoldfisch
 *
 */
public class Game {
	

	/**
	 * A Singleton tervezési minta megvalósítását támogató változó.
	 */
	private static final Game instance = new Game();
	

	/**
	 * A pályán lévő, még élő munkások.
	 */
	private List<Worker> workers;
	
	/**
	 * A pályán lévő dobozok
	 */
	private List<Box> boxes;
	
	/**
	 * Az MVC modell controllere
	 */
	private Controller controller;
	
	/**
	 * A játékosok pontszáma
	 */
	private int score1 = 0, score2 = 0;
	
	/**
	 * Szokványos getter, a controller attribútumhoz
	 * 
	 * @return A controller attribútum
	 */
	public Controller getController() {
		return controller;
	}

	/**
	 * Szokványos setter a controller attribútumhoz
	 * 
	 * @param controller A beállítandó controller
	 */
	public void setController(Controller controller) {
		this.controller = controller;
	}

	/**
	 * Szokványos getter a boxes attribútumhoz
	 * 
	 * @return A boxes attribútum
	 */
	public List<Box> getBoxes() {
		return boxes;
	}

	/**
	 * Szokványos setter a boxes attribútumhoz
	 * 
	 * @param boxes A beállítandó boxes attribútum
	 */
	public void setBoxes(List<Box> boxes) {
		this.boxes = boxes;
		freeBoxCounter = boxes.size();
	}

	/**
	 * Referencia a pályát tartalmazó objektumra.
	 */
	private Map map;
	
	/**
	 * Referencia a szabad dobozokat vizsgáló objektumra.
	 */
	private BoxRecorder boxRecorder;
	
	/**
	 * A még mozgatható dobozok számát tárolja.
	 */
	private int freeBoxCounter;
	
	/**
	 * A még játékban lévő munkások számát tárolja.
	 */
	private int workerCounter;
	
	/**
	 * A Singleton tervezési mintához szükséges, kívülről nem elérhető konstruktor.
	 */
	private Game( ) {}
	
	/**
	 * A Singleton tervezési mintához szükséges példányt (amiből egy van) visszadja.
	 * 
	 * @return Az egyedüli példány
	 */
	public static Game getInstance(){
        return instance;
    }
	
	/**
	 * Alapvető getter függvény.
	 * 
	 * @return Pályán lévő munkások
	 */
	public List<Worker> getWorkers() {
		return workers;
	}

	/**
	 * Alapvető setter függvény.
	 * 
	 * @param workers A pályán lévő munkások kívánt értéke.
	 */
	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
		this.workerCounter = workers.size();
	}

	/**
	 * Alapvető getter függvény.
	 * 
	 * @return A boxRecorder változó értéke
	 */
	public BoxRecorder getBoxRecorder() {
		return boxRecorder;
	}

	/**
	 * Alapvető setter függvény.
	 * 
	 * @param boxRecorder A boxRecorder kívánt értéke.
	 */
	public void setBoxRecorder(BoxRecorder boxRecorder) {
		this.boxRecorder = boxRecorder;
	}
	
	/**
	 * Alapvető getter függvény.
	 * 
	 * @return A map változó értéke
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * Alapvető setter függvény.
	 * 
	 * @param map A map kívánt értéke.
	 */
	public void setMap(Map map) {
		this.map = map;
	}

	/**
	 * Egy darab munkás hozzáadása.
	 * 
	 * @param w Hozzáadni kívánt munkás
	 */
	public void addWorker(Worker w) {
		instance.workers.add(w);
	}
	
	/**
	 * Pályán klévő szabad dobozok csökkentése.
	 * 
	 * @param b A kivenni kívánt doboz
	 */
	public void decreaseBoxes(Box b) {
		if (b != null) {
			boxes.remove(b);
		}
		freeBoxCounter--;
		if (freeBoxCounter == 0) { //TODO játék vége
			System.out.println("GAME OVER");
			
			for(Worker w : workers) {
				if(w.getId() == 1)
					score1 = w.getPoints();
				else if(w.getId() == 2)
					score2 = w.getPoints();
			}
			
			controller.endGame(score1, score2);
		}
	}
	
	/**
	 * A kapott munkást törli a játékból.
	 * @param w - a törölni kívánt munkás
	 */
	public void deleteWorker(Worker w) {
		if(w.getId() == 1)
			score1 = w.getPoints();
		else if (w.getId() == 2)
			score2 = w.getPoints();
		
		workers.remove(w);
		workerCounter--;
		if (workerCounter == 0) {
			controller.endGame(score1, score2);
		}
	}
	
	/**
	 * Elindít egy játékot, a pálya betöltésével.
	 * 
	 * @param mapFile - a betöltendő pálya
	 */
	public void startGame(String mapFile) {
		boxRecorder = new BoxRecorder();
		map = new Map();
		workers = new ArrayList<Worker>();
		boxes = new ArrayList<Box>();
		
		map.loadMap(mapFile);
	}
	
}
