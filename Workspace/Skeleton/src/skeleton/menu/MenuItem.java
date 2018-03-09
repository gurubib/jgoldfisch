package skeleton.menu;

import java.util.List;

import skeleton.game.logic.Field;

public class MenuItem {
	
	private String name;
	private String desc;
	private skeleton.game.logic.Map map = new skeleton.game.logic.Map();
	
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

	public void setMap(List<Field> fields) {
		map.manualLoad(fields);
	}
	
	public void execute() {
		System.out.println(name + " MenuItem selected");
	}
}
