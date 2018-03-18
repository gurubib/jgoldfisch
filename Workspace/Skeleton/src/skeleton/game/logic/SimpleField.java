package skeleton.game.logic;

import skeleton.out.MethodWriter;

/**
 * Az egyszeru mezot reprezentalo osztaly. A Field absztrakt ososztaly
 * leszarmazottja.
 */
public class SimpleField extends Field {

	/**
	 * A mezore egy Box lep. Ez az osztaly fel van keszítve minden
	 * eshetosegre: allhat mar rajta Worker, Box, vagy lehet ures is.
	 * 
	 * @param b
	 *            A mezore lepo Box referenciaja.
	 * @param d
	 *            A mezore lepo Box lepesenek iranya.
	 */
	@Override
	public Movable boxEnters(Box b, Direction d) {
		MethodWriter.printOutMethod("SimpleField.boxEnters", b.toString() + ", " + d.toString());

		if (d == Direction.RIGHT) {

			MethodWriter.printOutQuestion("Is there a box or a worker on the field? B/W/X");

			String answer = MethodWriter.readFromStdin();

			// A felhasznalo altal megadott Movable referencia (vagy semmi) beallítasa
			// a mezore.
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
		// Az erkezo elhelyezese
		this.setMovable(b);
		b.place(this);
		// Az erkezo Box referenciajanak kitorlese arrol a mezorol, ahonnan
		// jott.
		this.getNeighbor(d.getOpposite()).remove(b);

		if (movable == null)
			MethodWriter.printOutRet("null");
		else
			MethodWriter.printOutRet(movable.toString());
		return movable;
	}

	/**
	 * A mezore egy Worker lep. Ez az osztaly fel van keszítve minden
	 * eshetosegre: allhat mar rajta Worker, Box, vagy lehet ures is.
	 * 
	 * @param w
	 *            A mezore lepo Worker referenciaja.
	 * @param d
	 *            A mezore lepo Worker lepesenek iranya.
	 */
	@Override
	public Movable workerEnters(Worker w, Direction d) {
		MethodWriter.printOutMethod("SimpleField.workerEnters", w.toString() + ", " + d.toString());

		if (d == Direction.RIGHT) {
			MethodWriter.printOutQuestion("Is there a box or a worker on the field? B/W/X");

			String answer = MethodWriter.readFromStdin();

			// A felhasznalo altal megadott Movable referencia (vagy semmi) beallítasa
			// a mezore.
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
		// Az erkezo Worker elhelyezese.
		this.setMovable(w);
		w.place(this);
		// Az erkezo Worker referenciajanak kitorlese arrol a mezorol, ahonnan
		// jott.
		getNeighbor(d.getOpposite()).remove(w);

		if (movable == null)
			MethodWriter.printOutRet("null");
		else
			MethodWriter.printOutRet(movable.toString());
		return movable;
	}
}