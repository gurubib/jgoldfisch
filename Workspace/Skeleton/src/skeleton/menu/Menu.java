package skeleton.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import skeleton.game.logic.Field;
import skeleton.game.logic.*;


/**
 * A szkeleton program menüjét megvalósító osztály. Eltárolja a menüpontokat, kezeli őket.
 * 
 * @author jgoldfisch
 *
 */
public class Menu {
	
	/**
	 * A menüpontok, Map interfészű tárolóban van elhelyezve, az adott menü számával,
	 * ezáltal nem keveredhetnek össze a menüpontok sorrendileg,
	 * hiszen nem a sorszámuk határozza meg a tárolás sorrendjét.
	 */
	private Map<Integer, MenuItem> menuItems = new HashMap<Integer, MenuItem>();
	
	/**
	 * Menüpont hozzáadása.
	 * 
	 * @param number Új menüpont száma
	 * @param name Új menüpont neve
	 */
	public void addMenuItem(int number, String name) {
		menuItems.put(number, new MenuItem(name));
	}
	
	/**
	 * Menüpont lekérdezése, a száma alapján.
	 * 
	 * @param n Menüpont száma
	 * @return A megfelelő számú menüpont.
	 */
	public MenuItem getMenuItem(int n) {
		return menuItems.get(n);
	}
	
	
	/**
	 * Adott számú menüpont leírásának beállítása. Ez jelenik meg a képernyőn,
	 * hosszabb szöveg.
	 * 
	 * @param n Menüpont száma
	 * @param desc Menüpont leírása
	 */
	public void setDescForItem(int n, String desc) {
		menuItems.get(n).setDesc(desc);
	}
	
	/**
	 * Menü kiírása a képernyőre. Kiíródik a program címe,
	 * majd utána minden menüpont leírása, ez magábanfoglalja a számát is.
	 */
	public void drawMenu() {
		System.out.println("\nSKELETON\n");
		
		for(Entry<Integer, MenuItem> e : menuItems.entrySet()) {
			System.out.println(e.getValue().getDesc());
		}
	}
	
	/**
	 * Menüpont választása, szám alapján. A választott menüpont ilyenkor lefut.
	 * 
	 * @param n Menüpont száma
	 */
	public void chooseMenuItem(int n) {
		menuItems.get(n).execute();
	}
	
	/**
	 * Az összes menüpont inicializálása. Végigmegy minden menüponton, majd
	 * odaadja őket egy külön erre a feladatra létesült osztálynak,
	 * ami elvégzi az inicializálást.
	 */
	public void initAllMenuItems() {
		for (Entry<Integer, MenuItem> e : menuItems.entrySet()) {
			MenuItemInitializier.initMenuItem(e.getKey(), e.getValue());
		}
	}
	
	/**
	 * Adott menüpontot alaphelyzetbe állítja,
	 * erre szükség van az adott menüpont futtatása után.
	 * A menüponthoz tartozó térképet állítja vissza az eredeti állapotába.
	 * 
	 * @param n Menüpont száma
	 */
	public void resetMenuItem(int n) {
		menuItems.get(n).resetMap();
	}
}

/**
 * Menüpont inicializálását elvégző, nem nyilvános osztály.
 * A programhoz szükséges egyes játékesetek (use-case-ek), ide vannak leírva,
 * a szkeleton tervnek megfelelően. Nem szükséges belőle konkrét példány,
 * hiszen minden eshetőség előre definiálva van, így minden metódusa statikus.
 * 
 * @author jgoldfisch
 *
 */
class MenuItemInitializier {
	
	/**
	 * Menüpont inicializálását irányító függvény, a menüpont száma alapján hívja
	 * az adott ponthoz tartozó esetet inicializáló privát függvényt.
	 * 
	 * @param menuNo Menüpont száma
	 * @param item Menüpont
	 */
	public static void initMenuItem(int menuNo, MenuItem item) {
		
		switch (menuNo) {
			case 1:
				menuItemInit_1(item);
				break;
			case 2:
				menuItemInit_2(item);
				break;
			case 3:
				menuItemInit_3(item);
				break;
			case 4: 
				menuItemInit_4(item);
				break;
			case 5:
				menuItemInit_5(item);
				break;
			case 6:
				menuItemInit_6(item);
				break;
			case 7: 
				menuItemInit_7(item);
				break;
			case 8:
				menuItemInit_8(item);
				break;
			case 9: 
				menuItemInit_9(item);
				break;
			case 10:
				menuItemInit_10(item);
				break;
			default:
				menuItemInit_1(item);
				break;	
		}
	}
	
