package skeleton.game.logic;

public class Box extends Movable {

/*	public Box(Field field) {
		super(field);
	}*/

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pushBack(Direction d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pushByBox(Box b, Direction d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pushByWorker(Worker w, Direction d) {
		System.out.println("Box.pushByWorker(w, d)");
		
		Field f1 = getField().getNeighbor(d);
		Movable m = f1.boxEnters(this, d);
		
		if (m != null)
			m.pushByBox(this, d);
		
		finalizeStep();
		
	}

	@Override
	public void scorePoint(Direction d) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void finalizeStep() {						//LOL felesleges
		System.out.println("Worker.finalizeStep(d)");
		
		getField().boxArrived(this);
	}

}
