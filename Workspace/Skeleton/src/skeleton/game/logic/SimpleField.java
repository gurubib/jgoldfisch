package skeleton.game.logic;

import skeleton.out.MethodWriter;

/**
 * Az egyszerű mezőt reprezentáló osztÃ¡ly. A Field absztrakt Å‘sosztÃ¡ly
 * leszÃ¡rmazottja.
 */
public class SimpleField extends Field {

	/**
	 * A mezÅ‘re egy Box lÃ©p. Ez az osztÃ¡ly fel van kÃ©szÃ­tve minden
	 * eshetÅ‘sÃ©gre: Ã¡llhat mÃ¡r rajta Worker, Box, vagy lehet Ã¼res is.
	 * 
	 * @param b
	 *            A mezÅ‘re lÃ©pÅ‘ Box referenciÃ¡ja.
	 * @param d
	 *            A mezÅ‘re lÃ©pÅ‘ Box lÃ©pÃ©sÃ©nek irÃ¡nya.
	 */
	@Override
	public Movable boxEnters(Box b, Direction d) {
		MethodWriter.printOutMethod("SimpleField.boxEnters", b.toString() + ", " + d.toString());

		if (d == Direction.RIGHT) {

			MethodWriter.printOutQuestion("Is there a box or a worker on the field? B/W/X");

			String answer = MethodWriter.readFromStdin();

			// A felhasznÃ¡lÃ³ Ã¡ltal megadott Movable referencia (vagy semmi) beÃ¡llÃ­tÃ¡sa
			// a mezÅ‘re.
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
		// Az Ã©rkezÅ‘ elhelyezÃ©se
		this.setMovable(b);
		b.place(this);
		// Az Ã©rkezÅ‘ Box referenciÃ¡jÃ¡nak kitÃ¶rlÃ©se arrÃ³l a mezÅ‘rÅ‘l, ahonnan
		// jÃ¶tt.
		this.getNeighbor(d.getOpposite()).remove(b);

		if (movable == null)
			MethodWriter.printOutRet("null");
		else
			MethodWriter.printOutRet(movable.toString());
		return movable;
	}

	/**
	 * A mezÅ‘re egy Worker lÃ©p. Ez az osztÃ¡ly fel van kÃ©szÃ­tve minden
	 * eshetÅ‘sÃ©gre: Ã¡llhat mÃ¡r rajta Worker, Box, vagy lehet Ã¼res is.
	 * 
	 * @param w
	 *            A mezÅ‘re lÃ©pÅ‘ Worker referenciÃ¡ja.
	 * @param d
	 *            A mezÅ‘re lÃ©pÅ‘ Worker lÃ©pÃ©sÃ©nek irÃ¡nya.
	 */
	@Override
	public Movable workerEnters(Worker w, Direction d) {
		MethodWriter.printOutMethod("SimpleField.workerEnters", w.toString() + ", " + d.toString());

		if (d == Direction.RIGHT) {
			MethodWriter.printOutQuestion("Is there a box or a worker on the field? B/W/X");

			String answer = MethodWriter.readFromStdin();

			// A felhasznÃ¡lÃ³ Ã¡ltal megadott Movable referencia (vagy semmi) beÃ¡llÃ­tÃ¡sa
			// a mezÅ‘re.
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
		// Az Ã©rkezÅ‘ Worker elhelyezÃ©se.
		this.setMovable(w);
		w.place(this);
		// Az Ã©rkezÅ‘ Worker referenciÃ¡jÃ¡nak kitÃ¶rlÃ©se arrÃ³l a mezÅ‘rÅ‘l, ahonnan
		// jÃ¶tt.
		getNeighbor(d.getOpposite()).remove(w);

		if (movable == null)
			MethodWriter.printOutRet("null");
		else
			MethodWriter.printOutRet(movable.toString());
		return movable;
	}
}