	/**
	 * Adott menüponthoz tartozó eset inicializálása, létrehozza a mezőket,
	 * beállítja a szomszédokat, létrehoz egy munkást, akivel kezdődik az eset lefutása.
	 * Fontos, hogy itt kerülnek beállításra az objektumok nevei, annak megfelelően,
	 * hogy az adott esethez lehetségesen tartozó use-case szekvencia diagramján követhető legyen.
	 * Az elkészült mezőket és a munkást átadja a menüpontnak, aki elmenti.
	 * 
	 * Menüpont: 1. Worker steps into wall
	 * 
	 * @param item Inicializálandó menüpont
	 */
	private static void menuItemInit_1(MenuItem item) {
		List<Field> fields = new ArrayList<Field>();
		
		SimpleField f1 = new SimpleField();
		WallField wf = new WallField();
		
		f1.setSkeletonName("f1");
		wf.setSkeletonName("wf");
		
		f1.setNeighbor(Direction.RIGHT, wf);
		wf.setNeighbor(Direction.LEFT, f1);
		
		Worker w = new Worker();
		w.setSkeletonName("w1");
		w.setField(f1);
		
		f1.setMovable(w);
		
		fields.add(f1);
		fields.add(wf);
		
		item.init(fields);
		item.setMainWorker(w);
	}
	
	/**
	 * Adott menüponthoz tartozó eset inicializálása, létrehozza a mezőket,
	 * beállítja a szomszédokat, létrehoz egy munkást, akivel kezdődik az eset lefutása.
	 * Fontos, hogy itt kerülnek beállításra az objektumok nevei, annak megfelelően,
	 * hogy az adott esethez lehetségesen tartozó use-case szekvencia diagramján követhető legyen.
	 * Az elkészült mezőket és a munkást átadja a menüpontnak, aki elmenti.
	 * 
	 * Menüpont: 2. Worker steps into hole
	 * 
	 * @param item Inicializálandó menüpont
	 */
	private static void menuItemInit_2(MenuItem item) {
		List<Field> fields = new ArrayList<Field>();
		
		SimpleField f1 = new SimpleField();
		HoleField hf = new HoleField();
		
		f1.setSkeletonName("f1");
		hf.setSkeletonName("hf");
		
		f1.setNeighbor(Direction.RIGHT, hf);
		hf.setNeighbor(Direction.LEFT, f1);
		
		Worker w = new Worker();
		w.setSkeletonName("w1");
		w.setField(f1);
		
		f1.setMovable(w);
		
		fields.add(f1);
		fields.add(hf);
		
		item.init(fields);
		item.setMainWorker(w);
	}
	
	/**
	 * Adott menüponthoz tartozó eset inicializálása, létrehozza a mezőket,
	 * beállítja a szomszédokat, létrehoz egy munkást, akivel kezdődik az eset lefutása.
	 * Fontos, hogy itt kerülnek beállításra az objektumok nevei, annak megfelelően,
	 * hogy az adott esethez lehetségesen tartozó use-case szekvencia diagramján követhető legyen.
	 * Az elkészült mezőket és a munkást átadja a menüpontnak, aki elmenti.
	 * 
	 * Menüpont: 3. Worker steps to another field, and the third field is a hole (without switch)
	 * 
	 * @param item Inicializálandó menüpont
	 */
	private static void menuItemInit_3(MenuItem item) {
		List<Field> fields = new ArrayList<Field>();
		
		SimpleField f1 = new SimpleField();
		SimpleField f2 = new SimpleField();
		HoleField hf = new HoleField();
		
		f1.setSkeletonName("f1");
		f2.setSkeletonName("f2");
		hf.setSkeletonName("hf");
		
		f1.setNeighbor(Direction.RIGHT, f2);
		f2.setNeighbor(Direction.LEFT, f1);
		f2.setNeighbor(Direction.RIGHT, hf);
		hf.setNeighbor(Direction.LEFT, f2);
		
		Worker w = new Worker();
		w.setSkeletonName("w1");
		w.setField(f1);
		
		f1.setMovable(w);
		
		fields.add(f1);
		fields.add(f2);
		fields.add(hf);
		
		item.init(fields);
		item.setMainWorker(w);
	}

