package skeleton.game.logic;

public class Worker extends Movable {

	private int points = 0;
	
/*	public Worker(Field field) {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scorePoint(Direction d) {
		// TODO Auto-generated method stub
		
	}
	
	public void control(Direction d) {
		System.out.println("Worker.control(d)");
		
		Field f1 = getField().getNeighbor(d);
		
		Movable m = f1.workerEnters(this, d);
		
		if (m != null)
			m.pushByWorker(this, d);
		
		finalizeStep();
	}
	
	@Override
	public void finalizeStep() {						//LOL felesleges
		System.out.println("Worker.finalizeStep(d)");
		
		getField().workerArrived(this);
	}
	
	public void goBack(Direction d) {
		//TODO
	}
	
	public void increasePoints() {
		//TODO
	}

}
