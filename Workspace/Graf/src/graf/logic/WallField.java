package graf.logic;

import graf.out.MethodWriter;

/**
*Olyan mező objektum, mely egy falat reprezentál. Nem lehet rajta átmenni, és ismeri a szomszédait.
*A Field absztrakt ősosztály leszármazottja.
* 
* @author jgoldfisch
*/
public class WallField extends Field {
	/**
	* Megakadályozza, hogy egy Box rálépjen a mezőre. Ezt azzal éri el, hogy amikor rálép, akkor a paraméterben kapott objektum referenciáját adja vissza.
	* @param b
	*            A mezőre lépő Box referenciája.
	* @param d
	*            A mezőre lépő Box lépésének iránya.
	**/
	@Override
	public Movable boxEnters(Box b, Direction d) {
		MethodWriter.printOutMethod("WallField.boxEnters", b.toString() + ", " + d.toString());
		//elhelyezi az új mezőre a Boxott és törli az elözőről
		b.place(this);
		getNeighbor(d.getOpposite()).remove(b);
		
		MethodWriter.printOutRet(b.toString());
		//a paraméterben kapott objektum referenciáját adja vissza, ezzel jelezve annak hogy vissza kell lépnie.
		return b;
	}
	/**
	* Megakadályozza, hogy egy Worker rálépjen a mezőre. Ezt azzal éri el, hogy amikor rálép, akkor a paraméterben kapott objektum referenciáját adja vissza.
	* @param w
	*            A mezőre lépő Worker referenciája.
	* @param d
	*            A mezőre lépő Box lépésének iránya.
	**/
	@Override
	public Movable workerEnters(Worker w, Direction d) {
		MethodWriter.printOutMethod("WallField.workerEnters", w.toString() + ", " + d.toString());
		//elhelyezi az új mezőre a Workert és törli az elözőről
		w.place(this);
		getNeighbor(d.getOpposite()).remove(w);
		//a paraméterben kapott objektum referenciáját adja vissza, ezzel jelezve annak hogy vissza kell lépnie.
		MethodWriter.printOutRet(w.toString());
		return w;
	}

}
