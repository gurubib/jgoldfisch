package skeleton.menu;

import java.util.List;

import skeleton.game.logic.Direction;
import skeleton.game.logic.Field;
import skeleton.game.logic.Worker;

/**
 * Egy menüponot megvalósító osztály, eltárolja a menüpont rövid nevét, leírását,
 * a hozzá tartozó térképet, az eset lefutását indító munkást. Elvégzi az adott esetek
 * lefutásával kapcsolatos feladatokat.
 * 
 * @author jgoldfisch
 *
 */
public class MenuItem {
	
	/**
	 * Menüpont rövid neve, főleg tesztelésnél lehet hasznos.
	 */
	private String name;
	
	/**
	 * Menüpont leírása, hosszab szöveg, a felhasználó ezt látja a képernyőn.
	 */
	private String desc;
	
	/**
	 * A menüponthoz tartozó térkép (játéklogika értelemben). Egy adott,
	 * előre megadott esetnek megfelelően van felépítve.
	 */
	private skeleton.game.logic.Map map = new skeleton.game.logic.Map();
	
	/**
	 * Az adott esethez tartozó, azt elkezdő munkás.
	 */
	private Worker mainWorker;
	
	//private Game game = Game.getInstance();
	
	/**
	 * Konstruktor, egy nevet vár, alapesetben ez lesz a leírása is a menüpontnak.
	 * 
	 * @param name Létrehozandó menüpont kívánt neve
	 */
	public MenuItem(String name) {
		this.name = name;
		this.desc = name.toLowerCase();
	}
	
	/**
	 * Alapvető getter függvény.
	 * 
	 * @return Referencia a mainWorker változóra
	 */
	public Worker getMainWorker() {
		return mainWorker;
	}


	/**
	 * Alapvető setter függvény.
	 * 
	 * @param mainWorker mainWorker változót erre állítja be
	 */
	public void setMainWorker(Worker mainWorker) {
		this.mainWorker = mainWorker;
	}


	/**
	 * Alapvető getter függvény.
	 * 
	 * @return Menüpont neve
	 */
	public String getName() {
		return name;
	}

	/**
	 * Alapvető setter függvény.
	 * 
	 * @param setName setName változót erre állítja be
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Alapvető getter függvény.
	 * 
	 * @return Menüpont leírása
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Alapvető setter függvény.
	 * 
	 * @param desc desc változót erre állítja be
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	/**
	 * Alapvető getter függvény.
	 * 
	 * @return Menüponthoz tartozó térkép
	 */
	public skeleton.game.logic.Map getMap() {
		return map;
	}

	/**
	 * Menüpont inicializálását végző függvény, inicializálja a térképet.
	 * 
	 * @param fields Megfelelően inicializált mezők
	 */
	public void init(List<Field> fields) {
		map.manualLoad(fields);
	}
	
	/**
	 * Az adott menüponthoz tartozó eset mindtegy lefuttatása, vagyis elindítja a megfeleő hívást
	 * az irányító munkásra. Ezt tekinthetjük, úgy mintha a felhasználó csinálná.
	 */
	public void execute() {
		mainWorker.control(Direction.RIGHT);
	}
	
	/**
	 * A menüponthoz tartozó eset lefutása után több dolgot is vissza kell állítani alaphelyzetbe,
	 * például a térképet.
	 */
	public void resetMap() {
		map.reset(); 
		mainWorker.setField(map.getField(0));
		map.getField(0).setMovable(mainWorker);
	}
}
