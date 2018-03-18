package skeleton.game.logic;

import java.util.HashMap;
import java.util.Map;

import skeleton.out.MethodWriter;

/**
 * Az egyes mező típusok absztrakt ősosztálya.
 */
public abstract class Field {
	
	private String skeletonName;
	
	private Map<Direction, Field> neighbors = new HashMap<Direction, Field>();
	private Movable movable = null;
	
	
	public abstract Movable boxEnters(Box b, Direction d);
	public abstract Movable workerEnters(Worker w, Direction d);
	
	@Override
	public String toString() {
		return skeletonName;
	}
	
	/**
	 * A név beállítására szolgáló függvény.
	 * @param skeletonName A beállítandó név.
	 */
	public void setSkeletonName(String skeletonName) {
		this.skeletonName = skeletonName;
	}
	
	/**
	 * A mező paraméterben átadott irányban lévő szomszédjának getter függvénye.
	 * @param d A kért irány.
	 * @return A kért irányban lévő szomszéd.
	 */
	public Field getNeighbor(Direction d) {
		MethodWriter.printOutMethod("Field.getNeighbor", "d");
		
		MethodWriter.printOutRet("f");
		return neighbors.get(d);
	}
	
	/**
	 * A mező szomszédjának beállítása.
	 * @param d A szomszéd iránya.
	 * @param f	A szomszéd mező referenciája.
	 */
	public void setNeighbor(Direction d, Field f) {
		neighbors.put(d, f);
	}
	
	/**
	 * A mezőn lévő Movable referencia beállítása.
	 * @param m A beállítani kívánt Movable referencia.
	 */
	public void setMovable(Movable m) {
		movable = m;
	}
	
	/**
	 * A mezőn tárolt Movable referencia visszaadására szolgáló függvény.
	 * @return A mezőn lévő Movable referencia.
	 */
	public Movable getMovable() {
		return movable;
	}
	
	/**
	 * A mezőn lévő Movable referencia kitörlésére szolgáló függvény.
	 * @param m A kitörlendő Movable referencia.
	 */
	public void remove(Movable m) {
		MethodWriter.printOutMethod("Field.remove", m.toString());
		
		if (movable == m)
			this.movable = null;
		
		MethodWriter.printOutRet("");
	}
	
	/**
	 * A Recorder osztály frissítésére szolgáló függvény.
	 */
	public void updateRecorder() {
		//TODO
	}
	
	/**
	 * Függvény arra az esetre, ha egy doboz érkezik egy célmezőre.
	 * @param b Az érkező doboz referenciája.
	 */
	public void boxArrived(Box b) {
		MethodWriter.printOutMethod("Field.boxArrived",b.toString());
		MethodWriter.printOutRet("");
	}
	
	/**
	 * Függvény arra az esetre, ha egy munkás érkezik a célmezőre.
	 * @param w Az érkező munkás referenciája.
	 */
	public void workerArrived(Worker w) {
		MethodWriter.printOutMethod("Field.workerArrived",w.toString());
		MethodWriter.printOutRet("");
	}
}
