package skeleton;

import skeleton.menu.*;
import skeleton.game.logic.*;

public class Skeleton {

	public static void main(String[] args) {
		
		Menu m = new Menu();
		
		m.addMenuItem(1, "First");
		m.addMenuItem(2, "Second");
		m.addMenuItem(3, "Third");
		
		m.setDescForItem(2, "This is the second menu option.");
		
		m.drawMenu();
		
		Direction d = Direction.DOWN;
		
		System.out.println(d.getOpposite());
		
	}

}