	/**
	 * Adott menüponthoz tartozó eset inicializálása, létrehozza a mezőket,
	 * beállítja a szomszédokat, létrehoz egy munkást, akivel kezdődik az eset lefutása.
	 * Fontos, hogy itt kerülnek beállításra az objektumok nevei, annak megfelelően,
	 * hogy az adott esethez lehetségesen tartozó use-case szekvencia diagramján követhető legyen.
	 * Az elkészült mezőket és a munkást átadja a menüpontnak, aki elmenti.
	 * 
	 * Menüpont: 4. Worker steps to another field, and the third field is a hole (with switch)
	 * 
	 * @param item Inicializálandó menüpont
	 */
	private static void menuItemInit_4(MenuItem item) {
		List<Field> fields = new ArrayList<Field>();
		
		SimpleField f1 = new SimpleField();
		SimpleField f2 = new SimpleField();
		HoleField hf = new HoleField();
		SwitchField sf = new SwitchField();
		
		f1.setSkeletonName("f1");
		f2.setSkeletonName("f2");
		hf.setSkeletonName("hf");
		sf.setSkeletonName("sf");
		
		f1.setNeighbor(Direction.RIGHT, f2);
		f2.setNeighbor(Direction.LEFT, f1);
		f2.setNeighbor(Direction.RIGHT, hf);
		hf.setNeighbor(Direction.LEFT, f2);
		
		hf.setSwitchField(sf);
		sf.setHole(hf);
		
		Worker w = new Worker();
		w.setSkeletonName("w1");
		w.setField(f1);
		
		f1.setMovable(w);
		
		fields.add(f1);
		fields.add(f2);
		fields.add(hf);
		fields.add(sf);
		
		item.init(fields);
		item.setMainWorker(w);
	}
	
	/**
	 * Adott menüponthoz tartozó eset inicializálása, létrehozza a mezőket,
	 * beállítja a szomszédokat, létrehoz egy munkást, akivel kezdődik az eset lefutása.
	 * Fontos, hogy itt kerülnek beállításra az objektumok nevei, annak megfelelően,
	 * hogy az adott esethez lehetségesen tartozó use-case szekvencia diagramján követhető legyen.
	 * Az elkészült mezőket és a munkást átadja a menüpontnak, aki elmenti.
	 * 
	 * Menüpont: 5. Worker steps to another Field, and the third field is a switch.
	 * 
	 * @param item Inicializálandó menüpont
	 */
	private static void menuItemInit_5(MenuItem item) {
		List<Field> fields = new ArrayList<Field>();
		
		SimpleField f1 = new SimpleField();
		SimpleField f2 = new SimpleField();
		HoleField hf = new HoleField();
		SwitchField sf = new SwitchField();
		WallField wf = new WallField();
		
		f1.setSkeletonName("f1");
		f2.setSkeletonName("f2");
		hf.setSkeletonName("hf");
		sf.setSkeletonName("sf");
		wf.setSkeletonName("wf");
		
		f1.setNeighbor(Direction.RIGHT, f2);
		f2.setNeighbor(Direction.LEFT, f1);
		f2.setNeighbor(Direction.RIGHT, sf);
		sf.setNeighbor(Direction.LEFT, f2);
		sf.setNeighbor(Direction.RIGHT, wf);
		wf.setNeighbor(Direction.LEFT, sf);
		
		hf.setSwitchField(sf);
		sf.setHole(hf);
		
		Worker w = new Worker();
		w.setSkeletonName("w1");
		w.setField(f1);
		
		f1.setMovable(w);
		
		fields.add(f1);
		fields.add(f2);
		fields.add(hf);
		fields.add(sf);
		
		item.init(fields);
		item.setMainWorker(w);
	}

