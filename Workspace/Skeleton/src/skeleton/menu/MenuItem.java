package skeleton.menu;

import java.util.List;

import skeleton.game.logic.Direction;
import skeleton.game.logic.Field;
import skeleton.game.logic.Game;
import skeleton.game.logic.Worker;

public class MenuItem {
	
	private String name;
	private String desc;
	private skeleton.game.logic.Map map = new skeleton.game.logic.Map();
	private Game game = Game.getInstance();
	
	public MenuItem(String name) {
		this.name = name;
		this.desc = name.toLowerCase();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public skeleton.game.logic.Map getMap() {
		return map;
	}

	public void init(List<Field> fields) {
		map.manualLoad(fields);
		
		Worker mainWorker = (Worker)fields.get(0).getMovable();
		
		game.init(mainWorker);
	}
	
	public void execute() {
		game.getMainWorker().control(Direction.RIGHT);
	}
}
