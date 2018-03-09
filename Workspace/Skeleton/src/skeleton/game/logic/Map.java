package skeleton.game.logic;

import java.util.ArrayList;
import java.util.List;

public class Map {
	
	private List<Field> fields = new ArrayList<Field>();
	
	public void loadMap(int n) {
		//TODO
	}
	
	public void manualLoad(List<Field> fields) {
		this.fields = fields;
	}
	
	public void confField(int n, Field f) {
		fields.set(n, f);
	}
	
	public Field getField(int n) {
		return fields.get(n);
	}
}
