package skeleton;

import skeleton.menu.*;

import java.util.ArrayList;
import java.util.List;

import skeleton.game.logic.*;

public class Skeleton {

	public static void main(String[] args) {
		
		Menu m = new Menu();
		
		m.addMenuItem(1, "First");
		m.addMenuItem(2, "Second");
		m.addMenuItem(3, "Third");
		
		m.setDescForItem(2, "This is the second menu option.");
		
		initMenuItemMaps(m);
		
		m.drawMenu();
		
		Direction d = Direction.DOWN;
		
		System.out.println(d.getOpposite());
		
	}
	
	private static void initMenuItemMaps(Menu m) {
		List<Field> fl1 = new ArrayList<Field>();
		
		fl1.add(new SimpleField());
		fl1.add(new SimpleField());
		fl1.add(new SimpleField());
		
		fl1.get(0).setMovable(new Worker());
		fl1.get(1).setMovable(new Box());
		
		m.setMenuItemMap(1, fl1);
	}

}
