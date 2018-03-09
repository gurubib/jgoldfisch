package skeleton.game.logic;

public abstract class Movable {
	
	private Field field;
	
	public abstract void die();
	public abstract void pushBack(Direction d);
	public abstract void pushByBox(Box b, Direction d);
	public abstract void pushByWorker(Worker w, Direction d);
	public abstract void scorePoint(Direction d);
	public abstract void finalizeStep();
	
	public void setField(Field f) {
		field = f;
	}
	
	public void place(Field f) {
		System.out.println("Movable.place(f)");
		
		this.field = f;
	}
	
	public Field getField() {
		System.out.println("Movable.getField()");
		
		return this.field;
	}
}
