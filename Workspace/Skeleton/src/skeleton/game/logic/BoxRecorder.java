package skeleton.game.logic;

import java.util.ArrayList;
import java.util.List;

//Elvileg nem kell a szkeletonban figyelni, hogy vege e a jateknak

public class BoxRecorder {
	
	private List<Field> fixFields = new ArrayList<Field>();
	
	public List<Field> getFixFields() {
		return fixFields;
	}

	public void setFixFields(List<Field> fixFields) {
		this.fixFields = fixFields;
	}

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
