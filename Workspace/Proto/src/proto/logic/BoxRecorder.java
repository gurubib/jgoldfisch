package proto.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * FONTOS: A szkeleton programban nem modellezzük és vizsgáljuk a játék egészét,
 * mint például a játék megnyerésének figyelését, továbbá az ehhez tartozó feladatokat.
 * Ebből kifolyólag ez az osztály NINCS használva, megvalósítása minimális.
 * 
 * Nyilvántartás vezetése a mozdíthatatlan mezőkről, amiken beragadt doboz van. Segítségével
 * lehetséges a beragadt (pl. sarokban lévő) dobozok detektálása.
 * 
 * @author jgoldfisch
 *
 */
public class BoxRecorder {
	
	/**
	 * Referenciák azokra a mezőkre, amikre nem lehet rálépni.
	 */
	private List<Field> fixFields = new ArrayList<Field>();
	
	/**
	 * Alapvető getter függvény.
	 * 
	 * @return Referencia a nem szabad mezőkre
	 */
	public List<Field> getFixFields() {
		return fixFields;
	}

	/**
	 * Alapvető setter függvény.
	 * 
	 * @param fixFields A fix mezők kívánt értékei
	 */
	public void setFixFields(List<Field> fixFields) {
		this.fixFields = fixFields;
	}

	/**
	 * A Map-től megkapja a falakat, erre azért van szükség mert kezdetben ezeket tárolja el a fixFields-be.
	 * 
	 * @param walls A falak referenciáji
	 */
	public void init(List<Field> walls) {
		fixFields = walls;
	}
	
	/**
	 * Ennek a függvénynek a segítségével jelezhető, ha
	 * egy célmezőre (EndField) beérkezett egy láda (Box). A paraméter átadásával
	 * jelezzük, hogy melyik mezőről van szó, majd ez a mező bekerül a nyilvántartásba,
	 * hiszen onnan a doboz nem mozdítható el.
	 *
	 * @param f Az adott célmező
	 */
	public void endFieldOccupied(Field f) {
	}
	
	/**
	 * Ezt a függvényt mindig egy doboz (Box) mozgatása után hívjuk meg,
	 * miután elhelyeződött a mezőn (Field.arrived()). A paraméterben átadott mező az a mező,
	 * ahová a doboz újonnan került. A nyilvántartás alapján pedig el tudja dönteni,
	 * hogy a dobozunk beszorult-e (a szomszédai alapján). Amennyiben igen, akkor azt is felveszi a nyilvántartásba.
	 * 
	 * @param f Az adott mező
	 */
	public void update(Field f) {
	}
	
	/**
	 * Ezzel a függvénnyel lehetséges annak az
	 * ellenőrzése, hogy a paraméterben kapott mező benne-e van már a nyilvántartásban. Ha
	 * nincs akkor csökkenti a szabad dobozok számát a Game-ben.
	 * 
	 * @param f Az adott mező
	 */
	public void checkRecordWith(Field f) {
	}
}
