package skeleton.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Menu {
	
	private Map<Integer, MenuItem> menuItems = new HashMap<Integer, MenuItem>();
	
	public void addMenuItem(int number, String name) {
		menuItems.put(number, new MenuItem(name));
	}
	
	public MenuItem getMenuItem(int n) {
		return menuItems.get(n);
	}
	
	public void setDescForItem(int n, String desc) {
		menuItems.get(n).setDesc(desc);
	}
	
	public void drawMenu() {
		for(Entry<Integer, MenuItem> e : menuItems.entrySet()) {
			System.out.println(e.getValue().getDesc());
		}
	}
	
	public void chooseMenuItem(int n) {
		menuItems.get(n).execute();
	}
}