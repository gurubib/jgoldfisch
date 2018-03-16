package skeleton.game.logic;

import skeleton.out.MethodWriter;

public class SwitchField extends Field {

	private boolean active = false;
	private HoleField hole;

	public HoleField getHole() {
		return hole;
	}

	public void setHole(HoleField hole) {
		this.hole = hole;
	}

	@Override
	public Movable boxEnters(Box b, Direction d) {
		MethodWriter.printOutMethod("SwitchField.boxEnters", b.toString() + ", " + d.toString());

		if (d == Direction.RIGHT) {

			MethodWriter.printOutQuestion("Is there a box or a worker on the field? B/W/X");

			String answer = MethodWriter.readFromStdin();

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

		this.setMovable(b);
		b.place(this);
		getNeighbor(d.getOpposite()).remove(b);

		if (movable == null)
			MethodWriter.printOutRet("null");
		else
			MethodWriter.printOutRet(movable.toString());

		return movable;
	}

	@Override
	public Movable workerEnters(Worker w, Direction d) {
		MethodWriter.printOutMethod("SwitchField.workerEnters", w.toString() + ", " + d.toString());

		if (d == Direction.RIGHT) {

			MethodWriter.printOutQuestion("Is there a box or a worker on the field? B/W/X");

			String answer = MethodWriter.readFromStdin();

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

		this.setMovable(w);
		w.place(this);
		getNeighbor(d.getOpposite()).remove(w);

		if (movable == null)
			MethodWriter.printOutRet("null");
		else
			MethodWriter.printOutRet(movable.toString());

		return movable;
	}

	@Override
	public void boxArrived(Box b) {
		MethodWriter.printOutMethod("SwitchField.boxArrived", b.toString());

		this.activate();

		hole.changeToActive();

		MethodWriter.printOutRet("");
	}

	public void activate() {
		MethodWriter.printOutMethod("SwitchField.activate", "");

		active = true;

		MethodWriter.printOutRet("");
	}

	public void deactivate() {
		MethodWriter.printOutMethod("SwitchField.deactivate", "");

		active = false;

		MethodWriter.printOutRet("");
	}

	public void remove(Movable m) {
		MethodWriter.printOutMethod("SwitchField.remove", m.toString());

		this.deactivate();

		MethodWriter.printOutRet("");
	}

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
