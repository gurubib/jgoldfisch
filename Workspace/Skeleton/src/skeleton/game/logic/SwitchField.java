package skeleton.game.logic;

import skeleton.out.MethodWriter;
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

		if (d == Direction.RIGHT) {

			MethodWriter.printOutQuestion("Is there a box or a worker on the field? B/W/X");

			String answer = MethodWriter.readFromStdin();

			switch (answer) {
			case "b":
				Box b2 = new Box();
				b2.setSkeletonName(MethodWriter.nameGenerator("b"));
				b2.setField(this);
				this.setMovable(b2);
				break;
			case "w":
				Worker w = new Worker();
				w.setSkeletonName(MethodWriter.nameGenerator("w"));
				w.setField(this);
				this.setMovable(w);
				break;
			default:
				this.setMovable(null);
			}
		}

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

		if (d == Direction.RIGHT) {

			MethodWriter.printOutQuestion("Is there a box or a worker on the field? B/W/X");

			String answer = MethodWriter.readFromStdin();

			switch (answer) {
			case "b":
				Box b = new Box();
				b.setSkeletonName(MethodWriter.nameGenerator("b"));
				b.setField(this);
				this.setMovable(b);
				break;
			case "w":
				Worker w2 = new Worker();
				w2.setSkeletonName(MethodWriter.nameGenerator("w"));
				w2.setField(this);
				this.setMovable(w2);
				break;
			default:
				this.setMovable(null);
			}
		}

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

		MethodWriter.printOutQuestion("Is the switch on or off? 1/0");
		String answer = MethodWriter.readFromStdin();

		active = answer.equals("1");

		if (active)
			hole.execute(m);

		MethodWriter.printOutRet("");
	}

}
