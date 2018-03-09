package skeleton.game.logic;

import java.util.HashMap;
import java.util.Map;

public abstract class Field {
	
	private Map<Direction, Field> neighbors = new HashMap<Direction, Field>();
	private Movable movable = null;
	
	
	public abstract Movable boxEnters(Box b, Direction d);
	public abstract Movable workerEnters(Worker w, Direction d);
	
	public Field getNeighbor(Direction d) {
		return neighbors.get(d);
	}
	
	public void setMovable(Movable m) {
		movable = m;
	}
	
	public Movable getMovable() {
		return movable;
	}
	
	public void remove() {
		//TODO
	}
	
	public void updateRecorder() {
		//TODO
	}
	
	public void boxArrived(Box b) {
		//TODO
	}
	
	public void workerArrived(Worker w) {
		//TODO
	}
	
}
