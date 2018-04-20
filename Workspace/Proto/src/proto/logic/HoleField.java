package proto.logic;

import proto.out.MethodWriter;

/**
 * Ez az osztály reprezentálja az olyan mezőt, melyen lyuk van. switchfield 
 * változója pedig az esetleges hozzátartozó kapcsoló(mező)t tartalmazza.
 *
 */

public class HoleField extends Field {
	private SwitchField switchField = null;

	
	/**
	 * Beállítja az esetlegesen hozzátartozó kapcsolómezőt.
	 * @param switchField
	 */
	public void setSwitchField(SwitchField switchField) {
		this.switchField = switchField;
	}

	/**
	 * Fogadja a mezőre érkező Box-t, beállítja annak a mező referenciáját, 
	 * aztán pedig kitörli a régi mezőjén lévő referenciáját (ahonnan jött). 
	 * Majd pedig végül megnézi, hogy van e rajta Movable, 
	 * és visszaadja annak a referenciáját (ha nincs akkor null). 
	 *	
	 * @param b az érkező doboz
	 * @param d	az irány, ahonnan érkezett
 	 *
	 */

	@Override
	public Movable boxEnters(Box b, Direction d) {
		MethodWriter.printOutMethod("HoleField.boxEnters", b.toString() + ", " + d.toString());
		
		//Eredeti mezőn lévő entitás
		Movable movable = getMovable();
		
		//Magunk elhelyezése
		this.setMovable(b);
		b.place(this);
		
		//Előző mezőről való eltávolítás
		this.getNeighbor(d.getOpposite()).remove(b);
		
		if (movable == null)
			MethodWriter.printOutRet("null");
		else
			MethodWriter.printOutRet(movable.toString());
		return movable;
	}
	
	/**Fogadja a mezőre érkező Worker-t, beállítja annak a mező referenciáját, 
	 * aztán pedig kitörli a régi mezőjén lévő referenciáját (ahonnan jött). 
	 * Majd pedig végül megnézi, hogy van e rajta Movable, és visszaadja annak a referenciáját 
	 * (ha nincs akkor null).
	 *
	 * @param w a mezőre érkező Worker
	 * @param d	az irány, ahonnan a munkás jött
	 */

	@Override
	public Movable workerEnters(Worker w, Direction d) {
		MethodWriter.printOutMethod("HoleField.workerEnters", w.toString() + ", " + d.toString());
		
		//Eredeti mezőn lévő entitás
		Movable movable = getMovable();
		
		//Magunk elhelyezése
		this.setMovable(w);
		w.place(this);
		
		//Előző mezőről való eltávolítás
		this.getNeighbor(d.getOpposite()).remove(w);
		
		if (movable == null)
			MethodWriter.printOutRet("null");
		else
			MethodWriter.printOutRet(movable.toString());
		return movable;
	}
	
	/**
	 * Doboz lyukra való érkezését kezeli. Ellenőrzi, 
	 * hogy tartozik-e hozzá kapcsoló, továbbá meghívja a reagáláshoz tartozó további metódusokat.
	 *
	 * @param b az érkező doboz
	 */
	@Override
	public void boxArrived(Box b) {
		MethodWriter.printOutMethod("HoleField.boxArrived", b.toString());
	
		if (switchField == null) {
			execute(b);
		}
		else {
			switchField.holeInteracted(b);
		}
		MethodWriter.printOutRet("");
			
	}
	
	/**
	 * Munkás lyukra való érkezését kezeli. Ellenőrzi, 
	 * hogy tartozik-e hozzá kapcsoló, továbbá meghívja a reagáláshoz tartozó további metódusokat.
	 *
	 * @param w az érkező munkás
	 */
	
	@Override
	public void workerArrived(Worker w) {
		MethodWriter.printOutMethod("HoleField.workerArrived", w.toString());
		
		if (switchField == null) {
			execute(w);
		}
		else {
			switchField.holeInteracted(w);
		}
		MethodWriter.printOutRet("");
			
	}
	
	/**
	 * Függvény arra, ha meg akarjuk semmisíteni a mezőn álló Movable objektumot. 
	 * Meghívja a kellő metódusokat az objektum megsemmisítéséhez.
	 *
	 * @param m a mezőn álló Movable objektum
	 */
	public void execute(Movable m) {
		MethodWriter.printOutMethod("HoleField.execute", m.toString());
		m.die();
		MethodWriter.printOutRet("");
	}
	 
	/**
	 * A kapcsoló megváltoztatása esetén hívjuk meg, 
	 * hogy ha áll valami abban a pillanatban a lyukon, akkor az megsemmisül.
	 */
	public void changeToActive() {
		MethodWriter.printOutMethod("HoleField.changeToActive","");
		if (getMovable() != null)
			execute(getMovable());
		this.setSlime(null);
		MethodWriter.printOutRet("");
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + switchField.isActive();
	}

}
