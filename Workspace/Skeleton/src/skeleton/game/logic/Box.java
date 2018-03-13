package skeleton.game.logic;

import skeleton.out.MethodWriter;

public class Box extends Movable {

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
		MethodWriter.printOutMethod("Box.pushByWorker", "w, d");
		
		
		Field f1 = getField().getNeighbor(d);
		Movable m = f1.boxEnters(this, d);
		
		if (m != null)
			m.pushByBox(this, d);
		
		finalizeStep();
		
		MethodWriter.printOutRet("");
	}

	@Override
	public void scorePoint(Direction d) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void finalizeStep() {						//LOL felesleges
		MethodWriter.printOutMethod("Box.finalizeStep", "");
		
		getField().boxArrived(this);
		
		MethodWriter.printOutRet("");
	}

}
