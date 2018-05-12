package graf.logic;

import graf.out.MethodWriter;

/**
 * Az egyszerű mezőt reprezentaló osztály. A Field absztrakt ősosztály
 * leszármazottja.
 */
public class SimpleField extends Field {

	/**
	 * A mezőre egy Box lép. Ez az ősztaly fel van készítve minden
	 * eshetőségre: állhat már rajta Worker, Box, vagy lehet üres is.
	 * 
	 * @param b
	 *            A mezőre lépő Box referenciája.
	 * @param d
	 *            A mezőre lépő Box lépésének iránya.
	 */
	@Override
	public Movable boxEnters(Box b, Direction d) {
		MethodWriter.printOutMethod("SimpleField.boxEnters", b.toString() + ", " + d.toString());

		Movable movable = getMovable();
		// Az érkező elhelyezése
		this.setMovable(b);
		b.place(this);
		// Az érkező Box referenciájának kitörlese arról a mezőrol, ahonnan
		// jött.
		this.getNeighbor(d.getOpposite()).remove(b);

		if (movable == null)
			MethodWriter.printOutRet("null");
		else
			MethodWriter.printOutRet(movable.toString());
		return movable;
	}

	/**
	 * A mezőre egy Worker lép. Ez az osztály fel van készítve minden
	 * eshetőségre: állhat már rajta Worker, Box, vagy lehet üres is.
	 * 
	 * @param w
	 *            A mezőre lépő Worker referenciája.
	 * @param d
	 *            A mezőre lépő Worker lépésének iránya.
	 */
	@Override
	public Movable workerEnters(Worker w, Direction d) {
		MethodWriter.printOutMethod("SimpleField.workerEnters", w.toString() + ", " + d.toString());

	
		Movable movable = getMovable();
		// Az érkező Worker elhelyezése.
		this.setMovable(w);
		w.place(this);
		// Az érkező Worker referenciájának kitörlése arról a mezőről, ahonnan
		// jött.
		getNeighbor(d.getOpposite()).remove(w);

		if (movable == null)
			MethodWriter.printOutRet("null");
		else
			MethodWriter.printOutRet(movable.toString());
		return movable;
	}
}