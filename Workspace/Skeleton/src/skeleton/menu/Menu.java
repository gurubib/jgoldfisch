package skeleton.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Menu {
	
	private Map<Integer, MenuItem> menuItems = new HashMap<Integer, MenuItem>();
	
	public void addMenuItem(int number, String name) {
		menuItems.put(number, new MenuItem(name));
	}
	
	public void setDescForItem(int n, String desc) {
		menuItems.get(n).setDesc(desc);
	}
	
	public void drawMenu() {
		for(Entry<Integer, MenuItem> e : menuItems.entrySet()) {
			System.out.println(e.getKey() + ": " + e.getValue().getName() + " - " + e.getValue().getDesc());
		}
	}
}

class MenuItem {
	
	private String name;
	private String desc;
	
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
}