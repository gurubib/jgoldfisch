package graf.logic;

import graf.out.MethodWriter;
/**
 * 
 * Egy kapcsolót és hozzá tartozó állapotot reprezentáló mező objektum. 
 * Ismeri a hozzá tartozó HoleField-t.
 *
 */
public class SwitchField extends Field {

	private boolean active = false;
	private HoleField hole;
/**
 * Visszaadja a lyukmezőt, amit kapcsol.
 * @return a kapcsolóhoz tartozó lyuk
 */
	public HoleField getHole() {
		return hole;
	}
/**
 * Beállítja a kapcsolóhoz tartozó lyukat.
 * @param hole az új érték
 */
	public void setHole(HoleField hole) {
		this.hole = hole;
	}
	
	
	public boolean isActive() {
		return active;
	}
	
/**
 * Fogadja a mezőre érkező Box-t, beállítja annak a mező referenciáját, 
 * aztán pedig kitörli a régi mezőjén lévő referenciáját (ahonnan jött). 
 * Majd pedig végül megnézi, hogy van e rajta Movable, és visszaadja annak a referenciáját
 * (ha nincs akkor null).
 * @param b a mezőre érkező doboz
 * @param d	az irány, ahonnan érkezik a doboz
 */
	@Override
	public Movable boxEnters(Box b, Direction d) {
		MethodWriter.printOutMethod("SwitchField.boxEnters", b.toString() + ", " + d.toString());

		Movable movable = getMovable();

		this.setMovable(b);
		b.place(this);
		getNeighbor(d.getOpposite()).remove(b);

		if (movable == null)
			MethodWriter.printOutRet("null");
		else
			MethodWriter.printOutRet(movable.toString());

		return movable;
	}
/**
 * Fogadja a mezőre érkező Worker-t, beállítja annak a mező referenciáját, 
 * aztán pedig kitörli a régi mezőjén lévő referenciáját (ahonnan jött). 
 * Majd pedig végül megnézi, hogy van e rajta Movable, és visszaadja annak a referenciáját 
 * (ha nincs akkor null).
 * 
 * @param w a mezőre érkező munkás
 * @param d az irány, ahonnan érkezik a munkás
 */
	@Override
	public Movable workerEnters(Worker w, Direction d) {
		MethodWriter.printOutMethod("SwitchField.workerEnters", w.toString() + ", " + d.toString());

		Movable movable = getMovable();

		this.setMovable(w);
		w.place(this);
		getNeighbor(d.getOpposite()).remove(w);

		if (movable == null)
			MethodWriter.printOutRet("null");
		else
			MethodWriter.printOutRet(movable.toString());

		return movable;
	}

/**
 * Doboz kapcsolóra való érkezését kezeli. Szól a hozzá tartozó lyuknak, 
 * továbbá meghívja a reagáláshoz tartozó további metódusokat.
 * 
 * @param b az érkező doboz
 */
	@Override
	public void boxArrived(Box b) {
		MethodWriter.printOutMethod("SwitchField.boxArrived", b.toString());

		this.activate();

		hole.changeToActive();

		MethodWriter.printOutRet("");
	}
/**
 * Aktiválja a kapcsolót, a változójának "true" értékre állításával.
 */
	public void activate() {
		MethodWriter.printOutMethod("SwitchField.activate", "");

		active = true;

		MethodWriter.printOutRet("");
	}
/**
 * Deaktiválja a kapcsolót, a változójának "false" értékre állításával.
 */
	public void deactivate() {
		MethodWriter.printOutMethod("SwitchField.deactivate", "");

		active = false;

		MethodWriter.printOutRet("");
	}
/**
 * Az ős függvényének bővítése, azzal, hogy false értékre állítja az active változót.
 */
	public void remove(Movable m) {
		MethodWriter.printOutMethod("SwitchField.remove", m.toString());

		this.deactivate();

		MethodWriter.printOutRet("");
	}
/**
 * Akkor hívódik meg, ha a kapcsolóhoz tartozó mezőre valaki rálép. 
 * Eldönti, hogy a kapcsoló éppen aktív-e és ennek függvényében meghívja a szükséges metódusokat.
 *
 * @param m a mezőre lépő objektum
 */
	public void holeInteracted(Movable m) {
		MethodWriter.printOutMethod("SwitchField.holeInteracted", m.toString());

		if (active)
			hole.execute(m);

		MethodWriter.printOutRet("");
	}

	/**
	 * Az objektum állapotát és nevét kiíró toString() 
	 * függvény felüldefiniálása.
	 */
	@Override
	public String toString() {
		String act = "0";
		if (active)
			act = "1";
		
		return super.toString() + " " + act;
	}
}
