package skeleton.game.logic;

import skeleton.out.MethodWriter;

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

		if (d == Direction.RIGHT) {

			MethodWriter.printOutQuestion("Is there a box or a worker on the field? B/W/X");

			String answer = MethodWriter.readFromStdin();

			// A felhasználó által megadott Movable referencia (vagy semmi) beállítasa
			// a mezőre.
			switch (answer) {
			case "b":
				Box b2 = new Box();
				b2.setField(this);
				this.setMovable(b2);
				break;
			case "w":
				Worker w = new Worker();
				w.setField(this);
				this.setMovable(w);
				break;
			default:
				this.setMovable(null);
			}
		}

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

		if (d == Direction.RIGHT) {
			MethodWriter.printOutQuestion("Is there a box or a worker on the field? B/W/X");

			String answer = MethodWriter.readFromStdin();

			// A felhasználó által megadott Movable referencia (vagy semmi) beállítása
			// a mezőre.
			switch (answer) {
			case "b":
				Box b = new Box();
				b.setField(this);
				this.setMovable(b);
				break;
			case "w":
				Worker w2 = new Worker();
				w2.setField(this);
				this.setMovable(w2);
				break;
			default:
				this.setMovable(null);
			}
		}

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