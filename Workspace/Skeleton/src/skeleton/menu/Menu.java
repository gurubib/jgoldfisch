package skeleton.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import skeleton.game.logic.*;

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
	
	public void setMenuItemMap(int n, List<Field> fields) {
		menuItems.get(n).setMap(fields);
	}
	
	public void chooseMenuItem(int n) {
		menuItems.get(n).execute();
	}
}