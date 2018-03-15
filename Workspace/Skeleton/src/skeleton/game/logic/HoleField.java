package skeleton.game.logic;

import skeleton.out.MethodWriter;

public class HoleField extends Field {
	private SwitchField switchField;

	
	
	public void setSwitchField(SwitchField switchField) {
		this.switchField = switchField;
	}

	@Override
	public Movable boxEnters(Box b, Direction d) {
		MethodWriter.printOutMethod("HoleField.boxEnters", b.toString() + ", " + d.toString());
		
		
		Movable movable = getMovable();
		this.setMovable(b);
		
		b.place(this);
		this.getNeighbor(d.getOpposite()).remove(b);
		if (movable == null)
			MethodWriter.printOutRet("null");
		else
			MethodWriter.printOutRet(movable.toString());
		return movable;
	}

	@Override
	public Movable workerEnters(Worker w, Direction d) {
		MethodWriter.printOutMethod("HoleField.workerEnters", w.toString() + ", " + d.toString());
		
		Movable movable = getMovable();
		this.setMovable(w);
		
		w.place(this);
		this.getNeighbor(d.getOpposite()).remove(w);
		if (movable == null)
			MethodWriter.printOutRet("null");
		else
			MethodWriter.printOutRet(movable.toString());
		return movable;
	}
	
	@Override
	public void boxArrived(Box b) {
		MethodWriter.printOutMethod("HoleField.boxArrived", b.toString());
	
		if (switchField == null) {
			execute(b);
		}
		else {
			switchField.holeInteracted(b);
		}
		MethodWriter.printOutRet("");
			
	}
	
	@Override
	public void workerArrived(Worker w) {
		MethodWriter.printOutMethod("HoleField.workerArrived", w.toString());
		
		if (switchField == null) {
			execute(w);
		}
		else {
			switchField.holeInteracted(w);
		}
		MethodWriter.printOutRet("");
			
	}
	
	public void execute(Movable m) {
		MethodWriter.printOutMethod("HoleField.execute", m.toString());
		m.die();
		MethodWriter.printOutRet("");
	}
	
	public void changeToActive() {
		MethodWriter.printOutMethod("HoleField.changeToActive","");
		if (getMovable() != null)
			execute(getMovable());
		MethodWriter.printOutRet("");
	}

}
