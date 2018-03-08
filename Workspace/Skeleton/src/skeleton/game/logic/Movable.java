package skeleton.game.logic;

public abstract class Movable {
	
	private Field field;
	
	public abstract void die();
	public abstract void pushBack(Direction d);
	public abstract void pushByBox(Box b, Direction d);
	public abstract void pushByWorker(Worker w, Direction d);
	public abstract void scorePoint(Direction d);
	
	public Movable(Field field) {
		this.field = field;
	}
	
	public void finalizeStep() {
		//TODO
	}
	
	public void place(Field f) {
		this.field = f;
	}
	
	public Field getField() {
		return this.field;
	}
}
