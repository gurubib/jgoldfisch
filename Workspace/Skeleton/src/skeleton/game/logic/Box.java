package skeleton.game.logic;

import skeleton.out.MethodWriter;

public class Box extends Movable {

	@Override
	public void die() {
		MethodWriter.printOutMethod("Box.die", "");
		
		this.getField().remove(this);
		
		MethodWriter.printOutRet("");
	}

	@Override
	public void pushBack(Direction d) {
		MethodWriter.printOutMethod("Box.pushBack", d.toString());
		
		Field neighbor = getField().getNeighbor(d);
		
		Movable neighborMovable = neighbor.boxEnters(this, d);
		
		if (neighborMovable != null)
			neighborMovable.pushBack(d);
		
		MethodWriter.printOutRet("");
	}

	@Override
	public void pushByBox(Box b, Direction d) {
		MethodWriter.printOutMethod("Box.pushByBox", b.toString() + ", " + d.toString());
		
		Field neighbor = getField().getNeighbor(d);
		
		Movable neighborMovable = neighbor.boxEnters(this, d);
		
		if (neighborMovable == this) {
			Field backwardNeighbor = neighbor.getNeighbor(d.getOpposite());
			Movable backwardMovable = backwardNeighbor.boxEnters(this, d.getOpposite());
			
			backwardMovable.pushBack(d.getOpposite());
		} else if (neighborMovable != null) {
			neighborMovable.pushByBox(this, d);
		}
		
		this.finalizeStep();

		MethodWriter.printOutRet("");
	}

	@Override
	public void pushByWorker(Worker w, Direction d) {
		MethodWriter.printOutMethod("Box.pushByWorker", w.toString() + ", " + d.toString());
		
		Field neighbor = getField().getNeighbor(d);
		
		Movable neighborMovable = neighbor.boxEnters(this, d);
		
		if (neighborMovable == this) {
			Field backwardNeighbor = neighbor.getNeighbor(d.getOpposite());
			Movable backwardMovable = backwardNeighbor.boxEnters(this, d.getOpposite());
			
			backwardMovable.pushBack(d.getOpposite());
		} else if (neighborMovable != null) {
			neighborMovable.pushByBox(this, d);
		}
		
		this.finalizeStep();

		MethodWriter.printOutRet("");
	}

	@Override
	public void scorePoint(Direction d) {
		MethodWriter.printOutMethod("Box.finalizeStep", "");
		
		Field neighbor = this.getField().getNeighbor(d);
		
		Movable neighborMovable = neighbor.getMovable();
		
		neighborMovable.scorePoint(d);
		
		MethodWriter.printOutRet("");
	}
	
	@Override
	public void finalizeStep() {
		MethodWriter.printOutMethod("Box.finalizeStep", "");
		
		getField().boxArrived(this);
		
		MethodWriter.printOutRet("");
	}

}
