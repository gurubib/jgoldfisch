package skeleton.game.logic;

import skeleton.out.MethodWriter;

public class WallField extends Field {

	@Override
	public Movable boxEnters(Box b, Direction d) {
		MethodWriter.printOutMethod("WallField.boxEnters", b.toString() + ", " + d.toString());
		
		b.place(this);
		getNeighbor(d.getOpposite()).remove(b);
		
		MethodWriter.printOutRet(b.toString());
		return b;
	}

	@Override
	public Movable workerEnters(Worker w, Direction d) {
		MethodWriter.printOutMethod("WallField.workerEnters", w.toString() + ", " + d.toString());
		
		w.place(this);
		getNeighbor(d.getOpposite()).remove(w);
		
		MethodWriter.printOutRet(w.toString());
		return w;
	}

}
