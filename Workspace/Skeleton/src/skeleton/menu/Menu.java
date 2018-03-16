package skeleton.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import skeleton.game.logic.Field;
import skeleton.game.logic.*;

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
		MenuItemInitializier.initMenuItem(n, menuItems.get(n));
		
		menuItems.get(n).execute();
	}
}

class MenuItemInitializier {
	
	public static void initMenuItem(int menuNo, MenuItem item) {
		
		switch (menuNo) {
			case 1:
				menuItemInit_1(item);
				break;
			case 2:
				menuItemInit_2(item);
				break;
			case 3:
				menuItemInit_3(item);
				break;
			case 4: 
				menuItemInit_4(item);
				break;
			case 5:
				menuItemInit_5(item);
				break;
			case 6:
				menuItemInit_6(item);
				break;
			case 7: 
				menuItemInit_7(item);
				break;
			case 8:
				menuItemInit_8(item);
				break;
			case 9: 
				menuItemInit_9(item);
				break;
			case 10:
				menuItemInit_10(item);
				break;
			default:
				menuItemInit_1(item);
				break;	
		}
	}
	
		
	private static void menuItemInit_1(MenuItem item) {
		List<Field> fields = new ArrayList<Field>();
		
		SimpleField f1 = new SimpleField();
		WallField wf = new WallField();
		
		f1.setSkeletonName("f1");
		wf.setSkeletonName("wf");
		
		f1.setNeighbor(Direction.RIGHT, wf);
		wf.setNeighbor(Direction.LEFT, f1);
		
		Worker w = new Worker();
		w.setSkeletonName("w");
		w.setField(f1);
		
		f1.setMovable(w);
		
		fields.add(f1);
		fields.add(wf);
		
		item.init(fields);
	}

	private static void menuItemInit_2(MenuItem item) {
		List<Field> fields = new ArrayList<Field>();
		
		SimpleField f1 = new SimpleField();
		HoleField hf = new HoleField();
		
		f1.setSkeletonName("f1");
		hf.setSkeletonName("hf");
		
		f1.setNeighbor(Direction.RIGHT, hf);
		hf.setNeighbor(Direction.LEFT, f1);
		
		Worker w = new Worker();
		w.setSkeletonName("w");
		w.setField(f1);
		
		f1.setMovable(w);
		
		fields.add(f1);
		fields.add(hf);
		
		item.init(fields);
	}
	
	private static void menuItemInit_3(MenuItem item) {
		List<Field> fields = new ArrayList<Field>();
		
		SimpleField f1 = new SimpleField();
		SimpleField f2 = new SimpleField();
		HoleField hf = new HoleField();
		
		f1.setSkeletonName("f1");
		f2.setSkeletonName("f2");
		hf.setSkeletonName("hf");
		
		f1.setNeighbor(Direction.RIGHT, f2);
		f2.setNeighbor(Direction.LEFT, f1);
		f2.setNeighbor(Direction.RIGHT, hf);
		hf.setNeighbor(Direction.LEFT, f2);
		
		Worker w = new Worker();
		w.setSkeletonName("w");
		w.setField(f1);
		
		f1.setMovable(w);
		
		fields.add(f1);
		fields.add(f2);
		fields.add(hf);
		
		item.init(fields);
	}
	
	private static void menuItemInit_4(MenuItem item) {
		List<Field> fields = new ArrayList<Field>();
		
		SimpleField f1 = new SimpleField();
		SimpleField f2 = new SimpleField();
		HoleField hf = new HoleField();
		SwitchField sw = new SwitchField();
		
		f1.setSkeletonName("f1");
		f2.setSkeletonName("f2");
		hf.setSkeletonName("hf");
		sw.setSkeletonName("sw");
		
		f1.setNeighbor(Direction.RIGHT, f2);
		f2.setNeighbor(Direction.LEFT, f1);
		f2.setNeighbor(Direction.RIGHT, hf);
		hf.setNeighbor(Direction.LEFT, f2);
		
		hf.setSwitchField(sw);
		sw.setHole(hf);
		
		Worker w = new Worker();
		w.setSkeletonName("w");
		w.setField(f1);
		
		f1.setMovable(w);
		
		fields.add(f1);
		fields.add(f2);
		fields.add(hf);
		
		item.init(fields);
	}
	
	private static void menuItemInit_5(MenuItem item) {
		
	}
	
	private static void menuItemInit_6(MenuItem item) {
		
	}
	
	private static void menuItemInit_7(MenuItem item) {
		
	}
	
	private static void menuItemInit_8(MenuItem item) {
		
	}
	
	private static void menuItemInit_9(MenuItem item) {
		
	}
	
	private static void menuItemInit_10(MenuItem item) {
		
	}
}