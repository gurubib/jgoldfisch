package skeleton.game.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * FONTOS: A szkeleton programban nem modellezzük és vizsgáljuk a játék egészét,
 * mint például a játék megnyerésének figyelését, továbbá az ehhez tartozó feladatokat.
 * Ebből kifolyólag ez az osztály NINCS használva, megvalósítása minimális.
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
	private List<Worker> workers = new ArrayList<Worker>();
	
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
//	private int freeBoxCounter;
	
	/**
	 * A még játékban lévő munkások számát tárolja.
	 */
//	private int workerCounter;
	
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
		instance.boxRecorder = new BoxRecorder();
		
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
		//TODO
	}
}
