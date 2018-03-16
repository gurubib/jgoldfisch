package skeleton.game.logic;

import skeleton.out.MethodWriter;

public class EndField extends Field {

	@Override
	public Movable boxEnters(Box b, Direction d) {
		MethodWriter.printOutMethod("EndField.boxEnters", b.toString() + ", " + d.toString());
		
		if (this.getMovable() == null) {

			MethodWriter.printOutQuestion("Is there a box or a worker on the field? B/X");

			String answer = MethodWriter.readFromStdin();

			switch (answer) {
			case "b":
				Box b2 = new Box();
				b2.setField(this);
				this.setMovable(b2);
				break;
			default:
				this.setMovable(null);
			}

		}
		
		b.place(this);
		
		Field backwardNeighbor = this.getNeighbor(d.getOpposite());
		Movable movable = this.getMovable();
		this.setMovable(b);
		backwardNeighbor.remove(b);
		
		if (movable != null) {
			MethodWriter.printOutRet(b.toString());
			return b;
		}
		
		backwardNeighbor.getMovable().scorePoint(d.getOpposite());

		MethodWriter.printOutRet("null");
		return null;
	}

	@Override
	public Movable workerEnters(Worker w, Direction d) {
		MethodWriter.printOutMethod("EndField.workerEnters", w.toString() + ", " + d.toString());
		
		if (this.getMovable() == null) {

			MethodWriter.printOutQuestion("Is there a box or a worker on the field? B/X");

			String answer = MethodWriter.readFromStdin();

			switch (answer) {
			case "b":
				Box b2 = new Box();
				b2.setField(this);
				this.setMovable(b2);
				break;
			default:
				this.setMovable(null);
			}

		}
		
		w.place(this);
		
		Field backwardNeighbor = this.getNeighbor(d.getOpposite());
		Movable movable = this.getMovable();
		this.setMovable(w);
		backwardNeighbor.remove(w);
		
		if (movable != null) {
			MethodWriter.printOutRet(w.toString());
			return w;
		}
		this.setMovable(w);
		MethodWriter.printOutRet("null");
		return null;
	}

}
