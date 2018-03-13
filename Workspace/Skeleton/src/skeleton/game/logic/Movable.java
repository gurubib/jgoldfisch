package skeleton.game.logic;

import skeleton.out.MethodWriter;

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
		MethodWriter.printOutMethod("Movable.place", "f");
		
		this.field = f;
		
		MethodWriter.printOutRet("");
	}
	
	public Field getField() {
		MethodWriter.printOutMethod("Movable.getField", "");
		
		MethodWriter.printOutRet("f");
		return this.field;
	}
}
