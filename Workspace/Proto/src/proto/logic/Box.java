package proto.logic;

import proto.out.MethodWriter;
/**
 * 
 * Ennek az osztálynak az egyes példányai reprezentálják a dobozokat a játékban. 
 * Őket lehet a célmezőre eljuttatni.
 *
 */
public class Box extends Movable {

/**
 * Ez a függvény felel az entitás elpusztításáért, illetve csökkenti a szabad dobozok számát.
 */
	@Override
	public void die() {
		MethodWriter.printOutMethod("Box.die", "");
		
		//ReferenciĂˇk nullra ĂˇllĂ­tĂˇsa
		this.getField().remove(this);
		this.setField(null);
		Game.getInstance().decreaseBoxes(this);
		MethodWriter.printOutRet("");
	}
	/**
	 * Ennek a függvénynek a segítségével a tolás irányába visszaléptethetjük a dobozt. 
	 * (Erre szükség van pl. amikor a falba bele akarnánk tolni a dobozt.)
	 *
	 * @param d a visszaléptetés iránya
	 */
	@Override
	public void pushBack(Direction d) {
		MethodWriter.printOutMethod("Box.pushBack", d.toString());
		
		Field neighbor = getField().getNeighbor(d);
		
		Movable neighborMovable = neighbor.boxEnters(this, d);
		
		if (neighborMovable != null)
			neighborMovable.pushBack(d);
		
		MethodWriter.printOutRet("");
	}
	/**
	 * Függvény arra az esetre, amennyiben a ládánkat (Box) egy másik doboz tolja meg, 
	 * intézi, hogy a láda a megfelelő helyre kerüljön.
	 * Továbbá ellenőrzi, hogy a kapott erővel van-e lehetőség eltolni a doboz-t, 
	 * figyelembe véve a mezőn lévő anyagot.
	 *
	 *@param b a doboz, ami tol
	 *@param d az irány ahonnan tol
	 *@param f az erő amivel tolni kívánják a dobozt
	 */
	@Override
	public void pushByBox(Box b, Direction d,int f) {
		MethodWriter.printOutMethod("Box.pushByBox", b.toString() + ", " + d.toString());
		
		Field neighbor = getField().getNeighbor(d);
		
		int f2 = getField().interact(f);
		System.out.println(f2);
		Movable neighborMovable = neighbor.boxEnters(this, d);
		//Ha Ă¶nmagunkat kapjuk vissza, akkor visszalĂ©pĂĽnk ellenkezĹ‘ irĂˇnyba
		if (neighborMovable == this || f2 <0) {
			Field backwardNeighbor = neighbor.getNeighbor(d.getOpposite());
			Movable backwardMovable = backwardNeighbor.boxEnters(this, d.getOpposite());
			
			backwardMovable.pushBack(d.getOpposite());
		} else if (neighborMovable != null) {
			neighborMovable.pushByBox(this, d, f2);
		}
		
		this.finalizeStep();

		MethodWriter.printOutRet("");
	}
	/**
	 * A láda (Box) egy játékos által való megtolásákor hívjuk meg, 
	 * intézi hogy a láda a megfelelő helyre kerüljön. 
	 * Továbbá ellenőrzi, hogy a munkás által átadott erővel van-e lehetőség
	 *  a dobozt eltolni a mezőn lévő anyag függvényében.
	 * 
	 *@param w a munkás, aki tolja a ládát
	 *@param d az irány, ahonnan tolja
	 *@param f az erő amivel tolni kívánják a dobozt
	 */
	@Override
	public void pushByWorker(Worker w, Direction d, int f) {
		MethodWriter.printOutMethod("Box.pushByWorker", w.toString() + ", " + d.toString());
		
		Field neighbor = getField().getNeighbor(d);
		
		int f2 = getField().interact(f);
		Movable neighborMovable = neighbor.boxEnters(this, d);
		//Ha Ă¶nmagunkat kapjuk vissza, akkor visszalĂ©pĂĽnk ellenkezĹ‘ irĂˇnyba
		if (neighborMovable == this || f2 <0) {
			Field backwardNeighbor = neighbor.getNeighbor(d.getOpposite());
			Movable backwardMovable = backwardNeighbor.boxEnters(this, d.getOpposite());
			
			backwardMovable.pushBack(d.getOpposite());
		} else if (neighborMovable != null) {
			neighborMovable.pushByBox(this, d, f2);
		}
		
		this.finalizeStep();

		MethodWriter.printOutRet("");
	}
	/**
	 * Amennyiben a célmezőre tolódott a láda, ennek a függvénynek a segítségével végezhetjük el
	 *  a megfelelő játékos pontjainak növelését, így a hívást mindig továbbadja 
	 *  a megfelelő irányban álló Movable-nek (meghívja rajta önmagát).
	 *
	 *@param d az irány, ahonnan tolták a ládát
	 */
	@Override
	public void scorePoint(Direction d) {
		MethodWriter.printOutMethod("Box.finalizeStep", "");
		
		Field neighbor = this.getField().getNeighbor(d);
		
		Movable neighborMovable = neighbor.getMovable();
		
		neighborMovable.scorePoint(d);
		
		MethodWriter.printOutRet("");
	}
	/**
	 * Ez az a függvény, ami az egyes lépések véglegesítésért felel, 
	 * a léptetés érvényessége vizsgálatát követően.
	 */
	@Override
	public void finalizeStep() {
		MethodWriter.printOutMethod("Box.finalizeStep", "");
		
		getField().boxArrived(this);
		Game.getInstance().getBoxRecorder().update(this.getField());
		MethodWriter.printOutRet("");
	}

}
