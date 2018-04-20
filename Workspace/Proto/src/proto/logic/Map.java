package skeleton.game.logic;

import java.util.ArrayList;
import java.util.List;
/**
 * A pálya betöltéséért és tárolásáért felelős osztály.
 */
public class Map {
	
	private List<Field> fields = new ArrayList<Field>();
	/**
	 * A pálya fájlból való betöltésére szolgáló függvény.
	 * @param n
	 */
	public void loadMap(int n) {
		//TODO
	}
	/**
	 * Függvény arra az esetre, ha manuálisan akarjuk felépíteni a pályát.
	 * @param fields A felépítendő mező lista.
	 */
	public void manualLoad(List<Field> fields) {
		this.fields = fields;
	}
	/**
	 * Függvény egy mező cseréjére.
	 * @param n A listában az n-edik mező.
	 * @param f Az új mező.
	 */
	public void confField(int n, Field f) {
		fields.set(n, f);
	}
	/**
	 * Getter függvény
	 * @param n A listában az n-edik mező.
	 * @return A kért mező.
	 */
	public Field getField(int n) {
		return fields.get(n);
	}
	
	/**
	 * A térkép alaphelyzetbe állítása, tehát minden mezőről töröljük az entitásokat, ha volt rajtuk.
	 */
	public void reset() {
		for(Field f : fields) {
			Movable m = f.getMovable();
			
			if (m != null) {
				f.setMovable(null);
			}
		}
	}
}
