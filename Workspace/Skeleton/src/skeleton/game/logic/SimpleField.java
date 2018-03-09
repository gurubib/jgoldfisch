package skeleton.game.logic;

public class SimpleField extends Field {

	@Override
	public Movable boxEnters(Box b, Direction d) {
		System.out.println("SimpleField.boxEnters(w, d)");
		
		b.place(this);
		getNeighbor(d.getOpposite()).remove();
		
		Movable movable = getMovable();
		
		if (movable != null)
			return movable;
		else
			return null;
	}

	@Override
	public Movable workerEnters(Worker w, Direction d) {
		System.out.println("SimpleField.workerEnters(w, d)");
		
		w.place(this);
		getNeighbor(d.getOpposite()).remove();
		
		Movable movable = getMovable();
		
		if (movable != null)
			return movable;
		else
			return null;
	}
	
	@Override
	public void boxArrived(Box b) {
		System.out.println("SimpleField.boxArrived(b)");
		
		
	}
	
	@Override
	public void workerArrived(Worker w) {
		System.out.println("SimpleField.boxArrived(b)");
	}

}
