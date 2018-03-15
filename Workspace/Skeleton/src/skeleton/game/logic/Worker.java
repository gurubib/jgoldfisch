package skeleton.game.logic;

import skeleton.out.MethodWriter;

public class Worker extends Movable {
	
	private int points = 0;

	@Override
	public void die() {
		MethodWriter.printOutMethod("Worker.die",  "");

		this.getField().remove(this);
		
		MethodWriter.printOutRet("");
	}

	@Override
	public void pushBack(Direction d) {
		MethodWriter.printOutMethod("Worker.pushBack", d.toString());
		
		Field neighbor = this.getField().getNeighbor(d);
		
		Movable neighborMovable = neighbor.workerEnters(this, d);
		
		
		if (neighborMovable != null)
			neighborMovable.die();
		
		MethodWriter.printOutRet("");
	}

	@Override
	public void pushByBox(Box b, Direction d) {
		MethodWriter.printOutMethod("Worker.pushByBox", b.toString() + ", " + d.toString());
		
		Field neighbor = this.getField().getNeighbor(d);
		
		Movable neighborMovable = neighbor.workerEnters(this, d);
		
		if (neighborMovable == this)
			this.die();
		else if (neighborMovable != null) {
			neighborMovable.pushByWorker(this, d);
			this.finalizeStep();
		} else {
			this.finalizeStep();
		}
		
		MethodWriter.printOutRet("");
	}

	@Override
	public void pushByWorker(Worker w, Direction d) {
		MethodWriter.printOutMethod("Worker.pushByWorker", w.toString() + ", " + d.toString());
		
		w.goBack(d.getOpposite());
		
		MethodWriter.printOutRet("");
	}

	@Override
	public void scorePoint(Direction d) {
		MethodWriter.printOutMethod("Worker.scorePoint", d.toString());
		
		this.increasePoints();
		
		MethodWriter.printOutRet("");
	}
	
	public void control(Direction d) {
		MethodWriter.printOutMethod("Worker.control", "d");
		
		Field neighbor = this.getField().getNeighbor(d);
		
		Movable neighborMovable = neighbor.workerEnters(this, d);
		
		if (neighborMovable == this) {
			Field backwardNeighbor = this.getField().getNeighbor(d.getOpposite());
			backwardNeighbor.workerEnters(this, d.getOpposite());
		} else if (neighborMovable != null)
			neighborMovable.pushByWorker(this, d);
		
		this.finalizeStep();
		
		MethodWriter.printOutRet("");
	}
	
	@Override
	public void finalizeStep() {
		MethodWriter.printOutMethod("Worker.finalizeStep", "");
		
		getField().workerArrived(this);
		
		MethodWriter.printOutRet("");
	}
	
	public void goBack(Direction d) {
		MethodWriter.printOutMethod("Worker.goBack", "d");
		
		Field neighbor = this.getField().getNeighbor(d);
		
		Movable neighborMovable = neighbor.workerEnters(this, d);
		
		if (neighborMovable != null)
			neighborMovable.pushByWorker(this, d);
		
		MethodWriter.printOutRet("");
	}
	
	public void increasePoints() {
		MethodWriter.printOutMethod("Worker.increasePoint", "");
		
		points++;
		
		MethodWriter.printOutRet("");
	}

}