	/**
	 * Adott menüponthoz tartozó eset inicializálása, létrehozza a mezőket,
	 * beállítja a szomszédokat, létrehoz egy munkást, akivel kezdődik az eset lefutása.
	 * Fontos, hogy itt kerülnek beállításra az objektumok nevei, annak megfelelően,
	 * hogy az adott esethez lehetségesen tartozó use-case szekvencia diagramján követhető legyen.
	 * Az elkészült mezőket és a munkást átadja a menüpontnak, aki elmenti.
	 * 
	 * Menüpont: 6. Worker steps to another Field, and the third field is Wall
	 * 
	 * @param item Inicializálandó menüpont
	 */
	private static void menuItemInit_6(MenuItem item) {
		List<Field> fields = new ArrayList<Field>();
		
		SimpleField f1 = new SimpleField();
		SimpleField f2 = new SimpleField();
		WallField wf = new WallField();
		
		f1.setSkeletonName("f1");
		f2.setSkeletonName("f2");
		wf.setSkeletonName("wf");
		
		f1.setNeighbor(Direction.RIGHT, f2);
		f2.setNeighbor(Direction.LEFT, f1);
		f2.setNeighbor(Direction.RIGHT, wf);
		wf.setNeighbor(Direction.LEFT, f2);
		
		Worker w = new Worker();
		w.setSkeletonName("w1");
		w.setField(f1);
		
		f1.setMovable(w);
		
		fields.add(f1);
		fields.add(f2);
		fields.add(wf);
		
		item.init(fields);
		item.setMainWorker(w);
	}

	/**
	 * Adott menüponthoz tartozó eset inicializálása, létrehozza a mezőket,
	 * beállítja a szomszédokat, létrehoz egy munkást, akivel kezdődik az eset lefutása.
	 * Fontos, hogy itt kerülnek beállításra az objektumok nevei, annak megfelelően,
	 * hogy az adott esethez lehetségesen tartozó use-case szekvencia diagramján követhető legyen.
	 * Az elkészült mezőket és a munkást átadja a menüpontnak, aki elmenti.
	 * 
	 * Menüpont: 7. Worker steps to another Field, and the third field is an endzone field
	 * 
	 * @param item Inicializálandó menüpont
	 */
	private static void menuItemInit_7(MenuItem item) {
		List<Field> fields = new ArrayList<Field>();
		
		SimpleField f1 = new SimpleField();
		SimpleField f2 = new SimpleField();
		EndField ef = new EndField();
		
		f1.setSkeletonName("f1");
		f2.setSkeletonName("f2");
		ef.setSkeletonName("ef");
		
		f1.setNeighbor(Direction.RIGHT, f2);
		f2.setNeighbor(Direction.LEFT, f1);
		f2.setNeighbor(Direction.RIGHT, ef);
		ef.setNeighbor(Direction.LEFT, f2);
		
		Worker w = new Worker();
		w.setSkeletonName("w1");
		w.setField(f1);
		
		f1.setMovable(w);
		
		fields.add(f1);
		fields.add(f2);
		fields.add(ef);
		
		item.init(fields);
		item.setMainWorker(w);
	}
	
	/**
	 * Adott menüponthoz tartozó eset inicializálása, létrehozza a mezőket,
	 * beállítja a szomszédokat, létrehoz egy munkást, akivel kezdődik az eset lefutása.
	 * Fontos, hogy itt kerülnek beállításra az objektumok nevei, annak megfelelően,
	 * hogy az adott esethez lehetségesen tartozó use-case szekvencia diagramján követhető legyen.
	 * Az elkészült mezőket és a munkást átadja a menüpontnak, aki elmenti.
	 * 
	 * Menüpont: 8. Worker pushes a box to another Field, and the third field is a regular field, the fourth is wall
	 * 
	 * @param item Inicializálandó menüpont
	 */
	private static void menuItemInit_8(MenuItem item) {
		List<Field> fields = new ArrayList<Field>();
		
		SimpleField f1 = new SimpleField();
		SimpleField f2 = new SimpleField();
		SimpleField f3 = new SimpleField();
		WallField wf = new WallField();
		
		f1.setSkeletonName("f1");
		f2.setSkeletonName("f2");
		f3.setSkeletonName("f3");
		wf.setSkeletonName("wf");
		
		f1.setNeighbor(Direction.RIGHT, f2);
		f2.setNeighbor(Direction.LEFT, f1);
		f2.setNeighbor(Direction.RIGHT, f3);
		f3.setNeighbor(Direction.LEFT, f2);
		f3.setNeighbor(Direction.RIGHT, wf);
		wf.setNeighbor(Direction.LEFT, f3);
		
		Worker w = new Worker();
		w.setSkeletonName("w1");
		w.setField(f1);
		
		f1.setMovable(w);
		
		fields.add(f1);
		fields.add(f2);
		fields.add(f3);
		
		item.init(fields);
		item.setMainWorker(w);
	}

