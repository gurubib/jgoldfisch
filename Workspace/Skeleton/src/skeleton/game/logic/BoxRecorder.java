package skeleton.game.logic;

import java.util.ArrayList;
import java.util.List;

import skeleton.out.MethodWriter;


//Elvileg nem kell a szkeletonban figyelni, hogy vege e a jateknak

public class BoxRecorder {
	
	private List<Field> fixFields = new ArrayList<Field>();
	
	public void init(List<Field> walls) {
		fixFields = walls;
	}
	
	public void endFieldOccupied(Field f) {
		//TODO
	}
	
	public void update(Field f) {
		//TODO
	}
	
	public void checkRecordWith(Field f) {
	}
}
