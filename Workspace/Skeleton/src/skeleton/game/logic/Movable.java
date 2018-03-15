package skeleton.game.logic;

import skeleton.out.MethodWriter;

public abstract class Movable {
	
	private String skeletonName;
	
	private Field field;
	
	public abstract void die();
	public abstract void pushBack(Direction d);
	public abstract void pushByBox(Box b, Direction d);
	public abstract void pushByWorker(Worker w, Direction d);
	public abstract void scorePoint(Direction d);
	public abstract void finalizeStep();
	
	@Override
	public String toString() {
		return skeletonName;
	}
	public void setSkeletonName(String skeletonName) {
		this.skeletonName = skeletonName;
	}
	public void setField(Field f) {
		field = f;
	}
	
	
	
	public void place(Field f) {
		MethodWriter.printOutMethod("Movable.place", f.toString());
		
		this.field = f;
		
		MethodWriter.printOutRet("");
	}
	
	public Field getField() {
		MethodWriter.printOutMethod("Movable.getField", "");
		
		MethodWriter.printOutRet(this.field.toString());
		return this.field;
	}
}