	/**
	 * Adott menüponthoz tartozó eset inicializálása, létrehozza a mezőket,
	 * beállítja a szomszédokat, létrehoz egy munkást, akivel kezdődik az eset lefutása.
	 * Fontos, hogy itt kerülnek beállításra az objektumok nevei, annak megfelelően,
	 * hogy az adott esethez lehetségesen tartozó use-case szekvencia diagramján követhető legyen.
	 * Az elkészült mezőket és a munkást átadja a menüpontnak, aki elmenti.
	 * 
	 * Menüpont: 9. Worker pushes a box to another Field, and the fourth field is a regular field
	 * 
	 * @param item Inicializálandó menüpont
	 */
	private static void menuItemInit_9(MenuItem item) {
		List<Field> fields = new ArrayList<Field>();
		
		SimpleField f1 = new SimpleField();
		SimpleField f2 = new SimpleField();
		SimpleField f3 = new SimpleField();
		SimpleField f4 = new SimpleField();
		WallField wf = new WallField();
 		
		f1.setSkeletonName("f1");
		f2.setSkeletonName("f2");
		f3.setSkeletonName("f3");
		f4.setSkeletonName("f4");
		wf.setSkeletonName("wf");
		
		f1.setNeighbor(Direction.RIGHT, f2);
		f2.setNeighbor(Direction.LEFT, f1);
		f2.setNeighbor(Direction.RIGHT, f3);
		f3.setNeighbor(Direction.LEFT, f2);
		f3.setNeighbor(Direction.RIGHT, f4);
		f4.setNeighbor(Direction.LEFT, f3);
		f4.setNeighbor(Direction.RIGHT, wf);
		wf.setNeighbor(Direction.LEFT, f4);
		
		Worker w = new Worker();
		w.setSkeletonName("w1");
		w.setField(f1);
		
		f1.setMovable(w);
		fields.add(f1);
		fields.add(f2);
		fields.add(f3);
		fields.add(f4);
		
		item.init(fields);
		item.setMainWorker(w);
	}
	
	/**
	 * Adott menüponthoz tartozó eset inicializálása, létrehozza a mezőket,
	 * beállítja a szomszédokat, létrehoz egy munkást, akivel kezdődik az eset lefutása.
	 * Fontos, hogy itt kerülnek beállításra az objektumok nevei, annak megfelelően,
	 * hogy az adott esethez lehetségesen tartozó use-case szekvencia diagramján követhető legyen.
	 * Az elkészült mezőket és a munkást átadja a menüpontnak, aki elmenti.
	 * 
	 * Menüpont: 10. Worker steps to endzone field
	 * 
	 * @param item Inicializálandó menüpont
	 */
	private static void menuItemInit_10(MenuItem item) {
		List<Field> fields = new ArrayList<Field>();
		
		SimpleField f1 = new SimpleField();
		EndField ef = new EndField();
 		
		f1.setSkeletonName("f1");
		ef.setSkeletonName("ef");
		
		f1.setNeighbor(Direction.RIGHT, ef);
		ef.setNeighbor(Direction.LEFT, f1);
		
		Worker w = new Worker();
		w.setSkeletonName("w1");
		w.setField(f1);
		
		f1.setMovable(w);
		
		fields.add(f1);
		fields.add(ef);
		
		item.init(fields);
		item.setMainWorker(w);
	}
}